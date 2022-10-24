package com.yx.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import com.yx.core.Data;
import com.yx.methods.Covariance;
import com.yx.methods.KKMeans;


public class FileWriterToTxt extends FileWriter {
	
	public static void writeFile(KKMeans ke,File f) throws IOException {
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
	}
	
	public static void writeFile(Data d,File f) throws IOException {
		FileOutputStream h = new FileOutputStream(f);
		OutputStreamWriter w = new OutputStreamWriter(h,"UTF-8");
		Double a;
		for(int i=1;i<=d.Line;i++) {
			for(int j=1;j<=d.Column;j++) {
				a = d.calData[i][j];
				w.write(a.toString()+",");
			}
			w.write("\r\n");
			w.flush();
		}
		w.close();
	}
	
	public static void writeFile(double [][] d,int line,int column,File f) throws IOException {
		FileOutputStream h = new FileOutputStream(f);
		OutputStreamWriter w = new OutputStreamWriter(h,"UTF-8");
		Double a;
		for(int i=0;i<line;i++) {
			for(int j=0;j<column;j++) {
				a = d[i][j];
				w.write(a.toString()+",");
			}
			w.write("\r\n");
			w.flush();
		}
		w.close();
	}
}
