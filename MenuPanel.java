import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {
	private String prompt;
	private Game game;
	private ArrayList<String> options;
	private int x;
	private int y;
	
	public MenuPanel(Game g, int x, int y) {
		this.setBackground(Color.BLACK);
		this.x = x;
		this.y = y;
		setBounds(new Rectangle(x, y));
		setVisible(true);
		game = g;
		options = new ArrayList<String>();
	}
	
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g2);
		setBounds(new Rectangle(200, 550));
		g2.setFont(new Font("Arial", Font.PLAIN, 14));
		g2.setColor(Color.WHITE);
	
	
	}
	
	
}
