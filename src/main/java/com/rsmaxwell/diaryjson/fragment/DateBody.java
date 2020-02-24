package com.rsmaxwell.diaryjson.fragment;

import java.util.TreeMap;

public class DateBody {

	public String order;
	public String wordFilename;
	public String imageFilename;
	public TreeMap<FragmentKey, FragmentBody> fragments = new TreeMap<FragmentKey, FragmentBody>();

	public DateBody(String order, String wordFilename, String imageFilename) {
		this.order = order;
		this.wordFilename = wordFilename;
		this.imageFilename = imageFilename;
	}

	public void add(Fragment fragment) {
		FragmentKey key = new FragmentKey(fragment.order);
		FragmentBody body = new FragmentBody(fragment.html);

		FragmentBody original = fragments.get(key);
		if (original != null) {
			System.out.println("Duplicate FragmentKey: " + key);
			System.out.println("    discarding: " + body);
			System.out.println("    keeping :   " + original);
		} else {
			fragments.put(key, body);
		}
	}
}
