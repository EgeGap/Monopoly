import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import javax.swing.JPanel;

public class TokenMover implements ActionListener 
{
	
	private Game gameDriver;
	private Pawn pawn;
	private GameTable gameTable;
	private int location;
	private int direction;
	private JPanel gameBoard;
	
	public TokenMover(Game gameDriver, Pawn pawn, GameTable gameTable, int location, int direction, JPanel gameBoard) 
	{
		// direction : 1 --> forward
		// direction : -1 --> backward

		this.gameDriver = gameDriver;
		this.pawn = pawn;
		this.gameTable= gameTable;
		this.location = location;
		this.direction = direction;
		this.gameBoard = gameBoard;
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		int current = pawn.getLocation();
		if (current == location) 
		{
			Timer timer = (Timer)e.getSource();
            timer.stop();
            gameDriver.pawnPlaced();
		}
		
		else 
		{
			int targetLocation = HelperMethods.mod(current + direction, 40);
			gameTable.placePawnOn(pawn, targetLocation);
			gameBoard.repaint();
		}
	}

	
}
