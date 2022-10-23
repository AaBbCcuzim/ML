package com.yx.core;

public class Data {
	public int Line; 
	public int Column;
	public double [][] calData;
	
	public Data() {
		this.Line = 0;
		this.Column = 0;
	}
	
	public void print(){
		for(int i=1;i<=Line;i++){
			for(int j=1;j<=Column;j++) {
				System.out.print(calData[i][j]+" ");
			}
			System.out.println("");
		}
	}
}
