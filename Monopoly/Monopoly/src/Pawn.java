import java.awt.Color;
import java.awt.Point;
import java.io.Serializable;

public class Pawn implements Serializable
{
	private int location;
	// piyonun konumunu belirtir. (0 ile 39 arasýnda (inclusive) deðer alýr.)
	
	
	private SquareField squareField;
	// piyonun üzerinde bulunduðu SquareField objesini belirtir.
	
	private Player owner;
	//piyonun sahibini belirtir.
	
	private int colorID;
	//piyonun rengini belirtir.
	
	private String colorName;
	//piyonun renk adýný tutar.
	
	private Point position;
	//piyonun x,y cinsinden konumunu tutmak için.
	
	private Color color;
	// piyonun rengini Color classý olarak tutmak için.  (bkz. BoardJPanel.java'da piyonlarý çizerken p.getPawn().getColor() ile piyonun Color classý cinsinden rengi alýnýr ve bu þekilde kolaylýkla piyonun renginde yuvarlak oyun ekranýna çizilir.)
	
	private boolean hidden;
	// piyonun gizlenmiþ olup olmadýðýný belirtmek için
	
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
		// piyonu gizli yapar. Game classýnda oyundan çýkan oyuncularýn piyonunu gizlemek için kullanýlýr.
		hidden = true;
	}
	
	public boolean isHidden() 
	{
		// hidden deðiþkeni için getter metodu. Piyonun gizlenmiþ olup olmadýðýný anlamak için kullanýlýr. (bkz. BoardJPanel.java: if (!p.getPawn().isHidden()))
		return hidden;
	}
	
	public void determineTheColor() 
	{
		
		// aþaðýda colorID deðeri kullanýlarak bu deðer String versiyona dönüþtürülür.
		// aþaðýda; colorID deðerinin tanýmladýðý renkler þu þekilde tanýmlanmýþtýr:
			/*	
			 * colorID 1 ise renk kýrmýzýdýr.
			 * colorID 2 ise renk mavidir.
			 * colorID 3 ise renk magentadýr.
			 * ..
			 * ..
			 * ..
			 * colorID 8 ise renk gridir.
			 */
		
		// 8'e kadar colorID tanýmlanmýþ olmasýnýn sebebi, oyunda en fazla 8 oyuncunun olmasýdýr.
		// Game classýnda, her oyuncunun piyonunun colorID'si, oyuncunun ID'sine eþit olacak þekilde ayarlanmýþtýr.
		// Böylece 1 numaralý oyuncunun piyonu kýrmýzý, 2 numaralý oyuncunun piyonu mavi, ..., 8 numaralý oyuncunun piyonu gri olur.
		
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
		// Piyonlarýn eþitliði sahipleriyle karþýlaþtýrýlýr.
		// eðer iki piyonun sahibi aynýysa, bu piyonlar ayný piyon demektir.
		return p.getOwner().getID() == owner.getID();
	}
	
	public String toString() 
	{
		return String.format("Pawn: Owner: %s ColorName: %s Position: (%d, %d)", owner, colorName, position.x, position.y);
		// Oyunun herhangi bir yerinde kullanýlmasa da sembolik olarak oluþturulmuþ toString metodurur.
		// Döndürdüðü örnek String: Pawn: Owner: Player3 (Ahmet) ColorName: Magenta Position: (114, 235)
	}
	
}
