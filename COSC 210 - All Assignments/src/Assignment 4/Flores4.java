//Program:      Flores4.java
//Course:       COSC210
//Description:	Assigment 4
//Author:       Edgar Flores
//Revised:      3/23/2017
//Language:     Java
//IDE:          NetBeans 8.2
//Notes:        none
//**************************************************************************************************************************
//**************************************************************************************************************************

//Class:		Flores4
//Description:	This program sorts a set of objects with random data with the following sort algorithms:
//                  - Quick Sort
//                  - Merge Sort
//                  - Iterative Selection Sort
//                  - Iterative Bubble Sort
//                  - Iterative Insertion Sort
//                  - Shell Sort
//              The random data contains:
//                  1.) an int in the range [0-50000)
//                  2.) a double in the range [0-1)
//                  3.) a String of exactly 40 lowercase letters
//              The user chooses the number of objects in the set to be sorted, and the sort field for the objects 
//              to be sorted:
//                  1 = iCode - sort by the integer value
//                  2 = dCode - sort by double value
//                  3 = sCode - sort by string value
//              Additionally, the program outputs the time each sorting algorithms take to sort a set of objects
//              in milli seconds. 
//**************************************************************************************************************************
//**************************************************************************************************************************
public class Flores4 {

    public static int code;
    //**************************************************************************************************************************    
    //Method:       main
    //Description:	This provides method asks the user to specify the number of bojects to be created and sorted, the sort 
    //              field, and whether or not to display the unsorted and sorted Array. The program then creates the objects 
    //              and calls six sorting algorithms. 
    //Parameters:  	none
    //Returns:      nothing
    //Calls:        printArrays
    //              copyArray
    //Globals:      code
    public static void main(String[] args) {

        KeyboardInputClass newinput = new KeyboardInputClass();
        char loopAnswer = 'Y';

        System.out.println("Assignment 4: This program sorts a set of objects with random data by implementing six sorting "
                + "algorithms, and according to a sort field\n");
        while (loopAnswer == 'Y') {

            int objNum = newinput.getInteger(true, 0, 0, 999999999, "Specify the # of objects to be created and sorted: ");

            DataClass[] originalArray = new DataClass[objNum];
            DataClass[] copyArray = new DataClass[objNum];

            System.out.println("Creating array of objects with random data...");

            //Generate Random Data into orginalArray
            DataClass newData;
            for (int i = 0; i < objNum; i++) {
                newData = new DataClass();
                originalArray[i] = newData;
            }//End of for loop       

            System.out.println();
            code = newinput.getInteger(true, 1, 1, 3, "Select a sort field: 1=iCode; 2=dCode; 3=sCode: ");

            char unsortedAnswer = newinput.getCharacter(true, 'N', "YN", 1, "Show the unsorted arrays? (Y/N; default = N): ");
            char sortedAnswer = newinput.getCharacter(true, 'N', "YN", 1, "Show the sorted arrays? (Y/N; default = N): ");
            System.out.println("Sorting...");
            System.out.println();

            String sortAlgorithm = "";
            long endTime = 0;
            long startTime = 0;

            for (int i = 0; i < 6; i++) {

                copyArray(objNum, copyArray, originalArray);

                switch (i) {
                    case 0:
                        startTime = System.currentTimeMillis();
                        SortArrayClass.quickSort(copyArray, objNum);
                        endTime = System.currentTimeMillis();
                        sortAlgorithm = "Quick Sort";
                        break;
                    case 1:
                        startTime = System.currentTimeMillis();
                        SortArrayClass.mergeSort(copyArray, objNum);
                        endTime = System.currentTimeMillis();
                        sortAlgorithm = "Merge Sort";
                        break;
                    case 2:
                        startTime = System.currentTimeMillis();
                        SortArrayClass.iterativeSelectionSort(copyArray, objNum);
                        endTime = System.currentTimeMillis();
                        sortAlgorithm = "Iterative Selection Sort";
                        break;
                    case 3:
                        startTime = System.currentTimeMillis();
                        SortArrayClass.iterativeBubbleSort(copyArray, objNum);
                        endTime = System.currentTimeMillis();
                        sortAlgorithm = "Iterative Bubble Sort";
                        break;
                    case 4:
                        startTime = System.currentTimeMillis();
                        SortArrayClass.iterativeInsertionSort(copyArray, objNum);
                        endTime = System.currentTimeMillis();
                        sortAlgorithm = "Iterative Insertion Sort";
                        break;
                    case 5:
                        startTime = System.currentTimeMillis();
                        SortArrayClass.shellSort(copyArray, objNum);
                        endTime = System.currentTimeMillis();
                        sortAlgorithm = "Shell Sort";
                        break;
                }//End of switch

                System.out.println(sortAlgorithm + ": Elapsed time = " + (endTime - startTime) + "ms");
                System.out.println();
                
                printArrays(originalArray, copyArray, unsortedAnswer, sortedAnswer, objNum);
                

            }//End of for loop

            System.out.println();
            loopAnswer = newinput.getCharacter(true, 'Y', "YN", 1, "Preform another task with a different set of "
                    + "objects? (Y/N; default = Y):");
        }//End of while loop
    }//end of main
    //**************************************************************************************************************************    
    //Method:       printArrays
    //Description:	This method prints the unsorted and sorted array as the user desires
    //Parameters:  	originalArray - array of original objects created
    //              copyArray - array of a copy of the original array objects
    //              unsortedAnswer - answer of whether or not to print the unsorted array
    //              sortedAnswers - answer of whether or not to print the sorted array
    //              objNum - the number of objects the user specified
    //Returns:      nothing
    //Calls:        nothing	  
    //Globals:      none

    private static void printArrays(DataClass[] originalArray, DataClass[] copyArray, char unsortedAnswer, char sortedAnswer, int objNum) {
        KeyboardInputClass newinput = new KeyboardInputClass();
        //Prints unsorted array
        if (unsortedAnswer == 'Y') {
            System.out.println("Unsorted data...");
            System.out.println("String... Integer...Double...");
            System.out.println("-----------------------------");
            for (int i = 0; i < objNum; i++) {
                DataClass originalData = originalArray[i];
                System.out.println(originalData.randomData());
            }//End of for loop
            System.out.println();

            
            int continueAnswer = newinput.getInteger(true, 1, 1, 1, "Press ENTER to continue...");
        }//End of if statement

        //prints sorted array
        if (sortedAnswer == 'Y') {
            System.out.println("Sorted data...");
            System.out.println("String... Integer...Double...");
            System.out.println("-----------------------------");
            for (int i = 0; i < objNum; i++) {
                DataClass copyData = copyArray[i];
                System.out.println(copyData.randomData());
            }//End of for loop
            System.out.println();
            
            int continueAnswer1 = newinput.getInteger(true, 1, 1, 1, "Press ENTER to continue...");
        }//End of if statement
        if (unsortedAnswer == 'Y' || sortedAnswer == 'Y') {
            System.out.println("*********************************************\n"
                    + "*********************************************\n");
        }
    }

    //**************************************************************************************************************************    
    //Method:       copyArray
    //Description:	This method makes a copy of the orginal array
    //Parameters:  	originalArray - array of original objects created
    //              copyArray - array of a copy of the original array objects
    //              unsortedAnswer - answer of whether or not to print the unsorted array
    //              sortedAnswers - answer of whether or not to print the sorted array
    //              objNum - the number of objects the user specified
    //Returns:      nothing
    //Calls:        nothing	  
    //Globals:      none
    private static void copyArray(int objNum, DataClass[] copyArray, DataClass[] originalArray) {

        for (int i = 0; i < objNum; i++) {
            DataClass originalData = originalArray[i];
            double orginalDouble = originalData.getRandomDb();
            int orginalInt = originalData.getRandomInt();
            String orginalString = originalData.getRandomString();

            DataClass copyData = new DataClass();
            copyData.setRandomDb(orginalDouble);
            copyData.setRandomInt(orginalInt);
            copyData.setRandomString(orginalString);
            copyArray[i] = copyData;
        }//End of for loop
    }

//Class:		DataClass
//Description:	Generates random data for an object. The random data contains:
//              1.) an int in the range [0-50000)
//              2.) a double in the range [0-1)
//              3.) a String of exactly 40 lowercase letters
//**************************************************************************************************************************
//**************************************************************************************************************************    
    public static class DataClass implements Comparable<DataClass> {

        String randomString;
        int randomInt;
        double randomDb;

        //**************************************************************************************************************************    
        //Method:       DataClass
        //Description:	Constructor for generating the random data for an object
        //Parameters:  	none
        //Returns:      nothing
        //Calls:        nohting
        //Globals:      none 
        public DataClass() {
            String s = "";
            for (int i = 0; i < 40; i++) {
                int randomNum = 97 + (int) (Math.random() * 26);
                char letter = (char) (randomNum);
                s = s + letter;
            }//end of for loop        
            this.randomString = s;
            this.randomInt = (int) (Math.random() * 50001);
            this.randomDb = Math.random();
        }//End of constructor method

        //**************************************************************************************************************************    
        //Method:       setRandomString
        //Description:	sets the string value for the randomString
        //Parameters:  	randomString - this is the value of a string
        //Returns:      nothing	
        //Calls:        nothing
        //Globals:      none
        public void setRandomString(String randomString) {
            this.randomString = randomString;
        }

        //**************************************************************************************************************************    
        //Method:       getRandomString
        //Description:	gets the string value for the randomString
        //Parameters:  	none
        //Returns:      nothing	
        //Calls:        nothing
        //Globals:      none
        public String getRandomString() {
            return randomString;
        }

        //**************************************************************************************************************************    
        //Method:       setRandomInt
        //Description:	sets the int value for the randomInt
        //Parameters:  	randomInt - this is the value of an int
        //Returns:      nothing	
        //Calls:        nothing
        //Globals:      none
        public void setRandomInt(int randomInt) {
            this.randomInt = randomInt;
        }

        //**************************************************************************************************************************    
        //Method:       getRandomInt
        //Description:	gets the int value for the randomInt
        //Parameters:  	none
        //Returns:      nothing	
        //Calls:        nothing
        //Globals:      none
        public int getRandomInt() {
            return randomInt;
        }

        //**************************************************************************************************************************    
        //Method:       setRandomDb
        //Description:	sets the double value for the randomDb
        //Parameters:  	randomDb - this is the value of a double
        //Returns:      nothing	
        //Calls:        nothing
        //Globals:      none
        public void setRandomDb(double randomDb) {
            this.randomDb = randomDb;
        }

        //**************************************************************************************************************************    
        //Method:       getRandomDb
        //Description:	gets the double value for the randomDb
        //Parameters:  	none
        //Returns:      nothing	
        //Calls:        nothing
        //Globals:      none
        public double getRandomDb() {
            return randomDb;
        }

        //**************************************************************************************************************************    
        //Method:       randomData
        //Description:	this formats the random data for an object into a String
        //Parameters:  	none
        //Returns:      randomString + " " + randomInt + " " + randomDb;
        //Calls:        nothing  
        //Globals:      none
        public String randomData() {
            return randomString + " " + randomInt + " " + randomDb;
        }

        //**************************************************************************************************************************    
        //Method:       compareTo
        //Description:	This overrides the compareTo method. This method provides three different ways to compare objects 
        //              according to a specified sort field:
        //              1 = sorts by int
        //              2 = sorts by double
        //              3 = sorts by String
        //Parameters:  	DataClass t- this is the other object being compared
        //Returns:      0
        //              1
        //              -1
        //Calls:        nothing  
        //Globals:      code
        @Override
        public int compareTo(DataClass t) {
            switch (code) {
                case 1:
                    int firstInt = this.randomInt;
                    int secondInt = t.randomInt;
                    if (firstInt == secondInt) {
                        return 0;
                    } else if (firstInt < secondInt) {
                        return -1;
                    } else {
                        return 1;
                    }//End of else

                case 2:
                    double firstDouble = this.randomDb;
                    double secondDouble =  t.randomDb;
                    if (firstDouble == secondDouble) {
                        return 0;
                    } else if (firstDouble < secondDouble) {
                        return -1;
                    } else {
                        return 1;
                    }//End of else

                case 3:
                    String firstString = this.randomString;
                    String secondString = t.randomString;                   
                    // use compare to method; this returns an int instead of boolean;
                    return firstString.compareTo(secondString);
                default:
                    return 0;
            }//End of switch
        }//End of compare to method
    }//End of DataClass
}//End of Flores4

