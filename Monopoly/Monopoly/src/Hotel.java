
public class Hotel extends Building
{

	public Hotel(String title, String description, int id, SquareField squareField) 
	{
		super("hotel", title, description, id, squareField);
		// Building'in constructor'ýna burada belirtilen veriler gönderilir.
		// bkz. Building.java constructor
	}
	
	public String toString() 
	{
		return "Hotel on " + getSquareField().getObject().getTitle();
		// Otelin üzerinde bulunduðu SquareField'ýn objesinin title'ý kullanýlarak otel hakkýnda bilgi döndürülür.
		// Örnek: Hotel on Antalya - Land
	}
	
}