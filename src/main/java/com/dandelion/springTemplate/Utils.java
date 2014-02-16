package com.dandelion.springTemplate;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
		public static String getMD5(String base) {
			String md5;
			try {
				MessageDigest md = MessageDigest.getInstance("MD5");
				md5 = ByteUtilities.toHexString(md.digest(base.getBytes("UTF-8")));
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			} catch (NoSuchAlgorithmException e) {
				throw new RuntimeException(e);
			}
			return md5;
		}

		public static boolean checkEmailFormat(String email) {
			String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(email);
			return m.find();
		}

		public static boolean checkMD5PasswordFormat(String password) {
			if (password.length() == 32)
				return true;
			return false;
		}

		public static boolean checkRealPasswordFormat(String password) {
			if (password.length() < 6 || password.length() > 20)
				return false;
			boolean hasLetter = false;
			for (int i = 0; i < password.length(); ++i) {
				char c = password.charAt(i);
				if (!hasLetter && Character.isLetter(c))
					hasLetter = true;
				else if (!Character.isDigit(c) && !Character.isLetter(c)) {
					return false;
				}
			}
			if (!hasLetter)
				return false;
			return true;
	}

}
