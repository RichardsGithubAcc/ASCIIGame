import java.awt.Color;

public class Clothing extends Item {
	private int armorValue;

	public Clothing(Game ge, char i, Color c, String name, String[] t, int x, int y, boolean p, int w, int v, int d,
			int dm, double dmg, int aV) {
		super(ge, i, c, name, t, x, y, p, w, v, d, dm, dmg);
		armorValue = aV;
	}	
}
