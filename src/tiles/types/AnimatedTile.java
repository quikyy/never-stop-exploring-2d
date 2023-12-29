package tiles.types;

import tiles.Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class AnimatedTile extends Tile {

    public BufferedImage img1, img2;
    public BufferedImage currImg;
    public int imgChangeCounter = 0;

    private final int imgChangeFirstStep = 50;
    private final int imgChangeSecondStep = imgChangeFirstStep * 2;

    public AnimatedTile(String imgPath1, String imgPath2, boolean collision, int bufferArea, boolean isFast) {
        super(imgPath1, collision, bufferArea, isFast);
        loadSprites(imgPath1, imgPath2);
    }

    private void loadSprites(String path1, String path2) {
        try {
            InputStream is = new FileInputStream(path1);
            img1 = ImageIO.read(is);
            is = new FileInputStream(path2);
            img2 = ImageIO.read(is);
            currImg = img1;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addCounterAndChangeImg() {
        imgChangeCounter++;
        if (imgChangeCounter == imgChangeFirstStep) {
            currImg = img2;
        } else if (imgChangeCounter == imgChangeSecondStep) {
            currImg = img1;
            imgChangeCounter = 0;
        }
    }
}
