package com.rsmaxwell.diaryjson.fragment;

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

	public String wordFilename;
	public String imageFilename;
	public String diary;
	public String[] images;

	@JsonIgnore
	private static ObjectMapper objectMapper;

	static {
		objectMapper = new ObjectMapper();
	}

	public Fragment() {
	}

	public static Fragment readFromFile(String fragmentDirName) throws JsonParseException, JsonMappingException, IOException {

		Fragment fragment = objectMapper.readValue(new File(fragmentDirName + "/fragment.json"), Fragment.class);
		fragment.html = new String(Files.readAllBytes(new File(fragmentDirName, "fragment.html").toPath()));
		fragment.wordFilename = fragmentDirName;
		return fragment;
	}

	public Fragment(int year, int month, int day, String order) throws Exception {
		this.year = year;
		this.month = month;
		this.day = day;
		this.order = order;
	}

	public void check() throws Exception {
		if (year < 1828) {
			throw new Exception("year too small: " + year);
		} else if (year > 1839) {
			throw new Exception("year too large: " + year);
		} else if (month < 1) {
			throw new Exception("month too small: " + month);
		} else if (month > 12) {
			throw new Exception("month too large: " + month);
		} else if (day < 1) {
			throw new Exception("day too small: " + day);
		} else if (day > 31) {
			throw new Exception("day too large: " + day);
		}
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
		return String.format("%04d/%02d/%02d-%s", year, month, day, order);
	}

	@Override
	public String toString() {
		return String.format("%04d-%02d-%02d-%s %s", year, month, day, order, wordFilename);
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
