package others.entity;

import others.infra.Entity;
import others.infra.Game;
import others.infra.Time;

import java.awt.*;

import static others.infra.Game.GameState.OL_PRESENTS;

/**
 * Initializer class.
 * 
 * @author ABIR
 */
public class Initializer extends Entity {

    private FadeEffect fadeEffect;
    
    public Initializer(Game game) {
        super(game);
        game.setBackgroundColor(Color.WHITE);
    }

    @Override
    public void start() {
        fadeEffect = game.getEntity(FadeEffect.class);
        fadeEffect.setIntensity(1);
        fadeEffect.fadeIn();
    }
    
    private int ip = 0;
    private double waitTime;
    
    @Override
    protected void updateInitializing() {
        //game.setGameState(TITLE);
        //if (1 == 1) return;
                
        switch (ip) {
            case 0:
                if (Time.getFixedFrames() > 180) {
                    fadeEffect.fadeOut();
                    ip = 1;
                }
                return;
            case 1:
                if (!fadeEffect.isFinished()) {
                    return;
                }
                waitTime = Time.getCurrentTime();
                ip = 2;
            case 2:
                if (Time.getCurrentTime() - waitTime < 1) {
                    return;
                }
                game.setGameState(OL_PRESENTS);
                setDestroyed(true);
        }
    }
    
}
