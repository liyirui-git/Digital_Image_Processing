package Plan1;

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
		
		File inverse = Preprocess.CreatFile("C:\\My Document\\BUAA\\Lab Project\\Inversed.jpg");
		ImageIO.write(bi,"png", inverse);
		
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
		
		File inverse = Preprocess.CreatFile("C:\\My Document\\BUAA\\Lab Project\\Inversed.jpg");
		ImageIO.write(bi,"jpg", inverse);
		
		System.out.println("<Log Finish!>");
	}
}
