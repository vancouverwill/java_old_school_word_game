package willmelbourne;
import java.util.ArrayList;
/**
 * Write a description of class ItemCollection here.
 * 
 * @author William Melbourne 
 * @version 1.01
 */
public class ItemCollection
{
	// instance variables - replace the example below with your own
	private ArrayList<Item> collectedItems;
	private double capacity;
	private double currentLoad;

	/**
	 * Constructor for objects of class ItemCollection
	 */
	public ItemCollection(double capacity, double intialLoad)
	{
		// initialise instance variables
		this.capacity = capacity;
		this.currentLoad = intialLoad;
		collectedItems = new ArrayList<Item>();
	}

	public ArrayList<Item> getCollectedItems(){
		return collectedItems;
	}

	/**
	 *  Add Item to Collection
	 *  @param item is an item
	 */
	public boolean putInCollection(Item item)
	{
		if((capacity-currentLoad)>=item.getWeight()){
			collectedItems.add(item);
			currentLoad += item.getWeight();
			return true;
		}
		else
		{
			return false;
		}
	}

	/**
	 *  Remove Item from Collection
	 *  @param item is an item
	 */
	public void removeFromCollection(Item item)
	{
		collectedItems.remove(item);
		currentLoad -= item.getWeight();
	}
	
	/**
	 * reduce current load
	 *  @param item is an item
	 */
	public void reduceCurrentLoad(Item item)
	{	
		currentLoad -= item.getWeight();
	}
	
	/**
	 * get collections of items
	 * @return item collection
	 */
	public String getCollectionItems()
	{
		String returnString = "";
		if ((collectedItems.size())>0){
			for (Item item : collectedItems) {
				returnString += item.toString() + ", ";  
			}
		}
		else { returnString = "nothing"; }
		return returnString;
	}




}
