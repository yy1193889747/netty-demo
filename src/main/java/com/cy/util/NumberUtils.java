package com.cy.util;

import java.io.UnsupportedEncodingException;

public class NumberUtils {

	public static final long NULL_VALUE = Long.MIN_VALUE;

	public static int byte2Int4C(byte[] b) {
		int index = 0;
		return (b[index + 3] & 0xff) << 24 | (b[index + 2] & 0xff) << 16 | (b[index + 1] & 0xff) << 8
				| b[index + 0] & 0xff;
	}

	public static long byte2Long4C(byte[] arr) {
		int index = 0;
		long ret = (0xff00000000000000L & ((long) arr[index + 7] << 56))
				| (0x00ff000000000000L & ((long) arr[index + 6] << 48))
				| (0x0000ff0000000000L & ((long) arr[index + 5] << 40))
				| (0x000000ff00000000L & ((long) arr[index + 4] << 32))
				| (0x00000000ff000000L & ((long) arr[index + 3] << 24))
				| (0x0000000000ff0000L & ((long) arr[index + 2] << 16))
				| (0x000000000000ff00L & ((long) arr[index + 1] << 8))
				| (0x00000000000000ffL & (long) arr[index + 0]);
		return ret;
	}

	public static short byte2Short4C(byte[] arr) {

		return (short) ((arr[1] << 8) | (arr[0] & 0xFF));
	}

	public static String byte2String4C(byte[] arr) {

		return new String(arr);
	}

	public static String byte2UTF16String4C(byte[] arr) {

		try {
			return new String(arr, "UTF-16LE").replace("\u0000", "");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

}
