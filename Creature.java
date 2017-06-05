import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class Creature extends Entity implements Dynamic {
	private int health;
	private int armorVal;
	private int maxHealth;
	private double healthMod;
	private double armorMod;
	private double attackMod;
	private int dHealth;
	private ArrayList<Item> inventory;
	
	public Creature(char i, Color c, String n, int x, int y, boolean p, int h, int armV, 
			int mH, double hM, double armM, double atkM, int dH) {
		super(i, c, n, x, y, p);
		health = h; armorVal = armV; maxHealth = mH; healthMod = hM; armorMod = armM;
		attackMod = atkM; dHealth = dH;
	}
	
	@Override
	public void update() {
		health += dHealth;
		if (dHealth > 0) dHealth--;
		else if (dHealth < 0) dHealth++;
	}
	
	public void addToInventory(Item i) {
		inventory.add(i);
	}
	
	public boolean removeFromInventory(Item i) {
		return inventory.remove(i);
	}
	
	public boolean isInInventory(Item i) {
		for (Item ownedItem: inventory) {
			return i == ownedItem;
		}
		return false;
	}

	public boolean moveUp(WorldMap map) {
		int yCoord = super.getY();
		int xCoord = super.getX();
		Tile t = map.getPoint(new Point(xCoord, yCoord + 1));
		if(t.getTerrain().isPassable()) {
			super.setY(yCoord + 1);
			t.addItem(this);
			map.getPoint(new Point(xCoord, yCoord)).removeItem(this);
			return true;
		}
		return false;
	}
	
	public boolean moveDown(WorldMap map) {
		int yCoord = super.getY();
		int xCoord = super.getX();
		Tile t = map.getPoint(new Point(xCoord, yCoord - 1));
		if(t.getTerrain().isPassable()) {
			super.setY(yCoord - 1);
			t.addItem(this);
			map.getPoint(new Point(xCoord, yCoord)).removeItem(this);
			return true;
		}
		return false;
	}
	
	public boolean moveLeft(WorldMap map) {
		int yCoord = super.getY();
		int xCoord = super.getX();
		Tile t = map.getPoint(new Point(xCoord - 1, yCoord));
		if(t.getTerrain().isPassable()) {
			super.setY(xCoord - 1);
			t.addItem(this);
			map.getPoint(new Point(xCoord, yCoord)).removeItem(this);
			return true;
		}
		return false;
	}
	
	public boolean moveRight(WorldMap map) {
		int yCoord = super.getY();
		int xCoord = super.getX();
		Tile t = map.getPoint(new Point(xCoord + 1, yCoord));
		if(t.getTerrain().isPassable()) {
			super.setY(xCoord + 1);
			t.addItem(this);
			map.getPoint(new Point(xCoord, yCoord)).removeItem(this);
			return true;
		}
		return false;
	}
	
	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * @param health the health to set
	 */
	public void setHealth(int health) {
		this.health = health;
	}

	/**
	 * @return the armorValue
	 */
	public int getArmorVal() {
		return armorVal;
	}

	/**
	 * @param armorValue the armorValue to set
	 */
	public void setArmorVal(int armorVal) {
		this.armorVal = armorVal;
	}

	/**
	 * @return the maxHealth
	 */
	public int getMaxHealth() {
		return maxHealth;
	}

	/**
	 * @param maxHealth the maxHealth to set
	 */
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	/**
	 * @return the healthMod
	 */
	public double getHealthMod() {
		return healthMod;
	}

	/**
	 * @param healthMod the healthMod to set
	 */
	public void setHealthMod(double healthMod) {
		this.healthMod = healthMod;
	}

	/**
	 * @return the armorMod
	 */
	public double getArmorMod() {
		return armorMod;
	}

	/**
	 * @param armorMod the armorMod to set
	 */
	public void setArmorMod(double armorMod) {
		this.armorMod = armorMod;
	}

	/**
	 * @return the attackMod
	 */
	public double getAttackMod() {
		return attackMod;
	}

	/**
	 * @param attackMod the attackMod to set
	 */
	public void setAttackMod(double attackMod) {
		this.attackMod = attackMod;
	}

	/**
	 * @return the dHealth
	 */
	public int getdHealth() {
		return dHealth;
	}

	/**
	 * @param dHealth the dHealth to set
	 */
	public void setdHealth(int dHealth) {
		this.dHealth = dHealth;
	}

	/**
	 * @return the inventory
	 */
	public ArrayList<Item> getInventory() {
		return inventory;
	}

	/**
	 * @param inventory the inventory to set
	 */
	public void setInventory(ArrayList<Item> inventory) {
		this.inventory = inventory;
	}
	
	
}
