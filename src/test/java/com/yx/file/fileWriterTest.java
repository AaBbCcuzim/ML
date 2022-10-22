package com.yx.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

import org.junit.Test;

import com.yx.core.Data;
import com.yx.methods.KKMeans;

public class fileWriterTest {

	@Test
	public void writeFile() throws IOException {
		String fa = fileWriterTest.class.getClassLoader().getResource("testdata2.txt").getPath();	
		Data d = FileReaderFromCsv.loadFile(new File(fa));
		int k = 3;
		KKMeans ke = new KKMeans(d,k);
		ke.runKKMeans();
		String fb = fileWriterTest.class.getClassLoader().getResource("testdata3.txt").getPath();
		File f = new File(fb);
		FileOutputStream h = new FileOutputStream(f);
		OutputStreamWriter w = new OutputStreamWriter(h,"UTF-8");
		Double a;
		Integer b;
		for(int i=1;i<=ke.resultLine;i++) {
			for(int j=1;j<ke.resultColumn;j++) {
				a = ke.result[i][j];
				w.write(a.toString()+",");
			}
			b = (int)ke.result[i][ke.resultColumn];
			w.write(b.toString()+"\r\n");
			w.flush();
		}
		w.close();
		System.out.println("Access fileWriterTest");
	}
}
