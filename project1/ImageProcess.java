package project1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageProcess {
	File image;
	String path; 
	BufferedImage bi;
	int type;
	
	public ImageProcess(String path) throws IOException {
		this.path = path;
		this.image = new File(path);
		
		if(image.exists()) {
			System.out.println("<Image Exists!>");
		} else {
			System.out.println("<Error> Can't find image in :"+path);
			System.exit(0);
		}
		
		this.bi = ImageIO.read(image);
		
		this.type = bi.getType();
		
		//System.out.println("Construct!");
	}
	
	public void PrintImage() throws IOException {
		
		System.out.println("<Printing Start!>");
		
		int x = 0;
		int y = 0;
		int z = 1;
		
		if ( type ==BufferedImage.TYPE_INT_ARGB || type == BufferedImage.TYPE_INT_RGB ){  
		    /*result = (int [])bi.getRaster().getDataElements(x, y, width, height, pixels);
		    for(int i=0; i<result.length; i++) {
		    	System.out.println(i+":"+result[i]);
		    }*/
			System.out.println("<1-1>");
		}else {
			
			String filename = "image1.txt";
			File resultFile = Preprocess.CreatFile("E:\\My Document\\"+filename);

			for(y=0; y<bi.getHeight(); y++) {
				
				for(x=0; x<bi.getWidth(); x++) {
					String s = Integer.toHexString(bi.getRGB(x,y))+" ";					
					Preprocess.writeFile(resultFile, filename, s);
				}
				
				Preprocess.writeFile(resultFile, filename, "\n");
			
				if((float)y/bi.getHeight()>(float)z/10) {
					System.out.println("Finish:"+z+"0%...");
					z++;
				}
				
			}
			System.out.println("<Printing Finish!>");
			
		}
	}
	
	public void Inverse() throws IOException {
		
		System.out.println("<Inverse Start!>");
		
		int x = 0;
		int y = 0;
		
		for(y=0; y<bi.getHeight(); y++) {
			for(x=0; x<bi.getWidth(); x++) {		
				//Image inverse processing
				//bi.setRGB(x, y, 0xffffffff-bi.getRGB(x, y)+0xff000000);
				bi.setRGB(x, y, 0xffffffff-bi.getRGB(x, y)); //有效位其实为后六位
			}
		}
		
		File inverse = Preprocess.CreatFile("C:\\My Document\\BUAA\\Lab Project\\Inversed.jpg");
		ImageIO.write(bi,"jpg", inverse);
		
		System.out.println("<Inverse Finish!>");
	}
	
	public void PowerTransform(double n) throws IOException {
		
		System.out.println("<Power Start!>");
		
		int x = 0;
		int y = 0;
		
		for(y=0; y<bi.getHeight(); y++) {
			for(x=0; x<bi.getWidth(); x++) {		
				bi.setRGB(x, y, (int)Math.pow(bi.getRGB(x, y)-0xff000000,n)); 
			}
		}
		
		File Power = Preprocess.CreatFile("C:\\My Document\\BUAA\\Lab Project\\Powerd.jpg");
		ImageIO.write(bi,"jpg", Power);
		
		System.out.println("<Power Finish!>");
	}
	
	public void LogTransform() throws IOException {
		
		System.out.println("<Log Start!>");
		
		int x = 0;
		int y = 0;
		
		for(y=0; y<bi.getHeight(); y++) {
			for(x=0; x<bi.getWidth(); x++) {		
				bi.setRGB(x, y, (int)Math.log(bi.getRGB(x, y)-0xff000000)); 
			}
		}
		
		File log = Preprocess.CreatFile("C:\\My Document\\BUAA\\Lab Project\\Loged.jpg");
		ImageIO.write(bi,"jpg", log);
		
		System.out.println("<Log Finish!>");
	}
	
	public void Greyed() throws IOException {
		
		System.out.println("<Greyed Start!>");
		
		int blue_num;
		int green_num;
		int red_num;
		int grey;
		
		int x = 0;
		int y = 0;
		
		for(y=0; y<bi.getHeight(); y++) {
			for(x=0; x<bi.getWidth(); x++) {		
				blue_num = (bi.getRGB(x, y) & 0x000000ff) >> 0;
				green_num = (bi.getRGB(x, y) & 0x0000ff00) >> 8;
				red_num = (bi.getRGB(x, y) & 0x00ff0000) >> 16;
				grey =  (red_num*38 + green_num*75 + blue_num*15) >> 7;
				bi.setRGB(x, y, grey*0x10101);
			}
		}
		
		File greyImage = Preprocess.CreatFile("C:\\My Document\\BUAA\\Lab Project\\Greyed.jpg");
		ImageIO.write(bi,"jpg", greyImage);
		
		System.out.println("<Grey Finish!>");
	}
	
	public void LinerGreyTransform(int n) throws IOException {
		System.out.println("<Liner-Grey Start!>");
		
		int x = 0;
		int y = 0;
		int num = 0;
		
		Greyed();
		
		if(n>=0) {
			for(int i = 0; i<n; i++) {
				num+=0x10101;       //this make sure this is a Grey Transform
			}
		}else {
			for(int i = 0; i<-n; i++) {
				num-=0x10101;
			}
		}
		
		for(y=0; y<bi.getHeight(); y++) {
			for(x=0; x<bi.getWidth(); x++) {		
				if(bi.getRGB(x, y)+num>0xffffffff)
					bi.setRGB(x, y, 0xffffffff); //有效位其实为后六位
				else if(bi.getRGB(x, y)+num<0xff000000)
					bi.setRGB(x, y, 0xff000000);
				else
					bi.setRGB(x, y, bi.getRGB(x, y)+num);
			}
		}
		
		File liner = Preprocess.CreatFile("C:\\My Document\\BUAA\\Lab Project\\LinerGrey.jpg");
		ImageIO.write(bi,"jpg", liner);
		
		System.out.println("<Liner-Grey Finish!>");
	}
	
	public void PowerGreyTransform (double n) throws IOException {
		/*The differences between PowerGreyImage & PowerTranform is that, first one is between 0 and 1.*/
		System.out.println("<Power-Grey Start!>");
		
		if(n<0) {
			System.out.println("<Power-Grey error!>: n must greater than 0.");
			System.exit(0);
		}
		
		int x = 0;
		int y = 0;
		int Grey = 0;
		
		Greyed();
		
		for(y=0; y<bi.getHeight(); y++) {
			for(x=0; x<bi.getWidth(); x++) {
				Grey = (bi.getRGB(x, y) & 0x00ffffff) >> 16;
			    Grey = (int)(Math.pow((double)Grey/0xff,n)*0xff);
			    bi.setRGB(x, y, Grey*0x10101);
			}
		}
		
		File PowerGrey = Preprocess.CreatFile("C:\\My Document\\BUAA\\Lab Project\\PowerGrey.jpg");
		ImageIO.write(bi, "jpg", PowerGrey);
		
		System.out.println("<Power-Grey Finished!>");
	}
	
	public void HistogramEqualization () throws IOException {
		System.out.println("<Histogram Equalization Start!>");
		
		Greyed();
		
		int x = 0;
		int y = 0;
		int Grey = 0;
		int total = bi.getHeight()*bi.getWidth();
		int [] mapping = new int [256];
		int [] num = new int [256];
		
		for(int i=0; i<num.length; i++) {
			mapping[i] = 0;
			num[i] = 0;
		}
		
		for(y=0; y<bi.getHeight(); y++) {
			for(x=0; x<bi.getWidth(); x++) {
				Grey = (bi.getRGB(x, y) & 0x00ffffff) >> 16;
				num[Grey]++;
			}
		}
		
		for(int i=0; i<mapping.length; i++) {
			double d = 0;
			for(int j=0; j<=i; j++) {
				d = d+(double)num[j]/total;
			}
			mapping[i] = (int) (d*0xff);
		}
		
		for(y=0; y<bi.getHeight(); y++) {
			for(x=0; x<bi.getWidth(); x++) {
				Grey = (bi.getRGB(x, y) & 0x00ffffff) >> 16;
				Grey = mapping[Grey];
				bi.setRGB(x, y, Grey*0x10101);
			}
		}
		
		File HE = Preprocess.CreatFile("C:\\My Document\\BUAA\\Lab Project\\HistogramEqualization.jpg");
		ImageIO.write(bi, "jpg", HE);
		
		System.out.println("<Histogram Equalization Finished!>");
	}
	
}
