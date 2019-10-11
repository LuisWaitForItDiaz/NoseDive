package io.github.nose;

import java.math.*;
import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import state.GameState;

import static state.Game.gameOver;

public class RainbowBalloon extends Balloon {
    public float x, y, theta, yVel;
    protected SpriteSheet sprite;
    protected SpriteSheet spriteDestroy;
    protected Animation animation;
    public Shape hitBox;
    private Random rand = new Random();

    public GameState game;

    public RainbowBalloon(GameState game, ResourceManager res){
        super(game, res);
        x = rand.nextFloat()* 900 + 250;
        y = 901;
        theta = 0;
        yVel = (float) -3.5;

        hitBox = new Rectangle(x + 49, y + 49, 92, 98);

        this.game = game;
        try{

            sprite = new SpriteSheet(new Image("res/powerBalloon.png").getScaledCopy(1), 196, 196);
            animation = new Animation(sprite, 80);

        }catch(SlickException e){
            e.printStackTrace();
        }
    }

    public boolean pop(){
        hitBox = null;
        try {
            spriteDestroy = new SpriteSheet(( new Image("res/powerBalloonPop.png").getScaledCopy(1)),  196, 196);
            animationPop = new Animation(spriteDestroy, 40);

        }catch(SlickException e){
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void update(int delta) {
        y += yVel;
        theta += .05;
        if (hitBox != null) hitBox.setY(y + 49);
    }

    public void draw(Graphics g) {
        if(hitBox == null){ animationPop.draw(x, y);}
        else{
            animation.draw(x, y);
        }
    }
    @Override
    void reward(){

    }
}
