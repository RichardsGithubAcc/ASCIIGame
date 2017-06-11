import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.JComponent;
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
		Game game = new Game(80, 40);
		//game.genForest(-40, -40, 40, 40);
		ArrayList<Entity> n = new ArrayList<Entity>();
		game.setPlayer(new Player(game, 0, 0));
		n.add(game.getPlayer());
		game.getMap().setPoint(new Point(0, 0), new Tile(game.bush, n));
		GraphicsPanel graphicsPanel = new GraphicsPanel(game.getMap());
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 250, 800, 800);

		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "MOVE_UP");
		contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "MOVE_DOWN");
		contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "MOVE_LEFT");
		contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "MOVE_RIGHT");
		contentPane.getActionMap().put("MOVE_UP", new MoveAction(1, game.getPlayer()));
		contentPane.getActionMap().put("MOVE_DOWN", new MoveAction(3, game.getPlayer()));
		contentPane.getActionMap().put("MOVE_LEFT", new MoveAction(2, game.getPlayer()));
		contentPane.getActionMap().put("MOVE_RIGHT", new MoveAction(4, game.getPlayer()));
		
		
		contentPane.add(graphicsPanel, BorderLayout.CENTER);	

	}
	
	public class MoveAction extends AbstractAction {
		int d;
		Player p;
		
		public MoveAction(int dir, Player pl) {
			d = dir;
			p = pl;
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
		}
	}
	
	
}
