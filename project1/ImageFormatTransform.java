/* Data: July/15th/2017
 * Time: 12:49 (UTC+8)
 * Coder: Asp
 * For What? To do some easy Image processing to help Asp understand a part of 
 *           knowledge in Digital Image Processing......
 *           And this part is mainly about Image Format Exchange.
 * The Last Update Data: July/22nd/2017, 15:28(UTC+8)
 * */


package project1;

import java.io.IOException;
import java.util.Scanner;

public class ImageFormatTransform {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Preprocess.Welcome();
		
		String path = "C:\\My Document\\BUAA\\Lab Project\\";
		System.out.println("Please input the file name:");
		Scanner scanner = new Scanner(System.in);
		String FileName =scanner.next();
		System.out.println();
		
		
		//ImageProcess IP = new ImageProcess(path+FileName);
		Filter FT = new Filter(path+FileName, 3, 3);
		long t1 = System.currentTimeMillis();
		
		//IP.PrintImage();
		//IP.Inverse();
		//IP.PowerTransform(0.99999);
		//IP.LogTransform();
		//IP.LinerGreyTransform(-40); //if n>0, ligthen; if n<0, darken.
		//IP.GreyImage();
		//IP.PowerGreyTransform(0.2); //if n>1, darken; if 0<n<1, ligthen.
		
		//IP.HistogramEqualization();
		//FT.ArithmeticAverage();
		FT.StatisticalFilter(4); //if type==1, is medium statistic; if type==2, is max-statistic; if type==3, is min-statistic; if type==4, is medial-point statistic.
		
		System.out.println("\ntotal:"+(float)(System.currentTimeMillis()-t1)/1000+"s");
		
		scanner.close();
	}

}
