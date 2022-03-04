import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

public class SquareField implements Serializable{

	// Oyundaki dikd�rtgen/kare alanlar� temsil eder.
	
	
	private int location; // location id. 0'dan 39'a kadar de�er alabilir. start karesi 0'd�r. ard�ndan saat y�n�nde her karenin location'� 1 artarak gider.
	private Point leftTop, rightTop, leftBottom, rightBottom; // dikd�rtgen alan�n k��e noktalar�
	private GameObject object; // bu karede bulunan GameObject objesi
	private ArrayList<Pawn> pawns; // bu alanda bulunan piyonlar� tutmak i�in bir arraylist

	public SquareField(int location, Point rightTop, Point leftBottom) 
	{
		// constructor'a location id'si, sa� �st k��e, sol alt k��e verilir.
		// her bir karenin sa� �st ve sol alt k��
		this.location = location;
		this.rightTop = rightTop;
		this.leftBottom = leftBottom;
		
		determineOtherPoints(); // sa� �st ve sol alt k��eler kullan�larak sol �st ve sa� alt k��eler belirlenecek.
		pawns = new ArrayList<Pawn>(); // piyonlar listesi yarat�l�r.
	}
	
	private void determineOtherPoints() 
	{
		/*
		1---------------------2
		|                     |
		|                     |
		|                     |
		4---------------------3
		
		1: leftTop
		2: rightTop: (a, b) olsun
		3: rightBottom
		4: leftBottom: (c, d) olsun
		
		1 ve 2 ayn� yatay do�rultuda oldu�undan 1'in y koordinat� ile 2'nin y koordinat� birbirine e�ittir.
		1 ve 4 ayn� dikey do�rultuda oldu�undan 1'in x koordinat� ile 4'�n x koordinat� birbirine e�ittir.
		o zaman 1: leftTop: (c, b) olur (yani (leftBottom.x, rightTop.y))
		ayn� sebepten 3: rightBottom: (a, d) olur (yani (rightTop.x, leftBottom.y))
		
		*/
		leftTop = new Point(leftBottom.x, rightTop.y);
		rightBottom = new Point(rightTop.x, leftBottom.y);
	}
	
	public void setLocation (int location) 
	{
		this.location = location;
	}
	
	public int getLocation () 
	{
		return location;
	}
	
	public void setObject (GameObject object) 
	{
		this.object = object;
	}
	
	
	public GameObject getObject() 
	{
		return object;
	}
	
	public void placePawn(Pawn pawn) 
	{
		if (!isPawnContained(pawn)) 
		{
			SquareField oldPosition = pawn.getSquareField();
			oldPosition.removePawn(pawn);
			pawn.determineThePositionOn(this);
			pawns.add(pawn);
			pawn.setSquareField(this);
			pawn.setLocation(location);
		}
	}
	
	private boolean isPawnContained(Pawn pawn) 
	{
		boolean contained = false;
		
		for (Pawn p : pawns) 
			if (p.equals(pawn))
				contained = true;
			
	
		return contained;
	}
	
	public void removePawn(Pawn pawn)
	{
		int index = -1;
		for (int i = 0; i < pawns.size(); i++) 
		{
			if (pawns.get(i).equals(pawn)) 
			{
				index = i;	
			}
		}
		
		if (index != -1)
			pawns.remove(index);
		
		replacePawns();
	}
	
	private void replacePawns() 
	{
		ArrayList<Pawn> tempPawns = new ArrayList<Pawn>();
		for (Pawn p : pawns) 
		{
			tempPawns.add(p);
		}
		
		pawns = new ArrayList<Pawn>();

		for (Pawn pawn: tempPawns) 
		{
			pawn.determineThePositionOn(this);
			pawns.add(pawn);
			pawn.setSquareField(this);
			pawn.setLocation(location);
		}
	}
	
	public ArrayList<Pawn> getPawns() 
	{
		return pawns;
	}
	
	public boolean containsPoint(int x, int y) 
	{
		// Belirtilen x,y koordinat�n�n bu dikd�rtgenin i�erisinde bulunup bulunmad���n� tespit eder.
		// GameTable class�nda getLocationFromPos() metodunda kullan�l�r (bkz. GameTable.java: getLocationFromPos() metodundaki a��klamalar) 
		return leftTop.x < x && x < rightTop.x && leftTop.y< y && y < leftBottom.y;
		
		// e�er gelen x parametresi sol �st noktan�n x'i ile sa� �st noktan�n x'i aras�nda ise ve gelen y parametresi sol �st noktan�n y'si ile sol alt noktan�n y'si aras�nda ise verilen (x, y) koordinat� bu karenin i�erisindedir.
	}
	
	public Point getRightTop() 
	{
		return rightTop;
	}
	
	public Point getRightBottom() 
	{
		return rightBottom;
	}
	
	public Point getLeftTop() 
	{
		return leftTop;
	}
	
	public Point getLeftBottom() 
	{
		return leftBottom;
	}
	
	public int getXDistanceToRightBorder(int x) 
	{
		return rightTop.x - x;
	}
	
	public int getXDistanceToLeftBorder(int x) 
	{
		return leftTop.x - x;
	}
	
	public int getYDistanceToBottomBorder(int y) 
	{
		return rightBottom.y - y;
	}
	
	public int getYDistanceToTopBorder(int y) 
	{
		return rightTop.y - y; 
	}
	
	public boolean areTherePawnsAtTheLeftSideOf(int x, int y) 
	{
		boolean areThere = false;
		for (Pawn p: pawns) 
		{
			Point pos = p.getPosition();
			if (pos.x < x && y == pos.y)
				areThere = true;
		}
		
		return areThere;
	}
	
	public boolean areTherePawnsAboveOf(int x, int y) 
	{
		boolean areThere = false;
		for (Pawn p: pawns) 
		{
			Point pos = p.getPosition();
			if (pos.y < y && x == pos.x)
				areThere = true;
		}
		return areThere;
	}
	
}
