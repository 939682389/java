import java.awt.Image;
import java.awt.Graphics;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.util.Random;

public class EnemyPlane{
	
	private Image enemyPic = null;
	private Image[] explosionPics = null;
	
	private int state;
	private final int Alive  = 0;
	private final int Dead = 1;
	
	private int enemyX = 0;
	private int enemyY = 0;
	private int enemyStep = 20;
	
	private int numOfEnemy = 8;
	
	private int picWidth = 40;
	private int picHeight = 40;
	
	private int explosionCount = 5;
	private int explosionX;
	private int explosionY;
	
	public EnemyPlane(){
		try{
			File fb0 = new File("images\\enemy.png");
			enemyPic = ImageIO.read(fb0);
		}catch(Exception e){
			e.printStackTrace();
		}
		state = Dead;
		
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
		return enemyX + picWidth/2;
	}
	
	public int getCenterY(){
		return enemyY + picHeight/2;
	}
	
	public boolean isDead(){
		return ((state == Dead)&&(explosionCount>=5));
	}
	
	public boolean isAlive(){
		return (state == Alive);
	}
	
	public void setDead(){
		state = Dead;
		explosionCount = 0;
		explosionX = enemyX;
		explosionY = enemyY;
	}
	
	public void spawnEnemy(){
		state = Alive;
		Random r = new Random();
		enemyX = r.nextInt(320-picWidth);
		enemyY = -1*(40 + r.nextInt(480-picHeight));
	}
	
	public void update(){
		if(state == Alive){
			enemyY += enemyStep;
			if(enemyY > 480){
				state = Dead;
			}
		}
	}
	
	
	public void drawEnemy(Graphics g, JPanel jp){
		if(state == Alive){
			g.drawImage(enemyPic, enemyX, enemyY, jp);
		}else if(state == Dead && explosionCount < 5){
			g.drawImage(explosionPics[explosionCount], explosionX, explosionY, jp);
			explosionCount++;
		}
	}
	
}