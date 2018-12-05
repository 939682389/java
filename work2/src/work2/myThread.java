package work2;
import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class myThread implements Runnable{
	game1 game1;
	Vector number = new Vector();

	public myThread(game1 game1)
	{
		this.game1=game1;
	}
	public void run(){
		
		JLabel show = new JLabel();
		show.setFont(new java.awt.Font("宋体",Font.PLAIN, 33));
		char pst=(char)(1+Math.random()*(25)+65);
		show.setText(pst+"");
		bean bean = new bean();
	    bean.setShow(show);
	    bean.setChar(pst);
	    number.add(bean);
	    game1.jpanel1.add(show);
		boolean fo = true;
		MyPanel panel= new MyPanel(game1,bean);
		game1.add(panel);
		game1.addKeyListener(panel);
		int x=(int)(1+Math.random()*(350));
		int y= 0; 
		while(fo)
		{
			y+=game1.speed;
			show.setBounds(x, y, 33, 33); //可见JLabel每次移动的距离为2；
			try{
				Thread.sleep(100); //每隔100ms的时间；
			}catch(InterruptedException e){
				e.printStackTrace();
			}
	
			if(y >= 350){  //落到一定高度，停止；
				game1.removeKeyListener(panel);
				game1.flag++;
				break;
			}
		}
		
		
	}
}
