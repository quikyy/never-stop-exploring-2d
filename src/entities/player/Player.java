package entities.player;

import entities.Entity;
import entities.MovementDirection;
import window.GameScreen;

import java.awt.*;

public class Player extends Entity {
    public GameScreen gameScreen;
    public final int screenX;
    public final int screenY;
    public final int minScreenX;
    public final int maxScreenX;
    public final int minScreenY;
    public final int maxScreenY;
    PlayerMovementHandler playerMovementHandler;

    public Player(PlayerMovementHandler playerMovementHandler, GameScreen gameScreen) {
        this.playerMovementHandler = playerMovementHandler;
        this.gameScreen = gameScreen;
        this.screenX = gameScreen.screeWidth / 2  - (gameScreen.tileSize); // center of the screen
        this.screenY = gameScreen.screenHeight / 2  - (gameScreen.tileSize); // center of the screen

        this.minScreenX = screenX - (11 * gameScreen.tileSize);
        this.maxScreenX = screenX + (11 * gameScreen.tileSize);
        this.minScreenY = screenY - (11 * gameScreen.tileSize);
        this.maxScreenY = screenY + (11 * gameScreen.tileSize);
        super.loadSprites("src/resources/character/character_sprites.png");
        setDefaultValues();
    }

    private void setDefaultValues() {
        super.worldX = screenX; // resp at center of the screen
        super.worldY = screenY; // resp at center of the screen

        super.collisionAreaScreen = new Rectangle(screenX, screenY, 28, 28);

        super.speed = 4; // init movement speed
        super.currentMovementDirection = MovementDirection.STAND_DOWN;
        super.currentSprite = spriteDown1;
    }
    int x = 1;
    public void update() {
        int requestX = worldX;
        int requestY = worldY;
        switch (playerMovementHandler.lastKeyCodePressed) {
            case 87 -> {
                requestY -= speed;
                if (checkCollision(gameScreen.tilesManager.collisionTiles, requestX, requestY)) {
                    return;
                }
                setMovingSpriteImage(MovementDirection.MOVE_UP);
                worldY -= speed;
            }
            case 83 -> {
                requestY += speed;
                if (checkCollision(gameScreen.tilesManager.collisionTiles, requestX, requestY)) {
                    return;
                }
                setMovingSpriteImage(MovementDirection.MOVE_DOWN);
                worldY += speed;
            }
            case 65 -> {
                requestX -= speed;
                if (checkCollision(gameScreen.tilesManager.collisionTiles, requestX, requestY)) {
                    return;
                }
                setMovingSpriteImage(MovementDirection.MOVE_LEFT);
                worldX -= speed;
            }
            case 68 -> {
                requestX += speed;
                if (checkCollision(gameScreen.tilesManager.collisionTiles, requestX, requestY)) {
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
        g2d.drawImage(currentSprite, screenX, screenY, null);
        g2d.setColor(Color.yellow);
        g2d.drawRect(collisionAreaScreen.x, collisionAreaScreen.y, collisionAreaScreen.width, collisionAreaScreen.height);
    }
}
