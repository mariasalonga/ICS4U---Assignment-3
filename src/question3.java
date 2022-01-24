/*Maria Salonga 
This code analyzes and saves blue jay statistics.
 * Monday, November 1, 2021
* This program */
import java.io.*; 
import java.util.*;  
//import exceptionBasics.*;
public class question3 {
	//These variables are used in the highest number method and are reset after the method has been used
	static double highest = 0;
	static String highestNumberPlayer = "";
	
	//This method prints out the contents of the array
	public static void printOutArray(String[][] twoDArray, Scanner fileSc) throws IOException {
		
		for(int x = 0; x < 16; x++) {
			
			String[] tokens = (fileSc.nextLine().split(","));
			
			for(int y = 0; y < 18; y++) {
			
				twoDArray[x][y] = tokens[y];
				System.out.print(twoDArray[x][y] + '\t');
			
			}
			
			System.out.println("");
			
		}
			
	}
		
	//This method finds the highest number in that column
	public static void highestNumber(String[][] array, int y) {
		
		for(int x = 1; x < 16; x++) {
			
			if((Double.parseDouble(array[x][y])) > highest) {
				
				highest = (Double.parseDouble(array[x][y]));
				highestNumberPlayer = array[x][0];
				
			}
			
		}
	
	}
	
	//This code resets the values of the static variable
	public static void reset() {
		
		highest = 0;
		highestNumberPlayer = "";
		
	}
	
	//This method checks which players have played more than 20 games
	public static void check20Games(String[][]array) {
		int num = 0;
		for(int x = 1; x < 16; x++) {
			
			if((Integer.parseInt(array[x][2])) > 20) {
				
				num++;
				
			}
			
		}
		System.out.println(num + " players have played more than 20 games.");
		
	}
	
	//This is the start of the main method
	public static void main(String[] args) throws IOException {
		
		//This is the variable I created for the file
		File blueJays = new File("BlueJays.csv");
		
		//This scanner reads through the file
		Scanner fileSc = new Scanner(blueJays);

		//This 2D array is empty, but will be filled by the blue jay statistics
		String[][] twoDBlueJays = new String[16][18];
		
		//Here I call the method to print out the array
		printOutArray(twoDBlueJays, fileSc);
		
		//Here I calculate the highest batting average
		highestNumber(twoDBlueJays, 14);
		System.out.println("The player with the highest batting average is: " + highestNumberPlayer + " with a batting average of " + highest + ".");
		reset();
		
		//Here I calculate the highest walks
		highestNumber(twoDBlueJays, 10);
		System.out.println("The player with the highest amount of walks is: " + highestNumberPlayer + " with a record number of " + highest + ".");
		reset();
		
		//Here I calculate the highest stolen basis
		highestNumber(twoDBlueJays, 12);
		System.out.println("The player with the highest amount of stolen basis is: " + highestNumberPlayer + " with a record number of " + highest + ".");
		
		//Here I check who has played more than 20 games
		check20Games(twoDBlueJays);
	
		//Here I close the scanner
		fileSc.close();
	}

}
