package com.rsmaxwell.diaryjson.template;

import java.io.File;
import java.util.List;

import com.rsmaxwell.diaryjson.Fragment;

public class DocumentFooter extends AbstractTemplate {

	public DocumentFooter(File dir) {
		super(dir);
	}

	@Override
	public void add(Fragment previousFragment, Fragment fragment, List<Fragment> listOfNewFragments) throws Exception {
		if (previousFragment != null) {
			if ((fragment == null) || (previousFragment.year != fragment.year)) {
				listOfNewFragments.add(get(previousFragment));
			}
		}
	}

}
