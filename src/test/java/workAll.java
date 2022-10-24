import java.io.File;
import java.io.IOException;

import org.junit.Test;

import com.yx.core.Data;
import com.yx.file.FileReaderFromCsv;
import com.yx.file.FileWriterToTxt;
import com.yx.file.fileTest;
import com.yx.methods.Covariance;
import com.yx.methods.KKMeans;
import com.yx.methods.PCA;
import com.yx.methods.meanNormalization;

public class workAll {
	
	@Test
	public void mainTest() throws IOException {
		String f = fileTest.class.getClassLoader().getResource("SIMULATED_00004.csv").getPath();
		Data d1 = FileReaderFromCsv.loadFile(new File(f));
		Data d2 = meanNormalization.dataProcess(d1);
		Data d3 = Covariance.covarianceProcess(d2);
		Data d4 = PCA.runPCAAfterCovar(d3, d1, 3);
		Data d5 =new Data();
		d5.Line = d4.Line;
		d5.Column = d4.Column+1;
		d5.calData =new double[d5.Line+1][d5.Column+1];
		for(int i=1;i<=d4.Line;i++) {
			for(int j=1;j<=d4.Column;j++) {
				d5.calData[i][j] = d4.calData[i][j];
			}
			d5.calData[i][d5.Column] = 0.0;
		}
		for(int i=1;i<=10;i++) {
			KKMeans ke = new KKMeans(d5,i,true);
			ke.runKKMeans(100);
			System.out.println(ke.minJ);
		}
		
		//FileWriterToTxt.writeFile(ke, new File("testdata3.txt"));
	}
}
