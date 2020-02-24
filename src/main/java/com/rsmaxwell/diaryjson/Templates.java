package com.rsmaxwell.diaryjson;

import java.io.File;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rsmaxwell.diaryjson.fragment.DateBody;
import com.rsmaxwell.diaryjson.fragment.DateKey;
import com.rsmaxwell.diaryjson.fragment.Fragment;
import com.rsmaxwell.diaryjson.template.FragmentList;
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

	public void addGeneratedFragments(FragmentList fragments) throws Exception {

		List<Fragment> listOfNewFragments = new ArrayList<Fragment>();

		fragments.addGeneratedFragments(listOfNewFragments, this);

		for (Fragment fragment : listOfNewFragments) {
			fragments.add(fragment);
		}
	}

	private Template NewTemplate(String templatesDirName, String templateName) throws Exception {

		String fragmentDirName = templatesDirName + "/" + templateName;
		String templateJsonFilename = fragmentDirName + "/template.json";

		try {

			TemplateInfo info = objectMapper.readValue(new File(templateJsonFilename), TemplateInfo.class);

			Class<?> clazz = Class.forName(info.classname);
			Constructor<?> ctor = clazz.getConstructor(String.class);
			Object object = ctor.newInstance(new Object[] { fragmentDirName });

			if (!Template.class.isInstance(object)) {
				throw new Exception("The template [" + templateJsonFilename + "] class [" + info.classname + "] does not implement ["
						+ Template.class.getName() + "]");
			}

			return (Template) object;

		} catch (Exception e) {
			throw new Exception(templateJsonFilename, e);
		}
	}

	public void add(DateKey previousDateKey, DateBody previousDateBody, DateKey dateKey, DateBody dateBody, List<Fragment> listOfNewFragments)
			throws Exception {

		for (Template template : templates) {
			template.add(previousDateKey, previousDateBody, dateKey, dateBody, listOfNewFragments);
		}
	}
}
