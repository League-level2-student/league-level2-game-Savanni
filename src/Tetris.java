
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Tetris extends JPanel implements ActionListener, KeyListener {

	JFrame frame = new JFrame();
	
	Timer t = new Timer(1000 / 60, this);

	
	
	public void startup() {
		frame.setPreferredSize(new Dimension(500, 800));
		frame.add(this);
		frame.setVisible(true);
		frame.pack();
		frame.addKeyListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tetris tetris = new Tetris();
		tetris.startup();
	}










	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}






	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}






	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}






	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
//end of tetris class







class grid   {
	
	public void draw(Graphics g) {
		
		
	}
	
	
	
	
	
	
	
}