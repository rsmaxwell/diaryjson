package com.rsmaxwell.diaryjson.template;

import java.util.List;

import com.rsmaxwell.diaryjson.Fragment;

public interface Template {

	void add(Fragment previousFragmenty, Fragment fragment, List<Fragment> listOfNewFragments) throws Exception;
}
