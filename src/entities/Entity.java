package entities;

import tiles.MapTile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public abstract class Entity {

    public final int tileSize = 32;
    public int worldX;
    public int worldY;
    public int speed;
    public int movingSpriteCounter;
    public Rectangle collisionArea;
    public MovementDirection currentMovementDirection;
    public BufferedImage currentSprite;
    public BufferedImage spriteUp1, spriteUp2, spriteUp3;
    public BufferedImage spriteDown1, spriteDown2, spriteDown3;
    public BufferedImage spriteRight1, spriteRight2, spriteRight3;
    public BufferedImage spriteLeft1, spriteLeft2, spriteLeft3;

    private final int spriteChangeFirstValue = 20;
    private final int spriteChangeSecondValue = 40;

    public void loadSprites(String path) {
        try {
            InputStream is = new FileInputStream(path);
            BufferedImage sprites = ImageIO.read(is);
            spriteDown1 = sprites.getSubimage(0,0, tileSize, tileSize);
            spriteDown2 = sprites.getSubimage(32,0, tileSize, tileSize);
            spriteDown3 = sprites.getSubimage(64,0, tileSize, tileSize);

            spriteUp1 = sprites.getSubimage(96, 0, tileSize, tileSize);
            spriteUp2 = sprites.getSubimage(128, 0, tileSize, tileSize);
            spriteUp3 = sprites.getSubimage(159, 0, tileSize, tileSize);

            spriteRight1 = sprites.getSubimage(191, 0,tileSize,tileSize);
            spriteRight2 = sprites.getSubimage(223, 0,tileSize,tileSize);
            spriteRight3 = sprites.getSubimage(255, 0,tileSize,tileSize);

            spriteLeft1 = sprites.getSubimage(287, 0,tileSize,tileSize);
            spriteLeft2 = sprites.getSubimage(319, 0,tileSize,tileSize);
            spriteLeft3 = sprites.getSubimage(351, 0,tileSize,tileSize);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setMovingSpriteImage(MovementDirection movementDirection) {
        currentMovementDirection = movementDirection;
        movingSpriteCounter++;

        switch (movementDirection) {
            case MOVE_DOWN -> {
                if (movingSpriteCounter > spriteChangeSecondValue) {
                    currentSprite = spriteDown2;
                    movingSpriteCounter = 0;
                }
                else if (movingSpriteCounter < spriteChangeFirstValue) {
                    currentSprite = spriteDown2;
                } else if (movingSpriteCounter > spriteChangeFirstValue) {
                    currentSprite = spriteDown3;
                }
            }

            case MOVE_UP -> {
                if (movingSpriteCounter > spriteChangeSecondValue) {
                    currentSprite = spriteUp2;
                    movingSpriteCounter = 0;
                }
                else if (movingSpriteCounter < spriteChangeFirstValue) {
                    currentSprite = spriteUp2;
                } else if (movingSpriteCounter > spriteChangeFirstValue) {
                    currentSprite = spriteUp3;
                }
            }

            case MOVE_LEFT -> {
                if (movingSpriteCounter > spriteChangeSecondValue) {
                    currentSprite = spriteLeft2;
                    movingSpriteCounter = 0;
                }
                else if (movingSpriteCounter < spriteChangeFirstValue) {
                    currentSprite = spriteLeft2;
                } else if (movingSpriteCounter > spriteChangeFirstValue) {
                    currentSprite = spriteLeft3;
                }
            }

            case MOVE_RIGHT -> {
                if (movingSpriteCounter > spriteChangeSecondValue) {
                    currentSprite = spriteRight2;
                    movingSpriteCounter = 0;
                }
                else if (movingSpriteCounter < spriteChangeFirstValue) {
                    currentSprite = spriteRight2;
                } else if (movingSpriteCounter > spriteChangeFirstValue) {
                    currentSprite = spriteRight3;
                }
            }
        }
    }

    public void setStandSpriteImage() {
        switch (currentMovementDirection) {
            case MOVE_UP -> currentSprite = spriteUp1;
            case MOVE_DOWN -> currentSprite = spriteDown1;
            case MOVE_RIGHT -> currentSprite = spriteRight1;
            case MOVE_LEFT -> currentSprite = spriteLeft1;
        }
    }

    public boolean checkCollision(List<MapTile> mapTiles, int requestX, int requestY) {

        for (MapTile mapTile : mapTiles) {
           int minX = mapTile.x - 20;
           int maxX = mapTile.x + 20;
           int minY = mapTile.y - 20;
           int maxY = mapTile.y + 20;

           if (requestX > minX && requestX < maxX) {
               if (requestY > minY && requestY < maxY) {
                   System.out.println("collison!");
                   return true;
               }
           }

        }
        return false;
    }

}
