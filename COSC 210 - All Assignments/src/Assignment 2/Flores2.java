//Program:      Flores2.java
//Course:       COSC210
//Description:	Assigment 2
//Author:       Edgar Flores
//Revised:      2/15/2017
//Language:     Java
//IDE:          NetBeans 8.2
//Notes:        none
//*******************************************************************************
//*******************************************************************************

//Class:		Flores2
//Description:	Stores objects into a dynamic array list. The objects being stored are from the class Person.
//*******************************************************************************
//*******************************************************************************
public class Flores2 {
    //********************************************************************
    //Method:       main
    //Description:	Provides a menu of options a user can select from to manipulate the dynamic array list
    //              1 = Add an entry at the end of the list
    //              2 = Add an entry at a specified position in the list
    //              3 = Remove entry from the list
    //              4 = Show entry at a specified position in the list
    //              5 = Show the hobby of a specified individual
    //              6 = Show number of entries, current max size, and contents of the list
    //              7 = Exit program
    //Parameters:  	none
    //Returns:     	none
    //Calls:       	addNewPerson
    //              addNewPersonInSpecifiedPosition
    //              removeEntry
    //              displaySpecifiedPosition
    //              showHobby
    //              displayListContents
    //Globals:      none
    public static void main(String[] args) {

        KeyboardInputClass keyboardInput = new KeyboardInputClass();
        ListInterface myList = new DynamicArrayList();
        boolean loop = true;

        System.out.println("Assignment 2: Edgar Flores");
        while (loop == true) {
            System.out.println();
            System.out.println("1 = Add an entry at the end of the list");
            System.out.println("2 = Add an entry at a specified position in the list");
            System.out.println("3 = Remove entry from the list");
            System.out.println("4 = Show entry at a specified position in the list");
            System.out.println("5 = Show the hobby of a specified individual");
            System.out.println("6 = Show number of entries, current max size, and contents of the list");
            System.out.println("7 = Exit program");
            int option = keyboardInput.getInteger(true, 1, 1, 7, "");
            System.out.println();

            switch (option) {
                case 1:
                    addNewPerson(myList);
                    break;
                case 2:
                    addNewPersonInSpecifiedPosition(myList);
                    break;
                case 3:
                    removeEntry(myList);
                    break;
                case 4:
                    displaySpecifiedPosition(myList);
                    break;
                case 5:
                    showHobby(myList);
                    break;
                case 6:
                    displayListContents(myList);
                    break;
                case 7:
                    loop = false;
                    break;
            }
        }
    }
    //********************************************************************
    //Method:       addNewPerson
    //Description:	Creates an object of the class Person and then stores that object into the end of the dynamic array list 
    //Parameters:  	ListInterface myList - this is the dynamic array list
    //Returns:     	none
    //Calls:       	newPerson.setName
    //              newPerson.setHobby
    //              myList.add
    //              myList.getLength
    //              newPerson.getNameAndHobby
    //Globals:      none
    private static void addNewPerson(ListInterface myList) {
        KeyboardInputClass keyboardInput = new KeyboardInputClass();
        Person newPerson = new Person();
        newPerson.setName(keyboardInput.getString("", "Enter first name: "));
        newPerson.setHobby(keyboardInput.getString("", "Enter hobby: "));
        myList.add(myList.getLength() + 1, newPerson);
        System.out.println();
        System.out.println(newPerson.getNameAndHobby() + " has been added to the list");
    }
    //********************************************************************
    //Method:       addNewPersonInSpecifiedPosition
    //Description:	Asks for specified position, creates an object of the class Person, and then stores that object into the 
    //              specified position in the dynamic array list.
    //Parameters:  	ListInterface myList - this is the dynamic array list
    //Returns:     	none
    //Calls:       	newPerson.setName
    //              newPerson.setHobby
    //              myList.getLength
    //              myList.add(int newPosition, Object newEntry)
    //              newPerson.getNameAndHobby
    //Globals:      none
    private static void addNewPersonInSpecifiedPosition(ListInterface myList) {
        KeyboardInputClass keyboardInput = new KeyboardInputClass();
        int specifiedPosition = keyboardInput.getInteger(true, 1, 1, (int) myList.getLength() + 1, "Enter poistion number in which to add the new entry: ");
        Person newPerson = new Person();
        newPerson.setName(keyboardInput.getString("", "Enter first name: "));
        newPerson.setHobby(keyboardInput.getString("", "Enter hobby: "));
        myList.add(specifiedPosition, newPerson);
        System.out.println();
        System.out.println(newPerson.getNameAndHobby() + " has been added to position " + specifiedPosition);
    }
    //********************************************************************
    //Method:       removeEntry
    //Description:	Removes an object of the class Person from the dynamic array list at a specified position
    //Parameters:  	ListInterface myList - this is the dynamic array list
    //Returns:     	none
    //Calls:       	myList.getLength
    //              myList.remove
    //Globals:      none
    private static void removeEntry(ListInterface myList) {
        KeyboardInputClass keyboardInput = new KeyboardInputClass();
        int positionToBeRemoved = keyboardInput.getInteger(true, 1, 1, (int) myList.getLength(), "Enter poistion number to be removed: ");
        myList.remove(positionToBeRemoved);
        System.out.println();
        System.out.println("Position " + positionToBeRemoved + " has been removed");
    }
    //********************************************************************
    //Method:       displaySpecifiedPosition
    //Description:	Displays the entry of the object from the class Person in a specified position of the dynamic array list
    //Parameters:  	ListInterface myList - this is the dynamic array list
    //Returns:     	none
    //Calls:       	myList.getLength
    //              myList.getEntry
    //              newPerson.getNameAndHobby
    //Globals:      none
    private static void displaySpecifiedPosition(ListInterface myList) {
        KeyboardInputClass keyboardInput = new KeyboardInputClass();
        Person newPerson = new Person();
        int positionToBeViwed = keyboardInput.getInteger(true, 1, 1, (int) myList.getLength(), "Enter the list poistion number to be viewed: ");
        newPerson = (Person) myList.getEntry(positionToBeViwed);
        System.out.println("\nThis position contains: ");
        System.out.println(newPerson.getNameAndHobby());
    }
    //********************************************************************
    //Method:       showHobby
    //Description:	Displays the hobby for a specified person
    //Parameters:  	ListInterface myList - this is the dynamic array list
    //Returns:     	none
    //Calls:       	myList.getLength
    //              myList.getEntry
    //              newPerson.getName
    //              newPerson.getHobby
    //Globals:      none
    private static void showHobby(ListInterface myList) {
        KeyboardInputClass keyboardInput = new KeyboardInputClass();
        Person newPerson = new Person();
        int numberOfEntries = myList.getLength();
        String specifiedPersonName = keyboardInput.getString("", "Enter name of the person to search for: ");
        for (int position = 1; position <= numberOfEntries; ++position) {
            newPerson = (Person) myList.getEntry(position);
            String newPersonName = newPerson.getName();
            if (specifiedPersonName.equalsIgnoreCase(newPersonName)) {
                System.out.println("\n" + newPersonName + "'s hobby is: " + newPerson.getHobby());
                break;
            } else if (!specifiedPersonName.equalsIgnoreCase(newPersonName) && position == numberOfEntries) {
                System.out.println("\n" + specifiedPersonName + " was NOT found in the list");
            }
        }
    }
    //********************************************************************
    //Method:       displayListContents 
    //Description:	Displays the number of enteries in the list, current maximum size of the list, and the contents of the enteries in the
    //              dynamic array list
    //Parameters:  	ListInterface myList - this is the dynamic array list
    //Returns:     	none
    //Calls:       	myList.getLength
    //              myList.getMaxLength
    //              myList.getEntry
    //              newPerson.getNameAndHobby
    //Globals:      none
    private static void displayListContents(ListInterface myList) {
        Person newPerson = new Person();
        int numberOfEntries = myList.getLength();
        int currentMaxSize = myList.getMaxLength();
        System.out.print("List contains ");
        System.out.print(numberOfEntries + " entries");
        System.out.println(", and can currently hold a maximum of " + currentMaxSize + " enteries");
        System.out.println();
        System.out.println("Entries are as follows: ");
        for (int position = 1; position <= numberOfEntries; ++position) {
            newPerson = (Person) myList.getEntry(position);
            System.out.println(position + ".) " + newPerson.getNameAndHobby());
        }
    }
}
