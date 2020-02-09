package com.rsmaxwell.diaryjson.template;

import java.io.File;
import java.util.List;

import com.rsmaxwell.diaryjson.Fragment;

public class DayFooter extends AbstractTemplate {

	public DayFooter(File dir) {
		super(dir);
	}

	@Override
	public void add(Fragment previousFragment, Fragment fragment, List<Fragment> listOfNewFragments) throws Exception {
		if (previousFragment != null) {
			if ((fragment == null) || (previousFragment.day != fragment.day)) {
				listOfNewFragments.add(get(previousFragment));
			}
		}
	}

}
