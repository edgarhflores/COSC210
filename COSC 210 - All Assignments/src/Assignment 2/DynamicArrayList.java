//Program:      DynamicArrayList.java
//Course:       COSC210
//Description:	Methods that allow for manipulation of a dynamic array list
//Author:       Edgar Flores
//Revised:      2/15/2017
//Language:     Java
//IDE:          NetBeans 8.2
//Notes:        Only added one extra method called: getMaxLength 
//*********************************************************************************
//*********************************************************************************
public class DynamicArrayList implements ListInterface {
	private Object[] entry;// this is the array
	private int length;// this is the counter of the array length
	private static final int INITIAL_SIZE = 1;
	//*****************************************************************************
	public DynamicArrayList() {
		length = 0;
		entry = new Object[INITIAL_SIZE];
	}
	//*****************************************************************************
   	public DynamicArrayList(int initialSize) {
		length = 0;
		entry = new Object[initialSize];
	}
	//*****************************************************************************
	public boolean add(Object newEntry) {
		if (isArrayFull()) 
			doubleArray();
		entry[length] = newEntry;
		length++;
		return true;
	}
	//*****************************************************************************
	public boolean add(int newPosition, Object newEntry) {
		boolean isSuccessful = true;
		if ((newPosition >= 1) && (newPosition <= length+1)) {	
			if (isArrayFull()) 
				doubleArray();
			makeRoom(newPosition);				
			entry[newPosition-1] = newEntry;
			length++;
		}
		else
			isSuccessful = false;
			
		return isSuccessful;
	}
	//*****************************************************************************
	public Object remove(int givenPosition) {
		Object result = null;// must return null to avoid a program crash
		if ((givenPosition >= 1) && (givenPosition <= length)) {
			result = entry[givenPosition-1];
			if (givenPosition < length)
				removeGap(givenPosition);
			length--;
		}
		return result;
	}
	//*****************************************************************************
	public void clear()	{
		length = 0;
	}
	//*****************************************************************************
	public boolean replace(int givenPosition, Object newEntry) {
		boolean isSuccessful = true;
		if ((givenPosition >= 1) && (givenPosition <= length))
			entry[givenPosition-1] = newEntry;
		else
			isSuccessful = false;

		return isSuccessful;
	}
	//*****************************************************************************
	public Object getEntry(int givenPosition) {
		Object result = null;
		if ((givenPosition >= 1) && (givenPosition <= length))
			result = entry[givenPosition-1];

		return result;
	}
	//*****************************************************************************
	public boolean contains(Object anEntry) {
		boolean found = false;
		for (int index = 0; !found && (index < length); index++) {
			if (anEntry.equals(entry[index]))
				found = true;
		}
		return found;
	}
	//*****************************************************************************
	public int getLength() {
		return length;
	}
	//*****************************************************************************
    //Method:       getMaxLength
    //Description:	Retrieves the maximum size of the list
    //Parameters:  	none
    //Returns:     	currenMaxSize
    //Calls:       	none
    //Globals:      none
    public int getMaxLength(){
        Object[] MaxSize = entry;
		int currentMaxSize = MaxSize.length;
        return currentMaxSize;
    }
	//*****************************************************************************
	public boolean isEmpty() {
		return length == 0;
	}
	//*****************************************************************************
	public boolean isFull()	{
		return false;
	}
	//*****************************************************************************
	public void display() {
		for (int index = 0; index < length; index++)
			System.out.println(entry[index]);
	}
	//*****************************************************************************
	private boolean isArrayFull() {
	  return length == entry.length;
	}
	//*****************************************************************************
	private void doubleArray() {
		Object[] oldList = entry;
		int oldSize = oldList.length;
		entry = new Object[2*oldSize];
		for (int index = 0; index < oldSize; index++)
			entry[index] = oldList[index];
	}
	//*****************************************************************************
	private void makeRoom(int newPosition) {
		for (int index = length; index >= newPosition; index--)
			entry[index] = entry[index-1];
	}
	//*****************************************************************************	
	private void removeGap(int givenPosition) {
		for (int index = givenPosition; index < length; index++)
			entry[index-1] = entry[index];
	}
	//*****************************************************************************
} // end DynamicArrayList
//*********************************************************************************
//*****************************************************************