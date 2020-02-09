package com.rsmaxwell.diaryjson.template;

import java.io.File;
import java.util.List;

import com.rsmaxwell.diaryjson.Fragment;

public class MonthFooter extends AbstractTemplate {

	public MonthFooter(File dir) {
		super(dir);
	}

	@Override
	public void add(Fragment previousFragment, Fragment fragment, List<Fragment> listOfNewFragments) throws Exception {
		if (previousFragment != null) {
			if ((fragment == null) || (previousFragment.month != fragment.month)) {
				listOfNewFragments.add(get(previousFragment));
			}
		}
	}
}
