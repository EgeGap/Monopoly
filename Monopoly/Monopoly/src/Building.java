
public abstract class Building extends Property 
{
	// Building class� abstract ��nk� kodun herhangi bir yerinde Building objesi yarat�lm�yor.
	
	
	//Building'in �zerinde bulundu�u SquareField'� belirtmek i�in.
	private SquareField squareField;
	
	public Building(String type, String title, String description, int id, SquareField squareField) 
	{
		super(type, title, description, id);
		//type, title, description ve id de�i�kenleri Property class�n�n constructor'�na g�nderilir.
		// bkz. Property.java constructor
		
		//ayriyeten, verilen squareField objesi Building objesinin squareField'� olarak atan�r.
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
	// Building'i extend eden t�m classlar toString metodu tan�mlamak zorunda.
	
}
