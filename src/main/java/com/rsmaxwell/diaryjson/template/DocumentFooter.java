package com.rsmaxwell.diaryjson.template;

import java.io.File;
import java.util.List;

import com.rsmaxwell.diaryjson.DayOfFragments;
import com.rsmaxwell.diaryjson.Fragment;

public class DocumentFooter extends AbstractTemplate {

	public DocumentFooter(File dir) {
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
