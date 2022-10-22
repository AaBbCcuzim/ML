package com.yx.file;

import org.junit.Test;

public class fileTest {
	
	@Test
	public void getFilePath() {
		String filePath = fileTest.class.getClassLoader().getResource("testdata1.txt").getPath();
        System.out.println(filePath);
	}
	
}
