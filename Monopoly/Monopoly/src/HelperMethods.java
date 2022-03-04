import java.util.ArrayList;

public class HelperMethods {

	public static int countValue(int value, ArrayList<Integer> arr) 
	{
		//countValue metodu, bu classtaki getIndexesOfNumber metodlar� taraf�ndan kullan�l�r.
		// ayn� zamanda, Game class�nda oyunun ilk ba�lang�c�nda maksimum zar� birden fazla
		// oyuncunun at�p atmad���n� kontrol etmek i�in kullan�l�r.
		
		// arr ArrayListinde value de�erinin ka� kere ge�ti�ini sayar ve d�nd�r�r.
		
		int counter = 0;
		for (Integer i: arr) 
		{
			//ArrayList'te Integer objeleri tutuluyor.
			//ArrayList'teki elemanlar Integer tipinde oldu�u i�in, normal bir intle
			// kar��la�t�rma yaparken bu Integer'� (int) e cast ediyoruz.
			if (value == (int) i) 
			{
				counter++;
			}
		}
		return counter;
	}
	
	public static int countValue(double value, ArrayList<Double> arr) 
	{
		// yukar�daki metodun overloaded h�li. �al��ma mant��� yukar�daki metodla ayn�d�r.
		// Farklar Double d:arr ve (double) d k�sm�d�r.
		// Double de�erlerin bulundu�u bir arraylistte belirtilen de�erin (value) ka� kere ge�ti�ini sayar.
		
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
		// Bu metod sadece Game class� i�erisinde kullan�lmaktad�r.
		
		
		
		// Ayn� zamanda, ba�lang��ta en y�ksek zar� atan oyuncuyu belirlerken
		// birden fazla en y�ksek zar� atan oyuncu oldu�unda
		// bu oyuncular�n kimler oldu�unu belirlerken kullan�l�r. (getMax ile beraber)
		
		// Ayriyeten, en y�ksek zar� atan tek oyuncu oldu�unda yine bu oyuncunun kim oldu�unu bulmak i�in kullan�l�r.
		// value olarak verilen de�erin bulundu�u indexleri bir listeye atar.
		
		
		// �ncelikle listede value de�erinin ka� kere ge�ti�i say�l�yor.
		// indexleri tutan arrayin boyutu da count kadar olmal�.
		int count = countValue(value, arr);
		
		//indexlerin tutulaca�� array olu�turuluyor. count elemanl�k bir array.
		int [] indexes = new int[count];
		
		
		//indexes arrayine eleman eklerken mevcut indexi tutmak i�in kullan�lacak bir de�i�ken.
		int counter = 0;
		
		//verilen ArrayList'i tarayan bir for d�ng�s�.
		for (int i = 0; i < arr.size(); i++)
		{
			
			
			// e�er arr arraylistindeki i indexli eleman aranan de�ere e�itse, indexes arrayine i eklenir.  
						// b�ylece aranan de�erin bulundu�u indexler indexes arrayine konmu� olur.
						// counter de�i�keni indexes arrayine yeni elemanlar eklemek i�in tutulan bir yard�mc� de�i�kendir.
						// indexes arrayine her ekleme yap�ld���nda counter bir art�r�l�r.
						// b�ylelikle indexes arrayine counter indisi kullan�larak her seferinde yeni de�erler eklenebilmektedir.
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
		// yukar�daki metodun overloaded h�lidir. yukar�daki metodun yapt��� i�i Double'lar�n olu�turdu�u bir ArrayList'te double bir de�er ile yapar.
		// fark : value == (double)arr.get(i)
		
		// Bu metod sadece Game class�nda kullan�l�r.
		// en y�ksek paraya sahip olan oyuncuya $100 verme kart� uygulan�rken 
		// en y�ksek paraya sahip olan oyuncular�n indexlerinin bulunmas�nda kullan�l�r. (getMax ile beraber)
		
		// Ayn� zamanda,
		// oyun kendili�inden bitmeden oyun bitirildi�i zaman kazanan� belirlemek i�in en y�ksek paraya sahip olanlar belirlenirken kullan�l�r.
		
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
		// verilen double arrayi i�erisindeki en b�y�k say�y� bulur ve d�nd�r�r.
		// maksimum say�n�n bulunmas� i�in a�a��da en b�y�k say�y� bulma algoritmas� kullan�l�r.
		
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
		// yukar�daki metodun overloaded h�li.
		
		
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
		
		// Sadece Game class�nda kullan�l�r.
		// En y�ksek zar� atan belirlenirken, birden fazla zar atan oyuncu olmas� durumunda
		// bu oyuncular�n indexleri belirlendikten sonra oyuncular�n
		// Player ArrayListi olarak eldesinde kullan�l�r.
		// Ayn� zamanda, en y�ksek zar� atan tek oyuncunun oldu�u durumda da bu oyuncunun eldesinde kullan�l�r.
		
		
		// ��lem tamamland���nda d�nd�r�lmek �zere bir Player ArrayListi olu�turuluyor.
		// Verilen players ArrayListi i�erisinde belirtilen indexe sahip oyuncular bu arrayliste eklenecek.
		ArrayList<Player> pickedPlayers = new ArrayList<Player>();

		// a�a��daki for d�ng�s�yle, indexes arrayi taran�yor ve indexes arrayinde belirtilen index numaralar� players.get() metodu i�erisine parametre olarak veriliyor.
		// bu yolla, belirtilen indexlerdeki oyuncular players arraylistinden se�ilip pickedPlayers arraylistine aktar�lm�� oluyor.
		for (int i = 0; i < indexes.length; i++) 
		{
			// �rnek: i=2 olsun.
			// indexes arrayinin 2 indisli eleman� 5 olsun.
			// bu durumda; i = 2 iken, players arraylistinin 5 indisli eleman� pickedPlayers arraylistine eklenir.
			pickedPlayers.add(players.get(indexes[i]));	
		}
		
		return pickedPlayers;
		
	}

	public static int mod (int n, int d) 
	{
		// n mod d i�leminin sonucunu d�nd�r�r.
		// �ki classta kullan�l�r: Game class� ve TokenMover class�
		// piyonlar� ileri-geri hareket ettirirken mesela 5 ad�m ileri git denildi�inde mevcut konuma 5 eklendi�inde sonu� 39'dan b�y�k ��kabilir.
		// 39'dan b�y�k bir sonucun gameTable'da bir kar��l��� yok. (40 kare oldu�u i�in ve kareleri 0'dan 39'a numaraland�rd���m�z i�in)
		// bu metod ile 39'dan b�y�k olan sonu� 39 ile 0 aras�ndaki bir sonuca indirgenir.
		
		
		int result = n % d;
		
		// Java'da mod i�leminin sonucu negatif ��kabilir.
		// Fakat bizim ihtiya� duydu�umuz pozitif mod.
		// Bu nedenle d'yi sonuca ekleyerek negatif modu pozitife �eviriyoruz.
		// �rnek: -465 % 17 i�leminin sonucu Java'da -6'd�r.
		// -6 + 17 = 11.
		// 11 yine -465 % 17 i�leminin sonucudur.
		
		if (result < 0)
			result = result + d;
		
		return result;

	}

}
