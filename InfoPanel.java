import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

public class InfoPanel extends JPanel {
	private Game g;
	private JTextArea textArea;
	private JLabel health;

	public InfoPanel(Game game) {
		this.setBackground(Color.BLACK);
		g = game;
		health = new JLabel();
		health.setFont(new Font("Arial", Font.PLAIN, 18));
		setBounds(new Rectangle(200, 800));
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		String h = "";
		for (int i = 0; i < g.getPlayer().getHealth() / 20; i++) {
			h += "| ";
		}
		int mod = g.getPlayer().getHealth() % 20;
		if (mod != 0) {
			if (mod < 10) {
				h += ":";
			} else {
				h += "/";
			}
		}
		health.setText("<html><font color = '#707070'>HEALTH: </font><font color = 'green'>" + h);
		if ((double) g.getPlayer().getHealth() / g.getPlayer().getMaxHealth() < 0.33) {
			health.setText("<html><font color = '#707070'>HEALTH: </font><font color = 'red'>" + h);
		} else {
			if ((double) g.getPlayer().getHealth() / g.getPlayer().getMaxHealth() < 0.66) {
				health.setText("<html><font color = '#707070'>HEALTH: </font><font color = 'yellow'>" + h);
			}
		}
		add(health);

		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setColumns(25);
		textArea.setRows(10);
		textArea.setEditable(false);
		textArea.setBackground(Color.BLACK);
		textArea.setFont(new Font("Arial", Font.PLAIN, 14));
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		add(scroll);
	}

	public void paintComponent(Graphics lol) {
		Graphics2D g2 = (Graphics2D) lol;
		super.paintComponent(g2);
		String h = "";
		for (int i = 0; i < g.getPlayer().getHealth() / 20; i++) {
			h += "| ";
		}
		int mod = g.getPlayer().getHealth() % 20;
		if (mod != 0) {
			if (mod < 10) {
				h += ":";
			} else {
				h += "/";
			}
		}
		if ((double) g.getPlayer().getHealth() / g.getPlayer().getMaxHealth() < 0.33) {
			health.setText("<html><font color = '#707070'>HEALTH: </font><font color = 'red'>" + h);
		} else {
			if ((double) g.getPlayer().getHealth() / g.getPlayer().getMaxHealth() < 0.66) {
				health.setText("<html><font color = '#707070'>HEALTH: </font><font color = 'yellow'>" + h);
			} else {
				health.setText("<html><font color = '#707070'>HEALTH: </font><font color = 'green'>" + h + "</html>");
			}
		}
		ArrayList<String> progress = g.getProgress();

		while (!progress.isEmpty()) {
			String event = progress.remove(0);
			//event = "<html><font color = 'green'>" + event + "\n\n</font></html>";
			textArea.append(event + "\n\n");
		}
	}
}
