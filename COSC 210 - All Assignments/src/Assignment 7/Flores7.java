//Program:		Flores7.java
//Course:		COSC210
//Description:	Assignment 7 - Airplane landing simulation program 
//Author:		Edgar Flores
//Revised:		4/2/2017
//Language:		Java
//IDE:			NetBeans 8.2
//******************************************************************************
//******************************************************************************

//Class:        Flores7
//Description:	This simulates a landing program for an airplane arriving at
//              an airport and waiting to land. The airport can accept a landing
//              every two minutes
//              Airplanes contain the following data:
//              - Airline
//              - Airline number
//              - Remaining fuel quantity
//              - Arrival clock
//              Menu options:
//              - Hitting the "ENTER" key - allows one simulated minute to pass 
//                                          by
//              - p - one simulated minute will pass and a new airplane will be 
//                    added to the queue           
//              - s - shows the content of the flight queue, but no clock passes
//              - x - signals a desire to exit the routine
//******************************************************************************
public class Flores7 {

    public static int clock = 0;
    public static boolean isFirst;
    public static int landClock = 0;
    public static boolean landTimer = false;

    //********************************************************************
    //Method:       main
    //Description:	Provides menu of options for controlling an airplane landing
    //              simulation program station. 
    //              Menu options:
    //              - Hitting the "ENTER" key - allows one simulated minute to 
    //                                          pass by
    //              - p - one simulated minute will pass and a new airplan will 
    //                    be added to the queue           
    //              - s - shows the content of the flight queue, but no clock 
    //                    passes
    //              - x - signals a desire to exit the routine
    //Parameters:  	none
    //Returns:     	nothing
    //Calls:       	addNewPlaneToQueue
    //              displayAirplaneQueue
    //Globals:      clock   - the internal clock of the program
    //              isFirst - determines if the plane is the first plane in 
    //                        queue
    //              landClock - this keeps track of time between airplane 
    //                          landings to determine if two minutes have passed
    //                          for a plane to land
    //              landTimer - this variable lets the landClock know when to 
    //                          start counting
    public static void main(String[] args) {
        QueueInterface airplaneQueue = new PriorityQueue();
        KeyboardInputClass input = new KeyboardInputClass();
        char userInput = 'a';
        boolean crash = false;
        boolean loop = true;

        System.out.println("Assignment 7");
        System.out.println("Airport Simulation Program: Edgar Flores");
        System.out.println("----------------------------------------------");
        System.out.println("ENTER = increment the simulation clock only");
        System.out.println("P = generate a new arrival (and increment the clock)");
        System.out.println("S = show priority queue contents (clock does not change)");
        System.out.println("X = exit simulation program");
        System.out.println();

        while (loop) {
            userInput = input.getCharacter(true, 'A', "PSX", 1, "Next Action:");
            switch (userInput) {
                case 'A':
                    updateTime(airplaneQueue);
                    break;
                case 'P':
                    addNewPlaneToQueue(airplaneQueue);
                    break;
                case 'S':
                    displayAirplaneQueue(airplaneQueue);
                    break;
                case 'X':
                    loop = false;
            }//End of switch
            System.out.println();
        }//End of while loop       

    }//End of main method

    //********************************************************************
    //Method:       addNewPlaneToQueue
    //Description:	Adds a new plane to the airplane queue and increments the
    //              clock
    //Parameters:  	airPlaneQueue - this is the queue that holds the airplane
    //                              objects
    //Returns:     	nothing
    //Calls:       	updateTime
    //Globals:      isFirst
    private static void addNewPlaneToQueue(QueueInterface airplaneQueue) {
        Airplane newPlane = new Airplane();
        isFirst = airplaneQueue.isEmpty();
        airplaneQueue.priorityEnqueue(newPlane);
        System.out.println(newPlane.getAirline() + " flight "
                + newPlane.getAirplaneNumber() + " arrives at "
                + newPlane.getArrivalTime() + " minutes with "
                + newPlane.getFuel() + " minutes of fuel left");
        updateTime(airplaneQueue);
        isFirst = airplaneQueue.isEmpty();
    }

    //********************************************************************
    //Method:       displayAirplaneQueue
    //Description:	Display the planes from airplaneQueue
    //Parameters:  	airPlaneQueue - this is the queue that holds the airplane
    //                              objects
    //Returns:     	nothing
    //Calls:       	nothing
    //Globals:      none
    private static void displayAirplaneQueue(QueueInterface airplaneQueue) {
        QueueInterface tempQueue = new PriorityQueue();
        Airplane dequeuePlane;
        if (airplaneQueue.isEmpty()) {
            System.out.println("No Planes in Queue");
        }
        while (!airplaneQueue.isEmpty()) {
            dequeuePlane = (Airplane) airplaneQueue.dequeue();
            tempQueue.enqueue(dequeuePlane);
            System.out.println(dequeuePlane.getAirline() + " flight "
                    + dequeuePlane.getAirplaneNumber() + ": Fuel left ="
                    + dequeuePlane.getFuel() + "; Arrival time="
                    + dequeuePlane.getArrivalTime() + "; Wait time= "
                    + dequeuePlane.getWaitTime());
        }
        while (!tempQueue.isEmpty()) {
            dequeuePlane = (Airplane) tempQueue.dequeue();
            airplaneQueue.enqueue(dequeuePlane);
        }
    }

    //********************************************************************
    //Method:       updateTime
    //Description:	This method updates the clock time, and the fuel and wait
    //              time of each plane in airPlaneQueue
    //Parameters:  	airPlaneQueue - this is the queue that holds airplane objects
    //Returns:     	nothing
    //Calls:       	checkForCrash
    //              checkLanding
    //Globals:      clock
    private static void updateTime(QueueInterface airplaneQueue) {
        QueueInterface tempQueue = new PriorityQueue();
        Airplane dequeuePlane;

        while (!airplaneQueue.isEmpty()) {
            //get a plane
            dequeuePlane = (Airplane) airplaneQueue.dequeue();
            //Decrement fuel mins of the plane
            dequeuePlane.setFuel(dequeuePlane.getFuel() - 1);
            //increment wait time of the plane
            dequeuePlane.setWaitTime(dequeuePlane.getWaitTime() + 1);
            //add updated plane to tempQueue
            tempQueue.enqueue(dequeuePlane);
        }//end of while

        //requeue airplaneQueue with updated planes
        while (!tempQueue.isEmpty()) {
            dequeuePlane = (Airplane) tempQueue.dequeue();
            airplaneQueue.enqueue(dequeuePlane);
        }//end of while
        if (!airplaneQueue.isEmpty()) {
            boolean crash = checkForCrash(airplaneQueue);
            if (!crash) {
                checkLanding(airplaneQueue);
            }
        }
        clock++;
    }//end of updateTime

    //********************************************************************
    //Method:       checkForCrash
    //Description:	This method checks to see if a plane will crash
    //Parameters:  	tempQueue - this is the queue that temporarily holds 
    //              airplane objects from airplaneQueue
    //Returns:     	crash - evaluation of airplane crash 
    //Calls:       	nothing
    //Globals:      none
    private static boolean checkForCrash(QueueInterface tempQueue) {
        boolean crash = false;
        Airplane plane = (Airplane) tempQueue.getFront();
        int planeFuel = plane.getFuel();
        if (planeFuel < 0) {
            System.out.println(plane.getAirline() + " flight "
                    + plane.getAirplaneNumber() + " CRASHES after "
                    + "waiting for " + plane.getWaitTime()
                    + " minutes");
            tempQueue.dequeue();
            crash = true;
        }
        return crash;
    }

    //********************************************************************
    //Method:       checkLanding
    //Description:	This method checks to see if a plane will land
    //Parameters:  	tempQueue - this is the queue that temporarily holds 
    //              airplane objects from airplaneQueue
    //Returns:     	land - evaluation of airplane landing 
    //Calls:       	nothing
    //Globals:      landClock
    //              isFirst
    //              landTimer
    private static boolean checkLanding(QueueInterface tempQueue) {
        Airplane plane = (Airplane) tempQueue.getFront();
        boolean land = false;
        if (isFirst && !landTimer) {
            landClock = 2;
        }
        boolean canLand = landClock % 2 == 0;
        if (isFirst && !landTimer || landTimer && canLand && !isFirst) {
            System.out.println(plane.getAirline() + " flight "
                    + plane.getAirplaneNumber() + " lands with "
                    + plane.getFuel() + " mins of fuel left after waiting "
                    + plane.getWaitTime() + " min(s)");
            tempQueue.dequeue();
            landClock++;
            landTimer = true;
            land = true;
        } else if (landTimer) {
            landClock++;
        }
        return land;
    }

//Class:		Airplane
//Description:	Generates random data for an airplane. The random data contains:
//              1.) An airLine from the following 8:
//                  - United
//                  - Southwest
//                  - Northwest
//                  - Continental
//                  - American
//                  - Frontier
//                  - Alaska
//                  - Lufthansa
//              2.) A flight number from the range 1,000 - 9,000
//              3.) Amount of fuel left repersented minutes from 1-20
//                  - 1 is min and 20 is max
//              4.) An arival clock
//******************************************************************************
//******************************************************************************  
    public static class Airplane implements Comparable<Airplane> {

        String airLine;
        int airplaneNumber;
        int fuel;
        int arrivalTime;
        int waitTime;

        //*********************************************************************
        //Method:       DataClass
        //Description:	Constructor for generating the random data for an object
        //Parameters:  	none
        //Returns:      nothing
        //Calls:        nohting
        //Globals:      clock 
        public Airplane() {
            String airlineName = "";
            int ranNum = 1 + (int) (Math.random() * 8);
            switch (ranNum) {
                case 1:
                    airlineName = "United";
                    break;
                case 2:
                    airlineName = "Southwest";
                    break;
                case 3:
                    airlineName = "Northwest";
                    break;
                case 4:
                    airlineName = "Continental";
                    break;
                case 5:
                    airlineName = "American";
                    break;
                case 6:
                    airlineName = "Frontier";
                    break;
                case 7:
                    airlineName = "Alaska";
                    break;
                case 8:
                    airlineName = "Lufthansa";
            }
            this.airLine = airlineName;
            this.airplaneNumber = 1000 + (int) (Math.random() * 8000);
            this.fuel = 1 + (int) (Math.random() * 20);
            this.arrivalTime = clock;
            this.waitTime = 0;
        }//End of constructor

        //**********************************************************************
        //Method:       getAirline
        //Description:	gets the string value for airLine
        //Parameters:  	none
        //Returns:      airLine - this is an air line from one of the following:
        //                          - United
        //                          - Southwest
        //                          - Northwest
        //                          - Continental
        //                          - American
        //                          - Frontier
        //                          - Alaska
        //                          - Lufthansa
        //                        
        //Calls:        nothing
        //Globals:      none
        public String getAirline() {
            return airLine;
        }//End of getAirLine

        //**********************************************************************
        //Method:       getAirplaneNumber
        //Description:	gets the int value for airplaneNumber
        //Parameters:  	none
        //Returns:      airplaneNumber - the airplane number	
        //Calls:        nothing
        //Globals:      none
        public int getAirplaneNumber() {
            return airplaneNumber;
        }//End of getAirplaneNumber

        //**********************************************************************
        //Method:       getFuel
        //Description:	gets the int value for fuel
        //Parameters:  	none
        //Returns:      fuel - the amount of fuel in mins
        //Calls:        nothing
        //Globals:      none
        public int getFuel() {
            return fuel;
        }//End of getFuel

        //**********************************************************************
        //Method:       setFuel
        //Description:	sets the int value for fuel
        //Parameters:  	fuel - the amount of fuel in mins
        //Returns:      nothing	
        //Calls:        nothing
        //Globals:      none
        public void setFuel(int fuel) {
            this.fuel = fuel;
        }

        //**********************************************************************
        //Method:       getArrivalTime
        //Description:	gets the int value for the arrival clock of the plane
        //Parameters:  	none
        //Returns:      arrivalTime - the arrival time of a plane	
        //Calls:        nothing
        //Globals:      none
        public int getArrivalTime() {
            return arrivalTime;
        }

        //**********************************************************************
        //Method:       setArrivalTime
        //Description:	sets the int value for the arrival clock of the plane
        //Parameters:  	arrivalTime - the arrival time of a plane
        //Returns:      nothing	
        //Calls:        nothing
        //Globals:      none        
        public void setArrivalTime(int arrivalTime) {
            this.arrivalTime = arrivalTime;
        }

        //**********************************************************************
        //Method:       getWaitTime
        //Description:	gets the int value for wait time
        //Parameters:  	none
        //Returns:      waitTime - the wait time of a plane in the queue for
        //                         landing
        //Calls:        nothing
        //Globals:      none  
        public int getWaitTime() {
            return waitTime;
        }

        //**********************************************************************
        //Method:       setWaitTime
        //Description:	sets the int value for wait time
        //Parameters:  	waitTime - the wait time of a plane in the queue for
        //                         landing
        //Returns:      nothing
        //Calls:        nothing
        //Globals:      none 
        public void setWaitTime(int waitTime) {
            this.waitTime = waitTime;
        }

        //**********************************************************************
        // Method:      compareTo
        // Description: Compares the fuel data for planes
        // Parameters:  plane - the plane object
        // Returns:     outcome - int variable holding either -1, 1, or 0 used 
        //                        for sorting.
        // Calls:       None
        @Override
        public int compareTo(Airplane plane) {
            int outcome = 0;
            if (this.fuel < plane.fuel) {
                outcome = -1;
            }
            if (this.fuel > plane.fuel) {
                outcome = 1;
            }
            return outcome;
        }

    }//End of Airplane class
}// End of Flores7
