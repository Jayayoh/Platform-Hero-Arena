import java.awt.Image;


//this class will do nothing but hold the images (and load them) that the game will use. all mathods that require images will reference this class
public class imageCache 
{
	//the list of images to be accessed globally - blocks
	static Image blockSprites[] = 
	{
		Main.tool.getImage("Resources/Images/iceBlock1.png"), // the ice block sprite
		Main.tool.getImage("Resources/Images/dirtBlock1.png"), //the dirt block sprite
		Main.tool.getImage("Resources/Images/steelBlock.png"), //the steel block sprite
		Main.tool.getImage("Resources/Images/fireBlock.gif"), //the fire block animated sprite
		
	};
	
	//list of images to be accessed globally - titles
	static Image titleSprites[] = 
	{
		Main.tool.getImage("Resources/Images/mainTitle.png"), //the main screen title
		Main.tool.getImage("Resources/Images/editorTitle.png"), //the editor screen title
		Main.tool.getImage("Resources/Images/charSelTitle.png") //the title image for the character select screen
	};
	
	//the list of images for the characters
	
	//the list of images for dice man
	static Image diceMan[] = 
	{
		Main.tool.getImage("Resources/Images/diceFallLeft.png"), //falling
		Main.tool.getImage("Resources/Images/diceFallRight.png"),
		Main.tool.getImage("Resources/Images/diceStandLeft.png"), //standing
		Main.tool.getImage("Resources/Images/diceStandRight.png"),
		Main.tool.getImage("Resources/Images/diceWalkLeft1.png"), //walking left
		Main.tool.getImage("Resources/Images/diceWalkLeft2.png"),
		Main.tool.getImage("Resources/Images/diceWalkLeft3.png"),
		Main.tool.getImage("Resources/Images/diceWalkLeft4.png"),
		Main.tool.getImage("Resources/Images/diceWalkLeft5.png"),
		Main.tool.getImage("Resources/Images/diceWalkRight1.png"), //walking right
		Main.tool.getImage("Resources/Images/diceWalkRight2.png"),
		Main.tool.getImage("Resources/Images/diceWalkRight3.png"),
		Main.tool.getImage("Resources/Images/diceWalkRight4.png"),
		Main.tool.getImage("Resources/Images/diceWalkRight5.png")
	};
	
	//the list of bullet sprites
	static Image bulletSprites[] = 
	{
		Main.tool.getImage("Resources/Images/bullet1.png") //the firey blue bullet
	};
}
