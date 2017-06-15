import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;

public class GameDisplay extends JFrame {

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
		setBounds(300, 10, 1000, 800);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		// graphicics panel
		GraphicsPanel graphicsPanel = new GraphicsPanel(game.getMap());
		contentPane.add(graphicsPanel, BorderLayout.CENTER);	

		// button panel
		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.NORTH);
		
		/*
		 * inventory panel
		 */
		InventoryPanel inventoryPanel = new InventoryPanel(game);
		contentPane.add(inventoryPanel, BorderLayout.EAST);
		
		/*
		 * button for reset
		 */
		JButton quitButton = new JButton("Reset");
		buttonPanel.add(quitButton);
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
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
			}
					
		});
		
		// button for keyboard
		JButton keyboard = new JButton("Keyboard");
		keyboard.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("UP"), "MOVE_UP");
		keyboard.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("DOWN"), "MOVE_DOWN");
		keyboard.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("LEFT"), "MOVE_LEFT");
		keyboard.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("RIGHT"), "MOVE_RIGHT");
		keyboard.getInputMap(JComponent.WHEN_FOCUSED).put(KeyStroke.getKeyStroke("E"), "EXAMINE");
		keyboard.getActionMap().put("MOVE_UP", new MoveAction(1, game.getPlayer(), game));
		keyboard.getActionMap().put("MOVE_DOWN", new MoveAction(3, game.getPlayer(), game));
		keyboard.getActionMap().put("MOVE_LEFT", new MoveAction(2, game.getPlayer(), game));
		keyboard.getActionMap().put("MOVE_RIGHT", new MoveAction(4, game.getPlayer(), game));
		keyboard.getActionMap().put("EXAMINE", new ExamineAction(0, game.getPlayer(), game, keyboard));
		buttonPanel.add(keyboard);
				
	}
	
	public void gameOver() {
		JFrame death = new JFrame();
		death.setDefaultCloseOperation(EXIT_ON_CLOSE);
		JLabel label = new JLabel("You died!");
		death.add(label);
		death.setBounds(700, 500, 150, 100);
		death.setVisible(true);
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
}
