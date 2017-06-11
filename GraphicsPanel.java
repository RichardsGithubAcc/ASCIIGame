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

	private WorldMap map;

	/*
	 * constructor for GraphicsPanel, sets private LineRotator object to
	 * LineRotator object received by parameter order: O(1) params: LineRotator
	 * lineRot no return values
	 */
	public GraphicsPanel(WorldMap map) {
		this.setBackground(Color.BLACK);
		this.map = map;
	}

	/*
	 * paints the current state of the camera window onto this panel order: O(1)
	 * params: Graphics g no return values
	 */
	public void paintComponent(Graphics g) {
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setFont(new Font(g2.getFont().getFontName(), Font.PLAIN, 12));
		super.paintComponent(g2);
		
		int width = this.getWidth();
		int height = this.getHeight() - 10;
		int col = map.getPanelCol();
		int row = map.getPanelRow();
		double tileWidth = (double)width/col;
		double tileHeight = (double)height/row;
		Point camera = map.getCamera();
		Tile tile;
		Point point = new Point();
		String str = new String();
		int startX = camera.x - row/2;
		int startY = camera.y - col/2;
		int startDrawX = (int)(tileWidth/2);
		int startDrawY = (int)(tileHeight/2) + 10;
		for (int i = 0; i < col; i++) {
			for (int j = 0; j < row; j++) {
				point.x = startX + i;
				point.y = startY + j;
				tile = map.getPoint(point);		
				
				if (tile != null && tile.getTerrain() != null) {
					str = "";
					Creature c = tile.hasCreature();
					if(c != null) {
						g2.setColor(c.getColor());
						str += c.getIcon();
						g2.drawString(str, (int) (i * tileWidth) + startDrawX, (int)((row - j) * tileHeight) + startDrawY );
					} else {
						g2.setColor(tile.getTerrain().getColor());
						str += tile.getTerrain().getIcon();
						g2.drawString(str, (int) (i * tileWidth) + startDrawX, (int)((row - j) * tileHeight) + startDrawY );;
					}
				} else {
					g2.setColor(new Color(0, 142, 25));
					g2.drawString(".", (int) (i * tileWidth) + startDrawX, (int)((row - j) * tileHeight) + startDrawY );;
				}
				

			}
		}
	}

}