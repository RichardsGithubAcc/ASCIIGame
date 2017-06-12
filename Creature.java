import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;

public class Creature extends Entity implements Dynamic {
	private int health;
	private int delay = 0;
	private int armorVal;
	private int maxHealth;
	private double healthMod;
	private double armorMod;
	private double attackMod;
	private int dHealth;
	private ArrayList<Item> inventory;
	private Clothing arms, legs, torso, head, feet, hands;
	private Item weapon;

	public Creature(Game ge, char i, Color c, String n, String[] tags, int x, int y, boolean p, int h, int armV, int mH,
			double hM, double armM, double atkM, int dH) {
		super(ge, i, c, n, tags, x, y, p);
		health = h;
		armorVal = armV;
		maxHealth = mH;
		healthMod = hM;
		armorMod = armM;
		attackMod = atkM;
		dHealth = dH;
	}

	@Override
	public void update() {
		if (delay <= 0) {
			health += dHealth;
			if (dHealth > 0)
				dHealth--;
			else if (dHealth < 0)
				dHealth++;
		} else
			delay--;
	}

	public void addToInventory(Item i) {
		inventory.add(i);
	}

	public boolean removeFromInventory(Item i) {
		return inventory.remove(i);
	}

	public boolean isInInventory(Item i) {
		for (Item ownedItem : inventory) {
			return i == ownedItem;
		}
		return false;
	}

	public void hit(int d, Item w, Creature c) {
		int dInit = d;
		double tA = armorVal * armorMod;
		// int red = (int)Math.pow(this.getArmorVal() * this.getArmorMod(),
		// 0.926);
		if (w.hasTag("bashing")) {
			tA *= 0.4;
		}
		if (w.hasTag("bullet")) {
			if (!torso.hasTag("bulletproof")) {
				tA *= 0.1;
			}
			if (!head.hasTag("bulletproof")) {
				if (Math.random() < 0.15) {
					d *= 5;
				}
			}
		}
		int red = (int) Math.round(Math.pow(tA, 0.926));
		d = (d - red > 0) ? d - red : 0;
		if (w.hasTag("cutting")) {
			d *= 1.5;
			if (d - 10 > 0)
				dHealth = -1 * (d - 10);
		}
		if (w.hasTag("piercing") && d > 0) {
			double target = (double) d / dInit;
			if (Math.random() < target)
				d *= 3;
		}
		d = (d > 0) ? d : 0;
		health -= d;
	}

	public void attack(Creature c) {
		if (weapon instanceof Weapon) {
			int d = (int) (((Weapon) weapon).getAttackMod() * weapon.getDamage());
			c.hit(d, weapon, this);
			int red = (int) Math.pow(c.getArmorVal() * c.getArmorMod(), 0.926);
			if (d - red <= 0)
				weapon.setDurability(weapon.getDurability() - 1);
		} else {
			int d = (int) weapon.getDamage();
			c.hit(d, weapon, this);
		}
	}

	public boolean moveUp() {
		int yCoord = super.getY();
		int xCoord = super.getX();
		WorldMap map = super.getGame().getMap();
		Tile t = (map.getPoint(new Point(xCoord + 1, yCoord)) == null) ? new Tile(new Terrain(super.getGame(), '.', new Color(0, 142, 25), "sparse", null, 0, 0, true, 0)) : map.getPoint(new Point(xCoord, yCoord + 1));
		Creature c = t.hasCreature();
		if (c != null) {
			attack(c);
		} else {
			if (t.getTerrain().isPassable()) {
				super.setY(yCoord + 1);
				t.addItem(this);
				map.setPoint(new Point(xCoord, yCoord + 1), t);
				map.getPoint(new Point(xCoord, yCoord)).removeCreature();
				return true;
			}
		}
		return false;
	}

	public boolean moveDown() {
		int yCoord = super.getY();
		int xCoord = super.getX();
		WorldMap map = super.getGame().getMap();
		Tile t = (map.getPoint(new Point(xCoord + 1, yCoord)) == null) ? new Tile(new Terrain(super.getGame(), '.', new Color(0, 142, 25), "sparse", null, 0, 0, true, 0)) : map.getPoint(new Point(xCoord, yCoord - 1));
		Creature c = t.hasCreature();
		if (c != null) {
			attack(c);
		} else {
			if (t.getTerrain().isPassable()) {
				super.setY(yCoord - 1);
				t.addItem(this);
				map.setPoint(new Point(xCoord, yCoord - 1), t);
				map.getPoint(new Point(xCoord, yCoord)).removeCreature();
				return true;
			}
		}
		return false;
	}

	public boolean moveLeft() {
		WorldMap map = super.getGame().getMap();
		int yCoord = super.getY();
		int xCoord = super.getX();
		Tile t = (map.getPoint(new Point(xCoord - 1, yCoord)) == null) ? new Tile(new Terrain(super.getGame(), '.', new Color(0, 142, 25), "sparse", null, 0, 0, true, 0)) : map.getPoint(new Point(xCoord - 1, yCoord));
		Creature c = t.hasCreature();
		if (c != null) {
			attack(c);
		} else {
			if (t.getTerrain().isPassable()) {
				super.setX(xCoord - 1);
				t.addItem(this);
				map.setPoint(new Point(xCoord - 1, yCoord), t);
				map.getPoint(new Point(xCoord, yCoord)).removeCreature();
				return true;
			}
		}
		return false;
	}

	public boolean moveRight() {
		WorldMap map = super.getGame().getMap();
		int yCoord = super.getY();
		int xCoord = super.getX();
		Tile t = (map.getPoint(new Point(xCoord + 1, yCoord)) == null) ? new Tile(new Terrain(super.getGame(), '.', new Color(0, 142, 25), "sparse", null, 0, 0, true, 0)) : map.getPoint(new Point(xCoord + 1, yCoord));
		Creature c = t.hasCreature();
		if (c != null) {
			attack(c);
		} else {
			if (t.getTerrain().isPassable()) {
				super.setX(xCoord + 1);
				t.addItem(this);
				map.setPoint(new Point(xCoord + 1, yCoord), t);
				map.getPoint(new Point(xCoord, yCoord)).removeCreature();
				return true;
			}
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
	 * @param health
	 *            the health to set
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
	 * @param armorValue
	 *            the armorValue to set
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
	 * @param maxHealth
	 *            the maxHealth to set
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
	 * @param healthMod
	 *            the healthMod to set
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
	 * @param armorMod
	 *            the armorMod to set
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
	 * @param attackMod
	 *            the attackMod to set
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
	 * @param dHealth
	 *            the dHealth to set
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
	 * @param inventory
	 *            the inventory to set
	 */
	public void setInventory(ArrayList<Item> inventory) {
		this.inventory = inventory;
	}

	/**
	 * @return the delay
	 */
	public int getDelay() {
		return delay;
	}

	/**
	 * @param delay
	 *            the delay to set
	 */
	public void setDelay(int delay) {
		this.delay = delay;
	}

	public void addDelay(int e) {
		delay += e;
	}

	public void removeDelay(int e) {
		delay -= e;
	}

	/**
	 * @return the arms
	 */
	public Clothing getArms() {
		return arms;
	}

	/**
	 * @param arms
	 *            the arms to set
	 */
	public void setArms(Clothing arms) {
		this.arms = arms;
	}

	/**
	 * @return the legs
	 */
	public Clothing getLegs() {
		return legs;
	}

	/**
	 * @param legs
	 *            the legs to set
	 */
	public void setLegs(Clothing legs) {
		this.legs = legs;
	}

	/**
	 * @return the torso
	 */
	public Clothing getTorso() {
		return torso;
	}

	/**
	 * @param torso
	 *            the torso to set
	 */
	public void setTorso(Clothing torso) {
		this.torso = torso;
	}

	/**
	 * @return the head
	 */
	public Clothing getHead() {
		return head;
	}

	/**
	 * @param head
	 *            the head to set
	 */
	public void setHead(Clothing head) {
		this.head = head;
	}

	/**
	 * @return the feet
	 */
	public Clothing getFeet() {
		return feet;
	}

	/**
	 * @param feet
	 *            the feet to set
	 */
	public void setFeet(Clothing feet) {
		this.feet = feet;
	}

	/**
	 * @return the hands
	 */
	public Clothing getHands() {
		return hands;
	}

	/**
	 * @param hands
	 *            the hands to set
	 */
	public void setHands(Clothing hands) {
		this.hands = hands;
	}

	/**
	 * @return the weapon
	 */
	public Item getWeapon() {
		return weapon;
	}

	/**
	 * @param weapon
	 *            the weapon to set
	 */
	public void setWeapon(Item weapon) {
		this.weapon = weapon;
	}

}
