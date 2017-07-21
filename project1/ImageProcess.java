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
	
	public void InverseImage() throws IOException {
		
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
	
	public void SquareImage(double n) throws IOException {
		
		System.out.println("<Square Start!>");
		
		int x = 0;
		int y = 0;
		
		for(y=0; y<bi.getHeight(); y++) {
			for(x=0; x<bi.getWidth(); x++) {		
				//Image inverse processing
				//bi.setRGB(x, y, 0xffffffff-bi.getRGB(x, y)+0xff000000);
				bi.setRGB(x, y, (int)Math.pow(bi.getRGB(x, y)-0xff000000,n)); //有效位其实为后六位
			}
		}
		
		File square = Preprocess.CreatFile("C:\\My Document\\BUAA\\Lab Project\\Squared.jpg");
		ImageIO.write(bi,"jpg", square);
		
		System.out.println("<Square Finish!>");
	}
	
	public void LogImage() throws IOException {
		
		System.out.println("<Log Start!>");
		
		int x = 0;
		int y = 0;
		
		for(y=0; y<bi.getHeight(); y++) {
			for(x=0; x<bi.getWidth(); x++) {		
				//Image inverse processing
				//bi.setRGB(x, y, 0xffffffff-bi.getRGB(x, y)+0xff000000);
				bi.setRGB(x, y, (int)Math.log(bi.getRGB(x, y)-0xff000000)); //有效位其实为后六位
			}
		}
		
		File log = Preprocess.CreatFile("C:\\My Document\\BUAA\\Lab Project\\Loged.jpg");
		ImageIO.write(bi,"jpg", log);
		
		System.out.println("<Log Finish!>");
	}
	
	public void LinerLightenImage(int n) throws IOException {
		System.out.println("<LinerLighten Start!>");
		
		int x = 0;
		int y = 0;
		int num = 0;
		
		for(int i = 0; i<n; i++) {
			num+=0x10101;
		}
		
		for(y=0; y<bi.getHeight(); y++) {
			for(x=0; x<bi.getWidth(); x++) {		
				//Image inverse processing
				//bi.setRGB(x, y, 0xffffffff-bi.getRGB(x, y)+0xff000000);
				if(bi.getRGB(x, y)+num>0xffffffff)
					bi.setRGB(x, y, (int)0xffffffff); //有效位其实为后六位
				else
					bi.setRGB(x, y, bi.getRGB(x, y)+num);
			}
		}
		
		File lighten = Preprocess.CreatFile("C:\\My Document\\BUAA\\Lab Project\\Lighten.jpg");
		ImageIO.write(bi,"jpg", lighten);
		
		System.out.println("<LinerLighten Finish!>");
	}
	
	public void GreyImage() throws IOException {
		
		System.out.println("<Greyed Start!>");
		
		int blue_num;
		int green_num;
		int red_num;
		int grey;
		
		int x = 0;
		int y = 0;
		
		for(y=0; y<bi.getHeight(); y++) {
			for(x=0; x<bi.getWidth(); x++) {		
				//Image inverse processing
				//bi.setRGB(x, y, 0xffffffff-bi.getRGB(x, y)+0xff000000);
				blue_num = (bi.getRGB(x, y) & 0x000000ff) >> 0;
				green_num = (bi.getRGB(x, y) & 0x0000ff00) >> 8;
				red_num = (bi.getRGB(x, y) & 0x00ff0000) >> 16;
				grey =  (red_num*38 + green_num*75 + blue_num*15) >> 7;
				bi.setRGB(x, y, grey+grey*0x100+grey*0x10000);
			}
		}
		
		File greyImage = Preprocess.CreatFile("C:\\My Document\\BUAA\\Lab Project\\Greyed.jpg");
		ImageIO.write(bi,"jpg", greyImage);
		
		System.out.println("<Grey Finish!>");
	}
	
	public void SquareLightenImage() {
		
	}
}
