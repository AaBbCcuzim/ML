package com.yx.methods;

import com.yx.core.Data;

public class Covariance {
	public static Data covarianceProcess(Data d) {
		double [] avgData = new double[d.Column];
		for(int i=1;i<=d.Line;i++) {
			for(int j=1;j<d.Column;j++) {
				avgData[j]+=d.calData[i][j];
			}
		}
		for(int i=1;i<d.Column;i++) {
			avgData[i]/=d.Line;
		}
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
		return df;
	}
}
