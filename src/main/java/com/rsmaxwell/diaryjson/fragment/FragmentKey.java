package com.rsmaxwell.diaryjson.fragment;

public class FragmentKey implements Comparable {

	public String order;

	public FragmentKey(String order) {
		this.order = order;
	}

	@Override
	public int compareTo(Object o) {
		FragmentKey other = (FragmentKey) o;
		return order.compareTo(other.order);
	}

	@Override
	public String toString() {
		return order;
	}
}
