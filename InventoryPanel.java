import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
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
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;



public class InventoryPanel extends JPanel {

	private Game game;
	
	private JComboBox<String> handsDropdown;
	private JComboBox<String> headDropdown;
	private JComboBox<String> torsoDropdown;
	private JComboBox<String> armsDropdown;
	private JComboBox<String> legsDropdown;
	private JComboBox<String> feetDropdown;
	private JTextArea textArea;
	
	public InventoryPanel(Game game) {

		this.game = game;
		hardCoded();
		setBounds(new Rectangle(200, 800));
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setName("Inventory");
	
		JLabel handsLabel = new JLabel("Hands");
		add(handsLabel);
        handsDropdown = new JComboBox<String>();
        handsDropdown.setPreferredSize(new Dimension(30,20));

    	handsDropdown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleSelectedEvent(e);
			}
			
		});
    	add(handsDropdown);
	
		JLabel headLabel = new JLabel("Head");
		add(headLabel);
        headDropdown = new JComboBox<String>();
        headDropdown.setPreferredSize(new Dimension(30,20));

    	headDropdown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleSelectedEvent(e);
			}
			
		});
    	add(headDropdown);
    
		JLabel torsoLabel = new JLabel("Torso");
		add(torsoLabel);
        torsoDropdown = new JComboBox<String>();
        torsoDropdown.setPreferredSize(new Dimension(30,20));

    	torsoDropdown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleSelectedEvent(e);
			}
			
		});
    	add(torsoDropdown);

		JLabel armsLabel = new JLabel("Arms");
		add(armsLabel);
        armsDropdown = new JComboBox<String>();
        armsDropdown.setPreferredSize(new Dimension(30,20));

    	armsDropdown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleSelectedEvent(e);
			}
			
		});
    	add(armsDropdown);
    	
		JLabel legsLabel = new JLabel("Legs");
		add(legsLabel);
        legsDropdown = new JComboBox<String>();
        legsDropdown.setPreferredSize(new Dimension(30,20));

    	torsoDropdown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleSelectedEvent(e);
			}
			
		});
    	add(legsDropdown);

		JLabel feetLabel = new JLabel("Feet");
		add(feetLabel);
        feetDropdown = new JComboBox<String>();
        feetDropdown.setPreferredSize(new Dimension(30,20));

    	feetDropdown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleSelectedEvent(e);
			}
			
		});
    	add(feetDropdown);
    	
    	JLabel emptyLabel = new JLabel("           ");
    	JLabel progressLabel = new JLabel("Game Progress");
    	add(emptyLabel);
		add(progressLabel);
		
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setColumns(15);
		textArea.setRows(80);
		textArea.setEditable(false);
	
		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		setBorder (new EmptyBorder(5, 5, 5, 5));		
		add(scroll);
	}


	public void paintComponent(Graphics g) {

		Graphics2D g2 = (Graphics2D)g;
		super.paintComponent(g2);
		
		handsDropdown.removeAllItems();
		
		Player player = game.getPlayer();
		ArrayList<Item> inventory = player.getInventory();

		String name = player.getWeapon().getName();

		handsDropdown.addItem(name);
			
		for (Item item: inventory) {
		
		    if (item.getName() != name) {
		    	handsDropdown.addItem(item.getName());
		    } 
		}
		
		ArrayList<String> progress = game.getProgress();
		
        while (!progress.isEmpty()) {
        	String event = progress.remove(0);
        	event  = event + "\n\n";
        	textArea.append(event);
        }
	}

 
    
	private void handleSelectedEvent(ActionEvent event) {
		
		
		String name =  (String)handsDropdown.getSelectedItem();
		Player player = game.getPlayer();

		ArrayList<Item> inventory = player.getInventory();
	    for (Item item: inventory) {
	    	if (item.getName() == name) {
	    		player.setWeapon(item);
	    		return;
	    	} 
	    }
	}
	
	// test code, will be removed
	private void hardCoded() {

		Player player = game.getPlayer();
		String[] tag = {}; 
		Weapon w1 = new Weapon(game, 'g', Color.BLUE, "GUN", tag, player.getX(), player.getY(), true, 10, 10, 10, 100, 10, 0,0,1,null);
		Weapon w2 = new Weapon(game, 'p', Color.BLACK, "Pistol", tag, player.getX(), player.getY(), true, 10, 10, 10, 100, 10, 0,0,1,null);
		Weapon w3 = new Weapon(game, 'k', Color.WHITE, "Knife", tag, player.getX(), player.getY(), true, 10, 10, 10, 100, 10, 0,0,1,null);
		Weapon w4 = new Weapon(game, 's', Color.GRAY, "Stone", tag, player.getX(), player.getY(), true, 10, 10, 10, 100, 10, 0,0,1,null);
		
		ArrayList<Item> inventory = player.getInventory();
		if (inventory == null) {
			System.out.println("null");
		}
		
	    inventory.add(w1);
	    inventory.add(w2);
	    inventory.add(w3);
	    inventory.add(w4);
	} 

}
