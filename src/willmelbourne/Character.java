package willmelbourne;
import java.util.ArrayList;
import java.util.Random;
/**
 * Write a description of class Character here.
 * 
 * @author William Melbourne 
 * @version 1.01
 */
public class Character
{
	// instance variables - replace the example below with your own
	private String name;
	private ArrayList<String> responses;
	private Item holds;
	private Item needs;
	//private String response;


	/**
	 * Constructor for objects of class Character
	 */
	public Character(String name)
	{
		// initialise instance variables
		responses = new ArrayList<String>();
		this.name = name;
		//this.response = response;
	}

	/**
	 *  Add Item to Collection
	 *  @param item is an item
	 */
	public void addResponse(String response)
	{
		responses.add(response);
	}

	/**
	 * get name
	 * @return name
	 */
	public String getName(){
		return name;
	}

	/**
	 * get character's response
	 * @return character's reponse
	 */
	public String getResponse(){
		Random randomGenerator = new Random();
		String response = responses.get(randomGenerator.nextInt(responses.size()));
		return response;
	}

	/**
	 *  returns a string with the parameters
	 */
	public String toString()
	{
		return name;
	}

	/**
	 * @return the holds
	 */
	public Item getHolds() {
		return holds;
	}

	/**
	 * @param holds the holds to set
	 */
	public void setHolds(Item holds) {
		this.holds = holds;
	}

	/**
	 * @return the needs
	 */
	public Item getNeeds() {
		return needs;
	}

	/**
	 * @param needs the needs to set
	 */
	public void setNeeds(Item needs) {
		this.needs = needs;
	}

}
