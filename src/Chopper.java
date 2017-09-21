import java.awt.Image;

import javax.swing.ImageIcon;

public class Chopper {

private double x = 1;
private double y = 1;
private double velX = 0;
private double velY = 0;
private int maxX = 1000;
private int maxY = 980;
private Image chopper;
// Stores direction of our Chopper

	public double getY() {
		return y;
	}

	public void setY(double velY) {
		this.y += velY;
	}

	public double getX() {
		return x;
	}

	public void setX(double velX) {
		this.x += velX;
	}
	
	public void tick() {
		setY(this.velY);
		setX(this.velX);
		
		//x += velX;
		//y += velY;
		
	}
	public void setVelX(double velX) {
		this.velX += velX;
	}
	
	public void setVelY(double velY) {
		this.velY += velY;
	}
	
	public void gravity() {
		setVelY(0.1); 
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