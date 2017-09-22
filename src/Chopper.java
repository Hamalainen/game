import java.awt.Image;

import javax.swing.ImageIcon;

public class Chopper {

private double x = 39;
private double y = 1;
private double velX = 0;
private double velY = 0;
private double maxVel = 5;
private Image chopper;
private int width;
private int height;
// Stores direction of our Chopper

public Chopper(int width, int height)
{
	this.width = width;
	this.height = height;
}

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
			setVelY(0.2); 
	}
	
	public void Left() {
		this.x = getX()  -3;
		setX(x);
	}
	public void Right() {
		this.x = getX() +3;
		setX(x);
	}
	public void Down() {
		this.y = getY() +3;
		setY(y);
	}
	public void Up() {
		this.y = getY() -3;
		setY(y);
	}
	public void setChopper() {
		chopper = loadImage("Images/Chopper.png");
	}
	private Image loadImage(String path)
	{
		ImageIcon iih = new ImageIcon(path);
		return iih.getImage();
	}
	public Image getChopper()
	{
		return chopper;
	}
	
}