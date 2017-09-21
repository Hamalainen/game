    import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
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
private boolean inGame = true;

// Timer used to record tick times
private Timer timer;

// Used to set game speed, the lower the #, the faster the Chopper travels
// which in turn
// makes the game harder.
private static int speed = 45;

// Instances of our Chopper & food so we can use their methods
private Chopper chopper = new Chopper();
private String userName = "";



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
    // Only draw if the game is running / the Chopper is alive
    if (inGame == true)
    {
    	// Draw our Chopper.      
    	g.drawImage(chopper.getChopper(), (int)(chopper.getX()), (int)(chopper.getY()), 75, 50, null);
    }
     // Sync our graphics together
     Toolkit.getDefaultToolkit().sync();
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


void endGame(Graphics g) {

    // Create a message telling the player the game is over
    String message = "Game over";

    // Create a new font instance
    Font font = new Font("Times New Roman", Font.BOLD, 14);
    FontMetrics metrics = getFontMetrics(font);

    // Set the color of the text to red, and set the font
    g.setColor(Color.red);
    g.setFont(font);

    // Draw the message to the board
    g.drawString(message, (BOARDWIDTH - metrics.stringWidth(message)) / 2,
            BOARDHEIGHT / 2);
        

    System.out.println("Game Ended");

}

// Run constantly as long as we're in game.
@Override
public void actionPerformed(ActionEvent e) {
    if (inGame == true) {
    	chopper.tick();
    	if(chopper.getY() < 300) {
    		chopper.gravity();
    	}

    	
      
    }
    
    // Repaint or 'render' our screen
    repaint();
}

private class Keys extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            chopper.setVelX(-5);
            System.out.println("left");
        }

        if (key == KeyEvent.VK_RIGHT) {
            chopper.setVelX(5);
        }

        if (key == KeyEvent.VK_UP) {
            chopper.setVelY(-5);
        }

        if (key == KeyEvent.VK_DOWN) {
            chopper.setVelY(5);
        }
    }

    /*
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            chopper.setVelX(0);
        }

        if (key == KeyEvent.VK_RIGHT) {
            chopper.setVelX(0);
        }

        if (key == KeyEvent.VK_UP) {
            chopper.setVelY(0);
        }

        if (key == KeyEvent.VK_DOWN) {
            chopper.setVelY(0);
        }
    }
    */
}
}