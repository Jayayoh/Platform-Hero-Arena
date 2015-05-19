import java.awt.Graphics2D;
import java.awt.Image;


//dice man will be one of the playable characters.
public class diceMan extends Character
{
	
	//diceman will be a ranged character - he will shoot bullets
	
	//the default constructor
	public diceMan(int x, int y)
	{
		int_x = x; //set the x value
		int_y = y; //set the y value
		int_width = 34; //set the width as predefined by the image value
		int_height = 42; //set the height, as predefined by the image value
		
		//set the standing / falling images
		for(int i = 0; i < 2; i++)
		{
			fall.add(imageCache.diceMan[i]);
		}
		
		for(int i = 2; i < 4; i++)
		{
			stand.add(imageCache.diceMan[i]);
		}
		
		//set the walking left images
		for(int i = 4; i < 9; i++)
		{
			walkLeft.add(imageCache.diceMan[i]);
		}
		
		//set the walking right images
		for(int i = 9; i < 14; i++)
		{
			walkRight.add(imageCache.diceMan[i]);
		}
		
		//set the character's name
		name = "Dice Man";
		
		//the active image.
		activeSprite = stand.get(1);
		
		//setup the default speed
		int_xSpeed = 4;
		
		//set the bullet image
		img_bullet = imageCache.bulletSprites[0];
	}
}
