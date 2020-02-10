/**
 * This class holds the information for each different object that can be used
 *
 * @author  Majuth Kirakalaprathapan
 * @version 2.0 
 * @since   2018-06-11
 */
public class Object {

	private String name;
	private String description;
	
	/**
	 * Main constructor creates object using the name of the object
	 * @param  nm  Name of object is passed to create object
	 */
	public Object(String nm){
		name = nm;
	}
	
	/**
	 * setName method sets name of object
	 * @param  nm  Name of object is passed to method to change
 	 */
	public void setName(String nm){
		name = nm;
	}
	
	/**
	 * getName method gets the name of the object
	 * @return Returns the name of the object
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * setDescription method sets the description of the object
	 * @param  descrip  Passes on the description of the object
	 */
	public void setDescription(String descrip){
		description = descrip;
	}
	
	/**
	 * getDescription gets the description of the object
	 * @return  String  Returns the description of the object
	 */
	public String getDescription(){
		return description;
	}
	
	/**
	 * copyObject copies the object from the room to the backpack
	 * @param  roomObject  Passes the object from the room that is gonna be copied
	 */
	public void copyObject(Object roomObject){
		name = roomObject.getName();
		description =roomObject.getDescription();
	}
}
