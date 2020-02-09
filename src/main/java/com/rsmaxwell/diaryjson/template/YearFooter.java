package com.rsmaxwell.diaryjson.template;

import java.io.File;
import java.util.List;

import com.rsmaxwell.diaryjson.DayOfFragments;
import com.rsmaxwell.diaryjson.Fragment;

public class YearFooter extends AbstractTemplate {

	public YearFooter(File dir) {
		super(dir);
	}

	@Override
	public void add(DayOfFragments previousDay, DayOfFragments day, List<Fragment> listOfNewFragments) throws Exception {
		if (previousDay != null) {
			if ((day == null) || (previousDay.year != day.year)) {
				listOfNewFragments.add(get(previousDay));
			}
		}
	}

}
