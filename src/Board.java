
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;



public class Board extends JPanel implements ActionListener {

// TODO: Implement a way for the player to win

// Holds height and width of the window
private final static int BOARDWIDTH = 1200;
private final static int BOARDHEIGHT = 980;


// The total amount of pixels the game could possibly have.
// We don't want less, because the game would end prematurely.
// We don't more because there would be no way to let the player win.


// Check to see if the game is running
//private boolean inGame = false;

// Timer used to record tick times
private Timer timer;

// Used to set game speed, the lower the #, the faster the Chopper travels
// which in turn
// makes the game harder.
private static int speed = 45;

// Instances of our Chopper & food so we can use their methods
private Chopper chopper = new Chopper(BOARDWIDTH, BOARDHEIGHT);
private Target target = new Target(BOARDWIDTH);
private Bomb bomb = new Bomb(BOARDHEIGHT);
//private boolean pause = false;
private Image background;
private Image explosion;
private int score;
private boolean inGame = false;
private boolean isPaused = false;



public int getBoardWidth() {
	return BOARDWIDTH;
}

public int getBoardHeight() {
	return BOARDHEIGHT;
}


public Board() { 
    setFocusable(true);
    setPreferredSize(new Dimension(BOARDWIDTH, BOARDHEIGHT));
    addKeyListener(new Keys());
    initializeGame();
   
}

// Used to paint our components to the screen
@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    draw(g);
}


// Draw our Chopper & Food (Called on repaint()).
void draw(Graphics g) {
	
	backScreen(g);
	gameScreen(g);
	
	
}


void initializeGame()
{	
	setBackgroundImage();
	setExplosion();
	chopper.setChopper();
	target.setTarget();
	bomb.setBomb();
	
	
    // set the timer to record our game's speed / make the game move
    timer = new Timer(speed, this);
    timer.start();
    
}

// if our Chopper is in the close proximity of the food..


// Used to check collisions with Chopper's self and board edges

void backScreen(Graphics g) {
	//g.drawImage(getBackgroundImage(), 0, 0, BOARDWIDTH, BOARDHEIGHT, null);
	drawBackGround(g);
	drawTarget(g);
	drawBomb(g);
}

void gameScreen(Graphics g) {
	//setFocusable(true);
	drawGameScreen(g);
	printScore(g);
	drawChopper(g);
}


void drawBackGround(Graphics g) {
	g.drawImage(getBackgroundImage(), 0, 0, BOARDWIDTH, BOARDHEIGHT, null);
}

void drawTarget(Graphics g) {
	if(target.getVelX() >= 0) {
		g.drawImage(target.getTarget(), (int)target.getX()+37, BOARDHEIGHT -50, -75, 50, null);
	}
	else if(target.getVelX() < 0) {
		g.drawImage(target.getTarget(), (int)target.getX()-37, BOARDHEIGHT -50, 75, 50, null);
	}
}

void drawBomb(Graphics g) {
	if(bomb.isDropped()) {
		g.drawImage(bomb.getBomb(), (int)(bomb.getX())+7, (int)(bomb.getY())+46 , 15, 25, null);

		if(bomb.getY() >= BOARDHEIGHT-100) {
				g.drawImage(getExplosion(), (int)(bomb.getX()), BOARDHEIGHT - size(50) , size(50), size(50), null);
		}
	}
}

void drawChopper(Graphics g) {
	if(chopper.getVelX() >= 0) {
		g.drawImage(chopper.getChopper(), (int)(chopper.getX()) - 37, (int)(chopper.getY()), 75, 50, null);
		
	}
	else if(chopper.getVelX() < 0){
		g.drawImage(chopper.getChopper(), (int)(chopper.getX()) + 37, (int)(chopper.getY()), -75, 50, null);
		
	}
}

void drawGameScreen(Graphics g) {
	final int ALPHA = 175; // how much see-thru. 0 to 255
    final Color GP_BG = new Color(75, 75, 75, 0);
    g.setColor(GP_BG);
	g.drawRect(0,0,BOARDWIDTH,BOARDHEIGHT);
	g.fillRect(0, 0, BOARDWIDTH, BOARDHEIGHT);
}

void pauseGame(Graphics g) {

		//Transparent rectangle with paused text
		final int ALPHA = 175; // how much see-thru. 0 to 255
	    final Color GP_BG = new Color(75, 75, 75, ALPHA);
	    String text = "PAUSED";
	    String fontName = "serif";
	    Font font = new Font(fontName, Font.BOLD, 25);
	    g.setFont(font); 
	    FontMetrics metrics = g.getFontMetrics(font);
	    int x = BOARDWIDTH /2 - metrics.stringWidth(text) /2;
	    g.setColor(GP_BG);
		g.drawRect(0,0,BOARDWIDTH,BOARDHEIGHT);
		g.fillRect(0, 0, BOARDWIDTH, BOARDHEIGHT);
		g.setColor(Color.RED);
		g.drawString(text, x, BOARDHEIGHT/2);
}

void printScore(Graphics g) {
	String text = "Score: " + getScore().toString();
    String fontName = "serif";
    Font font = new Font(fontName, Font.BOLD, 25);
    g.setFont(font); 
    FontMetrics metrics = g.getFontMetrics(font);
    int x = 1190 - metrics.stringWidth(text);
	g.setColor(Color.BLACK);
	g.drawString(text, x, 30);
	
}



// Run constantly as long as we're in game.
@Override
public void actionPerformed(ActionEvent e) {
    if(inGame) {
    	chopper.tick();
     	chopper.gravity();
     	target.tick();
     	if(bomb.isDropped()) {
     		bomb.tick();
     	}
    	repaint();
    }
}


private void scrollWindow() {
	if(chopper.getX() > BOARDWIDTH/2 +10) {
		
	}
}

private class Keys extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
        	chopper.setLeft(true);
        }

        if (key == KeyEvent.VK_RIGHT) {
        	chopper.setRight(true);
        }

        if (key == KeyEvent.VK_UP) {
        	chopper.setUp(true);
        }

        if (key == KeyEvent.VK_DOWN) {
        	chopper.setDown(true);
            	
        }
        if (key == KeyEvent.VK_ENTER) {
        	isPaused = false;
        	inGame = true;
        	System.out.println(inGame);
        }
        if(key == KeyEvent.VK_SPACE && !bomb.isDropped()) {
    		bomb.setX(chopper.getX());
    		bomb.setY(chopper.getY());
    		bomb.setVelY(chopper.getVelY());
        	bomb.drop(true);
        }
        if (key == KeyEvent.VK_P) {
        	
        	if(isPaused == false) {
        		isPaused = true;
        		inGame = false;
        	}
        	else {
        		isPaused = false;
            	inGame = true;
        	}
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
        	chopper.setLeft(false);
        }

        if (key == KeyEvent.VK_RIGHT) {
        	chopper.setRight(false);
        }

        if (key == KeyEvent.VK_UP) {
        	chopper.setUp(false);
        }

        if (key == KeyEvent.VK_DOWN) {
        	chopper.setDown(false);
            	
        }
    }
}

private void setScore() {
	score += 1;
}

private Integer getScore() {
	return score;
}


private Boolean hit() {
	if(bomb.getY() >= BOARDHEIGHT-100  && (bomb.getX() >= target.getX() - 37 && bomb.getX() <= target.getX() + 37)) {
		setScore();
		return true;
	} 
	return false;
}

private Integer size(int small) {
	if(hit()) {
		return small *2;
	}
	return small;
}

private void setBackgroundImage() {
	background = loadImage("Images/Background.png");
}
private Image loadImage(String path)
{
	ImageIcon iih = new ImageIcon(path);
	return iih.getImage();
}
private Image getBackgroundImage() {
	return background;
}

private void setExplosion() {
	explosion = loadImage("Images/Explosion.png");
}

private Image getExplosion() {
	return explosion;
}
}