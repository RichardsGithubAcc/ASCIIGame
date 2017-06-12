import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;

public class UserGuide extends JPanel  {
	
	private JFrame frame = null;
	private static UserGuide instance = null;
	private Game game;

	private UserGuide() {
		
	}

	public static UserGuide getInstance() {
		if(instance == null) {
			instance = new UserGuide();
		}
		return instance;
	}
 
    public void createWindow(Game game) {
    	
    	if (frame == null) {
    		this.game = game;
    		frame = new JFrame("User Guide");
    		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    		frame.setBounds(500, 280, 400, 200);
       		frame.setPreferredSize(new Dimension(400,200));
 
       		JPanel contentPanel = new JPanel();
       		frame.setContentPane(contentPanel);
    		contentPanel.setOpaque(true);
    		contentPanel.setLayout(new BorderLayout(0, 0));
    		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
    		
    		JPanel userGuidePanel = getInstance(); 	
    		contentPanel.add(userGuidePanel);
	
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
