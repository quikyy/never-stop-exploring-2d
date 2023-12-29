package mapmanager;

import entities.player.Player;
import window.GameScreen;

import java.awt.*;

public abstract class MapManager {

    public GameScreen gameScreen;
    public Player player;

    public MapManager(GameScreen gameScreen, Player player) {
        this.gameScreen = gameScreen;
        this.player = player;
    }

    public abstract void load();
    public abstract void draw(Graphics2D g2d);

}
