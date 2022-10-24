package com.yx.core;

import Jama.Matrix;

public class DataOrMatrix {
	public static double[][] dataToMatrix(Data d){
		double [][] a = new double[d.Line][d.Column];
		for(int i=0;i<d.Line;i++) {
			for(int j=0;j<d.Column;j++) {
				a[i][j]=d.calData[i+1][j+1];
			}
		}
		return a;
	}
	
	public static Data matrixToData(Matrix m) {
		Data d = new Data();
		d.Line = m.getRowDimension();
		d.Column = m.getColumnDimension();
		d.calData = new double[d.Line+1][d.Column+1];
		double [][] table =m.getArray();
		for(int i=1;i<=d.Line;i++) {
			for(int j=1;j<=d.Column;j++) {
				d.calData[i][j] = table[i-1][j-1];
			}
		}
		return d;
	}
}
