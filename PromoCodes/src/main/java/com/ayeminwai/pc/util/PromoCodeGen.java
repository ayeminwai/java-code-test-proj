package com.ayeminwai.pc.util;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class PromoCodeGen {

	public String createRandomCode() {
		char[] chars = "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();

		Random random = new SecureRandom();
		for (int i = 0; i < 11; i++) {
			if (countAlpha(sb.toString()) == 5)
				break;
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}

		int rmcount = sb.length();

		char[] num = "1234567890".toCharArray();
		for (int i = 0; i < 11 - rmcount; i++) {
			char c = num[random.nextInt(num.length)];
			sb.append(c);
		}

		String output = sb.toString();
		return output;
	}

	@Deprecated
	private static int countNumber(String str) {
		int digits = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) >= 48 && str.charAt(i) <= 57)
				digits++;
		}

		return digits;
	}

	private static int countAlpha(String str) {
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
		Set<String> setList = new HashSet<String>();
		
		setList.add("c");
		setList.add("b");
		setList.add("f");
		setList.add("1");
		
		
	    for (Iterator<String> it = setList.iterator(); it.hasNext(); ) {
	    	String str = it.next();
	        System.out.println(str);
	    }
	}

}
