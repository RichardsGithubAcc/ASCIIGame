import java.awt.Color;
import java.util.ArrayList;

public class Player extends Creature implements Dynamic {
	private int hunger, volumeCarried, maxVolume;
	private int strength, dexterity, intelligence, perception;
	private double weightCarried, maxWeight;
	

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
		volumeCarried = 0;
		weightCarried = 0;
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
	
	public void attack(Creature c) {
		if(super.getWeapon() instanceof Weapon) {
			double strM = ((strength/10) + 1)/2;
			int d = (int) (((Weapon)super.getWeapon()).getAttackMod() * super.getWeapon().getDamage() * strM);
			c.hit(d, super.getWeapon(), this);
			int red = (int)Math.pow(c.getArmorVal() * c.getArmorMod(), 0.926);
			if(d - red <= 0) super.getWeapon().setDurability(super.getWeapon().getDurability() - 1);
		}
		else {
			double strM = ((strength/10) + 1)/2;
			int d = (int)(super.getWeapon().getDamage() * strM);
			c.hit(d, super.getWeapon(), this);
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
}
