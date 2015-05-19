import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import MouseBounds.mouseBounds;

import Collision.collision;


//the single player game screen
public class soloPlayScreen extends Screen 
{
	//the concept for this screen, is to have screen objects which are created locally, so as to share global variables
	//and have screens passed that relate to the game screen seemlessly
	
	Screen activeScreen;
	
	//the main constructor
	public soloPlayScreen()
	{
		activeScreen = new characterSelect();
	}
	
	//main screen methods
	public void paint(Graphics2D g2d)
	{
		activeScreen.paint(g2d);
	}
	
	//the mouse input method templates
	public void mouseMoved(int mX,int mY)
	{
		activeScreen.mouseMoved(mX, mY);
	}
	
	//mouse clicked template
	public void mouseClicked(int mX, int mY)
	{
		activeScreen.mouseClicked(mX, mY);
	}
	
	//the mouse pressed function
	public void mousePressed(int mX, int mY)
	{
		activeScreen.mousePressed(mX, mY);
	}
	
	//gameloop method, where all the important code goes
	public void mainLoop()
	{
		activeScreen.mainLoop();
	}
	
	//the key pressed method
	public void keyDown(KeyEvent e)
	{
		activeScreen.keyDown(e);
	}
	
	//the key released method
	public void keyUp(KeyEvent e)
	{
		activeScreen.keyUp(e);
	}
	
	//ATTENTION! here I am declaring the individual required screens
	
	//the character choosing screen
	public class characterSelect extends Screen
	{
		
		//main variables
		ArrayList <Character> characters = new ArrayList<Character>();
		
		int int_currentChar = 0;
		
		//the buttons on the screen
		JAOButton buttons[] = 
		{
			new JAOButton(190,325,50,50,"<"), //next character button
			new JAOButton(570,325,50,50,">"), //previous character button
			new JAOButton((Main.main_width - 100) / 2, 385, 100,50, "Play!") //continue button
		};
		
		public characterSelect()
		{
			characters.add(new diceMan(400,200));
		}
		
		//main screen methods
		public void paint(Graphics2D g2d)
		{
			//draw the background for the character selections scree (default black)
			g2d.setColor(Color.black);
			g2d.fillRect(0, 0, Main.main_width, Main.main_height);
			
			//draw the title image
			g2d.drawImage(imageCache.titleSprites[2],150,10,null);
			
			//run through the buttons and draw them
			for(int i = 0; i < buttons.length; i++)
			{
				buttons[i].paint(g2d);
			}
			
			//set the font
			g2d.setFont(new Font("Hurry Up", 0, 30));
			
			//draw the string identifying the current charactar selection
			g2d.setColor(Color.white);
			g2d.drawString(characters.get(int_currentChar).name,(Main.main_width - g2d.getFontMetrics(new Font("Hurry Up", 0, 30)).stringWidth(characters.get(int_currentChar).name)) / 2,360);
			
			//draw the image for the character above the name - need to get the image width
			g2d.drawImage(characters.get(int_currentChar).stand.get(0),(Main.main_width - characters.get(int_currentChar).int_width) / 2, 250, null);
		}
		
		//the mouse input method templates
		public void mouseMoved(int mX,int mY)
		{
			//run through the buttons
			for(int i = 0; i < buttons.length; i++)
			{
				buttons[i].mouseOver(mX, mY);
			}
		}
		
		//mouse clicked template
		public void mouseClicked(int mX, int mY)
		{
			//run through the buttons
			for(int i = 0; i < buttons.length; i++)
			{
				//check for mouse collisions
				if(mouseBounds.mouseCollide(mX, mY, buttons[i].int_x, buttons[i].int_y, buttons[i].int_width, buttons[i].int_height))
				{
					//check the name of the buttons
					if(buttons[i].str_name.equals("<")) //proceed backwards
					{
						if(int_currentChar > 0) //check the integer
						{
							int_currentChar -= 1; //if possible, move back one
						}
					}
					else if(buttons[i].str_name.equals(">")) //proceed forewards
					{
						if(int_currentChar < (characters.size() - 1)) //check the integer
						{
							int_currentChar += 1; //if possible, move foreward one
						}
					}
					else if(buttons[i].str_name.equals("Play!")) //check if the play button has been clicked
					{
						soloGame tempGame = new soloGame(characters.get(int_currentChar));
						
						//file
				        File toOpen = null;
				        
				        //create a file chooser
				        JFileChooser chooser = new JFileChooser();
				        
				        //set the directory to the maps
				        chooser.setCurrentDirectory(new File("Resources/Map Cache/"));
				        
				        //get a value based on the return of the file chooser
				        int returnVal = chooser.showOpenDialog(chooser);
				        
				        //if the file chooser returns "accept"
				        if(returnVal == JFileChooser.APPROVE_OPTION)
				        {
				        	//set the file to be opened "read"
				            toOpen = chooser.getSelectedFile();
				        }
				        
				        //try and read the file
				        try
				        {
				        	//create a buffer reader
				        	BufferedReader br = new BufferedReader(new FileReader(toOpen));
				        	
				        	//read the name of the project
				        	br.readLine();
				        	
				        	//read the number of blocks to iterate through
				        	int int_blocks = Integer.parseInt(br.readLine());
				        	
				        	System.out.println(int_blocks);
				        	
				    		//create a new block array
				    		tempGame.blocks = new ArrayList<block>();
				        	
				        	//loop through the number of blocks and perform the following reads
				        	for(int j = 0; j < int_blocks; j++)
				        	{
				        		//set the blocks type
				        		String type = br.readLine();
				        		
				        		//set the block location
				        		int x = Integer.parseInt(br.readLine());
				        		int y = Integer.parseInt(br.readLine());

				        		//set a new block
				        		if(type.equals("dirt"))
				        		{
				        			tempGame.blocks.add(new dirtBlock(x, y));
				        		}
				        		else if(type.equals("ice"))
				        		{
				        			tempGame.blocks.add(new iceBlock(x,y));
				        		}
				        		else if(type.equals("steel"))
				        		{
				        			tempGame.blocks.add(new steelBlock(x,y));
				        		}
				        		else if(type.equals("fire"))
				        		{
				        			tempGame.blocks.add(new fireBlock(x,y));
				        		}
				        	}
				        }
				        catch(Exception e)
				        {
				        	System.out.println(e);
				        }
				        
				        activeScreen = tempGame;
						
					}
				}
			}
		}
		
		//gameloop method, where all the important code goes
		public void mainLoop()
		{
			//run through the buttons
			for(int  i = 0; i < buttons.length; i++)
			{
				//perform their regular actions
				buttons[i].mainLoop();
			}
		}
		
		//the key pressed method
		public void keyDown(KeyEvent e)
		{
		}
		
		//the key released method
		public void keyUp(KeyEvent e)
		{
		}
	}
	
	//the paused screen
	public class paused extends Screen
	{
		
	}
	
	//this is the main gameplay screen!!!!
	
	class soloGame extends Screen
	{
		//main screen variables
		
		//the reference integers
		int refX = 400;
		int refY = 200;
		
		//the arraylist of blocks
		ArrayList <block> blocks = new ArrayList<block>();
		
		//the arraylist of blocks that are currently on screen
		ArrayList <block> collideableBlocks = new ArrayList<block>();
		
		//the arraylist for the bullets
		ArrayList <Bullet> bullets = new ArrayList<Bullet>();
		
		//the key booleans, identifying the state of the keys
		boolean upArrow = false;
		boolean downArrow = false;
		boolean leftArrow = false;
		boolean rightArrow = false;
		
		//the list of booleans allowing the player to move
		boolean rightable = true;
		boolean leftable = true;
		
		//the player object
		Character Player;
		
		//this counter will slow down th animations, which are currently running 100 / second
		int int_animationCounter = 0;
		
		//the list of buttons
		JAOButton buttons[] = 
		{
		};
		
		//the default constructor
		public soloGame(Character player)
		{
			//set the character from the one chosen in the previous screen
			Player = player;
		}
		
		//main screen methods
		public void paint(Graphics2D g2d)
		{
			//set color to black for the background
			g2d.setColor(Color.black);
			
			//draw the background
			g2d.fillRect(0,0,Main.main_width,Main.main_height);
			
			//draw the blocks
			for(int i = 0; i < blocks.size(); i++)
			{
				g2d.drawImage(imageCache.blockSprites[blocks.get(i).int_imgID],refX + blocks.get(i).int_x, refY + blocks.get(i).int_y, null);
			}
			
			g2d.setColor(Color.orange);
			
			//draw any bullets
			for(int i = 0; i < bullets.size(); i++)
			{
				if(refX + bullets.get(i).int_x > 0 && refX + bullets.get(i).int_x + bullets.get(i).int_width < Main.main_width && refY + bullets.get(i).int_y > 0 && refY + bullets.get(i).int_y + bullets.get(i).int_height < Main.main_height)
				{
	
					//translate and rotate the screen matrix
					g2d.translate(refX + bullets.get(i).int_x + (bullets.get(i).int_width / 2), refY + bullets.get(i).int_y + (bullets.get(i).int_height / 2));
					g2d.rotate(bullets.get(i).int_angle);
					g2d.translate(-(refX + bullets.get(i).int_x + (bullets.get(i).int_width / 2)), -(refY + bullets.get(i).int_y + (bullets.get(i).int_height / 2)));
					
					//draw the image
					g2d.drawImage(Player.img_bullet,refX + bullets.get(i).int_x, refY + bullets.get(i).int_y,null);
					
					//translate, then rotate the screen matrix back
					g2d.translate(refX + bullets.get(i).int_x + (bullets.get(i).int_width / 2), refY + bullets.get(i).int_y + (bullets.get(i).int_height / 2));
					g2d.rotate(-(bullets.get(i).int_angle));
					g2d.translate(-(refX + bullets.get(i).int_x + (bullets.get(i).int_width / 2)), -(refY + bullets.get(i).int_y + (bullets.get(i).int_height / 2)));
				}
			}
			
			//draw the player
			Player.paint(g2d);
		}
		
		//the mouse input method templates
		public void mouseMoved(int mX,int mY)
		{
			//run through the button actions
			for(int i = 0; i < buttons.length; i++)
			{
				//perform the respective button's actions
				buttons[i].mouseOver(mX, mY);
			}
		}
		
		//mouse clicked template
		public void mouseClicked(int mX, int mY)
		{
			//run through the buttons
			for(int i = 0; i < buttons.length; i++)
			{
				//check for mouse collisions
				if(mouseBounds.mouseCollide(mX, mY, buttons[i].int_x, buttons[i].int_y, buttons[i].int_width, buttons[i].int_height))
				{
				
				}
			}
		}
		
		//gameloop method, where all the important code goes
		public void mainLoop()
		{
			//run through the button actions
			for(int i = 0; i < buttons.length; i++)
			{
				//perform the respective button actions
				buttons[i].mainLoop();
			}
			
			//run the move function
			
			move();

			projectiles();
		}
		
		//the key pressed method
		public void keyDown(KeyEvent e)
		{
			//check the keys when pressed, determine pressed key, initiate action
			if(e.getKeyCode() == 65)
			{
				//set the left arrow to true
				leftArrow = true;
			}
			else if(e.getKeyCode() == 32)
			{
				//set the up arrow to true
				upArrow = true;
			}
			else if(e.getKeyCode() == 68)
			{
				//set the right arrow to true
				rightArrow = true;
			}
			else if(e.getKeyCode() == 40)
			{
				//set the down arrow to true
				downArrow = true;
			}
		}
		
		//this method will check to see if bullets have collided with the player, or with a block
		public void projectiles()
		{
			//check to see if a bullet has collided with the platforms
			for(int i = 0; i < bullets.size(); i++)
			{
				//run through the platforms
				for(int j = 0; j < collideableBlocks.size(); j++)
				{
					
				}
			}
			
			//run through on screen bullets
			for(int i = 0; i < bullets.size(); i++)
			{
				bullets.get(i).move();
			}
		}
		
		//the move method. control the player's movement
		public void move()
		{
			
			if(Player.isJumping == true)
			{
				Player.isFalling = false;
			}
			else
			{
				Player.isFalling = true;
			}
			
			//a temporary blocks which is the up/down block the player has collided or right / left
			block topCollided = null;
			block rightCollided = null;
			block leftCollided = null;
			block bottomCollided = null;
			
			//determine whether or not the player can proceed downward, via collisions
			for(int i = 0; i < collideableBlocks.size(); i++)
			{
				//check if a block is directly underneath a player; this is a very long if statement separated by line
				if(refX + collideableBlocks.get(i).int_x > Player.int_x && refX + collideableBlocks.get(i).int_x < Player.int_x + Player.int_width || 
						refX + collideableBlocks.get(i).int_x + collideableBlocks.get(i).int_width > Player.int_x && refX + collideableBlocks.get(i).int_x + collideableBlocks.get(i).int_width < Player.int_x + Player.int_width || 
							Player.int_x > refX + collideableBlocks.get(i).int_x && Player.int_x < refX + collideableBlocks.get(i).int_x + collideableBlocks.get(i).int_width ||
								Player.int_x + Player.int_width > refX + collideableBlocks.get(i).int_x && Player.int_x + Player.int_width < refX + collideableBlocks.get(i).int_x + collideableBlocks.get(i).int_width) 
				{

					if((refY + collideableBlocks.get(i).int_y) - (Player.int_y + Player.int_height) < 0)
					{
						continue;
					}
					else if((refY + collideableBlocks.get(i).int_y) - (Player.int_y + Player.int_height) <= Player.accellerator)
					{
						//check first if a block is solid
						if(collideableBlocks.get(i).isSolid == true)
						{
							//stop the player
							Player.isFalling = false;
							
							//set the player's location
							topCollided = collideableBlocks.get(i);
						}
					}
				}
			}
				
			//check left - right collisions
			for(int i = 0; i < collideableBlocks.size(); i++)
			{
				//check if a collision has happened
				if(collision.checkCollide(Player.int_x, Player.int_y, Player.int_width, Player.int_height,
					refX + collideableBlocks.get(i).int_x, refY + collideableBlocks.get(i).int_y, collideableBlocks.get(i).int_width, collideableBlocks.get(i).int_height))
				{
					//check first if a block is solid
					if(collideableBlocks.get(i).isSolid == true)
					{
						if(Player.isJumping == true)
						{
							if(collision.checkLocationBottom(Player.int_x, Player.int_y, Player.int_width, Player.int_height,
									refX + collideableBlocks.get(i).int_x, refY + collideableBlocks.get(i).int_y, collideableBlocks.get(i).int_width, collideableBlocks.get(i).int_height))
							{
								Player.isFalling = true;
								Player.isJumping = false;
								Player.accellerator = Player.acceleraterBase;
							}
						
							if(collision.checkLocationLeft(Player.int_x - 1, Player.int_y, Player.int_width + 2, Player.int_height,
									refX + collideableBlocks.get(i).int_x, refY + collideableBlocks.get(i).int_y, collideableBlocks.get(i).int_width, collideableBlocks.get(i).int_height))
							{
								rightable = false;
								rightCollided = collideableBlocks.get(i);
							}
							
							if(collision.checkLocationRight(Player.int_x - 1, Player.int_y, Player.int_width + 2, Player.int_height,
									refX + collideableBlocks.get(i).int_x, refY + collideableBlocks.get(i).int_y, collideableBlocks.get(i).int_width, collideableBlocks.get(i).int_height))
							{
								leftable = false;
								leftCollided = collideableBlocks.get(i);
							}
						}
						else
						{
							if(collision.checkLocationLeft(Player.int_x - 1, Player.int_y, Player.int_width + 2, Player.int_height,
									refX + collideableBlocks.get(i).int_x, refY + collideableBlocks.get(i).int_y, collideableBlocks.get(i).int_width, collideableBlocks.get(i).int_height))
							{
								rightable = false;
								rightCollided = collideableBlocks.get(i);
							}
							
							if(collision.checkLocationRight(Player.int_x - 1, Player.int_y, Player.int_width + 2, Player.int_height,
									refX + collideableBlocks.get(i).int_x, refY + collideableBlocks.get(i).int_y, collideableBlocks.get(i).int_width, collideableBlocks.get(i).int_height))
							{
								leftable = false;
								leftCollided = collideableBlocks.get(i);
							}
						}
					}
				}
			} 
			
			//check to see if the player has jumped
			if(upArrow == true && Player.isJumping != true && Player.isFalling != true)
			{
				Player.isJumping = true;
				Player.accellerator = 10;
			}
			
			//check if the player has stopped falling
			if(Player.isFalling == true)
			{
				if(Player.int_y + Player.int_height < Main.main_height - 100)
				{
					Player.fall();
				}
				else
				{
					refY -= Player.accellerator;
					
					//provide the accellerator with a terminal velocity
					if(Player.accellerator < 15)
					{
						Player.accellerator *= Player.acceleraterBase;
					}
				}
			}
			else if(Player.isJumping == true)
			{
				if(Player.int_y > 100)
				{
					Player.jump();
				}
				else
				{
					refY += Player.accellerator;
					Player.accellerator /= Player.acceleraterBase;
					
					if(Player.accellerator <= 1)
					{
						Player.isJumping = false;
					} 
				}
			}
			else
			{
				//set the player's location
				Player.int_y = refY + topCollided.int_y - Player.int_height;
				//reset the player's gravity modifier
				Player.accellerator = Player.acceleraterBase;
			}

			//activate the player's walk ability if able
			if(rightArrow == true && leftArrow == false && rightable == true)
			{
				//if the player is within the move boundaries, move him
				if(Player.int_x < 700)
				{
					//move the player
					Player.moveRight();
				}
				else
				{
					refX -= Player.int_xSpeed;
				}
				
				Player.isRight = true;
				Player.isLeft = false;
			}
			
			if(leftArrow == true && rightArrow == false && leftable == true)
			{
				//if the player is within the move bounds, move him
				if(Player.int_x > 100)
				{
					Player.moveLeft();
				}
				else
				{
					refX += Player.int_xSpeed;
				}
				
				Player.isLeft = true;
				Player.isRight = false;
			}
			
			//animate the player
			
			if(Player.isFalling == true)
			{
				if(Player.isLeft == true)
				{
					Player.activeSprite = Player.fall.get(0);
				}
				else if(Player.isRight == true)
				{
					Player.activeSprite = Player.fall.get(1);
				}
			}
			else if(Player.isJumping == true)
			{
				if(Player.isLeft == true)
				{
					
				}
				else if(Player.isRight == true)
				{
					
				}
			}
			else if(leftArrow == true && rightArrow == false && leftable == true)
			{
				if(int_animationCounter < Player.int_xSpeed)
				{
					int_animationCounter += 1;
				}
				else
				{
					Player.animateLeft();
					
					int_animationCounter = 0;
				}
			}
			else if(rightArrow == true && leftArrow == false && rightable == true)
			{
				
				if(int_animationCounter < Player.int_xSpeed)
				{
					int_animationCounter += 1;
				}
				else
				{
					Player.animateRight();
					
					int_animationCounter = 0;
				}
			}
			else
			{
				if(Player.isRight == true)
				{
					Player.activeSprite = Player.stand.get(1);
				}
				else if(Player.isLeft == true)
				{
					Player.activeSprite = Player.stand.get(0);
				}
			}
			
			//optimize the arraylist of collideable blocks
			collisionsOptimization();
			
			rightable = true;
			leftable = true;
		}
		
		//this method should optimize block collisions, by determining which blocks are on screen.
		public void collisionsOptimization()
		{
			//clear the optimization array
			collideableBlocks.removeAll(collideableBlocks);
			
			//run through the blocks, determine if they are on screen, if so, put them into the array to be collided with.
			for(int i = 0; i < blocks.size(); i++)
			{
				if(refX + blocks.get(i).int_x > 0 && refX + blocks.get(i).int_x + blocks.get(i).int_width < Main.main_width && refY + blocks.get(i).int_y > 0 && refY + blocks.get(i).int_y + blocks.get(i).int_height < Main.main_height)
				{
					collideableBlocks.add(blocks.get(i));
				}
			}
		}
		
		//the mouse input event
		public void mousePressed(int mX, int mY)
		{
			
			double divisor = (double)Math.sqrt(Math.pow(mX - Player.int_x, 2) + Math.pow(mY - Player.int_y,2));
			double slopeX = (mX - (double)Player.int_x) / divisor;
			double slopeY = (mY - (double)Player.int_y) / divisor;
			
			double angle = 0;
			
			//setup the angle based on slopes
			//if quadrant one
			if(mX > Player.int_x + (Player.int_width / 2) && mY < Player.int_y + (Player.int_height / 2))
			{
				angle = Math.atan((double)(Player.int_y + (Player.int_height / 2) - mY) / (mX - Player.int_x + (Player.int_width / 2)));
			}
			//if quadrant 2
			else if(mX > Player.int_x + (Player.int_width / 2) && mY > Player.int_y + (Player.int_height / 2))
			{
				angle = Math.atan((double)(Player.int_y + (Player.int_height / 2) - mY) / (mX - Player.int_x + (Player.int_width / 2)));
			}
			//if quadrant 3
			else if(mX < Player.int_x + (Player.int_width / 2) && mY > Player.int_y + (Player.int_height / 2))
			{
				angle = Math.toRadians(180) + Math.atan((double)(Player.int_y + (Player.int_height / 2) - mY) / (mX - Player.int_x + (Player.int_width / 2)));
			}
			//if quadrant 4
			else if(mX < Player.int_x + (Player.int_width / 2) && mY < Player.int_y + (Player.int_height / 2))
			{
				angle = Math.toRadians(180) + Math.atan((double)(Player.int_y + (Player.int_height / 2) - mY) / (mX - Player.int_x + (Player.int_width / 2)));
			}
			
			//angle *= (180/Math.PI);
			angle = Math.toRadians(90) - angle;
			//System.out.println(Math.toDegrees(angle));
			
			bullets.add(new Bullet(Player.int_x + (Player.int_width / 2) - refX,Player.int_y + (Player.int_height / 2) - refY, Player.bullet_width, Player.bullet_height, slopeX * 7, slopeY * 7, Player.bullet_power, angle));
		}
		
		//the key released method
		public void keyUp(KeyEvent e)
		{
			//check the keys when pressed, determine pressed key, initiate action
			
			if(e.getKeyCode() == 65)
			{
				//set the left arrow to true
				leftArrow = false;
			}
			else if(e.getKeyCode() == 32)
			{
				//set the up arrow to true
				upArrow = false;
			}
			else if(e.getKeyCode() == 68)
			{
				//set the right arrow to true
				rightArrow = false;
			}
			else if(e.getKeyCode() == 40)
			{
				//set the down arrow to true
				downArrow = false;
			}
		}
	}
}
