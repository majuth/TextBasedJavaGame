/**
 * The room class represents a room in the adventure game.
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labeled north, 
 * east, south, west.  For each direction, the room stores the number to
 * that room, or -1 if there is no exit in that direction.
 * Objects, description, event, and other attributes to
 * the room are also stored.
 * 
 * @author  Majuth Kirakalaprathapan
 * @version 2.0
 * @since   2018-06-11
 */

class Room {
	private String name;
	private String description;
    public Object objects[] = new Object[2];
    private String event;
    private boolean visited;
    private int northExit;
    private int southExit;
    private int eastExit;
    private int westExit;
    public int enemyHealth;
    private boolean searched = false;
    private String personTalking;
    private String enemyType;
   
    /**
     * The main constructor for the room class
     * @param nm     Name of the room
     * @param north  Number of room exit to the north, -1 if no exit
     * @param east   Number of room exit to the east, -1 if no exit
     * @param south  Number of room exit to the south, -1 if no exit
     * @param west   Number of room exit to the west, -1 if no exit
     * @param evnt   Name of event that happens in the room, trigger word for look method
     * @param obj1   Name of first object in room, null if no object
     * @param obj2   Name of second object in room, null if no object
     * @param type   Enemytype of enemy in the room, used in use method, none if no enemy
     * @param health Health of enemy in the room
     */
    public Room(String nm, int north, int east, int south, int west, String evnt, String obj1, String obj2, String type, int health){
        name = nm;
        northExit = north;
        eastExit = east;
        southExit = south;
        westExit = west;
        event = evnt;
        objects[0] = new Object(obj1);
    	objects[1] = new Object(obj2);
    	enemyType = type;
    	enemyHealth = health;
    }

    /**
     * setExits method sets the exits of the room
     * @param north  Number of room that is the north exit, -1 if none
     * @param east   Number of room that is the east exit, -1 if none
     * @param south  Number of room that is the south exit, -1 if none
     * @param west   Number of room that is the west exit, -1 if none
     */
    public void setExits(int north, int east, int south, int west){
            northExit = north;
            eastExit = east;
            southExit = south;
            westExit = west;
    }
    
    /**
     * getNorthExit method used to get number of room north of current room
     * @return  int  Room number that is north, -1 if no room
     */
    public int getNorthExit(){
    	return northExit;
    }
    
    /**
     * getEastExit method used to get number of room east of current room
     * @return  int  Room number that is east, -1 if no room
     */
    public int getEastExit(){
    	return eastExit;
    }
    
    /**
     * getSouthExit method used to get number of room south of current room
     * @return  int  Room number that is south, -1 if no room
     */
    public int getSouthExit(){
    	return southExit;
    }
    
    /**
     * getWestExit method used to get number of room west of current room
     * @return  int  Room number that is west, -1 if no room
     */
    public int getWestExit(){
    	return westExit;
    }
    
    /**
     * getName method gets name of the room
     * @return  String  Name of the object is returned
     */
    public String getName(){
    	return name;
    }
    
    /**
     * getDescription method gets the description of the room
     * @return  String  Description of the room is returned
     */
    public String getDescription(){
        return description;
    }
    
    /**
     * setDescription method sets the description of the room
     * @param  descrip  Pass the description of the room to store it
     */
    public void setDescription(String descrip){
    	description = descrip;
    }
    
    /**
     * getVisited method gets whether the room has been visited before
     * @return  boolean  Visited status of the room is returned
     */
    public boolean getVisited(){
    	return visited;
    }
    
    /**
     * setVisited sets the status of the room to visited
     */
    public void setVisited(){
    	visited = true;
    }
    
    /**
     * setObjects creates the objects in the room using their names
     * @param  obj1  The name of the first object is gotten
     * @param  obj2  The name of the second object is gotten
     */
    public void setObjects(String obj1, String obj2){
    	objects[0] = new Object(obj1);
    	objects[1] = new Object(obj2);
    }
    
    /**
     * getObjectsNames gets the names of all objects in the room in string form
     * @return  Objects in the room is returned as a string
     */
    public String getObjectsNames(){
    	String str;
    	if(objects[0].getName() == null && objects[1].getName() == null){
    		str = "none";
    	}else if(objects[1].getName() == null){
    		str = objects[0].getName();
    	}else{
    		str = objects[0].getName() + ", " + objects[1].getName();
    	}
    	return str;
    }
    
    /**
     * setObjectsDescriptions method sets the description of both objects
     * @param  descrp1  Description of first object
     * @param  descrp2  Description of second object
     */
    public void setObjectsDescriptions(String descrp1, String descrp2) {
    	objects[0].setDescription(descrp1);
    	objects[1].setDescription(descrp2);
    }
    
    /**
     * getObject method returns the object based on the integer given
     * @param   i       Number in array of object wanted
     * @return  Object  The object wanted is returned
     */
    public Object getObject(int i) {
    	return objects[i];
    }
    
    /**
     * getEvent method return the event in the room
     * @return  String  Returns the event in the room
     */
    public String getEvent(){
    	return event;
    }
    
    /**
     * getEnemyHealth returns the health of the enemy in the room
     * @return  int  Returns enemy health
     */
    public int getEnemyHealth(){
    	return enemyHealth;
    }
    
    /**
     * setSearched Sets searched status of room to true
     */
    public void setSearched(){
    	searched = true;
    }
    
    /**
     * etsearched gets the searched status of the room
     * @return  boolean  Returns whether a room was searched
     */
    public boolean getSearched(){
    	return searched;
    }
    
    /**
     * setPersontalking sets the dialog for the person talking
     * @param  txt  Pass the dialog for the person
     */
    public void setPersonTalking(String txt){
    	personTalking = txt;
    }
    
    /**
     * getPersonTalking returns the dialog of the person in the room
     * @return  String  Returns dialog in string form
     */
    public String getPersonTalking(){
    	return personTalking;
    }
    
    /**
     * getEnemyType gets the type of enemy that is in the room
     * @return  String  Return the enemy type as a string
     */
    public String getEnemyType(){
    	return enemyType;
    }
    
}