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
		private int fCost;//total cost: gCost + heuristic
		private int gCost;//movement cost to get here
		private Cell previous;//parent point
		
		public Cell(Point p, int c, Cell pe) {
			point = p;
			fCost = c;
			previous = pe;
		}
		
		public Point getPoint() {
			return point;
		}
		
		public void setPoint(Point p) {
			point = p;
		}
		
		public int getMoveCost() {
			if(previous == null) return 0;
			int g = map.getPoint(point).getTerrain().getMoveMod() + previous.getMoveCost();
			gCost = g;
			return g;
		}
		
		public int getCost() {
			calcCost(end);
			return fCost;
		}
		
		public void setCost(int f) {
			fCost = f;
		}
		
		public void calcCost(Point target) {
			fCost = getMoveCost() + Math.abs(point.x - target.x) + Math.abs(point.y - target.y);
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
			System.out.println("Does this contain (" + c.getPoint().x + ", " + c.getPoint().y + ")");
			Cell[] foo = super.toArray(new Cell[0]);
			for(int i = 0; i < foo.length; i++) {
				if(foo[i].getPoint().x == c.getPoint().x && foo[i].getPoint().y == c.getPoint().y) {
					System.out.println("answer: (" + foo[i].getPoint().x + ", " + foo[i].getPoint().y + ")");
					return foo[i];
				}
			}
			System.out.println("answer: no");
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
	
	public LinkedList<Point> getPath(Point s, Point e) {
		start = s;
		end = e;
		return getPath();
	}
	
	public LinkedList<Point> getPath() {
		System.out.println("pathfinding started");
		openList.add(new Cell(start, 0, null));
		Cell current;
		boolean done = false;
		while(!done) {
			if(openList.isEmpty()) return null;
			current = openList.poll();
			closedList.add(current);//check this line
			System.out.println("closed list expanded");
			if(current.getPoint().x == end.x && current.getPoint().y == end.y) {//we're done
				System.out.println("pathfinding done");
				LinkedList<Point> path = new LinkedList<Point>();
				path.addFirst(current.getPoint());
				while(current.getPrevious() != null) {
					path.addFirst(current.getPrevious().getPoint());//construct a linked list starting at the start and ending at the end
					current = current.getPrevious();
				}
				return path;
			}
			for(int dX = -1; dX < 2; dX++) {
				for(int dY = -1; dY < 2; dY++) {
					//calculate cost, then check if the cell is in the open list
					if(Math.abs(dY + dX) == 1) {
						System.out.println("considering (" + (current.getPoint().x + dX) + ", " + (current.getPoint().y + dY) + ")");
						Point p = new Point(current.getPoint().x + dX, current.getPoint().y + dY);
						Tile t = map.getPoint(p);
						if(t.getTerrain().isPassable() && t.hasCreature() == null) {
							current.calcCost(end);
							int cost = current.getMoveCost() + t.getTerrain().getMoveMod();// + Math.abs(p.x - end.x) + Math.abs(p.y - end.y);
							Cell c = openList.contains(new Cell(p, 0, current));
							if(c != null) {
								if(cost < c.getMoveCost()) c.setPrevious(current);//if the current path is better, use it instead
							} else {
								cost += Math.abs(p.x - end.x) + Math.abs(p.y - end.y);//cost is currently gCost, add h to convert to fCost
								openList.add(new Cell(p, cost, current));
							}
						}
					}
				}
			}
		}
		return null;
	}
}
