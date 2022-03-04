import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;

public class BoardJPanel extends javax.swing.JPanel
{
	// bu class, MainWindow class�nda monopoly oyun �emas�n�n g�sterildi�i tablo olarak kullan�l�yor.
	// (bkz. MainWindow.java: jPanel1 = new BoardJPanel(gameDriver); (initComponents metodu i�inde))
	// MainWindow'da do�rudan JPanel kullanmak yerine JPanel'i extend ederek olu�turulan bir class� kullanmam�z�n sebebi:
	// JPanel'in paint metodunu override etmek istiyoruz. paint metodu normalde JPanel'de bulunan bir metod.
	// JPanel'in her repaint edili�inde paint metodu �al��t�r�l�r.
	// Biz de paint metodunda board'un arkaplan�nda g�r�necek olan monopoly tasar�m �emam�z� koyuyoruz (monopoly.png).
	// Ayn� zamanda drawTokens metodu ile oyuncular�n piyonlar�n� �izdiriyoruz.
	// Constructor'da Game objesi al�yoruz ��nk� drawTokens() metodunda Game objesinde tutulan oyuncu listesine eri�iyoruz.
	
	
	Game gameMotor;
	public BoardJPanel(Game gameMotor) 
	{
		this.gameMotor = gameMotor;
	}


	public void paint(Graphics g) 
	{
		g.setColor(Color.BLACK);
		Image img = new ImageIcon("monopoly.png").getImage(); //�nternetteki kaynaklarda JPanel'e resim koyma i�lemi bu �ekilde anlat�ld��� i�in bu sat�r� ve bir alttaki sat�r� bu �ekilde yazd�k.
	    g.drawImage(img, 0, 0, null);
	    drawTokens(g);
	    
	}
	
	private void drawTokens(Graphics g) {

		for(Player p: gameMotor.getPlayers()) 
		{
			// t�m playerlar� tar�yoruz, her birinin piyonunu oyun board'u �zerine �izece�iz.
			if (!p.getPawn().isHidden()) { // kaybeden oyuncunun pawn objesi hidden olarak ayarlan�r.
				// bu if ile, sadece pawn'� hidden olmayanlar� �izdirmi� oluyoruz. (if(!p.getPawn().isHidden()) demek if (p.getPawn.isHidden() == false) demektir.)
				Point pos = p.getPawn().getPosition(); //piyonun pozisyonunu al�yoruz.
				g.setColor(Color.BLACK); // �izilecek dairenin �evresi siyah olacak, i�ini a�a��da uygun renkle dolduraca��z.
				g.drawOval(pos.x, pos.y, 10, 10); // bu k�s�mda piyonun pozisyonuna y�ksekli�i ve geni�li�i 10 olan bir �ember �iziyoruz.
				g.setColor(p.getPawn().getColor()); // yap�lacak �izimin rengini, �imdiki oyuncunun piyonu ne renkse o renge ayarl�yoruz.
				g.fillOval(pos.x, pos.y, 10, 10); // ard�ndan, �izdi�imiz �emberin i�ini bir yukar�da belirtti�imiz renkle dolduruyoruz.
			}
		}

	}

}
