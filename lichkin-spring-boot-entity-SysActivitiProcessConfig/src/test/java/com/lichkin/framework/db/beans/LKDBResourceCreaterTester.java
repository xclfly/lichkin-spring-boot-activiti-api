package com.lichkin.framework.db.beans;

import java.io.IOException;

import org.junit.Test;

public class LKDBResourceCreaterTester {

	@Test
	public void test() throws IOException {
		// 在使用框架前需要调用本方法来创建所需的R文件以及RInitializer文件
		LKDBResourceCreater.createRFiles("SysActivitiProcessConfigR", false);
	}

}
