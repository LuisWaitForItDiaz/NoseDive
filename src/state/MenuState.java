package state;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.Animation;
import org.newdawn.slick.geom.Circle;
import org.lwjgl.input.Mouse;
import java.awt.Toolkit;

import state.GameState;

public class MenuState extends BasicGameState{
    protected SpriteSheet titleLogo;
    protected SpriteSheet buttonSprite;
    protected Animation logoAnimation;
    protected Animation buttonAnimation;
    public Shape button;
    public GameState gameState = new GameState();
    public static final float  HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().height;
    private Sound sound;


    public final Shape mouseTracker= new Circle(5,5,5,5);

    public int getID(){
        return Game.MENU;
    }

    public MenuState() {
        try {
            titleLogo= new SpriteSheet(new Image("res/titleLogo.png").getScaledCopy(1), 1600, 900);
            buttonSprite = new SpriteSheet(new Image("res/StartButton.png").getScaledCopy(1), 380, 110);
            logoAnimation = new Animation(titleLogo, 100);
            buttonAnimation = new Animation(buttonSprite, 400);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        button = new Rectangle(600, 670, 399, 100);

    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        sound=new Sound("res/sounds/Title Menu.wav");
        sound.play(1f, 1f);

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{

        logoAnimation.draw(0, 0);
        buttonAnimation.draw(600, 670);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException{
        mouseTracker.setCenterX(Mouse.getX());
        mouseTracker.setCenterY(HEIGHT - Mouse.getY());
        if(Mouse.isButtonDown(0) == true){
            sound.stop();
            sbg.enterState(Game.GAME);
            gameState.sound=new Sound("res/sounds/Nose Diving 2.0.wav");
            gameState.sound.play();
            gameState.sound.loop();
        }

    }


}
