/*Maria Salonga
 * Monday, November 1, 2021
 * This program store the names and marks introduced by a user, in a file, saves them into an array, and prints the onto the console
 * */
import java.io.*;  
import java.util.*;  
import exceptionBasics.*;
public class question1 {
	
	//These are static variables
	static boolean exitLoop = false; 
	static boolean exit = false; 
	static String userChoice = "";
	
	//This method prints out the menu
	public static void printMenu(){
		System.out.println("a. Enter names and marks\r\n"
				+ "b. Read names and marks\r\n"
				+ "c. Read to an array names and marks.\r\n"
				+ "d. Exit the program.");
	}
	
	//This method prints out the users input onto a file
	public static void printOntoFile(PrintWriter output, String userMessage, Scanner input) throws emptyStringException {
		
		do {
				
				System.out.println("Enter a name and a mark.");
				userMessage = input.nextLine();
				
				if(userMessage.equals("xx")) {
					
					exit = true;
					
				} else if(userMessage.equals("")) {
					
					throw new emptyStringException();
					
				} else {
					
					output.println(userMessage);
					
				}
			
		} while(exit == false);

		output.close();
		
	}
	
	//This method prints out whatever is on the file onto the console
	public static void printFromFile(Scanner sc) throws emptyFileException{
		
		if((sc.hasNextLine()) == false) {
			throw new emptyFileException();
		}
		
		while(sc.hasNextLine()) {
			
			System.out.println(sc.nextLine());
			
		}
		sc.close();
		
	}
	
	//This method counts the lines on the file
	public static int countLines(Scanner sc, File marks) {
		int lineCount = 0;
		while(sc.hasNextLine()) {
			sc.nextLine();
			lineCount++;
		}
		sc.close();
		return lineCount;
	}
	
	//This method saves the contents of the file onto an array and prints it out
	public static void saveFileToArray(Scanner sc, int lineCount) {
		
		String[] arrayOfMarks = new String[lineCount];
		
		for(int x = 0; x < lineCount; x++) {
			
			arrayOfMarks[x] = sc.nextLine();
			System.out.println(arrayOfMarks[x]);
			
		}
		
		exit = false;
		sc.close();
	}
	
	//This is the start of the main method
	public static void main(String[] args) throws IOException {
		
		//Here, I opened the scanner and declared my variables
		File marks = new File("marks.txt");
		String userMessage = "";
		Scanner input = new Scanner(System.in);
		Scanner sc = new Scanner(marks);
		
		try {

		//This while loop will run until the user inputs D
		while(exitLoop == false) {
			
			printMenu();
			
			userChoice = input.nextLine();
			switch(userChoice) {
			
			/*the user can enter names and marks. Every time after the person has entered the mark,  the
			name and the mark will be written on the file called, “marks.txt”. You’ll keep asking for names and marks and writing
			them to the file until “xx” is entered as a name. Then close the file.*/
			case "a":
				
				PrintWriter output = new PrintWriter(marks);
				printOntoFile(output, userMessage, input);
				exit = false;
				
				break;
				
			/*If they select b, the names and marks from the file “marks.txt” will be printed on the console. Read a name,
			then a mark and then continue until you read in the number of names you wrote in Part A. You’ll
			need a loop that counts up to the number of records you wrote. As each name and mark are read in,
			print them in the console.*/
			case "b":
				
				sc = new Scanner(marks);
				printFromFile(sc);
			
				break;
			
			/*If they input c, the names and marks  from the file “marks.txt” and store them in an array. Instead of reading
			into variables and printing them after each line is read, an ARRAY is created for ‘name’ and an ARRAY
			for 'marks'.All the data is read from the file into the arrays and then printed in the console from
			the arrays after all the data has been read.*/
			case "c":
				sc = new Scanner(marks);
				if((sc.hasNextLine()) == false) {
					throw new emptyFileException();
				}
				sc.close();
				
				sc = new Scanner(marks);
				int lineCount = countLines(sc, marks);
				
				sc = new Scanner(marks);
				saveFileToArray(sc, lineCount);
				
				exit = false;
				break;
			
			/*If they input d, the program will exit*/
			case "d":
				
				System.exit(0);
				break;

			/*If they input another letter, it will print out invalid input*/	
			default:
				System.out.println("Invalid input. Please try again.");
			
			}	

		}
		
		//This is where all my exceptions are caught
		} catch(emptyStringException e){
			
			System.out.println("You have not entered anything. Please try again");
			
		} catch (emptyFileException e){
			
			System.out.println("Your file has no contents.");
			
		} catch (Exception e){
			
			System.out.println();
		}
		
		input.close();
		
	}

}