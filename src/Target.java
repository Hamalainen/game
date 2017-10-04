import java.awt.Image;

import javax.swing.ImageIcon;

public class Target {





private double x = 39;
private double y = 1;
private double velX = 0;
private Image target;
private int width;

// Stores direction of our Chopper

public Target(int width)
{
	this.width = width;
}

	public double getX() {
		return x;
	}

	public void setX(double velX) {
		this.x += velX;
	}
	
	public void tick() {
		 setX(this.velX);
		 if(x < 10024 - 39) {
			 setVelX(4);
		 }
		 else {
			 setVelX(-1);
		 }
		 if(x > 39)
		 {
			 setVelX(-4);
		 }
		 else setVelX(1);
		 
	}
	
	public void setVelX(double delta) {
		velX += delta; 
	}
	
	public double getVelX() {
		return this.velX;
	}

	
	public void setTarget() {
		target = loadImage("Images/Target1.png");
	}
	private Image loadImage(String path)
	{
		ImageIcon iih = new ImageIcon(path);
		return iih.getImage();
	}
	public Image getTarget()
	{
		return target;
	}
	
}