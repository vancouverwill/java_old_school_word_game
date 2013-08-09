package willmelbourne;
//import java.util.ArrayList;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author William Melbourne 
 * @version 1.01
 */

public class Game 
{
	private Parser parser;
	private Player gamePlayer;
	private String finished;

	//    private ArrayList<Player> gamePlayers;  -- possible future edition

	/**
	 * Create the game and initialise its internal map.
	 */
	public Game(String playerName) 
	{
		gamePlayer = new Player(playerName);
		setUpGameObjects();
		parser = new Parser();
		
		play();

	}
	
	public static void main(String[] args) throws Exception {
		new Game("will");
	}
	

	/**
	 * Create all the rooms and link their exits together.
	 */
	private void setUpGameObjects()
	{
		Room outside, courtyard, dining, library, dungeon, tower, northernCorridor, 
		northEasternCorridor, easternCorridor, lockedRoom;

		// create the rooms
		outside = new Room("outside the main entrance of the castle");
		courtyard = new Room("in the castle courtyard");
		dining = new Room("in the dining room");
		library = new Room("in a spooky library");
		dungeon = new Room("in the dark dungeon");
		tower = new Room("up in a tower");
		northernCorridor = new Room("in the corridor to the north");
		northEasternCorridor = new Room("in the corridor to the north east");
		easternCorridor = new Room("in the corridor to the east");
		lockedRoom = new Room("in the locked room");

		// initialise room exits
		outside.setExit("north", courtyard);
		courtyard.setExit("south", outside);
		courtyard.setExit("up", tower);
		courtyard.setExit("north", northernCorridor);
		courtyard.setExit("west", dining);
		dining.setExit("east", courtyard);
		dining.setExit("up", library);
		dining.setExit("down", dungeon);
		library.setExit("down", dining);
		dungeon.setExit("up", dining);
		tower.setExit("down", courtyard);
		northernCorridor.setExit("east", northEasternCorridor);
		northernCorridor.setExit("south", courtyard);
		northEasternCorridor.setExit("east", easternCorridor);
		northEasternCorridor.setExit("west", northernCorridor);
		easternCorridor.setExit("west", northEasternCorridor);
		easternCorridor.setExit("south", lockedRoom);
		lockedRoom.setExit("north", easternCorridor);




		Item spinningWheel, sword, coneOfLight, stone, wine, beer, beef, steak, bananna, dictionary, cake, chocolate;
		spinningWheel = new Item("SpinningWheel", 200.00, false);
		stone = new Item("stone", 200.00, false);
		courtyard.putInRoom(spinningWheel);
		courtyard.putInRoom(stone);

		sword = new Item("sword", 3.00, true);
		tower.putInRoom(sword);

		coneOfLight = new Item("Cone of Light",45.00, false);
		northernCorridor.putInRoom(coneOfLight);

		wine = new Item("wine", 1.00, true);
		beer = new Item("beer", 1.00, true);
		beef = new Item("beef", 1.00, true);

		cake = new Item("cake", 1.00, true);
		chocolate = new Item("chocolate", 1.00, true);
		bananna = new Item("Bananna", 1.00, true);
		dining.putInRoom(wine);
		dining.putInRoom(beer);
		dining.putInRoom(beef);
		//dining.putInRoom(steak);
		dining.putInRoom(bananna);
		dining.putInRoom(cake);
		dining.putInRoom(chocolate);

		dictionary = new Item("dictionary", 2.00, true);
		library.putInRoom(dictionary);


		Character oldwoman, oldman, spider, guard;
		oldwoman = new Character("Old woman");
		steak = new Item("steak", 1.00, true);
		oldwoman.addResponse("watch out for the evil spider");
		oldwoman.addResponse("spiders are sometimes sad, sometimes angry but always hungry");
		oldwoman.addResponse("cooking up a hot meal, cooking up a great meal");
		oldwoman.setHolds(steak);
		oldwoman.setNeeds(beef);
		courtyard.addRoomCharacter(oldwoman);

		oldman = new Character("Old man");
		oldman.addResponse("me used to be sumthin big round these parts!");
		oldman.addResponse("trade, trade, trade");
		oldman.addResponse("its all about give and take, eh son?");
		northernCorridor.addRoomCharacter(oldman);

		spider = new Character("spider");
		spider.addResponse("careful there little fellow");
		spider.addResponse("I'm going to bite you if you come any closer!");
		Item key;
		key = new Item("Key",1.00, true);
		spider.setHolds(key);
		spider.setNeeds(steak);
		dungeon.addRoomCharacter(spider);

		guard = new Character("guard");
		guard.addResponse("get out of here before I cut your hands off");
		guard.addResponse("i'm going to get you");
		guard.addResponse("whatttttttttt");
		guard.addResponse("You know what Spiders eat? Little children. hahahahahaha");
		tower.addRoomCharacter(guard);


		easternCorridor.setDirectionLocked("south");
		easternCorridor.setItemToUnlock(key);
		lockedRoom.setFinalRoom(true);
		gamePlayer.setCurrentRoom(outside);  // start game outside
	}

	/**
	 *  Main play routine.  Loops until end of play.
	 */
	public void play() 
	{            
		printWelcome();

		// Enter the main command loop.  Here we repeatedly read commands and
		// execute them until the game is over.

		// intialise finished variable to false, so until otherwise game will continue playing
		finished = "false";
		while ( finished=="false") {
			Command command = parser.getCommand();
			processCommand(command);
		}
		if(finished=="quit"){
			System.out.println();
			System.out.println("Sorry to see you leave so soon. Please come back soon. Good bye.");
		}
		else if(finished=="complete"){
			System.out.println();
			System.out.println("Wow!!!!!!!!!!!!!");
			System.out.println("Congratulations and Thank you for playing.  Good bye.");
		}
	}



	/**
	 * Given a command, process (that is: execute) the command.
	 * @param command The command to be processed.
	 * @return true If the command ends the game, false otherwise.
	 */
	private void processCommand(Command command) 
	{
		String commandWord = command.getCommandWord();
		if(command.isUnknown()) {
			System.out.println("I don't know what you mean...");
			finished = "false";
		}
		else if (commandWord.equals("help")) {
			printHelp();
		}
		else if (commandWord.equals("go")) {
			goRoom(command);
		}
		else if (commandWord.equals("seeItemsInRoom")){
			printItemsInLocation();
		}
		else if (commandWord.equals("seeItemsInCollection")){
			printItemsInCollection();
		}
		else if (commandWord.equals("pickup")){
			pickUpItem(command);
		}
		else if (commandWord.equals("drop")){
			dropItem(command);
		}
		else if (commandWord.equals("look"))
			look();
		else if (commandWord.equals("back")) {
			goBack();
		}
		else if (commandWord.equals("ask")) {
			ask();
		}
		else if (commandWord.equals("offer")) {
			offer();
		}
		else if (commandWord.equals("quit")) {
			finished = quit(command);
		}
	}

	// implementations of user commands:


	/** 
	 * Try to go to one direction. If there is an exit, enter the new
	 * room, otherwise print an error message.
	 */
	private void goRoom(Command command) 
	{
		if(!command.hasSecondWord()) {
			// if there is no second word, we don't know where to go...
			System.out.println("Go where?");
			return;
		}

		String direction = command.getSecondWord();
		String lockedDirection = getCurrentRoom().getDirectionLocked();
		// check if current direction is locked
		if(lockedDirection.equals(direction))
		{
			if(gamePlayer.getBackPack().getCollectedItems().contains(getCurrentRoom().getItemToUnlock())){
				System.out.println("Congratulations you have made it through the locked door.");
				System.out.println();
				leaveRoom(direction);
			}
			else{
				System.out.println("The door is locked!");
				System.out.println();
			}

		}
		else
		{
			leaveRoom(direction);
		}       
	}

	/** 
	 *  leave current room
	 * @param direction
	 */
	private void leaveRoom(String direction){

		// Try to leave current room.
		Room nextRoom = getCurrentRoom().getExit(direction);
		if (nextRoom == null) {
			System.out.println("There is no door!");
		}
		else {
			gamePlayer.addToRoute();
			enterRoom(nextRoom);
		}
	}

	/**
	 * Enters the specified room and prints the description.
	 */
	private void enterRoom(Room nextRoom)
	{
		finished = "false";
		if(nextRoom.getFinalRoom()==true){
			finished = "complete";
		}
		else{

			gamePlayer.setCurrentRoom(nextRoom);
			printLocationInfo();
		}      
	}


	/** 
	 * Takes the player back to the previous room, allowing the player to retrace the route through the game
	 */
	private void goBack()
	{

		if (gamePlayer.getRoute().empty()) {
			System.out.println("You have nowhere to go back to!");
		}
		else {
			Room lastInRoute=gamePlayer.getRoute().pop();
			enterRoom(lastInRoute);
		}
	}

	/**
	 * 
	 * "look" look around to see what exits are available
	 */
	private void look()
	{
		System.out.println(getCurrentRoom().getLongDescription());   
	}

	/** 
	 * "Quit" was entered. Check the rest of the command to see
	 * whether we really quit the game.
	 * @return true, if this command quits the game, false otherwise.
	 */
	private String quit(Command command) 
	{
		if(command.hasSecondWord()) {
			System.out.println("Quit what?");
			return "false";
		}
		else {
			return "quit";  // signal that we want to quit
		}
	}

	/**
	 * Print out the opening message for the player.
	 */
	private void printWelcome()
	{
		System.out.println("----------------------------------------------------------------------------");
		System.out.println("You are a traveler in a strange land.");
		System.out.println("One night while staying at a remote inn you hear tales of a");
		System.out.println("castle in the area where is hidden a beautiful jewel that nobody can find.");
		System.out.println("Being adventurous, you decide to give it a try.");
		System.out.println();
		System.out.println("Type 'help' if you need help.");
		System.out.println();
		printLocationInfo();
		System.out.println();
	}
	

	/**
	 * Print out some help information.
	 * Here we print some stupid, cryptic message and a list of the 
	 * command words.
	 */
	private void printHelp() 
	{
		System.out.println("----------------------------------------------------------------------------");
		System.out.println("You are lost. You are alone. You wander");
		System.out.println("around at the castle grounds.");
		System.out.println();
		System.out.println("Your command words are:");
		System.out.println(parser.showCommands());
	}

	/**
	 * print location info
	 */
	private void printLocationInfo()
	{
		System.out.println("----------------------------------------------------------------------------");
		System.out.println(gamePlayer.getLongDescription());
		System.out.println();
		System.out.println(getCurrentRoom().getLongDescription());
	}

	/**
	 * print items in location
	 */
	private void printItemsInLocation()
	{   
		System.out.println("----------------------------------------------------------------------------");
		System.out.println(getCurrentRoom().getRoomItems());
	}

	/**
	 * print items in collection
	 */
	private void printItemsInCollection()
	{   
		System.out.println("----------------------------------------------------------------------------");
		System.out.println("This collection contains " + gamePlayer.getBackPack().getCollectionItems());
	}
	
	/**
	 * pick up item
	 * @param command from parser
	 */
	private void pickUpItem(Command command){
		if(!command.hasSecondWord()) {
			// if there is no second word, we don't know where to go...

			System.out.println("Pick up what?");
			return;
		}

		String item = command.getSecondWord();

		System.out.println(gamePlayer.pickUpItem(item));
	}

	/**
	 * drop item
	 * @param command from parser
	 */
	private void dropItem(Command command){
		if(!command.hasSecondWord()) {
			// if there is no second word, we don't know where to go...
			System.out.println("Drop what?");
			return;
		}

		String item = command.getSecondWord();
		System.out.println(gamePlayer.dropItem(item));
		//gamePlayer.getBackPack().removeFromCollectionByDescription(item);

	}

	/**
	 * assk room character for information
	 */
	private void ask(){
		String response=getCurrentRoom().getRoomCharacterResponse();
		System.out.println();
		System.out.println("Excuse me. Is there any clues you can give me to help me on my quest?");
		System.out.println();
		System.out.println(response);
	}

	/**
	 * Offer your items for trade with the room character
	 */
	private void offer(){
		String response=gamePlayer.offerCharacter();
		System.out.println();
		System.out.println("Excuse me. Is there any thing in my bag you would like?");
		System.out.println();
		System.out.println(response);
	}
	
	private Room getCurrentRoom(){
		return gamePlayer.getCurrentRoom();
	}
	

}
