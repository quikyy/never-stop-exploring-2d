package window;

import entities.player.Player;
import entities.player.PlayerMovementHandler;
import tiles.TilesManager;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel implements Runnable {

    public final int tileSize = 32; // 32 x 32px tile

    // SCREEN
    public final int maxScreenColumn = 20;
    public final int maxScreenRow = 16;
    public final int screeWidth = maxScreenColumn * tileSize;
    public final int screenHeight = maxScreenRow * tileSize;
    final Dimension screenSize = new Dimension(screeWidth, screenHeight); // 20 x 16 tiles

    // WORLD
    public final int maxWorldColumn = 40;
    public final int maxWorldRow = 16;
    public final int worldWidth = maxWorldColumn * tileSize;
    public final int worldHeight = maxWorldColumn * tileSize;
    final PlayerMovementHandler playerMovementHandler = new PlayerMovementHandler();
    final Player player = new Player(playerMovementHandler, this);
    public final TilesManager tilesManager = new TilesManager(this, player);
    double FPS = 60;

    public GameScreen() {
        this.setPreferredSize(screenSize);
        this.setMinimumSize(screenSize);
        this.setMaximumSize(screenSize);
        this.setDoubleBuffered(true);
        this.setBackground(Color.BLACK);
        this.addKeyListener(playerMovementHandler);
        this.setFocusable(true);
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currTime;

        while (true) {
            currTime = System.nanoTime();
            delta += (currTime - lastTime) / drawInterval;
            lastTime = currTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    protected void update() {
        player.update();
        tilesManager.updateAnimatedTiles();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Toolkit.getDefaultToolkit().sync();
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        tilesManager.draw(g2d);
        player.draw(g2d);
        g2d.dispose();
    }
}
