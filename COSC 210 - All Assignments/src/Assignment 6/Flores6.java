//Program:		Flores6.java
//Course:		COSC210
//Description:	Assignement 6
//Author:		Edgar Flores
//Revised:		4/18/2017
//Language:		Java
//IDE:			NetBeans 8.2
//Notes:		none
//******************************************************************************
//******************************************************************************

//Class:        Flores6
//Description:	This program allows the user to play a simple game which 
//              positions N X's on an NxN board such that no X's are in the same
//              row, the same column, or along the same diagonal.
//******************************************************************************
//******************************************************************************
public class Flores6 {

    //Method:       main
    //Description:	This method asks the user for the value of N and then calls
    //              the appropriate method to create the board.
    //Parameters:  	none
    //Returns:     	nothing
    //Calls:       	createBoard
    //              recursiveGame
    //Globals:      n - this is the value the user specified for the number of
    //                  x's
    //              solution - this determines if there is or is not a solution
    //              boardEvaluated - this is the amount of boards evaluated
    //********************************************************************
    public static int n = 1;
    public static int boardsEvaluated = 0;
    public static boolean solution = false;

    public static void main(String[] args) {
        KeyboardInputClass newInput = new KeyboardInputClass();
        System.out.println("Assignment 6: Edgar Flores \n- This program allows "
                + "for simple game that positions N X's on an NxN board \nsuch "
                + "that no X's are in the same row, the same column, or along "
                + "the same diagonal. \n");
        while (n != 0) {
            n = newInput.getInteger(true, 0, 0, 999999999, "Specify the number"
                    + "of x's (default = 0 (to exit)):");
            if (n != 0) {
                char boardPositionAns = newInput.getCharacter(true, 'N', "YN", 1, "Show"
                        + " intermediate board positions? Y/N (default = N): ");
                char gameBoardArray[][] = new char[n][n];
                createBoard(gameBoardArray);
                recursiveGame(gameBoardArray, 0, boardPositionAns);
                solution = false;
                boardsEvaluated = 0;
                n = 1;
            } else if(n != 0) {
                System.out.println();
                System.out.println("No solution");
                System.out.println();
                solution = false;
                boardsEvaluated = 0;
                n = 1;
            }
        }
    }

    //Method:       createBoard
    //Description:	This method creates the board for the game
    //Parameters:   gameBoardArray - the array used to hold the containts of 
    //                               game board.
    //Returns:     	nothing
    //Calls:       	nothing           
    //Globals:      n - this is the value the user specified for the number of
    //                  x's
    //********************************************************************
    private static void createBoard(char[][] gameBoardArray) {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                gameBoardArray[i][j] = '*';
            }//End of inner for loop
        }//End of outer for loop        
    }

    //Method:       printBoard
    //Description:	This method prints the board for the game
    //Parameters:   gameBoardArray - the array used to hold the containts of 
    //                               game board.
    //Returns:     	nothing
    //Calls:       	nothing           
    //Globals:      n - this is the value the user specified for the number of
    //                  x's
    //********************************************************************
    private static void printBoard(char[][] gameBoardArray) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(gameBoardArray[i][j]);
            }//End of inner for loop
            System.out.println();
        }//End of outter for loop        
    }

    //Method:       copyBoard
    //Description:	This method copy the board for the game
    //Parameters:   previousBoard - the array of previous board
    //              copyOfPreviousBoard - an array which is the copy of the 
    //                                    previous board
    //Returns:     	nothing
    //Calls:       	nothing           
    //Globals:      n - this is the value the user specified for the number of
    //                  x's
    //********************************************************************
    private static void copyBoard(char[][] previousBoard,
            char[][] copyOfPreviousBoard) {
        for (int row = 0; row < n; row++) {
            for (int column = 0; column < n; column++) {
                char symbol = previousBoard[row][column];
                copyOfPreviousBoard[row][column] = symbol;
            }//End of inner for loop
        }//End of outer for loop
    }//End of copyBoard

    //Method:       conflict
    //Description:	This method will ensure that no X's are in the same
    //              row, the same column, or along the same diagonal.
    //Parameters:   gameBoard - the array used to hold the containts of 
    //                               game board.
    //              row - current row
    //              column - current column
    //Returns:     	conflict - if ture then there is a conflict; if false then
    //                          there is not a conflict
    //Calls:       	nothing           
    //Globals:      n - this is the value the user specified for the number of
    //                  x's
    //********************************************************************
    private static boolean conflict(char[][] gameBoard, int row, int column) {
        boolean conflict = false;
        int tempRow = row;
        int tempColumn = column;

        //check north
        while (tempRow >= 0) {
            if (gameBoard[tempRow][tempColumn] != '*') {
                conflict = true;
                break;
            }//End of if statement
            tempRow--;
        }//End of outer for loop

        //Check northeast
        if (conflict != true) {
            tempRow = row;
            tempColumn = column;
            while (tempRow >= 0 && tempColumn < n) {
                if (gameBoard[tempRow][tempColumn] != '*') {
                    conflict = true;
                    break;
                }//End of if statement
                tempRow--;
                tempColumn++;
            }//end of while loop
        }//end of if statement

        //Check east
        if (conflict != true) {
            tempRow = row;
            tempColumn = column;
            while (tempColumn < n) {
                if (gameBoard[tempRow][tempColumn] != '*') {
                    conflict = true;
                    break;
                }//End of if statement
                tempColumn++;
            }//end of while loop
        }//end of if statement

        //Check southeast
        if (conflict != true) {
            tempRow = row;
            tempColumn = column;
            while (tempRow < n && tempColumn < n) {
                if (gameBoard[tempRow][tempColumn] != '*') {
                    conflict = true;
                    break;
                }//End of if statement
                tempColumn++;
                tempRow++;
            }//end of while loop
        }//end of if statement

        //check south
        if (conflict != true) {
            tempRow = row;
            tempColumn = column;
            while (tempRow < n) {
                if (gameBoard[tempRow][tempColumn] != '*') {
                    conflict = true;
                    break;
                }//End of if statement
                tempRow++;
            }//end of while loop
        }//end of if statement

        //check southwest
        if (conflict != true) {
            tempRow = row;
            tempColumn = column;
            while (tempRow < n && tempColumn >= 0) {
                if (gameBoard[tempRow][tempColumn] != '*') {
                    conflict = true;
                    break;
                }//End of if statement
                tempColumn--;
                tempRow++;
            }//end of while loop
        }//end of if statement

        //check west
        if (conflict != true) {
            tempRow = row;
            tempColumn = column;
            while (tempColumn >= 0) {
                if (gameBoard[tempRow][tempColumn] != '*') {
                    conflict = true;
                    break;
                }//End of if statement
                tempColumn--;
            }//end of while loop
        }//end of if statement

        //check northwest
        if (conflict != true) {
            tempRow = row;
            tempColumn = column;
            while (tempRow >= 0 && tempColumn >= 0) {
                if (gameBoard[tempRow][tempColumn] != '*') {
                    conflict = true;
                    break;
                }//End of if statement
                tempColumn--;
                tempRow--;
            }//end of while loop
        }//end of if statement        

        return conflict;
    }//End of conflict method

    //Method:       recursiveGame
    //Description:	This method allows the user to play a simple game which 
    //              positions N X's on an NxN board
    //Parameters:   previousBoard - the array of previous board
    //              copyOfPreviousBoard - an array which is the copy of the 
    //                                    previous board
    //              row -   the current row
    //              boardPositionAns - this demtermines if user wants to print 
    //                                  out boards
    //Returns:     	nothing
    //Calls:       	conflict
    //              copyBoard
    //Globals:      n - this is the value the user specified for the number of
    //                  x's
    //              solution - this determines if there is or is not a solution
    //              boardEvaluated - this is the amount of boards evaluated
    //********************************************************************
    private static void recursiveGame(char[][] previousBoard, int row, char boardPositionAns) {
        KeyboardInputClass newInput = new KeyboardInputClass();
        char continueAnswer;
        char copyOfPreviousBoard[][] = new char[n][n];
        if (row < n && row >= 0) {
            for (int column = 0; column < n && solution == false; column++) {
                copyBoard(previousBoard, copyOfPreviousBoard);
                copyOfPreviousBoard[row][column] = 'X';
                if (boardPositionAns == 'Y') {
                    printBoard(copyOfPreviousBoard);
                    continueAnswer = newInput.getCharacter(false, 'a', "", 0,
                            "Press any key...");
                }
                copyOfPreviousBoard[row][column] = '*';

                if (conflict(copyOfPreviousBoard, row, column) == false) {
                    copyOfPreviousBoard[row][column] = 'X';
                    boardsEvaluated++;
                    if (row == n - 1) {
                        System.out.println("Final board configuration: ");
                        printBoard(copyOfPreviousBoard);
                        System.out.println("Number of boards evalutated = " + boardsEvaluated);
                        continueAnswer = newInput.getCharacter(false, 'a', "", 0,
                                "Press any key...");
                        solution = true;
                        break;
                    } else {
                        recursiveGame(copyOfPreviousBoard, row + 1, boardPositionAns);
                    }//end of else                 
                } else if (conflict(copyOfPreviousBoard, row, column) == true) {
                    boardsEvaluated++;
                }else if (conflict(copyOfPreviousBoard, row, column) == true 
                        && row == n - 1 && column == n - 1) {
                        System.out.println("No solution");
                        solution = true;
                }//                        
            }//End of for loop
        }//End of if statement
    }//End of recursiveGame

}//End of class
