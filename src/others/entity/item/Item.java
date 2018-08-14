package others.entity.item;

import others.entity.Actor;
import others.entity.Mario;
import others.infra.Entity;
import others.infra.Tile;

/**
 * Item class.
 * 
 * @author ABIR
 */
public class Item extends Actor {
    
    protected Mario mario;
    protected Tile tile;
    protected int endTileId;
    protected boolean getByMario;
    protected String extraData;
    
    public Item() {
    }

    public int getEndTileId() {
        return endTileId;
    }

    @Override
    public void start() {
        super.start();
        mario = game.getEntity(Mario.class);
        setVisible(true);
    }
    
    public void set(Tile tile, int endTileId, String extraData) {
        tile.convertToWorldPosition(position);
        this.tile = tile;
        this.endTileId = endTileId;
        this.extraData = extraData;
        setGame(tile.getGame());
        setzOrder(0);
        animationTimeScale = 20;
        flipSpriteAccordingToDirection = false;
    }
    
    public void onImpact(boolean lastObject) {
        System.out.println("achou object = " + getClass().getName());
        if (lastObject && endTileId != tile.getId()) {
            map.replaceTile(tile, endTileId);
        }
        tile.nextItem();
        game.addEntity(this);
    }
    
    @Override
    public void onImpactFromGround(ImpactStrength strength) {
    }
    
    public void onCollectedByPlayer() {
    }

    @Override
    public void onHorizontalEntityCollision(Entity otherEntity) {
        if (!getByMario && otherEntity instanceof Mario) {
            Mario player = (Mario) otherEntity;
            if (!player.isDead()) {
                getByMario = true;
                onGetByMario();
            }
        }
    }

    @Override
    public void onVerticalEntityCollision(Entity otherEntity) {
        if (!getByMario && otherEntity instanceof Mario) {
            Mario player = (Mario) otherEntity;
            if (!player.isDead()) {
                getByMario = true;
                onGetByMario();
            }
        }
    }

    protected void onGetByMario() {
    }    

}
