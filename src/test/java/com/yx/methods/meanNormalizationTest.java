package com.yx.methods;

import org.junit.Test;

import com.yx.core.Data;

public class meanNormalizationTest {
	
	@Test
	public void dataProcess() {
		Data d = new Data();
		double [][] h = {{0.0,0.0,0.0,0.0},{0.0,1.0,2.0,3.0},{0.0,10.0,20.0,30.0},{0.0,100.0,200.0,300.0}};
		d.calData = h;
		d.Line = 3;
		d.Column = 4;
		double [] avgData = new double[d.Column];
		double [] minData = new double[d.Column];
		double [] maxData = new double[d.Column];
		for(int i=1;i<=d.Line;i++) {
			for(int j=1;j<d.Column;j++) {
				avgData[j]+=d.calData[i][j];
				if(i==1 || d.calData[i][j]<minData[j]) {
					minData[j] = d.calData[i][j];
				}
				if(i==1 || d.calData[i][j]>maxData[j]){
					maxData[j] = d.calData[i][j];
				}
			}
		}
		for(int i=1;i<d.Column;i++) {
			avgData[i]/=d.Line;
		}
		for(int i=1;i<=d.Line;i++) {
			for(int j=1;j<d.Column;j++) {
				d.calData[i][j]=(d.calData[i][j]-avgData[j])/(maxData[j]-minData[j]);
			}
		}
		System.out.println("min");
		for(int i=1;i<d.Column;i++) {
			System.out.print(minData[i]+" ");
		}
		System.out.println("");
		System.out.println("max");
		for(int i=1;i<d.Column;i++) {
			System.out.print(maxData[i]+" ");
		}
		System.out.println("");
		System.out.println("avg");
		for(int i=1;i<d.Column;i++) {
			System.out.print(avgData[i]+" ");
		}
		System.out.println("");
		System.out.println("final");
		for(int i=1;i<=d.Line;i++) {
			for(int j=1;j<d.Column;j++) {
				System.out.print(d.calData[i][j]+" ");
			}
			System.out.println("");
		}
	}
}
