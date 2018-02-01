//Program:      Flores1.java
//Course:       COSC210
//Description:	Assigment 1
//Author:       Edgar Flores
//Revised:      1/30/2017
//Language:     Java
//IDE:          NetBeans 8.2
//Notes:        none
//*******************************************************************************
//*******************************************************************************

//Class:		Flores1
//Description:	Provides multiple methods for manipulating an array
//*******************************************************************************
//*******************************************************************************

public class Flores1 {

    //********************************************************************
    //Method:       main
    //Description:	Provides a menu of options a user can select from
    //Parameters:  	none
    //Returns:     	none
    //Calls:       	none
    //Globals:      none
    public static void main(String[] args) {
        KeyboardInputClass keyboardInput = new KeyboardInputClass();
        int[] arrayNums = new int[100];
        boolean loop = true;
        int arrayCounter = 0;
        System.out.println("Assignemnt 1: Edgar Flores");

        while (loop == true) {

            int option = keyboardInput.getInteger(true, 1, 1, 7,
                    "\n1 = New Integer(default)\n2 = Find Largest Integer\n3 = Find Smallest Integer\n"
                    + "4 = Look for Number Closest to\n5 = Find Average\n6 = Display Integers\n7 = Exit Program");

            switch (option) {
                case 1:
                    int newInteger = keyboardInput.getInteger(false, 0, 0, 0, "Enter Integer : ");
                    newInteger(arrayNums, newInteger, arrayCounter);
                    arrayCounter++;
                    break;
                case 2:
                    System.out.println("\nThe largest integer is: " + findLargest(arrayNums, arrayCounter));
                    break;
                case 3:
                    System.out.println("\nThe smallest integer is: " + findSmallest(arrayNums, arrayCounter));
                    break;
                case 4:
                    int userNum = keyboardInput.getInteger(false, 0, 0, 0, "\nEnter integer to search for: ");
                    System.out.println("The value closest to " + userNum + " is: " + valueClosest(userNum, arrayNums, arrayCounter));
                    break;
                case 5:
                    System.out.println("\nThe average is: " + findAverage(arrayNums, arrayCounter));
                    break;
                case 6:
                    displayValues(arrayNums, arrayCounter);
                    break;
                case 7:
                    loop = false;
                    break;
            }//End of switch
        }//end of while loop
    }// End of main method
    
    //********************************************************************
    //Method:       newInteger
    //Description:	Adds user's entered integer into the array called: arrayNums
    //Parameters:  	int[ ] arrayNums - the array that stores integers entered by the user
    //              int newInteger - the value of the integer entered by the user
    //              int arrayCounter - the value of the current array index
    //Returns:     	none
    //Calls:       	none
    //Globals:      none
    public static void newInteger(int[] arrayNums, int newInteger, int arrayCounter) {
        arrayNums[arrayCounter] = newInteger;
    }//End of newInteger method
    
    //********************************************************************
    //Method:       findLargest
    //Description:	finds the largest value in the array called: arrayNums
    //Parameters:  	int[ ] arrayNums - the array that stores integers entered by the user
    //              int arrayCounter - the value of the current array index
    //Returns:     	largest - this is the value of the largest integer in the array
    //Calls:       	none
    //Globals:      none
    public static int findLargest(int[] arrayNums, int arrayCounter) {
        int largest = arrayNums[0];
        for (int i = 0; i < arrayCounter; i++) {
            if (arrayNums[i] > largest) {
                largest = arrayNums[i];
            }
        }//end of for loop
        return largest;
    }//end of findLargest method
    
    //********************************************************************
    //Method:       findSmallest
    //Description:	finds the smallest value in the array called : arrayNums
    //Parameters:  	int[ ] arrayNums - the array that stores integers entered by the user
    //              int arrayCounter - the value of the current array index
    //Returns:     	smallest - this is the value of the smallest integer in the array
    //Calls:       	none
    //Globals:      none
    public static int findSmallest(int[] arrayNums, int arrayCounter) {
        int smallest = arrayNums[0];
        for (int i = 0; i < arrayCounter; i++) {
            if (arrayNums[i] < smallest) {
                smallest = arrayNums[i];
            }
        }//end of for loop
        return smallest;
    }//end of findLargest method
    
    //********************************************************************
    //Method:       displayValues
    //Description:	Prints out the values in the array called: arrayNums
    //Parameters:  	int[ ] arrayNums - the array that stores integers entered by the user
    //              int arrayCounter - the value of the current array index
    //Returns:     	none
    //Calls:       	none
    //Globals:      none
    public static void displayValues(int[] arrayNums, int arrayCounter) {
        System.out.println("Integers are: ");
        for (int i = 0; i < arrayCounter; i++) {
            System.out.println(arrayNums[i]);
        }//End of for loop
    }//End of displayValues method
    
    //********************************************************************
    //Method:       findAverage
    //Description:	finds the average of the values stored in the array called: arrayNums
    //Parameters:  	int[ ] arrayNums - the array that stores integers entered by the user
    //              int arrayCounter - the value of the current array index
    //Returns:     	average - the average of the values stored in the array called: arrayNums
    //Calls:       	none
    //Globals:      none
    public static float findAverage(int[] arrayNums, int arrayCounter) {
        int addedNums = 0;
        for (int i = 0; i < arrayCounter; i++) {
            addedNums += arrayNums[i];
        }
        float average = addedNums / arrayCounter;
        return average;
    }

    //********************************************************************
    //Method:           valueClosest
    //Description:      finds the closest value in the array called: arryNums, to a user specificed entery
    //Parameters:       userNum - the value of the integer the user is searching for
    //                  int[ ] arrayNums - the array that stores integers entered by the user
    //                  int arrayCounter - the value of the current array index
    //Returns:          closestValue - the value closest to the user specified entery
    //Calls:            none
    //Globals:          none
    public static int valueClosest(int userNum, int[] arrayNums, int arrayCounter) {
        int smallestDistance = Math.abs(arrayNums[0] - userNum);
        int closestValue = arrayNums[0];
        for (int i = 1; i < arrayCounter; i++) {
            int distance = Math.abs(arrayNums[i] - userNum);
            if (distance < smallestDistance) {
                smallestDistance = distance;
                closestValue = arrayNums[i];
            }
        }//end of for loop
        return closestValue;
    }
}// End of main class