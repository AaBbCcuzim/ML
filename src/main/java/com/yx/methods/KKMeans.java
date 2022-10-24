package com.yx.methods;

import java.util.Random;

import com.yx.core.Data;

public class KKMeans {
	public int k;
	public double minJ;
	public int dataLine;
	public int dataColumn;
	public int resultLine;
	public int resultColumn;
	public int predictLine;
	public int predictColumn;
	public double [][] data;
	public double [][] centerData;
	public double [][] result;
	public double [][] predict;
	
	//ju means have tag or no tag,tag to true,no tag to false 
	public KKMeans(Data d,int k,boolean ju) { 
		this.data=d.calData;		
		this.dataLine=d.Line;
		if(ju) {
			this.dataColumn=d.Column-1;
		}
		else this.dataColumn=d.Column;
		this.k=k;
	}
	
	public void runKKMeans(int times) {
		resultLine = dataLine;
		resultColumn = dataColumn+1;
		result = new double [dataLine+1][dataColumn+2];
		centerData = new double [k+1][dataColumn+1];
		for(int q=1;q<=times;q++) {
			int toLine;
			int toColumn;
			boolean stop;
			double [][] toResult ;
			double [][] toCenterData = new double [k+1][dataColumn+1];
			int [] labor_cnt = new int [dataLine+1];
	 		
			Random r = new Random();
			
			for(int i=1;i<=k;i++) {
				toCenterData[i] = data[r.nextInt(dataLine)+1];
			}
			while(true) {
				double [][] newCenterData = new double[k+1][dataColumn+1];
				int [] labornum = new int[k+1];
				stop = true;
				
				for(int i=1;i<=dataLine;i++) {
					int labor = 0;
					double edge = 0;
					for(int j=1;j<=k;j++) {
						double edget = 0;
						for(int t=1;t<=dataColumn;t++) {
							edget += (data[i][t]-toCenterData[j][t])*(data[i][t]-toCenterData[j][t]);
						}
						if(labor == 0 || edget < edge) {
							labor = j;
							edge = edget;
						}
						
					}
					if(labor_cnt[i] != labor) {
						stop = false;
					}
					labor_cnt[i] = labor;
				}
				if(stop == true) {
					toLine = dataLine;
					toColumn = dataColumn+1;
					toResult = new double[toLine+1][toColumn+1];
					for(int i=1;i<=dataLine;i++) {
						for(int j=1;j<=dataColumn;j++) {
							toResult [i][j] = data[i][j];
						}
						toResult[i][dataColumn+1] = labor_cnt[i];
					}
					break;
				}
				for(int i=1;i<=dataLine;i++) {
					int laborc = labor_cnt[i];
					for(int j=1;j<=dataColumn;j++) {
						newCenterData[laborc][j] += data[i][j];				
					}
					labornum[laborc]++;
				}
				for(int i=1;i<=k;i++) {
					for(int j=1;j<=dataColumn;j++) {
						newCenterData[i][j]/=labornum[i];
					}
					toCenterData[i] = newCenterData[i];
				}
			}
			double cj = 0;
			for(int i=1;i<=toLine;i++) {
				double sum = 0;
				for(int j=1;j<=toColumn-1;j++) {
					sum+=(toResult[i][j]-toCenterData[(int)toResult[i][toColumn]][j])*(toResult[i][j]-toCenterData[(int)toResult[i][toColumn]][j]);
				}
				cj+=sum;
			}
			cj/=toLine;
			if(q==1) {
				minJ = cj;
				for(int i=1;i<=toLine;i++) {
					for(int j=1;j<=toColumn;j++) {
						result[i][j]=toResult[i][j];
					}
				}
				for(int i=1;i<=k;i++) {
					for(int j=1;j<=toColumn-1;j++) {
						centerData[i][j]=toCenterData[i][j];
					}
				}
			}
			else {
				if(cj<minJ) {
					
					minJ=cj;
					for(int i=1;i<=toLine;i++) {
						for(int j=1;j<=toColumn;j++) {
							result[i][j]=toResult[i][j];
						}
					}
					for(int i=1;i<=k;i++) {
						for(int j=1;j<=toColumn-1;j++) {
							centerData[i][j]=toCenterData[i][j];
						}
					}
				}
			}
		}
 	}
	
	
	public void runKKMeans() {
		result = new double [dataLine+1][dataColumn+2];
		centerData = new double [k+1][dataColumn+1];
		int [] labor_cnt = new int [dataLine+1];
 		boolean stop = true;
		Random r = new Random();
		
		for(int i=1;i<=k;i++) {
			centerData[i] = data[r.nextInt(dataLine)+1];
		}
		while(true) {
			double [][] newCenterData = new double[k+1][dataColumn+1];
			int [] labornum = new int[k+1];
			stop = true;
			
			for(int i=1;i<=dataLine;i++) {
				int labor = 0;
				double edge = 0;
				for(int j=1;j<=k;j++) {
					double edget = 0;
					for(int t=1;t<=dataColumn;t++) {
						edget += (data[i][t]-centerData[j][t])*(data[i][t]-centerData[j][t]);
					}
					if(labor == 0 || edget < edge) {
						labor = j;
						edge = edget;
					}
					
				}
				if(labor_cnt[i] != labor) {
					stop = false;
				}
				labor_cnt[i] = labor;
			}
			if(stop == true) {
				resultLine = dataLine;
				resultColumn = dataColumn+1;

				for(int i=1;i<=dataLine;i++) {
					for(int j=1;j<=dataColumn;j++) {
						result [i][j] = data[i][j];
					}
					result[i][dataColumn+1] = labor_cnt[i];
				}
				break;
			}
			for(int i=1;i<=dataLine;i++) {
				int laborc = labor_cnt[i];
				for(int j=1;j<=dataColumn;j++) {
					newCenterData[laborc][j] += data[i][j];				
				}
				labornum[laborc]++;
			}
			for(int i=1;i<=k;i++) {
				for(int j=1;j<=dataColumn;j++) {
					newCenterData[i][j]/=labornum[i];
				}
				centerData[i] = newCenterData[i];
			}
		}
 	}
	
	public void printResult() {
		for(int i=1;i<=resultLine;i++) {
			for(int j=1;j<=resultColumn;j++) {
				System.out.print(result[i][j]+" ");
			}
			System.out.println("");
		}
	}
	
	public void predictData(double [] predictData) {
		predictLine = 1;
		predictColumn = dataColumn+1;
		predict = new double[2][dataColumn+2]; 
		
		int labor = 0;
		double edge = 0;
		for(int i=1;i<=k;i++) {
			double edget = 0;
			for(int j=1;j<=dataColumn;j++) {
				edget += (predictData[j]-centerData[i][j])*(predictData[j]-centerData[i][j]);
			}
			if(labor == 0 || edget < edge) {
				labor = i;
				edge = edget;
			}
		}
		for(int i=1;i<=dataColumn;i++) {
			predict[1][i] = predictData[i];
		}
		predict[1][dataColumn+1] = labor;
	}
	
	public void predictData(double [][] predictData,int num) {
		predictLine = num;
		predictColumn = dataColumn+1;
		predict = new double[num+1][dataColumn+2]; 
		for(int t=1;t<=num;t++)
		{
			int labor = 0;
			double edge = 0;
			for(int i=1;i<=k;i++) {
				double edget = 0;
				for(int j=1;j<=dataColumn;j++) {
					edget += (predictData[t][j]-centerData[i][j])*(predictData[t][j]-centerData[i][j]);
				}
				if(labor == 0 || edget < edge) {
					labor = i;
					edge = edget;
				}
			}
			for(int i=1;i<=dataColumn;i++) {
				predict[t][i] = predictData[t][i];
			}
			predict[t][dataColumn+1] = labor;
		}
	}
	
	public void printPredict() {
		for(int i=1;i<=predictLine;i++) {
			for(int j=1;j<=predictColumn;j++) {
				System.out.print(predict[i][j]+" ");
			}
			System.out.println("");
		}
	}
	
	public void printCenterData() {
		for(int i=1;i<=k;i++) {
			for(int j=1;j<=dataColumn;j++) {
				System.out.print(centerData[i][j]+" ");
			}
			System.out.println("");
		}
	}
}
