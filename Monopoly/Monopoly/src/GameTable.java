import java.io.Serializable;
import java.util.ArrayList;

public class GameTable implements Serializable{
	// Bu class, oyunun board'undaki karelerin tutuldu�u classt�r.
	
	private ArrayList<SquareField> fields;
	
	// fields ArrayListi, Game class�nda yarat�l�p GameTable objesinin constructor'�na verilerek bu objeye atan�r.
	/* bkz. Game.java: 
	 * gameTable = new GameTable(squares);
	 */
	
	public GameTable (ArrayList<SquareField> fields) 
	{
		this.fields = fields;
	}
	
	public void placePawnOn(Pawn pawn, int location) 
	{	
		// Belirtilen piyonu, belirtilen location'a yerle�tirir.
		// fields arraylisti, Game class�nda her bir kare alan�n location'� ayn� zamanda listedeki index'ine e�it olacak �ekilde tasarlanm��t�r.
		// bu sayede, fields.get(location) dendi�inde, istenen SquareField objesine eri�ilir.
		// Ard�ndan, SquareField'in placePawn metodu kullan�larak belirtilen piyon o kareye yerle�tirilir.
		// Yap�lan yerle�tirme i�lemi sanald�r. Yani sadece konum bilgisinin de�i�tirilmesiyle ger�ekle�tirilir.
		// Piyonlar�n konumlar�n�n board'da g�r�lmesi, BoardJPanel.java class�nda onlar�n �izdirilmesiyle ger�ekle�ir.
		fields.get(location).placePawn(pawn);	
		
		// bkz. SquareField.java placePawn() metodu.
	}
	
	public String getInformationOf(int loc) 
	{
		
		// belirtilen location'daki SquareField objesine eri�ir (bkz. fields.get(loc))
		// ard�ndan bu objenin getObject metodun �al��t�rarak bu square'de tutulan GameObject objesine eri�ilir (bkz. fields.get(loc).getObject())
		// ard�ndan, eri�ilen bu GameObject objesinin getInformation metodu �al��t�r�l�r ve al�nan sonu� d�nd�r�lm�� olur. (bkz. return fields.get(loc).getObject().getInformation();)
		return fields.get(loc).getObject().getInformation();
		
	}
	
	public int getLocationFromPos(int x, int y) 
	{
		// Bu metod, verilen x y konumlar�n�n hangi location index'ine denk oldu�unu tespit eder.
		// Oyun board'unda mouse ile bir yere t�kland���nda, e�er t�klanan yer oyun karesi ise, o kare hakk�nda bilgi penceresi g�sterilir.
		// T�klanan b�lgenin hangi SquareField'�n s�n�rlar� i�erisinde oldu�unu belirlemek i�in bu metod kullan�l�r.

		/* bkz. Game.java:
		 * // alt sat�rdaki x ve y, mouse'un t�kland��� b�lgenin x ve y konumudur.
		 *  // hem x hem de y de�eri 0-600 aras�nda de�i�kenlik g�sterir.
		 * 
		 * int loc = gameTable.getLocationFromPos(x, y); 
		 * // getLocationFromPos metodu, e�er belirtilen x y koordinatlar� herhangi bir SquareField'e ait de�ilse -1 d�nd�r�r. Bu durumda herhangi bir bilgi penceresi g�sterilmez.
		        
	        if (loc != -1) // e�er t�klanan yer bir SquareField ise, bilgi penceresini g�ster:
	        {
	        	InformationWindow info = new InformationWindow();
	        	info.setResizable(false);
	        	info.setTitle("Information");
	        	info.setTextOfTextArea(gameTable.getInformationOf(loc));
	        	info.pack();
	        	info.setVisible(true);
	        }
		 */
		
		int location = -1;
		// location'� -1'den ba�lat�yoruz. 
		// e�er a�a��daki for d�ng�s� i�erisinde (x, y) koordinat�n� i�eren
		// herhangi bir yer bulunamazsa, location -1 olarak kalacak
		// ve t�klanan b�lgenin herhangi bir kareye ait olmad���na dair -1 d�nd�r�lecek.
		
		// A�a��daki for d�ng�s�yle fields arraylistindeki t�m SquareField objelerini tar�yoruz:
		for(SquareField f: fields)
			// e�er bu SquareField'lar aras�ndan (x, y) koordinat�n� i�eren bir SquareField bulunursa, location'� o SquareField'�n location'u olarak ayarl�yoruz.
			// Not: location de�eri 0 ile 39 aras�nda de�i�kenlik g�sterir. (inclusive)
			//      Start karesinin location de�eri 0'd�r.
			//      Ard�ndan, saat y�n�nde giderek her bir karenin location de�eri kendisinden �ncekinin 1 fazlas�d�r.
			if (f.containsPoint(x, y))
				location = f.getLocation();
		
		return location;
	}
	
	
	public ArrayList<SquareField> getFields() 
	{
		return fields;
	}
	
	public void setFields(ArrayList<SquareField> fields) 
	{
		this.fields = fields;
	}
	
	public void addField(SquareField field) 
	{
		// verilen SquareField'� fields ArrayListine ekler. 
		// Bu metod program�n hi�bir yerinde kullan�lmamaktad�r, semboliktir.
		fields.add(field);
	}
	
	public SquareField getSquare(int location) 
	{
		return fields.get(location);
	}
	
	private ArrayList<Land> getLands() 
	{
		
		// Bu metod, yaln�zca a�a��daki getLandsInColorGroup metodu taraf�ndan kullan�lmaktad�r.
		// fields arraylistini tarar
		// ve land tipinde obje i�eren SquareField objelerinin bar�nd�rd��� Land objelerini 
		// bir ArrayList'e koyup d�nd�r�r.
		
		ArrayList<Land> lands = new ArrayList<Land>(); // Land'ler bu arrayliste konacak.
		
		// fields arraylistindeki t�m SquareField objelerini tar�yoruz.
		for (SquareField f: fields) 
		{
			GameObject obj = f.getObject();
			// taranmakta olan SquareField'in objesini getObject metodunu kullanarak al�r.
			// e�er obje tipi land ise, bu GameObject tipindeki obj, bir Land() objesini i�aret etmektedir. 
			// (obj: pointer, point etti�i: bir Land() objesi.)
			// O zaman, bu obj de�i�kenini (Land) e cast edebiliriz. (Polymorphism)
			// ve (Land) e cast edilmi� h�liyle lands arraylistine ekleyebiliriz.
			
			// e�er taranmakta olan SquareField (f)'�n objesi (obj) land tipinde ise, lands arraylistine bu objeyi (Land) olarak cast edip ekle.
			if (obj.getType().equals("land")) 
			{
				lands.add((Land)obj);
			}
		}
		
		
		return lands;
	}
	
	public ArrayList<Land> getLandsInColorGroup(int colorID) 
	{
		
		// Belirtilen colorID'ye sahip olan t�m land'leri bir arraylist'e ekleyip d�nd�r�r.
		// Player class'�n�n canBuildHome() ve canBuildHotel() metodlar�nda kullan�l�r.
		// Bu metodun amac�:
		// oyuncunun ayn� renk grubundaki arazilere sahip olup olmad���n� kontrol ederken
		// ihtiya� duyulan ayn� renk grubundaki araziler listesinin kolayca elde edilebilmesidir.
		
		ArrayList<Land> lands = new ArrayList<Land>();
		// belirtilen renk grubundaki t�m araziler bu arrayliste konacak ve d�nd�r�lecek.
		
		ArrayList<Land> allLands = getLands();
		// Yukar�daki getLands() metodu arac�l���yla oyunda bulunan t�m arazi objeleri allLands arraylistine konur.
	
		
		for (Land l: allLands) 
		{
			if (l.getColorID() == colorID) 
			{
				// colorID'si parametre olarak bu metoda verilen colorID'ye e�it olan t�m arazileri lands'e ekler.
				lands.add(l);
			}
		}
		
		return lands;
	}
	
}
