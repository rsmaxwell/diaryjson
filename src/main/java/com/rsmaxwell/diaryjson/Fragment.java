package com.rsmaxwell.diaryjson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Fragment implements Comparable, Cloneable {

	public int year;
	public int month;
	public int day;
	public String order;

	@JsonIgnore
	public String html;

	@JsonIgnore
	public String notes;

	public String source;

	@JsonIgnore
	private static ObjectMapper objectMapper;

	static {
		objectMapper = new ObjectMapper();
	}

	public Fragment() {
	}

	public static Fragment MakeFragment(File dir) throws JsonParseException, JsonMappingException, IOException {
		Fragment fragment = objectMapper.readValue(new File(dir, "fragment.json"), Fragment.class);
		fragment.html = new String(Files.readAllBytes(new File(dir, "fragment.html").toPath()));
		fragment.source = dir.getCanonicalPath();
		return fragment;
	}

	public Fragment(int year, int month, int day, String order) {
		this.year = year;
		this.month = month;
		this.day = day;
		this.order = order;
	}

	@Override
	public boolean equals(Object obj) {
		return (compareTo(obj) == 0);
	}

	@Override
	public int compareTo(Object o) {
		Fragment other = (Fragment) o;
		if (year != other.year) {
			return year - other.year;
		}
		if (month != other.month) {
			return month - other.month;
		}
		if (day != other.day) {
			return day - other.day;
		}
		return order.compareTo(other.order);
	}

	public String toDirectoryName() {
		return String.format("%04d-%02d-%02d-%s", year, month, day, order);
	}

	@Override
	public String toString() {
		return String.format("%04d-%02d-%02d-%s %s", year, month, day, order, source);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
