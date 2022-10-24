package com.yx.methods;

import org.junit.Test;

import com.yx.core.Data;

public class covarianceTest {
	
	@Test
	public void mainTest() {
		Data d = new Data();
		double [][] h = {{0.0,0.0,0.0},{0.0,1.0,2.0},{0.0,3.0,6.0},{0.0,4.0,2.0},{0.0,5.0,2.0}};
		d.calData = h;
		d.Line = 4;
		d.Column = 3;
		double [] avgData = new double[d.Column];
		for(int i=1;i<=d.Line;i++) {
			for(int j=1;j<d.Column;j++) {
				avgData[j]+=d.calData[i][j];
			}
		}
		for(int i=1;i<d.Column;i++) {
			avgData[i]/=d.Line;
		}
		for(int i=1;i<d.Column;i++) {
			System.out.print(avgData[i]+" ");
		}
		System.out.println("");
		Data df = new Data();
		df.Line = d.Column-1;
		df.Column = d.Column-1;
		df.calData = new double[d.Column][d.Column];
		for(int i=1;i<d.Column;i++) {
			for(int j=1;j<d.Column;j++) {
				double sum = 0;
				for(int k=1;k<=d.Line;k++) {
					sum += (d.calData[k][i]-avgData[i])*(d.calData[k][j]-avgData[j]);
				}
				sum/=(d.Line-1);
				df.calData[i][j]=sum;
			}
		}
		for(int i=1;i<=d.Column-1;i++) {
			for(int j=1;j<=d.Column-1;j++) {
				System.out.print(df.calData[i][j]+" ");
			}
			System.out.println("");
		}
	}
}
