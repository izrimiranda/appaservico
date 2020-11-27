package br.com.izri.aservico.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javafx.scene.control.DatePicker;

public class DateUtils {

	public static final long MILLISECONDS_IN_DAY = 1000 * 60 * 60 * 24;

	public static final String HOUR_MINUTE_PATTERN = "HH:mm";

	public enum PATTERN {
		YEAR("yyyy"), MONTH("MMM"), DDMM_SLASH_SEPARATED_PATTERN("dd/MM"), DDMMYYYY_SLASH_SEPARATED_PATTERN(
		    "dd/MM/yyyy"), DDMMYYYY_HYPHEN_SEPARETED_PATTERN("dd-MM-yyyy"), MMYYYY_SLASH_SEPARATED_PATTERN(
		        "MM/yyyy"), HOUR_MINUTE_PATTERN("HH:mm"), DDMMYYYYHHMM_PATTERN(
		            "ddMMyyyyHHmm"), DDMMYYYYHHMM_SLASH_SEPARATED_PATTERN("dd/MM/yyyy HH:mm"), DDMMYYYY_UNDERLINE_SEPARETED_PATTERN("dd_MM_yyyy");

		private final String pattern;

		private PATTERN(String pattern) {
			this.pattern = pattern;
		}
	}

	/* Formato padrão dd/MM/yyyy */
	private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(
	    DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN.pattern);

	/**
	 * Calculates the number of days between start and end dates, taking into
	 * consideration leap years, year boundaries etc.
	 *
	 * @param start
	 *            the start date
	 * @param end
	 *            the end date, must be later than the start date
	 * @return the number of days between the start and end dates
	 */
	public static long countDaysBetween(Date start, Date end) {
		// reset all hours mins and secs to zero on start date
		final Calendar startCal = Calendar.getInstance();
		startCal.setTime(start);
		startCal.set(Calendar.HOUR_OF_DAY, 0);
		startCal.set(Calendar.MINUTE, 0);
		startCal.set(Calendar.SECOND, 0);
		final long startTime = startCal.getTimeInMillis();

		// reset all hours mins and secs to zero on end date
		final Calendar endCal = Calendar.getInstance();
		endCal.setTime(end);
		endCal.set(Calendar.HOUR_OF_DAY, 0);
		endCal.set(Calendar.MINUTE, 0);
		endCal.set(Calendar.SECOND, 0);
		final long endTime = endCal.getTimeInMillis();

		return (endTime - startTime) / DateUtils.MILLISECONDS_IN_DAY;
	}

	private static String doFormat(Date date) {
		return DateUtils.DATE_FORMATTER.format(date);
	}

	/**
	 * Formata data usando um padrão qualquer.
	 *
	 * @param date
	 *            Data a ser formatada.
	 * @param pattern
	 *            padrão a ser utilizado.
	 * @return String com a data formatada.
	 */
	public static String formatDate(Date date, String pattern) {
		try {
			DateUtils.DATE_FORMATTER.applyPattern(pattern);
			final String formattedDate = DateUtils.doFormat(date);

			return formattedDate;
		} finally {
			DateUtils.voltaFormatoDefault();
		}
	}

	/**
	 * Formata data usando o padrão dd/MM/yyyy.
	 *
	 * @see #DateUtils .DDMMYYYY_SLASH_SEPARATED_PATTERN
	 * @param date
	 *            data a ser formatada.
	 * @return String com a data formatada.
	 */
	public static String formatDate(Date date) {
		return DateUtils.doFormat(date);
	}

	public static Date parse(String date) throws ParseException {
		return DateUtils.doParse(date, true, null);
	}

	public static Date parse(String date, boolean lenient) throws ParseException {
		return DateUtils.doParse(date, lenient, null);
	}

	public static Date parse(String date, String pattern, boolean lenient) throws ParseException {
		return DateUtils.doParse(date, lenient, pattern);
	}

	public static Date parse(String date, String pattern) throws ParseException {
		try {
			DateUtils.DATE_FORMATTER.applyPattern(pattern);
			final Date parsedDate = DateUtils.doParse(date);

			return parsedDate;
		} finally {
			DateUtils.voltaFormatoDefault();
		}
	}

	public static String format(Calendar cal) {
		return DateUtils.formatDate(cal.getTime());
	}

	public static String format(Date date) {
		return DateUtils.formatDate(date);
	}

	public static String format(Calendar calendar, String pattern) {
		String data = "";

		if (calendar != null) {
			data = DateUtils.formatDate(calendar.getTime(), pattern);
		}
		return data;
	}

	public static String format(Calendar data, PATTERN pattern) {
		String dataRetorno = "";
		if (data != null) {
			dataRetorno = DateUtils.format(data, pattern.pattern);
		}
		return dataRetorno;
	}

	private static void voltaFormatoDefault() {
		DateUtils.DATE_FORMATTER.applyPattern(DateUtils.PATTERN.DDMMYYYY_SLASH_SEPARATED_PATTERN.pattern);
	}

	private static Date doParse(String date) throws ParseException {
		return DateUtils.doParse(date, true, null);
	}

	private static Date doParse(String date, boolean lenient, String formato) throws ParseException {
		final SimpleDateFormat clone = (SimpleDateFormat) DateUtils.DATE_FORMATTER.clone();
		clone.setLenient(lenient);
		if (formato != null) {
			clone.applyPattern(formato);
		}

		return clone.parse(date);
	}

	/**
	 * Verifica se a tecla digitada é um número.
	 *
	 * @param key
	 * @return boolean
	 */
	public static boolean isNumber(java.awt.event.KeyEvent key) {

		if ((key.getKeyCode() >= 48) && (key.getKeyCode() <= 57)) {
			return true;
		}
		if ((key.getKeyCode() >= 96) && (key.getKeyCode() <= 105)) {
			return true;
		}
		if (key.getKeyCode() == 8) {
			return true;
		}

		return false;
	}

	public static String formataData(String text) {
		String retorno = text;

		if (text.length() == 2) {
			retorno = text + "/";
		} else if (text.length() == 5) {
			retorno = text + "/";
		}
		return retorno;
	}

	public static Calendar parseToCalendar(String data, boolean ignoreExceptions) {
		Calendar calendar = null;
		System.out.println("data: " + StringUtils.isNotEmpty(data));
		if ((StringUtils.isNotEmpty(data)) && (data.length() == 10)) {
			calendar = Calendar.getInstance();
			calendar = DateUtils.parseToCalendar(data, ignoreExceptions, true);
		}

		return calendar;
	}

	public static Calendar parseToCalendar(String data, boolean ignoreExceptions, boolean lenient) {
		Calendar cal = null;

		try {
			final Date d = DateUtils.parse(data, lenient);
			cal = Calendar.getInstance();
			cal.setLenient(lenient);
			cal.setTime(d);
		} catch (final Exception e) {
			if (!ignoreExceptions) {
				throw new RuntimeException(e);
			}
		}

		return cal;
	}

	public static Calendar parseToCalendar(String data, PATTERN pattern, boolean ignoreExceptions, boolean lenient) {
		return DateUtils.parseToCalendar(data, pattern.pattern, ignoreExceptions, lenient);
	}

	private static Calendar parseToCalendar(String data, String pattern, boolean ignoreExceptions, boolean lenient) {
		Calendar cal = null;

		try {
			final Date d = DateUtils.parse(data, pattern, lenient);
			cal = Calendar.getInstance();
			cal.setLenient(lenient);
			cal.setTime(d);
		} catch (final Exception e) {
			if (!ignoreExceptions) {
				throw new RuntimeException(e);
			}
		}

		return cal;
	}

	public static void validaData(String data) {
		if (StringUtils.isNotEmpty(data)) {
			// DateTimeFormatter format =
			// DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.US);

			try {
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean isDatasValidas(String dataInicio, String dataFinal) {

		boolean retorno = false;

		Calendar dataCalendarUm = Calendar.getInstance();
		Calendar dataCalendarDois = Calendar.getInstance();

		if (StringUtils.isNotEmpty(dataInicio) && StringUtils.isNotEmpty(dataFinal)) {
			dataCalendarUm = DateUtils.parseToCalendar(dataInicio, false);
			dataCalendarDois = DateUtils.parseToCalendar(dataFinal, false);

			if (dataCalendarUm.getTimeInMillis() <= dataCalendarDois.getTimeInMillis()) {
				retorno = true;
			}
		}

		if (StringUtils.isEmpty(dataInicio) && StringUtils.isNotEmpty(dataFinal)) {
			retorno = false;
		}

		if (StringUtils.isNotEmpty(dataInicio) && StringUtils.isEmpty(dataFinal)) {
			retorno = false;
		}

		return retorno;
	}

	public static void teste(String dataInicio, String dataFinal) {

		final DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		format.setLenient(false);

		try {
			System.out.println(format.parse(dataInicio));
			System.out.println(format.parse(dataFinal));
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public static String converterDatePickerToString(DatePicker datePicker) throws ParseException {

		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.US);
		String dataFormatada = datePicker.getValue().format(format);

		return dataFormatada;
	}
}
