import java.applet.AudioClip;
import java.net.URL;
import javax.swing.JApplet;

public class Sound{
	
	private AudioClip shoot;
	private AudioClip explosion;
	private Player player = null;
	
	public Sound(Player player){
		this.player = player;
		
		String fileName = "music/shoot.wav";
		URL url = null;
		try{
			url = new URL("file:"+fileName);
			shoot = JApplet.newAudioClip(url);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		fileName = "music/explosion.wav";
		try{
			url = new URL("file:"+fileName);
			explosion = JApplet.newAudioClip(url);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void shootVoice(){//…‰ª˜“Ù–ß
		new Thread( new Runnable(){
			public void run(){
				while(player.isAlive()){
					shoot.play();
					try{
						Thread.sleep(1100);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	public void explosionVoice(){
		new Thread( new Runnable(){
			public void run(){
				explosion.play();
			}
		}).start();
		
	}
}