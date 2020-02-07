package com.rsmaxwell.diaryjson;

import java.util.TreeSet;

public class DayOfFragments implements Comparable {

	public int year;
	public int month;
	public int day;
	public TreeSet<Fragment> fragments = new TreeSet<Fragment>();

	public DayOfFragments(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}

	public void add(Fragment fragment) throws Exception {
		boolean result = fragments.add(fragment);
		if (!result) {
			throw new Exception("duplicate fragment: " + fragment);
		}
	}

	public int size() {
		return fragments.size();
	}

	@Override
	public int compareTo(Object o) {
		DayOfFragments other = (DayOfFragments) o;
		if (year != other.year) {
			return year - other.year;
		}
		if (month != other.month) {
			return month - other.month;
		}
		return (day - other.day);
	}

	public boolean hasDocumentHeader() {
		return hasType("document-header");
	}

	public boolean hasDocumentFooter() {
		return hasType("document-footer");
	}

	public boolean hasYearHeader() {
		return hasType("year-header");
	}

	public boolean hasMonthHeader() {
		return hasType("month-header");
	}

	public boolean hasDayHeader() {
		return hasType("day-header");
	}

	private boolean hasType(String type) {
		for (Fragment fragment : fragments) {
			if (type.equals(fragment.type)) {
				return true;
			}
		}
		return false;
	}
}
