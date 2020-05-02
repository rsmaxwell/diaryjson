package com.rsmaxwell.diaryjson.template;

import java.io.IOException;
import java.util.List;
import java.util.TreeMap;

import com.rsmaxwell.diaryjson.Templates;
import com.rsmaxwell.diaryjson.fragment.DateBody;
import com.rsmaxwell.diaryjson.fragment.DateKey;
import com.rsmaxwell.diaryjson.fragment.Fragment;
import com.rsmaxwell.diaryjson.fragment.FragmentBody;
import com.rsmaxwell.diaryjson.fragment.FragmentKey;

public class FragmentList {

	public TreeMap<DateKey, DateBody> dates = new TreeMap<DateKey, DateBody>();

	public void add(Fragment fragment) {

		DateKey key = new DateKey(fragment.year, fragment.month, fragment.day);

		DateBody body = dates.get(key);
		if (body == null) {
			body = new DateBody(fragment.order, fragment.wordFilename, fragment.diary, fragment.imageFilename);
			dates.put(key, body);
		}

		if (body.wordFilename == null) {
			body.wordFilename = fragment.wordFilename;
		}

		if (body.diary == null) {
			body.diary = fragment.diary;
		}

		if (body.imageFilename == null) {
			body.imageFilename = fragment.imageFilename;
		}

		body.add(fragment, key);
	}

	public void addGeneratedFragments(List<Fragment> listOfNewFragments, Templates templates) throws Exception {

		DateKey previousDateKey = null;
		DateBody previousDateBody = null;

		for (DateKey key : dates.keySet()) {
			DateBody body = dates.get(key);

			templates.add(previousDateKey, previousDateBody, key, body, listOfNewFragments);

			previousDateKey = key;
			previousDateBody = body;
		}
	}

	public void generateHtmlDocuments(String baseUriName, DiaryOutput output) throws IOException {

		StringBuilder html = new StringBuilder();
		DateKey previousDateKey = new DateKey(0, 0, 0);

		for (DateKey dateKey : dates.keySet()) {
			DateBody dateBody = dates.get(dateKey);

			if (previousDateKey.year != dateKey.year) {
				if (html.length() > 0) {
					output.generate(previousDateKey.year, html.toString());
				}

				html = new StringBuilder();
			}

			for (FragmentKey fragmentKey : dateBody.fragments.keySet()) {
				FragmentBody fragmentBody = dateBody.fragments.get(fragmentKey);

				html.append(fragmentBody.html);
				previousDateKey = dateKey;
			}
		}

		if (html.length() > 0) {
			output.generate(previousDateKey.year, html.toString());
		}
	}
}
