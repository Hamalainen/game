import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.KeyStroke;


public class Game extends JFrame {
Chopper chopper = new Chopper();
private boolean running = false;
private Thread thread;
Game() {
    add(new Board());
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

private synchronized void start() {
	if(running)
		return;
	running = true;
	thread = new Thread(this);
	thread.start();
}

private synchronized void stop() {
	if(!running)
		return;
	
	running = false;
	try {
		thread.join();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.exit(1);
}

private void tick() {
	chopper.tick();
}
}