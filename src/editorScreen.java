import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import MouseBounds.mouseBounds;


//this screen will be the level editor, including opening and creating levels
class editorScreen extends Screen
{
	
	//define the boundaries for the drawing screen
	int drawX = 300;
	int drawY = 220;
	int drawWidth = 475;
	int drawHeight = 235;
	
	//the reference integers
	int refX = 0;
	int refY = 0;
	
	//the string representing the current block selection
	String str_selection = "Dirt Block";
	
	//the list of buttons
	JAOButton buttons[] = 
		{
			new JAOButton(50,150,100,40,"Menu"), //menu button
			new JAOButton(175,150,100,40,"Save"), //save button
			new JAOButton(300,150,100,40, "Load"), //load button
			new JAOButton(175,410,100,40, "Ice Block"), //select ice button
			new JAOButton(175,345,100,40, "Steel Block"), //select steel button
			new JAOButton(50,345,100,40, "Dirt Block"), //select dirt button
			new JAOButton(50,410,100,40, "Fire Block"), //select fire button
			new JAOButton(175,280,100,40, "Erase"), //selection for no block button
			new JAOButton(50,280,100,40, "Set Name") //set the name
		};
	
	//the arraylist of blocks
	ArrayList <block> blocks = new ArrayList();
	
	//the key booleans, identifying the state of the keys
	boolean upArrow = false;
	boolean downArrow = false;
	boolean leftArrow = false;
	boolean rightArrow = false;
	
	//the name of the current map
	String str_name = "";
	
	//the general message for the user - errors and successes
	String str_msg = "Enter a Map Name";
	
	//the color of the current message
	Color color_msg = Color.red;
	
	//the main constructor
	public editorScreen()
	{
		
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
	
	public void mouseClicked(int mX, int mY)
	{
		//run through the buttons
		for(int i = 0; i < buttons.length; i++)
		{
			if(mouseBounds.mouseCollide(mX, mY, buttons[i].int_x, buttons[i].int_y, buttons[i].int_width, buttons[i].int_height))
			{
				//identify the button - menu
				if(buttons[i].str_name.equals("Menu"))
				{
					//return to the menu screen
					Main.mainScreen = new titleScreen();
					//break the loop
					break;
				}
				//ice block button
				else if(buttons[i].str_name.equals("Ice Block"))
				{
					str_selection = "Ice Block";
					break;
				}
				//fire block button
				else if(buttons[i].str_name.equals("Fire Block"))
				{
					str_selection = "Fire Block";
					break;
				}
				//dirst block button
				else if(buttons[i].str_name.equals("Dirt Block"))
				{
					str_selection = "Dirt Block";
					break;
				}
				//steel block button
				else if(buttons[i].str_name.equals("Steel Block"))
				{
					str_selection = "Steel Block";
					break;
				}
				//no block (for erasing)
				else if(buttons[i].str_name.equals("Erase"))
				{
					str_selection = "Erase";
					break;
				}
				//set the map name
				else if(buttons[i].str_name.equals("Set Name"))
				{
					getNameFrame namer = new getNameFrame();
				}
				
				//save the map
				else if(buttons[i].str_name.equals("Save"))
				{
					writeToFile();
				}
				
				//load a file
				else if(buttons[i].str_name.equals("Load"))
				{
					loadFile();
				}
			}
		}
		
		
		//check to see if the drawing screen was clicked on
		if(mouseBounds.mouseCollide(mX, mY, drawX, drawY, drawWidth, drawHeight))
		{
			//calculate the position of the block, create a new block to go into the array
			if(str_selection.equals("Dirt Block"))
			{
				int iter = 0;
				
				//run through the blocks, check to see if one is already in that spot
				for(int i = 0; i < blocks.size(); i++)
				{
					//check if you clicked on an already existing block
					if(blocks.get(i).int_x == ((mX - (mX % 20))) - drawX - refX && blocks.get(i).int_y == ((mY - (mY%20))) - drawY - refY)
					{
						//set the block to the new block
						blocks.set(i, new dirtBlock(((mX - (mX % 20))) - drawX - refX,((mY - (mY%20))) - drawY - refY));
						break;
					}
					else
					{
						//if not, count how many blocks you searched
						iter += 1;
					}
				}
				
				//if you searched through all the blocks and found to comparisons
				if(iter == blocks.size())
				{
					//add a new block
					blocks.add(new dirtBlock(((mX - (mX % 20))) - drawX - refX,((mY - (mY%20))) - drawY - refY)); 
				}
			}
			else if(str_selection.equals("Fire Block"))
			{
				int iter = 0;
				
				//run through the blocks, check to see if one is already in that spot
				for(int i = 0; i < blocks.size(); i++)
				{
					//check if the blocks overlap
					if(blocks.get(i).int_x == ((mX - (mX % 20))) - drawX - refX && blocks.get(i).int_y == ((mY - (mY%20))) - drawY - refY)
					{
						//if so, set a new block
						blocks.set(i, new fireBlock(((mX - (mX % 20))) - drawX - refX,((mY - (mY%20))) - drawY - refY)); 
						break;
					}
					else
					{
						//otherwise, count the blocks
						iter += 1;
					}
				}
				
				//if all boocks have been checked
				if(iter == blocks.size())
				{
					//add a new block
					blocks.add(new fireBlock(((mX - (mX % 20))) - drawX - refX,((mY - (mY%20))) - drawY - refY)); 
				}
			}
			else if(str_selection.equals("Steel Block"))
			{
				int iter = 0;
				
				//run through the blocks, check to see if one is already in that spot
				for(int i = 0; i < blocks.size(); i++)
				{
					//check if blocks overlap
					if(blocks.get(i).int_x == ((mX - (mX % 20))) - drawX - refX && blocks.get(i).int_y == ((mY - (mY%20))) - drawY - refY)
					{
						//if so, replace the block
						blocks.set(i, new steelBlock(((mX - (mX % 20))) - drawX - refX,((mY - (mY%20))) - drawY - refY)); 
						break;
					}
					else
					{
						//otherwise, count the block
						iter += 1;
					}
				}
				
				//if all blocks have been checked
				if(iter == blocks.size())
				{
					//add a new block
					blocks.add(new steelBlock(((mX - (mX % 20))) - drawX - refX,((mY - (mY%20))) - drawY - refY)); 
				}
			}
			else if(str_selection.equals("Ice Block"))
			{
				int iter = 0;
				
				//run through the blocks, check to see if one is already in that spot
				for(int i = 0; i < blocks.size(); i++)
				{
					//check if blocks overlap
					if(blocks.get(i).int_x == ((mX - (mX % 20))) - drawX - refX && blocks.get(i).int_y == ((mY - (mY%20))) - drawY - refY)
					{
						//set a new block
						blocks.set(i, new iceBlock(((mX - (mX % 20))) - drawX - refX,((mY - (mY%20))) - drawY - refY)); 
						break;
					}
					else
					{
						//otherwise count the blocks
						iter += 1;
					}
				}
				
				//if all blocks have been checked
				if(iter == blocks.size())
				{
					//add a new block
					blocks.add(new iceBlock(((mX - (mX % 20))) - drawX - refX,((mY - (mY%20))) - drawY - refY)); 
				}
			}
			else if(str_selection.equals("Erase"))
			{
				//run through the blocks, check to see if one is already in that spot
				for(int i = 0; i < blocks.size(); i++)
				{
					//check if you overlap a block
					if(blocks.get(i).int_x == (mX - (mX % 20)) - drawX - refX && blocks.get(i).int_y == (mY - (mY%20)) - drawY - refY)
					{
						//if so, erase the block
						blocks.remove(i);
						break;
					}
				}
			}
		}
	}
	
	//the key pressed method
	public void keyDown(KeyEvent e)
	{
		//check the keys when pressed, determine pressed key, initiate action
		if(e.getKeyCode() == 37)
		{
			//set the left arrow to true
			leftArrow = true;
		}
		else if(e.getKeyCode() == 38)
		{
			//set the up arrow to true
			upArrow = true;
		}
		else if(e.getKeyCode() == 39)
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
	
	//the key released method
	public void keyUp(KeyEvent e)
	{
		//check the keys when pressed, determine pressed key, initiate action
		if(e.getKeyCode() == 37)
		{
			//set the left arrow to true
			leftArrow = false;
		}
		else if(e.getKeyCode() == 38)
		{
			//set the up arrow to true
			upArrow = false;
		}
		else if(e.getKeyCode() == 39)
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
	
	//gameloop method, where all the important code goes
	public void mainLoop()
	{
		//run through the button actions
		for(int i = 0; i < buttons.length; i++)
		{
			//perform the respective button actions
			buttons[i].mainLoop();
		}
		
		//perform key actions for when they are pressed / released
		
		//up arrow
		if(upArrow == true)
		{
			refY += 20;
		}
		
		//down arrow
		if(downArrow == true)
		{
			refY -= 20;
		}
		
		//left arrow
		if(leftArrow == true)
		{
			refX += 20;
		}
		
		//right arrow
		if(rightArrow == true)
		{
			refX -= 20;
		}
	}
	
	//the paint method
	@Override 
	public void paint(Graphics2D g)
	{
		//setup the background
		g.setColor(Color.black);
		
		//draw the background
		g.fillRect(0, 0, Main.main_width,Main.main_height);
		
		//draw the main title
		g.drawImage(imageCache.titleSprites[1], 50, 25, null);
		
		//draw the buttons
		for(int i = 0; i < buttons.length; i++)
		{
			//draw the respective buttons
			buttons[i].paint(g);
		}
		
		//draw the red outline space for the map portion
		g.setColor(Color.red);
		g.drawRect(drawX, drawY, drawWidth, drawHeight);
		
		//display the currently selected block
		g.setColor(buttons[0].main_color);
		g.setFont(buttons[0].main_font);
		g.drawString("Selection: " + str_selection, 550,175);
		
		//draw the blocks on the map
		for(int i = 0; i < blocks.size(); i++)
		{
			if(drawX + refX + blocks.get(i).int_x + 20 < drawX || drawX + refX + blocks.get(i).int_x + blocks.get(i).int_width - 20 > drawX + drawWidth ||drawY + refY + blocks.get(i).int_y + 20 < drawY || drawY + refY + blocks.get(i).int_y + blocks.get(i).int_height - 20 > drawY + drawHeight)
			{
				
			}
			else
			{
				g.drawImage(imageCache.blockSprites[blocks.get(i).int_imgID],drawX + refX + blocks.get(i).int_x, drawY + refY + blocks.get(i).int_y, null);
			}
			
		}
		
		//draw a small grfeen square marking the location of the spawn point
		g.setColor(Color.green);
		
		if(drawX + refX + 20 < drawX || drawX + refX - 20 > drawX + drawWidth ||drawY + refY + 20 < drawY || drawY + refY - 20 > drawY + drawHeight)
		{
			//do nothing
		}
		else
		{
			g.fillRect(refX + drawX, refY + drawY, 20, 20);
		}
		
		//draw a buffer around the draw screen for block overlapping
		g.setColor(Color.black);
		
		g.fillRect(drawX - 20, drawY - 20, drawWidth + 40, 20);
		g.fillRect(drawX - 20, drawY - 20, 20, drawHeight + 20);
		g.fillRect(drawX + drawWidth + 1, drawY, 20, drawHeight);
		g.fillRect(drawX - 20, drawY + drawHeight + 1, drawWidth + 40, 20);
		
		//draw a separator between the buttons
		g.setColor(Color.red);
		g.drawLine(50, 205, 750, 205);
		
		//draw the name of the map
		g.setColor(Color.white);
		g.drawString("Name: " + str_name, 50, 235);

		//draw the message
		g.setColor(color_msg);
		g.drawString(str_msg, 50, 260);
	}
	
	//the save method
	public void writeToFile()
    {
        BufferedWriter out;
        FileWriter ryt;
        
        try
        {
            ryt=new FileWriter("Resources/Map Cache/" + str_name + ".pha");
            out=new BufferedWriter(ryt);
            
            //check to make sure the map has a name
            if(str_name.equals(""))
            {
            	//display error message
            	color_msg = Color.red;
            	str_msg = "Error! Enter Name!";
            }
            else
            {
             	
	            //write the name of the map with a space
	            out.write(str_name);
	            out.newLine();
	            
	            //write the size of the array
	            out.write(Integer.toString(blocks.size()));
	            out.newLine();
	            
	            //loop through the blocks, write their information on lines
	            for(int i = 0; i < blocks.size(); i++)
	            {
	            	//write the identifying criteria
	            	out.write(blocks.get(i).str_ID);
	            	out.newLine();
	            	
	            	//write the x value
	            	out.write(Integer.toString(blocks.get(i).int_x));
	            	out.newLine();
	            	
	            	//write the y value
	            	out.write(Integer.toString(blocks.get(i).int_y));
	            	out.newLine();
	            	
	            	//display the success message
	            	color_msg = Color.green;
	            	
	            	str_msg = "Save Successful!";
	            }
            }
            
            out.close();

        }
        catch(Exception e)
        {
        	System.out.println(e);
        }
    }
	 
	//this class is a jframe to get the filename of your map
	public class getNameFrame extends JFrame implements ActionListener
	{
		//components
		JPanel pnl = new JPanel();
		JButton btn = new JButton("Enter");
		JTextField txt = new JTextField(15);
		
		//main constructor
		public getNameFrame()
		{
			//set name
			super("Set Map Title");
			//set Size
			setSize(300,65);
			//set window location
			setLocation((Main.tool.getScreenSize().width - Main.main_width) / 2 + ((Main.main_width - 300) / 2), (Main.tool.getScreenSize().height - Main.main_height) / 2 + ((Main.main_height - 300) / 2));
			//set visible
			setVisible(true);
			//set unresizable
			setResizable(false);
			//set it's default close operation
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			//add the JPanel
			add(pnl);
			//add components to the JPanel
			pnl.add(btn);
			pnl.add(txt);
			//add an actionlistener to the button
			btn.addActionListener(this);
		}

		//the action method
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource() == btn)
			{
				//set the map name
				str_name = txt.getText();
				
				//check if a name has been entered
				if(!str_name.equals(""))
				{
					//set the message
					str_msg = "Name Entered!";
					
					//set the message color
					color_msg = Color.green;
				}
				else
				{
					//set the message
					str_msg = "Enter a Map Name";
					
					//set the message color
					color_msg = Color.red;
				}
				
				this.dispose();
			}
		}
	}
	
	//load files
	public void loadFile()
	{
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
        	str_name = br.readLine();
        	
        	//read the number of blocks to iterate through
        	int int_blocks = Integer.parseInt(br.readLine());
        	
        	System.out.println(int_blocks);
        	
    		//create a new block array
    		blocks = new ArrayList<block>();
        	
        	//loop through the number of blocks and perform the following reads
        	for(int i = 0; i < int_blocks; i++)
        	{
        		//set the blocks type
        		String type = br.readLine();
        		
        		//set the block location
        		int x = Integer.parseInt(br.readLine());
        		int y = Integer.parseInt(br.readLine());

        		//set a new block
        		if(type.equals("dirt"))
        		{
        			blocks.add(new dirtBlock(x, y));
        		}
        		else if(type.equals("ice"))
        		{
        			blocks.add(new iceBlock(x,y));
        		}
        		else if(type.equals("steel"))
        		{
        			blocks.add(new steelBlock(x,y));
        		}
        		else if(type.equals("fire"))
        		{
        			blocks.add(new fireBlock(x,y));
        		}
        	}
        	
        	//reset the reference point 
        	refX = 0;
        	refY = 0;
        	
        }
        catch(Exception e)
        {
        	System.out.println(e);
        }
	}
}
