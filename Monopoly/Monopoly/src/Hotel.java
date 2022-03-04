
public class Hotel extends Building
{

	public Hotel(String title, String description, int id, SquareField squareField) 
	{
		super("hotel", title, description, id, squareField);
		// Building'in constructor'�na burada belirtilen veriler g�nderilir.
		// bkz. Building.java constructor
	}
	
	public String toString() 
	{
		return "Hotel on " + getSquareField().getObject().getTitle();
		// Otelin �zerinde bulundu�u SquareField'�n objesinin title'� kullan�larak otel hakk�nda bilgi d�nd�r�l�r.
		// �rnek: Hotel on Antalya - Land
	}
	
}