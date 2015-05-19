import java.awt.Graphics2D;
import java.awt.Image;
import java.util.ArrayList;


//this class will act as the parent class for all character objects
public class Character 
{
	//the main variables
	int int_x;
	int int_y;
	int int_width;
	int int_height;
	
	//the integer representing the player's horizontal speed
	int int_xSpeed;
	
	//the specific images for the characters
	ArrayList <Image> walkRight = new ArrayList<Image>();
	ArrayList <Image> walkLeft = new ArrayList<Image>();
	ArrayList <Image> stand = new ArrayList<Image>();
	ArrayList <Image> fall = new ArrayList<Image>();
	
	//the bullet - specific information
	int bullet_width = 5;
	int bullet_height = 5;
	int bullet_power = 2;
	Image img_bullet = null;
	
	//the name of the character
	String name;
	
	//the active sprite
	Image activeSprite;
	
	//the jumping/ gravity variables
	boolean isJumping;
	boolean isFalling;
	
	final double acceleraterBase = 1.1;
	double accellerator = acceleraterBase;
	
	//the animations integers
	int int_animateRight = 0;
	int int_animateLeft = 0;
	
	//a pair of booleans identifying the direction the player is facing
	boolean isRight = false;
	boolean isLeft = false;
	
	//the paint method
	public void paint(Graphics2D g)
	{
		//draw the character's sprite reletive to the screen
		g.drawImage(activeSprite,int_x,int_y,null);
	}
	
	//the move right function
	public void moveRight()
	{
		int_x += int_xSpeed;
	}
	
	//the move left function
	public void moveLeft()
	{
		int_x -= int_xSpeed;
	}
	
	//the fall function
	public void fall()
	{
		//lower the character
		int_y += accellerator;
		accellerator *= acceleraterBase;
	}
	
	//the jumping function (opposite of falling)
	public void jump()
	{
		int_y -= accellerator;
		accellerator /= acceleraterBase;
		
		if(accellerator <= 1)
		{
			isJumping = false;
		}
	}
	
	//the right walking animation
	public void animateRight()
	{
		int_animateRight += 1;
		
		activeSprite = walkRight.get(int_animateRight);
		
		if(int_animateRight >= walkRight.size() - 1)
		{
			int_animateRight = 0;
		}
	}
	
	//the left walking animation
	public void animateLeft()
	{
		int_animateLeft += 1;
		
		activeSprite = walkLeft.get(int_animateLeft);
		
		if(int_animateLeft >= walkLeft.size() - 1)
		{
			int_animateLeft = 0;
		}
	}
}
