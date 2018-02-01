//Program:      Person.java
//Course:       COSC210
//Description:	Person class for objects used in Assigment 2
//Author:       Edgar Flores
//Revised:      2/15/2017
//Language:     Java
//IDE:          NetBeans 8.2
//Notes:        none
//*******************************************************************************
//*******************************************************************************

//Class:		Person
//Description:	Declares the variables and data types that will be used in the object
//              String name - the name of the person
//              String hobby - the hobby of the person
//*******************************************************************************
//*******************************************************************************
public class Person {
    String Name;
    String Hobby;
    //********************************************************************
    //Method:       Person
    //Description:	Default constructor
    //Parameters:  	none
    //Returns:     	none
    //Calls:       	none
    //Globals:      none
    public Person() {
    }
    //********************************************************************
    //Method:       Person
    //Description:	Constructor for the Person object
    //Parameters:  	String name - the name of the person
    //              String hobby - the hobby of the person
    //Returns:     	none
    //Calls:       	none
    //Globals:      none
    Person(String name, String hobby) {
        Name = name;
        Hobby = hobby;
    }   
    //********************************************************************
    //Method:       getNameAndHoby
    //Description:	Retrieves the name and hobby of the person 
    //Parameters:  	none
    //Returns:     	Name + ", " + Hobby
    //Calls:       	none
    //Globals:      none
    public String getNameAndHobby() {
        return Name + ", " + Hobby;
    }

    //********************************************************************
    //Method:       getName
    //Description:	Retrieves the name of the person in the object
    //Parameters:  	none
    //Returns:     	Name
    //Calls:       	none
    //Globals:      none
    public String getName() {
        return Name;
    }
    //********************************************************************
    //Method:       setName
    //Description:	Sets the name of the person
    //Parameters:  	String name - the name of the person
    //Returns:     	none
    //Calls:       	none
    //Globals:      none
    public void setName(String Name) {
        this.Name = Name;
    }
    //********************************************************************
    //Method:       getHobby
    //Description:	Retrieves the hobby of the person in the object
    //Parameters:  	none
    //Returns:     	Hobby
    //Calls:       	none
    //Globals:      none
    public String getHobby() {
        return Hobby;
    }
    //********************************************************************
    //Method:       setHobby
    //Description:	Sets the hobby of the person
    //Parameters:  	String Hobby- the hobby of the person
    //Returns:     	none
    //Calls:       	none
    //Globals:      none
    public void setHobby(String Hobby) {
        this.Hobby = Hobby;
    }

}
