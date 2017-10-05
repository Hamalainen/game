import java.awt.Image;

import javax.swing.ImageIcon;

public class Chopper {

private double x = 39;
private double y = 1;
private double velX = 0;
private double velY = 0;
private double maxVel = 25;
private Image chopper;
private int gameScreenWidth;
private int gameScreenHeight;
private int backgroundWidth;
private int backgroundHeight;
private boolean right = false;
private boolean left = false;
private boolean up = false;
private boolean down = false;
// Stores direction of our Chopper

public Chopper(int gameScreenWidth, int gameScreengameScreenHeight, int backgroundWidth, int backgroundgameScreenHeight)
{
	this.gameScreenWidth = gameScreenWidth;
	this.gameScreenHeight = gameScreengameScreenHeight;
	this.backgroundWidth = backgroundWidth;
	this.backgroundHeight = backgroundgameScreenHeight;
}

	public void setRight(boolean delta) {
		right = delta;
	}
	public void setLeft(boolean delta) {
		left = delta;
	}
	public void setUp(boolean delta) {
		up = delta;
	}
	public void setDown(boolean delta) {
		down = delta;
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
		 if(y < gameScreenHeight - 50)
		 {
			 y += velY;
		 }
		 else {
			 y  = gameScreenHeight-50;
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
		 
		 if(x < gameScreenWidth - 39)
		 {
			 x += velX;
		 }
		 else {
			 x = gameScreenWidth - 39;
			 this.velX = -1;
		 }
	}
	
	public void tick() {
		setY(this.velY);
		setX(this.velX);
		if(right && this.velX < maxVel) {
			setVelX(0.8);
		}
		if(left && this.velX < maxVel) {
			setVelX(-0.8);
		}
		if(up && this.velY < maxVel) {
			setVelY(-1.2);
		}
		if(down && this.velY < maxVel) {
			setVelY(0.8);
		}
		
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
	}
	public void setVelX(double velX) {	
		this.velX += velX;
		System.out.println(this.velX);
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
	public void setXstatic(int x) {
		this.x = x;
	}
	public boolean getRight() {
		return right;
	}
	public boolean getLeft() {
		return left;
	}
	
}