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
	int m;
	int n;
	
	public Filter(String path, int m, int n) throws IOException {
		super(path);
		
		if (m<0 || n<0) {
			System.out.println("<error>: m & n must great than zero!");
			System.exit(0);
		}
		
		this.m = m;
		this.n = n;
	}
	
	public void ArithmeticAverage () throws IOException {
		System.out.println("<ArithmeticAverage Start!>");
		
		Greyed();
		
		int x = 0;
		int y = 0;
		int m1 = (m-1)/2;
		int n1 = (n-1)/2;
		int m2,n2;
		int [][] ImageArray = new int [bi.getWidth()][bi.getHeight()];
		
		for(y=0; y<bi.getHeight(); y++) {
			for(x=0; x<bi.getWidth(); x++) {
				int total = 0;
				int num = 0;
				int grey = 0;
				
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
				
				grey = total/num;
				
				ImageArray[x][y] = grey*0x10101;
			}
		}
		
		for(x=0; x<bi.getWidth(); x++)
			for(y=0; y<bi.getHeight(); y++)
				bi.setRGB(x, y, ImageArray[x][y]);
		
		File AA = Preprocess.CreatFile("C:\\My Document\\BUAA\\Lab Project\\ArithmeticAverage.jpg");
		ImageIO.write(bi,"jpg", AA);
		
		System.out.println("<ArithmeticAverage Finish!>");
	}
	
	public void StatisticalFilter (int type) throws IOException {
		System.out.println("<Statistical Start!>");
		
		if(type>4 || type<1) {
			System.out.println("<Error>: illegal value of \"type\".");
			System.exit(0);
		}
		
		Greyed();
		
		int x = 0;
		int y = 0;
		int m1 = (m-1)/2;
		int n1 = (n-1)/2;
		int m2,n2;
		int []array = new int [m*n];
		int [][] ImageArray = new int [bi.getWidth()][bi.getHeight()];
		
		for(y=0; y<bi.getHeight(); y++) {
			for(x=0; x<bi.getWidth(); x++) {
				int num = 0;
				int grey = 0;
				
				m2 = x-m1;
				n2 = y-n1;
				if(m2<0)
					m2 = 0;
				if(n2<0)
					n2 = 0;
				
				for(int i = m2; i<=x+m1 && i<bi.getWidth(); i++)
					for(int j = n2; j<=y+n1 && j<bi.getHeight(); j++) {
						array[num++] = (bi.getRGB(i, j) & 0x00ffffff) >> 16;
					}
				
				//∂‘array≈≈–Ú
				boolean f = true;
				while(f) {
					f = false;
					for (int i=0; i<num-1; i++) {
						if(array[i]>array[i+1]) {
							int t = array[i];
							array[i] = array[i+1];
							array[i+1] = t;
							f = true;
						}
					}
				}
				
				if(type == 1) {
					//select the medium one.
					if(num%2==0) {
						grey = (array[(num/2)-1] + array[num/2]) / 2;
					} else {
						grey = array[(num-1)/2];
					}
					
				}else if (type == 2) {
					//select the biggest one.
					grey = array[num-1];
				}else if (type == 3) {
					//select the smallest one.
					grey = array[0];
				}else if (type == 4) {
					grey = (array[0]+array[num-1])/2;
				}
				
				ImageArray[x][y] = grey*0x10101;
			}
		}
		
		for(x=0; x<bi.getWidth(); x++)
			for(y=0; y<bi.getHeight(); y++)
				bi.setRGB(x, y, ImageArray[x][y]);
		
		File S = Preprocess.CreatFile("C:\\My Document\\BUAA\\Lab Project\\Statistical.jpg");
		ImageIO.write(bi,"jpg", S);
		
		System.out.println("<Statistical Finish!>");
	}
}
