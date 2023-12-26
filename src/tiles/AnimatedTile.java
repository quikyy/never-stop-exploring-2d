package tiles;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
public class AnimatedTile extends Tile {

    public int counter = 0;
    BufferedImage img1, img2;
    BufferedImage currImg;

    public AnimatedTile(int id, boolean collision) {
        super(id, collision);
    }

    public void loadSprites(String path1, String path2) {
        try {
            InputStream is = new FileInputStream(path1);
            img1 = ImageIO.read(is);
            is = new FileInputStream(path2);
            img2 = ImageIO.read(is);
            currImg = img1;
            System.out.println("load");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addCounterAndChangeImg() {
        counter++;
        if (counter == 50) {
            currImg = img2;
        } else if (counter == 100) {
            currImg = img1;
            counter = 0;
        }
    }
}
