package work2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class game1 extends JFrame{
	JPanel contentPane;
	JPanel jpanel1 = new JPanel();
	int score;
	int flag=0;
	int speed=2;
	public game1(){
		gameInit();
	}
	
	private void gameInit(){
		score=0;
		contentPane = (JPanel)getContentPane();
		contentPane.setLayout(null);
		setSize(new Dimension(500, 500));
		setTitle("alphabet game");
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jpanel1.setBounds(new Rectangle(4, 4, 400, 400));
		contentPane.add(jpanel1);
		Runnable myRunnable = new myThread(this); // 创建一个Runnable实现类的对象
		
		
		for(int i=0;i<10;i++)
		{
			new Thread(myRunnable).start(); 
			try{
				Thread.sleep((int)(1+Math.random())*3000);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
			
		}
		while(flag>=0) 
		{
			try{
				Thread.sleep(1000);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
			System.out.println(flag);
			if(flag>=10)
			{
				int res=JOptionPane.showConfirmDialog(null, "最终成绩"+score, "是否继续", JOptionPane.YES_NO_OPTION);
				break;
			}
		}
		
	}
	
	
	public static void main(String[] args){
		new game1();
		
	}

}
