import java.io.Serializable;
import java.util.Random;

public class Die implements Serializable
{
	//Zar classý.
	//Game classýnda kullanýlýyor.
	
	private int face;
	//Zarýn üzerindeki sayýyý tutar.
	
	public Die() 
	{
		// Boþ constructor. 
		// Öntanýmlý olarak zarýn üzerindeki sayýyý 1 yapar (bkz. this(1): Die(int face) constructor'ýný 1 vererek çaðýrýr. this.face = 1; satýrý çalýþtýrýlmýþ olur.)
		this(1);
	}
	
	public Die(int face) 
	{
		this.face = face;
	}
	
	public int getFace() 
	{
		return face;
	}
	
	public void setFace(int face) 
	{
		this.face = face;	
	}
	
	public int roll() 
	{
		// 1 ile 6 arasýnda rastgele bir sayý üretilir ve bu üretilen sayý face'e atanýr.
		// Metod ayný zamanda yeni zar deðerini de döndürür.
		Random generator = new Random();
		face = generator.nextInt(6) + 1;
		return face;
		
	}
	
	
}
