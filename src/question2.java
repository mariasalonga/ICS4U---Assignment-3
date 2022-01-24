/*Maria Salonga
 * Monday, November 1, 2021
 * This program store the names and marks introduced by a user, in a file, saves them into an array, and prints the onto the console
 * */
import java.io.*;  
import java.util.*;  
import exceptionBasics.*;

public class question2 {
	
	//These variables determine whether or not to exit different loops and saves the user's choice
	static boolean exitLoop = false;
	static String userChoice = "";
	static boolean exit = false;
	
	/*This method prints out the menu that the user can select from*/
	public static void printMenu() {
		
		System.out.println("a. Enter names and marks.\r\n"
				+ "b. Read names and marks\r\n"
				+ "c. Calculate average.\r\n"
				+ "d. Exit the program.");
		
	}
	
	/*This method saves the user's input onto the file*/
	public static void writeIntoFile(String userInput, Scanner input, PrintWriter output) throws emptyStringException {
		do {
			
			System.out.println("Enter a name and a mark.");
			userInput = input.nextLine();
			
			if(userInput.equals("xx")) {
				
				exit = true;
				
			} else if(userInput.equals("")) {
				
				throw new emptyStringException();
				
			} else {
				
				userInput = (userInput.replace(' ', ';'));
				output.println(userInput);
				
			}
		
		} while(exit == false);
		
		output.close();
		
	}
	
	/*This method reads from the file*/
	public static void printFromFile(Scanner sc) throws emptyFileException {
		
			if((sc.hasNextLine()) == false) {
				throw new emptyFileException();
			}
		
			while(sc.hasNextLine()) {
				String thisLine = sc.nextLine();
				thisLine = (thisLine.replace(';',' '));
				System.out.println(thisLine);
			}
			
			sc.close();
		
	}
	
	/*This method calculates the average*/
	public static void calculateAverage(Scanner sc) throws emptyFileException {
		
		int sum = 0;
		int students = 0;
		
		if((sc.hasNextLine()) == false) {
			
			throw new emptyFileException();
			
		}
	
		while(sc.hasNextLine()) {
			
			String[] tokens = ((sc.nextLine()).split(";"));
			sum = (Integer.parseInt(tokens[1])) + sum;
			students++;
			
		}
		
		System.out.println("The average of this class is: " + (sum/students));
		
		sc.close();
		
	}
	
	/*This is the start of the main method*/
	public static void main(String[] args) throws IOException {
		
		/*This is where all the variables are initialized*/	
		File grades = new File("grades.txt");
		Scanner input = new Scanner(System.in);
		String userInput = "";
		
		/*This is the beginning of the try*/
		try {
			
			/*This while loop keeps running until the user picks d, which exits the program*/
			while(exitLoop == false) {
		
				printMenu();
				userChoice = input.nextLine();
				
				/*This switch checks what the user's choice is*/
				switch(userChoice) {
				
				/*This code will execute if the user picks a, it ill write their input onto the file*/
				case "a":
					
					PrintWriter output = new PrintWriter(grades);
					writeIntoFile(userInput, input, output);
					exit = false;
					
					break;
				
				/*This code will execute if the user picks b, it will print out the contents of the file onto the console*/
				case "b":
					
					Scanner sc = new Scanner(grades);
					printFromFile(sc);
					
					break;
				
				/*This code calculates the average*/
				case "c":
					
					Scanner scanner = new Scanner(grades);
					calculateAverage(scanner);
					
					break;
				
				/*This code exits the program*/
				case "d":
					System.exit(0);
					break;
				
				/*This code will execute if the user does not input a valid entry*/
				default:
					System.out.println("You have not entered a valid input. Please try again.");
					
				}
		
		
			}
		/*This is where all the exceptions are caught*/
		} catch (emptyStringException e){
	
			System.out.println("You have not entered anything. Please try again.");
			
		} catch (emptyFileException e) {
			
			System.out.println("Your file has no contents.");
		
		} catch (Exception e) {
			
			System.out.println("");
			
		}
		input.close();
	}

}
