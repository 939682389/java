import java.awt.Image;
import java.awt.Graphics;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Player{
	
	private int state;
	private final int Alive = 1;
	private final int Dead = 0;
	
	private int planeX;
	private int planeY;
	private Image player;
	private int planeStep = 20;
	private int picWidth = 40;
	private int picHeight = 40;
	
	private int life;
	
	public Player(){
		try{
			File fb0 = new File("images\\Player.png");
			player = ImageIO.read(fb0);
		}catch(Exception e){
			e.printStackTrace();
		}
		state = Dead;
		life = 3;
	}
	
	public int getLife(){
		return life;
	}
	
	public void setDead(){
		state = Dead;
		life--;
	}
	
	public boolean isAlive(){
		return (state == Alive);
	}
	
	public boolean isDead(){
		return (state == Dead);
	}
	
	public int getCenterX(){
		return (planeX + picWidth/2);
	}
	
	public int getCenterY(){
		return (planeY + picHeight/2);
	}
	
	public int getX(){
		return planeX;
	}
	
	public int getY(){
		return planeY;
	}
	
	public void spawnPlane(int x, int y){
		state = Alive;
		planeX = x;
		planeY = y;
	}
	
	public void upMove(){
		if(planeY != 0){
			planeY -= planeStep;
		}
	}
	
	public void downMove(){
		if(planeY != (480-picHeight)){
			planeY += planeStep;
		}
	}
	
	public void leftMove(){
		if(planeX != 0){
			planeX -= planeStep;
		}
	}
	
	public void rightMove(){
		if(planeX != (320- picWidth)){
			planeX += planeStep;
		}
	}
	
	public void drawPlane(Graphics g, JPanel jp){
		if(state == Alive)
			g.drawImage(player, planeX, planeY, jp);
	}
	
}