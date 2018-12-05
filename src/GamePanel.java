import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.List;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel implements
Runnable, KeyListener{
	
	private int width = 320;
	private int height = 480;
	private final int backStep = 10; 
	
	private Image backScene0;
	private int back0Y;
	private Image backScene1;
	private int back1Y;
	
	private Player player = null;
	
	private Bullet[] bullets = null;
	private ArrayList<EnemyBullet> enemybullets = new ArrayList<EnemyBullet>();
	private int numOfBullets = 15;
	private int numOfenemybullets = 0;
	private int shootInterval = 400;
	private int enemyshootInterval = 400;
	private EnemyPlane [] enemies = null;
	private int numOfEnemy = 8;
	
	private int r = 30;
	private Sound sound;
	
	private int score = 0;
	private ShowPanel gui;
	private int rebirthInterval = 0;
	
	public GamePanel(ShowPanel sp){//游戏开始
		gui = sp;
		this.setPreferredSize(new Dimension(width, height));
		this.setVisible(true);
		//this.setFocusable(true);
		
		createBackScreen();//创建背景
		createPlayer();//创建玩家
		createBullets();//创建子弹
		createEnemies();//创建敌军
		
		sound = new Sound(player);
		sound.shootVoice();
		
		Thread t = new Thread(this);
		t.start();
		
		this.addKeyListener(this);
	}
	
	public void createPlayer(){
		player = new Player();
		player.spawnPlane(140, 440);
		gui.updateLife(player.getLife());
	}
	
	public void updatePlayer(){
		if(player.isDead() && player.getLife()>0){
			if(rebirthInterval ==25){
				player.spawnPlane(140, 440);//重生
				sound.shootVoice();
				rebirthInterval=0;
			}else{
				rebirthInterval++;
			}
		}
	}
	
	public void createEnemies(){//创建敌军
		enemies = new EnemyPlane[numOfEnemy];
		for(int i=0; i<numOfEnemy; i++)
			enemies[i] = new EnemyPlane();
	}
	
	public void createBullets(){//创建子弹
		bullets = new Bullet[numOfBullets];
		for(int i=0; i<numOfBullets; i++){
			bullets[i] = new Bullet();
		}
	}
	
	private void createBackScreen(){//创建背景
		try{
			File fb0 = new File("images\\map0.png");
			backScene0 = ImageIO.read(fb0);
			File fb1 = new File("images\\map1.png");
			backScene1 = ImageIO.read(fb1);
		}catch(Exception e){
			e.printStackTrace();
		}
		back1Y=0;
		back0Y=-1*height;
	}
	
	public boolean checkCollision(int x1, int y1, int x2, int y2){//检查碰撞
		int dstx = x2-x1;
		int dsty = y2-y1;
		
		return ((r*r) >= (dstx*dstx+dsty*dsty));
	}
	
	public void handleCollision(){//碰撞
		int i,j;
		for(i=0; i<numOfBullets; i++)//子弹数量
			for(j=0; j<numOfEnemy; j++)//敌军数量
			{
				if(bullets[i].isInScreen() && enemies[j].isAlive())
				{
					if(checkCollision(bullets[i].getCenterX(), bullets[i].getCenterY(),
					enemies[j].getCenterX(), enemies[j].getCenterY())){
						bullets[i].setOutScreen();
						enemies[j].setDead();
						score += 10;
						gui.updateScore(score);
						sound.explosionVoice();
					}
				}
			}
		
		for(i=0; i<numOfEnemy; i++)
		{
			if(enemies[i].isAlive()  && player.isAlive()){
				if(checkCollision(player.getCenterX(), player.getCenterY(),
				enemies[i].getCenterX(), enemies[i].getCenterY())){
					player.setDead();
					gui.updateLife(player.getLife());
					enemies[i].setDead();
					score+=10;
					gui.updateScore(score);
				}
			}
		}
		for(i=0; i<enemybullets.size(); i++)
		{
			if(enemybullets.get(i).isInScreen()  && player.isAlive()){
				if(checkCollision(player.getCenterX(), player.getCenterY(),
						enemybullets.get(i).getCenterX(), enemybullets.get(i).getCenterY())){
							player.setDead();
							gui.updateLife(player.getLife());
							enemybullets.get(i).setOutScreen();
							gui.updateScore(score);
						}
			}
		}
	}
	
	public void run(){
		while(true){
			try{
				repaint();
				handleCollision();
				updatePlayer();
				moveBack();
				shootBullets();
				shootEnemyBullets();//敌军子弹
				updateEnemies();
				Thread.sleep(200);
			}catch(Exception e){
			}
		}
	}
	
	public void updateEnemies(){
		int i;
		
		if(player.isAlive()){
			for(i=0; i<numOfEnemy; i++){
				if(enemies[i].isDead()){
					break;
				}
			}
			if(i<numOfEnemy){
				enemies[i].spawnEnemy();
				if(Math.random()<0.2)//My
				{
					enemybullets.add(new EnemyBullet(enemies[i]));//添加子弹			
				}
			}
		}
		
		for(i=0; i<numOfEnemy; i++)
			enemies[i].update();
	}
	
	public void shootBullets(){
		if(player.isAlive()){
			if(shootInterval == 2*200){
				int i ;
				for(i=0; i<numOfBullets; i++)
				{
					if(bullets[i].isOutScreen())
						break;
				}
				bullets[i].shoot(player.getX(), player.getY());
				shootInterval = 0;
			}else{
				shootInterval += 200;
			}
		}
		
		for(int i=0; i<numOfBullets; i++)
			bullets[i].update();
	}
	public void shootEnemyBullets(){//敌军发射子弹
		if(!enemybullets.isEmpty()){
			if(enemyshootInterval == 2*200){
				int i ;
				for(i=0; i<enemybullets.size(); i++)
				{

					if(enemybullets.get(i).isOutScreen())
						break;
				}
				if(i!=enemybullets.size())
				{
					if(enemybullets.get(i).enemie.isAlive())
						enemybullets.get(i).shoot(enemybullets.get(i).enemie.getCenterX(),enemybullets.get(i).enemie.getCenterY());
						enemyshootInterval = 0;
				}
			}else{
				enemyshootInterval += 200;
			}
		}
		
		for(int i=0; i<enemybullets.size(); i++)
			enemybullets.get(i).update();
	}
	
	private void moveBack(){
		if(back0Y == height){
			back0Y = -1*height;
		}else{
			back0Y += backStep;
		}
		
		if(back1Y == height){
			back1Y = -1*height;
		}else{
			back1Y += backStep;
		}
	}
	
	public void paint(Graphics g){
		g.drawImage(backScene0, 0, back0Y, this);
		g.drawImage(backScene1, 0, back1Y, this);
		
		player.drawPlane(g, this);
		
		for(int i= 0; i<numOfBullets; i++){
			bullets[i].drawBullet(g, this);
		}
		
		for(int i=0; i<numOfEnemy; i++){
			enemies[i].drawEnemy(g, this);
		}
		for (int i=0;i<enemybullets.size();i++)
		{
			enemybullets.get(i).drawBullet(g, this);
		}
		
		if(player.getLife() == 0){
			gui.drawGameover(g, this);
		}
	}
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_UP){
			player.upMove();
		}else if(key == KeyEvent.VK_DOWN){
			player.downMove();
		}else if(key == KeyEvent.VK_LEFT){
			player.leftMove();
		}else if(key == KeyEvent.VK_RIGHT){
			player.rightMove();
		}
	}
	
	public void keyTyped(KeyEvent e){
	}
	
	public void keyReleased(KeyEvent e){
	}
}