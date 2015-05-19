
//this block will slow players on contact
public class steelBlock extends block 
{

	//the main constructor
	public steelBlock(int x, int y) 
	{
		//call to the super class
		super(x, y);
		
		//setup the health integer for STEEL - should not be damaged
		int_health = 10;
		
		//the image identifier
		int_imgID = 2;
		
		//the identifier string
		str_ID = "steel";
		
		//set the block to solid
		isSolid = true;
	}

}
