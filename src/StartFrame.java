import java.awt.Container;
import javax.swing.JFrame;
import java.awt.BorderLayout;

public class StartFrame extends JFrame {
	
	public StartFrame() {
		setTitle("SpaceWar0.1");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Container cp = getContentPane();
		
		ShowPanel sp = new ShowPanel();
		cp.add(sp, BorderLayout.EAST);
		
		GamePanel gp = new GamePanel(sp);
		cp.add(gp, BorderLayout.CENTER);
		
		pack();
		
		gp.requestFocusInWindow();
		setVisible(true);
	}
	
	public static void main(String[] args) {
		StartFrame gs = new StartFrame();
	}
}
