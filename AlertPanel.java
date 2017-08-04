import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

import javafx.scene.layout.Border;

public class AlertPanel extends JPanel {
	private ArrayList<String> log;
	private Game game;
	private int newAlerts;
	private int x;
	private int y;
	
	public AlertPanel(Game g, int x, int y) {
		this.setBackground(Color.BLACK);
		this.x = x;
		this.y = y;
		setBounds(new Rectangle(x, y));
		setVisible(true);
		game = g;
		log = new ArrayList<String>();
		this.setPreferredSize(new Dimension(x, y));
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g2);
		setBounds(new Rectangle(x, y - 50));
		int height = (int)this.getBounds().getHeight();
		g2.setFont(new Font("Arial", Font.PLAIN, 14));
		g2.setColor(Color.WHITE);
		//setLog(game.getProgress());
		if(game.getProgress().size() != 0) {
			fetchProgress();
		}
		int space = 0;
		if(log.size() > 0) {
			for(int i = 0; i < log.size(); i++) {
				if(i < newAlerts) {
					g2.setColor(Color.WHITE);
				} else {
					g2.setColor(Color.GRAY);
				}
				int spacer = (log.get(i).substring(0, 1).equals("!")) ? 28 : 14;
				space += spacer;
				g2.drawString(log.get(i).substring(1), 5, height - (space));
			}
		}
	}
	
	public void fetchProgress() {
		ArrayList<String> progress = game.getProgress();
		for(int i = progress.size() - 1; i >= 0; i--) {
			log.add(0, progress.get(i));
		}
		game.setProgress(new ArrayList<String>());
		newAlerts = progress.size();
		processLog();
	}
	
	public void processLog() {
		for(int i = 0; i < log.size(); i++) {
			if(log.get(i).length() > 36) {
				//int index = log.get(i).lastIndexOf(' ');
				String str = log.get(i);
				int index = -1;
				for(int j = 36; j > 0; j--) {
					if(str.charAt(j) == ' ') {
						index = j;
						j = -1;
					}
				}
				if(index > 0) {
					String start = log.get(i);
					log.add(i + 1, "$" + start.substring(0, index));
					log.set(i, "!" + start.substring(index + 1));
					newAlerts++;
				} 
			} else {
				if(!log.get(i).substring(0, 1).equals("!") && !log.get(i).substring(0, 1).equals("$")) log.set(i, "!" + log.get(i));
			}
		}
	}
	
	public void addToLog(String str) {
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