package com.rsmaxwell.diaryjson.template;

import java.io.File;
import java.util.List;

import com.rsmaxwell.diaryjson.DayOfFragments;
import com.rsmaxwell.diaryjson.Fragment;

public class YearHeader extends AbstractTemplate {

	public YearHeader(File dir) {
		super(dir);
	}

	@Override
	public void add(DayOfFragments previousDay, DayOfFragments day, List<Fragment> listOfNewFragments) throws Exception {
		if (day != null) {
			if ((previousDay == null) || (previousDay.year != day.year)) {
				listOfNewFragments.add(get(day));
			}
		}
	}
}
