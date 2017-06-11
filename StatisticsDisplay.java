import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;

public class StatisticsDisplay extends JPanel  {
	
	private JFrame frame = null;
	private static StatisticsDisplay instance = null;
	private Game game;

	private StatisticsDisplay() {
		
	}

	public static StatisticsDisplay getInstance() {
		if(instance == null) {
			instance = new StatisticsDisplay();
		}
		return instance;
	}
 
    public void createWindow(Game game) {
    	
    	if (frame == null) {
    		this.game = game;
    		frame = new JFrame("Statistics");
    		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    		frame.setBounds(500, 280, 400, 200);
       		frame.setPreferredSize(new Dimension(400,200));
 
       		JPanel contentPanel = new JPanel();
       		frame.setContentPane(contentPanel);
    		contentPanel.setOpaque(true);
    		contentPanel.setLayout(new BorderLayout(0, 0));
    		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    		
    		JPanel statisticsPanel = getInstance(); 	
    		contentPanel.add(statisticsPanel);
	
      		frame.addWindowListener(new WindowAdapter() {

    			@Override
    			public void windowClosed(WindowEvent e)  {
    				frame = null;
    			}
    			
    		});
    		frame.pack();
    		frame.setVisible(true);
    	}
    }

}
