package com.rsmaxwell.diaryjson.template;

import java.io.IOException;

public interface DiaryOutput {

	void generate(int year, String html) throws IOException;
}
