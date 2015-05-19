
//import swing capability
import javax.swing.*;

//import awt for graphics functionality
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

//the main class. all code will be called from here. extends JFrame for swing capability
public class Main extends JFrame implements KeyListener
{
	
	//the toolkit top get dimensions, images etc
	static Toolkit tool = Toolkit.getDefaultToolkit();
	
	//the main JPanel, just a lightweight container to call paint methods
	mainPanel pnl = new mainPanel();
	
	//this number will determine whether or not the program breaks
	static int exit_code = 0;
	
	//setup the dimensions for the screen
	static int main_width = 800;
	static int main_height = 500;
	
	static Main window;
	
	//create instances of the screen
	//static titleScreen titlePage = new titleScreen();
	//static editorScreen editScreen = new editorScreen();
	
	//the Main Screen Object
	static Screen mainScreen = new titleScreen();
	
	//the repaint thread
	gameThread activeThread = new gameThread();
	
	//the main constructor
	public Main()
	{
		//set the title of the window
		super("Platform Hero Arena");
		//set the window's size (note -- will not be full screen)
		setSize(main_width,main_height);		
		//set the window to the middle of the screen
		setLocation((tool.getScreenSize().width - main_width) / 2, (tool.getScreenSize().height - main_height) / 2);
		//set the window's visibility
		setVisible(true);
		//set the window's default close operation
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//add the key listener
		addKeyListener(this);
		//make the screen unresizable
		setResizable(false);
		//add the main Panel
		add(pnl);
		//start the active thread
		activeThread.run();

		
	}
	
	//the main method
	public static void main(String[] args)
	{
		//create an instance of this window
		window = new Main();
	}
	
	//this method will take an argument to decide whether or not to break the main loop
	public static void exit()
	{
		//increase the exit code in oder to break the loop
		exit_code = 1;
	}
	

	//key methods
	
	//key pressed
	@Override
	public void keyPressed(KeyEvent e) 
	{
		//call the active screen's key method
		mainScreen.keyDown(e);
	}

	
	//key released
	@Override
	public void keyReleased(KeyEvent e) 
	{
		//call the active screen's key released method
		mainScreen.keyUp(e);
	}
	
	//key typed
	@Override
	public void keyTyped(KeyEvent e) 
	{
		//do nothing as of now
	}
	
	//this inner class will be the JPanel that everything draws to in regards to this program
	public class mainPanel extends JPanel implements MouseListener, MouseMotionListener
	{
		
		//main constructor
		public mainPanel()
		{
			//set the JPanel visible
			setVisible(true);
			//destroy the layout, allowing free coordinates
			setLayout(null);
			//set the screen
			mainScreen = new titleScreen();
			//start the calculations timer
			update.start();
			//add muse listeners
			addMouseListener(this);
			addMouseMotionListener(this);
		}
		
		
		//the timer to manage the speed of updateing the button
		Timer update = new Timer(10,new ActionListener(){public void actionPerformed(ActionEvent e){
			//call the calculations 100 times / second
			mainScreen.mainLoop();
		}});
		
		//the gameloop
		public void gameLoop()
		{
			//run the gameloop for the specific screen
			mainScreen.mainLoop();
			
			//repaint the screen
			repaint();
		}
		
		//the painting method
		public void paintComponent(Graphics g)
		{
			//setup a 2d graphics object by type casting the graphics object
			Graphics2D g2d = (Graphics2D)g;
			
			//call the graphics updating for the Main Screen
			mainScreen.paint(g2d);
		}

		//mouse methods
		
		//mouse clicked
		@Override
		public void mouseClicked(MouseEvent e) 
		{
			//call the main screen's mouse clicked method
			mainScreen.mouseClicked(e.getX(), e.getY());
		}

		//mouse entered
		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		//mouse exited
		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		//mouse pressed
		@Override
		public void mousePressed(MouseEvent e)
		{
			mainScreen.mousePressed(e.getX(), e.getY());
		}

		//mouse released
		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		//mouse dragged
		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		//mouse moved
		@Override
		public void mouseMoved(MouseEvent e) 
		{
			//call the screen's mouse moved method
			mainScreen.mouseMoved(e.getX(), e.getY());
		}
	}
	
	//create the game thread
	public class gameThread implements Runnable
	{
		//the run function
		public void run()
		{
			//the main loop. will be broken to end the program
			while(true)
			{
				//repaint the panel
				pnl.repaint();
				
				//check whether or not to break the loop
				if(exit_code == 1)
				{
					//break the loop
					break; 
				}
			}
			
			//exit the program
			System.exit(0);
		}
	}
	
}
//end of program
