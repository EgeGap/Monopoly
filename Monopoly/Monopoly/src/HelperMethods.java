import java.util.ArrayList;

public class HelperMethods {

	public static int countValue(int value, ArrayList<Integer> arr) 
	{
		//countValue metodu, bu classtaki getIndexesOfNumber metodlarý tarafýndan kullanýlýr.
		// ayný zamanda, Game classýnda oyunun ilk baþlangýcýnda maksimum zarý birden fazla
		// oyuncunun atýp atmadýðýný kontrol etmek için kullanýlýr.
		
		// arr ArrayListinde value deðerinin kaç kere geçtiðini sayar ve döndürür.
		
		int counter = 0;
		for (Integer i: arr) 
		{
			//ArrayList'te Integer objeleri tutuluyor.
			//ArrayList'teki elemanlar Integer tipinde olduðu için, normal bir intle
			// karþýlaþtýrma yaparken bu Integer'ý (int) e cast ediyoruz.
			if (value == (int) i) 
			{
				counter++;
			}
		}
		return counter;
	}
	
	public static int countValue(double value, ArrayList<Double> arr) 
	{
		// yukarýdaki metodun overloaded hâli. Çalýþma mantýðý yukarýdaki metodla aynýdýr.
		// Farklar Double d:arr ve (double) d kýsmýdýr.
		// Double deðerlerin bulunduðu bir arraylistte belirtilen deðerin (value) kaç kere geçtiðini sayar.
		
		int counter = 0;
		for (Double d: arr) 
		{
			if (value == (double) d) 
			{
				counter++;
			}
		}
		return counter;
	}
	
	public static int[] getIndexesOfNumber(int value, ArrayList<Integer> arr) 
	{
		// Bu metod sadece Game classý içerisinde kullanýlmaktadýr.
		
		
		
		// Ayný zamanda, baþlangýçta en yüksek zarý atan oyuncuyu belirlerken
		// birden fazla en yüksek zarý atan oyuncu olduðunda
		// bu oyuncularýn kimler olduðunu belirlerken kullanýlýr. (getMax ile beraber)
		
		// Ayriyeten, en yüksek zarý atan tek oyuncu olduðunda yine bu oyuncunun kim olduðunu bulmak için kullanýlýr.
		// value olarak verilen deðerin bulunduðu indexleri bir listeye atar.
		
		
		// öncelikle listede value deðerinin kaç kere geçtiði sayýlýyor.
		// indexleri tutan arrayin boyutu da count kadar olmalý.
		int count = countValue(value, arr);
		
		//indexlerin tutulacaðý array oluþturuluyor. count elemanlýk bir array.
		int [] indexes = new int[count];
		
		
		//indexes arrayine eleman eklerken mevcut indexi tutmak için kullanýlacak bir deðiþken.
		int counter = 0;
		
		//verilen ArrayList'i tarayan bir for döngüsü.
		for (int i = 0; i < arr.size(); i++)
		{
			
			
			// eðer arr arraylistindeki i indexli eleman aranan deðere eþitse, indexes arrayine i eklenir.  
						// böylece aranan deðerin bulunduðu indexler indexes arrayine konmuþ olur.
						// counter deðiþkeni indexes arrayine yeni elemanlar eklemek için tutulan bir yardýmcý deðiþkendir.
						// indexes arrayine her ekleme yapýldýðýnda counter bir artýrýlýr.
						// böylelikle indexes arrayine counter indisi kullanýlarak her seferinde yeni deðerler eklenebilmektedir.
			if (value == (int)arr.get(i)) 
			{
				indexes[counter] = i;
				counter++;
			}
		}
		return indexes;
	}
	
	public static int[] getIndexesOfNumber(double value, ArrayList<Double> arr) 
	{
		// yukarýdaki metodun overloaded hâlidir. yukarýdaki metodun yaptýðý iþi Double'larýn oluþturduðu bir ArrayList'te double bir deðer ile yapar.
		// fark : value == (double)arr.get(i)
		
		// Bu metod sadece Game classýnda kullanýlýr.
		// en yüksek paraya sahip olan oyuncuya $100 verme kartý uygulanýrken 
		// en yüksek paraya sahip olan oyuncularýn indexlerinin bulunmasýnda kullanýlýr. (getMax ile beraber)
		
		// Ayný zamanda,
		// oyun kendiliðinden bitmeden oyun bitirildiði zaman kazananý belirlemek için en yüksek paraya sahip olanlar belirlenirken kullanýlýr.
		
		int count = countValue(value, arr);
		int [] indexes = new int[count];
		
		int counter = 0;
		for (int i = 0; i < arr.size(); i++) 
		{
			if (value == (double)arr.get(i)) 
			{
				indexes[counter] = i;
				counter++;
			}	
		}
		return indexes;
	}

	public static double getMax(double[] arr) 
	{
		// verilen double arrayi içerisindeki en büyük sayýyý bulur ve döndürür.
		// maksimum sayýnýn bulunmasý için aþaðýda en büyük sayýyý bulma algoritmasý kullanýlýr.
		
		double max = arr[0];
		for (int i = 1; i < arr.length; i++) 
		{
			if (arr[i] > max) 
			{
				max = arr[i];
			}
		}
		return max;
	}
	
	public static int getMax(ArrayList<Integer> arr) 
	{
		// yukarýdaki metodun overloaded hâli.
		
		
		int max = arr.get(0);
		for (int i = 1; i < arr.size(); i++) 
		{
			if (arr.get(i) > max) 
			{
				max = arr.get(i);
			}
		}
		return max;
	}
	

	public static ArrayList<Player> pickPlayers (int[] indexes, ArrayList<Player> players) 
	{
		
		// Sadece Game classýnda kullanýlýr.
		// En yüksek zarý atan belirlenirken, birden fazla zar atan oyuncu olmasý durumunda
		// bu oyuncularýn indexleri belirlendikten sonra oyuncularýn
		// Player ArrayListi olarak eldesinde kullanýlýr.
		// Ayný zamanda, en yüksek zarý atan tek oyuncunun olduðu durumda da bu oyuncunun eldesinde kullanýlýr.
		
		
		// Ýþlem tamamlandýðýnda döndürülmek üzere bir Player ArrayListi oluþturuluyor.
		// Verilen players ArrayListi içerisinde belirtilen indexe sahip oyuncular bu arrayliste eklenecek.
		ArrayList<Player> pickedPlayers = new ArrayList<Player>();

		// aþaðýdaki for döngüsüyle, indexes arrayi taranýyor ve indexes arrayinde belirtilen index numaralarý players.get() metodu içerisine parametre olarak veriliyor.
		// bu yolla, belirtilen indexlerdeki oyuncular players arraylistinden seçilip pickedPlayers arraylistine aktarýlmýþ oluyor.
		for (int i = 0; i < indexes.length; i++) 
		{
			// Örnek: i=2 olsun.
			// indexes arrayinin 2 indisli elemaný 5 olsun.
			// bu durumda; i = 2 iken, players arraylistinin 5 indisli elemaný pickedPlayers arraylistine eklenir.
			pickedPlayers.add(players.get(indexes[i]));	
		}
		
		return pickedPlayers;
		
	}

	public static int mod (int n, int d) 
	{
		// n mod d iþleminin sonucunu döndürür.
		// Ýki classta kullanýlýr: Game classý ve TokenMover classý
		// piyonlarý ileri-geri hareket ettirirken mesela 5 adým ileri git denildiðinde mevcut konuma 5 eklendiðinde sonuç 39'dan büyük çýkabilir.
		// 39'dan büyük bir sonucun gameTable'da bir karþýlýðý yok. (40 kare olduðu için ve kareleri 0'dan 39'a numaralandýrdýðýmýz için)
		// bu metod ile 39'dan büyük olan sonuç 39 ile 0 arasýndaki bir sonuca indirgenir.
		
		
		int result = n % d;
		
		// Java'da mod iþleminin sonucu negatif çýkabilir.
		// Fakat bizim ihtiyaç duyduðumuz pozitif mod.
		// Bu nedenle d'yi sonuca ekleyerek negatif modu pozitife çeviriyoruz.
		// Örnek: -465 % 17 iþleminin sonucu Java'da -6'dýr.
		// -6 + 17 = 11.
		// 11 yine -465 % 17 iþleminin sonucudur.
		
		if (result < 0)
			result = result + d;
		
		return result;

	}

}
