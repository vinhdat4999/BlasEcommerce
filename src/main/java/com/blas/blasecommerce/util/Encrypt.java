package com.blas.blasecommerce.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {
	public String encrypt(String text) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		String encryptText;
		MessageDigest msd = MessageDigest.getInstance("SHA-1");
		byte[] textByte = text.getBytes("UTF-8");
		byte[] encryptByteText = msd.digest(textByte);
		BigInteger bigInt = new BigInteger(1, encryptByteText);
		encryptText = bigInt.toString(16);
		return encryptText;
	}
}
