import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

public class InventoryPanel extends JPanel {

	private Game game;
	private JComboBox<String> holdingDropdown;
	private JComboBox<String> headDropdown;
	private JComboBox<String> armsDropdown;
	private JComboBox<String> handsDropdown;
	private JComboBox<String> torsoDropdown;
	private JComboBox<String> legsDropdown;
	private JComboBox<String> feetDropdown;
	private JTextArea textArea;
	private JLabel health;

	public InventoryPanel(Game game) {
		this.setBackground(Color.BLACK);
		health = new JLabel();
//		health.setFont(new Font("Arial", Font.PLAIN, 18));
		String h = "";
		for (int i = 0; i < game.getPlayer().getHealth() / 20; i++) {
			h += "| ";
		}
		int mod = game.getPlayer().getHealth() % 20;
		if (mod != 0) {
			if (mod < 10) {
				h += ":";
			} else {
				h += "/";
			}
		}
		health.setText("<html>health: <font color = 'green'>" + h + "</font></html>");
		if ((double) game.getPlayer().getHealth() / game.getPlayer().getMaxHealth() < 0.33) {
			health.setText("<html>health: <font color = 'red'>" + h + "</font></html>");
		} else {
			if ((double) game.getPlayer().getHealth() / game.getPlayer().getMaxHealth() < 0.66) {
				health.setText("<html>health: <font color = 'yellow'>" + h + "</font></html>");
			}
		}
		add(health);
		this.game = game;
		genWeapons();

		setBounds(new Rectangle(200, 800));
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		JLabel inventoryLabel = new JLabel("<html><font color = 'white'>Inventory</font></html>");
		JLabel emptyLabel = new JLabel(" ");
		add(inventoryLabel);
		add(emptyLabel);

		JLabel holdingLabel = new JLabel("<html><font color = 'white'>Holding</font></html>");
		add(holdingLabel);

		holdingDropdown = new JComboBox<String>();
		holdingDropdown.setPreferredSize(new Dimension(30, 20));

		holdingDropdown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				holdingDropdownEvent(e);
			}

		});
		add(holdingDropdown);

		JLabel headLabel = new JLabel("<html><font color = 'white'>Head</font></html>");
		add(headLabel);
		headDropdown = new JComboBox<String>();
		headDropdown.setPreferredSize(new Dimension(30, 20));

		headDropdown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				headDropdownEvent(e);
			}

		});
		add(headDropdown);

		JLabel armsLabel = new JLabel("<html><font color = 'white'>Arms</font></html>");
		add(armsLabel);
		armsDropdown = new JComboBox<String>();
		armsDropdown.setPreferredSize(new Dimension(30, 20));

		armsDropdown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				armsDropdownEvent(e);
			}

		});
		add(armsDropdown);

		JLabel handsLabel = new JLabel("<html><font color = 'white'>Hands</font></html>");
		add(handsLabel);
		handsDropdown = new JComboBox<String>();
		handsDropdown.setPreferredSize(new Dimension(30, 20));

		handsDropdown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handsDropdownEvent(e);
			}

		});
		add(handsDropdown);

		JLabel torsoLabel = new JLabel("<html><font color = 'white'>Torso</font></html>");
		add(torsoLabel);
		torsoDropdown = new JComboBox<String>();
		torsoDropdown.setPreferredSize(new Dimension(30, 20));

		torsoDropdown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				torsoDropdownEvent(e);
			}

		});
		add(torsoDropdown);

		JLabel legsLabel = new JLabel("<html><font color = 'white'>Legs</font></html>");
		add(legsLabel);
		legsDropdown = new JComboBox<String>();
		legsDropdown.setPreferredSize(new Dimension(30, 20));
		legsDropdown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				legsDropdownEvent(e);
			}

		});
		add(legsDropdown);

		JLabel feetLabel = new JLabel("<html><font color = 'white'>Feet</font></html>");
		add(feetLabel);
		feetDropdown = new JComboBox<String>();
		feetDropdown.setPreferredSize(new Dimension(30, 20));

		feetDropdown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				feetDropdownEvent(e);
			}

		});
		add(feetDropdown);

		JLabel emptyLabel2 = new JLabel(" ");
		JLabel progressLabel = new JLabel("<html><font color = 'white'>Game Progress</font></html>");
		add(emptyLabel2);
		add(progressLabel);

		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setColumns(15);
		textArea.setRows(80);
		textArea.setEditable(false);

		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		add(scroll);

	}

	public void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g2);

		String h = "";
		for (int i = 0; i < game.getPlayer().getHealth() / 20; i++) {
			h += "| ";
		}
		int mod = game.getPlayer().getHealth() % 20;
		if (mod != 0) {
			if (mod < 10) {
				h += ":";
			} else {
				h += "/";
			}
		}
		if ((double) game.getPlayer().getHealth() / game.getPlayer().getMaxHealth() < 0.33) {
			health.setText("<html><font color = 'white'>health: </font><font color = 'red'>" + h + "</font></html>");
		} else {
			if ((double) game.getPlayer().getHealth() / game.getPlayer().getMaxHealth() < 0.66) {
				health.setText("<html><font color = 'white'>health: </font><font color = 'yellow'>" + h + "</font></html>");
			} else {
				health.setText("<html><font color = 'white'>health: </font><font color = 'green'>" + h + "</font></html>");
			}
		}

		holdingDropdown.removeAllItems();

		Player player = game.getPlayer();
		ArrayList<Item> inventory = player.getInventory();

		String name = player.getHolding().getName();

		holdingDropdown.addItem(name);

		for (Item item : inventory) {

			if (item.getName() != name) {
				holdingDropdown.addItem(item.getName());
			}
		}

		ArrayList<String> progress = game.getProgress();

		while (!progress.isEmpty()) {
			String event = progress.remove(0);
			event = event + "\n\n";
			textArea.append(event);
		}
	}

	private void holdingDropdownEvent(ActionEvent event) {

		String name = (String) holdingDropdown.getSelectedItem();
		Player player = game.getPlayer();

		ArrayList<Item> inventory = player.getInventory();
		for (Item item : inventory) {
			if (item.getName() == name) {
				player.setHolding(item);
				return;
			}
		}
	}

	private void headDropdownEvent(ActionEvent event) {

	}

	private void armsDropdownEvent(ActionEvent event) {

	}

	private void handsDropdownEvent(ActionEvent event) {

	}

	private void torsoDropdownEvent(ActionEvent event) {

	}

	private void legsDropdownEvent(ActionEvent event) {

	}

	private void feetDropdownEvent(ActionEvent event) {

	}

	private void genWeapons() {
		Player player = game.getPlayer();

		String[] tag = {};
		Weapon w1 = new Weapon(game, 'g', Color.BLUE, "GUN", tag, player.getX(), player.getY(), true, 10, 10, 10, 100,
				10, 0, 0, 1, null);
		Weapon w2 = new Weapon(game, 'p', Color.BLACK, "Pistol", tag, player.getX(), player.getY(), true, 10, 10, 10,
				100, 10, 0, 0, 1, null);
		Weapon w3 = new Weapon(game, 'k', Color.WHITE, "Knife", tag, player.getX(), player.getY(), true, 10, 10, 10,
				100, 10, 0, 0, 1, null);
		Weapon w4 = new Weapon(game, 's', Color.GRAY, "Stone", tag, player.getX(), player.getY(), true, 10, 10, 10, 100,
				10, 0, 0, 1, null);

		ArrayList<Item> inventory = player.getInventory();
		if (inventory == null) {
			System.out.println("null");
		}
		inventory.add(w1);
		inventory.add(w2);
		inventory.add(w3);
		inventory.add(w4);
	}

	public void reset() {
		holdingDropdown.removeAllItems();
		headDropdown.removeAllItems();
		armsDropdown.removeAllItems();
		handsDropdown.removeAllItems();
		torsoDropdown.removeAllItems();
		legsDropdown.removeAllItems();
		feetDropdown.removeAllItems();
		textArea.setText("");
		genWeapons();
	}

}