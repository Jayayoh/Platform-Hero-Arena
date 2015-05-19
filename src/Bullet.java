import java.awt.Color;
import java.awt.Graphics2D;


//this class will contain all of the information a generic bullet requires. It will hold an Array of Images which will be passed as arguments by the character
public class Bullet 
{
	//main variables
	
	//dimensions, locations
	int int_x;
	int int_y;
	int int_height;
	int int_width;
	
	//speeds
	double int_xSpeed;
	double int_ySpeed;
	
	//bullet power
	int int_power;
	
	//the angle of the bullet (to be determined for drawing purposes)
	double int_angle;
	
	//default constructor
	public Bullet(int x, int y, int width, int height,double xspeed, double yspeed, int power, double angle)
	{
		//setup the variables
		int_x = x;
		int_y = y;
		int_width = width;
		int_height = height;
		int_power = power;
		int_xSpeed = xspeed;
		int_ySpeed = yspeed;
		int_angle = angle;
	}
	
	//move the bullets
	public void move()
	{
		//move the bullet
		int_x += int_xSpeed;
		int_y += int_ySpeed;
	}
}
