package io.github.nose;


import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

import java.util.ArrayList;

public class ResourceManager {

    protected ArrayList<SpriteSheet> balloonTextures = new ArrayList<>();
    protected ArrayList<SpriteSheet> balloonPopTextures = new ArrayList<>();

    protected ArrayList<SpriteSheet> powerTextures = new ArrayList<>();
    protected ArrayList<SpriteSheet> heartTextures = new ArrayList<>();
    protected ArrayList<SpriteSheet> bombTextures = new ArrayList<>();

    protected ArrayList<SpriteSheet> balconyTextures = new ArrayList<>();
    protected ArrayList<SpriteSheet> balconyDestroyTextures = new ArrayList<>();

    protected ArrayList<SpriteSheet> shirtTextures = new ArrayList<>();
    protected ArrayList<SpriteSheet> shirtDestroyTextures = new ArrayList<>();

    public ResourceManager(){
        try{
            balloonTextures.add(new SpriteSheet(new Image("res/rBalloon.png").getScaledCopy(1), 196, 196));
            balloonTextures.add(new SpriteSheet(new Image("res/gBalloon.png").getScaledCopy(1), 196, 196));
            balloonTextures.add(new SpriteSheet(new Image("res/yBalloon.png").getScaledCopy(1), 196, 196));

            balloonPopTextures.add(new SpriteSheet(( new Image("res/rBalloonPop.png").getScaledCopy(1)),  196, 196));
            balloonPopTextures.add(new SpriteSheet(new Image("res/gBalloonPop.png").getScaledCopy(1), 196, 196));
            balloonPopTextures.add(new SpriteSheet(new Image("res/yBalloonPop.png").getScaledCopy(1), 196, 196));

            balconyTextures.add(new SpriteSheet(new Image("res/rightBalcony.png").getScaledCopy(1), 1600, 900));
            balconyTextures.add(new SpriteSheet(new Image("res/leftBalcony.png").getScaledCopy(1), 1600, 900));

            balconyDestroyTextures.add(new SpriteSheet(new Image("res/rightBalconyDestroy.png").getScaledCopy(1), 1600, 900));
            balconyDestroyTextures.add(new SpriteSheet(new Image("res/leftBalconyDestroy.png").getScaledCopy(1), 1600, 900));

            shirtTextures.add(new SpriteSheet(new Image("res/rightShirt.png").getScaledCopy(1), 1600, 900));
            shirtTextures.add(new SpriteSheet(new Image("res/leftShirt.png").getScaledCopy(1), 1600, 900));
            shirtTextures.add(new SpriteSheet(new Image("res/midShirt.png").getScaledCopy(1), 1600, 900));
            shirtTextures.add(new SpriteSheet(new Image("res/leftMidShirt.png").getScaledCopy(1), 1600, 900));
            shirtTextures.add(new SpriteSheet(new Image("res/rightMidShirt.png").getScaledCopy(1), 1600, 900));

            shirtDestroyTextures.add(new SpriteSheet(new Image("res/rightShirtDestroy.png").getScaledCopy(1), 1600, 900));
            shirtDestroyTextures.add(new SpriteSheet(new Image("res/leftShirtDestroy.png").getScaledCopy(1), 1600, 900));
            shirtDestroyTextures.add(new SpriteSheet(new Image("res/MidShirtDestroy.png").getScaledCopy(1), 1600, 900));
            shirtDestroyTextures.add(new SpriteSheet(new Image("res/leftMidShirtDestroy.png").getScaledCopy(1), 1600, 900));
            shirtDestroyTextures.add(new SpriteSheet(new Image("res/rightShirtDestroy.png").getScaledCopy(1), 1600, 900));

            powerTextures.add(new SpriteSheet(new Image("res/powerBalloon.png").getScaledCopy(1), 196, 196));
            powerTextures.add(new SpriteSheet(( new Image("res/powerBalloonPop.png").getScaledCopy(1)),  196, 196));

            heartTextures.add(new SpriteSheet(new Image("res/heartBalloon.png").getScaledCopy(1), 196, 196));
            heartTextures.add(new SpriteSheet(new Image("res/heartBalloonPop.png").getScaledCopy(1), 196, 196));

            bombTextures.add(new SpriteSheet(new Image("res/bombBalloon.png").getScaledCopy(2), 392, 392));
            bombTextures.add(new SpriteSheet(new Image("res/bombBalloonPop.png").getScaledCopy(2), 392, 392));


        }catch(SlickException e){
            e.printStackTrace();
        }
    }
}
