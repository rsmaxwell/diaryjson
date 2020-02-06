package com.rsmaxwell.diaryjson;

public class Key implements Comparable {

	public int year;
	public int month;
	public int day;

	public Key(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}

	@Override
	public int compareTo(Object o) {
		Key other = (Key) o;
		if (year != other.year) {
			return year - other.year;
		}
		if (month != other.month) {
			return month - other.month;
		}
		return day - other.day;
	}
}
