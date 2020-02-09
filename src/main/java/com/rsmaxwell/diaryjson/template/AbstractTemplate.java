package com.rsmaxwell.diaryjson.template;

import java.io.File;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import com.rsmaxwell.diaryjson.Day;
import com.rsmaxwell.diaryjson.DayOfFragments;
import com.rsmaxwell.diaryjson.Fragment;
import com.rsmaxwell.diaryjson.Month;

public abstract class AbstractTemplate implements Template {

	private File dir;

	public AbstractTemplate(File dir) {
		this.dir = dir;
	}

	public Fragment get(DayOfFragments day) throws Exception {

		LocalDate localDate = LocalDate.of(day.year, day.month, day.day);
		DayOfWeek dayOfWeek = DayOfWeek.from(localDate);

		Map<String, String> map = new HashMap<String, String>();

		map.put("@@YEAR@@", Integer.toString(day.year));
		map.put("@@MONTH@@", Integer.toString(day.month));
		map.put("@@MONTH_NAME@@", Month.toString(day.month));
		map.put("@@DAY@@", Integer.toString(day.day));
		map.put("@@DAY_NAME@@", Day.toString(dayOfWeek.getValue()));

		map.put("@@BUILD_YEAR@@", getenv("BUILD_YEAR", "snapshot"));
		map.put("@@BUILD_ID@@", getenv("BUILD_ID", "snapshot"));
		map.put("@@BUILD_DATE@@", getenv("BUILD_DATE", "snapshot"));
		map.put("@@GIT_COMMIT@@", getenv("GIT_COMMIT", "snapshot"));
		map.put("@@GIT_BRANCH@@", getenv("GIT_BRANCH", "snapshot"));
		map.put("@@GIT_URL@@", getenv("GIT_URL", "snapshot"));

		Fragment fragment = Fragment.MakeFragment(dir);
		fragment.year = day.year;
		fragment.month = day.month;
		fragment.day = day.day;

		String string = fragment.html;
		for (String tag : map.keySet()) {
			string = string.replaceAll(tag, map.get(tag));
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
