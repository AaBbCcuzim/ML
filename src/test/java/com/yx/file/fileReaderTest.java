package com.yx.file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.junit.Test;

import com.yx.core.Data;

public class fileReaderTest {
	
	@Test
	public void loadFile() {
		String f = fileTest.class.getClassLoader().getResource("testdata1.txt").getPath();
		Data d = new Data();
		ArrayList<ArrayList<Double>> inData = new ArrayList<>();
		String stringData;
		int m = 0;
		try{
			InputStreamReader isn = new InputStreamReader(new FileInputStream(f),"UTF-8");
			BufferedReader brr = new BufferedReader(isn);
			while((stringData = brr.readLine()) != null) {	
				 ArrayList<Double> inDataLine = new ArrayList<>();
				 if(!stringData.contains("timestamp")) {
					 m++;
					 int cnth = 0;
					 int cntt = 0;
					 int n = 0;
					 String toData;
					 while(cntt < stringData.length()) {
						 if(stringData.charAt(cntt) == ',' || cntt == stringData.length()) {
							 if(cnth == cntt) {
								 cntt++; 
								 cnth = cntt;
							 }
							 else {
								 toData = stringData.substring(cnth, cntt);
								 if(toData.contains("-")) {
									 cntt++; 
									 cnth = cntt;
								 }
								 else {
									 inDataLine.add(Double.parseDouble(toData));
									 n++;
									 cntt++; 
									 cnth = cntt;
								 }
							 }
							 
						 }
						 else cntt++;
					 }
					 if(cnth != cntt) {
						 toData	= stringData.substring(cnth, cntt);	 
						 inDataLine.add(Double.parseDouble(toData));
					 }
					 n++;
					 inData.add(inDataLine);
					 d.Column = n;
				 }
			}
			d.Line = m;	
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		d.calData = new double[d.Line+1][d.Column+1]; 
		for(int i=1;i<=d.Line;i++) {
			for(int j=1;j<=d.Column;j++) { 
				d.calData[i][j] = inData.get(i-1).get(j-1);
			} 
		}
		System.out.println("Access fileReaderTest");
		for(int i=1;i<=d.Line;i++) {
			for(int j=1;j<=d.Column;j++) { 
				System.out.print(d.calData[i][j]+" ");
			} 
			System.out.println("");
		}
	}
}
