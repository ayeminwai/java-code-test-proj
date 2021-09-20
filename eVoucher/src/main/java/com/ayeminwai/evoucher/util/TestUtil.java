package com.ayeminwai.evoucher.util;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class TestUtil {

	public String createRandomCode() {
		char[] chars = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		
		Random random = new SecureRandom();
		for (int i = 0; i < 11; i++) {
			if(TestUtil.countAlpha(sb.toString()) == 4)
				break;
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
			
		}

		int rmcount = sb.length() - 1;
		
		char[] num = "1234567890".toCharArray();
		for (int i = 0; i < 11 - rmcount; i++) {
			char c = num[random.nextInt(num.length)];
			sb.append(c);
		}

		String output = sb.toString();
		return output;
	}
	
	public static int countNumber(String str) {
        int digits = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= 48 && str.charAt(i) <= 57)
                digits++;
        }
        
        return digits;
	}
	
	public static int countAlpha(String str) {
        int digits = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= 65 && str.charAt(i) <= 90)
                digits++;
            
            if (str.charAt(i) >= 97 && str.charAt(i) <= 122)
                digits++;
        }
        
        return digits;
	}

	public static void main(String[] args) {

		TestUtil util = new TestUtil();
		Set<String> promoList = new HashSet<String>();

		for (int i = 0; i < 10000; i++) {
			String promocode = util.createRandomCode();
			System.out.println(promocode);
			promoList.add(promocode);
		}

		System.out.println(promoList.size());


		/*
		 * char[] possibleCharacters = (new
		 * String("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"))
		 * .toCharArray(); for (int i = 0; i < 10000; i++) { String promocode =
		 * RandomStringUtils.random(11, 0, possibleCharacters.length - 1, true,
		 * true,possibleCharacters, new SecureRandom());
		 * 
		 * //RandomStringUtils.random(11, 0, possibleCharacters.length - 1, true, true,
		 * possibleCharacters, new SecureRandom()); System.out.println(promocode);
		 * promoList.add(promocode); } System.out.println(promoList.size());
		 */
	}
}
