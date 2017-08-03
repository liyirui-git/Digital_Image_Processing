package project1;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class Filter extends ImageProcess{
	/*	File image;
		String path; 
		BufferedImage bi;
		int type;
	*/
	public Filter(String path) throws IOException {
		super(path);
	}
	
	public void ArithmeticAverage (int m, int n) throws IOException {
		System.out.println("<ArithmeticAverage Start!>");
		
		Greyed();
		
		int x = 0;
		int y = 0;
		int m1 = (m-1)/2;
		int n1 = (n-1)/2;
		int m2,n2;
		
		for(y=0; y<bi.getHeight(); y++) {
			for(x=0; x<bi.getWidth(); x++) {
				int total = 0;
				int num = 0;
				
				m2 = x-m1;
				n2 = y-n1;
				if(m2<0)
					m2 = 0;
				if(n2<0)
					n2 = 0;
				
				for(int i = m2; i<=x+m1 && i<bi.getWidth(); i++)
					for(int j = n2; j<=y+n1 && j<bi.getHeight(); j++) {
						total = total + ((bi.getRGB(i, j) & 0x00ffffff) >> 16);
						num++;
					}
				
				int setter = total/num;
				
				bi.setRGB(x, y, setter*0x10101); 
			}
		}
		
		File AA = Preprocess.CreatFile("C:\\My Document\\BUAA\\Lab Project\\ArithmeticAverage.jpg");
		ImageIO.write(bi,"jpg", AA);
		
		System.out.println("<ArithmeticAverage Finish!>");
	}
}
