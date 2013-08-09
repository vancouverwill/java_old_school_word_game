package willmelbourne;
/**
 * Write a description of class Item here.
 * 
 * @author William Melbourne 
 * @version 1.01
 */
public class Item
{
	// instance variables - replace the example below with your own
	private String description;
	private double weight;
	private boolean canBePickedUp;

	/**
	 * Constructor for objects of class Item
	 */
	public Item(String description, double weight, boolean canBePickedUp)
	{
		// initialise instance variables
		this.description = description;
		this.weight = weight;
		this.canBePickedUp = canBePickedUp;
	}

	/**
	 *  returns a string with the parameters
	 */
	public String toString()
	{
		return description;
	}

	public String getDescription() {
		return description;
	}

	public double getWeight() {
		return weight;
	}

	public boolean getCanBePickedUp() {
		return canBePickedUp;
	}

}
