package com.rsmaxwell.diaryjson.template;

import java.io.File;
import java.util.List;

import com.rsmaxwell.diaryjson.Fragment;

public class MonthHeader extends AbstractTemplate {

	public MonthHeader(File dir) {
		super(dir);
	}

	@Override
	public void add(Fragment previousFragment, Fragment fragment, List<Fragment> listOfNewFragments) throws Exception {
		if (fragment != null) {
			if ((previousFragment == null) || (previousFragment.month != fragment.month)) {
				listOfNewFragments.add(get(fragment));
			}
		}
	}
}
