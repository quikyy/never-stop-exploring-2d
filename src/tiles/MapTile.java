package tiles;

public class MapTile {

    public Tile tile;
    public int playerScreenX;
    public int playerScreenY;
    public int worldX;
    public int worldY;

    public int minPlayerScreenX;
    public int maxPlayerScreenX;

    public int minPlayerScreenY;
    public int maxPlayerScreenY;
    public TileCollisionArea tileCollisionArea;


    public MapTile(Tile tile, int playerScreenX, int playerScreenY, int worldX, int worldY) {
        this.tile = tile;
        this.playerScreenX = playerScreenX;
        this.playerScreenY = playerScreenY;

        this.worldX = worldX;
        this.worldY = worldY;

        this.minPlayerScreenX = playerScreenX;
        this.maxPlayerScreenX = playerScreenX + tile.size;
        this.minPlayerScreenY = playerScreenY;
        this.maxPlayerScreenY = playerScreenY + tile.size;
        tileCollisionArea = new TileCollisionArea(worldX, worldY, tile.size);
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

        public TileCollisionArea (int worldX, int worldY, int tileSize) {
            this.topLeftCorner = worldX - tileSize + 6;
            this.topRightCorner = worldX + tileSize - 6;
            this.bottomLeftCorner = worldY - tileSize + 6;
            this.bottomRightCorner = worldY + tileSize - 6;
        }
    }





}
