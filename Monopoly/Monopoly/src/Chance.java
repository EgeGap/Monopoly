
public class Chance extends GameObject 
{
	
	// Chance karelerini temsil etmek i�in kullan�l�r.
	// Game class�nda kullan�l�r.
	
	public Chance (int id)
	{
		super("chance", "Chance", "When a player comes here, a chance card is randomly picked by the game and whatever is written on the card is applied.", id);
		// GameObject'in constructor'�na yukar�daki veriler g�nderilir. (bkz. GameObject.java constructor)
		// type chance olarak ayarlanm�� olur.
	}
}
