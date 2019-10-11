package io.github.nose;

import org.lwjgl.input.Keyboard;

import java.util.Iterator;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Rectangle;
import state.GameState;

public class NoseBoi {
    private float x, y, xVel = 0, yVel = 0;
    float distanceTraveled;
    protected SpriteSheet noseBoi;
    protected Animation noseAnimation;

    public Shape myHitBox;
    protected GameState game;
    int Score = 0;

    public Color c = new Color(1f,0f,0f,1);

    public NoseBoi(GameState game){
        this.game = game;
        x = 800;
        y = 0;

        myHitBox =  new Rectangle(x+ 75, y, 35, 196);



        try{
            noseBoi = new SpriteSheet(new Image("res/player.png").getScaledCopy(1), 192, 192);
            noseAnimation = new Animation(noseBoi, 100);

        }catch (SlickException e){
            e.printStackTrace();
        }

    }

    public void update(int delta){

        game.checkLife(game.Health);
        if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
            xVel = (float).95;
            distanceTraveled = xVel * delta;
            x += distanceTraveled;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
            xVel = (float)-.95;
            distanceTraveled = xVel * delta;
            x += distanceTraveled;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
            yVel = (float)-.8;
            distanceTraveled = yVel * delta;
            y += distanceTraveled;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            yVel = (float).8;
            distanceTraveled = yVel * delta;
            y += distanceTraveled;
        }
        checkCollision();

    }

    public void checkCollision(){
        if(x >1300 - 110){
            x = 1300 - 110;
            xVel = 0;

        }
        if(x < 300 - 75){
            x = 300 - 75;
            xVel = 0;
        }


        if(y + 98 > 900 ){
            y = 900 - 98;
            yVel = 0;
        }
        if(y + 98 < 0 ){
            y = -98;
            yVel = 0;
        }

        Iterator<Balloon> iterBalloon = game.balloonArrayList.iterator();
        while(iterBalloon.hasNext()){

            Balloon balloon = iterBalloon.next();
            if(balloon.hitBox != null && myHitBox.intersects(balloon.hitBox)){
                Score++;
                balloon.reward();
                balloon.pop();
            }
            if(balloon.animationPop != null && balloon.animationPop.getFrame() == 8){
                iterBalloon.remove();
            }
        }

        Iterator<BombBalloon> iterBombBalloon = game.bombArrayList.iterator();
        while(iterBombBalloon.hasNext()){

            BombBalloon BombBalloon = iterBombBalloon.next();
            if(BombBalloon.hitBox != null && myHitBox.intersects(BombBalloon.hitBox)){
                Score--;
                game.Health--;
                BombBalloon.reward();
                BombBalloon.pop();
            }
            if(BombBalloon.animationPop != null && BombBalloon.animationPop.getFrame() == 8){
                iterBombBalloon.remove();
            }
        }

        Iterator<HeartBalloon> iterHeartBalloon = game.heartArrayList.iterator();
        while(iterHeartBalloon.hasNext()){

            HeartBalloon heartBalloon = iterHeartBalloon.next();
            if(heartBalloon.hitBox != null && myHitBox.intersects(heartBalloon.hitBox)){
                Score++;
                game.Health = Math.max(0, Math.min(3, game.Health+1));
                heartBalloon.reward();
                heartBalloon.pop();
            }
            if(heartBalloon.animationPop != null && heartBalloon.animationPop.getFrame() == 8){
                iterHeartBalloon.remove();
            }
        }

        Iterator<RainbowBalloon> iterPowerBalloon = game.powerArrayList.iterator();
        while(iterPowerBalloon.hasNext()){

            RainbowBalloon powerBalloon = iterPowerBalloon.next();
            if(powerBalloon.hitBox != null && myHitBox.intersects(powerBalloon.hitBox)){
                Score++;
                powerBalloon.pop();
                powerBalloon.reward();

            }
            if(powerBalloon.animationPop != null && powerBalloon.animationPop.getFrame() == 8){
                iterPowerBalloon.remove();
            }
        }

        Iterator<Balcony> iterBalcony = game.balconyArrayList.iterator();
        while(iterBalcony.hasNext()){

            Balcony balcony = iterBalcony.next();
                if(balcony.hitBox != null && myHitBox.intersects(balcony.hitBox)){
                    game.Health--;
                    balcony.destroy();

                }
            if(balcony.animationDestroy != null && balcony.animationDestroy.getFrame() == 8) {
                iterBalcony.remove();
            }
        }

        Iterator<Shirt> iterShirt = game.shirtArrayList.iterator();
        while(iterShirt.hasNext()){

            Shirt shirt = iterShirt.next();
            if(shirt.hitBox != null && myHitBox.intersects(shirt.hitBox)){
                game.Health--;
                shirt.destroy();

            }
            if(shirt.animation != null &&shirt.animation.getFrame() == 8) {
                iterBalcony.remove();
            }
        }

    }



    public void draw(Graphics g){
        noseAnimation.draw(x, y);
        myHitBox =  new Rectangle(x+ 75, y + 186, 35, 10);
        g.drawString("SCORE: " + Score, 10, 25);
        g.drawString("HEALTH: " + game.Health, 10, 50);
    }
}
