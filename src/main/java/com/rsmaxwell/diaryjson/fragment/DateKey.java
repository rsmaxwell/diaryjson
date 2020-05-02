package com.rsmaxwell.diaryjson.fragment;

public class DateKey implements Comparable {

	public int year;
	public int month;
	public int day;

	public DateKey(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}

	@Override
	public int compareTo(Object o) {
		DateKey other = (DateKey) o;
		if (year != other.year) {
			return year - other.year;
		}
		if (month != other.month) {
			return month - other.month;
		}
		if (day != other.day) {
			return day - other.day;
		}
		return 0;
	}

	@Override
	public String toString() {
		return String.format("%d-%02d-%02d", year, month, day);
	}
}
