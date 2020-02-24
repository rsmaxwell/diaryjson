package com.rsmaxwell.diaryjson.template;

import java.util.List;

import com.rsmaxwell.diaryjson.fragment.DateBody;
import com.rsmaxwell.diaryjson.fragment.DateKey;
import com.rsmaxwell.diaryjson.fragment.Fragment;

public class DocumentFooter extends AbstractTemplate {

	public DocumentFooter(String fragmentDirName) {
		super(fragmentDirName);
	}

	@Override
	public void add(DateKey previousDateKey, DateBody previousDateBody, DateKey dateKey, DateBody dateBody, List<Fragment> listOfNewFragments)
			throws Exception {

		if (previousDateKey != null) {
			if ((dateKey == null) || (previousDateKey.year != dateKey.year)) {
				listOfNewFragments.add(get(previousDateKey, previousDateBody));
			}
		}
	}

}
