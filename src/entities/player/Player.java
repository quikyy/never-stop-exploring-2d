package entities.player;

import entities.Entity;
import entities.MovementDirection;
import window.GameScreen;

import java.awt.*;

public class Player extends Entity {
    public GameScreen gameScreen;
    public final int screeX;
    public final int screenY;
    PlayerMovementHandler playerMovementHandler;

    public Player(PlayerMovementHandler playerMovementHandler, GameScreen gameScreen) {
        this.playerMovementHandler = playerMovementHandler;
        this.gameScreen = gameScreen;
        this.screeX = gameScreen.screeWidth / 2  - (gameScreen.tileSize / 2);
        this.screenY = gameScreen.screenHeight / 2  - (gameScreen.tileSize / 2);
        super.collisionArea = new Rectangle(16, 16, 16, 16);
        super.loadSprites("src/resources/character/character_sprites.png");
        setDefaultValues();
    }

    private void setDefaultValues() {
        super.worldX = screeX;
        super.worldY = screenY;
        super.speed = 5; // init movement speed
        super.currentMovementDirection = MovementDirection.STAND_DOWN;
        super.currentSprite = spriteDown1;
    }

    public void update() {
        int requestX = worldX;
        int requestY = worldY;

        switch (playerMovementHandler.lastKeyCodePressed) {
            case 87 -> {
                requestY -= speed;
                if (checkCollision(gameScreen.tilesManager.mapTiles, requestX, requestY)) {
                    return;
                }
                setMovingSpriteImage(MovementDirection.MOVE_UP);
                worldY -= speed;
            }
            case 83 -> {
                requestY += speed;
                if (checkCollision(gameScreen.tilesManager.mapTiles, requestX, requestY)) {
                    return;
                }
                setMovingSpriteImage(MovementDirection.MOVE_DOWN);
                worldY += speed;
            }
            case 65 -> {
                requestX -= speed;
                if (checkCollision(gameScreen.tilesManager.mapTiles, requestX, requestY)) {
                    return;
                }
                setMovingSpriteImage(MovementDirection.MOVE_LEFT);
                worldX -= speed;
            }
            case 68 -> {
                requestX += speed;
                if (checkCollision(gameScreen.tilesManager.mapTiles, requestX, requestY)) {
                    return;
                }
                setMovingSpriteImage(MovementDirection.MOVE_RIGHT);
                worldX += speed;
            }
            case 67 -> {
                setMovingSpriteImage(MovementDirection.MOVE_RIGHT);
                worldX += speed / 2;
                worldY += speed / 2;
            }
            case 90 -> {
                setMovingSpriteImage(MovementDirection.MOVE_LEFT);
                worldX -= speed / 2;
                worldY += speed / 2;
            }
            case 69 -> {
                setMovingSpriteImage(MovementDirection.MOVE_RIGHT);
                worldX += speed / 2;
                worldY -= speed / 2;
            }
            case 81 -> {
                setMovingSpriteImage(MovementDirection.MOVE_LEFT);
                worldX -= speed / 2;
                worldY -= speed / 2;
            }

            case 0 -> {
                setStandSpriteImage();
            }
        }
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(currentSprite, screeX, screenY, null);
    }
}
