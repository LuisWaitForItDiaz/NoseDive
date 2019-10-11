package state;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Circle;
import org.lwjgl.input.Mouse;
import java.awt.Toolkit;


public class EndState extends BasicGameState {
    private SpriteSheet sprite;
    private Animation animation;
    public static final float  HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    public Sound sound;

    public final Shape mouseTracker= new Circle(5,5,5,5);

    public int getID() {return Game.GAMEOVER; }

    public EndState() {
        try {
            sprite = new SpriteSheet(new Image("res/GameOver.png").getScaledCopy(1), 1600, 900);
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
        mouseTracker.setCenterX(Mouse.getX());
        mouseTracker.setCenterY(HEIGHT - Mouse.getY());
        if(Mouse.isButtonDown(0) == true){
            Display.destroy();
            System.exit(Game.GAMEOVER);

            sbg.getState( Game.MENU).init(gc, sbg);
            sbg.enterState(Game.MENU);
        }

    }
}
