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
			super.paintComponent(g2);
			int width = this.getWidth();
			int height = this.getHeight();
			int col = map.getPanelCol();
			int row = map.getPanelRow();
			int tileWidth = width/col;
			int tileHeight = height/row;
			Point camera = map.getCamera();
			Tile tile;
			Point point = new Point();
			String str = new String();
			int startX = camera.x - col/2;
			int startY = camera.y - row/2;
			for (int i = 0; i < col; i++) {
				for (int j = 0; j < row; j++) {
					point.x = startX + i;
					point.y = startY + j;
					tile = map.getPoint(point);
					if (tile != null) {
						str = "";
						g2.setColor(tile.getTerrain().getColor());
						str += tile.getTerrain().getIcon();
						g2.drawString(str, i * tileWidth + 10, j * tileHeight + 20);
					} 
				}
			}
		}
		
}
