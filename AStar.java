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
		
		public Point getTile() {
			return point;
		}
		
		public void setTile(Point p) {
			point = p;
		}
		
		public int getMoveCost() {
			if(previous == null) return 0;
			int g = map.getTile(point).getTerrain().getMoveMod() + previous.getMoveCost();
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
			System.out.println("Does this contain (" + c.getTile().x + ", " + c.getTile().y + ")");
			Cell[] foo = super.toArray(new Cell[0]);
			for(int i = 0; i < foo.length; i++) {
				if(foo[i].getTile().x == c.getTile().x && foo[i].getTile().y == c.getTile().y) {
					System.out.println("answer: (" + foo[i].getTile().x + ", " + foo[i].getTile().y + ")");
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
		System.out.println("pathfinding started, target (" + end.x + ", " + end.y + ")");
		openList.add(new Cell(start, 0, null));
		
		Cell current;
		boolean done = false;
		while(!done) {
			if(closedList.size() > 100) return null;
			if(openList.isEmpty()) return null;
			current = openList.poll();
			System.out.println("current point is (" + current.getTile().x + ", " + current.getTile().y + ")");
			closedList.add(current);//check this line
			System.out.println("closed list expanded, now " + closedList.size());
			if(current.getTile().x == end.x && current.getTile().y == end.y) {//we're done
				done = true;
				System.out.println("pathfinding done");
				LinkedList<Point> path = new LinkedList<Point>();
				path.addFirst(current.getTile());
				while(current.getPrevious() != null) {
					path.addFirst(current.getPrevious().getTile());//construct a linked list starting at the start and ending at the end
					current = current.getPrevious();
				}
				return path;
			}
			for(int dX = -1; dX < 2; dX++) {
				for(int dY = -1; dY < 2; dY++) {
					//calculate cost, then check if the cell is in the open list
					if(Math.abs(dY + dX) == 1) {
						System.out.println("considering (" + (current.getTile().x + dX) + ", " + (current.getTile().y + dY) + ")");
						Point p = new Point(current.getTile().x + dX, current.getTile().y + dY);
						Tile t = map.getTile(p);
						boolean notOnClosedList = true;
						for(int i = 0; i < closedList.size(); i++) {
							Cell lol = closedList.get(i);//only add it if its not already in the closedList
							if(lol.getTile().x == p.x && lol.getTile().y == p.y) notOnClosedList = false;
						}
						if(t.getTerrain().isPassable() && notOnClosedList) {
							current.calcCost(end);
							int cost = current.getMoveCost() + t.getTerrain().getMoveMod();// + Math.abs(p.x - end.x) + Math.abs(p.y - end.y);
							Cell c = openList.contains(new Cell(p, 0, current));
							if(c != null) {
								if(cost < c.getMoveCost()) {
									openList.remove(c);
									c.setPrevious(current);//if the current path is better, use it instead
									c.calcCost(end);
									openList.add(c);
								}
								System.out.println("path changed");
							} else {
								cost += Math.abs(p.x - end.x) + Math.abs(p.y - end.y);//cost is currently gCost, add h to convert to fCost
//								if(p.x == end.x && p.y == end.y) {
//									done = true;
//									System.out.println("pathfinding done");
//									LinkedList<Point> path = new LinkedList<Point>();
//									path.addFirst(p);
//									while(current.getPrevious() != null) {
//										path.addFirst(current.getPrevious().getTile());//construct a linked list starting at the start and ending at the end
//										current = current.getPrevious();
//									}
//									return path;
//								}
								openList.add(new Cell(p, cost, current));
								System.out.println("openList expanded, now " + openList.size());
							}
						}
					}
				}
			}
		}
		return null;
	}
}
