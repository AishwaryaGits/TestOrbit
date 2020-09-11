package com.testorbit.mainsorbit.util;

import java.sql.Timestamp;

public class DateUtility {

	public static final int DATE_EPOCH_DIFF = 0;

	public DateUtility() {
		super();
	}

	public static Timestamp getCurrentDate() {

		java.util.Date dt = new java.util.Date(System.currentTimeMillis() + DATE_EPOCH_DIFF);

		// java.sql.Timestamp today = new Timestamp(System.currentTimeMillis());

		return new java.sql.Timestamp(dt.getTime());
	}


}
