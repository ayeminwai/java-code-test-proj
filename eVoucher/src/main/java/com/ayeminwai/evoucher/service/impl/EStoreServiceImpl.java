package com.ayeminwai.evoucher.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ayeminwai.evoucher.beans.req.CheckoutReq;
import com.ayeminwai.evoucher.beans.req.EVoucherDetailsListReq;
import com.ayeminwai.evoucher.beans.req.EVoucherListReq;
import com.ayeminwai.evoucher.beans.req.PaymentMethodsReq;
import com.ayeminwai.evoucher.beans.req.PaymentReq;
import com.ayeminwai.evoucher.beans.req.PurchaseHistoryReq;
import com.ayeminwai.evoucher.beans.res.CheckoutRes;
import com.ayeminwai.evoucher.beans.res.EVoucherDetailsListRes;
import com.ayeminwai.evoucher.beans.res.EVoucherDetailsRes;
import com.ayeminwai.evoucher.beans.res.EVoucherListRes;
import com.ayeminwai.evoucher.beans.res.EVoucherRes;
import com.ayeminwai.evoucher.beans.res.PaymentMethodRes;
import com.ayeminwai.evoucher.beans.res.PaymentMethodsRes;
import com.ayeminwai.evoucher.beans.res.PaymentRes;
import com.ayeminwai.evoucher.beans.res.Purchase;
import com.ayeminwai.evoucher.beans.res.PurchaseHistoryRes;
import com.ayeminwai.evoucher.constant.APICodes;
import com.ayeminwai.evoucher.constant.IMessages;
import com.ayeminwai.evoucher.constant.ISystem;
import com.ayeminwai.evoucher.exception.EVoucherException;
import com.ayeminwai.evoucher.model.EVoucher;
import com.ayeminwai.evoucher.model.EVoucherDetails;
import com.ayeminwai.evoucher.model.Payment;
import com.ayeminwai.evoucher.model.PaymentMethod;
import com.ayeminwai.evoucher.model.PaymentMethodDiscount;
import com.ayeminwai.evoucher.repository.EVoucherDetailsRepository;
import com.ayeminwai.evoucher.repository.EVoucherRepository;
import com.ayeminwai.evoucher.repository.PaymentMethodRepository;
import com.ayeminwai.evoucher.repository.PaymentRepository;
import com.ayeminwai.evoucher.service.EStoreService;
import com.ayeminwai.evoucher.util.DateUtil;
import com.ayeminwai.evoucher.util.EVoucherUtil;
import com.ayeminwai.evoucher.util.QRCodeGen;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class EStoreServiceImpl implements EStoreService {

	@Autowired
	EVoucherRepository eVoucherRepository;
	
	@Autowired
	EVoucherDetailsRepository eVoucherDetailsRepository;
	
	@Autowired
	PaymentMethodRepository paymentMethodRepository;
	
	@Autowired
	PaymentRepository paymentRepository;
	
	EVoucherUtil eVoucherUtil;
	
	QRCodeGen qrCodeGen;
	
	
	
	@Override
	public HashMap eVouchers(EVoucherListReq request) {
		log.info("eVouchers service");

		try {
			
			List<EVoucher> eVoucherList = eVoucherRepository.findAllByEvoucherStatus(ISystem.IStatus.ACTIVE);
			
			List<EVoucherRes> eVouchers = new ArrayList<EVoucherRes>();
			
			if (eVoucherList != null) {
				
				eVouchers = eVoucherList.stream()
						.map(obj -> new EVoucherRes(obj.getEvoucherId().toString(), obj.getTitle(),
								obj.getDescription(), DateUtil.convertDateToDateString(obj.getExpireDate()),
								obj.getImage(), obj.getAmount().toString(), obj.getPaymentMethod().getPmId().toString(),
								obj.getPaymentMethodDiscount().getPmdId().toString(), obj.getQuantity().toString(),
								obj.getBuyType().getBtId().toString()))
						.collect(Collectors.toList());

			}

			// after success of insert
			EVoucherListRes objRes = new EVoucherListRes();
			
			objRes.getCoreSuccessResponse();
			
			objRes.setEvouchers(eVouchers);

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
	public HashMap eVoucherDetails(EVoucherDetailsListReq request) {
		log.info("eVoucherDetails service");
		
		try {
			
			Long evoucherId = Long.valueOf(request.getEvoucherId());
			EVoucher eVoucher = eVoucherRepository.findById(evoucherId).get();
			
			if(eVoucher == null) {
				throw new EVoucherException(HttpStatus.BAD_REQUEST, IMessages.IError.BAD_REQUEST, APICodes.INVALID_EVOUCHER);
			}

			if(!ISystem.IStatus.ACTIVE.equals(eVoucher.getEvoucherStatus()))
				throw new EVoucherException(HttpStatus.BAD_REQUEST, IMessages.IError.BAD_REQUEST, APICodes.EVOUCHER_NOT_ACTIVE);
		
			List<EVoucherDetailsRes> eVoucherDetails = new ArrayList<EVoucherDetailsRes>();
			if (eVoucher.getEvoucherDetails() != null) {
				
				eVoucherDetails = eVoucher.getEvoucherDetails().stream()
						.map(obj -> new EVoucherDetailsRes(
								obj.getEvoucher().getEvoucherId().toString(), obj.getEvoucherDetailsId().toString(),
								obj.getName(), obj.getPhoneNo().toString(), obj.getMaxLimit().toString(), 
								obj.getGiftPerUserLimit().toString(), obj.getEvoucherDetailsStatus()))
						.collect(Collectors.toList());

			}

			// after success of insert
			EVoucherDetailsListRes objRes = new EVoucherDetailsListRes();

			objRes.getCoreSuccessResponse();

			objRes.setEvoucherDetails(eVoucherDetails);

			// send the response
			HashMap returnMap = new HashMap();
			returnMap.put(ISystem.IKey.IResponse.RESPONSE, objRes);

			return returnMap;
		} catch (EVoucherException e) {
			throw e;
		} catch (Exception e) {
			log.error(e);
			throw new EVoucherException(HttpStatus.INTERNAL_SERVER_ERROR, IMessages.IError.INTERNAL_ERROR,APICodes.APPL_ERR, e);
		}
	}
	
	@Override
	public HashMap<String, String> checkout(CheckoutReq request) {
		log.info("checkout service");
		
		try {
			Long evoucherId = Long.valueOf(request.getEvoucherId());
			EVoucher eVoucher = eVoucherRepository.findById(evoucherId).get();
			
			if(eVoucher == null) {
				throw new EVoucherException(HttpStatus.BAD_REQUEST, IMessages.IError.BAD_REQUEST, APICodes.INVALID_EVOUCHER);
			}

			if(!ISystem.IStatus.ACTIVE.equals(eVoucher.getEvoucherStatus()))
				throw new EVoucherException(HttpStatus.BAD_REQUEST, IMessages.IError.BAD_REQUEST, APICodes.EVOUCHER_NOT_ACTIVE);
		
			eVoucher.setEvoucherStatus(ISystem.IStatus.CHECKOUT);
			
			eVoucherRepository.save(eVoucher);

			// after success of insert
			CheckoutRes objRes = new CheckoutRes();

			objRes.getCoreSuccessResponse();

			// send the response
			HashMap returnMap = new HashMap();
			returnMap.put(ISystem.IKey.IResponse.RESPONSE, objRes);

			return returnMap;
		} catch (EVoucherException e) {
			throw e;
		} catch (Exception e) {
			log.error(e);
			throw new EVoucherException(HttpStatus.INTERNAL_SERVER_ERROR, IMessages.IError.INTERNAL_ERROR,APICodes.APPL_ERR, e);
		}
	}
	
	@Override
	public HashMap<String, String> paymentMethods(PaymentMethodsReq request) {
		log.info("paymentMethods service");
		
		try {
			log.info("get All Payment Methods");
			List<PaymentMethod> paymentMethodList = paymentMethodRepository.findAll();

			if(paymentMethodList == null) {
				log.error("Payment Method not found in database");
				throw new EVoucherException(HttpStatus.INTERNAL_SERVER_ERROR, IMessages.IError.INTERNAL_ERROR, APICodes.APPL_ERR);
			}
			
			List<PaymentMethodRes> paymentMethods = paymentMethodList.stream().map(
					obj -> new PaymentMethodRes(obj.getPmId(), obj.getPmCode(), obj.getPmDesc(), obj.getPmStatus())
					).collect(Collectors.toList());
			
			// after success of insert
			PaymentMethodsRes objRes = new PaymentMethodsRes();

			objRes.getCoreSuccessResponse();
			
			objRes.setPaymentMethods(paymentMethods);

			// send the response
			HashMap returnMap = new HashMap();
			returnMap.put(ISystem.IKey.IResponse.RESPONSE, objRes);

			return returnMap;
		} catch (EVoucherException e) {
			throw e;
		} catch (Exception e) {
			log.error(e);
			throw new EVoucherException(HttpStatus.INTERNAL_SERVER_ERROR, IMessages.IError.INTERNAL_ERROR,APICodes.APPL_ERR, e);
		}
	}
	
	@Override
	public HashMap<String, String> payment(PaymentReq request) {
		log.info("paymentMethods service");
		
		try {
			Long evoucherId = Long.valueOf(request.getEvoucherId());
			EVoucher evoucher = eVoucherRepository.findById(evoucherId).get();
			
			if(evoucher == null) {
				throw new EVoucherException(HttpStatus.BAD_REQUEST, IMessages.IError.BAD_REQUEST, APICodes.INVALID_EVOUCHER);
			}

			if(ISystem.IStatus.BUY.equals(evoucher.getEvoucherStatus()) || ISystem.IStatus.INACTIVE.equals(evoucher.getEvoucherStatus()))
				throw new EVoucherException(HttpStatus.BAD_REQUEST, IMessages.IError.BAD_REQUEST, APICodes.INVALID_EVOUCHER);
			
			int percent = evoucher.getPaymentMethodDiscount().getPmdPercent();
			
			BigDecimal amount = evoucher.getAmount();
			
			BigDecimal discountAmount = eVoucherUtil.getBigAmount(amount.doubleValue() * (percent /100));
			List<Payment> paymentList = new ArrayList<Payment>();
			
			for (EVoucherDetails detail : evoucher.getEvoucherDetails()) {
				
				Payment payment = new Payment();
				
				EVoucher ev = new EVoucher();
				ev.setEvoucherId(evoucherId);
				payment.setEvoucher(ev);
				
				EVoucherDetails evd = new EVoucherDetails();
				evd.setEvoucherDetailsId(detail.getEvoucherDetailsId());
				payment.setEvoucherDetail(evd);
				
				payment.setAmount(amount);
				
				payment.setDiscountAmount(discountAmount);
				
				PaymentMethod pm = new PaymentMethod();
				pm.setPmId(evoucher.getPaymentMethod().getPmId());
				payment.setPaymentMethod(pm);
				
				PaymentMethodDiscount pmd = new PaymentMethodDiscount();
				pmd.setPmdId(evoucher.getPaymentMethodDiscount().getPmdId());
				payment.setPaymentMethodDiscount(pmd);
				
				
				payment.setPhoneNo(detail.getPhoneNo());
				
				payment.setExpireDate(evoucher.getExpireDate());
				
				payment.setTranxDate(new Date());
				
				payment.setTranxStatus(ISystem.IStatus.ACTIVE);
				
				paymentList.add(payment);
			}
			
			log.info("Save Payment List");
			paymentRepository.saveAll(paymentList);
			
			evoucher.setEvoucherStatus(ISystem.IStatus.BUY);
			eVoucherRepository.save(evoucher);
			
			// after success of insert
			PaymentRes objRes = new PaymentRes();

			objRes.getCoreSuccessResponse();
			
			// send the response
			HashMap returnMap = new HashMap();
			returnMap.put(ISystem.IKey.IResponse.RESPONSE, objRes);

			return returnMap;
		} catch (EVoucherException e) {
			throw e;
		} catch (Exception e) {
			log.error(e);
			throw new EVoucherException(HttpStatus.INTERNAL_SERVER_ERROR, IMessages.IError.INTERNAL_ERROR,APICodes.APPL_ERR, e);
		}
	}
	
	@Override
	public HashMap<String, String> paymentMethods(PurchaseHistoryReq request) {
		log.info("paymentMethods service");
		
		try {
			
			String status = request.getEvoucherStatus();
			
			List<Payment> payments = paymentRepository.findByTranxStatus(status);
			
			List<Purchase> purchases = new ArrayList<Purchase>();
			for (Payment payment : payments) {
				Purchase purchase = new Purchase();
				
				purchase.setEVoucherId(payment.getEvoucherDetail().getEvoucherDetailsId().toString());
				purchase.setPromoCode(payment.getPromoCode());
				purchase.setQrImage(qrCodeGen.getImageToBase64(payment.getQrcodeImage()));
				
				purchases.add(purchase);
			}
			
			// after success of insert
			PurchaseHistoryRes objRes = new PurchaseHistoryRes();

			objRes.getCoreSuccessResponse();
			
			objRes.setPurchases(purchases);
			
			// send the response
			HashMap returnMap = new HashMap();
			returnMap.put(ISystem.IKey.IResponse.RESPONSE, objRes);

			return returnMap;
		} catch (EVoucherException e) {
			throw e;
		} catch (Exception e) {
			log.error(e);
			throw new EVoucherException(HttpStatus.INTERNAL_SERVER_ERROR, IMessages.IError.INTERNAL_ERROR,APICodes.APPL_ERR, e);
		}
	}


	@Autowired
	public void setEVoucherUtil(EVoucherUtil eVoucherUtil) {
		this.eVoucherUtil = eVoucherUtil;
	}
	
	@Autowired
	public void setQRCodeGen(QRCodeGen qrCodeGen) {
		this.qrCodeGen = qrCodeGen;
	}

}
