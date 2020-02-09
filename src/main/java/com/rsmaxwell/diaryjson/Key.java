package com.rsmaxwell.diaryjson;

public class Key implements Comparable {

	public int year;
	public int month;
	public int day;
	public String order;

	public Key(int year, int month, int day, String order) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.order = order;
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
		if (day != other.day) {
			return day - other.day;
		}
		return order.compareTo(other.order);
	}
}
