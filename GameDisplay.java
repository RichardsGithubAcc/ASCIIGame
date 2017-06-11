import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
		
		Game game = new Game(80, 40);
		game.genForest(-40, -40, 40, 40);
		//game.getMap().setPoint(new Point(0, 0), new Tile(new Terrain(game, '%', Color.RED, "debugTile", null, 0, 0, true, 0), null));
		GraphicsPanel graphicsPanel = new GraphicsPanel(game.getMap());
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(650, 280, 800, 800);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
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
				inventoryButton.addActionListener(new ActionListener() {
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
	
	public class Key implements KeyListener {
		private Game g;
		
		public Key(Game g) {
			this.g = g;
		}

		@Override
		public void keyTyped(KeyEvent e) {
			int key = e.getKeyCode();
			switch(key) {
			case KeyEvent.VK_UP: g.getPlayer().moveUp();
				break;
			case KeyEvent.VK_LEFT: g.getPlayer().moveLeft();
				break;
			case KeyEvent.VK_DOWN: g.getPlayer().moveDown();
				break;
			case KeyEvent.VK_RIGHT: g.getPlayer().moveRight();
			
			}
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
