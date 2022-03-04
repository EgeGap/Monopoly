import java.io.Serializable;


//implements Serializable kýsmý oyunun kaydedilebilmesi için.
//Obje hâlinde kaydý yapýlan tüm classlar Serializable interface'ini implement etmek zorundadýr.
//Serializable interface'i bizim yazdýðýmýz bir interface deðil, Java'nýn kendisinde bulunan bir interface. (bkz. 1.satýr:import java.io.Serializable;)
public class Account implements Serializable
{
	
	//Account classýný oyuncularýn para miktarýný tutmak için kullanýyoruz.
	//Her oyuncunun bir Account objesi var.
	//Ayný zamanda Free Parking alanýndaki parayý tutmak için de Account classýný kullanýyoruz.
	//FreeParking objesinin de bir Account objesi var.

	private double money;
	private Player owner;
	
	// bu classýn objeleri üç farklý þekilde dýþarýdan tanýmlanabilir. (3 farklý constructor olduðu için)
	
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
		this(player); //Account(Player player) constructor'ýna player'ý gönderir. owner = player satýrý çalýþtýrýlmýþ olur.
		this.money = money;
	}
	
	public boolean deposit (double amount) 
	{
		money += amount; //verilen miktardaki parayý hesaba yatýrýr.
		return true; //iþlemin baþarýlý olduðuna dair, sembolik. pek bir iþlevi yok: zaten yatýrma iþlevi her türlü baþarýlý olacaðý için.
	}
	
	public boolean withdraw (double amount) 
	{
		boolean successful = true; //baþlangýçta deðerini true olarak atadýk bi sorun çýkarsa false yapýyoruz en sonda da return ediyoruz.
		if (amount <= money) //eðer belirtilen miktar hesaptaki paradan daha azsa veya hesaptaki paraya eþitse o zaman hesaptan para çekme iþlemi gerçekleþtirilebilir.
		{
			money -= amount; //hesaptan belirtilen miktar kadar parayý çekiyoruz. Bu satýrýn eþ anlamlýsý: money = money - amount;
		}
		else // eðer belirtilen para miktarý hesaptaki paradan büyükse,
		{
			successful = false; //parayý çekemem ve iþlem baþarýsýz demektir.
		}
		
		return successful; //en sonda successful'u döndürüyoruz. programýn çeþitli bölgelerinde kullanýcýnýn iflas edip etmediðini bu metod sayesinde anlýyoruz.
							//eðer kullanýcýnýn banka hesabýndan para çekmeye çalýþýrken bu iþlem false döndürüyorsa, kullanýcýnýn yeterli parasý yok demektir ve kullanýcý iflas etmiþ demektir.
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
	
	public String toString() //ihtiyaç olursa diye sembolik olarak tanýmlanmýþ bir toString metodu
							// aslýnda programýn herhangi bir yerinde kullanýlmýyor.
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
