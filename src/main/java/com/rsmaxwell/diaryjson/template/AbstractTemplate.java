package com.rsmaxwell.diaryjson.template;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.rsmaxwell.diaryjson.Day;
import com.rsmaxwell.diaryjson.Month;
import com.rsmaxwell.diaryjson.fragment.DateBody;
import com.rsmaxwell.diaryjson.fragment.DateKey;
import com.rsmaxwell.diaryjson.fragment.Fragment;

public abstract class AbstractTemplate implements Template {

	private String fragmentDirName;

	private static String sourcePatternString = "([\\d]{4})-([\\d]{2})-([\\d]{2})-(img[\\d]{4})-.*";
	private static Pattern sourcePattern = Pattern.compile(sourcePatternString);

	public AbstractTemplate(String fragmentDirName) {
		this.fragmentDirName = fragmentDirName;
	}

	public Fragment get(DateKey key, DateBody body) throws Exception {

		LocalDate localDate = LocalDate.of(key.year, key.month, key.day);
		DayOfWeek dayOfWeek = DayOfWeek.from(localDate);

		Map<String, String> map = new HashMap<String, String>();

		String id = String.format("%04d-%02d-%02d", key.year, key.month, key.day);
		map.put("@@ID@@", id);

		if (body.imageFilename != null) {
			map.put("@@PAGE_LINK@@", body.imageFilename);
		}

		map.put("@@YEAR@@", Integer.toString(key.year));
		map.put("@@MONTH@@", Integer.toString(key.month));
		map.put("@@MONTH_NAME@@", Month.toString(key.month));
		map.put("@@DAY@@", Integer.toString(key.day));
		map.put("@@DAY_NAME@@", Day.toString(dayOfWeek.getValue()));

		map.put("@@BUILD_YEAR@@", getenv("BUILD_YEAR", "snapshot"));
		map.put("@@BUILD_ID@@", getenv("BUILD_ID", "snapshot"));
		map.put("@@BUILD_DATE@@", getenv("BUILD_DATE", "snapshot"));
		map.put("@@GIT_COMMIT@@", getenv("GIT_COMMIT", "snapshot"));
		map.put("@@GIT_BRANCH@@", getenv("GIT_BRANCH", "snapshot"));
		map.put("@@GIT_URL@@", getenv("GIT_URL", "snapshot"));

		Fragment fragment = Fragment.readFromFile(fragmentDirName);
		fragment.year = key.year;
		fragment.month = key.month;
		fragment.day = key.day;
		fragment.imageFilename = body.imageFilename;
		fragment.check();

		String html = fragment.html;
		if (html == null) {
			throw new Exception("the html field is null for the fragment: " + fragment.toString());
		}

		for (String tag : map.keySet()) {
			String replacement = map.get(tag);

			if (replacement == null) {
				throw new Exception("the replacement string is null: tag:" + tag + ", key: " + key + ", body: " + body);
			}

			html = html.replaceAll(tag, replacement);
		}
		fragment.html = html;
		return fragment;
	}

	private String getenv(String key, String fallback) {
		String value = System.getenv(key);
		if (value == null) {
			return fallback;
		}
		return value;
	}

}
