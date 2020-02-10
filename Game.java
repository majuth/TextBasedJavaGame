/**
 *  This Game class creates and initializes all the others; it creates all
 *  rooms, creates the parser, creates the objects and starts the game.  
 *  It also evaluates and executes the commands that the parser returns.
 *  An instance of the class must be created and play method must be called
 *  to start the game.
 * 
 * @author  Majuth Kirakalaprathapan
 * @version 2.0
 * @since   2018-06-11
 */

class Game{
	
    private Parser parser;
    private int currentRoom;
    private Room rooms[] = new Room[9];
    private Object backPack[] = new Object[9];
    private int nextEmptySpot = 0;
    private boolean robotsDisabled = false;

    /**
     * This is the main constructor it initializes the room objects, 
     * the backpack array and the parser.
     */
    public Game() {
		createRooms();
		createBackpack();
		parser = new Parser();
    }

    /**
     * This method creates all the rooms by passing the 
     * data needed to instantiate room objects.
     */
    private void createRooms(){
      
		// create the rooms
	    rooms[0] = new Room("Whispering Woods", -1, -1, 1, -1, "lookForItem", "rifle", null, "none", 0);
	    rooms[1] = new Room("Snaggy Spaceship", 0, 2, 5, 3, "alienFight", "usb", "securitycard", "alien", 100);
	    rooms[2] = new Room("Snobby Heights", -1, -1, -1, 1, "talkToPerson", "money", null, "none", 0);
	    rooms[3] = new Room("Android Industries", -1, 1, 4, -1, "factoryMission", "taser", null, "robot", 1000);
	    rooms[4] = new Room("Sneezy Sanatorium", 3, 5, -1, -1, "robotFight", "tranquilizer", null, "robot", 1000);
	    rooms[5] = new Room("Ascending Apartments", 1, -1, 6, 4, "talkToPerson", "giantkey", null, "none", 0);
	    rooms[6] = new Room("Alienating Outpost", 5, -1, -1, -1, "checkPost", "smallkey", null, "alien", 150);
	    rooms[7] = new Room("Perishing Police Station", -1, -1, -1, 6, "searchLocker", "flamethrower", null, "none", 0);
	    rooms[8] = new Room("Cynical City Hall", 6, -1, -1, -1, "bossFight", null, null, "boss", 5000);	
	   
		//set room descriptions
		rooms[0].setDescription("You are in a dark snow covered forest with large trees all around you, the only opening is south of you.");
		rooms[1].setDescription("You find a lightly guarded alien spaceship that seems to carry supplies.");
		rooms[2].setDescription("You enter a rich neighborhood with mansions surrounding you, most of the houses seem to be abandoned.");
		rooms[3].setDescription("The factory of an arms manafacture towers above you, they seem to have been producing robots before they shut down.");
		rooms[4].setDescription("You come across the local hospital, it has been abandoned after an alien attack but not everything was taken.");
		rooms[5].setDescription("You find the apartments which house those who did not flee the city after the attack.");
		rooms[6].setDescription("You come along a heavily reinforced checkpoint which is in the way of you and your journey through the city. \nYou must find a way through.");
		rooms[7].setDescription("You find a police station left in ruins, the fireproof armory remains intact.");
		rooms[8].setDescription("You stand infront of city hall, it has been overtaken by the aliens and used as their base of operation. It is locked up.");
		
		//set item descriptions
		rooms[0].setObjectsDescriptions("Rifle - A standard weapon that does low damage but affects all types of enemies", null);
		rooms[1].setObjectsDescriptions("USB - An item that has code stored by the aliens, can be used somewhere in the game", "Security Card - Used to open doors when a card is required");
		rooms[2].setObjectsDescriptions("Money - Can be used to get another item that can only be obtained using money", null);
		rooms[3].setObjectsDescriptions("Taser - A weapon specialized for use against androids", null);
		rooms[4].setObjectsDescriptions("Tranquilizer - A weapon specialized for use against simple living enemies", null);
		rooms[5].setObjectsDescriptions("Giantkey - Can be used to open the door to the cityhall", null);
		rooms[6].setObjectsDescriptions("Smallkey- Can be used to unlock something which requires a smallkey", null);
		rooms[7].setObjectsDescriptions("Flamethrower - The ultimate weapon which is affective agianst all types of enemies", null);
		
		//set output for person talking
		rooms[2].setPersonTalking("Hello, my name is Steve I used to work at android industries before the attack, \n" + 
								  "i can provide you with assistance on how to deal with the robots.\n" + 
								  "The factory is overcome by rogue robots who are almost indestructible. \n" + 
								  "Your only way to defeat these robots is to use a taser, the only one I know of is hidden \n" +
								  "in a vending machine in the factory. Once you defeat the rogue robots search for a good robot \n" +
								  "and hand him the usb with the aliens code so that he can disable the rogue robots.");
		rooms[5].setPersonTalking("Hi, my name is Tom, I was the janitor at the city hall prior to the invasion, \n" + 
								  "To get into the city hall you would need to use a giantkey.");
		
		// start game outside
		currentRoom = 0;
    }
    
    /**
     * The play method creates the input loop which processes the 
     * input commands and determines whether the program is finished.
     */
    public void play(){            
		printWelcome();
	
		// Enter the main command loop, repeatedly read commands
			
		boolean finished = false;
		while (! finished) {
		    Command command = parser.getCommand();
		    finished = processCommand(command);
		}
		System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * The printWelcome method prints out the opening message for the player.
     */
    private void printWelcome(){
		System.out.println();
		System.out.println("Welcome to The Outbreak!");
		System.out.println("The city has been overrun by rogue robots, and aliens, save your city!");
		System.out.println("Type 'help' if you need help.");
		System.out.println();
		printLocationInfo();
    }

    /**
     * The process command method processes the given command by executing the command.
     * If this command ends the game, true is returned, otherwise false is returned.
     */
    private boolean processCommand(Command command){
		boolean wantToQuit = false;
	
		if(command.isUnknown()) {
		    System.out.println("I don't know what you mean...");
		    return false;
		}
	
		//check which method to run
		String commandWord = command.getCommandWord();
		if (commandWord.equals("help")){
		    printHelp(command);
		}else if (commandWord.equals("go")){
		    goRoom(command);
		}else if (commandWord.equals("quit")){
		    wantToQuit = quit(command);
		}else if (commandWord.equals("look")){
			look(command);
		}else if (commandWord.equals("take")){
			take(command);
		}else if (commandWord.equals("check")){
			if(command.getSecondWord() == null){
				System.out.println("Check what?");
			}else if(command.getSecondWord().equals("backpack")){
				checkBackPack();
			}else{
				checkItem(command);
			}
		}else if (commandWord.equals("use")){
			wantToQuit = use(command);
		}else if (commandWord.equals("run")){
			if(rooms[currentRoom].getEnemyHealth() > 0){
			run(command);
			}else{
				System.out.println("There is nothing to run from.");
			}
		}
		return wantToQuit;
    }

    /**
     * printHelp method prints out help information. Print out all possible 
     * command words and how command words must be used with other words.
     */
    private void printHelp(Command command){
    	if(command.hasSecondWord()){
		    System.out.println("Help what?");
		
		}
		else{
			System.out.println("You are lost. You are alone. \nYou wander around the city.");
			System.out.println();
			System.out.println("Your command words are: \n" +
			"go, quit, help, look, take, check, use, run \n" +
			"go - use [go + direction] to go to another area \n" +
			"quit - use [quit] to exit the game \n" +
			"help - use [help] to bring up the help menu you are currently in \n" +
			"look - use [look] to see what happens next in the room or to see what objects are in the room \n" +
			"take - use [take + objectname] to put object in your backpack \n" +
			"check - use [check + backpack or objectname] to check whats in your backpack or to check what an object can do \n" +
			"use - use [use + objectname] to use an object for it's given purpose \n" +
			"run - use [run] to run from an enemy to the start");
		}
    }

    /** 
     * goRoom method is used to move the player to another room
     * based on the inputed direction and whether there is a room there.
     * -1 indicates that there is no room and any other number correlates to a room.
     */
    private void goRoom(Command command){
		if(!command.hasSecondWord()) {
		    // if there is no second word, we don't know where to go
		    System.out.println("Go where?");
		    return;
		}
	
		String direction = command.getSecondWord();
	
		// Try to leave current room.
		int nextRoom =-1;
		if(direction.equals("north"))
		    nextRoom = rooms[currentRoom].getNorthExit();
		if(direction.equals("east"))
		    nextRoom = rooms[currentRoom].getEastExit();
		if(direction.equals("south"))
		    nextRoom = rooms[currentRoom].getSouthExit();
		if(direction.equals("west"))
		    nextRoom = rooms[currentRoom].getWestExit();
	
		if (nextRoom == -1){
		    System.out.println("There is no exit!");
		}else{
		    currentRoom = nextRoom;
		    System.out.println();
		    printLocationInfo();
		    
		}
    }

    /** 
     * quit method is used when quit is entered. Check the rest of the command to see
     * whether to really quit the game. Return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command){
		if(command.hasSecondWord()){
		    System.out.println("Quit what?");
		    return false;
		}
		else{
			// signal that player wants to quit
			return true;  
		}
	}
    
    /**
     * createBackpack method instantiates a blank array of objects
     */
    private void createBackpack(){
    	for(int i = 0; i<9; i++) {
    		backPack[i] = new Object(null);
    	}
    }
    
    /**
     * look method is used to look at what's going on in the room,
     * if any events are going on in the room they will be brought 
     * up first depending on the rooms event type. Then after all 
     * events are done objects in the room will be brought up.
     * @param  command  Command is passed to method as second word in command is needed
     */
    private void look(Command command){
    	if(command.hasSecondWord()){
		    System.out.println("Look what?");
    	}
		else{
	    	if((rooms[currentRoom].getEvent()).equals("lookForItem")){
	    		//output items in room
				System.out.print("Items: ");
				System.out.println(rooms[currentRoom].getObjectsNames());
				rooms[currentRoom].setSearched();
			}else if((rooms[currentRoom].getEvent()).equals("alienFight")){
				//output enemy's health while it's alive
				if(rooms[currentRoom].getEnemyHealth() != 0){
					System.out.println("An alien appears in front of you with " + rooms[currentRoom].getEnemyHealth() + " health defeat it, or run!");
				}else{
					//output items in room
					System.out.print("Items: ");
					System.out.println(rooms[currentRoom].getObjectsNames());
					rooms[currentRoom].setSearched();
				}
			}else if((rooms[currentRoom].getEvent()).equals("talkToPerson")){
				//output persons dialog
				System.out.println();
				System.out.println("While you look around you meet a person.");
				System.out.println(rooms[currentRoom].getPersonTalking());
				if(rooms[currentRoom].objects[0].getName() != null){
					//output person giving item based on which person you are talking to
					 if(currentRoom == 2){
					 System.out.println("Here is the money you will need for the vending machine."); 
					 System.out.print("Items: ");
					 System.out.println(rooms[currentRoom].getObjectsNames());
					 rooms[currentRoom].setSearched();
					 }else if(currentRoom == 5){
						 System.out.println("I can provide you with a way into the city hall. Here is the key to the city hall door.");
						 System.out.print("Items: ");
						 System.out.println(rooms[currentRoom].getObjectsNames());
						 rooms[currentRoom].setSearched();
					 }
				 }
			}else if((rooms[currentRoom].getEvent()).equals("factoryMission")){
				if(rooms[currentRoom].objects[0].getName() != null){
					//get taser from room first
					System.out.println("You find a vending machine with a tazer in it, use money to get the taser.");
				}else if(rooms[currentRoom].objects[0].getName() == null && rooms[currentRoom].getEnemyHealth() > 0){
					//fight enemy in room, output's enemy health
					System.out.println("A hostile robot appears in front of you with " + rooms[currentRoom].getEnemyHealth() + " health defeat it, or run!");
				}else if(rooms[currentRoom].objects[0].getName() == null && rooms[currentRoom].getEnemyHealth() == 0 && robotsDisabled == false){
					//interact with assistance robot
					System.out.println("You run into another robot.");
					System.out.println("Hi, i am an assistance robot. What can i help you with?");
				}else if(rooms[currentRoom].objects[0].getName() == null && rooms[currentRoom].getEnemyHealth() == 0 && robotsDisabled == true){
					//give option to use teleporter in room
					System.out.println("You find a prototype teleporter in the storage section of the factory. \nIt seems to be powered still.");
				}
			}else if((rooms[currentRoom].getEvent()).equals("robotFight")){
				//output enemy's health until its defeated
				if(rooms[currentRoom].getEnemyHealth() != 0 && robotsDisabled != true){
					System.out.println("A robot appears in front of you with " + rooms[currentRoom].getEnemyHealth() + " health defeat it, or run!");
				}else{
					//reveal items in room after enemy is defeated
					System.out.print("Items: ");
					System.out.println(rooms[currentRoom].getObjectsNames());
					rooms[currentRoom].setSearched();
				}
			}else if((rooms[currentRoom].getEvent()).equals("checkPost")){
				if(rooms[currentRoom].getEnemyHealth() != 0){
					//output enemy's health
					System.out.println("An alien appears in front of you with " + rooms[currentRoom].getEnemyHealth() + " health defeat it, or run!");
				}else if(rooms[currentRoom].getEnemyHealth() == 0 && rooms[currentRoom].getSouthExit() == -1){
					//state that doors need to be opened while they remain locked
					System.out.println("The checkpost gate is still locked, use a security card to get through.");
				}else if(rooms[currentRoom].getSouthExit() != -1){
					//output items in room
					System.out.print("Items: ");
					System.out.println(rooms[currentRoom].getObjectsNames());
					rooms[currentRoom].setSearched();
				}
			}else if((rooms[currentRoom].getEvent()).equals("searchLocker")){
				//don't output items until gun locker has been opened
				if(rooms[currentRoom].getSearched() == false) {
					System.out.println("You come across a gun locker with a flamethrowe in it, it requires a small key to open.");
				}else if(rooms[currentRoom].getSearched() == true){
					System.out.print("Items: ");
					System.out.println(rooms[currentRoom].getObjectsNames());
				}
			}else if((rooms[currentRoom].getEvent()).equals("bossFight")){
				//boss will not be displayed until the door to cityhall is opened
				if(rooms[currentRoom].getSearched() == false){
					System.out.println("The cityhall is locked, use a giantkey to get in!");
				}else{
					System.out.println("You come across The Mighty Supreme Regime Leader of the aliens. \n" +
									   "Regime Leader: If you wish to stop our taskeover you must stop me first!");
					System.out.println("The alien boss has " + rooms[currentRoom].getEnemyHealth() + " health. Use a flamethrower to defeat it!");
				}
			}
		}
    }
    
    /**
     * take command is used to pick up objects and place the in the backpack by
     * copying the object in the room to an object in the backpack object array
     * @param  command  Users command is given to determine what object is taken (second word)
     */
    private void take(Command command){
        boolean objectTaken = false;
    	if(command.getSecondWord() != null){
        for (int i = 0; i < 2; i++){
    		if((command.getSecondWord()).equals(rooms[currentRoom].objects[i].getName()) && rooms[currentRoom].getSearched() == true){
    			//runs if item is in room and the room is allowed to be searched
    			backPack[nextEmptySpot].copyObject(rooms[currentRoom].objects[i]);
    			rooms[currentRoom].objects[i].setName(null);
    			rooms[currentRoom].objects[i].setDescription(null);
    			objectTaken = true;
    			//when all items are taken backpack will be full and nothing will be left to pickup
    			if(nextEmptySpot != 8){
    				nextEmptySpot++;
    			}
    			System.out.println("The " + backPack[nextEmptySpot - 1].getName() + " has been put in your backpack.");
    		}
    	} 
    	if(objectTaken == false){
    		boolean objectInBag = false;
    		for(int i = 0; i < 9; i++) {
    			if(command.getSecondWord().equals(backPack[i].getName())) {
    				objectInBag = true;
    			}
    		}
    		if(objectInBag == true) {
    			System.out.println("The " + command.getSecondWord() + " is already in your backpack.");
    		}else {
    			System.out.println("There is no such object to take.");
    		}
    	}
    	}else{
    		System.out.println("Take what?");
    	}
    }
    
    /**
     * checkBackPack method is used to output the items in the array 
     * of objects used for the backpack
     */
    private void checkBackPack(){
    	if(backPack[0].getName() != null){
    		System.out.print("You have a: ");
	    	for(int i =0; i < 9; i++){
	    		if(!(backPack[i].getName() == null)){
	    			System.out.print(backPack[i].getName() +", ");
	    		}
	    	}
	    	System.out.println("in your backpack.");
    	}else{
    		System.out.println("You have nothing in your backpack.");
    	}
    }
    
    /**
     * use method is used to utilize the objects ability if the object is used in the
     * proper way. 
     * @param  command  User command is passed on so item being used is known
     * @return boolean  Return true if item used was able to end the game else return false
     */
    private boolean use(Command command){
    	boolean beatGame = false;
    	if(command.getSecondWord() != null) {
	    	boolean hasObject = false;
	    	for (int i =0; i < 9; i++){
	    		if(command.getSecondWord().equals(backPack[i].getName())){
	    			hasObject = true;
	    		}
	    	}
	    	//check if player has item and is using it in the right item
	    	if(command.getSecondWord().equals("rifle") && rooms[currentRoom].getEnemyHealth() > 0 && hasObject == true){
	    		rooms[currentRoom].enemyHealth -= 25;
	    		System.out.println("It was effective, 25 damage done. The enemy has " + rooms[currentRoom].getEnemyHealth() + " health.");
	    		if(rooms[currentRoom].getEnemyHealth() == 0){
	    			System.out.println("You have defeated the enemy!");
	    		}
	    	}else if(command.getSecondWord().equals("money") && currentRoom == 3 && hasObject == true && rooms[currentRoom].getSearched() == false){
	    		System.out.print("Items: ");
				System.out.println(rooms[currentRoom].getObjectsNames());
				rooms[currentRoom].setSearched();
	    	}else if(command.getSecondWord().equals("taser") && rooms[currentRoom].getEnemyHealth() > 0 && hasObject == true){
	    		if(rooms[currentRoom].getEnemyType().equals("robot")){
	    			System.out.println("It was very effective " + rooms[currentRoom].getEnemyHealth() + " damage done.");
	    			rooms[currentRoom].enemyHealth -= rooms[currentRoom].getEnemyHealth();
	    			System.out.println("You have defeated the robot!");
	    		}else{
	    			System.out.println("Taser was ineffective");
	    		}
	    	}else if(command.getSecondWord().equals("tranquilizer") && rooms[currentRoom].getEnemyHealth() > 0 && hasObject == true){
	    		if(rooms[currentRoom].getEnemyType().equals("alien")){
	    			System.out.println("It was very effective " + rooms[currentRoom].getEnemyHealth() + " damage done.");
	    			rooms[currentRoom].enemyHealth -= rooms[currentRoom].getEnemyHealth();
	    			System.out.println("You have defeated the alien!");
	    		}else{
	    			System.out.println("Tranquilizer was ineffective");
	    		}
	    	}else if(command.getSecondWord().equals("usb") && currentRoom == 3 && hasObject == true && rooms[currentRoom].getEnemyHealth() == 0 && robotsDisabled == false){
	    		System.out.println("Robot: Scanning code....");
	    		System.out.println("Robot: This seems to be a robot self destruct key written by the aliens. \nYou will no longer need to worry about rogue robots on your journey!");
	    		robotsDisabled = true;	
	    	}else if(command.getSecondWord().equals("securitycard") && currentRoom == 6 && hasObject == true && rooms[currentRoom].getSouthExit() == -1){
	    		System.out.println("You find a control panel and use the securitycard, doors to the south and east open up.");
	    		rooms[6].setExits(5, 7, 8, -1);
	    		//room.setsouth and east to open
	    	}else if(command.getSecondWord().equals("smallkey") && currentRoom == 7 && hasObject == true && rooms[currentRoom].getSearched() == false){ 
	    		System.out.print("Items: ");
				System.out.println(rooms[currentRoom].getObjectsNames());
				rooms[currentRoom].setSearched();
	    	}else if(command.getSecondWord().equals("giantkey") && currentRoom == 8 && hasObject == true && rooms[currentRoom].getSearched() == false){ 
	    		System.out.println("The door to the city hall open up.");
				rooms[currentRoom].setSearched();
	    	}else if(command.getSecondWord().equals("flamethrower") && rooms[currentRoom].getEnemyHealth() > 0 && hasObject == true){
	    		if(rooms[currentRoom].getEnemyType().equals("boss")){
	    			if(rooms[currentRoom].getEnemyHealth() > 1000){
	    				System.out.println("It was very effective " + 1000 + " damage done. The alien boss has " + rooms[currentRoom].getEnemyHealth() + " health.");
	        			rooms[currentRoom].enemyHealth -= 1000;
	    			}else{
	    				System.out.println("It was very effective " + rooms[currentRoom].getEnemyHealth() + " damage done. \n");
	        			rooms[currentRoom].enemyHealth -= rooms[currentRoom].getEnemyHealth();
	        			System.out.println("You have defeated the boss! \n");
	        			System.out.print("Your courageous actions has brang on a rebelion which saves your city from the alien regime");
	        			if(robotsDisabled == true){
	        				System.out.println(" and the rogue roboots.");
	        			}
	        			else{
	        				System.out.println(".");
	        			}
	        			System.out.println("Congradulations you have beat the game!");
	        			beatGame = true;
	    			}
	    		}else{
	    			System.out.println("It was very effective " + rooms[currentRoom].getEnemyHealth() + " damage done.");
	    			rooms[currentRoom].enemyHealth -= rooms[currentRoom].getEnemyHealth();
	    			System.out.println("You have defeated the enemy!");
	    		}
	    	}else if(command.getSecondWord().equals("teleporter")){
	    		if(currentRoom == 3){
	    			//generate random number under 5 to determine which room to go to
	    			int newRoom = (int) Math.floor(Math.random() *5);
	    			currentRoom = newRoom;
	    			System.out.println();
	    		    System.out.println("You are transported to a random area.");
	    		    printLocationInfo();
	    		}else{
	    			System.out.println("There is no teleporter in this area.");
	    		}
	    	}else if (hasObject == false){
	    		System.out.println("You do not have this object.");
	    	}else{
	    		System.out.println("There is no where to use this object.");
	    	}
    	}else{
    		System.out.println("Use what?");
    	}
    	return beatGame;
    }
    
    /**
     * run method is used when a player wants to run from enemy.
     * Current room is set to zero and new location info is printed.
     * @param  command  User command is passed on to insure that command is being used properly (only one word)
     */
    private void run(Command command){
    	if(command.hasSecondWord()){
		    System.out.println("Run what?");
		}
		else{
			currentRoom = 0;
	    	System.out.println();
		    System.out.println("You keep running while the enemy chases you it doesn't stop chasing until you go back into the dark woods.");
		    printLocationInfo();
		}
    }
    
    /**
     * printLocationInfo method is used to print out information on the current room.
     * Name, description, exits, and whether room has been visited is outputted.
     */
    private void printLocationInfo(){
    	System.out.println("You are at " + rooms[currentRoom].getName());
	    System.out.println(rooms[currentRoom].getDescription());
	    if(rooms[currentRoom].getVisited() == true){
	    	System.out.println("You have visited this area.");
	    }else{
	    	System.out.println("You have not visited this area before.");
	    }
	    //print direction where exits are
	    rooms[currentRoom].setVisited();
	    System.out.print("Exits: ");
	    if(rooms[currentRoom].getNorthExit() != -1)
		System.out.print("north ");
	    if(rooms[currentRoom].getEastExit() != -1)
		System.out.print("east ");
	    if(rooms[currentRoom].getSouthExit() != -1)
		System.out.print("south ");
	    if(rooms[currentRoom].getWestExit() != -1)
		System.out.print("west ");
	    System.out.println();
    }
    
    /**
     * checkItem method is used to output description of an item
     * @param  command  User command is given to determine what object to change.
     */
    private void checkItem(Command command){
    	boolean hasObject = false;
    	int backPackSpot = 0;
    	//find spot in array where item is
    	for (int i =0; i < 9; i++){
    		if(command.getSecondWord().equals(backPack[i].getName())){
    			hasObject = true;
    			backPackSpot = i;
    		}
    	}
    	if(hasObject == true){
    		System.out.println(backPack[backPackSpot].getDescription());
    	}else{
    		System.out.println("You do not have such object");
    	}
    }
  
}