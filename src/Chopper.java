public class Chopper {

private double x = 1;
private double y = 1;


// Stores direction of our Chopper

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
	public void Left() {
		double x = getX()  * -1.3;
		setX(x);
	}
	public void Right() {
		double x = getX() * 1.3;
		setX(x);
	}
	public void Down() {
		double y = getY() * 1.3;
		setY(y);
	}
	public void Up() {
		double y = getY() * -1.3;
		setY(y);
	}
}