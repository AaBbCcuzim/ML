package com.yx.methods;

import java.io.File;
import org.junit.Test;

import com.yx.core.Data;
import com.yx.file.FileReaderFromCsv;
import com.yx.file.fileTest;

public class KKMeansTest {

	@Test
	public void mainTest() {
		String f = fileTest.class.getClassLoader().getResource("testdata2.txt").getPath();
		Data d = FileReaderFromCsv.loadFile(new File(f));
		int k = 3;
		KKMeans kt = new KKMeans(d,k);
		System.out.println("Access create Kmeans");
		kt.runKKMeans();
		System.out.println("Access run Kmeans");
		kt.printResult();
		System.out.println("Access printresult Kmeans");
		kt.printCenterData();
		System.out.println("Access printCenterData Kmeans");
		// line start from point one 
		double [] a = {0.0,1.0,1.0}; 
		double [][] b = {{0.0,0.0,0.0},{0.0,2.0,2.0},{0.0,255.0,255.0}};
		kt.predictData(a);
		kt.printPredict();
		kt.predictData(b, 2);
		kt.printPredict();
		System.out.println("Access printPredict Kmeans");
		
	}
}
