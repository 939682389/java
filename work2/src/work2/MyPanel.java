package work2;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MyPanel extends JPanel implements KeyListener{
	bean b;
	game1 game1;
	public MyPanel(game1 game1,bean b)
	{
		this.b=b;
		this.game1=game1;
	}
	public MyPanel()
	{
	}	
	public void keyTyped(KeyEvent e) {

	}
	public void keyPressed(KeyEvent e) {
		System.out.println(e.getKeyCode());
		if (e.getKeyCode()==b.getChar()){
	        b.getLabel().setText(null);//²»ÏÔÊ¾×Ö·û
	        game1.score+=1;
	        game1.removeKeyListener(this);//É¾³ý¼àÌý
	        game1.flag++;
		} 
		else if(e.getKeyCode()==33)
		{
			game1.speed++;
		}
		else if(e.getKeyCode()==34)
		{
			game1.speed--;
		}
		
	}
	
    public void keyReleased(KeyEvent e) {

    }
}
