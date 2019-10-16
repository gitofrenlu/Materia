
package com.example.material.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Queue;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentDateFormat {
	private final String format;
	private final Locale locale;
	private final TimeZone timezone;
	private final Queue<SimpleDateFormat> queue = new ConcurrentLinkedQueue();

	private ConcurrentDateFormat(String format, Locale locale, TimeZone timezone) {
		this.format = format;
		this.locale = locale;
		this.timezone = timezone;
		SimpleDateFormat initial = this.createInstance();
		this.queue.add(initial);
	}

	public static ConcurrentDateFormat of(String format) {
		return new ConcurrentDateFormat(format, Locale.getDefault(), TimeZone.getDefault());
	}

	public static ConcurrentDateFormat of(String format, TimeZone timezone) {
		return new ConcurrentDateFormat(format, Locale.getDefault(), timezone);
	}

	public static ConcurrentDateFormat of(String format, Locale locale, TimeZone timezone) {
		return new ConcurrentDateFormat(format, locale, timezone);
	}

	public String format(Date date) {
		SimpleDateFormat sdf = (SimpleDateFormat)this.queue.poll();
		if (sdf == null) {
			sdf = this.createInstance();
		}

		String result = sdf.format(date);
		this.queue.add(sdf);
		return result;
	}

	public Date parse(String source) throws ParseException {
		SimpleDateFormat sdf = (SimpleDateFormat)this.queue.poll();
		if (sdf == null) {
			sdf = this.createInstance();
		}

		Date result = sdf.parse(source);
		this.queue.add(sdf);
		return result;
	}

	private SimpleDateFormat createInstance() {
		SimpleDateFormat sdf = new SimpleDateFormat(this.format, this.locale);
		sdf.setTimeZone(this.timezone);
		return sdf;
	}
}
