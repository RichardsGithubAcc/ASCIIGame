import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;

public class InventoryDisplay extends JPanel  {
	
	private JFrame frame = null;
	private static InventoryDisplay instance = null;
	private Game game;
	private JComboBox<String> handDropdown;
	private JComboBox<String> headDropdown;
	private JComboBox<String> chestDropdown;
	private JComboBox<String> legsDropdown;
	private JComboBox<String> feetDropdown;
	
	private InventoryDisplay() {
		addDropdowns(this);
	}

	public static InventoryDisplay getInstance() {
		if(instance == null) {
			instance = new InventoryDisplay();
		}
		return instance;
	}
 
    public void createWindow(Game game) {
    	
    	if (frame == null) {
    		this.game = game;
    		frame = new JFrame("Inventory");
    		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    		frame.setBounds(500, 280, 400, 200);
       		frame.setPreferredSize(new Dimension(400,200));
 
       		JPanel contentPanel = new JPanel();
       		frame.setContentPane(contentPanel);
    		contentPanel.setOpaque(true);
    		contentPanel.setLayout(new BorderLayout(0, 0));
    		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    		
    		JPanel inventoryPanel = getInstance();
    		inventoryPanel.setLayout(new GridLayout(2,3));  	
    		contentPanel.add(inventoryPanel);
	
      		frame.addWindowListener(new WindowAdapter() {

    			@Override
    			public void windowClosed(WindowEvent e)  {
    				frame = null;
    				contentPanel.remove(inventoryPanel);
    			}
    			
    		});
    		frame.pack();
    		frame.setVisible(true);
    	}
    }
    
	private void handleSelectedEvent(ActionEvent event) {
		
	}
	
	private void addDropdowns(JPanel panel) {
		JPanel handPanel = new JPanel();
		JLabel handLabel = new JLabel("Hand");
        handDropdown = new JComboBox<String>();
    	
    	handDropdown.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				handleSelectedEvent(e);
			}
			
		});
   
     	handPanel.setLayout(new BorderLayout(0, 0));
     	handPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
     	handPanel.add(handLabel, BorderLayout.NORTH);
    	handPanel.add(handDropdown, BorderLayout.CENTER);
    	
       
		JPanel headPanel = new JPanel();
		JLabel headLabel = new JLabel("Head");
		headDropdown = new JComboBox<String>();
 
       	headDropdown.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			handleSelectedEvent(e);
    		}
    			
    	});
       	
     	headPanel.setLayout(new BorderLayout(0, 0));
     	headPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
     	headPanel.add(headLabel, BorderLayout.NORTH);
    	headPanel.add(headDropdown, BorderLayout.CENTER);
    	
		JPanel chestPanel = new JPanel();
		JLabel chestLabel = new JLabel("Chest");
        chestDropdown = new JComboBox<String>();
        
      	chestDropdown.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			handleSelectedEvent(e);
    		}
    			
    	});
 
    	chestPanel.setLayout(new BorderLayout(0, 0));
    	chestPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
    	chestPanel.add(chestLabel, BorderLayout.NORTH);
    	chestPanel.add(chestDropdown, BorderLayout.CENTER);
    	
		JPanel legsPanel = new JPanel();
		JLabel legsLabel = new JLabel("Legs");
        legsDropdown = new JComboBox<String>();

      	legsDropdown.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			handleSelectedEvent(e);
    		}
    			
    	});
      	
     	legsPanel.setLayout(new BorderLayout(0, 0));
     	legsPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
     	legsPanel.add(legsLabel, BorderLayout.NORTH);
    	legsPanel.add(legsDropdown, BorderLayout.CENTER);
    	
		JPanel feetPanel = new JPanel();
		JLabel feetLabel = new JLabel("Feet");
        feetDropdown = new JComboBox<String>();

      	feetDropdown.addActionListener(new ActionListener() {
      		@Override
    		public void actionPerformed(ActionEvent e) {
    			handleSelectedEvent(e);
    		}
    			
    	});
      	
     	feetPanel.setLayout(new BorderLayout(0, 0));
     	feetPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
     	feetPanel.add(feetLabel, BorderLayout.NORTH);
    	feetPanel.add(feetDropdown, BorderLayout.CENTER);
    	
    	panel.add(handPanel);
    	panel.add(headPanel);
    	panel.add(chestPanel);
    	panel.add(legsPanel);
    	panel.add(feetPanel);
	}

}
