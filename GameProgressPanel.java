import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GameProgressPanel extends JScrollPane {
	private Game game;
	
	public GameProgressPanel(Game game) {
		
		this.game = game;
		JTextArea textArea = new JTextArea(5, 30);
		add(textArea);
		

	}
	
	public void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D)g;
		super.paintComponent(g2);
		g2.drawString("Hello", 0,0);
		ArrayList<String> progress = game.getProgress();
			
        while (!progress.isEmpty()) {
        	String s = progress.remove(0);
        	g2.drawString(s, 10,10);
        }

	}
	
}
