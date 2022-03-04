import java.io.Serializable;
import java.util.Random;

public class Die implements Serializable
{
	//Zar class�.
	//Game class�nda kullan�l�yor.
	
	private int face;
	//Zar�n �zerindeki say�y� tutar.
	
	public Die() 
	{
		// Bo� constructor. 
		// �ntan�ml� olarak zar�n �zerindeki say�y� 1 yapar (bkz. this(1): Die(int face) constructor'�n� 1 vererek �a��r�r. this.face = 1; sat�r� �al��t�r�lm�� olur.)
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
		// 1 ile 6 aras�nda rastgele bir say� �retilir ve bu �retilen say� face'e atan�r.
		// Metod ayn� zamanda yeni zar de�erini de d�nd�r�r.
		Random generator = new Random();
		face = generator.nextInt(6) + 1;
		return face;
		
	}
	
	
}
