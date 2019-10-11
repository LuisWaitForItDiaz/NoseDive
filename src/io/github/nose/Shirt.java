package io.github.nose;

import java.util.ArrayList;
import java.util.Random;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.Image;
import state.GameState;


public class Shirt {
    public float x, y, yVel;
    protected SpriteSheet sprite;
    protected SpriteSheet spriteDestroy;
    protected Animation animation;
    public Shape hitBox;
    public GameState game;
    ArrayList<SpriteSheet> spriteSheetList = new ArrayList<>();
    ArrayList<SpriteSheet> spriteDestroyList = new ArrayList<>();

    public ResourceManager res;

    Random rand = new Random();
    public Shirt(GameState game){
        yVel = -6;
        y =  901;
        x = 0;

        this.game = game;

        try{

            spriteSheetList.add(new SpriteSheet(new Image("res/rightShirt.png").getScaledCopy(1), 1600, 900));
            spriteSheetList.add(new SpriteSheet(new Image("res/leftShirt.png").getScaledCopy(1), 1600, 900));
            spriteSheetList.add(new SpriteSheet(new Image("res/midShirt.png").getScaledCopy(1), 1600, 900));
            spriteSheetList.add(new SpriteSheet(new Image("res/leftMidShirt.png").getScaledCopy(1), 1600, 900));
            spriteSheetList.add(new SpriteSheet(new Image("res/rightMidShirt.png").getScaledCopy(1), 1600, 900));

            sprite = spriteSheetList.get(rand.nextInt(5));

            if(spriteSheetList.indexOf(sprite) == 0) {
                hitBox = new Rectangle(x + 1050, y + 1322, 100, 125);
            }
            if(spriteSheetList.indexOf(sprite) == 1) {
                hitBox = new Rectangle(x + 315, y + 640, 100, 125);
            }
            if(spriteSheetList.indexOf(sprite) == 2) {
                hitBox = new Rectangle(x + 721, y + 389, 100, 125);
            }
            if(spriteSheetList.indexOf(sprite) == 3) {
                hitBox = new Rectangle(x + 486, y + 363, 100, 125);
            }
            if(spriteSheetList.indexOf(sprite) == 4) {
                hitBox = new Rectangle(x + 1050, y + 415, 100, 125);
            }


        }catch(SlickException e){
            e.printStackTrace();
        }

    }

    public void update(int delta){
        y+= yVel;
        if(hitBox!= null)hitBox.setY(y+350);
    }
    public boolean destroy(){
        hitBox = null;
        try {
            spriteDestroyList.add(new SpriteSheet(new Image("res/rightShirtDestroy.png").getScaledCopy(1), 1600, 900));
            spriteDestroyList.add(new SpriteSheet(new Image("res/leftShirtDestroy.png").getScaledCopy(1), 1600, 900));
            spriteDestroyList.add(new SpriteSheet(new Image("res/MidShirtDestroy.png").getScaledCopy(1), 1600, 900));
            spriteDestroyList.add(new SpriteSheet(new Image("res/leftMidShirtDestroy.png").getScaledCopy(1), 1600, 900));
            spriteDestroyList.add(new SpriteSheet(new Image("res/rightShirtDestroy.png").getScaledCopy(1), 1600, 900));


            animation = new Animation(spriteDestroyList.get(spriteSheetList.indexOf(sprite)), 40);

        }catch(SlickException e){
            e.printStackTrace();
        }
        return true;
    }

    public void draw(Graphics g){
        if(hitBox == null){ animation.draw(0, y); }
        else{
            sprite.draw(0, y);
            g.draw(hitBox);
        }
    }
}
