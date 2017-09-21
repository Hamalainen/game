import java.awt.Image;

import javax.swing.ImageIcon;

public class Chopper {

private int x = 1;
private int y = 1;
private double velX = 0;
private double velY = 0;
private int maxX = 1000;
private int maxY = 980;
private Image chopper;
// Stores direction of our Chopper

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public void tick() {
		x += velX;
		y += velY;
		System.out.println("tick");
	}
	public void setVelX(double velX) {
		this.velX = velX;
	}
	
	public void setVelY(double velY) {
		this.velY = velY;
	}
	
	
	
	public void Left() {
		int x = getX()  -3;
		setX(x);
	}
	public void Right() {
		int x = getX() +3;
		setX(x);
	}
	public void Down() {
		int y = getY() +3;
		setY(y);
	}
	public void Up() {
		int y = getY() -3;
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