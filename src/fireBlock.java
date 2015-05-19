
//this block will slow players on contact
public class fireBlock extends block 
{

	//the main constructor
	public fireBlock(int x, int y) 
	{
		//call to the super class
		super(x, y);
		
		//setup the health integer for FIRE -- should not be damaged
		int_health = 10;
		
		//the image identifier
		int_imgID = 3;
		
		//the identifier string
		str_ID = "fire";
		
		//set the block to not solid
		isSolid = false;
	}

}
