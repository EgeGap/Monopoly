import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;

public class Pawn implements Serializable
{
	private int location;
	// piyonun konumunu belirtir. (0 ile 39 aras�nda (inclusive) de�er al�r.)
	
	
	private SquareField squareField;
	// piyonun �zerinde bulundu�u SquareField objesini belirtir.
	
	private Player owner;
	//piyonun sahibini belirtir.
	
	private int colorID;
	//piyonun rengini belirtir.
	
	private String colorName;
	//piyonun renk ad�n� tutar.
	
	private Point position;
	//piyonun x,y cinsinden konumunu tutmak i�in.
	
	private Color color;
	// piyonun rengini Color class� olarak tutmak i�in.  (bkz. BoardJPanel.java'da piyonlar� �izerken p.getPawn().getColor() ile piyonun Color class� cinsinden rengi al�n�r ve bu �ekilde kolayl�kla piyonun renginde yuvarlak oyun ekran�na �izilir.)
	
	private boolean hidden;
	// piyonun gizlenmi� olup olmad���n� belirtmek i�in
	
	public Pawn(int location, SquareField square, Player owner, int colorID)  
	{
		this.location = location;
		squareField = square;
		this.owner = owner;
		this.colorID = colorID;
		determineTheColor();
		hidden = false;
		square.placePawn(this);
	}
	
	public void hide() 
	{
		// piyonu gizli yapar. Game class�nda oyundan ��kan oyuncular�n piyonunu gizlemek i�in kullan�l�r.
		hidden = true;
	}
	
	public boolean isHidden() 
	{
		// hidden de�i�keni i�in getter metodu. Piyonun gizlenmi� olup olmad���n� anlamak i�in kullan�l�r. (bkz. BoardJPanel.java: if (!p.getPawn().isHidden()))
		return hidden;
	}
	
	public void determineTheColor() 
	{
		
		// a�a��da colorID de�eri kullan�larak bu de�er String versiyona d�n��t�r�l�r.
		// a�a��da; colorID de�erinin tan�mlad��� renkler �u �ekilde tan�mlanm��t�r:
			/*	
			 * colorID 1 ise renk k�rm�z�d�r.
			 * colorID 2 ise renk mavidir.
			 * colorID 3 ise renk magentad�r.
			 * ..
			 * ..
			 * ..
			 * colorID 8 ise renk gridir.
			 */
		
		// 8'e kadar colorID tan�mlanm�� olmas�n�n sebebi, oyunda en fazla 8 oyuncunun olmas�d�r.
		// Game class�nda, her oyuncunun piyonunun colorID'si, oyuncunun ID'sine e�it olacak �ekilde ayarlanm��t�r.
		// B�ylece 1 numaral� oyuncunun piyonu k�rm�z�, 2 numaral� oyuncunun piyonu mavi, ..., 8 numaral� oyuncunun piyonu gri olur.
		
		switch (colorID) 
		{
			case 1:
				colorName = "Red";
				color = Color.RED;
				break;
			case 2:
				colorName = "Blue";
				color = Color.BLUE;
				break;
			case 3:
				colorName = "Magenta";
				color = Color.MAGENTA;
				break;
			case 4:
				colorName = "Orange";
				color = Color.ORANGE;
				break;
			case 5:
				colorName = "Green";
				color = Color.GREEN;
				break;
			case 6:
				colorName = "White";
				color = Color.WHITE;
				break;
			case 7:
				colorName = "Cyan";
				color = Color.CYAN;
				break;
			case 8:
				colorName = "Gray";
				color = Color.GRAY;
				break;
		}
	}
	
	public void determineThePositionOn(SquareField field) 
	{
		if (field.getPawns().size() == 0) 
		{
			position = new Point(field.getLeftTop().x+3, field.getLeftTop().y+3);
		}
		
		else 
		{
			Point lastPawnPosition = field.getPawns().get(field.getPawns().size()-1).getPosition();
			int x = lastPawnPosition.x;
			int y = lastPawnPosition.y;
			
			if (field.getXDistanceToRightBorder(x+10+5) > 5) 
			{
				position = new Point(x+15, y);
			}
			else if (field.getXDistanceToLeftBorder(x-10-5) > 5 && !field.areTherePawnsAtTheLeftSideOf(x, y))
			{
				position = new Point(x-15, y);
			}
			else if (field.getYDistanceToBottomBorder(y+10+5) > 5)
			{
				position = new Point(x, y+15);
			}
			else if (field.getYDistanceToTopBorder(y-10-5) > 5 && !field.areTherePawnsAboveOf(x, y))
			{
				position = new Point(x, y-15);
			}
			else 
			{
				System.out.println("Fatal error! Couldn't place the pawn.");
			}
		}
	}
	
	public Point getPosition() 
	{
		return position;
	}
	
	public void setPosition(Point pos) 
	{
		position = pos;
	}
	
	public int getLocation() 
	{
		return location;
	}
	
	public SquareField getSquareField() 
	{
		return squareField;
	}
	
	public Player getOwner() 
	{
		return owner;
	}
	
	public void setLocation(int location) 
	{
		this.location = location;
	}
	
	public void setSquareField(SquareField newSquareField) 
	{
		squareField = newSquareField;
	}
	
	public void setOwner(Player owner) 
	{
		this.owner = owner;
	}
	
	public Color getColor() 
	{
		return color;
	}
	
	public String getColorName() 
	{
		return colorName;
	}
	
	public boolean equals(Pawn p) 
	{
		// Piyonlar�n e�itli�i sahipleriyle kar��la�t�r�l�r.
		// e�er iki piyonun sahibi ayn�ysa, bu piyonlar ayn� piyon demektir.
		return p.getOwner().getID() == owner.getID();
	}
	
	public String toString() 
	{
		return String.format("Pawn: Owner: %s ColorName: %s Position: (%d, %d)", owner, colorName, position.x, position.y);
		// Oyunun herhangi bir yerinde kullan�lmasa da sembolik olarak olu�turulmu� toString metodurur.
		// D�nd�rd��� �rnek String: Pawn: Owner: Player3 (Ahmet) ColorName: Magenta Position: (114, 235)
	}
	
}
