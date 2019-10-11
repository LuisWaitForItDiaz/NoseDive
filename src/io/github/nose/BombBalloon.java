package io.github.nose;

import java.util.Random;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import state.GameState;

public class BombBalloon extends Balloon{
    public float x, y, theta, yVel;
    float distanceTraveled;
    protected SpriteSheet sprite;
    protected SpriteSheet spriteDestroy;
    protected Animation animation;
    protected Animation animationPop;
    public Shape hitBox;
    private Random rand = new Random();
    int randBalloon;
    public GameState game;

    public ResourceManager res;


    public BombBalloon(GameState game, ResourceManager res) {
        super(game, res);
        x = rand.nextFloat() * 900 + 250;
        y = 901;
        theta = 0;
        yVel = (float) -3.5;


        this.game = game;
        try {

            sprite = new SpriteSheet(new Image("res/bombBalloon.png").getScaledCopy(2), 392, 392);
            animation = new Animation(sprite, 200);

            hitBox = new Rectangle(x + 145, y, 92, 98);

        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public void update(int delta) {
        y += yVel;
        theta += .05;
        //x += 3* Math.cos(theta);
        if (hitBox != null) hitBox.setY(y + 118);

    }
    @Override
    void reward() {
        // override with whatever balloons do
    }

    public boolean pop() {
        hitBox = null;
        try {
            spriteDestroy = new SpriteSheet(new Image("res/bombBalloonPop.png").getScaledCopy(2), 392, 392);

            animationPop = new Animation(spriteDestroy, 50);

        } catch (SlickException e) {
            e.printStackTrace();
        }
        return true;
    }

    public void draw(Graphics g) {
        if (hitBox == null) {
            animationPop.draw(x, y);
        } else {
            animation.draw(x, y);
        }
    }
}