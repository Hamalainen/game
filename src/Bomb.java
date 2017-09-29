import java.awt.Image;

import javax.swing.ImageIcon;

public class Bomb {

private double x = 39;
private double y = 1;
private double velX = 0;
private double velY = 0;
private double maxVel = 8;
private Image bomb;
private int width;
private int height;
private boolean right = false;
private boolean left = false;
private boolean up = false;
private boolean down = false;
private boolean drop = false;
// Stores direction of our Chopper

public Bomb(int height) {
	this.height = height;
}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}
	
	public void tick() {
		gravity();
		y += velY;
		
		if(y > height) {
			drop = false;
			setVelY(-getVelY());
			setVelX(-getVelX());
		}
		
		
		
		
	System.out.println("tick");	
		
	}
	public void setVelX(double velX) {	
		this.velX += velX;
	}
	
	public double getVelX() {
		return this.velX;
	}
	
	public void setVelY(double velY) {
		this.velY += velY;
	}
	
	public double getVelY()  {
		return this.velY;
	}
	
	public void gravity() {
			setVelY(0.5); 
	}
	
	public void drop(Boolean omegalul) {
		drop = omegalul;
	}
	
	public Boolean isDropped() {
		return drop;
	}
	
	public void setBomb() {
		bomb = loadImage("Images/bomb.png");
	}
	
	private Image loadImage(String path)
	{
		ImageIcon iih = new ImageIcon(path);
		return iih.getImage();
	}
	
	public Image getBomb()
	{
		return bomb;
	}
}