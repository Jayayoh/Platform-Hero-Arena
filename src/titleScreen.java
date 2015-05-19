import java.awt.Color;
import java.awt.Image;

import Collision.collision;
import MouseBounds.mouseBounds;
import java.awt.Graphics2D;


//this class is the subclass of screen that represents the title screen
class titleScreen extends Screen
{
	//main variables
	
	//the array of buttons
	JAOButton[] buttons = {
			new JAOButton((Main.main_width - 100) / 2,300,115,40,"Single player"), //single player button
			new JAOButton((Main.main_width - 100) / 2, 350,115,40,"Level Editor") //level editor button
	};

	//constructor
	public titleScreen()
	{
		
	}
	
	//the main titlescreen loop
	public void mainLoop()
	{
		//loop through the buttons, perform actions
		for(int i = 0; i < buttons.length; i++)
		{
			//perform button animations
			buttons[i].mainLoop();
		}
	}
	
	//the mouse moved method
	public void mouseMoved(int mX, int mY)
	{
		//loop through the buttons
		for(int i = 0; i < buttons.length; i++)
		{
			//perform the seperate button's mouseOVer function
			buttons[i].mouseOver(mX,mY);
		}
	}
	
	//the mouse clicked method
	@Override
	public void mouseClicked(int mX, int mY)
	{
		//run through the buttons, perform actions etc
		for(int i = 0; i < buttons.length; i++)
		{
			//check the buttons
			if(mouseBounds.mouseCollide(mX,mY,buttons[i].int_x,buttons[i].int_y,buttons[i].int_width,buttons[i].int_height))
			{
				//loop through the buttons
				if(buttons[i].str_name.equals("Level Editor"))
				{
					//change the screen to the level editor
					Main.mainScreen = new editorScreen();
				}
				else if(buttons[i].str_name.equals("Single player"))
				{
					Main.mainScreen = new soloPlayScreen();
				}
			}
		}
	}
	
	//painting method
	@Override
	public void paint(Graphics2D g2d)
	{
		//set the background color to red
		g2d.setColor(Color.black);
		
		//draw the background
		g2d.fillRect(0,0,Main.main_width,Main.main_height);
		
		//draw the button
		for(int i = 0; i < buttons.length; i++)
		{
			//draw the buttons through the loop
			buttons[i].paint(g2d);
		}
		
		//draw the main titl;e
		g2d.drawImage(imageCache.titleSprites[0],100,50,null);
	}
	
}
