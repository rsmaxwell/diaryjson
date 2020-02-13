package com.rsmaxwell.diaryjson.template;

import java.io.File;
import java.util.List;

import com.rsmaxwell.diaryjson.Fragment;

public class DocumentHeader extends AbstractTemplate {

	public DocumentHeader(File dir) {
		super(dir);
	}

	@Override
	public void add(Fragment previousFragment, Fragment fragment, List<Fragment> listOfNewFragments) throws Exception {
		if (fragment != null) {
			if ((previousFragment == null) || (fragment.year != previousFragment.year)) {
				listOfNewFragments.add(get(fragment));
			}
		}
	}
}
