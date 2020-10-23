
package a3;


import java.io.File;
import java.util.Scanner;

public class A3 {

	static String regularExpression[];
	static String strings[];

	public static void main(String[] args) {
		

		try {
			File f = new File("input1.txt");
			Scanner k = new Scanner(f);
			while (k.hasNextLine()) {
				int totalRE = Integer.parseInt(k.nextLine().toString());
				regularExpression = new String[totalRE];
				int count = 1;
				while (count <= totalRE) {
					regularExpression[count - 1] = k.nextLine().toString();
					count++;
				}
				count = 1;
				int totalString = Integer.parseInt(k.nextLine().toString());
				strings = new String[totalString];
				while (count <= totalString) {
					strings[count - 1] = k.nextLine().toString();
					count++;
				}

			}

			
			for (int c = 0; c < strings.length; c++) {
				for (int i = 0; i < regularExpression.length; i++) {
					if(strings[c].matches(regularExpression[i])){
						System.out.println("YES, "+(i+1));
						break;
					}
					else if(i==regularExpression.length-1){
						System.out.println("NO, 0");
					}
				}
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}



