import java.io.Serializable;
import java.util.ArrayList;

public class Land extends Property implements Serializable
{

	private int colorID; // Arazinin hangi renk grubuna ait olduðunu tutmak için.
	private String colorName; // Arazinin ait olduðu renk grubunu string hâlinde belirtmek için.
	private double rentPerTurn; // Arazi boþ iken sahibine kazandýrdýðý kira miktarý.
	private ArrayList<House> houses; // Arazide birden fazla ev kurulabileceði için, arazi üzerinde kurulan evleri House ArrayList'inde tutuyoruz.
	private Hotel hotel; // Her araziye sadece 1 otel kurulabildiði için, arazide kurulan oteli Hotel objesi ile tutuyoruz.
	private boolean hotelExists; // otel olup olmadýðýný bir deðiþkende saklamak için.
	private double price; // Arazinin boþ hâlinin fiyatý.
	private double houseBuildingPrice; // Arazide ev kurmak için gereken para miktarý.
	private double hotelBuildingPrice; // Arazide otel kurmak için gereken para miktarý.
	private final double HOUSE_RENT_MULTIPLE_COEFFICIENT = 1.5; // Kurulan her ev, arazi sahibinin kazandýðý kira miktarýný 1.5 katýna çýkaracak.
	private final double HOTEL_RENT_MULTIPLE_COEFFICIENT = 8; // Otel olduðunda, arazinin kazandýðý kira miktarý arazinin boþ hâldeyken kazandýrdýðý kira miktarýnýn 8 katý olacak.
	private final double HOUSE_PRICE_MULTIPLE_COEFFICIENT = 1.6; // Kurulan her ev, arazinin fiyatýný 1.6'ya katlayacak.
	private final double HOTEL_PRICE_MULTIPLE_COEFFICIENT = 8.3; // Arazide otel olduðunda, arazinin fiyatý arazinin boþ hâlinin fiyatýnýn 8.3 katý olacak.
	private final int MAXIMUM_NUMBER_OF_HOUSES = 4; // Bir arazide kurulabilecek maksimum ev sayýsý 4. 4 ev kurulduktan sonra sadece otel kurulabilecek.
	
	
	public Land(String title, String description, int id, double price, int colorID, double rentPerTurn) 
	{
		
		super("land", title, description, id);
		// Property classýnýn constructorý çaðýrýlýyor ve constructora buradaki bilgiler veriliyor.
		// Classýn tipi land olarak belirlenmiþ oluyor.
		
		this.colorID = colorID;
		this.price = price;
		this.rentPerTurn = rentPerTurn;
		
		
		this.houseBuildingPrice = price * 1.5; // houseBuildingPrice, arazinin boþ hâlinin fiyatýnýn 1.5 katý olarak belirleniyor.
		this.hotelBuildingPrice = price * 4; // hotelBuildingPrice, arazinin boþ hâlinin fiyatýnýn 4 katý olarak belirleniyor.
		houses = new ArrayList<House>(); // houses arraylisti oluþturuluyor.
		determineTheColorName(); // verilen colorID, string versiyona dönüþtürülüyor.
		
	}
	
	private boolean isThereAHouse() 
	{
		// eðer arazide kurulu en az 1 ev varsa, true döndürür.
		// arazide hiç ev yoksa false döndürür.
		
		return houses.size() > 0;
		// eðer houses arraylistinin size'ý 0'dan büyükse arazide ev var demektir ve bu koþulda true döndürülür.
		// aksi takdirde, arazide ev yok demektir ve false döndürülür.
	}
	
	public boolean isThereAHotel() 
	{
		return hotelExists;
	}
	
	public int getNumberOfHouses() 
	{
		// arazide kurulu ev sayýsýný döndürür.
		return houses.size();
	}
	
	private void determineTheColorName() 
	{
		
		// classýn colorID'sine göre, string hâlinde rengi belirler.
		// arazinin ait olduðu renk grubunun yazý hâlinde gösterilebilmesi adýna önemlidir.
		// Game classý içerisinde Land objeleri yaratýlýrken colorID olarak;
		/*
		 * mor renkteki arazilere 0,
		 * açýk mavi rengindeki arazilere 1,
		 * pembe arazilere 2,
		 * turuncu arazilere 3,
		 * kýrmýzý arazilere 4,
		 * sarý arazilere 5,
		 * yeþil arazilere 6,
		 * mavi arazilere ise 7 atanmýþtýr.
		 * aþaðýdaki switch kullanýlarak colorID string'e dönüþtürülmekte ve bu string colorName'e atanmaktadýr.
		 */
		
		switch(colorID) 
		{
			case 0:
				colorName = "Purple";
				break;
			case 1:
				colorName = "Light Blue";
				break;
			case 2:
				colorName = "Pink";
				break;
			case 3:
				colorName = "Orange";
				break;
			case 4:
				colorName = "Red";
				break;
			case 5:
				colorName = "Yellow";
				break;
			case 6:
				colorName = "Green";
				break;
			case 7:
				colorName = "Blue";
				break;
		}
		
	}
	
	public int getColorID () 
	{
		return colorID;
	}
	
	public String getColorName() 
	{
		return colorName;
	}
	
	public void setColorID (int colorID) 
	{
		this.colorID = colorID;
	}
	
	public void setColorName (String colorName)
	{
		this.colorName = colorName;
	}
	
	
	public double getHouseBuildingPrice() 
	{
		return houseBuildingPrice;
	}
	
	public void setHouseBuildingPrice (double houseBuildingPrice) 
	{
		this.houseBuildingPrice = houseBuildingPrice;
	}
	
	public double getHotelBuildingPrice() 
	{
		return hotelBuildingPrice;
	}
	
	public void setHotelBuildingPrice (double hotelBuildingPrice) 
	{
		this.hotelBuildingPrice = hotelBuildingPrice;
	}
	
	public double getPrice() 
	{
		// arazinin mevcut ücretini Land classý dýþýndan öðrenmek için kullanýlýr.
		// calculatePrice metodunun döndürdüðü deðer aynen döndürülür.
		return calculatePrice();
	}
	
	public void setPrice(double price) 
	{
		this.price = price;
	}
	
	public ArrayList<House> getHouses() 
	{
		return houses;
	}
	
	public void setHouses(ArrayList<House> houses) 
	{
		this.houses = houses;
	}
	
	public Hotel getHotel() 
	{
		return hotel;
	}
	
	public void setHotel(Hotel hotel) 
	{
		this.hotel = hotel;
	}
	
	
	public void buildHouse(House house) 
	{
		// belirtilen ev objesini bu arazinin houses arraylistine ekler.
		// kýsacasý, araziye belirtilen evi diker.
		houses.add(house);
	}
	
	public void buildHotel(Hotel hotel) 
	{
		// Bu metod, Player classý içerisinden çaðrýlýr.
		
		
		// Bu metod;
		// Belirtilen evi bu arazi üzerine diker.
		// Bunun için, metoda parametre olarak verilen Hotel objesini bu arazinin hotel deðiþkenine atar. (this.hotel = hotel;)
		// arazide otel olduðunu belirtmek için hotelExists deðiþkenini true olarak ayarlar. (hotelExists = true;)
		// Bir arazide ayný anda hem ev hem otel olamaz, bundan dolayý bir araziye otel dikildiðinde o arazideki tüm evler yok edilmek zorundadýr.
		// Bunun için, arazinin houses arraylisti sýfýrlanýr ve ayný zamanda arazinin sahibinin bu land'deki tüm evleri oyuncunun houses arraylistinden silinir.
		
		
		this.hotel = hotel;
		hotelExists = true;
		
		for (House h: houses) 
		{
			//Bu arazideki tüm evlerin sahibi sýfýrlanýr.
			h.setOwner(null);
		}
		
		hotel.getOwner().removeHousesInLand(this);
		// Bu arazinin sahibinin bu arazideki tüm evleri silinir.
		
		houses = new ArrayList<House>();
		// Bu arazinin houses arraylisti boþ bir House ArrayListine atanýr, böylece bu arazideki evler silinir.
		
	}
	
	public boolean getRentFromPlayer(Player player) 
	{
		// Bu metod çalýþtýrýldýðýnda;
		// metoda parametre olarak gönderilen oyuncu, arazinin sahibine bu arazinin mevcut kira miktarý kadar kira öder.
		// eðer oyuncunun yeterli parasý yoksa false döndürülür.
		// bkz. Player.java payRentTo() metodu
		return player.payRentTo(owner, getRentAmount());
	}

	public double getRentAmount() 
	{
		// calculateRentPerTurn metodundan döndürülen deðer ne ise ayný deðeri döndürür.
		return calculateRentPerTurn();
	}
	
	private double calculateRentPerTurnForHouses(int houseCount) 
	{	
		// bu metod, arazide houseCount kadar ev varken arazinin sahibine kazandýracaðý kira miktarýný hesaplar.
		
		// Araziye dikilen her ev, arazinin sahibine kazandýrdýðý kira miktarýný (rentPerTurn) HOUSE_RENT_MULTIPLE_COEFFICIENT katýna çýkarýr.
		// HOUSE_RENT_MULTIPLE_COEFFICIENT 1.5 olarak tanýmlanmýþ olduðundan,
		// araziye dikilen her ev arazinin sahibine kazandýrdýðý kira miktarýný 1.5 katýna çýkarýr.
		
		// arazide 1 ev varken,
		// arazinin sahibine kazandýracaðý kira miktarý rentPerTurn*1.5 olacaktýr.
		// arazide 2 ev varken,
		// arazinin sahibine kazandýracaðý kira miktarý rentPerTurn*1.5*1.5 = rentPerTurn*((1.5)^2) olacaktýr.
		// arazide 3 ev varken,
		// arazinin sahibine kazandýracaðý kira miktarý rentPerTurn*1.5*1.5*1.5 = rentPerTurn*((1.5)^3) olacaktýr.
		// arazide x ev varken,
		// arazinin sahibine kazandýracaðý kira miktarý rentPerTurn*((1.5)^x) olacaktýr.
		
		// arazide houseCount kadar ev varken,
		// arazinin sahibine kazandýracaðý kira miktarý rentPerTurn*((1.5)^houseCount) olacaktýr.
		// Java'da (1.5)^houseCount iþlemini þu þekilde yapabiliriz:  Math.pow(1.5, houseCount)
		// Ayriyeten, Java'da bir virgüllü sayýyý (bu sayý x olsun) virgülden sonraki iki basamaða yuvarlamak için
		// þu kodu kullanabiliriz : Math.round(x*100.0)/100.0;
		// Örnek: x = 154.531598 olsun.
		// x*100.0 = 15453.1598 olur.
		// Math.round(x*100.0) = Math.round(15453.1598) = 15453
		// Math.round(x*100.0)/100.0 = 15453/100.0 = 154.53
		// Böylece 154.53198 sayýsýný 154.53'e yuvarlamýþ olduk.
		// Burada sayýyý virgülden sonraki iki basamaða yuvarlamamýzýn sebebi þu:
		// virgüllü bir sayýnýn kuvvetlerini alarak iþlem yaptýðýmýzdan dolayý virgülden sonra çok uzayan sayýlar karþýmýza çýkabilir.
		// Bunu önlemek istiyoruz.
		
		// aþaðýdaki satýrlarda, yukarýda bahsedilen ve kira hesaplama iþlemini yapýyoruz.
		// double rpt = rentPerTurn diyip sonra return'lü satýrda rpt yi kullanmak yerine
		// rpt'yi hiç tanýmlamayýp direkt rentPerTurn'ü de return'lü satýrda rpt yerine kullanabilirdik.
		
		double rpt = rentPerTurn;
		return Math.round(rpt*Math.pow(HOUSE_RENT_MULTIPLE_COEFFICIENT, houseCount)*100.0)/100.0;
	}
	
	private double calculatePriceOfLandWithHouses (int houseCount) 
	{
		// bu metod, arazide houseCount kadar ev varken arazinin fiyatýnýn ne kadar olacaðýný hesaplar.
		
		// bu metod, yukarýda anlatýlan metoda benzer bir þekilde çalýþýr.
		// ama bu sefer, price katlanýr ve HOUSE_RENT_MULTIPLE_COEFFICIENT yerine HOUSE_PRICE_MULTIPLE_COEFFICIENT kullanýlýr.
		// Çünkü bu metodda arazinin fiyatýný hesaplýyoruz, kirayý deðil.
		return Math.round(price * Math.pow(HOUSE_PRICE_MULTIPLE_COEFFICIENT, houseCount)*100.0)/100.0;
	}
	
	private double getPriceWithHotel() 
	{
		// arazide otel varken arazinin fiyatýnýn ne kadar olacaðýný hesaplar.
		// calculatePriceOfLandWithHouses metoduna benzer çalýþýr. Tek farký, arazide sadece bir otel olabileceði için, parametre olarak otel sayýsýný belirten bir deðer almamasýdýr.
		// doðrudan otel sayýsý 1 olarak alýnýr ve iþlem o þekilde yapýlýr.
		return Math.round((price * HOTEL_PRICE_MULTIPLE_COEFFICIENT)*100.0)/100.0;
	}
	
	private double getRentPerTurnWithHotel () 
	{
		// arazide otel varken arazinin sahibine kazandýracaðý kiranýn ne kadar olacaðýný hesaplar.
		return Math.round((rentPerTurn * HOTEL_RENT_MULTIPLE_COEFFICIENT)*100.0)/100.0;
	}
	
	private double calculateRentPerTurn() 
	{
		// arazinin þimdiki ev/otel durumuna göre sahibine kazandýracaðý kira miktarýný hesaplar.
		
		
		double rpt = rentPerTurn;
		// eðer aþaðýdaki iki if'e de girilmezse, arazide otel ya da ev yok demektir.
		// bu durumda rpt'nin deðeri deðiþmeyecek ve rentPerTurn olarak kalacak.
		// rentPerTurn de arazinin boþ hâliyle sahibine kazandýrdýðý kira miktarý.
		// bu nedenle arazide ev ya da otel yokken, bu metod arazinin boþ hâlindeki kira miktarýný döndürmüþ olacak.
		
		if (isThereAHouse()) 
		{
			// eðer ev varsa, arazideki ev sayýsýný calculateRentPerTurnForHouses metoduna parametre olarak gönderip arazideki mevcut ev sayýsýna göre arazinin kira miktarýný hesaplýyoruz.
			// hesaplanan ev miktarý rpt deðiþkenine atanýyor.
			rpt = calculateRentPerTurnForHouses(getNumberOfHouses());
		}
		else if (hotelExists) 
		{
			// eðer ev yoksa ve otel varsa, arazinin otelli hâlindeki kira miktarý rpt'ye atanýr.
			rpt = getRentPerTurnWithHotel();
		}
		
		// en sonda da yukarýdaki koda göre hesaplanmýþ olan rpt deðiþkeni döndürülür.
		return rpt;
		
	}
	
	private double calculatePrice() 
	{
		// calculateRentPerTurn metoduna benzer çalýþýr.
		// tek farký, kirayý deðil fiyatý hesaplamasýdýr.
		
		double price = this.price;
		if (isThereAHouse()) 
		{
			price = calculatePriceOfLandWithHouses(getNumberOfHouses());
		}
		else if (hotelExists) 
		{
			price = getPriceWithHotel();
		}
		return price;
	}
	
	public int getHouseLimit()
	{
		return MAXIMUM_NUMBER_OF_HOUSES;
	}
	
	@Override
	public String getInformation() 
	{
		// bu metod tarafýndan döndürülen string, oyun board'undaki land'lerin üzerine týklandýðýnda açýlan bilgi penceresinde yazdýrýlýr.
		
		
		String info = super.getInformation();
		// Property classýnýn getInformation metodu çalýþtýrýlýr ve buradan elde edilen string info'ya atanýr.
		// bkz. Property.java:getInformation() metodu
		// aþaðýdaki satýrlarda, gereken tüm diðer bilgiler info stringine ekleniyor.
		
		
		info += "Color Group: "+ colorName + "\n";
		// arazinin ait olduðu renk grubu info'ya ekleniyor.
		
		info += "Price when:\n\tThere is no building on this land: $" + price + "\n";
		
		// aþaðýdaki for döngüsünde, 1'den 4'e kadar (MAXIMUM_NUMBER_OF_HOUSES=4 olduðu için) arazide bulunan ev sayýsýna göre arazinin fiyatý info'ya ekleniyor.
		for (int i = 1; i <= MAXIMUM_NUMBER_OF_HOUSES; i++) 
		{
			String word, isAre;
			
			// eðer 1 ev için olan koþulu yazdýrýyorsak (i = 1), There is 1 house diye yazdýrýlýyor.
			// eðer ev sayýsý 1'den fazlaysa (i > 1), There are x houses diye yazdýrýlýyor.
			// aþaðýda is/are ve house/houses ayrýmý yapýlýyor.
			if (i > 1) 
			{
				word = "houses";
				isAre = "are";
			}
			else 
			{
				word = "house";
				isAre = "is";
			}

			info += String.format("\tThere %s %d %s built on this land: $", isAre, i, word);
			// There {isAre} {i} {word} built on this land: ${calculatePriceOfLandWithHouses(i)}
			// Örnek çýktý: (i = 2 için) There are 2 houses built on this land: $(bu arazinin 2 evli ücreti)
			// Örnek çýktý: (i = 1 için) There is 1 house built on this land: $(bu arazinin 1 evli ücreti)
			info += calculatePriceOfLandWithHouses(i) + "\n";
		}
		
		info += "\tThere is a hotel built on this land: $" + getPriceWithHotel() + "\n";
		info += "Current price: $" + calculatePrice() + "\n";
		
		// aþaðýdaki for döngüsünde, 1'den 4'e kadar (MAXIMUM_NUMBER_OF_HOUSES=4 olduðu için) arazide bulunan ev sayýsýna göre arazinin kazandýrdýðý kira miktarý info'ya ekleniyor.
		info += "Rent Per Turn when:\n\tThere is no building on this land: $" + rentPerTurn + "\n";
		for (int i = 1; i <= MAXIMUM_NUMBER_OF_HOUSES; i++) 
		{
			String word, isAre;
			if (i > 1) 
			{
				word = "houses";
				isAre = "are";
			}
			else 
			{
				word = "house";
				isAre = "is";
			}

			info += String.format("\tThere %s %d %s built on this land: $", isAre, i, word);
			info += calculateRentPerTurnForHouses(i) + "\n";
		}
		info += "\tThere is a hotel built on this land: $" + getRentPerTurnWithHotel() + "\n";
		info += "Current rent per turn: $" + calculateRentPerTurn() + "\n";
		info += "House Building Price: $" + houseBuildingPrice + "\n";
		info += "Hotel Building Price: $" + hotelBuildingPrice + "\n";
		
		// bu arazide ev yoksa, "There is no house on this land." yazdýrýlýyor.
		
		if (!isThereAHouse()) 
		{
			info += "There is no house on this land.\n";
		}
		// varsa, arazide kaç tane ev olduðu sahibiyle birlikte yazdýrýlýyor.
		else 
		{
			String word, isAre;
			if (houses.size() > 1) 
			{
				word = "houses";
				isAre = "are";
			}
			else 
			{
				word = "house";
				isAre = "is";
			}

			if (this.isOwned())
				info += "There " + isAre + " " + houses.size() + " " + word + " owned by " + owner + " on this land.\n";
				// landin sahibi varsa, arazide kaç ev olduðu sahibiyle beraber yazdýrýlýyor
			else
				info += "There " + isAre + " " + houses.size() + " unowned " + word + " on this land.\n";
				// landin sahibi yoksa, arazide kaç ev olduðu yazdýrýlýyor. evlerin sahipsiz olduðu da belirtiliyor.
		}
		
		if (!isThereAHotel()) 
		{
			info += "There is no hotel on this land.\n";
		}
		else 
		{
			if (this.isOwned())
				info += "There is a hotel owned by " + hotel.getOwner() + " on this land.\n";
			else
				info += "There is an unowned hotel on this land.\n";
		}
		
		return info;
	}
	
	public boolean equals (Land l) 
	{
		return l.getDescription().equals(description) && l.getTitle().equals(title);
	}
	
	public String toString() 
	{
		return description;
	}

}
