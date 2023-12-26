import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameScreen extends JPanel implements Runnable, KeyListener {

    final int tileSize = 32; // 32 x 32px tile
    final Dimension screenSize = new Dimension(640, 512); // 20 x 16 tiles

    final PlayerCharacter playerCharacter = new PlayerCharacter();

    public GameScreen() {
        this.setPreferredSize(screenSize);
        this.setMinimumSize(screenSize);
        this.setMaximumSize(screenSize);
        this.setDoubleBuffered(true);
        this.setBackground(Color.BLACK);
        this.addKeyListener(this);
        this.setFocusable(true);
    }

    @Override
    public void run() {
        while (true) {
            update();

            repaint();
        }
    }

    protected void update() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        playerCharacter.drawInitialPlayerCharacter(g2d);
        g2d.dispose();
     }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
