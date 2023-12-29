package tiles.types;

import tiles.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class StaticTile extends Tile {

    public BufferedImage img;

    public StaticTile(String path, boolean collision, int bufferArea, boolean isFast) {
        super(path, collision, bufferArea, isFast);
        loadSprite();
    }

    private void loadSprite() {
        try {
            InputStream is = new FileInputStream(path);
            img = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
