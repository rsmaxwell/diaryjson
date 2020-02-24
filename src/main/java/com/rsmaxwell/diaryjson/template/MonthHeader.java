package com.rsmaxwell.diaryjson.template;

import java.util.List;

import com.rsmaxwell.diaryjson.fragment.DateBody;
import com.rsmaxwell.diaryjson.fragment.DateKey;
import com.rsmaxwell.diaryjson.fragment.Fragment;

public class MonthHeader extends AbstractTemplate {

	public MonthHeader(String fragmentDirName) {
		super(fragmentDirName);
	}

	@Override
	public void add(DateKey previousDateKey, DateBody previousDateBody, DateKey dateKey, DateBody dateBody, List<Fragment> listOfNewFragments)
			throws Exception {

		if (dateKey != null) {
			if ((previousDateKey == null) || (previousDateKey.month != dateKey.month)) {
				listOfNewFragments.add(get(dateKey, dateBody));
			}
		}
	}
}
