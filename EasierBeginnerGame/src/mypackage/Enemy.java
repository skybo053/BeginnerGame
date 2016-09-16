package mypackage;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;
import java.util.Random;

public class Enemy {

	private int x, y;
	private int r;
	private int speed;
	
	private Random rand;
	private int startPos;
	
	private int[] speedArray;
	private int[] xArray;
	private int[] yArray;
	
	public Enemy() {
		rand = new Random();
		initArrays();
		
		
		startPos = rand.nextInt(4);   //random number 0,1,2,3 - to determine starting side of game board
		speed = speedArray[rand.nextInt(speedArray.length)];
		
		r = 15;
		
		switch(startPos){
		
		case 0: //comes from top
			x = xArray[rand.nextInt(xArray.length)];
			y = -r * 2;
			break;
		
		case 1: // comes from right
			x = GameDisplay.width;
			y = yArray[rand.nextInt(yArray.length)];
			break;
				
		case 2: // comes from bottom
			x = xArray[rand.nextInt(xArray.length)];
			y = GameDisplay.height;
			break;
			
		case 3: // comes from left
			x = -r * 2;
			y = yArray[rand.nextInt(yArray.length)];
			break; 
		}
		
		
	}
	
	public int getX(){ return x; }
	public int getY(){ return y; }
	
	
	
	public void update(List<Enemy> list){
		switch(startPos){
		
		case 0: //moving down
		y += speed;
		break;
		
		case 1: //moving left
			x += -speed;
			break;
			
		case 2: //moving up
			y += -speed;
			break;
			
		case 3: //moving right
			x += speed;
			break;
		}
		
		//checking if off game panel
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).getX() <= -r * 2 - 5 ||
			   list.get(i).getX() >= GameDisplay.width + (r * 2 + 5) ||
			   list.get(i).getY() <= -r * 2 - 5 ||
			   list.get(i).getY() >= GameDisplay.height + (r * 2 + 5)){
				   list.remove(i);
			   }
		}
	}//end update method
	
	
	
	public void draw(Graphics2D g){
		g.setColor(Color.red);
		g.fillOval(x, y, r*2, r*2);
	}


	private void initArrays(){
		//width 800 height 700
		
		speedArray = new int[]{1,2,3,4,5,6,7,8};
		xArray = new int[]{10, 40, 80, 100, 170, 250, 300,340,380,435,485,550,600,645,690, 725, 755, 766};
		yArray = new int[]{10, 40, 80, 100, 140, 170, 200, 250, 300,340,380,435,485,550,600,645,680};
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}// end of class enemy
