package willmelbourne;
import java.util.Set;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author William Melbourne 
 * @version 1.01
 */

public class Room 
{
	private String description;
	private HashMap<String, Room> exits;        // stores exits of this room.
	private ArrayList<Item> items;
	private Character roomCharacter;
	private String directionLocked;
	private Item itemToUnlock;
	private Boolean finalRoom;

	/**
	 * Create a room described "description". Initially, it has
	 * no exits. "description" is something like "a kitchen" or
	 * "an open court yard".
	 * @param description The room's description.
	 */
	public Room(String description) 
	{
		this.description = description;
		exits = new HashMap<String, Room>();
		items = new ArrayList<Item>();
		directionLocked ="none";
		finalRoom = false;
	}

	/**
	 * Define an exit from this room.
	 * @param direction The direction of the exit.
	 * @param neighbor  The room to which the exit leads.
	 */
	public void setExit(String direction, Room neighbor) 
	{
		exits.put(direction, neighbor);
	} 

	/**
	 * 
	 * @return ArrayList of Items in the room
	 */
	public ArrayList<Item> getItems(){
		return items;
	}

	/**
	 * @return The short description of the room
	 * (the one that was defined in the constructor).
	 */
	public String getShortDescription()
	{
		return description;
	}

	/**
	 * Return a description of the room in the form:
	 *     You are in the kitchen.
	 *     Exits: north west
	 * @return A long description of this room
	 */
	public String getLongDescription()
	{
		String charactersMessage;
		if (roomCharacter==null){
			charactersMessage = "There is no one here!"+  "\n";
		}
		else
		{
			charactersMessage = "Watch out there is a " + roomCharacter +  " here.\n";
		}
		return "You are " + description + ".\n"  + charactersMessage + getRoomItems()+ ".\n" + getExitString();
	}

	/**
	 * Return a string describing the room's exits, for example
	 * "Exits: north west".
	 * @return Details of the room's exits.
	 */
	private String getExitString()
	{
		String returnString = "Exits:";
		Set<String> keys = exits.keySet();
		for(String exit : keys) {
			returnString += " " + exit;
		}
		return returnString;
	}

	/**
	 * Return the room that is reached if we go from this room in direction
	 * "direction". If there is no room in that direction, return null.
	 * @param direction The exit's direction.
	 * @return The room in the given direction.
	 */
	public Room getExit(String direction) 
	{
		return exits.get(direction);
	}

	public void setDirectionLocked(String directionLocked) {
		this.directionLocked = directionLocked;
	}

	/**
	 * find out which direction if any is locked
	 * @return direction locked, if no direction is locked String will be "false"
	 */
	public String getDirectionLocked() {
		return directionLocked;
	}

	/**
	 *  Add Items to Room
	 *  @param item is an item
	 */
	public void putInRoom(Item item)
	{
		items.add(item);
	}

	/**
	 * @return a string of the items contained in the room
	 */
	public String getRoomItems()
	{
		String returnString = "This room contains: ";
		if ((items.size())>0){
			for (Item item : items) {
				returnString += item + ", ";  
			}
		}
		else { returnString = "There are no items in this room"; }
		return returnString;
	}

	/**
	 * setRoomCharacter
	 * @param name (String)
	 */
	public void setRoomCharacter(String name){
		roomCharacter = new Character(name);
	}

	/**
	 * add a room character
	 * @param character (Character)
	 */
	public void addRoomCharacter(Character character){
		roomCharacter = character;
	}

	/**
	 * 
	 * @return room Character's Name
	 */
	public String getRoomCharacterName(){
		return roomCharacter.getName();
	}

	/**
	 * get response of room character
	 * @return String
	 */
	public String getRoomCharacterResponse(){
		if (roomCharacter==null){
			return "There is no one here to talk to!";
		}
		else
		{
			return roomCharacter.getResponse();
		}
	}

	/**
	 * @return the roomCharacter
	 */
	public Character getRoomCharacter() {
		return roomCharacter;
	}

	/**
	 * @return the itemToUnlock
	 */
	public Item getItemToUnlock() {
		return itemToUnlock;
	}

	/**
	 * @param itemToUnlock the itemToUnlock to set
	 */
	public void setItemToUnlock(Item itemToUnlock) {
		this.itemToUnlock = itemToUnlock;
	}

	/**
	 * @return the finalRoom
	 */
	public Boolean getFinalRoom() {
		return finalRoom;
	}

	/**
	 * @param finalRoom the finalRoom to set
	 */
	public void setFinalRoom(Boolean finalRoom) {
		this.finalRoom = finalRoom;
	}




}

