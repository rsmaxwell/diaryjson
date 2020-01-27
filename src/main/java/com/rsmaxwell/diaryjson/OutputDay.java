package com.rsmaxwell.diaryjson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class OutputDay implements Comparable {

	public int year;
	public int month;
	public int day;
	public String page;
	public String line;

	@JsonInclude(Include.NON_EMPTY)
	public String notes;

	@Override
	public int compareTo(Object o) {
		OutputDay other = (OutputDay) o;
		if (year != other.year) {
			return year - other.year;
		}
		if (month != other.month) {
			return month - other.month;
		}
		if (day != other.day) {
			return day - other.day;
		}
		return page.compareTo(other.page);
	}

}
