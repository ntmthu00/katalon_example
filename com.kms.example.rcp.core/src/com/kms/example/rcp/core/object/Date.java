package com.kms.example.rcp.core.object;

/**
 * Date format: dd/MM/yyyy
 * 
 * @author Thu Nguyen
 */
public class Date {
	private int day;
	private int month;
	private int year;

	public Date() {
		super();
	}

	public Date(int day, int month, int year) {
		super();
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "Date [day=" + day + ", month=" + month + ", year=" + year + "]";
	}

	public static Date parseDate(String s) {
		String[] arrStr = s.split("/");
		Date newDate = new Date();
		newDate.day = Integer.parseInt(arrStr[0]);
		newDate.month = Integer.parseInt(arrStr[1]);
		newDate.year = Integer.parseInt(arrStr[2]);
		return newDate;
	}
}
