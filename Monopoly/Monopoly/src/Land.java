import java.io.Serializable;
import java.util.ArrayList;

public class Land extends Property implements Serializable
{

	private int colorID; // Arazinin hangi renk grubuna ait oldu�unu tutmak i�in.
	private String colorName; // Arazinin ait oldu�u renk grubunu string h�linde belirtmek i�in.
	private double rentPerTurn; // Arazi bo� iken sahibine kazand�rd��� kira miktar�.
	private ArrayList<House> houses; // Arazide birden fazla ev kurulabilece�i i�in, arazi �zerinde kurulan evleri House ArrayList'inde tutuyoruz.
	private Hotel hotel; // Her araziye sadece 1 otel kurulabildi�i i�in, arazide kurulan oteli Hotel objesi ile tutuyoruz.
	private boolean hotelExists; // otel olup olmad���n� bir de�i�kende saklamak i�in.
	private double price; // Arazinin bo� h�linin fiyat�.
	private double houseBuildingPrice; // Arazide ev kurmak i�in gereken para miktar�.
	private double hotelBuildingPrice; // Arazide otel kurmak i�in gereken para miktar�.
	private final double HOUSE_RENT_MULTIPLE_COEFFICIENT = 1.5; // Kurulan her ev, arazi sahibinin kazand��� kira miktar�n� 1.5 kat�na ��karacak.
	private final double HOTEL_RENT_MULTIPLE_COEFFICIENT = 8; // Otel oldu�unda, arazinin kazand��� kira miktar� arazinin bo� h�ldeyken kazand�rd��� kira miktar�n�n 8 kat� olacak.
	private final double HOUSE_PRICE_MULTIPLE_COEFFICIENT = 1.6; // Kurulan her ev, arazinin fiyat�n� 1.6'ya katlayacak.
	private final double HOTEL_PRICE_MULTIPLE_COEFFICIENT = 8.3; // Arazide otel oldu�unda, arazinin fiyat� arazinin bo� h�linin fiyat�n�n 8.3 kat� olacak.
	private final int MAXIMUM_NUMBER_OF_HOUSES = 4; // Bir arazide kurulabilecek maksimum ev say�s� 4. 4 ev kurulduktan sonra sadece otel kurulabilecek.
	
	
	public Land(String title, String description, int id, double price, int colorID, double rentPerTurn) 
	{
		
		super("land", title, description, id);
		// Property class�n�n constructor� �a��r�l�yor ve constructora buradaki bilgiler veriliyor.
		// Class�n tipi land olarak belirlenmi� oluyor.
		
		this.colorID = colorID;
		this.price = price;
		this.rentPerTurn = rentPerTurn;
		
		
		this.houseBuildingPrice = price * 1.5; // houseBuildingPrice, arazinin bo� h�linin fiyat�n�n 1.5 kat� olarak belirleniyor.
		this.hotelBuildingPrice = price * 4; // hotelBuildingPrice, arazinin bo� h�linin fiyat�n�n 4 kat� olarak belirleniyor.
		houses = new ArrayList<House>(); // houses arraylisti olu�turuluyor.
		determineTheColorName(); // verilen colorID, string versiyona d�n��t�r�l�yor.
		
	}
	
	private boolean isThereAHouse() 
	{
		// e�er arazide kurulu en az 1 ev varsa, true d�nd�r�r.
		// arazide hi� ev yoksa false d�nd�r�r.
		
		return houses.size() > 0;
		// e�er houses arraylistinin size'� 0'dan b�y�kse arazide ev var demektir ve bu ko�ulda true d�nd�r�l�r.
		// aksi takdirde, arazide ev yok demektir ve false d�nd�r�l�r.
	}
	
	public boolean isThereAHotel() 
	{
		return hotelExists;
	}
	
	public int getNumberOfHouses() 
	{
		// arazide kurulu ev say�s�n� d�nd�r�r.
		return houses.size();
	}
	
	private void determineTheColorName() 
	{
		
		// class�n colorID'sine g�re, string h�linde rengi belirler.
		// arazinin ait oldu�u renk grubunun yaz� h�linde g�sterilebilmesi ad�na �nemlidir.
		// Game class� i�erisinde Land objeleri yarat�l�rken colorID olarak;
		/*
		 * mor renkteki arazilere 0,
		 * a��k mavi rengindeki arazilere 1,
		 * pembe arazilere 2,
		 * turuncu arazilere 3,
		 * k�rm�z� arazilere 4,
		 * sar� arazilere 5,
		 * ye�il arazilere 6,
		 * mavi arazilere ise 7 atanm��t�r.
		 * a�a��daki switch kullan�larak colorID string'e d�n��t�r�lmekte ve bu string colorName'e atanmaktad�r.
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
		// arazinin mevcut �cretini Land class� d���ndan ��renmek i�in kullan�l�r.
		// calculatePrice metodunun d�nd�rd��� de�er aynen d�nd�r�l�r.
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
		// k�sacas�, araziye belirtilen evi diker.
		houses.add(house);
	}
	
	public void buildHotel(Hotel hotel) 
	{
		// Bu metod, Player class� i�erisinden �a�r�l�r.
		
		
		// Bu metod;
		// Belirtilen evi bu arazi �zerine diker.
		// Bunun i�in, metoda parametre olarak verilen Hotel objesini bu arazinin hotel de�i�kenine atar. (this.hotel = hotel;)
		// arazide otel oldu�unu belirtmek i�in hotelExists de�i�kenini true olarak ayarlar. (hotelExists = true;)
		// Bir arazide ayn� anda hem ev hem otel olamaz, bundan dolay� bir araziye otel dikildi�inde o arazideki t�m evler yok edilmek zorundad�r.
		// Bunun i�in, arazinin houses arraylisti s�f�rlan�r ve ayn� zamanda arazinin sahibinin bu land'deki t�m evleri oyuncunun houses arraylistinden silinir.
		
		
		this.hotel = hotel;
		hotelExists = true;
		
		for (House h: houses) 
		{
			//Bu arazideki t�m evlerin sahibi s�f�rlan�r.
			h.setOwner(null);
		}
		
		hotel.getOwner().removeHousesInLand(this);
		// Bu arazinin sahibinin bu arazideki t�m evleri silinir.
		
		houses = new ArrayList<House>();
		// Bu arazinin houses arraylisti bo� bir House ArrayListine atan�r, b�ylece bu arazideki evler silinir.
		
	}
	
	public boolean getRentFromPlayer(Player player) 
	{
		// Bu metod �al��t�r�ld���nda;
		// metoda parametre olarak g�nderilen oyuncu, arazinin sahibine bu arazinin mevcut kira miktar� kadar kira �der.
		// e�er oyuncunun yeterli paras� yoksa false d�nd�r�l�r.
		// bkz. Player.java payRentTo() metodu
		return player.payRentTo(owner, getRentAmount());
	}

	public double getRentAmount() 
	{
		// calculateRentPerTurn metodundan d�nd�r�len de�er ne ise ayn� de�eri d�nd�r�r.
		return calculateRentPerTurn();
	}
	
	private double calculateRentPerTurnForHouses(int houseCount) 
	{	
		// bu metod, arazide houseCount kadar ev varken arazinin sahibine kazand�raca�� kira miktar�n� hesaplar.
		
		// Araziye dikilen her ev, arazinin sahibine kazand�rd��� kira miktar�n� (rentPerTurn) HOUSE_RENT_MULTIPLE_COEFFICIENT kat�na ��kar�r.
		// HOUSE_RENT_MULTIPLE_COEFFICIENT 1.5 olarak tan�mlanm�� oldu�undan,
		// araziye dikilen her ev arazinin sahibine kazand�rd��� kira miktar�n� 1.5 kat�na ��kar�r.
		
		// arazide 1 ev varken,
		// arazinin sahibine kazand�raca�� kira miktar� rentPerTurn*1.5 olacakt�r.
		// arazide 2 ev varken,
		// arazinin sahibine kazand�raca�� kira miktar� rentPerTurn*1.5*1.5 = rentPerTurn*((1.5)^2) olacakt�r.
		// arazide 3 ev varken,
		// arazinin sahibine kazand�raca�� kira miktar� rentPerTurn*1.5*1.5*1.5 = rentPerTurn*((1.5)^3) olacakt�r.
		// arazide x ev varken,
		// arazinin sahibine kazand�raca�� kira miktar� rentPerTurn*((1.5)^x) olacakt�r.
		
		// arazide houseCount kadar ev varken,
		// arazinin sahibine kazand�raca�� kira miktar� rentPerTurn*((1.5)^houseCount) olacakt�r.
		// Java'da (1.5)^houseCount i�lemini �u �ekilde yapabiliriz:  Math.pow(1.5, houseCount)
		// Ayriyeten, Java'da bir virg�ll� say�y� (bu say� x olsun) virg�lden sonraki iki basama�a yuvarlamak i�in
		// �u kodu kullanabiliriz : Math.round(x*100.0)/100.0;
		// �rnek: x = 154.531598 olsun.
		// x*100.0 = 15453.1598 olur.
		// Math.round(x*100.0) = Math.round(15453.1598) = 15453
		// Math.round(x*100.0)/100.0 = 15453/100.0 = 154.53
		// B�ylece 154.53198 say�s�n� 154.53'e yuvarlam�� olduk.
		// Burada say�y� virg�lden sonraki iki basama�a yuvarlamam�z�n sebebi �u:
		// virg�ll� bir say�n�n kuvvetlerini alarak i�lem yapt���m�zdan dolay� virg�lden sonra �ok uzayan say�lar kar��m�za ��kabilir.
		// Bunu �nlemek istiyoruz.
		
		// a�a��daki sat�rlarda, yukar�da bahsedilen ve kira hesaplama i�lemini yap�yoruz.
		// double rpt = rentPerTurn diyip sonra return'l� sat�rda rpt yi kullanmak yerine
		// rpt'yi hi� tan�mlamay�p direkt rentPerTurn'� de return'l� sat�rda rpt yerine kullanabilirdik.
		
		double rpt = rentPerTurn;
		return Math.round(rpt*Math.pow(HOUSE_RENT_MULTIPLE_COEFFICIENT, houseCount)*100.0)/100.0;
	}
	
	private double calculatePriceOfLandWithHouses (int houseCount) 
	{
		// bu metod, arazide houseCount kadar ev varken arazinin fiyat�n�n ne kadar olaca��n� hesaplar.
		
		// bu metod, yukar�da anlat�lan metoda benzer bir �ekilde �al���r.
		// ama bu sefer, price katlan�r ve HOUSE_RENT_MULTIPLE_COEFFICIENT yerine HOUSE_PRICE_MULTIPLE_COEFFICIENT kullan�l�r.
		// ��nk� bu metodda arazinin fiyat�n� hesapl�yoruz, kiray� de�il.
		return Math.round(price * Math.pow(HOUSE_PRICE_MULTIPLE_COEFFICIENT, houseCount)*100.0)/100.0;
	}
	
	private double getPriceWithHotel() 
	{
		// arazide otel varken arazinin fiyat�n�n ne kadar olaca��n� hesaplar.
		// calculatePriceOfLandWithHouses metoduna benzer �al���r. Tek fark�, arazide sadece bir otel olabilece�i i�in, parametre olarak otel say�s�n� belirten bir de�er almamas�d�r.
		// do�rudan otel say�s� 1 olarak al�n�r ve i�lem o �ekilde yap�l�r.
		return Math.round((price * HOTEL_PRICE_MULTIPLE_COEFFICIENT)*100.0)/100.0;
	}
	
	private double getRentPerTurnWithHotel () 
	{
		// arazide otel varken arazinin sahibine kazand�raca�� kiran�n ne kadar olaca��n� hesaplar.
		return Math.round((rentPerTurn * HOTEL_RENT_MULTIPLE_COEFFICIENT)*100.0)/100.0;
	}
	
	private double calculateRentPerTurn() 
	{
		// arazinin �imdiki ev/otel durumuna g�re sahibine kazand�raca�� kira miktar�n� hesaplar.
		
		
		double rpt = rentPerTurn;
		// e�er a�a��daki iki if'e de girilmezse, arazide otel ya da ev yok demektir.
		// bu durumda rpt'nin de�eri de�i�meyecek ve rentPerTurn olarak kalacak.
		// rentPerTurn de arazinin bo� h�liyle sahibine kazand�rd��� kira miktar�.
		// bu nedenle arazide ev ya da otel yokken, bu metod arazinin bo� h�lindeki kira miktar�n� d�nd�rm�� olacak.
		
		if (isThereAHouse()) 
		{
			// e�er ev varsa, arazideki ev say�s�n� calculateRentPerTurnForHouses metoduna parametre olarak g�nderip arazideki mevcut ev say�s�na g�re arazinin kira miktar�n� hesapl�yoruz.
			// hesaplanan ev miktar� rpt de�i�kenine atan�yor.
			rpt = calculateRentPerTurnForHouses(getNumberOfHouses());
		}
		else if (hotelExists) 
		{
			// e�er ev yoksa ve otel varsa, arazinin otelli h�lindeki kira miktar� rpt'ye atan�r.
			rpt = getRentPerTurnWithHotel();
		}
		
		// en sonda da yukar�daki koda g�re hesaplanm�� olan rpt de�i�keni d�nd�r�l�r.
		return rpt;
		
	}
	
	private double calculatePrice() 
	{
		// calculateRentPerTurn metoduna benzer �al���r.
		// tek fark�, kiray� de�il fiyat� hesaplamas�d�r.
		
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
		// bu metod taraf�ndan d�nd�r�len string, oyun board'undaki land'lerin �zerine t�kland���nda a��lan bilgi penceresinde yazd�r�l�r.
		
		
		String info = super.getInformation();
		// Property class�n�n getInformation metodu �al��t�r�l�r ve buradan elde edilen string info'ya atan�r.
		// bkz. Property.java:getInformation() metodu
		// a�a��daki sat�rlarda, gereken t�m di�er bilgiler info stringine ekleniyor.
		
		
		info += "Color Group: "+ colorName + "\n";
		// arazinin ait oldu�u renk grubu info'ya ekleniyor.
		
		info += "Price when:\n\tThere is no building on this land: $" + price + "\n";
		
		// a�a��daki for d�ng�s�nde, 1'den 4'e kadar (MAXIMUM_NUMBER_OF_HOUSES=4 oldu�u i�in) arazide bulunan ev say�s�na g�re arazinin fiyat� info'ya ekleniyor.
		for (int i = 1; i <= MAXIMUM_NUMBER_OF_HOUSES; i++) 
		{
			String word, isAre;
			
			// e�er 1 ev i�in olan ko�ulu yazd�r�yorsak (i = 1), There is 1 house diye yazd�r�l�yor.
			// e�er ev say�s� 1'den fazlaysa (i > 1), There are x houses diye yazd�r�l�yor.
			// a�a��da is/are ve house/houses ayr�m� yap�l�yor.
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
			// �rnek ��kt�: (i = 2 i�in) There are 2 houses built on this land: $(bu arazinin 2 evli �creti)
			// �rnek ��kt�: (i = 1 i�in) There is 1 house built on this land: $(bu arazinin 1 evli �creti)
			info += calculatePriceOfLandWithHouses(i) + "\n";
		}
		
		info += "\tThere is a hotel built on this land: $" + getPriceWithHotel() + "\n";
		info += "Current price: $" + calculatePrice() + "\n";
		
		// a�a��daki for d�ng�s�nde, 1'den 4'e kadar (MAXIMUM_NUMBER_OF_HOUSES=4 oldu�u i�in) arazide bulunan ev say�s�na g�re arazinin kazand�rd��� kira miktar� info'ya ekleniyor.
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
		
		// bu arazide ev yoksa, "There is no house on this land." yazd�r�l�yor.
		
		if (!isThereAHouse()) 
		{
			info += "There is no house on this land.\n";
		}
		// varsa, arazide ka� tane ev oldu�u sahibiyle birlikte yazd�r�l�yor.
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
				// landin sahibi varsa, arazide ka� ev oldu�u sahibiyle beraber yazd�r�l�yor
			else
				info += "There " + isAre + " " + houses.size() + " unowned " + word + " on this land.\n";
				// landin sahibi yoksa, arazide ka� ev oldu�u yazd�r�l�yor. evlerin sahipsiz oldu�u da belirtiliyor.
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
