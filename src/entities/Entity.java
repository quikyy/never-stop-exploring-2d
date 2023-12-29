package entities;

import entities.player.movement.MovementDirection;
import tiles.repository.TilesRepository;
import tiles.types.MapTile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public abstract class Entity {

    public final int tileSize = 32;
    public int worldX;
    public int worldY;
    public int speed;
    public int movingSpriteCounter;
    public Rectangle collisionAreaScreen;
    public MovementDirection currentMovementDirection;
    public BufferedImage currentSprite;
    public BufferedImage standUpImg, upImg1, upImg2;
    public BufferedImage standDownImg, downImg1, downImg2;
    public BufferedImage standRightImg, rightImg1, rightImg2;
    public BufferedImage standLeftImg, leftImg1, leftImg2;

    private final int imgChangeFirstStep = 15;
    private final int imgChangeSecondStep = imgChangeFirstStep * 2;

    public void loadEntitySprites(String path) {
        try {
            InputStream is = new FileInputStream(path);
            BufferedImage sprites = ImageIO.read(is);
            standDownImg = sprites.getSubimage(0,0, tileSize, tileSize);
            downImg1 = sprites.getSubimage(32,0, tileSize, tileSize);
            downImg2 = sprites.getSubimage(64,0, tileSize, tileSize);

            standUpImg = sprites.getSubimage(96, 0, tileSize, tileSize);
            upImg1 = sprites.getSubimage(128, 0, tileSize, tileSize);
            upImg2 = sprites.getSubimage(159, 0, tileSize, tileSize);

            standRightImg = sprites.getSubimage(191, 0,tileSize,tileSize);
            rightImg1 = sprites.getSubimage(223, 0,tileSize,tileSize);
            rightImg2 = sprites.getSubimage(255, 0,tileSize,tileSize);

            standLeftImg = sprites.getSubimage(287, 0,tileSize,tileSize);
            leftImg1 = sprites.getSubimage(319, 0,tileSize,tileSize);
            leftImg2 = sprites.getSubimage(351, 0,tileSize,tileSize);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setMovingSpriteImage(MovementDirection movementDirection) {
        currentMovementDirection = movementDirection;
        movingSpriteCounter++;
        switch (movementDirection) {
            case MOVE_DOWN -> {
                if (movingSpriteCounter > imgChangeSecondStep) {
                    currentSprite = downImg1;
                    movingSpriteCounter = 0;
                }
                else if (movingSpriteCounter < imgChangeFirstStep) {
                    currentSprite = downImg1;
                } else if (movingSpriteCounter > imgChangeFirstStep) {
                    currentSprite = downImg2;
                }
            }

            case MOVE_UP -> {
                if (movingSpriteCounter > imgChangeSecondStep) {
                    currentSprite = upImg1;
                    movingSpriteCounter = 0;
                }
                else if (movingSpriteCounter < imgChangeFirstStep) {
                    currentSprite = upImg1;
                } else if (movingSpriteCounter > imgChangeFirstStep) {
                    currentSprite = upImg2;
                }
            }

            case MOVE_LEFT -> {
                if (movingSpriteCounter > imgChangeSecondStep) {
                    currentSprite = leftImg1;
                    movingSpriteCounter = 0;
                }
                else if (movingSpriteCounter < imgChangeFirstStep) {
                    currentSprite = leftImg1;
                } else if (movingSpriteCounter > imgChangeFirstStep) {
                    currentSprite = leftImg2;
                }
            }

            case MOVE_RIGHT -> {
                if (movingSpriteCounter > imgChangeSecondStep) {
                    currentSprite = rightImg1;
                    movingSpriteCounter = 0;
                }
                else if (movingSpriteCounter < imgChangeFirstStep) {
                    currentSprite = rightImg1;
                } else if (movingSpriteCounter > imgChangeFirstStep) {
                    currentSprite = rightImg2;
                }
            }
        }
    }

    public void setStandSpriteImage() {
        switch (currentMovementDirection) {
            case MOVE_UP -> currentSprite = standUpImg;
            case MOVE_DOWN -> currentSprite = standDownImg;
            case MOVE_RIGHT -> currentSprite = standRightImg;
            case MOVE_LEFT -> currentSprite = standLeftImg;
        }
    }

    public boolean checkEntityCollisionWithTiles(int requestX, int requestY) {
        for (MapTile mapTile : TilesRepository.getMapCollisionTiles()) {
            if (mapTile.isPlayerCollisionWithTile(requestX, requestY)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkEntityOnFastTile(int requestX, int requestY) {
        for (MapTile mapTile : TilesRepository.getMapCollisionTiles()) {
            if (mapTile.isPlayerCollisionWithTile(requestX, requestY) && mapTile.tile.path.equals("src/resources/ground/grass_path01.png")) {
                System.out.println("HE");
                return true;
            }
        }
        return false;
    }

}
