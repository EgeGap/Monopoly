import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class DicePanel extends JPanel 
{

	private int counter = -1;
	private int limit;
	private String[] paths;
	private int die1, die2;
	boolean cont = true;
	
	public DicePanel(int limit, String[] paths, int die1, int die2) 
	{
		super();
		this.limit = limit;
		this.paths = paths;
		this.die1 = die1;
		this.die2 = die2;
	}
	
	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		
		Random generator = new Random();
		Image die1, die2;
		
		if (counter != limit) 
		{
			String die1Path = paths[generator.nextInt(6)];
			String die2Path = paths[generator.nextInt(6)];
			
			die1 = new ImageIcon(die1Path).getImage();
			die2 = new ImageIcon(die2Path).getImage();
			
			counter++;
		}
		else 
		{
			String die1Path = paths[this.die1-1];
			String die2Path = paths[this.die2-1];
			
			die1 = new ImageIcon(die1Path).getImage();
			die2 = new ImageIcon(die2Path).getImage();
			
			counter = 0;
			cont = false;
		}

	    g.drawImage(die1, 40, 60, null);
	    g.drawImage(die2, 160, 60, null);
	}
	
	public boolean isContinuing() 
	{
		return cont;
	}
	
	public int getCounter() 
	{
		return counter;
	}
	

} 
