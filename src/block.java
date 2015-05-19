
//this class will provide the template for the individual block classes. these classes will makeup the environment of the game
public class block 
{
	//main variables
	
	//the image identifier
	int int_imgID;
	
	//dimensions
	int int_x; //x location
	int int_y; //y location
	int int_width = 20; //constant width
	int int_height = 20; //constant height
	
	//the identifying string
	String str_ID;
	
	//the health integer
	int int_health;
	
	//the boolean identifying if a block is solid
	boolean isSolid;
	
	//the main constructor
	public block(int x, int y)
	{
		//set the dimensions
		int_x = x;
		int_y = y;
	}
}
