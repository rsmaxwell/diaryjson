package com.rsmaxwell.diaryjson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Template {

	private String template;
	private Map<String, String> map = new HashMap<String, String>();

	public static String getString(String text) throws Exception {
		Template t = new Template(text);
		return t.process();
	}

	public static String getString(File file) throws Exception {
		Template t = new Template(file);
		return t.process();
	}

	public static String getString(File file, DayOfFragments day) throws Exception {
		Template t = new Template(file);
		return t.process(day);
	}

	public Template(String text) throws IOException {
		template = text;
	}

	public Template(File file) throws IOException {
		template = new String(Files.readAllBytes(file.toPath()));
	}

	public String process(DayOfFragments day) throws Exception {

		LocalDate localDate = LocalDate.of(day.year, day.month, day.day);
		DayOfWeek dayOfWeek = DayOfWeek.from(localDate);

		map.put("@@YEAR@@", Integer.toString(day.year));
		map.put("@@MONTH@@", Integer.toString(day.month));
		map.put("@@MONTH_NAME@@", Month.toString(day.month));
		map.put("@@DAY@@", Integer.toString(day.day));
		map.put("@@DAY_NAME@@", Day.toString(dayOfWeek.getValue()));

		return process();
	}

	public String process() throws Exception {

		map.put("@@BUILD_ID@@", getenv("BUILD_ID", "snapshot"));
		map.put("@@BUILD_DATE@@", getenv("BUILD_DATE", "snapshot"));
		map.put("@@GIT_COMMIT@@", getenv("GIT_COMMIT", "snapshot"));
		map.put("@@GIT_BRANCH@@", getenv("GIT_BRANCH", "snapshot"));
		map.put("@@GIT_URL@@", getenv("GIT_URL", "snapshot"));

		String string = template;
		for (String tag : map.keySet()) {
			string = string.replaceAll(tag, map.get(tag));
		}

		return string;
	}

	private String getenv(String key, String fallback) {
		String value = System.getenv(key);
		if (value == null) {
			return fallback;
		}
		return value;
	}
}
