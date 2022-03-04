
public class House extends Building
{

	// Hotel classýna benzer bir þekilde tanýmlanmýþtýr.
	// bkz. Hotel.java
	
	public House(String title, String description, int id, SquareField squareField) 
	{
		super("house", title, description, id, squareField);
	}
	
	public boolean equals(House h) 
	{
		// eðer verilen House objesinin ID'si ile bu House objesinin ID'si eþitse, true döndürülür.
		return h.getID() == getID();
	}

	public String toString() 
	{
		return "House on " + getSquareField().getObject().getTitle();
	}
	
}
