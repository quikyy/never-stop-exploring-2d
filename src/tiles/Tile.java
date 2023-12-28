package tiles;

public abstract class Tile {
    public final int size = 32; // px
    public int id;
    public boolean collision;
    public String path;

    public Tile(int id, String path, boolean collision) {
        this.id = id;
        this.path = path;
        this.collision = collision;
    }

    public Tile(int id, boolean collision) {
        this.id = id;
        this.collision = collision;
    }
}
