import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.Point;

/*
 * A class that extends the JPanel class, paints the camera window of the world map.
 * stores a WorldMap object as private data
 * public GraphicsPanel(WorldMap map): constructor for GraphicsPanel, sets
 *  private GraphicsPanel object to GraphicsPanel object received by parameter
 * paintComponent(Graphics g): paints the current state of the camera window of the
 *  world map onto this panel
 */
public class GraphicsPanel extends JPanel {
	private Point aim;
	private WorldMap map;
	private Game game;

	/*
	 * constructor for GraphicsPanel, sets private LineRotator object to
	 * LineRotator object received by parameter order: O(1) params: LineRotator
	 * lineRot no return values
	 */
	public GraphicsPanel(Game game) {
		this.setBackground(Color.BLACK);
		this.game = game;
		map = game.getMap();
	}

	/*
	 * paints the current state of the camera window onto this panel order: O(1)
	 * params: Graphics g no return values
	 */
	public void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		g2.setFont(new Font(g2.getFont().getFontName(), Font.PLAIN, 12));
		super.paintComponent(g2);

		int width = this.getWidth();
		int height = this.getHeight() - 10;
		int col = map.getPanelCols();
		int row = map.getPanelRows();
		double tileWidth = (double) width / col;
		double tileHeight = (double) height / row;
		Point camera = map.getCamera();
		Tile tile;
		Point point = new Point();
		String str = new String();
		int startX = camera.x - row / 2;
		int startY = camera.y - col / 2;
		int startDrawX = (int) (tileWidth / 2);
		int startDrawY = (int) (tileHeight / 2);
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				point.x = startX + i;
				point.y = startY + j;
				tile = map.getTile(point);

				if (tile != null && tile.getTerrain() != null) {
					str = "";
					Creature c = tile.getCreature();
					Item it = tile.getItem();
					if (c != null) {
						g2.setColor(c.getColor());
						str += c.getIcon();
						g2.drawString(str, (int) (i * tileWidth) + startDrawX,
								(int) ((row - j) * tileHeight) + startDrawY);
					}
					else if (it != null) {
						g2.setColor(it.getColor());
						str += it.getIcon();
						g2.drawString(str, (int)(i*tileWidth) + startDrawX, (int)((row - j) * tileHeight) + startDrawY);
					}
					else {
						g2.setColor(tile.getTerrain().getColor());
						str += tile.getTerrain().getIcon();
						g2.drawString(str, (int) (i * tileWidth) + startDrawX,
								(int) ((row - j) * tileHeight) + startDrawY);
					}
				} else {
					g2.setColor(new Color(0, 142, 25));
					g2.drawString(".", (int) (i * tileWidth) + startDrawX, (int) ((row - j) * tileHeight) + startDrawY);
				}

			}
		}
		
		if(aim != null) {
			int gridX = aim.x - startX;
			int gridY = aim.y - startY;
			g2.setColor(Color.RED);
			g2.fillRect((int)((gridX) * tileWidth) + startDrawX, (int)((row - gridY - 1) * tileHeight) + startDrawY, (int)tileWidth, (int)tileHeight);
			int pY = game.getPlayer().getY() - startY;
			int pX = game.getPlayer().getX() - startX;
			double slope = ((double)pY - gridY) / ((double)pX - gridX);
			for(int i = (int)Math.signum(pX - gridX); Math.abs(i) < Math.abs(pX - gridX); i += Math.signum(pX - gridX)) {
				int y = (int)Math.round(slope * (gridX + i) - (slope * gridX) + gridY);
				g2.drawString("*", Math.round((gridX + i) * tileWidth), (row - y) * (int)tileHeight);
			}
		}
	}

	/**
	 * @return the aim
	 */
	public Point getAim() {
		return aim;
	}

	/**
	 * @param aim
	 *            the aim to set
	 */
	public void setAim(Point aim) {
		this.aim = aim;
	}

	public void moveLeft() {
		if (aim != null) {
			aim.x--;
		}
	}

	public void moveRight() {
		if (aim != null) {
			aim.x++;
		}
	}

	public void moveUp() {
		if (aim != null) {
			aim.y++;
		}
	}

	public void moveDown() {
		if (aim != null) {
			aim.y--;
		}
	}

	/**
	 * @return the map
	 */
	public WorldMap getMap() {
		return map;
	}

	/**
	 * @param map
	 *            the map to set
	 */
	public void setMap(WorldMap map) {
		this.map = map;
	}

}