import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;




public class Game extends JFrame {
Board board = new Board();
Chopper chopper = new Chopper(board.getBoardWidth(), board.getBoardHeight());
private Target target = new Target(board.getBoardWidth());
private Bomb bomb = new Bomb(board.getBoardHeight());
private boolean pause = false;
private boolean inGame = false;


Game() {
    add(board);
    setResizable(false);
    pack();
    setTitle("GET TO THA CHOPPA");
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   
}

public static void main(String[] args) {
    // Creates a new thread so our GUI can process itself
    EventQueue.invokeLater(new Runnable() {
        @Override
        public void run() {
            JFrame frame = new Game();
            frame.setVisible(true);
        }
        
    });

}

private void tick() {
 	chopper.tick();
 	chopper.gravity();
 	target.tick();
 	if(bomb.isDropped()) {
 		bomb.tick();
 	}
}





/*private class Keys extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
        	chopper.setLeft(true);
        	System.out.println("skjhfv.kljadhflkjg");
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
        	System.out.println(inGame);
        	pause = false;
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
}*/
public Boolean isPaused() {
	return pause;
}

public Boolean inGame() {
	return inGame;
}
}

