import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerCharacter {

    final int tileSize = 32;
    int currentX = 64;
    int currentY = 64;
    final int initialSpeed = 100;


    public void drawInitialPlayerCharacter(Graphics2D g2d) {
        g2d.setColor(Color.WHITE);
        g2d.fillRect(currentX, currentY, tileSize,tileSize);
    }

}
