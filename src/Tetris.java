
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Tetris extends JPanel implements ActionListener, KeyListener {

	JFrame frame = new JFrame();

	Timer t = new Timer(1000 / 60, this);

	grid grid = new grid();

	block activeBlock = new block();

	ArrayList<block> inactiveBlocks = new ArrayList<block>();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Tetris().startup();
	}

	public void startup() {
		frame.setPreferredSize(new Dimension(500, 800));
		frame.add(this);
		frame.setVisible(true);
		frame.pack();
		frame.addKeyListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		activeBlock = new block();
		JOptionPane.showMessageDialog(null, "Your goal is to clear lines and get as many points as you can!");
		t.start();
	}

	int x = 0;

	public void paintComponent(Graphics g) {
		for (block b : inactiveBlocks) {
			b.draw(g);
		}
		grid.draw(g);
		activeBlock.draw(g);
		

	}
	public void actionPerformed(ActionEvent e) {
		activeBlock.update(inactiveBlocks);
		if (!activeBlock.active) {
			inactiveBlocks.add(activeBlock);
			activeBlock = new block();
		}
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == (KeyEvent.VK_ESCAPE)) {
			t.stop();
			System.exit(0);
		}
		if (e.getKeyCode() == (KeyEvent.VK_LEFT)) {
			activeBlock.moveLeft();
		}
		if (e.getKeyCode() == (KeyEvent.VK_RIGHT)) {
			activeBlock.moveRight();
		}
		if (e.getKeyCode() == (KeyEvent.VK_DOWN)) {
			activeBlock.moveDown();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}

// start of grid class

class grid {
	int xoffset = 0;
	int yoffset = 0;

	public void draw(Graphics g) {


		g.setColor(Color.BLACK);
		for (int i = 0; i < 19; i++) {
			g.fillRect(0, 500, yoffset, 1);
			yoffset += 20;
			
		}
		yoffset = 0;

		for (int i = 0; i < 10; i++) {
			g.fillRect(xoffset, 0, 1, 500);
			xoffset += 40;
		}
		xoffset = 0;

	}
}

// start of block class
class block {
	public int xPosition = 0;
	public int yPosition = 0;
	public Color blockcolor;

	int xMove = 20;
	int yMove = 20;

	private int floor = 500;
	boolean active = true;

	private boolean moveRight = false;
	private boolean moveLeft = false;

	int speed = 2000;
	long dropTime = -1;

	public block() {
		xPosition = 255;
		yPosition = 0;

		switch (new Random().nextInt(4)) {
		case 0:
			blockcolor = Color.BLUE;
			break;
		case 1:
			blockcolor = Color.red;
			break;
		case 3:
			blockcolor = Color.green;
			break;
		case 4:
			blockcolor = Color.yellow;
			break;

		}

	}

	public void moveLeft() {
		moveLeft = true;
	}

	public void moveRight() {
		moveRight = true;
	}

	public void moveDown() {
		yPosition += yMove;
	}

	public void draw(Graphics g) {
		g.setColor(blockcolor);
		// Square
		if (blockcolor == Color.BLUE) {
			g.fillRect(xPosition - 4, yPosition, 40, 40);
		}
		// Line
		if (blockcolor == Color.red) {
			g.fillRect(xPosition - 4, yPosition, 20, 80);
		}
		// L-Piece
		if (blockcolor == Color.green) {
			g.fillRect(xPosition - 4, yPosition + 20, 20, 20);
			g.fillRect(xPosition - 4, yPosition, 60, 20);
		// Z-Piece WIP
		}
		

	}

	public void update(ArrayList<block> blocks) {
		if (yPosition == floor - 40) {
			active = false;
		}

		if (checkBlock(xPosition, yPosition, blocks)) {
			yPosition -= yMove;
			active = false;
		}
		if (moveLeft && !checkBlock(xPosition - xMove, yPosition, blocks)) {
			xPosition -= xMove;
		}
		if (moveRight && !checkBlock(xPosition + xMove, yPosition, blocks)) {

		}
		moveRight = false;
		moveLeft = false;
		if (active) {
			if (System.currentTimeMillis() - dropTime >= speed) {
				yPosition += yMove;
				dropTime = System.currentTimeMillis();
			}
			
		}
		else {
			speed = 0;
		}
		
	}

	private boolean checkBlock(int x, int y, ArrayList<block> blocks) {
		for (block b : blocks) {
			if (x == b.xPosition && y == b.yPosition) {
				return true;
			}
		}
		return false;
	}

}