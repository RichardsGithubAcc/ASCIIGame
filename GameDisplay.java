import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.JFrame;
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
		
		Game game = new Game(80, 80);
		ArrayList<Entity> n = new ArrayList<Entity>();
		game.setPlayer(new Player(game, 0, 0));
		n.add(game.getPlayer());
		game.getMap().setPoint(new Point(0, 0), new Tile(game.bush, n));
		GraphicsPanel graphicsPanel = new GraphicsPanel(game.getMap());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(300, 0, 1000, 1000);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "MOVE_UP");
		contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "MOVE_DOWN");
		contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "MOVE_LEFT");
		contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "MOVE_RIGHT");
		contentPane.getActionMap().put("MOVE_UP", new MoveAction(1, game.getPlayer(), game));
		contentPane.getActionMap().put("MOVE_DOWN", new MoveAction(3, game.getPlayer(), game));
		contentPane.getActionMap().put("MOVE_LEFT", new MoveAction(2, game.getPlayer(), game));
		contentPane.getActionMap().put("MOVE_RIGHT", new MoveAction(4, game.getPlayer(), game));
		
		contentPane.add(graphicsPanel, BorderLayout.CENTER);	
		// button panel
		JPanel buttonPanel = new JPanel();
		contentPane.add(buttonPanel, BorderLayout.NORTH);
							
		/*
		 * button to open inventory
		 */
		InventoryDisplay inventoryDisplay = InventoryDisplay.getInstance();
		JButton inventoryButton = new JButton("Inventory");
		buttonPanel.add(inventoryButton);
			@Override
			public void actionPerformed(ActionEvent e) {
				inventoryDisplay.createWindow(game);
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

			
		/*
		 * quit button
		 */
		JButton quitButton = new JButton("Quit");
		buttonPanel.add(quitButton);
		quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
					
		});
				
				
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
	
	
}
