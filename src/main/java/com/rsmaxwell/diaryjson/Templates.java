package com.rsmaxwell.diaryjson;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rsmaxwell.diaryjson.template.Template;
import com.rsmaxwell.diaryjson.template.TemplateInfo;

public class Templates {

	private List<Template> templates = new ArrayList<Template>();

	private static ObjectMapper objectMapper;

	static {
		objectMapper = new ObjectMapper();
	}

	public Templates(File templateDir) throws Exception {
		for (File dir : templateDir.listFiles()) {
			templates.add(NewTemplate(dir));
		}
	}

	public void addGeneratedFragments(TreeMap<Key, DayOfFragments> mapOfDays) throws Exception {
		List<Fragment> listOfNewFragments = new ArrayList<Fragment>();
		DayOfFragments previousDay = null;

		for (Key key : mapOfDays.keySet()) {
			DayOfFragments day = mapOfDays.get(key);
			add(previousDay, day, listOfNewFragments);
			previousDay = day;
		}
		add(previousDay, null, listOfNewFragments);

		for (Fragment fragment : listOfNewFragments) {
			Key key = new Key(fragment.year, fragment.month, fragment.day);
			DayOfFragments day = mapOfDays.get(key);
			day.add(fragment);
		}
	}

	private Template NewTemplate(File dir) throws Exception {
		try {
			TemplateInfo info = objectMapper.readValue(new File(dir, "template.json"), TemplateInfo.class);

			Class<?> clazz = Class.forName(info.classname);
			Constructor<?> ctor = clazz.getConstructor(File.class);
			Object object = ctor.newInstance(new Object[] { dir });

			if (!Template.class.isInstance(object)) {
				throw new Exception("The template [" + dir.getCanonicalPath() + "] class [" + info.classname + "] does not implement ["
						+ Template.class.getName() + "]");
			}

			return (Template) object;

		} catch (Exception e) {
			throw new Exception(dir.getCanonicalPath(), e);
		}
	}

	private void add(DayOfFragments previousDay, DayOfFragments day, List<Fragment> listOfNewFragments) throws Exception {
		for (Template template : templates) {
			template.add(previousDay, day, listOfNewFragments);
		}
	}
}
