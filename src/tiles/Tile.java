package tiles;
public abstract class Tile {

    public final int size = 32; // px
    public int id;
    public String path;
    public boolean collision;
    public int bufferArea;
    public boolean isFast;

    public Tile(String path, boolean collision, int bufferArea, boolean isFast) {
        this.path = path;
        this.collision = collision;
        this.bufferArea = bufferArea;
        this.isFast = isFast;
    }


}
