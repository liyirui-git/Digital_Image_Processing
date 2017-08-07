package project1;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageSegmentation extends ImageFilter{
	/*	File image;
		String path; 
		BufferedImage bi;
		int type;
		int m;
		int n;
	*/
	int threshold;
	
	public ImageSegmentation(String path, int threshold) throws IOException {
		super(path, 3, 3);           //the model is 3x3, so m=3, n=3.
		this.threshold = threshold;
	}
	
	public void Prewitt() throws IOException {
		System.out.println("<PrewittSegmentation Start!>");
		int x, y;
		
		Greyed();
		AdaptiveFilter(7, 7, 3, 3);
		
		int m1 = (m-1)/2;
		int n1 = (n-1)/2;
		int m2,n2;
		int [][] ImageArray = new int [bi.getWidth()][bi.getHeight()];
		
		for(y=0; y<bi.getHeight(); y++) {
			for(x=0; x<bi.getWidth(); x++) {
				int grey = 0;
				int Gx;
				int Gy;
				int [] Square = new int [m*n];
				int k = 0;
				
				m2 = x-m1;
				n2 = y-n1;
				if(m2<0) {
					//m2 = 0;
					continue;
				}
				if(n2<0) {
					//n2 = 0;
					continue;
				}
				
				for(int i = m2; i<=x+m1 && i<bi.getWidth(); i++)
					for(int j = n2; j<=y+n1 && j<bi.getHeight(); j++) {
						Square[k++] = (bi.getRGB(i, j) & 0x00ffffff)>>16;
					}
				
				Gx = Square[6] + Square[7] + Square[8] - (Square[0] + Square[1] + Square[2]);
				Gy = Square[2] + Square[5] + Square[8] - (Square[0] + Square[3] + Square[6]);
				
				if(Math.abs(Gx)>=threshold || Math.abs(Gy)>=threshold)
					grey = 0xff;
				else
					grey = 0x00;
				
				ImageArray[x][y] = grey*0x10101;
			}
			
			
		}
		
		for(x=0; x<bi.getWidth(); x++)
			for(y=0; y<bi.getHeight(); y++)
				bi.setRGB(x, y, ImageArray[x][y]);
		
		File PS = Preprocess.CreatFile("C:\\My Document\\BUAA\\Lab Project\\PrewittSegmentation.jpg");
		ImageIO.write(bi,"jpg", PS);
		
		System.out.println("<PrewittSegmentation Finish!>");
	}
	
	public void Sobel () throws IOException {
		System.out.println("<SobelSegmentation Start!>");
		int x, y;
		
		Greyed();
		AdaptiveFilter(7, 7, 3, 3);
		
		int m1 = (m-1)/2;
		int n1 = (n-1)/2;
		int m2,n2;
		int [][] ImageArray = new int [bi.getWidth()][bi.getHeight()];
		
		for(y=0; y<bi.getHeight(); y++) {
			for(x=0; x<bi.getWidth(); x++) {
				int grey = 0;
				int Gx;
				int Gy;
				int [] Square = new int [m*n];
				int k = 0;
				
				m2 = x-m1;
				n2 = y-n1;
				if(m2<0) {
					//m2 = 0;
					continue;
				}
				if(n2<0) {
					//n2 = 0;
					continue;
				}
				
				for(int i = m2; i<=x+m1 && i<bi.getWidth(); i++)
					for(int j = n2; j<=y+n1 && j<bi.getHeight(); j++) {
						Square[k++] = (bi.getRGB(i, j) & 0x00ffffff)>>16;
					}
				
				Gx = Square[6] + Square[7] + Square[7] + Square[8] - (Square[0] + Square[1] + Square[1] + Square[2]);
				Gy = Square[2] + Square[5] + Square[5] + Square[8] - (Square[0] + Square[3] + Square[3] + Square[6]);
				
				if(Math.abs(Gx)>=threshold || Math.abs(Gy)>=threshold)
					grey = 0xff;
				else
					grey = 0x00;
				
				ImageArray[x][y] = grey*0x10101;
			}
			
			
		}
		
		for(x=0; x<bi.getWidth(); x++)
			for(y=0; y<bi.getHeight(); y++)
				bi.setRGB(x, y, ImageArray[x][y]);
		
		File SS = Preprocess.CreatFile("C:\\My Document\\BUAA\\Lab Project\\SobelSegmentation.jpg");
		ImageIO.write(bi,"jpg", SS);
		
		System.out.println("<SobelSegmentation Finish!>");
	}
}
