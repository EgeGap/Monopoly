import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

public class SquareField implements Serializable{

	// Oyundaki dikdörtgen/kare alanlarý temsil eder.
	
	
	private int location; // location id. 0'dan 39'a kadar deðer alabilir. start karesi 0'dýr. ardýndan saat yönünde her karenin location'ý 1 artarak gider.
	private Point leftTop, rightTop, leftBottom, rightBottom; // dikdörtgen alanýn köþe noktalarý
	private GameObject object; // bu karede bulunan GameObject objesi
	private ArrayList<Pawn> pawns; // bu alanda bulunan piyonlarý tutmak için bir arraylist

	public SquareField(int location, Point rightTop, Point leftBottom) 
	{
		// constructor'a location id'si, sað üst köþe, sol alt köþe verilir.
		// her bir karenin sað üst ve sol alt köþ
		this.location = location;
		this.rightTop = rightTop;
		this.leftBottom = leftBottom;
		
		determineOtherPoints(); // sað üst ve sol alt köþeler kullanýlarak sol üst ve sað alt köþeler belirlenecek.
		pawns = new ArrayList<Pawn>(); // piyonlar listesi yaratýlýr.
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
		
		1 ve 2 ayný yatay doðrultuda olduðundan 1'in y koordinatý ile 2'nin y koordinatý birbirine eþittir.
		1 ve 4 ayný dikey doðrultuda olduðundan 1'in x koordinatý ile 4'ün x koordinatý birbirine eþittir.
		o zaman 1: leftTop: (c, b) olur (yani (leftBottom.x, rightTop.y))
		ayný sebepten 3: rightBottom: (a, d) olur (yani (rightTop.x, leftBottom.y))
		
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
		// Belirtilen x,y koordinatýnýn bu dikdörtgenin içerisinde bulunup bulunmadýðýný tespit eder.
		// GameTable classýnda getLocationFromPos() metodunda kullanýlýr (bkz. GameTable.java: getLocationFromPos() metodundaki açýklamalar) 
		return leftTop.x < x && x < rightTop.x && leftTop.y< y && y < leftBottom.y;
		
		// eðer gelen x parametresi sol üst noktanýn x'i ile sað üst noktanýn x'i arasýnda ise ve gelen y parametresi sol üst noktanýn y'si ile sol alt noktanýn y'si arasýnda ise verilen (x, y) koordinatý bu karenin içerisindedir.
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
