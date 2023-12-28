package entities.player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class PlayerMovementHandler implements KeyListener {

    int lastKeyCodePressed = 0;

    Set<Integer> currentPressedKeys = new HashSet<>();

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode != lastKeyCodePressed) {
            currentPressedKeys.add(keyCode);
            lastKeyCodePressed = keyCode;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        currentPressedKeys.remove(e.getKeyCode());
        if (currentPressedKeys.isEmpty()) {
            lastKeyCodePressed = 0;
        }

    }
}
