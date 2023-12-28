package tiles;

import entities.player.Player;
import utils.MapLoader;
import window.GameScreen;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TilesManager {

    GameScreen gameScreen;
    Player player;
    List<Tile> tiles = new ArrayList<>();
    List<AnimatedTile> animatedTiles = new ArrayList<>();
    public List<MapTile> collisionTiles = new ArrayList<>();
    StaticTile defaultTile;
    int[][] map;

    public TilesManager(GameScreen gameScreen, Player player) {
        this.gameScreen = gameScreen;
        this.player = player;
        map = MapLoader.loadMapFromFile(gameScreen.maxWorldColumn);
        initStaticTiles();
        defaultTile = (StaticTile) tiles.get(0);
    }

    private void initStaticTiles() {
        tiles.add(new StaticTile(1, "src/resources/ground/grass01.png", false));
        tiles.add(new StaticTile(2, "src/resources/ground/grass02.png", false));
        tiles.add(new StaticTile(3, "src/resources/ground/grass03.png", false));
        tiles.add(new StaticTile(4, "src/resources/ground/grass_path01.png", false));

        tiles.add(new StaticTile(5, "src/resources/utils/stone01.png", true));
        tiles.add(new StaticTile(6, "src/resources/utils/red_flower01.png", true));
        tiles.add(new StaticTile(7, "src/resources/utils/tree01.png", true));


        AnimatedTile water = new AnimatedTile(100, true);
        water.loadSprites("src/resources/water/water1.png", "src/resources/water/water2.png");
        tiles.add(water);
        animatedTiles.add(water);
    }

    public void draw(Graphics2D g2d) {
        drawTiles(g2d);
    }

    private void drawTiles(Graphics g2d) {
        collisionTiles = new ArrayList<>();
        for (int column = 0; column < gameScreen.maxWorldColumn; column++) {
            for (int row = 0; row < gameScreen.maxWorldRow; row++) {

                int worldX = column * gameScreen.tileSize;
                int worldY = row * gameScreen.tileSize;

                int screenX = worldX - player.worldX + player.screenX;
                int screenY = worldY - player.worldY + player.screenY;

                if (screenX > player.minScreenX && screenX < player.maxScreenX) {
                    if (screenY > player.minScreenY && screenY < player.maxScreenY) {
                        int tileId = map[row][column];
                        Tile tile = tiles.get(tileId);
                        if (tile instanceof StaticTile) {
                            StaticTile staticTile = (StaticTile) tiles.get(tileId);
                            if (tileId != defaultTile.id) {
                                g2d.drawImage(defaultTile.img, screenX, screenY ,null);
                                g2d.drawImage(staticTile.img, screenX, screenY, null);
                            } else {
                                g2d.drawImage(staticTile.img, screenX, screenY ,null);
                            }
                            if (staticTile.collision) {
                                MapTile mapTile = new MapTile(staticTile, screenX, screenY, worldX, worldY);
                                collisionTiles.add(mapTile);
                                g2d.setColor(Color.CYAN);
                        }
                        } else if (tile instanceof AnimatedTile) {
                            AnimatedTile animatedTile = (AnimatedTile) tiles.get(tileId);
                            g2d.drawImage(animatedTile.currImg, screenX, screenY,null);
                            if (animatedTile.collision) {
                                collisionTiles.add(new MapTile(animatedTile, screenX, screenY, worldX, worldY));
                            }
                        }
                    }
                }
            }
        }

        for (MapTile mapTile : collisionTiles) {
            g2d.setColor(Color.red);
//            g2d.drawRect(mapTile.minPlayerScreenX, mapTile.minPlayerScreenY, 1, 1);
//            g2d.drawRect(mapTile.maxPlayerScreenX, mapTile.minPlayerScreenY, 1, 1);
//
//            g2d.setColor(Color.blue);
//            g2d.drawRect(mapTile.minPlayerScreenX, mapTile.maxPlayerScreenY, 1, 1);
//            g2d.drawRect(mapTile.maxPlayerScreenX, mapTile.maxPlayerScreenY, 1, 1);

            g2d.drawRect(mapTile.playerScreenX, mapTile.playerScreenY, 32, 32);
        }
    }

    public void updateAnimatedTiles() {
        animatedTiles.forEach(AnimatedTile::addCounterAndChangeImg);
    }

}
