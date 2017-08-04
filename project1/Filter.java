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
		System.out.println("<StatisticalFilter Start!>");
		
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
				sort(array, num);
				
				grey = select(type, array, num);
				
				ImageArray[x][y] = grey*0x10101;
			}
		}
		
		for(x=0; x<bi.getWidth(); x++)
			for(y=0; y<bi.getHeight(); y++)
				bi.setRGB(x, y, ImageArray[x][y]);
		
		File S = Preprocess.CreatFile("C:\\My Document\\BUAA\\Lab Project\\StatisticalFilter.jpg");
		ImageIO.write(bi,"jpg", S);
		
		System.out.println("<StatisticalFilter Finish!>");
	}
	
	public void AdaptiveFilter (int Mmax, int Nmax, int Mmin, int Nmin) throws IOException {
		System.out.println("<AdaptiveFilter Start!>");
		//Adaptive median filter

		int [][] ImageArray = new int [bi.getWidth()][bi.getHeight()];
		
		int x, y;
		
		Greyed();
		
		for(y=0; y<bi.getHeight(); y++) {
			for(x=0; x<bi.getWidth(); x++) {
				int grey = AF_levelA(x, y, Mmin, Nmin, Mmax, Nmax);
				ImageArray[x][y] = grey*0x10101;
			}
		}
		
		for(x=0; x<bi.getWidth(); x++)
			for(y=0; y<bi.getHeight(); y++)
				bi.setRGB(x, y, ImageArray[x][y]);
		
		File AF = Preprocess.CreatFile("C:\\My Document\\BUAA\\Lab Project\\AdaptiveFilter.jpg");
		ImageIO.write(bi,"jpg", AF);
		
		System.out.println("<AdaptiveFilter Finish!>");
	}
	
	public int AF_levelA(int x, int y, int Mmin, int Nmin, int Mmax, int Nmax) {
		int num = 0;
		int Zmin, Zmax, Zmed, Zxy;
		int m1 = (Mmin-1)/2;
		int n1 = (Nmin-1)/2;
		int m2 = x-m1;
		int n2 = y-n1;
		int []array = new int[Mmin*Nmin];
		
		if(m2<0)
			m2 = 0;
		if(n2<0)
			n2 = 0;
		
		Zxy = bi.getRGB(x, y);
		
		for(int i = m2; i<=x+m1 && i<bi.getWidth(); i++)
			for(int j = n2; j<=y+n1 && j<bi.getHeight(); j++) {
				array[num++] = (bi.getRGB(i, j) & 0x00ffffff) >> 16;
			}
		
		sort(array, num);
		
		//select the medium one.
		Zmed = select(1, array, num);
		//select the biggest one.
		Zmax = select(2, array, num);
		//select the smallest one.
		Zmin = select(3, array, num);
		
		int A1 = Zmed - Zmin;
		int A2 = Zmed - Zmax;
		
		if(A1>0 && A2<0) {
			return AF_levelB(Zxy, Zmin, Zmax, Zmed);
		}else {
			if(m1<=(Mmax-1)/2 && n1<=(Nmax-1)/2) {
				m1++;
				n1++;
				Mmin = 2*m1+1;
				Nmin = 2*n1+1;
				return AF_levelA(x, y, Mmin, Nmin, Mmax, Nmax);
			}else
				return Zmed;
		}
	}
	
	public int AF_levelB(int Zxy, int Zmin, int Zmax, int Zmed) {
		int B1 = Zxy - Zmin;
		int B2 = Zxy - Zmax;
		
		if(B1>0 && B2<0)
			return Zxy;
		else
			return Zmed;
	}
	
	public int[] sort (int [] array, int num) {
		
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
		
		return array;
	}
	
	public int select (int type, int [] array, int num) {
		if(type == 1) {
			//select the medium one.
			if(num%2==0) {
				return (array[(num/2)-1] + array[num/2]) / 2;
			} else {
				return array[(num-1)/2];
			}
			
		}else if (type == 2) {
			//select the biggest one.
			return array[num-1];
		}else if (type == 3) {
			//select the smallest one.
			return array[0];
		}else if (type == 4) {
			return (array[0]+array[num-1])/2;
		}else {
			return 0;
		}
	}
}
