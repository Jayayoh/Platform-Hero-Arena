
//this block will slow players on contact
public class iceBlock extends block 
{

	//the main constructor
	public iceBlock(int x, int y) 
	{
		//call to the super class
		super(x, y);
		
		//setup the health integer for ICE
		int_health = 6;
		
		//the image identifier
		int_imgID = 0;
		
		//the identifier string
		str_ID = "ice";
		
		//set the block to solid
		isSolid = true;
	}

}