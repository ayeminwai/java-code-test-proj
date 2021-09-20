package com.ayeminwai.evoucher.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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

	public String getImageToBase64(String imageName) throws IOException {
		log.info("ImageName {}", imageName);
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

}
