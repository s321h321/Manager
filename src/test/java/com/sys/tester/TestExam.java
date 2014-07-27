package com.sys.tester;



import java.io.File;

import org.beetl.core.misc.BeetlUtil;
import org.junit.Test;

public class TestExam {

	@Test
	public void test() {
		System.out.println(BeetlUtil.getWebRoot() + File.separator);
	}

}
