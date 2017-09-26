    import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;
import javax.swing.WindowConstants;



public class Board extends JPanel implements ActionListener {

// TODO: Implement a way for the player to win

// Holds height and width of the window
private final static int BOARDWIDTH = 1000;
private final static int BOARDHEIGHT = 980;

// Used to represent pixel size of food & our Chopper's joints
private final static int PIXELSIZE = 25;

// The total amount of pixels the game could possibly have.
// We don't want less, because the game would end prematurely.
// We don't more because there would be no way to let the player win.

private final static int TOTALPIXELS = (BOARDWIDTH * BOARDHEIGHT)
        / (PIXELSIZE * PIXELSIZE);

// Check to see if the game is running
private boolean inGame = false;

// Timer used to record tick times
private Timer timer;

// Used to set game speed, the lower the #, the faster the Chopper travels
// which in turn
// makes the game harder.
private static int speed = 45;

// Instances of our Chopper & food so we can use their methods
private Chopper chopper = new Chopper(BOARDWIDTH, BOARDHEIGHT);
private String userName = "";
private boolean pause = false;;



public int GetBoardWidth() {
	return BOARDWIDTH;
}

public int GetBoardHeight() {
	return BOARDHEIGHT;
}


public Board() { 
	
	addKeyListener(new Keys());
    setBackground(Color.white);
    setFocusable(true);
    setPreferredSize(new Dimension(BOARDWIDTH, BOARDHEIGHT));

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
	
	 if(chopper.getVelX() >= 0) {
		g.drawImage(chopper.getChopper(), (int)(chopper.getX()) - 37, (int)(chopper.getY()), 75, 50, null);
		if(pause) {
			pauseGame(g);
		}
	}
	else if(chopper.getVelX() < 0){
		g.drawImage(chopper.getChopper(), (int)(chopper.getX()) + 37, (int)(chopper.getY()), -75, 50, null);
		if(pause) {
			pauseGame(g);
		}
	}
	
	
	
   //}
     // Sync our graphics together
    // Toolkit.getDefaultToolkit().sync();
}


void initializeGame()
{
	chopper.setChopper();
	
    // set the timer to record our game's speed / make the game move
    timer = new Timer(speed, this);
    timer.start();
    
}

// if our Chopper is in the close proximity of the food..


// Used to check collisions with Chopper's self and board edges


void pauseGame(Graphics g) {

		
		final int ALPHA = 175; // how much see-thru. 0 to 255
	    final Color GP_BG = new Color(75, 75, 75, ALPHA);
	    String text = "PAUSED";
	    String fontName = "serif";
	    Font font = new Font(fontName, Font.BOLD, 15);
	    g.setFont(font); 
	    FontMetrics metrics = g.getFontMetrics(font);
	    int x = BOARDWIDTH /2 - metrics.stringWidth(text) /2;
	    
	    g.setColor(GP_BG);
		g.drawRect(0,0,BOARDWIDTH,BOARDHEIGHT);
		g.fillRect(0, 0, BOARDWIDTH, BOARDHEIGHT);
		g.setColor(Color.RED);
		g.drawString(text, x, BOARDHEIGHT/2);
		
		




}



// Run constantly as long as we're in game.
@Override
public void actionPerformed(ActionEvent e) {
    if (inGame == true) {
    	chopper.tick();
    	chopper.gravity();
    	
    }
    	
    	
    
    
    // Repaint or 'render' our screen
    repaint();
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
        	pause = false;
        	inGame = true;
        }
        if (key == KeyEvent.VK_P) {
        	
        	if(pause == false) {
        		pause = true;
        		inGame = false;
        	}
        	else {
        		pause = false;
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
}