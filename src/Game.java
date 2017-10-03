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

/*void tick() {
 	chopper.tick();
 	chopper.gravity();
 	target.tick();
 	if(bomb.isDropped()) {
 		bomb.tick();
 	}
}*/

}

