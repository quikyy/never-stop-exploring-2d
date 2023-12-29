package tiles.repository;

import tiles.Tile;
import tiles.types.AnimatedTile;
import tiles.types.MapTile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TilesRepository {

    public static List<Tile> gameTiles = new ArrayList<>();

    public static List<MapTile> mapTiles = new ArrayList<>();

    public static void addGameTile(Tile tile) {
        tile.id = gameTiles.size();
        gameTiles.add(tile);
    }

    public static Tile getGameTileById(int id) {
        return gameTiles.stream().filter(t -> t.id == id).findAny().get();
    }

    public static List<Tile> getGameAnimatedTiles() {
        return gameTiles.stream().filter(t -> t instanceof AnimatedTile).collect(Collectors.toList());
    }

    public static void addMapTile(MapTile mapTile) {
        mapTiles.add(mapTile);
    }

    public static List<MapTile> getMapCollisionTiles() {
        return mapTiles.stream().filter(t -> t.tile.collision).collect(Collectors.toList());
    }


}
