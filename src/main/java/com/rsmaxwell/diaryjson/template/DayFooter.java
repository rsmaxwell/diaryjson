package com.rsmaxwell.diaryjson.template;

import java.io.File;
import java.util.List;

import com.rsmaxwell.diaryjson.DayOfFragments;
import com.rsmaxwell.diaryjson.Fragment;

public class DayFooter extends AbstractTemplate {

	public DayFooter(File dir) {
		super(dir);
	}

	@Override
	public void add(DayOfFragments previousDay, DayOfFragments day, List<Fragment> listOfNewFragments) throws Exception {
		if (previousDay != null) {
			if ((day == null) || (previousDay.day != day.day)) {
				listOfNewFragments.add(get(previousDay));
			}
		}
	}

}
