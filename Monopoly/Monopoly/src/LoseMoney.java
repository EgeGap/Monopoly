
public class LoseMoney extends GameObject
{
	// Oyundaki lose $50 karesini temsil eden classst�r.
	// Class�n objesi Game class�nda yarat�lmaktad�r.
	
	private double amount;
	
	public LoseMoney(int id, double amount) 
	{
		super("losemoney", "Lose $" + (int)amount, "Whoever comes here loses $" + (int)amount, id);
		// GameObject class�n�n constructor�na yukar�daki veriler veriliyor. (bkz. GameObject.java constructor'�)
		// b�ylece:
		//   bu class�n type'� "losemoney"
		//   ba�l��� Lose $amount
		//   a��klamas� "Whoever comes here loses $amount"
		//   id'si ise bu class�n constructor�na verilen id olur.
		this.amount = amount;		
		
	}
	
	public double getAmount() 
	{
		return amount;
	}
	
	public void setAmount(double amount) 
	{
		this.amount = amount;
	}
}
