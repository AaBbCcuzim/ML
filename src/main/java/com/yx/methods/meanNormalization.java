package com.yx.methods;

import com.yx.core.Data;

public class meanNormalization {
	
	public static Data dataProcess(Data d) {
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
		return d;
	}
}
