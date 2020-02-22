package com.rsmaxwell.diaryjson.template;

import java.io.File;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.rsmaxwell.diaryjson.Day;
import com.rsmaxwell.diaryjson.Fragment;
import com.rsmaxwell.diaryjson.Month;

public abstract class AbstractTemplate implements Template {

	private File dir;

	private static String sourcePatternString = "([\\d]{4})-([\\d]{2})-([\\d]{2})-(img[\\d]{4})-.*";
	private static Pattern sourcePattern = Pattern.compile(sourcePatternString);

	public AbstractTemplate(File dir) {
		this.dir = dir;
	}

	public Fragment get(Fragment base) throws Exception {

		LocalDate localDate = LocalDate.of(base.year, base.month, base.day);
		DayOfWeek dayOfWeek = DayOfWeek.from(localDate);

		Map<String, String> map = new HashMap<String, String>();

		String id = String.format("%04d-%02d-%02d", base.year, base.month, base.day);
		map.put("@@ID@@", id);

		if (base.imageFilename != null) {
			map.put("@@PAGE_LINK@@", base.imageFilename);
		}

		map.put("@@YEAR@@", Integer.toString(base.year));
		map.put("@@MONTH@@", Integer.toString(base.month));
		map.put("@@MONTH_NAME@@", Month.toString(base.month));
		map.put("@@DAY@@", Integer.toString(base.day));
		map.put("@@DAY_NAME@@", Day.toString(dayOfWeek.getValue()));

		map.put("@@BUILD_YEAR@@", getenv("BUILD_YEAR", "snapshot"));
		map.put("@@BUILD_ID@@", getenv("BUILD_ID", "snapshot"));
		map.put("@@BUILD_DATE@@", getenv("BUILD_DATE", "snapshot"));
		map.put("@@GIT_COMMIT@@", getenv("GIT_COMMIT", "snapshot"));
		map.put("@@GIT_BRANCH@@", getenv("GIT_BRANCH", "snapshot"));
		map.put("@@GIT_URL@@", getenv("GIT_URL", "snapshot"));

		Fragment fragment = Fragment.MakeFragment(dir);
		fragment.year = base.year;
		fragment.month = base.month;
		fragment.day = base.day;
		fragment.check();

		String string = fragment.html;

		if (string == null) {
			throw new Exception("the html fields is null for the fragment: " + fragment.toString());
		}

		for (String tag : map.keySet()) {
			String replacement = map.get(tag);

			if (replacement == null) {
				throw new Exception("the replacement string is null: tag:" + tag + ", base: " + base);
			}

			string = string.replaceAll(tag, replacement);
		}
		fragment.html = string;
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
