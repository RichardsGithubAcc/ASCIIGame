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
public class GraphicsPanel extends JPanel{

		private WorldMap map;

		/*
		 * constructor for GraphicsPanel, sets private LineRotator object to LineRotator 
		 *  object received by parameter
		 * order: O(1)
		 * params: LineRotator lineRot
		 * no return values
		 */
		public GraphicsPanel(WorldMap map){
			this.setBackground(Color.BLACK);
			this.map = map;
		}
		
	    /*
	     * paints the current state of the camera window onto this panel
	     * order: O(1)
	     * params: Graphics g
	     * no return values
	     */
		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D)g;
			g2.setFont(new Font(g2.getFont().getFontName(), Font.PLAIN, 12));
			super.paintComponent(g2);
			int width = this.getWidth();
			int height = this.getHeight();
			int col = map.getPanelCol();
			int row = map.getPanelRow();
			int tileWidth = width/80;//current screen size of 80x80 is hardcoded, all 80's/40's are due to this
			int tileHeight = height/80;
			Point camera = map.getCamera();
			Tile tile;
			Point point = new Point();
			String str = new String();
//			int startX = camera.x - row/2;
//			int startY = camera.y - col/2;
			int startX = camera.x - 40;
			int startY = camera.y - 40;
			int adjY = 0 - startY;
			int adjX = 0 - startX;
			for (int i = 0; i < 80; i++) {
				for (int j = 0; j < 80; j++) {
					point.x = startX + i;
					point.y = startY + j;
					tile = map.getPoint(point);
					point.x = (startX + i + adjX);
					point.y = 80 - (startY + j + adjY);
					
					if (tile != null) {
						str = "";
						g2.setColor(tile.getTerrain().getColor());
						str += tile.getTerrain().getIcon();
						g2.drawString(str, point.x * tileWidth + 12, point.y * tileHeight + 12);
						//g2.drawString("@", 0, 10);
					} 
				}
			}
		}
		
}
