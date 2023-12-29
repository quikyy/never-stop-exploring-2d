package tiles.manager;

import entities.player.Player;
import mapmanager.MapManager;
import tiles.Tile;
import tiles.repository.TilesRepository;
import tiles.types.AnimatedTile;
import tiles.types.MapTile;
import tiles.types.StaticTile;
import utils.MapLoader;
import window.GameScreen;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TilesManager extends MapManager {

    StaticTile defaultTile;
    final int[][] tilesMap;

    public TilesManager(GameScreen gameScreen, Player player) {
        super(gameScreen, player);
        tilesMap = MapLoader.loadMapFromFile(gameScreen.maxWorldColumn, MapLoader.tilesMapPath);
        load();
        setDefaultGameTile();
    }

    @Override
    public void load() {
        TilesRepository.addGameTile(new StaticTile( "src/resources/ground/grass01.png", false,6, false));
        TilesRepository.addGameTile(new StaticTile( "src/resources/ground/grass02.png", false, 6, false));
        TilesRepository.addGameTile(new StaticTile("src/resources/ground/grass03.png", false, 6, false));

        TilesRepository.addGameTile(new StaticTile("src/resources/ground/grass_path01.png", false, -4, true));

        TilesRepository.addGameTile(new StaticTile("src/resources/utils/stone01.png", true, 6, false));
        TilesRepository.addGameTile(new StaticTile("src/resources/utils/red_flower01.png", false, 6, false));
        TilesRepository.addGameTile(new StaticTile("src/resources/utils/tree01.png", true, 6, false));

        TilesRepository.addGameTile(new AnimatedTile( "src/resources/water/water1.png", "src/resources/water/water2.png", true, 6, false));

        TilesRepository.addGameTile(new StaticTile("src/resources/water/water_edge_01.png", true, 6, false));

        TilesRepository.addGameTile(new StaticTile("src/resources/utils/statue/statue01.png", true, 4, false));
        TilesRepository.addGameTile(new StaticTile("src/resources/utils/statue/statue02.png", true, 25, false));
        TilesRepository.addGameTile(new StaticTile("src/resources/utils/statue/statue03.png", true, 25, false));
        TilesRepository.addGameTile(new StaticTile("src/resources/utils/statue/statue04.png", true, 4, false));
    }

    @Override
    public void draw(Graphics2D g2d) {
        TilesRepository.mapTiles = new ArrayList<>();
        for (int column = 0; column < gameScreen.maxWorldColumn; column++) {
            for (int row = 0; row < gameScreen.maxWorldRow; row++) {

                int worldX = column * gameScreen.tileSize;
                int worldY = row * gameScreen.tileSize;
                int screenX = worldX - player.worldX + player.screenX;
                int screenY = worldY - player.worldY + player.screenY;

                if (screenX < player.leftScreenInSight && screenX > player.rightScreenInSight && screenY < player.topScreenInSight && screenY > player.bottomScreenInSight) {
                    return; // this chunk of map is out of player sight. there is no need to draw
                }

                int tileId = tilesMap[row][column];
                Tile tile = TilesRepository.getGameTileById(tileId);
                if (tile instanceof StaticTile) {
                    StaticTile staticTile = (StaticTile) TilesRepository.getGameTileById(tileId);
                    if (tileId != defaultTile.id) {
                        g2d.drawImage(defaultTile.img, screenX, screenY ,null);
                        g2d.drawImage(staticTile.img, screenX, screenY, null);
                        TilesRepository.addMapTile(new MapTile(defaultTile, screenX, screenY, worldX, worldY));
                        TilesRepository.addMapTile(new MapTile(staticTile, screenX, screenY, worldX, worldY));
                    } else {
                        g2d.drawImage(staticTile.img, screenX, screenY ,null);
                        TilesRepository.addMapTile(new MapTile(staticTile, screenX, screenY, worldX, worldY));
                    }
                } else if (tile instanceof AnimatedTile) {
                    AnimatedTile animatedTile = (AnimatedTile) TilesRepository.getGameTileById(tileId);
                    g2d.drawImage(animatedTile.currImg, screenX, screenY,null);
                    TilesRepository.addMapTile(new MapTile(animatedTile, screenX, screenY, worldX, worldY));
                }
            }
        }
        drawCollisionTilesAround(g2d);
    }

    public void updateAnimatedTiles() {
        List<Tile> animatedTiles = TilesRepository.getGameAnimatedTiles();
        for (Tile animTile : animatedTiles) {
            AnimatedTile animatedTile = (AnimatedTile) animTile;
            animatedTile.addCounterAndChangeImg();
        }
    }


    private void drawCollisionTilesAround(Graphics g2d) {
        for (MapTile mapTile : TilesRepository.getMapCollisionTiles()) {
            g2d.setColor(Color.CYAN);
            g2d.drawRect(mapTile.playerScreenX, mapTile.playerScreenY, 32,  32);
        }
    }

    private void setDefaultGameTile() {
        defaultTile = (StaticTile) TilesRepository.getGameTileById(0);
    }
}
