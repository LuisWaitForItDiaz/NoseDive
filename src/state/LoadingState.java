package state;

import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.Animation;


public class LoadingState extends BasicGameState {
    private SpriteSheet sprite;
    private Animation animation;


    public int getID() { return Game.LOADING;}

    public LoadingState() {
        try {
            sprite = new SpriteSheet(new Image("res/loading.png").getScaledCopy(1), 1600, 900);
            animation = new Animation(sprite, 150);
        } catch (SlickException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {


    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        animation.draw(0, 0);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        if(i >= 400){
            sbg.enterState(Game.GAME);
        }
    }

}