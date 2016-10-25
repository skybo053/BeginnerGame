package mypackage;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class ExplosionIMG {

	private double x;
	private double y;
	private ImageIcon img;
	private long timer;
	
	public ExplosionIMG(double x, double y) {
		this.x = x;
		this.y = y;
		timer = System.nanoTime();
		img = new ImageIcon(this.getClass().getResource("/Enemy_Explode.png"));
		
	}
	
	public void draw(Graphics g){
		g.drawImage(img.getImage(), (int)x, (int)y, null);
	}
	
	public long getTimer(){
		return timer;
	}

}
