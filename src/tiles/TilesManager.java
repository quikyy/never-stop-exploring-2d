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
    List<AnimatedTile> animatedTileList = new ArrayList<>();
    public List<MapTile> mapTiles = new ArrayList<>();

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
        tiles.add(new StaticTile(6, "src/resources/utils/red_flower01.png", false));
        tiles.add(new StaticTile(7, "src/resources/utils/tree01.png", true));


        AnimatedTile water = new AnimatedTile(100, true);
        water.loadSprites("src/resources/water/water1.png", "src/resources/water/water2.png");
        tiles.add(water);
        animatedTileList.add(water);
    }

    public void draw(Graphics2D g2d) {
        drawTiles(g2d);
    }

    private void drawTiles(Graphics g2d) {
        mapTiles = new ArrayList<>();
        for (int column = 0; column < gameScreen.maxWorldColumn; column++) {
            for (int row = 0; row < gameScreen.maxWorldRow; row++) {
                int worldX = column * gameScreen.tileSize;
                int worldY = row * gameScreen.tileSize;
                int screenX = worldX - player.worldX + player.screeX;
                int screenY = worldY - player.worldY + player.screenY;

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
                        mapTiles.add(new MapTile(staticTile, worldX, worldY));
                    }
                } else if (tile instanceof AnimatedTile) {
                    AnimatedTile animatedTile = (AnimatedTile) tiles.get(tileId);
                    g2d.drawImage(animatedTile.currImg, screenX, screenY,null);
                    if (animatedTile.collision) {
                        mapTiles.add(new MapTile(animatedTile, worldX, worldY));
                    }
                }
            }
        }
    }

    public void updateAnimatedTiles() {
        animatedTileList.forEach(AnimatedTile::addCounterAndChangeImg);
    }

}
