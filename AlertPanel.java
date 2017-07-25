import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class AlertPanel extends JPanel {
	private ArrayList<String> log;
	private Game game;
	private int newAlerts;
	
	public AlertPanel(Game g) {
		this.setBackground(Color.BLACK);
		setBounds(new Rectangle(250, 600));
		setVisible(true);
		game = g;
		log = new ArrayList<String>();
		this.setPreferredSize(new Dimension(250, 600));
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g2);
		setBounds(new Rectangle(200, 550));
		int height = (int)this.getBounds().getHeight();
		g2.setFont(new Font("Arial", Font.PLAIN, 14));
		g2.setColor(Color.WHITE);
		//setLog(game.getProgress());
		if(game.getProgress().size() != 0) {
			fetchProgress();
		}
		processLog();
		if(log.size() > 0) {
			for(int i = 0; i < log.size(); i++) {
				if(i < newAlerts) {
					g2.setColor(Color.WHITE);
				} else {
					g2.setColor(Color.GRAY);
				}
				g2.drawString(log.get(i), 5, height - (i * 28));
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
	}
	
	public void processLog() {
		for(int i = 0; i < log.size(); i++) {
			if(log.get(i).length() > 90) {
				int index = log.get(i).lastIndexOf(' ');
				String start = log.get(i);
				String first = start.substring(0, index);
				String last = start.substring(index + 1);
				log.set(i, first + "\n" + last);
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