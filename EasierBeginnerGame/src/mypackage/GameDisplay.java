package mypackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class GameDisplay extends JPanel implements Runnable, KeyListener{
	public static int width;
	public static int height;
	private Thread thread = null;
	private boolean running;
	private BufferedImage image;
	private Graphics2D g;
	private int fps = 30;
	private double averageFPS;
	
	private int x = 10;
	private int y = 10;
	long waitTime;
	public static Player p1;
	public static ArrayList<Bullet> bulletList;
	public ArrayList<Enemy> enemyList;
	
	private long enemyTimer;
	private int enemyDelay;
	
	public GameDisplay(int width,  int height) {
		super();
		this.width = width;
		this.height = height;
		this.setPreferredSize(new Dimension(width, height));
		this.setFocusable(true);
		//this.requestFocus();
		this.addKeyListener(this);
		enemyTimer = System.nanoTime();
		enemyDelay = 2000;
		
	}
	
	public void addNotify(){
		super.addNotify();
		if(thread == null){
			thread = new Thread(this);
			thread.start();
		}
	}
	
	
	public void run(){
		running = true;
		image = new BufferedImage(width,height, BufferedImage.TYPE_INT_RGB);
		//g = (Graphics2D) image.getGraphics();
		g = image.createGraphics();
		p1 = new Player();
		bulletList = new ArrayList<>();
		enemyList = new ArrayList<>();
		
		long startTime;
		int frameCount = 0;
		
		long URDTimeMillis;
		long totalTime = 0;
		long targetTime = 1000 / fps;
		int maxFrameCount = 30;
		
		while(running){
			startTime = System.nanoTime();
			
			update();
			render();
			draw();
			
			URDTimeMillis = (System.nanoTime() - startTime) / 1000000;
			waitTime = targetTime - URDTimeMillis;
			if(waitTime < 0) waitTime = Math.abs(waitTime);
			try{
				Thread.sleep(waitTime);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
			
			totalTime += System.nanoTime() - startTime;
			frameCount++;
			
			if(frameCount == maxFrameCount){
				averageFPS = 1000.0 / ((totalTime / frameCount) / 1000000);
				frameCount = 0;
				totalTime = 0;
			}
		}
	}

	public void update(){
		//player
		p1.update();
		
		//bullets
		for(int i = 0; i < bulletList.size(); i++){
			bulletList.get(i).update(bulletList);
		}
		
		//add enemies
		long elapsed = (System.nanoTime() - enemyTimer) / 1000000;
		if(elapsed > enemyDelay){
			enemyList.add(new Enemy());
			enemyTimer = System.nanoTime();
		}
		
		//update enemy position
		for(int i = 0; i < enemyList.size(); i++){
			enemyList.get(i).update(enemyList);
		}
		
		 System.out.println("Size: " + bulletList.size() + "   Last move: " + p1.getLast() + 
				(p1.getFiring() ? "   Player is firing" : "   Player is not firing" + " Enemy list size: " +
		          enemyList.size()));   
		
	}
	
	
	
	public void render(){
		
		g.setColor(Color.white);
		g.fillRect(0,0,width, height);
		
		p1.draw(g);
		for(int i = 0; i < bulletList.size(); i++){
			bulletList.get(i).draw(g);
			}
		for(int i = 0; i < enemyList.size(); i++){
			enemyList.get(i).draw(g);
		}
	}
	
	
	
	public void draw(){

		Graphics g2 =  this.getGraphics();
		g2.drawImage(image, 0, 0, null);
		g2.dispose();
	}
	
	
	public void keyPressed(KeyEvent e){
		int key = e.getKeyCode();
		
		
		if(key == KeyEvent.VK_LEFT){
			if(!p1.getMoved()){
				p1.setMoved();
			}
			p1.setDirection("left");
		}
		
		if(key == KeyEvent.VK_RIGHT ){
			if(!p1.getMoved()){
				p1.setMoved();
			}
			p1.setDirection("right");
		}
		
		if(key == KeyEvent.VK_UP ){
			if(!p1.getMoved()){
				p1.setMoved();
			}
			p1.setDirection("up");
		}
		
		if(key == KeyEvent.VK_DOWN) {
			if(!p1.getMoved()){
				p1.setMoved();
			}
			p1.setDirection("down");
		}
		if(key == KeyEvent.VK_SPACE){
			
					p1.setFiring(true);
			}
		}
	
	
	public void keyReleased(KeyEvent e){
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_LEFT){
			p1.removeDirection("left");
			
		}
		if(key == KeyEvent.VK_RIGHT){
			p1.removeDirection("right");
			
		}
		if(key == KeyEvent.VK_UP){
			p1.removeDirection("up");
			
		}
		if(key == KeyEvent.VK_DOWN){
			p1.removeDirection("down");
			
		}
		if(key == KeyEvent.VK_SPACE){
			p1.setFiring(false);
		}
		
	}
	
	public void keyTyped(KeyEvent e){
		
	}
	
	
	
}// end game display class
