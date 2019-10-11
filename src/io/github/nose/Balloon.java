package io.github.nose;
import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import state.GameState;

public class Balloon {
    public float x, y, theta, yVel;
    float distanceTraveled;
    protected SpriteSheet sprite;
    protected Animation animation;
    protected Animation animationPop;
    public Shape hitBox;
    private Random rand = new Random();
    int randBalloon;

    public GameState game;

    public ResourceManager res;


    public Balloon(GameState game, ResourceManager res){
         x = rand.nextFloat()* 900 + 250;
         y = 901;
         theta = 0;
         yVel = (float) -3.5;

         hitBox = new Rectangle(x + 49, y + 49, 92, 98);

         this.game = game;
         this.res = res;

         randBalloon = rand.nextInt((3));
         sprite = res.balloonTextures.get(randBalloon);

         animation = new Animation(sprite, 200);
         hitBox = new Rectangle(x + 49, y + 49, 92, 98);

    }

    public void update(int delta){
        y += yVel;
        theta += .05;
        //x += 3* Math.cos(theta);
        if(hitBox!= null) hitBox.setY(y+49);

    }

    void reward(){
        // override with whatever balloons do
    }
    public boolean pop(){
        hitBox = null;

        animationPop = new Animation(res.balloonPopTextures.get(randBalloon), 40);

        return true;
    }

    public void draw(Graphics g) {
        if(hitBox == null){ animationPop.draw(x, y);}
        else{
            animation.draw(x, y);
        }
    }

}
