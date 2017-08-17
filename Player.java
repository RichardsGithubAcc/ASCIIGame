import java.awt.Color;
import java.awt.Point;

public class Player extends Creature {
	private int hunger, volumeCarried, maxVolume, thirst;
	private int strength, dexterity, intelligence, perception;
	private double weightCarried, maxWeight;
	public static final int DEFAULT_PLAYER_MOVE_DELAY = 2;


	public Player(Game ge, char i, Color c, String n, String[] tags, int x, int y, boolean p, int h, int mH, double hM, double armM,
			double atkM, int dH, int s, int d, int in, int pe) {
		super(ge, i, c, n, tags, x, y, p, h, 0, mH, hM, armM, atkM, dH);
		// TODO Auto-generated constructor stub
		strength = s;
		dexterity = d;
		intelligence = in;
		perception = pe;
		maxWeight = 20 + 2 * strength;
		hunger = 0;
		setThirst(0);
		volumeCarried = 0;
		weightCarried = 0;
		maxVolume = 0;
	}
	
	public Player(Game ge, char i, Color c, String n, String[] tags, int x, int y, int health, int str, int dex, int in, int pe) {
		super(ge, i, c, n, tags, x, y, health, 0);
		strength = str;
		dexterity = dex;
		intelligence = in;
		perception = pe;
		maxWeight = 20 + 2 * strength;
		hunger = 0;
		setThirst(0);
		volumeCarried = 0;
		weightCarried = 0;
		maxVolume = 0;
	}
	
	public Player(Game ge, int x, int y) {
		super(ge, '@', Color.WHITE, "player", null, x, y, false, 100, 0, 100, 1, 1, 1, 0);
		strength = 8;
		dexterity = 8;
		intelligence = 8;
		perception = 8;
		weightCarried = 0;
		volumeCarried = 0;
		maxWeight = 20 + 2 * strength;
		maxVolume = 0;
		hunger = 0;
		setThirst(0);
	}
	
	public Player(Player origin) {
		super(origin);
		hunger = origin.getHunger();
		volumeCarried = origin.getVolumeCarried();
		maxVolume = origin.getMaxVolume();
		strength = origin.getStrength();
		dexterity = origin.getDexterity();
		intelligence = origin.getIntelligence();
		perception = origin.getPerception();
		weightCarried = origin.getWeightCarried();
		maxWeight = origin.getMaxWeight();
	
	}
	
	@Override
	public void update() {
		super.update();
		hunger++;
		maxWeight = 20 + 2 * strength;
		weightCarried = checkWeight();
		volumeCarried = checkVolume();
		if(weightCarried > maxWeight && super.getDelay() == 0) {
			super.addDelay(1);
		}
		if(volumeCarried > maxVolume && super.getDelay() == 0) {
			super.addDelay(1);
		}
		if(super.getDelay() > 0) {
			super.removeDelay(1);
		}
		setThirst(getThirst() + 1);
	}
	
	public int checkWeight() {
		int sum = 0;
		for(Item i : super.getInventory()) {
			sum += i.getWeight();
		}
		return sum;
	}
	
	public int checkVolume() {
		int sum = 0;
		for(Item i : super.getInventory()) {
			if(i.hasTag("storage")) {
				sum -= i.getVolume();
			} else {
				sum += i.getVolume();
			}
		}
		return sum;
	}
	
	public void attack(Creature c) {
		//super.getGame().addProgress(super.getName() + " attacked " + c.getName() + " with a weapon that has " + super.getWeapon().getDamage() + " damage");
		if(super.getHolding() instanceof Weapon) {
			double strM = ((((double)strength)/10) + 1)/2;
			double d = (((Weapon)super.getHolding()).getAttackMod() * super.getHolding().getDamage() * strM);
			//super.getGame().addProgress(super.getName() + " " + d);
			c.hit(d, super.getHolding(), this);
			double red = Math.pow(c.getArmorVal() * c.getArmorMod(), 0.926);
			if(d - red <= 0) super.getHolding().setDurability(super.getHolding().getDurability() - 1);
		}
		else {
			double strM = (((double)strength/10) + 1)/2;
			double d = (super.getHolding().getDamage() * strM);
			c.hit(d, super.getHolding(), this);
		}
		super.getGame().updateProgress();
	}
	
	public void examine(int x, int y) {
		x = (int)Math.signum(x);
		y = (int)Math.signum(y);
		Tile tile = super.getGame().getMap().getTile(new Point(super.getX() + x, super.getY() + y));
		tile.getTerrain().use();
		super.getGame().getFrame().repaint();
	}

	public void pickUp() {
		Tile tile = super.getGame().getMap().getTile(new Point(super.getX(), super.getY()));
		for (int i = 0; i < tile.getEntities().size(); i++) {
			if (tile.getEntities().get(i) instanceof Item) {
				Item item = (Item) tile.getEntities().get(i);
				if (weightCarried+item.getWeight() <= maxWeight && volumeCarried + item.getVolume() <= maxVolume)
					addToInventory(item);
			}
				
		}
		
	}
	
	/**
	 * @return the hunger
	 */
	public int getHunger() {
		return hunger;
	}

	/**
	 * @param hunger the hunger to set
	 */
	public void setHunger(int hunger) {
		this.hunger = hunger;
	}

	/**
	 * @return the volumeCarried
	 */
	public int getVolumeCarried() {
		return volumeCarried;
	}

	/**
	 * @param volumeCarried the volumeCarried to set
	 */
	public void setVolumeCarried(int volumeCarried) {
		this.volumeCarried = volumeCarried;
	}

	/**
	 * @return the maxVolume
	 */
	public int getMaxVolume() {
		return maxVolume;
	}

	/**
	 * @param maxVolume the maxVolume to set
	 */
	public void setMaxVolume(int maxVolume) {
		this.maxVolume = maxVolume;
	}

	/**
	 * @return the strength
	 */
	public int getStrength() {
		return strength;
	}

	/**
	 * @param strength the strength to set
	 */
	public void setStrength(int strength) {
		this.strength = strength;
	}

	/**
	 * @return the dexterity
	 */
	public int getDexterity() {
		return dexterity;
	}

	/**
	 * @param dexterity the dexterity to set
	 */
	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}

	/**
	 * @return the intelligence
	 */
	public int getIntelligence() {
		return intelligence;
	}

	/**
	 * @param intelligence the intelligence to set
	 */
	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	/**
	 * @return the perception
	 */
	public int getPerception() {
		return perception;
	}

	/**
	 * @param perception the perception to set
	 */
	public void setPerception(int perception) {
		this.perception = perception;
	}

	/**
	 * @return the weightCarried
	 */
	public double getWeightCarried() {
		return weightCarried;
	}

	/**
	 * @param weightCarried the weightCarried to set
	 */
	public void setWeightCarried(double weightCarried) {
		this.weightCarried = weightCarried;
	}

	/**
	 * @return the maxWeight
	 */
	public double getMaxWeight() {
		return maxWeight;
	}

	/**
	 * @param maxWeight the maxWeight to set
	 */
	public void setMaxWeight(double maxWeight) {
		this.maxWeight = maxWeight;
	}

	/**
	 * @return the thirst
	 */
	public int getThirst() {
		return thirst;
	}

	/**
	 * @param thirst the thirst to set
	 */
	public void setThirst(int thirst) {
		this.thirst = thirst;
	}
	
	public boolean moveUp() {
		if(super.getDelay() > 0) return false;
		int yCoord = super.getY();
		int xCoord = super.getX();
		WorldMap map = super.getGame().getMap();
		Tile t = (map.getTile(new Point(xCoord, yCoord + 1)) == null) ? new Tile(new Terrain(super.getGame(), '.', new Color(0, 142, 25), "sparse", null, 0, 0, true, 0)) : map.getTile(new Point(xCoord, yCoord + 1));
		Creature c = t.getEntity();
		if (c != null) {
			attack(c);
		} else {
			if (t.getTerrain().isPassable()) {
				super.setY(yCoord + 1);
				t.addEntity(this);
				map.setPoint(new Point(xCoord, yCoord + 1), t);
				map.getTile(new Point(xCoord, yCoord)).removeCreature();
				super.addDelay(super.getDelay() + DEFAULT_PLAYER_MOVE_DELAY + t.getTerrain().getMoveMod());
				return true;
			}
		}
		return false;
	}

	public boolean moveDown() {
		if(super.getDelay() > 0) return false;
		int yCoord = super.getY();
		int xCoord = super.getX();
		WorldMap map = super.getGame().getMap();
		Tile t = (map.getTile(new Point(xCoord, yCoord - 1)) == null) ? new Tile(new Terrain(super.getGame(), '.', new Color(0, 142, 25), "sparse", null, 0, 0, true, 0)) : map.getTile(new Point(xCoord, yCoord - 1));
		Creature c = t.getEntity();
		if (c != null) {
			attack(c);
		} else {
			if (t.getTerrain().isPassable()) {
				super.setY(yCoord - 1);
				t.addEntity(this);
				map.setPoint(new Point(xCoord, yCoord - 1), t);
				map.getTile(new Point(xCoord, yCoord)).removeCreature();
				super.addDelay(super.getDelay() + DEFAULT_PLAYER_MOVE_DELAY + t.getTerrain().getMoveMod());
				return true;
			}
		}
		return false;
	}

	public boolean moveLeft() {
		if(super.getDelay() > 0) return false;
		WorldMap map = super.getGame().getMap();
		int yCoord = super.getY();
		int xCoord = super.getX();
		Tile t = (map.getTile(new Point(xCoord - 1, yCoord)) == null) ? new Tile(new Terrain(super.getGame(), '.', new Color(0, 142, 25), "sparse", null, 0, 0, true, 0)) : map.getTile(new Point(xCoord - 1, yCoord));
		Creature c = t.getEntity();
		if (c != null) {
			attack(c);
		} else {
			if (t.getTerrain().isPassable()) {
				super.setX(xCoord - 1);
				t.addEntity(this);
				map.setPoint(new Point(xCoord - 1, yCoord), t);
				map.getTile(new Point(xCoord, yCoord)).removeCreature();
				super.addDelay(super.getDelay() + DEFAULT_PLAYER_MOVE_DELAY + t.getTerrain().getMoveMod());
				return true;
			}
		}
		return false;
	}

	public boolean moveRight() {
		if(super.getDelay() > 0) return false;
		WorldMap map = super.getGame().getMap();
		int yCoord = super.getY();
		int xCoord = super.getX();
		Tile t = (map.getTile(new Point(xCoord + 1, yCoord)) == null) ? new Tile(new Terrain(super.getGame(), '.', new Color(0, 142, 25), "sparse", null, 0, 0, true, 0)) : map.getTile(new Point(xCoord + 1, yCoord));
		Creature c = t.getEntity();
		if (c != null) {
			attack(c);
		} else {
			if (t.getTerrain().isPassable()) {
				super.setX(xCoord + 1);
				t.addEntity(this);
				map.setPoint(new Point(xCoord + 1, yCoord), t);
				map.getTile(new Point(xCoord, yCoord)).removeCreature();
				super.addDelay(super.getDelay() + DEFAULT_PLAYER_MOVE_DELAY + t.getTerrain().getMoveMod());
				return true;
			}
		}
		return false;
	}
}
