package io.github.nose;


import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import static state.Game.gameOver;

public class Building {
    private float x, y;
    private float y2 = y + 900;
    private Image building, building2;
    public Shape leftHitBox = null;
    public Shape rightHitBox = null;

    public Building(){
        try{
            building = new Image("res/buildingSides.png");
            building2 = new Image("res/buildingSides.png");

        }catch (SlickException e){
            e.printStackTrace();
            //Logger.getLogger(NoseBoi.class.getName().log(Level.SEVERE, null) e);
        }
        x = 0;
        y = 0;
    }

    public void update(int delta){
        if(gameOver == false){
            y-=6;
            if(y <= -900){
                y = y2 + 900;
            }
            y2 -= 6;

            if(y2 <= -900){
                y2 = y + 900;
            }
        }
    }


    public void draw(Graphics g){
        building.draw(x, y);
        building2.draw(x, y2);

        leftHitBox = new Rectangle(0, 0, 300, 900);
        rightHitBox = new Rectangle(1300, 0, 300, 900);
    }
}

