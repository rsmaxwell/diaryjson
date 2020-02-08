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

	public boolean hasDocumentHeader_1() {
		return hasType("document-header-1");
	}

	public boolean hasDocumentHeader_2() {
		return hasType("document-header-2");
	}

	public boolean hasDocumentForward() {
		return hasType("document-forward");
	}

	public boolean hasDocumentFooter() {
		return hasType("document-footer");
	}

	public boolean hasYearHeader_1() {
		return hasType("year-header-1");
	}

	public boolean hasYearHeader_2() {
		return hasType("year-header-2");
	}

	public boolean hasYearFooter() {
		return hasType("year-footer");
	}

	public boolean hasMonthHeader_1() {
		return hasType("month-header-1");
	}

	public boolean hasMonthHeader_2() {
		return hasType("month-header-2");
	}

	public boolean hasMonthFooter() {
		return hasType("month-footer");
	}

	public boolean hasDayHeader() {
		return hasType("day-header");
	}

	public boolean hasDayFooter() {
		return hasType("day-footer");
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
