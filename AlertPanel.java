import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JPanel;

public class AlertPanel extends JPanel {
	private ArrayList<String> log;
	private Game game;
	
	public AlertPanel(Game g) {
		this.setBackground(Color.BLACK);
		setBounds(new Rectangle(200, 600));
		game = g;
		log = new ArrayList<String>();
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		int height = (int)this.getBounds().getHeight();
		g2.setFont(new Font("Arial", Font.PLAIN, 14));
		g2.setColor(Color.WHITE);
		for(int i = log.size(); i >= 0; i--) {
			g2.drawString(log.get(i), 0, height - (log.size() - i) * 28);
		}
	}
	
	public void addLog(String str) {
		log.add(str);
	}

	public ArrayList<String> getLog() {
		return log;
	}

	public void setLog(ArrayList<String> log) {
		this.log = log;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}
