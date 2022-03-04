
public class House extends Building
{

	// Hotel class�na benzer bir �ekilde tan�mlanm��t�r.
	// bkz. Hotel.java
	
	public House(String title, String description, int id, SquareField squareField) 
	{
		super("house", title, description, id, squareField);
	}
	
	public boolean equals(House h) 
	{
		// e�er verilen House objesinin ID'si ile bu House objesinin ID'si e�itse, true d�nd�r�l�r.
		return h.getID() == getID();
	}

	public String toString() 
	{
		return "House on " + getSquareField().getObject().getTitle();
	}
	
}
