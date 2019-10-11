package io.github.nose;

import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.Image;
import state.GameState;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.Animation;

public class Balcony {
    public float x, y, yVel;
    int randBalcony;
    protected static SpriteSheet balcony;
    protected static SpriteSheet balconyDestroy;
    public Shape hitBox;
    public Animation animationDestroy;
    public GameState game;
    public ResourceManager res;

    Random rand = new Random();

    public Balcony(GameState game, ResourceManager res){
        yVel = -6;
        y = 901;

        this.game = game;
        this.res = res;

        randBalcony = rand.nextInt(2);
        balcony = res.balconyTextures.get(randBalcony);

        if(randBalcony == 0) {
            x = 300;

        }
        else{
            x = 1050;
        }

        hitBox = new Rectangle(x ,y + 650, 250, 150);

    }

    public boolean destroy(){
        hitBox = null;
        animationDestroy = new Animation(res.balconyDestroyTextures.get(1 - randBalcony), 40);

        return true;
    }

    public void update(int delta){
        y += yVel;
        if(hitBox!= null) hitBox.setY(y+650);
    }

    public void draw(Graphics g){

        if(hitBox == null) {
            animationDestroy.draw(0, y);}
        else{
            balcony.draw(0, y);
        }



    }
}
