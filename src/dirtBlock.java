
//this block will slow players on contact
public class dirtBlock extends block 
{

	//the main constructor
	public dirtBlock(int x, int y) 
	{
		//call to the super class
		super(x, y);
		
		//setup the health integer for DIRT
		int_health = 4;
		
		//the image identifier
		int_imgID = 1;
		
		//the identifier string
		str_ID = "dirt";
		
		//set the block to solid
		isSolid = true;
	}

}
