package com.qraccess.utils;

public class Log {
	public static void error(String text) {
		System.err.print(text);
	}
	
	public static void info(String text) {
		System.out.println("INFO: " + text);
	}
}
