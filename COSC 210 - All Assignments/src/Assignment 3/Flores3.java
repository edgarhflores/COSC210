//Program:      Flores3.java
//Course:       COSC210
//Description:	Assigment 3 - Recursion Project
//Author:       Edgar Flores
//Revised:      3/6/2017
//Language:     Java
//IDE:          NetBeans 8.2
//Notes:        none
//**************************************************************************************************************************
//**************************************************************************************************************************

//Class:		Flores3
//Description:	This program allows a user to change the contents of a text file recursively. That is, the program asks the 
//              user for a text file, reads the text file, and creates a double array and copies the text file into that array.
//              The program then asks the user for a location (row and column) and a character (char). Then, the program 
//              changes the character in the user specified location to the new character. Next, the program changes any 
//              character surrounding the user specified location that was the same to the original character. Finally, the 
//              program moves on to change any other character surrounding the recently changed characters to the new character
//              and so on depending on the four neighborhood method or the eight neighborhood method.
//**************************************************************************************************************************
//**************************************************************************************************************************
public class Flores3 {
    //**************************************************************************************************************************    
    //Method:       main
    //Description:	This method asks the user for a text file, creates a double array, and then copies the text file contents
    //              into the the double array. The method prompts the user to enter a specified location (row and column).
    //              Additionally, the method displays a short menu option:
    //              1 = 4-Neighborhood
    //              2 = 8-Neighborhood
    //              3 = Exit Program
    //Parameters:  	none
    //Returns:      none	
    //Calls:        none	  
    //Globals:      linecount
    //              text[]
    public static void main(String[] args) {

        KeyboardInputClass keyboardInput = new KeyboardInputClass();
        TextFileClass textFile = new TextFileClass();
        boolean loop = true;
        int row = 0;
        int column = 0;

        System.out.println("Assignment 3: Edgar Flores\n");
        textFile.getFileName("Specify the text file to be read");
        textFile.getFileContents();
        char textArray[][] = new char[textFile.lineCount][textFile.text[5].length()];

        //This assigns the text file into the 2-D Array
        for (int i = 0; i < textFile.lineCount; i++) {
            for (int j = 0; j < textFile.text[i].length(); j++) {
                textArray[i][j] = textFile.text[i].charAt(j);
            }//End of inner for loop
        }//End of outer for loop
        
        //This is the menu option
        while (loop == true) {
            System.out.println();
            printArray(textArray, textFile);
            System.out.println();
            System.out.println("Choose an option: \n");
            System.out.println("1 = 4-Neighborhood");
            System.out.println("2 = 8-Neighborhood");
            System.out.println("3 = Exit Program");
            int option = keyboardInput.getInteger(true, 1, 1, 3, "");
            if (option == 1 || option == 2) {
                System.out.println();
                row = keyboardInput.getInteger(true, 2, 2, textFile.lineCount-1, "Enter a row location");
                System.out.println();
                column = keyboardInput.getInteger(true, 1, 0, textFile.text[5].length()-1, "Enter a column location");
                System.out.println();
            }
            switch (option) {
                case 1:
                    fourNeighbor(textArray, row, column, textFile);
                    break;
                case 2:
                    eightNeighbor(textArray, row, column, textFile);
                    break;
                case 3:
                    loop = false;
                    break;
            }//End of switch
        }//End of while loop
    }//End of main
    
    //**************************************************************************************************************************    
    //Method:       printArray()      
    //Description:	This method prints the double array
    //Parameters:  	textArray -     this is the double array that holds the text file contents
    //              textfile -      this is the text file the user chose
    //Returns:     	none
    //Calls:       	none      
    //Globals:      linecount
    //              text[]
    private static void printArray(char[][] textArray, TextFileClass textFile) {
        for (int i = 0; i < textFile.lineCount; i++) {
            for (int j = 0; j < textFile.text[i].length(); j++) {
                System.out.print(textArray[i][j]);
            }//End of inner for loop
            System.out.println();
        }//End of outter for loop
    }//End of printArray Method
    
    //**************************************************************************************************************************    
    //Method:       fourNeighbor()
    //Description:	This deals with the four neighborhood method. This allows for character and orignalChar to be declared
    //              before the recursion begans.
    //Parameters:  	textArray -     this is the double array that holds the text file contents
    //                    row -     this the the user specified row
    //                 column -     this the the user specified column
    //               textfile -     this is the text file the user chose   
    //Returns:     	none
    //Calls:       	fourNeighbor()           
    //Globals:      none
    private static void fourNeighbor(char[][] textArray, int row, int column, TextFileClass textFile) {
        KeyboardInputClass keyboardInput = new KeyboardInputClass();
        char character = keyboardInput.getCharacter(false, '+', "", 0, "Enter a character");
        char orignalChar = textArray[row][column];
        fourNeighbor(character, orignalChar, textArray, row, column, textFile);
    }//End of fourNeighborhood
    
    //************************************************************************************************************************** 
    //Method:       fourNeighbor()
    //Description:	This method allows the text file to be changed recursively according to the four neighborhood method
    //Parameters:  	character -     this is the user specified character
    //            orignalChar -     this is the char that was orignally in the user specified location
    //              textArray -     this is the double array that holds the text file contents
    //                    row -     this the the user specified row
    //                 column -     this the the user specified column
    //               textfile -     this is the text file the user chose   
    //Returns:     	none
    //Calls:       	fourNeighbor()           
    //Globals:      linecount
    //              text[]
    private static void fourNeighbor(char character, char orignalChar, char[][] textArray, int row, int column, TextFileClass textFile) {
        if (row >= 2 && row < textFile.lineCount && column >= 0 && column < textFile.text[5].length() && textArray[row][column] == orignalChar) {
            textArray[row][column] = character;
            //Check north
            fourNeighbor(character, orignalChar, textArray, row - 1, column, textFile);
            //Check east
            fourNeighbor(character, orignalChar, textArray, row, column + 1, textFile);
            //Check south
            fourNeighbor(character, orignalChar, textArray, row + 1, column, textFile);
            //Check west
            fourNeighbor(character, orignalChar, textArray, row, column - 1, textFile);
        }//end of if
    }//End of fourNeighbor
    
    //**************************************************************************************************************************    
    //Method:       eightNeighbor()
    //Description:	This deals with the eight neighborhood method. This allows for character and orignalChar to be declared
    //              before the recursion begans.
    //Parameters:  	textArray -     this is the double array that holds the text file contents
    //                    row -     this the the user specified row
    //                 column -     this the the user specified column
    //               textfile -     this is the text file the user chose   
    //Returns:     	none
    //Calls:       	fourNeighbor()           
    //Globals:      none
    private static void eightNeighbor(char[][] textArray, int row, int column, TextFileClass textFile) {
        KeyboardInputClass keyboardInput = new KeyboardInputClass();
        char character = keyboardInput.getCharacter(false, '+', "", 0, "Enter a character");
        char orignalChar = textArray[row][column];
        eightNeighbor(character, orignalChar, textArray, row, column, textFile);
    }

    //************************************************************************************************************************** 
    //Method:       eightNeighbor()
    //Description:	This method allows the text file to be changed recursively according to the eight neighborhood method
    //Parameters:  	character -     this is the user specified character
    //            orignalChar -     this is the char that was orignally in the user specified location
    //              textArray -     this is the double array that holds the text file contents
    //                    row -     this the the user specified row
    //                 column -     this the the user specified column
    //               textfile -     this is the text file the user chose   
    //Returns:     	none
    //Calls:       	fourNeighbor()           
    //Globals:      linecount
    //              text[]
    private static void eightNeighbor(char character, char orignalChar, char[][] textArray, int row, int column, TextFileClass textFile) {
        if (row >= 2 && row < textFile.lineCount && column >= 0 && column < textFile.text[5].length() && textArray[row][column] == orignalChar) {
            textArray[row][column] = character;
            //Check north
            eightNeighbor(character, orignalChar, textArray, row - 1, column, textFile);
            //Check northeast
            eightNeighbor(character, orignalChar, textArray, row - 1, column + 1, textFile);
            //Check east
            eightNeighbor(character, orignalChar, textArray, row, column + 1, textFile);
            //Check southeast
            eightNeighbor(character, orignalChar, textArray, row + 1, column + 1, textFile);
            //Check south
            eightNeighbor(character, orignalChar, textArray, row + 1, column, textFile);
            //Check southwest
            eightNeighbor(character, orignalChar, textArray, row + 1, column - 1, textFile);
            //Check west
            eightNeighbor(character, orignalChar, textArray, row, column - 1, textFile);
            //Check northwest
            eightNeighbor(character, orignalChar, textArray, row - 1, column - 1, textFile);
        }//end of if
    }//End of eightNeighbor
}//End of class
