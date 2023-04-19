package com.qraccess.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordEncrypter {
	
	public static String encrypt(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] b = str.getBytes();
			md.update(b);
			return Base64.getEncoder().encodeToString(md.digest());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
