/* Data: July/15th/2017
 * Time: 12:49 (UTC+8)
 * Coder: Asp
 * For What? To do some easy Image processing to help Asp understand a part of 
 *           knowledge in Digital Image Processing......
 *           And this part is mainly about Image Format Exchange.
 * */


package Plan1;

import java.io.IOException;
import java.util.Scanner;

public class ImageFormatChange {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Preprocess.Welcome();
		
		String path = "C:\\My Document\\BUAA\\Lab Project\\";
		System.out.println("Please input the file name:");
		Scanner scanner = new Scanner(System.in);
		String FileName =scanner.next();
		System.out.println();
		
		
		ImageProcess IP = new ImageProcess(path+FileName);
		long t1 = System.currentTimeMillis();
		
		//IP.PrintImage();
		//IP.InverseImage();
		IP.SquareImage(0.99999);
		//IP.LogImage();
	
		System.out.println("\ntotal:"+(float)(System.currentTimeMillis()-t1)/1000+"s");
		
		scanner.close();
	}

}
