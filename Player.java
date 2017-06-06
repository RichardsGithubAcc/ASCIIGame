import java.awt.Color;
import java.util.ArrayList;

public class Player extends Creature implements Dynamic {
	private int hunger, volumeCarried, maxVolume;
	private int strength, dexterity, intelligence, perception;
	private double weightCarried, maxWeight;
	private Item arms, legs, torso, head, feet, hands, weapon;

	public Player(char i, Color c, String n, int x, int y, boolean p, int h, int armV, int mH, double hM, double armM,
			double atkM, int dH, int s, int d, int in, int pe) {
		super(i, c, n, x, y, p, h, armV, mH, hM, armM, atkM, dH);
		// TODO Auto-generated constructor stub
		strength = s;
		dexterity = d;
		intelligence = in;
		perception = pe;
		maxWeight = 20 + 2 * strength;
		hunger = 0;
	}
	
	@Override
	public void update() {
		hunger++;
		maxWeight = 20 + 2 * strength;
		if(weightCarried > maxWeight && super.getDelay() == 0) {
			super.addDelay(1);
		}
		if(volumeCarried > maxVolume && super.getDelay() == 0) {
			super.addDelay(1);
		}
		if(super.getDelay() > 0) {
			super.removeDelay(1);
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
	 * @return the arms
	 */
	public Item getArms() {
		return arms;
	}

	/**
	 * @param arms the arms to set
	 */
	public void setArms(Item arms) {
		this.arms = arms;
	}

	/**
	 * @return the legs
	 */
	public Item getLegs() {
		return legs;
	}

	/**
	 * @param legs the legs to set
	 */
	public void setLegs(Item legs) {
		this.legs = legs;
	}

	/**
	 * @return the torso
	 */
	public Item getTorso() {
		return torso;
	}

	/**
	 * @param torso the torso to set
	 */
	public void setTorso(Item torso) {
		this.torso = torso;
	}

	/**
	 * @return the head
	 */
	public Item getHead() {
		return head;
	}

	/**
	 * @param head the head to set
	 */
	public void setHead(Item head) {
		this.head = head;
	}

	/**
	 * @return the feet
	 */
	public Item getFeet() {
		return feet;
	}

	/**
	 * @param feet the feet to set
	 */
	public void setFeet(Item feet) {
		this.feet = feet;
	}

	/**
	 * @return the hands
	 */
	public Item getHands() {
		return hands;
	}

	/**
	 * @param hands the hands to set
	 */
	public void setHands(Item hands) {
		this.hands = hands;
	}

	/**
	 * @return the weapon
	 */
	public Item getWeapon() {
		return weapon;
	}

	/**
	 * @param weapon the weapon to set
	 */
	public void setWeapon(Item weapon) {
		this.weapon = weapon;
	}
	
}
