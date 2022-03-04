import java.io.Serializable;
import java.util.ArrayList;

public class GameTable implements Serializable{
	// Bu class, oyunun board'undaki karelerin tutulduðu classtýr.
	
	private ArrayList<SquareField> fields;
	
	// fields ArrayListi, Game classýnda yaratýlýp GameTable objesinin constructor'ýna verilerek bu objeye atanýr.
	/* bkz. Game.java: 
	 * gameTable = new GameTable(squares);
	 */
	
	public GameTable (ArrayList<SquareField> fields) 
	{
		this.fields = fields;
	}
	
	public void placePawnOn(Pawn pawn, int location) 
	{	
		// Belirtilen piyonu, belirtilen location'a yerleþtirir.
		// fields arraylisti, Game classýnda her bir kare alanýn location'ý ayný zamanda listedeki index'ine eþit olacak þekilde tasarlanmýþtýr.
		// bu sayede, fields.get(location) dendiðinde, istenen SquareField objesine eriþilir.
		// Ardýndan, SquareField'in placePawn metodu kullanýlarak belirtilen piyon o kareye yerleþtirilir.
		// Yapýlan yerleþtirme iþlemi sanaldýr. Yani sadece konum bilgisinin deðiþtirilmesiyle gerçekleþtirilir.
		// Piyonlarýn konumlarýnýn board'da görülmesi, BoardJPanel.java classýnda onlarýn çizdirilmesiyle gerçekleþir.
		fields.get(location).placePawn(pawn);	
		
		// bkz. SquareField.java placePawn() metodu.
	}
	
	public String getInformationOf(int loc) 
	{
		
		// belirtilen location'daki SquareField objesine eriþir (bkz. fields.get(loc))
		// ardýndan bu objenin getObject metodun çalýþtýrarak bu square'de tutulan GameObject objesine eriþilir (bkz. fields.get(loc).getObject())
		// ardýndan, eriþilen bu GameObject objesinin getInformation metodu çalýþtýrýlýr ve alýnan sonuç döndürülmüþ olur. (bkz. return fields.get(loc).getObject().getInformation();)
		return fields.get(loc).getObject().getInformation();
		
	}
	
	public int getLocationFromPos(int x, int y) 
	{
		// Bu metod, verilen x y konumlarýnýn hangi location index'ine denk olduðunu tespit eder.
		// Oyun board'unda mouse ile bir yere týklandýðýnda, eðer týklanan yer oyun karesi ise, o kare hakkýnda bilgi penceresi gösterilir.
		// Týklanan bölgenin hangi SquareField'ýn sýnýrlarý içerisinde olduðunu belirlemek için bu metod kullanýlýr.

		/* bkz. Game.java:
		 * // alt satýrdaki x ve y, mouse'un týklandýðý bölgenin x ve y konumudur.
		 *  // hem x hem de y deðeri 0-600 arasýnda deðiþkenlik gösterir.
		 * 
		 * int loc = gameTable.getLocationFromPos(x, y); 
		 * // getLocationFromPos metodu, eðer belirtilen x y koordinatlarý herhangi bir SquareField'e ait deðilse -1 döndürür. Bu durumda herhangi bir bilgi penceresi gösterilmez.
		        
	        if (loc != -1) // eðer týklanan yer bir SquareField ise, bilgi penceresini göster:
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
		// location'ý -1'den baþlatýyoruz. 
		// eðer aþaðýdaki for döngüsü içerisinde (x, y) koordinatýný içeren
		// herhangi bir yer bulunamazsa, location -1 olarak kalacak
		// ve týklanan bölgenin herhangi bir kareye ait olmadýðýna dair -1 döndürülecek.
		
		// Aþaðýdaki for döngüsüyle fields arraylistindeki tüm SquareField objelerini tarýyoruz:
		for(SquareField f: fields)
			// eðer bu SquareField'lar arasýndan (x, y) koordinatýný içeren bir SquareField bulunursa, location'ý o SquareField'ýn location'u olarak ayarlýyoruz.
			// Not: location deðeri 0 ile 39 arasýnda deðiþkenlik gösterir. (inclusive)
			//      Start karesinin location deðeri 0'dýr.
			//      Ardýndan, saat yönünde giderek her bir karenin location deðeri kendisinden öncekinin 1 fazlasýdýr.
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
		// verilen SquareField'ý fields ArrayListine ekler. 
		// Bu metod programýn hiçbir yerinde kullanýlmamaktadýr, semboliktir.
		fields.add(field);
	}
	
	public SquareField getSquare(int location) 
	{
		return fields.get(location);
	}
	
	private ArrayList<Land> getLands() 
	{
		
		// Bu metod, yalnýzca aþaðýdaki getLandsInColorGroup metodu tarafýndan kullanýlmaktadýr.
		// fields arraylistini tarar
		// ve land tipinde obje içeren SquareField objelerinin barýndýrdýðý Land objelerini 
		// bir ArrayList'e koyup döndürür.
		
		ArrayList<Land> lands = new ArrayList<Land>(); // Land'ler bu arrayliste konacak.
		
		// fields arraylistindeki tüm SquareField objelerini tarýyoruz.
		for (SquareField f: fields) 
		{
			GameObject obj = f.getObject();
			// taranmakta olan SquareField'in objesini getObject metodunu kullanarak alýr.
			// eðer obje tipi land ise, bu GameObject tipindeki obj, bir Land() objesini iþaret etmektedir. 
			// (obj: pointer, point ettiði: bir Land() objesi.)
			// O zaman, bu obj deðiþkenini (Land) e cast edebiliriz. (Polymorphism)
			// ve (Land) e cast edilmiþ hâliyle lands arraylistine ekleyebiliriz.
			
			// eðer taranmakta olan SquareField (f)'ýn objesi (obj) land tipinde ise, lands arraylistine bu objeyi (Land) olarak cast edip ekle.
			if (obj.getType().equals("land")) 
			{
				lands.add((Land)obj);
			}
		}
		
		
		return lands;
	}
	
	public ArrayList<Land> getLandsInColorGroup(int colorID) 
	{
		
		// Belirtilen colorID'ye sahip olan tüm land'leri bir arraylist'e ekleyip döndürür.
		// Player class'ýnýn canBuildHome() ve canBuildHotel() metodlarýnda kullanýlýr.
		// Bu metodun amacý:
		// oyuncunun ayný renk grubundaki arazilere sahip olup olmadýðýný kontrol ederken
		// ihtiyaç duyulan ayný renk grubundaki araziler listesinin kolayca elde edilebilmesidir.
		
		ArrayList<Land> lands = new ArrayList<Land>();
		// belirtilen renk grubundaki tüm araziler bu arrayliste konacak ve döndürülecek.
		
		ArrayList<Land> allLands = getLands();
		// Yukarýdaki getLands() metodu aracýlýðýyla oyunda bulunan tüm arazi objeleri allLands arraylistine konur.
	
		
		for (Land l: allLands) 
		{
			if (l.getColorID() == colorID) 
			{
				// colorID'si parametre olarak bu metoda verilen colorID'ye eþit olan tüm arazileri lands'e ekler.
				lands.add(l);
			}
		}
		
		return lands;
	}
	
}
