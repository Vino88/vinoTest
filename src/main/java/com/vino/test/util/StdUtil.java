package com.vino.test.util;

import java.math.BigDecimal;

public class StdUtil {

	public static BigDecimal toBigDecimal(Object s) {
		try {
			return new BigDecimal(s.toString());
		} catch (Exception e) {
			return new BigDecimal(0);
		}
	}
	
	public static Long toLong(Object s) {
		try {
			return new Long(s.toString());
		} catch (Exception e) {
			return new Long(0);
		}
	}
}
