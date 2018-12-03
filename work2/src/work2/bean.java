package work2;
import javax.swing.*;
import java.awt.*;

public class bean {
	
	JLabel jbl = null;
	char pstr ;
	   
	   public void setShow(JLabel show){
	      this.jbl = show;
	   }
	   public void setChar(char pstr){
	      this.pstr = pstr;
	   }
	   public JLabel getLabel(){
	      return this.jbl;
	   }
	   public char getChar(){
	      return pstr;
	   }

}
