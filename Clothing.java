import java.awt.Color;

public class Clothing extends Item {
	private int armorValue;
	private String slot;

	public Clothing(Game ge, char i, Color c, String name, String[] t, int x, int y, boolean p, int w, int v, int d,
			int dm, double dmg, int aV, String slot) {
		super(ge, i, c, name, t, x, y, p, w, v, d, dm, dmg);
		armorValue = aV;
		this.slot = slot;
	}
	
	public Clothing(Clothing origin) {
		super(origin);
		armorValue = origin.getArmorValue();
		slot = origin.getSlot();
	}

	/**
	 * @return the armorValue
	 */
	public int getArmorValue() {
		return armorValue;
	}

	/**
	 * @param armorValue the armorValue to set
	 */
	public void setArmorValue(int armorValue) {
		this.armorValue = armorValue;
	}

	/**
	 * @return the slot
	 */
	public String getSlot() {
		return slot;
	}

	/**
	 * @param slot the slot to set
	 */
	public void setSlot(String slot) {
		this.slot = slot;
	}	
}
