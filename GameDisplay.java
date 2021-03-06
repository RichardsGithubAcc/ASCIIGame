import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

public class GameDisplay extends JFrame {
	private JButton keyboard;
	private JFrame deathFrame;
	
	public static void main(String[]args) { 
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameDisplay frame = new GameDisplay("2D Roguelike Game");

					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
	
	public GameDisplay(String title) {
		super(title);
		
		Game game = new Game(80, 80, this);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 10, 1300, 1000);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		// graphicics panel
		GraphicsPanel graphicsPanel = new GraphicsPanel(game);
		contentPane.add(graphicsPanel, BorderLayout.CENTER);	

		// button panel
		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.NORTH);
		
		InventoryPanel inventoryPanel = new InventoryPanel(game);
		contentPane.add(inventoryPanel, BorderLayout.EAST);
		
		
		
		/*
		 * inventory panel
		 */
//		InfoPanel infoPanel = new InfoPanel(game);
//		contentPane.add(infoPanel, BorderLayout.EAST);
		
		/*
		 * button for reset
		 */
		JButton quitButton = new JButton("Reset");
		buttonPanel.add(quitButton);
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				game.reset();
				//inventoryPanel.reset();
				keyboard.getActionMap().clear();
				keyboard.getActionMap().put("MOVE_UP", new MoveAction(1, game.getPlayer(), game));
				keyboard.getActionMap().put("MOVE_DOWN", new MoveAction(3, game.getPlayer(), game));
				keyboard.getActionMap().put("MOVE_LEFT", new MoveAction(2, game.getPlayer(), game));
				keyboard.getActionMap().put("MOVE_RIGHT", new MoveAction(4, game.getPlayer(), game));
				keyboard.getActionMap().put("EXAMINE", new ExamineAction(0, game.getPlayer(), game, keyboard));
				if (deathFrame != null) {
					deathFrame.dispose();
				}
				repaint();
			}
					
		});
		
		JButton inventoryButton = new JButton("Inventory");
		buttonPanel.add(inventoryButton);
		inventoryButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame inventoryFrame = new JFrame();
				inventoryFrame.setVisible(true);
				inventoryFrame.setBounds(500, 500, 300, 500);
				//inventoryFrame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
				inventoryFrame.setContentPane(new InventoryPanel(game));
			}
		});
				
		/*
		 * button for stats
		 */
		StatisticsDisplay statisticsDisplay = StatisticsDisplay.getInstance();
		JButton statisticsButton = new JButton("Statistics");
		buttonPanel.add(statisticsButton);
		statisticsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				statisticsDisplay.createWindow(game);
			}
					
		});
				
		/*
		 * button for user guide
		 */
		UserGuide userGuide = UserGuide.getInstance();
		JButton userGuideButton = new JButton("User Guide");
		buttonPanel.add(userGuideButton);
		userGuideButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				userGuide.createWindow(game);
				userGuide.add(new JLabel("  -Welcome to the ASCII Rogue demo-  "));
				userGuide.add(new JLabel("   Use the arrow keys to move   "));
				userGuide.add(new JLabel("Move into enemies to attack them"));
				userGuide.add(new JLabel("Press 'e' + a direction to interact with the environment"));
				userGuide.add(new JLabel("Use 'c' to pick up items on the ground"));
				userGuide.add(new JLabel("Press the 'Keyboard' button to shift the focus onto the screen"));
				userGuide.add(new JLabel("Press 'f' with a gun in hand to aim(using the arrow keys) and again to fire"));
				
			}
					
		});
		
		// button for keyboard
		keyboard = new JButton("Keyboard");
		keyboard.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("UP"), "MOVE_UP");
		keyboard.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("DOWN"), "MOVE_DOWN");
		keyboard.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("LEFT"), "MOVE_LEFT");
		keyboard.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("RIGHT"), "MOVE_RIGHT");
		keyboard.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("E"), "EXAMINE");
		keyboard.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("I"), "INVENTORY");
		keyboard.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("F"), "FIRE");
		keyboard.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("C"), "PICK_UP");
		keyboard.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("D"), "DROP");
		keyboard.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("<"), "MOVE_Z_UP");
		keyboard.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke(">"), "MOVE_Z_DOWN");
		keyboard.getActionMap().put("MOVE_UP", new MoveAction(1, game.getPlayer(), game));
		keyboard.getActionMap().put("MOVE_DOWN", new MoveAction(3, game.getPlayer(), game));
		keyboard.getActionMap().put("MOVE_LEFT", new MoveAction(2, game.getPlayer(), game));
		keyboard.getActionMap().put("MOVE_RIGHT", new MoveAction(4, game.getPlayer(), game));
		keyboard.getActionMap().put("EXAMINE", new ExamineAction(0, game.getPlayer(), game, keyboard));
		keyboard.getActionMap().put("INVENTORY", new InventoryAction(game));
		keyboard.getActionMap().put("FIRE", new AimAction(game,game.getPlayer(), graphicsPanel, keyboard));
		keyboard.getActionMap().put("PICK_UP", new PickUpAction(game, game.getPlayer(), keyboard));
		keyboard.getActionMap().put("DROP", new DropAction(game, game.getPlayer(), keyboard));
		keyboard.getActionMap().put("MOVE_Z_UP", new ZAction(game, 1));
		keyboard.getActionMap().put("MOVE_Z_DOWN", new ZAction(game, -1));
		buttonPanel.add(keyboard);
				
	}
	
	public void gameOver() {
		deathFrame = new JFrame();
		deathFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JLabel label = new JLabel("You died!");
		deathFrame.add(label);
		deathFrame.setBounds(700, 500, 150, 100);
		deathFrame.setVisible(true);
	}
	
	public class MoveAction extends AbstractAction {
		int d;
		Player p;
		Game g;
		
		public MoveAction(int dir, Player pl, Game g) {
			d = dir;
			p = pl;
			this.g = g;
		}
		
		public void actionPerformed(ActionEvent e) {
			switch(d) {
			case 1: p.moveUp();
				break;
			case 2: p.moveLeft();
				break;
			case 3: p.moveDown();
				break;
			case 4: p.moveRight();
				break;
			}
			repaint();
			g.update();
		}
	}
	
	public class PickUpAction extends AbstractAction {
		Game g;
		Player p;
		JButton b;
		
		public PickUpAction(Game g, Player p, JButton b) {
			this.g = g;
			this.p = p;
			this.b = b;
		}
		
		public void actionPerformed(ActionEvent e) {
			p.pickUp();
		}
	}
	
	public class DropAction extends AbstractAction {
		Game g;
		Player p;
		JButton b;
		
		public DropAction(Game g, Player p, JButton b) {
			this.g = g;
			this.p = p;
			this.b = b;
		}
		
		public void actionPerformed(ActionEvent e) {
			p.drop();
		}
	}
	
	public class ExamineAction extends AbstractAction {
		int d;
		Player p;
		Game g;
		JButton button;
		
		public ExamineAction(int dir, Player pl, Game g, JButton b) {
			d = dir;
			p = pl;
			this.g = g;
			button = b;
		}
		
		public void actionPerformed(ActionEvent e) {
			button.getActionMap().put("MOVE_UP", new ExamineDirection(1, g.getPlayer(), g, button));
			button.getActionMap().put("MOVE_LEFT", new ExamineDirection(2, g.getPlayer(), g, button));
			button.getActionMap().put("MOVE_DOWN", new ExamineDirection(3, g.getPlayer(), g, button));
			button.getActionMap().put("MOVE_RIGHT", new ExamineDirection(4, g.getPlayer(), g, button));
			
		}
	}
	
	public class ExamineDirection extends AbstractAction {
		int d;
		Player p;
		Game g;
		JButton keyboard;
		
		public ExamineDirection(int dir, Player pl, Game g, JButton b) {
			d = dir;
			p = pl;
			this.g = g;
			keyboard = b;
		}
		
		public void actionPerformed(ActionEvent e) {
			switch(d) {
			case 1: p.examine(0, 1);
				break;
			case 2: p.examine(-1, 0);
				break;
			case 3: p.examine(0, -1);
				break;
			case 4: p.examine(1, 0);
				break;
			}
			repaint();
			g.update();
			keyboard.getActionMap().put("MOVE_UP", new MoveAction(1, g.getPlayer(), g));
			keyboard.getActionMap().put("MOVE_DOWN", new MoveAction(3, g.getPlayer(), g));
			keyboard.getActionMap().put("MOVE_LEFT", new MoveAction(2, g.getPlayer(), g));
			keyboard.getActionMap().put("MOVE_RIGHT", new MoveAction(4, g.getPlayer(), g));
		}
	}
	
	public class InventoryAction extends AbstractAction {
		Game g;
		
		public InventoryAction(Game game) {
			g = game;
		}
		
		public void actionPerformed(ActionEvent e) {
			JFrame inventoryFrame = new JFrame();
			inventoryFrame.setVisible(true);
			inventoryFrame.setBounds(500, 500, 300, 500);
			//inventoryFrame.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			inventoryFrame.setContentPane(new InventoryPanel(g));
		}
	}
	
	public class AimAction extends AbstractAction {
		Game g;
		Weapon w;
		GraphicsPanel display;
		JButton keyboard;
		
		public AimAction(Game game, Creature c, GraphicsPanel lol, JButton button) {
			g = game;
			Item i = c.getHolding();
			if(i != null && i instanceof Weapon) {
				w = (Weapon)i;
			}
			display = lol;
			keyboard = button;
		}
		
		public void actionPerformed(ActionEvent e) {
			w = (Weapon)g.getPlayer().getHolding();
			if(w instanceof Weapon && w.getInventory()[0] != null && w.getInventory()[0].getDurability() > 0) {
				
			} else {
				g.getProgress().add("You can't do that!");
				repaint();
				return;
			}
			keyboard.getActionMap().put("MOVE_UP", new AimDirection(g, w, 1, display, keyboard));
			keyboard.getActionMap().put("MOVE_LEFT", new AimDirection(g, w, 2, display, keyboard));
			keyboard.getActionMap().put("MOVE_DOWN", new AimDirection(g, w, 3, display, keyboard));
			keyboard.getActionMap().put("MOVE_RIGHT", new AimDirection(g, w, 4, display, keyboard));
			keyboard.getActionMap().put("FIRE", new AimDirection(g, w, 5, display, keyboard));
			display.setAim(new Point(g.getPlayer().getX(), g.getPlayer().getY()));
			
		}
	}
	
	public class AimDirection extends AbstractAction {
		Game g;
		Weapon w;
		int d;
		GraphicsPanel display;
		JButton keyboard;
		
		public AimDirection(Game game, Weapon we, int dir, GraphicsPanel lol, JButton button) {
			g = game;
			w = we;
			d = dir;
			display = lol;
			keyboard = button;
		}
		
		public void actionPerformed(ActionEvent e) {
			if(w == null) return;
			//display.setAim(new Point(g.getPlayer().getX(),A g.getPlayer().getY()));
			switch(d) {
			case 1: display.moveUp();
				break;
			case 2: display.moveLeft();
				break;
			case 3: display.moveDown();
				break;
			case 4: display.moveRight();
				break;
			case 5: Weapon w = (Weapon)g.getPlayer().getHolding();
				System.out.println(w instanceof Weapon);
				int accMod = (g.getPlayer().getPerception() - 10) * 10;
				if(w != null && w instanceof Weapon) {
					Point p = display.getAim();
					System.out.println(p.x + " " + p.y);
					w.fire(g.getPlayer().getX(), g.getPlayer().getY(), p.x, p.y, accMod, g.getPlayer());
					repaint();
				}
				display.setAim(null);
				keyboard.getActionMap().put("FIRE", new AimAction(g, g.getPlayer(), display, keyboard));
				keyboard.getActionMap().put("MOVE_UP", new MoveAction(1, g.getPlayer(), g));
				keyboard.getActionMap().put("MOVE_DOWN", new MoveAction(3, g.getPlayer(), g));
				keyboard.getActionMap().put("MOVE_LEFT", new MoveAction(2, g.getPlayer(), g));
				keyboard.getActionMap().put("MOVE_RIGHT", new MoveAction(4, g.getPlayer(), g));
				break;
			}
			repaint();
		}
	}
	
	public class ZAction extends AbstractAction {
		Game game;
		int z;
		
		public ZAction(Game g, int zDir) {
			game = g;
			z = zDir;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(z == 1) {
				game.getPlayer().moveZUp();
			} else {
				game.getPlayer().moveZDown();
			}
			repaint();
		}
	}
}
