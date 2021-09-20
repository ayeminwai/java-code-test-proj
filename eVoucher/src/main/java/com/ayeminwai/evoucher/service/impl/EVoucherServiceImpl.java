package com.ayeminwai.evoucher.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ayeminwai.evoucher.beans.req.ChangeStatusReq;
import com.ayeminwai.evoucher.beans.req.EVoucherCreateReq;
import com.ayeminwai.evoucher.beans.req.EVoucherUpdateReq;
import com.ayeminwai.evoucher.beans.res.ChangeStatusRes;
import com.ayeminwai.evoucher.beans.res.EVoucherCreateRes;
import com.ayeminwai.evoucher.beans.res.EVoucherUpdateRes;
import com.ayeminwai.evoucher.constant.APICodes;
import com.ayeminwai.evoucher.constant.IMessages;
import com.ayeminwai.evoucher.constant.ISystem;
import com.ayeminwai.evoucher.exception.EVoucherException;
import com.ayeminwai.evoucher.model.BuyType;
import com.ayeminwai.evoucher.model.EVoucher;
import com.ayeminwai.evoucher.model.EVoucherDetails;
import com.ayeminwai.evoucher.model.PaymentMethod;
import com.ayeminwai.evoucher.model.PaymentMethodDiscount;
import com.ayeminwai.evoucher.repository.EVoucherDetailsRepository;
import com.ayeminwai.evoucher.repository.EVoucherRepository;
import com.ayeminwai.evoucher.service.EVoucherService;
import com.ayeminwai.evoucher.util.DateUtil;
import com.ayeminwai.evoucher.util.EVoucherUtil;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class EVoucherServiceImpl implements EVoucherService {

	@Autowired
	EVoucherRepository eVoucherRepository;
	
	@Autowired
	EVoucherDetailsRepository eVoucherDetailsRepository;
	
	EVoucherUtil eVoucherUtil;
	
	@Override
	public HashMap createEVoucher(EVoucherCreateReq request) {
		log.info("eVoucherCreate service");
		
		try {
			Date createdDate = new Date();
			
			EVoucher evoucher = new EVoucher();
			
			evoucher.setTitle(request.getTitle());
			evoucher.setDescription(request.getDescription());
			evoucher.setExpireDate(DateUtil.convertDateStringToDate(request.getExpireDate()));
			evoucher.setImage(request.getImage());// to develop
			
			String strAmount = request.getAmount();
			BigDecimal amount = eVoucherUtil.getBigAmount(strAmount);
			evoucher.setAmount(amount);
			
			Long paymentMethodId = Long.valueOf(request.getPaymentMethod());
			PaymentMethod paymentMethod = new PaymentMethod();
			paymentMethod.setPmId(paymentMethodId);
			evoucher.setPaymentMethod(paymentMethod);
			
			Long paymentMethodDiscountId = Long.valueOf(request.getPaymentMethodDiscount());
			PaymentMethodDiscount paymentMethodDiscount = new PaymentMethodDiscount();
			paymentMethodDiscount.setPmdId(paymentMethodDiscountId);
			evoucher.setPaymentMethodDiscount(paymentMethodDiscount);
			
			String strQty = request.getQuantity();
			Long quantity = Long.valueOf(strQty);
			evoucher.setQuantity(quantity);
			
			Long buyTypeId = Long.valueOf(request.getBuyType());
			BuyType buyType = new BuyType();
			buyType.setBtId(buyTypeId);
			evoucher.setBuyType(buyType);
			
			evoucher.setEvoucherStatus(ISystem.IStatus.ACTIVE);
			evoucher.setCreatedDate(createdDate);
			
			eVoucherRepository.save(evoucher);
			
			Long phoneNo = Long.valueOf(request.getPhoneNo());
			Long maxLimit = Long.valueOf(request.getMaxEVoucherLimit());
			Long giftPerUserLimit = Long.valueOf(request.getGiftPerUserLimit());
			
			List<EVoucherDetails> evoucherDetailsList = new ArrayList<EVoucherDetails>();
			
			for (int i = 0; i < quantity; i++) {
				
				EVoucherDetails evoucherDetails = new EVoucherDetails();
				
				evoucherDetails.setBuyType(buyType);
				evoucherDetails.setEvoucher(evoucher);
				evoucherDetails.setName(request.getName());
				evoucherDetails.setPhoneNo(phoneNo);
				evoucherDetails.setMaxLimit(maxLimit);
				evoucherDetails.setGiftPerUserLimit(giftPerUserLimit);
				evoucherDetails.setEvoucherDetailsStatus(ISystem.IStatus.ACTIVE);
				
				evoucherDetailsList.add(evoucherDetails);
			}

			eVoucherDetailsRepository.saveAll(evoucherDetailsList);
			
			// after success of insert
			EVoucherCreateRes objRes = new EVoucherCreateRes();

			objRes.getCoreSuccessResponse();
			objRes.setEvoucherId(evoucher.getEvoucherId().toString());

			// send the response
			HashMap returnMap = new HashMap();
			returnMap.put(ISystem.IKey.IResponse.RESPONSE, objRes);

			return returnMap;
		} catch (EVoucherException e) {
			throw e;
		} catch (Exception e) {
			log.error(e);
			throw new EVoucherException(HttpStatus.INTERNAL_SERVER_ERROR, IMessages.IError.INTERNAL_ERROR, APICodes.APPL_ERR, e);
		}
	}
	
	@Override
	public HashMap updateEVoucher(EVoucherUpdateReq request) {
		
		log.info("updateEVoucher service");
		
		try {
			Date updatedDate = new Date();
			
			Long evoucherId = Long.valueOf(request.getEvoucherId());
			EVoucher evoucher = eVoucherRepository.findById(evoucherId).get();
			
			if(evoucher == null) {
				throw new EVoucherException(HttpStatus.BAD_REQUEST, IMessages.IError.BAD_REQUEST, APICodes.INVALID_EVOUCHER);
			}

			if(!ISystem.IStatus.ACTIVE.equals(evoucher.getEvoucherStatus()))
				throw new EVoucherException(HttpStatus.BAD_REQUEST, IMessages.IError.BAD_REQUEST, APICodes.EVOUCHER_NOT_ACTIVE);
			
			eVoucherDetailsRepository.deleteInBatch(evoucher.getEvoucherDetails());
			
			evoucher.setTitle(request.getTitle());
			evoucher.setDescription(request.getDescription());
			evoucher.setExpireDate(DateUtil.convertDateStringToDate(request.getExpireDate()));
			evoucher.setImage(request.getImage());// to develop
			
			String strAmount = request.getAmount();
			BigDecimal amount = eVoucherUtil.getBigAmount(strAmount);
			evoucher.setAmount(amount);
			
			Long paymentMethodId = Long.valueOf(request.getPaymentMethod());
			PaymentMethod paymentMethod = new PaymentMethod();
			paymentMethod.setPmId(paymentMethodId);
			evoucher.setPaymentMethod(paymentMethod);
			
			Long paymentMethodDiscountId = Long.valueOf(request.getPaymentMethodDiscount());
			PaymentMethodDiscount paymentMethodDiscount = new PaymentMethodDiscount();
			paymentMethodDiscount.setPmdId(paymentMethodDiscountId);
			evoucher.setPaymentMethodDiscount(paymentMethodDiscount);
			
			String strQty = request.getQuantity();
			Long quantity = Long.valueOf(strQty);
			evoucher.setQuantity(quantity);
			
			Long buyTypeId = Long.valueOf(request.getBuyType());
			BuyType buyType = new BuyType();
			buyType.setBtId(buyTypeId);
			evoucher.setBuyType(buyType);
			
			evoucher.setUpdatedDate(updatedDate);
			
			eVoucherRepository.save(evoucher);
			
			Long phoneNo = Long.valueOf(request.getPhoneNo());
			Long maxLimit = Long.valueOf(request.getMaxEVoucherLimit());
			Long giftPerUserLimit = Long.valueOf(request.getGiftPerUserLimit());
			
			List<EVoucherDetails> evoucherDetailsList = new ArrayList<EVoucherDetails>();
			
			for (int i = 0; i < quantity; i++) {
				
				EVoucherDetails evoucherDetails = new EVoucherDetails();
				
				evoucherDetails.setBuyType(buyType);
				evoucherDetails.setEvoucher(evoucher);
				evoucherDetails.setName(request.getName());
				evoucherDetails.setPhoneNo(phoneNo);
				evoucherDetails.setMaxLimit(maxLimit);
				evoucherDetails.setGiftPerUserLimit(giftPerUserLimit);
				evoucherDetails.setEvoucherDetailsStatus(ISystem.IStatus.ACTIVE);
				
				evoucherDetailsList.add(evoucherDetails);
			}

			eVoucherDetailsRepository.saveAll(evoucherDetailsList);
			
			
			// after success of update
			EVoucherUpdateRes objRes = new EVoucherUpdateRes();

			objRes.getCoreSuccessResponse();
			objRes.setEvoucherId(evoucher.getEvoucherId().toString());

			// send the response
			HashMap returnMap = new HashMap();
			returnMap.put(ISystem.IKey.IResponse.RESPONSE, objRes);

			return returnMap;

		} catch (EVoucherException e) {
			throw e;
		} catch (Exception e) {
			log.error(e);
			throw new EVoucherException(HttpStatus.INTERNAL_SERVER_ERROR, IMessages.IError.INTERNAL_ERROR, APICodes.APPL_ERR, e);
		}
	}

	@Override
	public HashMap<String, String> setInActive(ChangeStatusReq request) {
		log.info("updateEVoucher service");
		
		try {
			Date updatedDate = new Date();
			
			Long evoucherId = Long.valueOf(request.getEvoucherId());
			EVoucher evoucher = eVoucherRepository.findById(evoucherId).get();
			
			if(evoucher == null) {
				throw new EVoucherException(HttpStatus.BAD_REQUEST, IMessages.IError.BAD_REQUEST, APICodes.INVALID_EVOUCHER);
			}

			if(ISystem.IStatus.INACTIVE.equals(evoucher.getEvoucherStatus()))
				throw new EVoucherException(HttpStatus.BAD_REQUEST, IMessages.IError.BAD_REQUEST, APICodes.EVOUCHER_NOT_ACTIVE);
			
			
			
			evoucher.setUpdatedDate(updatedDate);
			evoucher.setEvoucherStatus(ISystem.IStatus.INACTIVE);
			
			for (EVoucherDetails evoucherDetails : evoucher.getEvoucherDetails()) {
				
				if(ISystem.IStatus.ACTIVE.equals(evoucherDetails.getEvoucherDetailsStatus())) {
					evoucherDetails.setEvoucherDetailsStatus(ISystem.IStatus.INACTIVE);
				}
				
			}
			
			eVoucherRepository.save(evoucher);
			
			// after success of update
			ChangeStatusRes objRes = new ChangeStatusRes();

			objRes.getCoreSuccessResponse();

			// send the response
			HashMap returnMap = new HashMap();
			returnMap.put(ISystem.IKey.IResponse.RESPONSE, objRes);

			return returnMap;

		} catch (EVoucherException e) {
			throw e;
		} catch (Exception e) {
			log.error(e);
			throw new EVoucherException(HttpStatus.INTERNAL_SERVER_ERROR, IMessages.IError.INTERNAL_ERROR, APICodes.APPL_ERR, e);
		}
	}
	
	@Autowired
	public void setEVoucherUtil(EVoucherUtil eVoucherUtil) {
		this.eVoucherUtil = eVoucherUtil;
	}

}
