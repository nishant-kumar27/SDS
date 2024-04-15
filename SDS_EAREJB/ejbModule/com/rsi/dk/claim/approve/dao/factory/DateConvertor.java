package com.rsi.dk.claim.approve.dao.factory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DateConvertor {
	private static DatatypeFactory df = null;

	static {
		try {
			df = DatatypeFactory.newInstance();
		} catch (DatatypeConfigurationException dce) {
			throw new IllegalStateException("Exception while obtaining DatatypeFactory instance", dce);
		}
	}

	public static XMLGregorianCalendar asXMLGregorianCalendar(Date date) {
		if (date == null) {
			return null;
		}
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTimeInMillis(date.getTime());
		return df.newXMLGregorianCalendar(gc);
	}

	public static Date asDate(XMLGregorianCalendar xgc) throws Exception {
		if (xgc == null) {
			return null;
		}
		return xgc.toGregorianCalendar().getTime();
	}

	public static Date getDate(String dateString) throws Exception {
		if (dateString != null) {
			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = formatter.parse(dateString);
			return date;
		} else {
			throw new Exception("Date Can Not Be Null");
		}
	}

	public static String getString(Date date) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
		String dateString = dateFormat.format(date);
		return dateString;
	}

}