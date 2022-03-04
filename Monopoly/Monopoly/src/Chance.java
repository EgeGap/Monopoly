
public class Chance extends GameObject 
{
	
	// Chance karelerini temsil etmek için kullanýlýr.
	// Game classýnda kullanýlýr.
	
	public Chance (int id)
	{
		super("chance", "Chance", "When a player comes here, a chance card is randomly picked by the game and whatever is written on the card is applied.", id);
		// GameObject'in constructor'ýna yukarýdaki veriler gönderilir. (bkz. GameObject.java constructor)
		// type chance olarak ayarlanmýþ olur.
	}
}
