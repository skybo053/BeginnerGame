package mypackage;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Bullet {

	private int r;
	private int speed;
	private String direction;
	private int pCenterX;
	private int pCenterY;
	
	public Bullet() {
		r = 5;
		speed = 10;
		pCenterX = GameDisplay.p1.getX() + GameDisplay.p1.getR() - r;
		pCenterY = GameDisplay.p1.getY() + GameDisplay.p1.getR() - r;
		//direction = GameDisplay.p1.getLast();
		direction = GameDisplay.p1.getLast();
	}
	 
	public int getY(){return pCenterY;}
	public int getX(){return pCenterX;}
	
	public void update(List<Bullet> list){
		if(direction == "up" || direction == "start")
		pCenterY -= speed;
		if(direction == "left")
			pCenterX -= speed;
		if(direction == "right")
			pCenterX += speed;
		if(direction == "down")
			pCenterY += speed;
		
		for(int i = 0; i < list.size(); i++){
		if(list.get(i).getY() <= 0 ||
				   list.get(i).getY() >= GameDisplay.height ||
				   list.get(i).getX() <= 0 ||
				   list.get(i).getX() >= GameDisplay.width) {
					list.remove(i);
				}
		
		}
	}
	
	
	public void draw(Graphics2D g){
		g.setColor(Color.green);
		g.fillOval(pCenterX, pCenterY, 2*r, 2*r);

	}

}
