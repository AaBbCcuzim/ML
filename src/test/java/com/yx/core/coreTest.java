package com.yx.core;

import org.junit.Test;

public class coreTest {
	
	@Test
	public void print(){
		Data d = new Data();
		double [][] a = {{0.0,0.0,0.0},{0.0,1.1,1.0},{0.0,1.0,1.0}};
		d.calData = a;
		d.Line = 2;
		d.Column = 2;
		for(int i=1;i<=d.Line;i++){
			for(int j=1;j<=d.Column;j++) {
				System.out.print(d.calData[i][j]+" ");
			}
			System.out.println("");
		}
	}
}
