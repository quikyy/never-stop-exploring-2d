package entities.player;

import entities.Entity;
import entities.player.movement.MovementDirection;
import entities.player.movement.PlayerMovementHandler;
import tiles.repository.TilesRepository;
import tiles.types.MapTile;
import window.GameScreen;

import java.awt.*;
import java.io.IOException;

public class Player extends Entity {

    public GameScreen gameScreen;
    PlayerMovementHandler playerMovementHandler;

    public final int screenX;
    public final int screenY;

    public final int leftScreenInSight;
    public final int rightScreenInSight;
    public final int topScreenInSight;
    public final int bottomScreenInSight;
    private final int tilesInSight = 11;

    public Player(PlayerMovementHandler playerMovementHandler, GameScreen gameScreen) {
        this.playerMovementHandler = playerMovementHandler;
        this.gameScreen = gameScreen;

        this.screenX = gameScreen.screeWidth / 2  - (gameScreen.tileSize); // center of the screen
        this.screenY = gameScreen.screenHeight / 2  - (gameScreen.tileSize); // center of the screen

        this.leftScreenInSight = screenX - (tilesInSight * gameScreen.tileSize);
        this.rightScreenInSight = screenX + (tilesInSight * gameScreen.tileSize);
        this.topScreenInSight = screenY - (tilesInSight * gameScreen.tileSize);
        this.bottomScreenInSight = screenY + (tilesInSight * gameScreen.tileSize);

        super.loadEntitySprites("src/resources/character/character_sprites.png");
        setDefaultValues();
    }

    private void setDefaultValues() {
        super.worldX = screenX; // resp at center of the screen
        super.worldY = screenY; // resp at center of the screen

        super.collisionAreaScreen = new Rectangle(screenX, screenY, 32, 32);

        super.speed = 2; // init movement speed
        super.currentMovementDirection = MovementDirection.STAND_DOWN;
        super.currentSprite = standDownImg;
    }
    public boolean isSprinting = false;
    public void update() {
        int requestX = worldX;
        int requestY = worldY;
        switch (playerMovementHandler.lastKeyCodePressed) {
            case 87 -> {
                if (isSprinting) {
                    requestY -= 4;
                } else {
                    requestY -= speed;

                }
                if (checkEntityCollisionWithTiles(requestX, requestY)) {
                    return;
                }
                setMovingSpriteImage(MovementDirection.MOVE_UP);
                worldY = requestY;
            }
            case 83 -> {
                if (isSprinting) {
                    requestY += 4;
                } else {
                    requestY += speed;
                }
                if (checkEntityCollisionWithTiles(requestX, requestY)) {
                    return;
                }
                setMovingSpriteImage(MovementDirection.MOVE_DOWN);
                worldY = requestY;
            }
            case 65 -> {
                if (isSprinting) {
                    requestX -= 4;
                } else {
                    requestX -= speed;
                }
                if (checkEntityCollisionWithTiles(requestX, requestY)) {
                    return;
                }
                setMovingSpriteImage(MovementDirection.MOVE_LEFT);
                worldX = requestX;
            }
            case 68 -> {
                if (isSprinting) {
                    requestX += 4;
                } else {
                    requestX += speed;
                }
                if (checkEntityCollisionWithTiles(requestX, requestY)) {
                    return;
                }
                setMovingSpriteImage(MovementDirection.MOVE_RIGHT);
                worldX = requestX;
            }
            case 67 -> {
                requestX += speed / 2;
                requestY +=  speed / 2;
                setMovingSpriteImage(MovementDirection.MOVE_RIGHT);
                worldX = requestX;
                worldY = requestY;
            }
            case 90 -> {
                requestX -= speed / 2;
                requestY += speed / 2;
                if (checkEntityCollisionWithTiles(requestX, requestY)) {
                    return;
                }
                setMovingSpriteImage(MovementDirection.MOVE_LEFT);
                worldX = requestX;
                worldY = requestY;
            }
            case 69 -> {
                requestX += speed / 2;
                requestY-= speed / 2;
                if (checkEntityCollisionWithTiles(requestX, requestY)) {
                    return;
                }
                setMovingSpriteImage(MovementDirection.MOVE_RIGHT);
                worldX = requestX;
                worldY = requestY;
            }
            case 81 -> {
                requestX -= speed / 2;
                requestY -= speed / 2;
                if (checkEntityCollisionWithTiles(requestX, requestY)) {
                    return;
                }
                setMovingSpriteImage(MovementDirection.MOVE_LEFT);
                worldX = requestX;
                worldY = requestY;
            }
            case 0 -> {
                setStandSpriteImage();
                speed = 2;
            }
        }
        if (!TilesRepository.mapTiles.isEmpty()) {
            isPlayerOnFastTile(requestX, requestY);
        }
    }

    public void draw(Graphics2D g2d) {
        g2d.drawImage(currentSprite, screenX, screenY, null);
        g2d.setColor(Color.yellow);
        g2d.drawRect(collisionAreaScreen.x, collisionAreaScreen.y, collisionAreaScreen.width, collisionAreaScreen.height);
    }

    private void isPlayerOnFastTile(int requestX, int requestY) {
        try {
            for (MapTile mapTile : TilesRepository.mapTiles) {
                if (mapTile.isPlayerCollisionWithTile(requestX, requestY)) {
                    if (mapTile.tile.isFast) {
                        isSprinting = true;
                        return;
                    }
                }
            }
            isSprinting = false;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
