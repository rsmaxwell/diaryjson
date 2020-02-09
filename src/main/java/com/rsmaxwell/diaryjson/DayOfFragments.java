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
		fragments.add(fragment);
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
}
