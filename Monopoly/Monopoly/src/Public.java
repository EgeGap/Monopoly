
public class Public extends Property{

	// oyundaki kamu hizmeti objelerini temsil eder.
	// (Water Services, Train Station, Telecommunication Services)
	
	
	private double price;
	// fiyat
	
	private double incomePerTurn;
	// el ba��na kazand�rd��� gelir
	
	public Public(String title, String description, int id, double price, double rentPerTurn) 
	{
		super("public", title, description, id);
		// Property class�n�n constructor�na yukar�da belirtilen veriler g�nderiliyor.
		// type public olarak ayarlanm�� oluyor.
		
		this.price = price;
		this.incomePerTurn = rentPerTurn;
	}
	
	public double getPrice() 
	{
		return price;
	}

	public void setPrice(double price) 
	{
		this.price = price;
	}
	
	public double getIncomePerTurn() 
	{
		return incomePerTurn;
	}
	
	public void setIncomePerTurn(double incomePerTurn) 
	{
		this.incomePerTurn = incomePerTurn;
	}
	
	@Override
	public String getInformation() 
	{
		// Property class�n�n getInformation metodunun d�nd�rd��� String'e �cret ve el ba��na gelir bilgisi eklenip d�nd�r�l�yor.
		String info = super.getInformation();
		info += "Price: $" + price + "\n" + "Income Per Turn: $" + incomePerTurn + "\n";
		return info;
	}
	
	public boolean equals(Public p) 
	{
		// e�er iki Public objesinin ID'si, description'� ve title'� birbirlerine e�itse bu iki Public objesi ayn�d�r.
		return p.getID() == getID() && p.getDescription().equals(description) && p.getTitle().equals(title);
	}
	
	public String toString() 
	{
		return title;
	}
}
