package com.ayeminwai.pc.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Base64;
import java.util.HashMap;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ayeminwai.pc.qr.model.QRCode;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeWriter;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class QRCodeGen {

	 @Value("${qrcode.filepath}")
	 private String filePath;

	 @Value("${qrcode.secretKey}")
	 private String secretKey;
	
	  AESUtils aesUtils;
	  
	@Autowired
	public void setAESUtils(AESUtils aesUtils) {
		this.aesUtils = aesUtils;
	}
	 

	public String generateQR(QRCode qrCode) {
		QRCodeWriter barcodeWriter = new QRCodeWriter();
		String imageName = getQRName();

		try {
			HashMap<String, Serializable> qrCodeMap = qrCode.getValues();
			String qrCodeSerialize = AESUtils.toString(qrCodeMap);

			String encryptedString = aesUtils.encrypt(qrCodeSerialize, secretKey);

			BitMatrix bitMatrix = barcodeWriter.encode(encryptedString, BarcodeFormat.QR_CODE, 200, 200);

			BufferedImage bi = MatrixToImageWriter.toBufferedImage(bitMatrix); // retrieve image
			File outputfile = new File(filePath + imageName);
			ImageIO.write(bi, "jpg", outputfile);

			return imageName;
		} catch (IOException e) {
			log.error(e.getMessage());
		} catch (WriterException e) {
			log.error(e.getMessage());
		}

		return "";
	}

	@SuppressWarnings("unchecked")
	public QRCode readQRString(String qrCodeString) throws ClassNotFoundException {
		QRCode qrCode = new QRCode();
		try {
			String decString = aesUtils.decrypt(qrCodeString, secretKey);

			HashMap<String, Serializable> qrCodeMapRes = (HashMap<String, Serializable>) AESUtils.fromString(decString);

			qrCode.setValues(qrCodeMapRes);
			
			return qrCode;
		} catch (IOException e) {
			log.error(e.getMessage());
		} 

		return null;
	}
	
	public static String getQRName() {
		return "QR" + UUID.randomUUID() + DateUtil.convertDateStringddMMyyyyHHmmss() + ".jpg";
	}

	public String getImageToBase64(String imageName) throws IOException {
		String imageDataString = "";
		File img = new File(filePath + imageName);
		if (img.exists()) {
			try {
				byte[] fileContent = FileUtils.readFileToByteArray(img);
				imageDataString = Base64.getEncoder().encodeToString(fileContent);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}

		return imageDataString;
	}
	
	@Deprecated
	public String getQRCodeImage(String imageName) {
		String encodedContent = null;
		try {
			File outputfile = new File(filePath + imageName);
			BufferedImage bufferedImage = ImageIO.read(outputfile);

			encodedContent = readQRCode(bufferedImage);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return encodedContent;

	}

	@Deprecated
	public String readQRCode(BufferedImage bufferedImage) {
		String encodedContent = null;
		try {
			BufferedImageLuminanceSource bufferedImageLuminanceSource = new BufferedImageLuminanceSource(bufferedImage);
			HybridBinarizer hybridBinarizer = new HybridBinarizer(bufferedImageLuminanceSource);
			BinaryBitmap binaryBitmap = new BinaryBitmap(hybridBinarizer);
			MultiFormatReader multiFormatReader = new MultiFormatReader();

			Result result = multiFormatReader.decode(binaryBitmap);
			encodedContent = result.getText();
		} catch (NotFoundException e) {
			e.printStackTrace();
		}
		return encodedContent;
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		QRCodeGen qrGen = new QRCodeGen();

		QRCode qrCode = new QRCode();

		qrCode.setEVoucherDetailId("1");
		qrCode.setPhoneNo("982738112");
		qrCode.setPromoCode("1A23Bu549zY");

		String qrCodeName = qrGen.generateQR(qrCode);

		try {
			System.out.println(qrCodeName);
			System.out.println(qrGen.getImageToBase64(qrCodeName));
			System.out.println(qrGen.getQRCodeImage(qrCodeName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * AESUtils aesEncryptionDecryption = new AESUtils(); String decString =
		 * aesEncryptionDecryption.decrypt(qrCodeName, secretKey);
		 * System.out.println(decString);
		 * 
		 * HashMap<String, Serializable> qrCodeMapRes = (HashMap<String, Serializable>)
		 * AESUtils.fromString(decString);
		 * 
		 * qrCode.setValues(qrCodeMapRes); System.out.println(qrCodeMapRes.toString());
		 * System.out.println(qrCode.getEVoucherDetailId());
		 * System.out.println(qrCode.getPhoneNo());
		 * System.out.println(qrCode.getPromoCode());
		 */

	}
}
