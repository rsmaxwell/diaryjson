package com.rsmaxwell.diaryjson.template;

import java.io.File;
import java.util.List;

import com.rsmaxwell.diaryjson.DayOfFragments;
import com.rsmaxwell.diaryjson.Fragment;

public class MonthHeader extends AbstractTemplate {

	public MonthHeader(File dir) {
		super(dir);
	}

	@Override
	public void add(DayOfFragments previousDay, DayOfFragments day, List<Fragment> listOfNewFragments) throws Exception {
		if (day != null) {
			if ((previousDay == null) || (previousDay.month != day.month)) {
				listOfNewFragments.add(get(day));
			}
		}
	}
}
