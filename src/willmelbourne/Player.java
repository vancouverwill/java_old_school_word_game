package willmelbourne;
import java.util.Stack;
import java.util.Iterator;
/**
 * The player class stores information about the player including name, where they are, the route they took and the items they carry.
 * 
 * @author William Melbourne 
 * @version 1.01
 */
public class Player
{
	// instance variables - replace the example below with your own
	private String name;
	private Room currentRoom;
	private Stack<Room> route;
	private ItemCollection backpack;

	/**
	 * Constructor for objects of class Player
	 */
	public Player(String name)
	{
		// initialise instance variables
		this.name = name;
		route = new Stack<Room>();
		backpack = new ItemCollection(5.00, 0.00);
	}

	/**
	 * get current room
	 * @return current room
	 */
	public Room getCurrentRoom(){
		return currentRoom;
	}

	/**
	 * set current room
	 * @param currentRoom
	 */
	public void setCurrentRoom(Room currentRoom){
		this.currentRoom=currentRoom;
	}
	
	/**
	 * add current room to route
	 */
	public void addToRoute(){
		route.push(currentRoom);
	}

	/**
	 * get long description of the player
	 * @return
	 */
	public String getLongDescription(){
		return "Your name is " + name + " and you currently have " +backpack.getCollectionItems() + " in your backpack";
	}

	/**
	 * @return the route
	 */
	public Stack<Room> getRoute() {
		return route;
	}

	/** 
	 * get back pack
	 * @return backpack
	 */
	public ItemCollection getBackPack(){
		return backpack;
	}

	/**
	 * try to pick up item, 
	 * 1.check to see if there are any items in room
	 * 2.check to see if item is in room
	 * 3.check to see if this item can be picked up
	 * 4.check to see if your backpack has space
	 * 5. pick up the item
	 * @param itemToDrop
	 * @return 
	 */
	public String pickUpItem(String itemToDrop)
	{
		String results ="";
		if(currentRoom.getItems().size()==0){
			results = "There is nothing in the room";
		}
		else {
			results = "There is nothing in the room called " + itemToDrop;
			Iterator<Item> it = currentRoom.getItems().iterator();
			Item thisItem = null;
			while(it.hasNext()) {
				thisItem = it.next();
				if(thisItem.getDescription() != null)
				{
					if(thisItem.getDescription().equals(itemToDrop)) 
					{     
						if(!thisItem.getCanBePickedUp()){
							results = "You cannot pick the " + itemToDrop + " up.";
						}
						else if(backpack.putInCollection(thisItem))
						{
								it.remove();
								results = "You picked up the " + itemToDrop + " in the room.";
						}
						else
						{
							results = "Your backpack has no space left for " + itemToDrop;
						}
						
					}
					else{

						//this item is not equal to what you want;
					}
				}else{
					// item has no description
				}
			}

		}
		return results;
	}

	/**
	 * dropItem function drops an item from the players backpack and into the room 
	 * @param   itemToDrop (String) as it is given by the parser 
	 */
	public String dropItem(String itemToDrop){
		String dropItemResults ="";
		Iterator<Item> it = backpack.getCollectedItems().iterator();
		Item thisItem = null;
		while(it.hasNext()) {
			thisItem = it.next();
			if(thisItem.getDescription() != null)
			{
				if(thisItem.getDescription().equals(itemToDrop)) 
				{                
					currentRoom.putInRoom(thisItem);
					backpack.reduceCurrentLoad(thisItem);
					it.remove();
					dropItemResults = "You left the " + itemToDrop + " in the room.";
				}
			}else{
				dropItemResults = "There is nothinge in your bag!";
			}
		}
		return dropItemResults;
	}

	/**
	 * offer character any item from your collection
	 * @return the response from the character
	 */
	public String offerCharacter(){
		if (backpack.getCollectedItems().contains(currentRoom.getRoomCharacter().getNeeds())){
			String characterWants= "I'll have that thankyou very much."; 
			characterWants += dropItem(currentRoom.getRoomCharacter().getNeeds().getDescription());
			currentRoom.putInRoom(currentRoom.getRoomCharacter().getHolds());
			return characterWants;
		}
		else
		{
			return "you don't have anything I need.";
		}

	}
}
