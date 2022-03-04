
public class Public extends Property{

	// oyundaki kamu hizmeti objelerini temsil eder.
	// (Water Services, Train Station, Telecommunication Services)
	
	
	private double price;
	// fiyat
	
	private double incomePerTurn;
	// el baþýna kazandýrdýðý gelir
	
	public Public(String title, String description, int id, double price, double rentPerTurn) 
	{
		super("public", title, description, id);
		// Property classýnýn constructorýna yukarýda belirtilen veriler gönderiliyor.
		// type public olarak ayarlanmýþ oluyor.
		
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
		// Property classýnýn getInformation metodunun döndürdüðü String'e ücret ve el baþýna gelir bilgisi eklenip döndürülüyor.
		String info = super.getInformation();
		info += "Price: $" + price + "\n" + "Income Per Turn: $" + incomePerTurn + "\n";
		return info;
	}
	
	public boolean equals(Public p) 
	{
		// eðer iki Public objesinin ID'si, description'ý ve title'ý birbirlerine eþitse bu iki Public objesi aynýdýr.
		return p.getID() == getID() && p.getDescription().equals(description) && p.getTitle().equals(title);
	}
	
	public String toString() 
	{
		return title;
	}
}
