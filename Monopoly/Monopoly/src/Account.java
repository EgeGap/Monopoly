import java.io.Serializable;


//implements Serializable k�sm� oyunun kaydedilebilmesi i�in.
//Obje h�linde kayd� yap�lan t�m classlar Serializable interface'ini implement etmek zorundad�r.
//Serializable interface'i bizim yazd���m�z bir interface de�il, Java'n�n kendisinde bulunan bir interface. (bkz. 1.sat�r:import java.io.Serializable;)
public class Account implements Serializable
{
	
	//Account class�n� oyuncular�n para miktar�n� tutmak i�in kullan�yoruz.
	//Her oyuncunun bir Account objesi var.
	//Ayn� zamanda Free Parking alan�ndaki paray� tutmak i�in de Account class�n� kullan�yoruz.
	//FreeParking objesinin de bir Account objesi var.

	private double money;
	private Player owner;
	
	// bu class�n objeleri �� farkl� �ekilde d��ar�dan tan�mlanabilir. (3 farkl� constructor oldu�u i�in)
	
	public Account(Player player) 
	{
		owner = player;
	}
	
	public Account(double money) 
	{
		this.money = money;
	}
	
	public Account (Player player, double money) 
	{
		this(player); //Account(Player player) constructor'�na player'� g�nderir. owner = player sat�r� �al��t�r�lm�� olur.
		this.money = money;
	}
	
	public boolean deposit (double amount) 
	{
		money += amount; //verilen miktardaki paray� hesaba yat�r�r.
		return true; //i�lemin ba�ar�l� oldu�una dair, sembolik. pek bir i�levi yok: zaten yat�rma i�levi her t�rl� ba�ar�l� olaca�� i�in.
	}
	
	public boolean withdraw (double amount) 
	{
		boolean successful = true; //ba�lang��ta de�erini true olarak atad�k bi sorun ��karsa false yap�yoruz en sonda da return ediyoruz.
		if (amount <= money) //e�er belirtilen miktar hesaptaki paradan daha azsa veya hesaptaki paraya e�itse o zaman hesaptan para �ekme i�lemi ger�ekle�tirilebilir.
		{
			money -= amount; //hesaptan belirtilen miktar kadar paray� �ekiyoruz. Bu sat�r�n e� anlaml�s�: money = money - amount;
		}
		else // e�er belirtilen para miktar� hesaptaki paradan b�y�kse,
		{
			successful = false; //paray� �ekemem ve i�lem ba�ar�s�z demektir.
		}
		
		return successful; //en sonda successful'u d�nd�r�yoruz. program�n �e�itli b�lgelerinde kullan�c�n�n iflas edip etmedi�ini bu metod sayesinde anl�yoruz.
							//e�er kullan�c�n�n banka hesab�ndan para �ekmeye �al���rken bu i�lem false d�nd�r�yorsa, kullan�c�n�n yeterli paras� yok demektir ve kullan�c� iflas etmi� demektir.
	}
	
	public double getMoney()  
	{
		return money;
	}
	
	public void setMoney(double amount) 
	{
		money = amount;
	}
	
	public Player getOwner() 
	{
		return owner;
	}
	
	public void setOwner(Player player) 
	{
		this.owner = player;
	}
	
	public String toString() //ihtiya� olursa diye sembolik olarak tan�mlanm�� bir toString metodu
							// asl�nda program�n herhangi bir yerinde kullan�lm�yor.
	{
		if (owner != null) 
		{
			return String.format("%s's account : ", owner) + "$" + money;
		}
		
		else 
		{
			return "Free Parking's account : $" + money;
		}	
	}
	
}
