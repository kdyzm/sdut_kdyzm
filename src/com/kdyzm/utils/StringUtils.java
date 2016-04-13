package com.kdyzm.utils;

public class StringUtils {
	public static boolean isNullOrEmpty(String str) {
		return str == null ? true : (str.trim().equals("") ? true : false);
	}
}
