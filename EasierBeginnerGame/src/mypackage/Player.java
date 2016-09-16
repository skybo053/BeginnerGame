package mypackage;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class Player {

	private int x, y, r;
	private int speed;
	private int lives;
	private Set<String> mySet;
	private boolean moved;
	String move = null;
	
	private boolean firing;
	private long firingTimer;
	private long firingDelay;
	
	
	public Player() {
		x = GameDisplay.width / 2;
		y = GameDisplay.height / 2;
		r = 12;
		
		firing = false;
		firingTimer = System.nanoTime();
		firingDelay = 100;
		
		speed = 5;
		lives = 10;
		
		mySet = new LinkedHashSet<>(4);
		mySet.add("start");
		
		
		
	}
	
	public void setFiring(boolean b){
		firing = b;
	}
	public boolean getFiring(){return firing;}
	
	public void setDirection(String move) {mySet.add(move); }
	
	public void removeDirection(String move){ mySet.remove(move); }
	
	public int getX(){return x;}
	
	public int getY(){return y;}
	
	public int getR(){return r;}
	
	public void setMoved(){
		moved = true;
		mySet.remove("start");
		}
	
	public boolean getMoved(){ return moved;};
	
	public void update(){
		
		//firing check
		
		if(firing){
		   long elapsed = (System.nanoTime() - firingTimer) / 1000000;
		   if(elapsed > firingDelay){
		   GameDisplay.bulletList.add(new Bullet());
		   firingTimer = System.nanoTime();
		   }
		}
				
		
		if(mySet.isEmpty())
		   return;
		
		//////////direction checks
		if(getLast().equals("left")){
			x += -speed;
		}
		if(getLast().equals("right")){
			x += speed;
		}
		
		if(getLast().equals("up")){
			y += -speed;
		}
		
		if(getLast().equals("down")){
			y += speed;
		}
		
		
		//////////////bounds checks
		if(x <= 0)
			x = 0;
		
		if(y <= 0)
			y = 0;
		
		if(x >= GameDisplay.width - 30){
			x = GameDisplay.width - 30;
		}
		if(y >= GameDisplay.height - 52){
			y = GameDisplay.height - 52;
		}
		
		
		
}
	
	public void draw(Graphics2D g){
		g.setColor(Color.black);
		g.fillOval(x, y, r*2, r*2);
		
	}
	
	public String getLast(){
		
		Iterator<String> it = mySet.iterator();
		
		while(it.hasNext()){
			move = it.next();
		}
		
		return move;
	}
}
