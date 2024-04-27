import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class CarDealership {

    static Scanner input = new Scanner(System.in);
    static Random random = new Random();
    static BufferedWriter writeFile;
    static BufferedReader readFile;

    public static void main(String[] args) throws IOException {
    	//Get amount of cars to be bought and ask for test drive and do appriopriate action 
    	
    	//declare car inventory
        String[]cars = new String[50];
     
        //call appropriate methods
        readFile(cars);
        askInputType(cars);
        outputCars(cars);
        //ask for input of cars and update onto document 
        System.out.println("How many cars would you like?");
        int carAmount = input.nextInt();

        for (int index = 0; index < carAmount; index++) {
            determineSearchResult(cars);
        }//for

        
        //write to file 
        writeToFile(cars);
        
        //ask for test drive 
        System.out.println("Would You like a test drive with one of your cars?");
        String answer = input.next();
        
        if(answer.equalsIgnoreCase("Yes")) {
        	testDrive();
        }//if
        else {
        	System.out.println("Thank you for using");
        	System.exit(0);
        }//else
        
    }//main

    public static String[] readFile(String[] cars) throws IOException {
        // Read car names from a file

        readFile = new BufferedReader(new FileReader("/Users/AgamjotNijjar/eclipse-workspace/Grade 12 Porgramming/src/PresentationOrder.txt"));

        for (int index = 0; index < cars.length; index++) {
            cars[index] = readFile.readLine();
        }//for

        readFile.close();
        return cars;
    }//readFile

    public static void askInputType(String[] cars) {
    	//ask whether to shuffle or sort and take action
    	
        System.out.println("Would you like to shuffle or sort the cars?");
        String answer = input.next();

        if (answer.equalsIgnoreCase("Shuffle")) {
            Shuffling.shuffleString(cars, cars.length);
        }//if 
        else if (answer.equalsIgnoreCase("Sort")) {
            CommonAlgorsLibrary.insertSortStrings(cars);
           
        }//else if 
        else {
            System.out.println("Invalid response");
            System.exit(0);
        }//else
    }//askInputType

    public static void outputCars(String[] cars) {
    	//output the car inventory
    	
        for (int i = 0; i < cars.length; i++) {
            System.out.println(cars[i]);
        }//for
    }//outputCars

    public static void determineSearchResult(String[] cars) {
    	//determines whether the searched car is found or not
    	
        System.out.println("Type the name of the car that you would like to search:");
        String searchName = input.next();

        int place = binarySearch(cars, searchName, 0, cars.length - 1);

        if (place != -1) {
            carBuy(cars, place);
        } //if
        else {
            System.out.println("Car not found.");
            System.exit(0);
        }//else
    }//determineSearchResult

    public static int binarySearch(String[] cars, String carName, int left, int right) {
    	//search for the car in the list
    	
        if (left <= right) {
            int mid = left + (right - left) / 2;
            int result = cars[mid].compareTo(carName);

            if (result == 0) {
                return mid; // Car found at mid index
            } //if
            else if (result < 0) {
                return binarySearch(cars, carName, mid + 1, right); // Search in the right half
            }//else if 
            else {
                return binarySearch(cars, carName, left, mid - 1); // Search in the left half
            }//else
        }//if

        return -1; // Car not found
    }//binarySearch

    public static void carBuy(String[] cars, int place) {
    	//replace the car name with message based on whatever the previous user/user have bought
    	
        cars[place] = "********CAR SOLD***************";
        outputCars(cars);
    }//carBuy

    public static void writeToFile(String[] cars) throws IOException {
    	//write to inventory to file 
    	
        BufferedWriter writeFile = new BufferedWriter(new FileWriter("/Users/AgamjotNijjar/eclipse-workspace/Grade 12 Porgramming/src/PresentationOrder.txt"));

        for (int index = 0; index < cars.length; index++) {
            writeFile.write(cars[index]);
            writeFile.newLine();
        }//for

        writeFile.close();
    }//writeToFile
    
    public static void testDrive() {
    	//Simulate the test drive using the car object 
    	
    	TestDriveObject car1 = new TestDriveObject();
		
		boolean steerInput=true;
		String onOffEngine = "Off";
		
		
		do {
			System.out.println("What Direction Would you like to turn (True for right and false for left)");
			steerInput=input.nextBoolean();
			
			car1.steering(steerInput);
			
			System.out.println("How Much would you like to accelerate or deaccelerate (Type Number here)");
			int speedChange = input.nextInt();
			System.out.println("Speed is " + car1.determineSpeed(speedChange));
			
			System.out.println("Would You Like to Apply Brake (true for brake on and false for off)");
			boolean brakeInput = input.nextBoolean();
			
			if(car1.brakeStatus(brakeInput)==true) {
				System.out.println("Brake Applied");
			}//if
			else {
				System.out.println("Brake Not Applied");
			}//else
			
			System.out.println("Fuel Remaining " + car1.fuelTank());
			
			if(car1.fuelTank()<=0) {
				System.out.println("Car ran out of fuel");
			}//if
			
			System.out.println("Would you like to turn engine off (Type On Or Off)");
			 onOffEngine= input.next();
			
			car1.engineStatus(onOffEngine);
			
			}//do
			//while(onOffEngine.equals("no"));
		while(car1.engineStatus(onOffEngine)==true);
		
    }//testDrive
   
    
}//CarDealership
