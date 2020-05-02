package com.rsmaxwell.diaryjson.fragment;

import java.util.TreeMap;

public class DateBody {

	public String order;
	public String wordFilename;
	public String diary;
	public String imageFilename;
	public TreeMap<FragmentKey, FragmentBody> fragments = new TreeMap<FragmentKey, FragmentBody>();

	public DateBody(String order, String wordFilename, String diary, String imageFilename) {
		this.order = order;
		this.wordFilename = wordFilename;
		this.imageFilename = imageFilename;
		this.diary = diary;
	}

	public void add(Fragment fragment, DateKey dateKey) {
		FragmentKey key = new FragmentKey(fragment.order);
		FragmentBody body = new FragmentBody(fragment.html);

		FragmentBody original = fragments.get(key);
		if (original != null) {
			System.out.println("Duplicate FragmentKey: " + dateKey + " : " + key);
		} else {
			fragments.put(key, body);
		}
	}
}
