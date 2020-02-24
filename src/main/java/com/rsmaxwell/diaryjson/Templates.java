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

	public Templates(String templatesDirName) throws Exception {
		File templatesDir = new File(templatesDirName);
		for (String name : templatesDir.list()) {
			templates.add(NewTemplate(templatesDirName, name));
		}

		if (templates.size() == 0) {
			throw new Exception("No templates found in: " + templatesDirName);
		}
	}

	public void addGeneratedFragments(TreeMap<Key, Fragment> mapOfFragments) throws Exception {

		List<Fragment> listOfNewFragments = new ArrayList<Fragment>();

		Fragment previousFragment = null;
		for (Key key : mapOfFragments.keySet()) {
			Fragment fragment = mapOfFragments.get(key);
			add(previousFragment, fragment, listOfNewFragments);
			previousFragment = fragment;
		}
		add(previousFragment, null, listOfNewFragments);

		for (Fragment fragment : listOfNewFragments) {
			Key key = new Key(fragment.year, fragment.month, fragment.day, fragment.order);

			Fragment original = mapOfFragments.get(key);
			if (original != null) {
				System.out.println("Duplicate fragment:");
				System.out.println("    discarding: " + fragment.toString());
				System.out.println("    keeping :   " + original.toString());
			} else {
				mapOfFragments.put(key, fragment);
			}
		}
	}

	private Template NewTemplate(String templatesDirName, String templateName) throws Exception {

		String templateJsonFilename = templatesDirName + "/" + templateName + "/template.json";

		try {

			TemplateInfo info = objectMapper.readValue(templateJsonFilename, TemplateInfo.class);

			Class<?> clazz = Class.forName(info.classname);
			Constructor<?> ctor = clazz.getConstructor(File.class);
			Object object = ctor.newInstance(new Object[] { templateName });

			if (!Template.class.isInstance(object)) {
				throw new Exception("The template [" + templateJsonFilename + "] class [" + info.classname + "] does not implement ["
						+ Template.class.getName() + "]");
			}

			return (Template) object;

		} catch (Exception e) {
			throw new Exception(templateJsonFilename, e);
		}
	}

	private void add(Fragment previousFragment, Fragment fragment, List<Fragment> listOfNewFragments) throws Exception {
		for (Template template : templates) {
			template.add(previousFragment, fragment, listOfNewFragments);
		}
	}
}
