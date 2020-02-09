package com.rsmaxwell.diaryjson.template;

import java.io.File;
import java.util.List;

import com.rsmaxwell.diaryjson.Fragment;

public class YearHeader extends AbstractTemplate {

	public YearHeader(File dir) {
		super(dir);
	}

	@Override
	public void add(Fragment previousFragment, Fragment fragment, List<Fragment> listOfNewFragments) throws Exception {
		if (fragment != null) {
			if ((previousFragment == null) || (previousFragment.year != fragment.year)) {
				listOfNewFragments.add(get(fragment));
			}
		}
	}
}
