import java.awt.Image;
import java.awt.Graphics;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class EnemyBullet {
	private Image bulletPic = null;
	private int state ;
	
	private final int InScreen = 0;
	private final int OutScreen = 1;
	
	private int bulletX;
	private int bulletY;
	private final int bulletStep = 30;
	public EnemyPlane enemie;
	private int picWidth = 40;
	private int picHeight = 28;
	private Image[] explosionPics = null;

	public EnemyBullet(EnemyPlane enemie){
		this.enemie=enemie;
		try{
			File fb0 = new File("images\\enemybullet.png");
			bulletPic = ImageIO.read(fb0);
		}catch(Exception e){
			e.printStackTrace();
		}
		state = OutScreen;
		
		explosionPics = new Image[5];
		
		for(int i=0; i<5; i++){
			String fileName = "images\\explode"+i+".png";
			try{
				File fb0 = new File(fileName);
				explosionPics[i] = ImageIO.read(fb0);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public int getCenterX(){
		return (bulletX + picWidth/2);
	}
	
	public int getCenterY(){
		return (bulletY + picHeight /2);
	}
	
	public void update(){
		if(state == InScreen){
			bulletY += bulletStep;//ÏòÏÂ
			if(bulletY > 480){
				state = OutScreen;
				
			}
		}
	}
	
	public void shoot(int planeX, int planeY){
		state = InScreen;
		bulletX = planeX;
		bulletY = planeY+picHeight;
	}
	
	public boolean isOutScreen(){
		return (state == OutScreen);
	}
	
	public boolean isInScreen(){
		return (state == InScreen);
	}
	
	public void setOutScreen(){
		state = OutScreen;
	}
	
	public void drawBullet(Graphics g, JPanel jp){
		if(state == InScreen){
			g.drawImage(bulletPic, bulletX, bulletY, jp);
		}
	}
}
