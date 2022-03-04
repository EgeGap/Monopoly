import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class BoardJPanel extends javax.swing.JPanel
{
	// bu class, MainWindow classýnda monopoly oyun þemasýnýn gösterildiði tablo olarak kullanýlýyor.
	// (bkz. MainWindow.java: jPanel1 = new BoardJPanel(gameDriver); (initComponents metodu içinde))
	// MainWindow'da doðrudan JPanel kullanmak yerine JPanel'i extend ederek oluþturulan bir classý kullanmamýzýn sebebi:
	// JPanel'in paint metodunu override etmek istiyoruz. paint metodu normalde JPanel'de bulunan bir metod.
	// JPanel'in her repaint ediliþinde paint metodu çalýþtýrýlýr.
	// Biz de paint metodunda board'un arkaplanýnda görünecek olan monopoly tasarým þemamýzý koyuyoruz (monopoly.png).
	// Ayný zamanda drawTokens metodu ile oyuncularýn piyonlarýný çizdiriyoruz.
	// Constructor'da Game objesi alýyoruz çünkü drawTokens() metodunda Game objesinde tutulan oyuncu listesine eriþiyoruz.
	
	
	Game gameMotor;
	public BoardJPanel(Game gameMotor) 
	{
		this.gameMotor = gameMotor;
	}


	public void paint(Graphics g) 
	{
		g.setColor(Color.BLACK);
		Image img = new ImageIcon("monopoly.png").getImage(); //Ýnternetteki kaynaklarda JPanel'e resim koyma iþlemi bu þekilde anlatýldýðý için bu satýrý ve bir alttaki satýrý bu þekilde yazdýk.
	    g.drawImage(img, 0, 0, null);
	    drawTokens(g);
	    
	}
	
	private void drawTokens(Graphics g) {

		for(Player p: gameMotor.getPlayers()) 
		{
			// tüm playerlarý tarýyoruz, her birinin piyonunu oyun board'u üzerine çizeceðiz.
			if (!p.getPawn().isHidden()) { // kaybeden oyuncunun pawn objesi hidden olarak ayarlanýr.
				// bu if ile, sadece pawn'ý hidden olmayanlarý çizdirmiþ oluyoruz. (if(!p.getPawn().isHidden()) demek if (p.getPawn.isHidden() == false) demektir.)
				Point pos = p.getPawn().getPosition(); //piyonun pozisyonunu alýyoruz.
				g.setColor(Color.BLACK); // çizilecek dairenin çevresi siyah olacak, içini aþaðýda uygun renkle dolduracaðýz.
				g.drawOval(pos.x, pos.y, 10, 10); // bu kýsýmda piyonun pozisyonuna yüksekliði ve geniþliði 10 olan bir çember çiziyoruz.
				g.setColor(p.getPawn().getColor()); // yapýlacak çizimin rengini, þimdiki oyuncunun piyonu ne renkse o renge ayarlýyoruz.
				g.fillOval(pos.x, pos.y, 10, 10); // ardýndan, çizdiðimiz çemberin içini bir yukarýda belirttiðimiz renkle dolduruyoruz.
			}
		}

	}

}
