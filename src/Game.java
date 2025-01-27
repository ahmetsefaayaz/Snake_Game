import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Game implements ActionListener,KeyListener{
	
	ArrayList <GamePanel>tail;
	boolean gameover = false;
	int fruitX,fruitY;
	int pixel = 20;
	private int score = -1;
	private GamePanel panel;
	private GameWindow window;
	private GamePanel snakeHead;
	private GamePanel FRUIT = new GamePanel();
	private Timer gameTimer;
	private char direction = ' ';
	Game(){
		tail = new ArrayList<GamePanel>();
		window = new GameWindow();
		panel = new GamePanel();
		panel.setBackground(Color.black);
		panel.setOpaque(true);
		panel.setPreferredSize(new Dimension(500,500));
		
		panel.setLayout(null);
		
		snakeHead = new GamePanel();
		snakeHead.headX = 12;
		snakeHead.headY = 12;
		snakeHead.setBackground(Color.green);
		snakeHead.setOpaque(true);
		snakeHead.setBounds(snakeHead.headX * pixel,snakeHead.headY*pixel,pixel,pixel);
		snakeHead.setLocation(snakeHead.headX * pixel, snakeHead.headY * pixel);
		tail.add(snakeHead);
		
		
		
		window.addKeyListener(this);
		
		window.add(panel);
		panel.add(snakeHead);
		
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		setFruit(FRUIT);
		startGameLoop();
		
	} 
	private void startGameLoop() {
		gameTimer = new javax.swing.Timer(50, this);
		gameTimer.start();
			
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		for(int i= 1;i<tail.size();i++) {
			if(tail.get(i).headX == tail.get(0).headX &&tail.get(i).headY == tail.get(0).headY) {
				gameover = true;
				break;
			}
		}
		if(gameover) {gameTimer.stop(); setGameOver();}
		
		for(int i = tail.size() - 1;i > 0;i--) {
			tail.get(i).headX = tail.get(i - 1).headX;
			tail.get(i).headY = tail.get(i - 1).headY;
			tail.get(i).setLocation(tail.get(i).headX * pixel, tail.get(i).headY * pixel);
		}
		
		updateGame();
		panel.repaint();
		
		// fruit eaten.
		if(fruitX == snakeHead.headX && fruitY == snakeHead.headY) {
			setFruit(FRUIT);
			
		}
	}
	void updateGame() {
		switch(direction) {
		case 'w' :
			snakeHead.headY -= 1;
			if(snakeHead.headY == -1) snakeHead.headY += (500 / pixel);
			snakeHead.setLocation(snakeHead.headX * pixel, snakeHead.headY * pixel );
			break;
		case 'a':
			snakeHead.headX -= 1;
			if(snakeHead.headX == -1) snakeHead.headX += (500 / pixel);
			snakeHead.setLocation(snakeHead.headX * pixel, snakeHead.headY* pixel);
			break;
		case 's':
			snakeHead.headY = (snakeHead.headY+1) % (500 / pixel);
			snakeHead.setLocation(snakeHead.headX* pixel , snakeHead.headY* pixel );
			break;
		case 'd':
			snakeHead.headX = (snakeHead.headX + 1) % (500 / pixel);
			snakeHead.setLocation(snakeHead.headX* pixel, snakeHead.headY* pixel);
			break;
		
		
		}
		
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		switch(e.getKeyChar()) {
		case 'w':
			if(direction != 's') direction = 'w';
			break;
		case 'a':
			if(direction != 'd') direction = 'a';
			break;
		case 's':
			if(direction != 'w') direction = 's';
			break;
		case 'd':
			if(direction != 'a') direction = 'd';
			break;
		}
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void setFruit(GamePanel FRUIT) {
		Random random = new Random();
		fruitX = random.nextInt(500 / pixel);
		fruitY = random.nextInt(500 / pixel);
		FRUIT.setBackground(Color.pink);
		FRUIT.setOpaque(true);
		FRUIT.setBounds(fruitX * pixel ,fruitY * pixel,pixel,pixel);
		score++;
		panel.add(FRUIT);
		addTail();
	}
	public void addTail() {
		int lastX = 0,lastY = 0;
		switch(direction) {
		case 'w':
			lastX = tail.get(tail.size() - 1).headX ;
			lastY = tail.get(tail.size() - 1).headY + 1;
			tail.add(new GamePanel());
			tail.get(tail.size() - 1).headX = lastX;
			tail.get(tail.size() - 1).headY = lastY;
			tail.get(tail.size() - 1).setBounds(tail.get(tail.size() - 1).headX*pixel,tail.get(tail.size() - 1).headY*pixel,pixel,pixel);
			panel.add(tail.get(tail.size() - 1));
			
			
			break;
		case 'a':
			lastX = tail.get(tail.size() - 1).headX + 1;
			lastY = tail.get(tail.size() - 1).headY ;
			tail.add(new GamePanel());
			tail.get(tail.size() - 1).headX = lastX;
			tail.get(tail.size() - 1).headY = lastY;
			tail.get(tail.size() - 1).setBounds(tail.get(tail.size() - 1).headX*pixel,tail.get(tail.size() - 1).headY*pixel,pixel,pixel);
			panel.add(tail.get(tail.size() - 1));
			
			
			break;
		case 's':
			lastX = tail.get(tail.size() - 1).headX ;
			lastY = tail.get(tail.size() - 1).headY - 1;
			tail.add(new GamePanel());
			tail.get(tail.size() - 1).headX = lastX;
			tail.get(tail.size() - 1).headY = lastY;
			tail.get(tail.size() - 1).setBounds(tail.get(tail.size() - 1).headX*pixel,tail.get(tail.size() - 1).headY*pixel,pixel,pixel);
			panel.add(tail.get(tail.size() - 1));
			
			
			break;
		case 'd':
			lastX = tail.get(tail.size() - 1).headX - 1;
			lastY = tail.get(tail.size() - 1).headY ;
			tail.add(new GamePanel());
			tail.get(tail.size() - 1).headX = lastX;
			tail.get(tail.size() - 1).headY = lastY;
			tail.get(tail.size() - 1).setBounds(tail.get(tail.size() - 1).headX*pixel,tail.get(tail.size() - 1).headY*pixel,pixel,pixel);
			panel.add(tail.get(tail.size() - 1));
			
			
			break;
			
		}
		
		
		
	}
	public void setGameOver() {
		window.dispose();
		GameWindow ScoreWindow = new GameWindow();
		ScoreWindow.setSize(500,500);
		ScoreWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ScoreWindow.setResizable(false);
		JLabel scoreLabel = new JLabel();
		scoreLabel.setText("Your score: " + score);
		scoreLabel.setBackground(Color.GREEN);
		scoreLabel.setOpaque(true);
		scoreLabel.setFont(new Font("consolas",Font.PLAIN,35));
		scoreLabel.setVerticalAlignment(JLabel.CENTER);
		scoreLabel.setHorizontalAlignment(JLabel.CENTER);
		ScoreWindow.add(scoreLabel);
		ScoreWindow.setLocationRelativeTo(null);
		ScoreWindow.setVisible(true);
		
	}
	
	
}
