
public class LoseMoney extends GameObject
{
	// Oyundaki lose $50 karesini temsil eden classstýr.
	// Classýn objesi Game classýnda yaratýlmaktadýr.
	
	private double amount;
	
	public LoseMoney(int id, double amount) 
	{
		super("losemoney", "Lose $" + (int)amount, "Whoever comes here loses $" + (int)amount, id);
		// GameObject classýnýn constructorýna yukarýdaki veriler veriliyor. (bkz. GameObject.java constructor'ý)
		// böylece:
		//   bu classýn type'ý "losemoney"
		//   baþlýðý Lose $amount
		//   açýklamasý "Whoever comes here loses $amount"
		//   id'si ise bu classýn constructorýna verilen id olur.
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
