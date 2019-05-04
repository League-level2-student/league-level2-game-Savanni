
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
import javax.swing.JPanel;
import javax.swing.Timer;

public class Tetris extends JPanel implements ActionListener, KeyListener {

	JFrame frame = new JFrame();

	Timer t = new Timer(1000 / 60, this);

	grid grid = new grid();

	block activeBlock = new block();

	ArrayList<block> inactiveBlocks = new ArrayList<block>();

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

	// start of grid class

	class grid {
		int xoffset = 0;
		int yoffset = 0;

		public void draw(Graphics g) {
			g.setColor(Color.gray);
			g.fillRect(0, 0, 360, 500);

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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	public void paintComponent(Graphics g) {
		grid.draw(g);

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

// start of block class
class block {
	public int xPosition = 0;
	public int yPosition = 0;
	public Color blockcolor;

	int xMove = 20;
	int yMove = 20;

	private int floor = 500;
	private boolean active = true;

	private boolean moveRight = false;
	private boolean moveLeft = false;

	int speed = 1000;
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
		g.fillRect(xPosition - 4, yPosition, 20, 20);
	}

	public void update(ArrayList<block> blocks) {
		if (yPosition >= floor) {
			active = false;
		}

		if (checkBlock(xPosition, yPosition, blocks)) {
			yPosition -= yMove;
			active = false;
		}
		if (moveLeft && !checkBlock(xPosition - xMove, yPosition, blocks)){
			xPosition -= xMove;
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