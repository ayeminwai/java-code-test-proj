package com.ayeminwai.pc.service.impl;

import java.sql.Connection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ayeminwai.pc.beans.req.PromoCodeGenReq;
import com.ayeminwai.pc.beans.req.PromoCodeValidateReq;
import com.ayeminwai.pc.beans.res.PromoCodeGenRes;
import com.ayeminwai.pc.beans.res.PromoCodeValidateRes;
import com.ayeminwai.pc.constant.APICodes;
import com.ayeminwai.pc.constant.IMessages;
import com.ayeminwai.pc.constant.ISystem;
import com.ayeminwai.pc.exception.PromoCodeException;
import com.ayeminwai.pc.model.EVoucher;
import com.ayeminwai.pc.model.Payment;
import com.ayeminwai.pc.qr.model.QRCode;
import com.ayeminwai.pc.repository.EVoucherDetailsRepository;
import com.ayeminwai.pc.repository.EVoucherRepository;
import com.ayeminwai.pc.repository.PaymentRepository;
import com.ayeminwai.pc.service.PromoCodeService;
import com.ayeminwai.pc.util.DateUtil;
import com.ayeminwai.pc.util.DateUtilJ8;
import com.ayeminwai.pc.util.PromoCodeGen;
import com.ayeminwai.pc.util.QRCodeGen;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@SuppressWarnings({"rawtypes","unchecked"})
public class PromoCodeServiceImpl implements PromoCodeService {

	@Autowired
	EVoucherRepository eVoucherRepository;
	
	@Autowired
	EVoucherDetailsRepository eVoucherDetailsRepository;
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	DataSource dataSource;
	
	PromoCodeGen promoCodeGen;
	QRCodeGen qrCodeGen;
	
	
	@Autowired
	public void setPromoCodeGen(PromoCodeGen promoCodeGen) {
		this.promoCodeGen = promoCodeGen;
	}
	
	@Autowired
	public void setQRCodeGen(QRCodeGen qrCodeGen) {
		this.qrCodeGen = qrCodeGen;
	}

	@Override
	public HashMap generate(PromoCodeGenReq request) {
		log.info("generate service");
		
		try (Connection conn = dataSource.getConnection();){
			conn.setAutoCommit(false);
			
			Long evoucherId = Long.valueOf(request.getEvoucherId());
			EVoucher evoucher = new EVoucher();
			evoucher.setEvoucherId(evoucherId);
			
			List<Payment> payments = paymentRepository.findByEvoucher(evoucher);
			
			if(payments.size() == 0) {
				throw new PromoCodeException(HttpStatus.BAD_REQUEST, IMessages.IError.BAD_REQUEST, APICodes.TRANX_NOT_FOUND);
			}

			Set<String> promoList = new HashSet<String>();
			
			while (promoList.size() < payments.size()) {
				promoList.add(promoCodeGen.createRandomCode());
			}
			
			int i = 0;
		    for (Iterator<String> it = promoList.iterator(); it.hasNext(); ) {
		    	String promoCode = it.next();
		    	if(promoCode != null) {
		    		Payment payment = paymentRepository.findById(payments.get(i).getPurchaseId()).get();
		    		payment.setPromoCode(promoCode);
		    		
		    		QRCode qrCode = new QRCode();
		    		qrCode.setEVoucherDetailId(payment.getEvoucherDetail().getEvoucherDetailsId());
		    		qrCode.setPhoneNo(payment.getPhoneNo());
		    		qrCode.setPromoCode(promoCode);
		    		
		    		String qrImageName = qrCodeGen.generateQR(qrCode);
		    		payment.setQrcodeImage(qrImageName);
		    		i++;
		    		
		    		paymentRepository.save(payment);
		    	}
		    }
		    
		    conn.commit();
			// after success of insert
			PromoCodeGenRes objRes = new PromoCodeGenRes();

			objRes.getCoreSuccessResponse();

			// send the response
			HashMap returnMap = new HashMap();
			returnMap.put(ISystem.IKey.IResponse.RESPONSE, objRes);

			return returnMap;
		} catch (PromoCodeException e) {
			throw e;
		} catch (Exception e) {
			log.error(e);
			throw new PromoCodeException(HttpStatus.INTERNAL_SERVER_ERROR, IMessages.IError.INTERNAL_ERROR, APICodes.APPL_ERR, e);
		}
	}

	@Override
	public HashMap<String, String> validatePromoCode(PromoCodeValidateReq request) {
		log.info("validatePromoCode service");
		
		try {
			
			String qrCode = request.getQrCode();
			String promoCode = request.getPromoCode();
			
			QRCode qrcodeObj = null;
			if(promoCode == null || "".equals(promoCode)) {
				
				qrcodeObj = qrCodeGen.readQRString(qrCode);
				promoCode = qrcodeObj.getPromoCode();
				
			}
			
			Payment payment = paymentRepository.findByPromoCode(promoCode);
			
			// after success of insert
			PromoCodeValidateRes objRes = new PromoCodeValidateRes();
			
			if (payment == null) {
				objRes.setResponseCode(APICodes.INVALID_EVOUCHER.code());
				objRes.setResponseText(APICodes.INVALID_EVOUCHER.getReasonPhrase());
				objRes.setResDateTime(DateUtilJ8.getServerTime());
			} else {
				objRes.getCoreSuccessResponse();
				objRes.setPhoneNo(payment.getPhoneNo().toString());
				objRes.setExpireDate(DateUtil.convertDateToDateString(payment.getExpireDate()));
				objRes.setStatus(payment.getTranxStatus());
			}

			// send the response
			HashMap returnMap = new HashMap();
			returnMap.put(ISystem.IKey.IResponse.RESPONSE, objRes);

			return returnMap;
		} catch (PromoCodeException e) {
			throw e;
		} catch (Exception e) {
			log.error(e);
			throw new PromoCodeException(HttpStatus.INTERNAL_SERVER_ERROR, IMessages.IError.INTERNAL_ERROR, APICodes.APPL_ERR, e);
		}
	}

}
