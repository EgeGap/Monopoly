
public abstract class Building extends Property 
{
	// Building classý abstract çünkü kodun herhangi bir yerinde Building objesi yaratýlmýyor.
	
	
	//Building'in üzerinde bulunduðu SquareField'ý belirtmek için.
	private SquareField squareField;
	
	public Building(String type, String title, String description, int id, SquareField squareField) 
	{
		super(type, title, description, id);
		//type, title, description ve id deðiþkenleri Property classýnýn constructor'ýna gönderilir.
		// bkz. Property.java constructor
		
		//ayriyeten, verilen squareField objesi Building objesinin squareField'ý olarak atanýr.
		this.squareField = squareField;
	}
	
	public SquareField getSquareField() 
	{
		return squareField;	
	}
	
	public void setSquareField(SquareField squareField) 
	{
		this.squareField = squareField;
	}

	public abstract String toString();
	// Building'i extend eden tüm classlar toString metodu tanýmlamak zorunda.
	
}
