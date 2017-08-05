import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {
	private String promptStr;
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

	/**
	 * @return the promptStr
	 */
	public String getPromptStr() {
		return promptStr;
	}

	/**
	 * @param promptStr the promptStr to set
	 */
	public void setPromptStr(String promptStr) {
		this.promptStr = promptStr;
	}

	/**
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * @param game the game to set
	 */
	public void setGame(Game game) {
		this.game = game;
	}

	/**
	 * @return the options
	 */
	public ArrayList<String> getOptions() {
		return options;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	
}
