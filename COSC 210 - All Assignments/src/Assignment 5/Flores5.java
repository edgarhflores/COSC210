//Program:		Flores5.java
//Course:		COSC210
//Description:	Assignement 5
//Author:		Edgar Flores
//Revised:		3/30/2017
//Language:		Java
//IDE:			NetBeans 8.2
//Notes:		none
//******************************************************************************
//******************************************************************************

//Class:        Flores5
//Description:	This program allows for two integers that have very long digits
//              to be added.
//******************************************************************************
//******************************************************************************
public class Flores5 {

    //Method:       main
    //Description:	This method asks the user for two very long integers 
    //              and then calls the apporiate methods to add the two long 
    //              integers together and print the result.
    //Parameters:  	none
    //Returns:     	nothing
    //Calls:       	addDigitsToList
    //              performAddition
    //              printResult            
    //Globals:      none
    //********************************************************************
    public static void main(String[] args) {
        KeyboardInputClass newInput = new KeyboardInputClass();
        boolean isNegAddend1 = false;
        boolean isNegAddend2 = false;
        System.out.println("Assignment 5: Edgar Flores - This program allows "
                + "for two integers with many digits to be added\n");
        while (!isNegAddend1 && !isNegAddend2) {
            ListInterface addend1 = new LList();
            ListInterface addend2 = new LList();
            ListInterface sumOfAddends = new LList();
            String addend1Digits = newInput.getString("0", "Enter frist "
                    + "addend: ");
            isNegAddend1 = addDigitsToList(addend1, addend1Digits);
            if (!isNegAddend1) {
                String addend2Digits = newInput.getString("0", "Enter second "
                        + "addend: ");
                isNegAddend2 = addDigitsToList(addend2, addend2Digits);
            }//End of if statement
            if (!isNegAddend1 && !isNegAddend2) {
                performAddition(addend1, addend2, sumOfAddends);
                printResult(sumOfAddends);
                char continueAnswer = newInput.getCharacter(false, 'a', "", 0,
                        "Press any key...");
            }
        }//End of while        
    }//End of Main

    //Method:       addDigitsToList
    //Description:	This method will add each digit to a position on a linked
    //              list.
    //Parameters:  	list- this is the linked list that the digits will be added 
    //                    to
    //              digits - these are the digits the user specified
    //Returns:     	isNeg - this determins if the number the user entered is
    //                      negative
    //Calls:       	nothing
    //Globals:      none
    //********************************************************************
    private static boolean addDigitsToList(ListInterface list, String digits) {
        boolean isNeg = false;
        //int digitPosition = 0;
        //Check for negative sign
        if (digits.charAt(0) == '-') {
            isNeg = true;
            return isNeg;
        } else {
            for (int digitPosition = 0; digitPosition < digits.length();
                    digitPosition++) {
                String charDigit = "" + digits.charAt(digitPosition);
                int digit = Integer.parseInt(charDigit);
                Integer digitInteger = Integer.valueOf(digit);
                list.add(digitInteger);
            }//End of for loop
            return isNeg;
        }//End of else
    }

    //Method:       performAddition
    //Description:	This method will add the integers of the two linked
    //              list together
    //Parameters:  	addend1 - this is the linked list of the first addend
    //              addend2 - this is the linked list of the second addend
    //              sumOfAddends - this is the linked list of the sum of the 
    //              first and second addend
    //Returns:     	nothing
    //Calls:       	additionOfUnequalLength
    //Globals:      none
    //********************************************************************
    private static void performAddition(ListInterface addend1,
            ListInterface addend2, ListInterface sumOfAddends) {
        int carryNum = 0, addend1Digit, addend2Digit, sumOfNums, length;
        while (addend1.isEmpty() != true || addend2.isEmpty() != true) {
            if (addend1.isEmpty() != true && addend2.isEmpty() != true) {
                //Get last digit in lists, preform addition, determine carryNum,
                //add the sum to the appropiate list, and then remove the digits 
                //used from lists
                addend1Digit = (Integer) addend1.getEntry(addend1.getLength());
                addend2Digit = (Integer) addend2.getEntry(addend2.getLength());
                sumOfNums = addend1Digit + addend2Digit + carryNum;
                if (sumOfNums >= 10) {
                    carryNum = 1;
                    sumOfNums -= 10;
                    //sumOfAddends.add(1, sumOfNums);
                } else {
                    carryNum = 0;
                sumOfAddends.add(1, sumOfNums);
                }//End of inner else statement
                addend1.remove(addend1.getLength());
                addend2.remove(addend2.getLength());
            } else if (addend2.isEmpty() == true) {
                length = addend1.getLength();
                additionOfUnequalLength(addend1, sumOfAddends, carryNum, 
                        length);
            } else if (addend1.isEmpty() == true) {
                length = addend2.getLength();
                additionOfUnequalLength(addend2, sumOfAddends, carryNum, 
                        length);
            }
        }//End of while loop

    }//End of performAddition

    //Method:       additionOfUnequalLength
    //Description:	This method will continue to add the integers of the array
    //              that still has more integers to deal with
    //Parameters:  	addend - this is the linked list of an addend
    //              sumOfAddends - this is the linked list of the sum of the 
    //              first and second addend
    //              carryNum - this is the carry number in addition
    //              length - the is the length of the addend
    //Returns:     	nothing
    //Calls:       	nothing
    //Globals:      none
    //********************************************************************
    private static void additionOfUnequalLength(ListInterface addend,
            ListInterface sumOfAddends, int carryNum, int length) {
        int digit, addendDigit, sumOfNums;
        char charOfString;
        String sumOfNumsString, charToString;
        addendDigit = (Integer) addend.getEntry(addend.getLength());
        sumOfNums = addendDigit + carryNum;
        //Check to see if last node in the list
        if (length == 1) {
            sumOfNumsString = Integer.toString(sumOfNums);
            charOfString = sumOfNumsString.charAt(0);
            charToString = "" + charOfString;
            digit = Integer.parseInt(charToString);
            sumOfAddends.add(1, digit);
            if (sumOfNumsString.length() == 2) {
                charOfString = sumOfNumsString.charAt(1);
                charToString = "" + charOfString;
                digit = Integer.parseInt(charToString);
                sumOfAddends.add(2, digit);
            }//End of if statement 
        } else {
            if (sumOfNums >= 10) {
                carryNum = 1;
                sumOfNums -= 10;
                sumOfAddends.add(1, sumOfNums);
            } else {
                carryNum = 0;
                sumOfAddends.add(1, sumOfNums);
            }//End of inner else
        }//End of outer else
        addend.remove(addend.getLength());
    }//End of additionOfUnequalLength

    //Method:       printResult
    //Description:	This method will print the results of the sum of the 
    //              addition of the first and second addend
    //Parameters:  	sumOfAddends - this is the linked list of the sum of the 
    //              first and second addend
    //Returns:     	nothing
    //Calls:       	nothing
    //Globals:      none
    //********************************************************************
    private static void printResult(ListInterface sumOfAddends) {
        String digits = "";
        for (int i = 1; i < sumOfAddends.getLength() + 1; i++) {
            Integer integerFromList = (Integer) sumOfAddends.getEntry(i);
            String integerToString = Integer.toString(integerFromList);
            digits += integerToString;
        }//End of for loop
        System.out.println("The sum is: \n" + digits);
    }//End of printResult
}//End of class
