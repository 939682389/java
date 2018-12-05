import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.Graphics;

public class ShowPanel extends JPanel{
	
	private Font font;
	
	private JLabel slabel;
	private JLabel score;
	
	private JLabel llabel;
	private JLabel life;
	
	private Image gameover;
	private int overX;
	private int overY;
	
	public ShowPanel(){
		this.setPreferredSize(new Dimension(197, 480));
		this.setBackground(new Color(180, 105, 65));
		this.setLayout(new GridLayout(6, 1));
		
		font = new Font("TimeRoman", Font.PLAIN, 30);
		
		slabel = new JLabel("Score:");
		slabel.setHorizontalAlignment(SwingConstants.CENTER);
		slabel.setFont(font);
		add(slabel);
		
		score = new JLabel("0");
		score.setHorizontalAlignment(SwingConstants.CENTER);
		score.setFont(font);
		add(score);
		
		llabel = new JLabel("Life:");
		llabel.setHorizontalAlignment(SwingConstants.CENTER);
		llabel.setFont(font);
		add(llabel);
		
		life = new JLabel("3");
		life.setHorizontalAlignment(SwingConstants.CENTER);
		life.setFont(font);
		add(life);
		
		initGameover();
	}
	
	public void initGameover(){
		try{
			File fb0 = new File("images\\gameover.png");
			gameover = ImageIO.read(fb0);
		}catch(Exception e){
			e.printStackTrace();
		}
		overX = 110;
		overY = 480;
	}
	
	public void drawGameover(Graphics g, JPanel jp){
		if(overY > 170){
			g.drawImage(gameover, overX, overY, jp);
			overY -= 10;
		}else{
			g.drawImage(gameover, overX, overY, jp);
		}
	}
	
	public void updateScore(int a){
		Integer ia = new Integer(a);
		score.setText(ia.toString());
	}
	
	public void updateLife(int a){
		Integer ia = new Integer(a);
		life.setText(ia.toString());
	}
}