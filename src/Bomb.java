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



	public double getY() {
		return y;
	}

	public void setY(double velY) {
		 if(y > 0) {
			 y += velY;
		 }
		 else {
			 y = 1;
			 this.velY = 0;
		 }
		 if(y < height - 50)
		 {
			 y += velY;
		 }
		 else {
			 y  = height-50;
			 this.velY = 0;
			 setVelX(0);
			 this.velX = 0;
		 }
	}

	public double getX() {
		return x;
	}

	public void setX(double velX) {
		 if(x > 39) {
			 x += velX;
		 }
		 else {
			 x = 39;
			 this.velX = 1;
		 }
		 if(x < width - 39)
		 {
			 x += velX;
		 }
		 else {
			 x = width-39;
			 this.velX = -1;
		 }
	}
	
	public void tick() {
		setY(this.velY);
		setX(this.velX);
		
		
		if(velX > 0.3) {
			setVelX(-0.3);
		}
		else if(velX < -0.3){
			setVelX(0.3);
		}
		
		if(velY > 0.3) {
			setVelY(-0.3);
		}
		else {
			setVelY(0.3);
		}
		if(drop) {
			setVelY(0.5);
		}
		
		
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
	
	public void drop() {
		drop = true;
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