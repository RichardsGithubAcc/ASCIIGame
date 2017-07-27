import java.awt.Point;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class AStar {
	private WorldMap map;
	private Point start;
	private Point end;
	private ArrayList<Cell> closedList;
	private CellQueue openList;
	
	public class Cell implements Comparable<Cell>{
		private Point point;
		private int fCost;
		private Cell previous;
		
		public Cell(Point p, int c) {
			point = p;
			fCost = c;
		}
		
		public Point getPoint() {
			return point;
		}
		
		public void setPoint(Point p) {
			point = p;
		}
		
		public int getCost() {
			return fCost;
		}
		
		public void setCost(int f) {
			fCost = f;
		}
		
		public Cell getPrevious() {
			return previous;
		}
		
		public void setPrevious(Cell c) {
			previous = c;
		}

		@Override
		public int compareTo(Cell two) {
			if(this.getCost() < two.getCost()) return -1;
			if(this.getCost() == two.getCost()) return 0;
			return 1;
		}
	}
	
	public class CellQueue extends PriorityQueue<Cell> {
		public Cell contains(Cell c) {
			Cell[] foo = (Cell[]) super.toArray();
			for(int i = 0; i < foo.length; i++) {
				if(foo[i].getPoint().x == c.getPoint().x && foo[i].getPoint().y == c.getPoint().y) return foo[i];
			}
			return null;
		}
	}
	
	public AStar(WorldMap m, Point s, Point e) {
		map = m;
		start = s;
		end = e;
		closedList = new ArrayList<Cell>();
		openList = new CellQueue();
	}
	
	public LinkedList<Cell> getPath(Point s, Point e) {
		start = s;
		end = e;
		return getPath();
	}
	
	public LinkedList<Cell> getPath() {
		openList.add(new Cell(start, 0));
		Cell current;
		boolean done = false;
		while(!done) {
			current = openList.poll();
			for(int dX = -1; dX < 2; dX++) {
				for(int dY = -1; dY < 2; dY++) {
					//calculate cost, then check if the cell is in the open list
					if(dX != 0 && dY != 0) {
						Point p = new Point(current.getPoint().x + dX, current.getPoint().y + dY);
						Tile t = map.getPoint(p);
						int cost = current.getCost() + t.getTerrain().getMoveMod() + Math.abs(p.x - end.x) + Math.abs(p.y - end.y);
						Cell c = openList.contains(new Cell(p, 0));
						if(c != null) {
							//check if the current path(cost) is better
						} else {
							openList.add(new Cell(p, cost));
						}
					}
				}
			}
		}
	}
}
