package com.yx.methods;

import com.yx.core.Data;
import com.yx.core.DataOrMatrix;

import Jama.Matrix;
import Jama.SingularValueDecomposition;

public class PCA {
	
	public static Data runPCAAfterCovar(Data d,Data ds,int k) {
		double [][] a = DataOrMatrix.dataToMatrix(d);
		Matrix m = new Matrix(a);
		SingularValueDecomposition s = m.svd();
		Matrix U =s.getU();
		Data df = DataOrMatrix.matrixToData(U);
		Data re = new Data();
		re.Line = ds.Line;
		re.Column = k;
		re.calData = new double[re.Line+1][re.Column+1];
		for(int i=1;i<=ds.Line;i++) {
			for(int j=1;j<=k;j++) {
				double sum = 0;
				for(int p=1;p<=df.Column;p++) {
					sum += ds.calData[i][p]*df.calData[p][j];
				}
				re.calData[i][j]= sum;
			}
		}
		return re;
	}
	
	public static Data runPCA(Data d,Data ds,int k) {
		Data dg = Covariance.covarianceProcess(d);
		double [][] a = DataOrMatrix.dataToMatrix(dg);
		Matrix m = new Matrix(a);
		SingularValueDecomposition s = m.svd();
		Matrix U =s.getU();
		Data df = DataOrMatrix.matrixToData(U);
		Data re = new Data();
		re.Line = ds.Line;
		re.Column = k;
		re.calData = new double[re.Line+1][re.Column+1];
		for(int i=1;i<=ds.Line;i++) {
			for(int j=1;j<=k;j++) {
				double sum = 0;
				for(int p=1;p<=df.Column;p++) {
					sum += ds.calData[i][p]*df.calData[p][j];
				}
				re.calData[i][j]= sum;
			}
		}
		return re;
	}
}
