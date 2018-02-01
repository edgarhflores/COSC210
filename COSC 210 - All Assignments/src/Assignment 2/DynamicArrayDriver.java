public class DynamicArrayDriver 
{
	public static void main(String[] args) 
	{
		testStringList();
		System.out.println("\n\nDone.");
	}  // end main

	public static void testStringList()
	{
		ListInterface myList = new DynamicArrayList();

		System.out.println("Testing add to end ");
		System.out.println("Add 15 : returns " + myList.add("15"));
		System.out.println("Add 25 : returns " + myList.add("25"));
		System.out.println("Add 35 : returns " + myList.add("35"));
		System.out.println("Add 45 : returns " + myList.add("45"));
		System.out.println("\n\nList should contain\n15 25 35 45 ");
		displayList(myList);
		
		System.out.println("\nIs List empty? " + myList.isEmpty());
		System.out.println("Is List full? " + myList.isFull() + "\n");

		System.out.println("Add 55 : returns " + myList.add("55"));
		System.out.println("Add 65 : returns " + myList.add("65"));
		System.out.println("Add 75 : returns " + myList.add("75"));
		System.out.println("Add 85 : returns " + myList.add("85"));
		System.out.println("Add 95 : returns " + myList.add("95"));
		
		System.out.println("\n\nIs List empty? " + myList.isEmpty());
		System.out.println("Is List full? " + myList.isFull());

		System.out.println("-------------------------\n");
		System.out.println("Testing ADT list operation display(): ");
		System.out.println("\n\nList should contain 15 25 35 45 55 65 75 85 95");
		myList.display();
		
		System.out.println("\n------------------------\n");
		System.out.println("Testing clear() ");
		myList.clear();
		
		System.out.println("List should be empty: ");
		System.out.println("Is list empty? " + myList.isEmpty());
		System.out.println("Is list full? " + myList.isFull());

		System.out.println("-------------------------\n");
		System.out.println("Create a new list of initial size 8.\n");
		myList = new DynamicArrayList(8);  // 8 is initial size

		System.out.println("Testing add at position ");
		System.out.println("Add 15 at position 1: returns " + myList.add(1, "15"));
		System.out.println("Add 25 at position 2: returns " + myList.add(2, "25"));
		System.out.println("Add 35 at position 3: returns " + myList.add(3, "35"));
		System.out.println("Add 99 at position 0: returns " + myList.add(0, "99"));
		System.out.println("Add 99 at position 9: returns " + myList.add(9, "99"));
	
		System.out.println("\n\nList should contain\n15 25 35 ");
		displayList(myList);
		
		System.out.println("Is List empty? " + myList.isEmpty());
		System.out.println("Is List full? " + myList.isFull() + "\n");

		System.out.println("Add 19 at position 1: returns " + myList.add(1, "19"));
		System.out.println("Add 39 at position 3: returns " + myList.add(3, "39"));
		System.out.println("Add 29 at position 2: returns " + myList.add(2, "29"));
		System.out.println("Add 55 at position 7: returns " + myList.add(myList.getLength()+1, "55"));
		System.out.println("Add 65 at position 8: returns " + myList.add(8, "65"));
	
		System.out.println("\n\nList should contain\n19 29 15 39 25 35 55 65");
		displayList(myList);
		
		System.out.println("Is List empty? " + myList.isEmpty());
		System.out.println("Is List full? " + myList.isFull());
			
		System.out.println("\n\nAdd 99 at position 9 (array is full): returns " 
		                   + myList.add(9, "99"));
		System.out.println("Is List full? " + myList.isFull());
		System.out.println("\n\nList should contain\n19 29 15 39 25 35 55 65 99");
		displayList(myList);

		System.out.println("\n-------------------------\n");
		System.out.println("Testing remove() ");
		System.out.println("Removing 15 at position 3: returns " + myList.remove(3));
		System.out.println("Removing 19 at position 1: returns " + myList.remove(1));
		System.out.println("Removing 65 at position 6: returns " + myList.remove(6));
		System.out.println("Removing 99 at position 6: returns " + myList.remove(6));
		System.out.println("Removing ?? at position 0: returns " + myList.remove(0));
	
		System.out.println("\n\nList should contain\n29 39 25 35 55");
		displayList(myList);

		System.out.println("\n-------------------------\n");
		System.out.println("Testing replace() ");
		System.out.println("Replace 29 at position 1 with 92 : returns " + myList.replace(1, "92"));
		System.out.println("Replace 39 at position 2 with 93 : returns " + myList.replace(2, "93"));
		System.out.println("Replace 25 at position 3 with 52 : returns " + myList.replace(3, "52"));
		System.out.println("Replace 35 at position 4 with 53 : returns " + myList.replace(4, "53"));
		System.out.println("Replace 55 at position 5 with 50 : returns " + myList.replace(5, "50"));
		System.out.println("Replace ?? at position 0 with 99 : returns " + myList.replace(0, "99"));
		System.out.println("Replace ?? at position 6 with 99 : returns " + myList.replace(6, "99"));
		
		System.out.println("\n\nList should contain\n92 93 52 53 50");
		displayList(myList);
		
		System.out.println("Is List empty? " + myList.isEmpty());
		System.out.println("Is List full? "  + myList.isFull());
		
		
		System.out.println("\n-------------------------\n");
		System.out.println("Testing contains() [results should be TRUE]");
		System.out.println("List contains 92: " + myList.contains("92"));
		System.out.println("List contains 52: " + myList.contains("52"));
		System.out.println("List contains 53: " + myList.contains("53"));
		System.out.println("List contains 50: " + myList.contains("50"));
		System.out.println("\n");

		System.out.println("Testing contains() [results should be FALSE]");
		System.out.println("List contains 91 returns : " + myList.contains("91"));
		System.out.println("List contains 55 returns : " + myList.contains("55"));
		System.out.println("List contains 4  returns : " + myList.contains("4"));
		System.out.println("List contains 12 returns : " + myList.contains("12"));

	} // end testStringList

	public static void displayList(ListInterface aList)
	{
		int numberOfEntries = aList.getLength();
		System.out.print("\nList contains ");
		System.out.print(numberOfEntries);
		System.out.println(" entries, as follows:");
		
		for (int position = 1; position <= numberOfEntries; ++position)
			System.out.print(aList.getEntry(position) + " ");

		System.out.println();
		
		for (int position = 1; position <= numberOfEntries; ++position)
		{
			if (position < 10)
				System.out.print(" " + position);
			else
				System.out.print(position);
				
			System.out.print(" ");
		}  // end for
		System.out.println();
		System.out.println();
	}  // end displayList

}  // end Driver
