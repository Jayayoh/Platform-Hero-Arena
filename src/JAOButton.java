import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;
import MouseBounds.mouseBounds;

//JAO Buttons (aptly named John Alexander Omeljaniuk) will be completely primitive based, and very colorful
class JAOButton 
{
	//Main variables
	
	//the location variables
	int int_x,int_y;
	
	//the dimension variables
	int int_width,int_height;
	
	//the main button Color
	Color main_color;
	
	//the color values
	int int_red,int_green,int_blue;
	
	//the main String for the button
	String str_name;
	
	//the main font
	Font main_font;
	
	//the boolean to identify mouse over
	boolean isMouseOver = false;
	
	//the boolean for switching the color change
	boolean colorDirection = false;
	
	//the static color for when a button is mouse overed
	Color static_color;

	//main constructor
	public JAOButton(int x, int y, int width, int height, String name)
	{
		//setup the variables - dimension
		int_x = x;
		int_y = y;
		int_width = width;
		int_height = height;
		
		//setup the variable colors
		int_red = (int)Math.ceil(Math.random() * 255);
		int_blue = (int)Math.ceil(Math.random() * 255);
		int_green = (int)Math.ceil(Math.random() * 255);
		
		//setup the Font
		main_font = new Font("Hurry Up", 0, 18);
		
		//setup the color
		main_color = new Color(int_red,int_green,int_blue);
		
		//setup the string
		str_name = name;
		
		//set the static color
		static_color = new Color(int_blue,int_green,int_red);
	}
	
	//the action methods, for performing actions such as mouse over
	public void mouseOver(int mX, int mY)
	{
		//call the mouse collision method
		if(mouseBounds.mouseCollide(mX, mY, int_x, int_y, int_width, int_height) == true)
		{
			//if the mouse is over the button, trip the switch
			isMouseOver = true;
		}
		else
		{
			//otherwise, trip the switch the other way
			isMouseOver = false;
		}
	}

	//the main loop for the buttons
	public void mainLoop()
	{
		//identify first whether or not the mouse hovers over
				if(isMouseOver == true)
				{
					//change the color on mouse over
					int_red = 255;
					int_green = 255;
					int_blue = 255;
					
					colorDirection = true;
				}
				else
				{
					//change the color by shifting through the RGB values
					if(colorDirection == false)
					{
						if(int_red <= 252)
						{
							int_red += 3;
						}
						else if(int_green <= 252)
						{
							int_green += 3;
						}
						else if(int_blue <= 252)
						{
							int_blue += 3;
							
							colorDirection = true;
						}
					}
					else if(colorDirection == true)
					{
						if(int_red >= 3)
						{
							int_red -= 3;
						}
						else if(int_green >= 3)
						{
							int_green -= 3;
						}
						else if(int_blue >= 3)
						{
							int_blue -= 3;
							
							colorDirection = false;
						}
					}
				}
				
				//setup the main color
				main_color = new Color(int_red,int_green,int_blue);
	}
	
	//main paint function
	public void paint(Graphics2D g)
	{
		//setup the button color
		g.setColor(main_color);
		
		//draw the button outline
		g.drawRect(int_x, int_y, int_width, int_height);
		
		//setup the font
		g.setFont(main_font);
		
		//draw the string on the button
		g.drawString(str_name,int_x  + ((int_width - g.getFontMetrics(main_font).stringWidth(str_name)) / 2),(int_y + int_height) - ((int_height - g.getFontMetrics(main_font).getHeight() + 8)) / 2);
	}
}
