package tiles.types;

import tiles.Tile;

public class MapTile {

    public Tile tile;
    public int playerScreenX;
    public int playerScreenY;
    public int worldX;
    public int worldY;
    public TileCollisionArea tileCollisionArea;

    public MapTile(Tile tile, int playerScreenX, int playerScreenY, int worldX, int worldY) {
        this.tile = tile;
        this.playerScreenX = playerScreenX;
        this.playerScreenY = playerScreenY;
        this.worldX = worldX;
        this.worldY = worldY;
        tileCollisionArea = new TileCollisionArea(worldX, worldY, tile.size, tile.bufferArea);
    }

    public boolean isPlayerCollisionWithTile(int requestX, int requestY) {
        return requestX >= tileCollisionArea.topLeftCorner
                && requestX <= tileCollisionArea.topRightCorner
                && requestY >= tileCollisionArea.bottomLeftCorner
                && requestY <= tileCollisionArea.bottomRightCorner;
    }

    public static class TileCollisionArea {
        public final int topLeftCorner;
        public final int topRightCorner;
        public final int bottomLeftCorner;
        public final int bottomRightCorner;

        public TileCollisionArea (int worldX, int worldY, int tileSize, int bufferArea) {
            this.topLeftCorner = worldX - tileSize + bufferArea;
            this.topRightCorner = worldX + tileSize - bufferArea;
            this.bottomLeftCorner = worldY - tileSize + bufferArea;
            this.bottomRightCorner = worldY + tileSize - bufferArea;
        }
    }
}
