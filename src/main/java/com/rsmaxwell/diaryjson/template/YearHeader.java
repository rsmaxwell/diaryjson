package com.rsmaxwell.diaryjson.template;

import java.util.List;

import com.rsmaxwell.diaryjson.fragment.DateBody;
import com.rsmaxwell.diaryjson.fragment.DateKey;
import com.rsmaxwell.diaryjson.fragment.Fragment;

public class YearHeader extends AbstractTemplate {

	public YearHeader(String fragmentDirName) {
		super(fragmentDirName);
	}

	@Override
	public void add(DateKey previousDateKey, DateBody previousDateBody, DateKey dateKey, DateBody dateBody, List<Fragment> listOfNewFragments)
			throws Exception {

		if (dateKey != null) {
			if ((previousDateKey == null) || (previousDateKey.year != dateKey.year)) {
				listOfNewFragments.add(get(dateKey, dateBody));
			}
		}
	}
}
