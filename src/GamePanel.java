import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;


public class GamePanel extends JPanel{
	public int headX,headY;
	GamePanel(){
		this.setPreferredSize(new Dimension(20,20));
		this.setBackground(Color.cyan);
		this.setOpaque(true);
	}
	
	
}
