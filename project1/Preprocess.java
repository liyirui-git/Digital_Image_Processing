package Plan1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Preprocess {
	public static void Welcome() {
	/*To print the welcome word for user and identified the user.*/
		System.out.println("<<<<<<<<<<<< 	I'm waking up...	>>>>>>>>>>>>");
		for(int i = 0; i<3; i++)
			System.out.println("------------ 	 			------------");
		System.out.println("------------ 	Edition: 0.0.1		------------");
		for(int i = 0; i<3; i++)
			System.out.println("------------ 	 			------------");
		System.out.println("Hi! Mr.Asp!");
		
		/*System.out.println("Who are you? I will flow the order from Mr.Asp .");
		System.out.print("Please input your password (number only):");
		Scanner scanner = new Scanner(System.in);
		int TryTimesMax = 5;
		while(true) {
			if(scanner.nextLong()==970812) {
				System.out.println("Hi! Mr.Asp!\n");
				break;
			}else {
				TryTimesMax--;
				if(TryTimesMax==0) {
					System.out.println("I'm sorry no more trys.");
					System.exit(0);
				}
				System.out.print("<Wrong password> Please try again:");
			}
		}
		scanner.close();*/
	}
	
	public static void writeFile(File file,String fileName,String s){	
		try {
			PrintWriter pw = new PrintWriter(new FileWriter(file, true));
			pw.print(s);
			pw.close();
		} catch (IOException e) {
			System.out.println("<Write "+fileName+" failed>");
		}
	}
	
	public static File CreatFile(String s) throws IOException {
		File resultFile = new File(s);
		if(resultFile.exists()) {
			resultFile.delete();

		resultFile.createNewFile();

		}
		return resultFile;
	}
}
