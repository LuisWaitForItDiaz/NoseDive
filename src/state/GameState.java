package state;
import java.util.Iterator;
import java.util.Random;
import java.util.ArrayList;

import io.github.nose.*;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import state.EndState;
import org.lwjgl.*;

public class GameState extends BasicGameState {
    private Building border;
    private SpriteSheet background;
    private SpriteSheet spriteHealth;
    private Animation backgroundAnimation;
    private NoseBoi player;
    public Sound sound;
    public int Health = 3;
    public EndState endState = new EndState();

    private int balconySpawnDelay;
    private int balloonSpawnDelay;
    private int shirtSpawnDelay;

    public int getID() {return Game.GAME;}

    public ArrayList<Balloon> balloonArrayList = new ArrayList<>();
    public ArrayList<RainbowBalloon> powerArrayList = new ArrayList<>();
    public ArrayList<HeartBalloon> heartArrayList = new ArrayList<>();
    public ArrayList<BombBalloon> bombArrayList = new ArrayList<>();
    public ArrayList<Balcony> balconyArrayList = new ArrayList<>();
    public ArrayList<Shirt> shirtArrayList = new ArrayList<>();
    public ArrayList<SpriteSheet> lifeArrayList = new ArrayList<>();


    Random rand = new Random();
    private long nextFrameTime;
    public ResourceManager res;


    public GameState() {
        res = new ResourceManager();
        //creates background
        try {
            background = new SpriteSheet(new Image("res/background.png").getScaledCopy(1), 1600, 900);
            backgroundAnimation = new Animation(background, 100);
        } catch (SlickException e) {

        }
        balloonSpawnDelay = createSpawnDelay(20, 120);
        balconySpawnDelay = createSpawnDelay(50, 250);
        shirtSpawnDelay = createSpawnDelay(100, 400);
    }


    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        player = new NoseBoi(this);
        border = new Building();

        nextFrameTime = Sys.getTime();

    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        //references each class and the drawing method within the class
        // draws everything inside the method
        backgroundAnimation.draw(0, 0);
        border.draw(g);
        player.draw(g);
        if(spriteHealth!= null) spriteHealth.draw(0,0);


        for (Balloon balloon : balloonArrayList) {
            balloon.draw(g);
        }

        for (BombBalloon bombBalloon : bombArrayList) {
            bombBalloon.draw(g);
        }

        for (HeartBalloon heartBalloon : heartArrayList) {
            heartBalloon.draw(g);
        }

        for (RainbowBalloon powerBalloon : powerArrayList) {
            powerBalloon.draw(g);
        }

        for (Balcony balcony : balconyArrayList) {
            balcony.draw(g);
        }

        for (Shirt shirt : shirtArrayList) {
            shirt.draw(g);
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        player.update(i);

        // press escape to close screen
        if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
            gc.exit();
        }

        if (--balloonSpawnDelay == 0) {
            Balloon balloon = new Balloon(this, res);
            balloonArrayList.add(balloon);
            balloonSpawnDelay = createSpawnDelay(20, 200);
        }

        if (rand.nextInt(500) == 1) {
            BombBalloon bombBalloon = new BombBalloon(this, res);
            bombArrayList.add(bombBalloon);
        }

        if (rand.nextInt(600) == 1) {
            HeartBalloon heartBalloon = new HeartBalloon(this, res);
            heartArrayList.add(heartBalloon);
        }

        if (rand.nextInt(700) == 1) {
            RainbowBalloon powerBalloon = new RainbowBalloon(this, res);
            powerArrayList.add(powerBalloon);
        }


        if (--balconySpawnDelay == 0) {
            Balcony balcony = new Balcony(this, res);
            balconyArrayList.add(balcony);
            balconySpawnDelay = createSpawnDelay(40, 240);
        }

        if (--shirtSpawnDelay == 0) {
            Shirt shirt = new Shirt(this);
            shirtArrayList.add(shirt);
            shirtSpawnDelay = createSpawnDelay(80, 340);
        }

        //references the update method in each class
        border.update(i);

        Iterator<Balloon> iterBalloon = balloonArrayList.iterator();
        while (iterBalloon.hasNext()) {
            Balloon balloon = iterBalloon.next();
            balloon.update(i);

            if (balloon.y < -196) {
                iterBalloon.remove();
            }
        }


        Iterator<BombBalloon> iterBombBalloon = bombArrayList.iterator();
        while (iterBombBalloon.hasNext()) {
            BombBalloon bombBalloon = iterBombBalloon.next();
            bombBalloon.update(i);

            if (bombBalloon.y < -196) {
                iterBombBalloon.remove();
            }
        }

        Iterator<HeartBalloon> iterHeartBalloon = heartArrayList.iterator();
        while (iterHeartBalloon.hasNext()) {
            HeartBalloon HeartBalloon = iterHeartBalloon.next();
            HeartBalloon.update(i);

            if (HeartBalloon.y < -196) {
                iterHeartBalloon.remove();
            }
        }

        Iterator<RainbowBalloon> iterPowerBalloon = powerArrayList.iterator();
        while (iterPowerBalloon.hasNext()) {
            RainbowBalloon powerBalloon = iterPowerBalloon.next();
            powerBalloon.update(i);

            if (powerBalloon.y < -196) {
                iterPowerBalloon.remove();
            }
        }

        Iterator<Balcony> iterBalcony = balconyArrayList.iterator();
        while (iterBalcony.hasNext()) {
            Balcony balcony = iterBalcony.next();
            balcony.update(i);

            if (balcony.y < -1000) {
                iterBalcony.remove();
            }
        }

        Iterator<Shirt> iterShirt = shirtArrayList.iterator();
        while (iterShirt.hasNext()) {
            Shirt shirt = iterShirt.next();
            shirt.update(i);

            if (shirt.y < -1000) {
                iterShirt.remove();
            }
        }


        if(Health==3){
            lifeArrayList.indexOf(2);
            checkLife(Health);
        }
        else if(Health == 2){
            lifeArrayList.indexOf(1);
            checkLife(Health);
        }
        else if (Health == 1){
            lifeArrayList.indexOf(0);
            checkLife(Health);
        }
        if(Health < 1){
            //sound.stop();
            System.out.println(Health);
            sbg.getState( Game.GAMEOVER);
            sbg.enterState(Game.GAMEOVER);
            endState.sound=new Sound("res/sounds/Title Menu.wav");
            endState.sound.play(.3f, 1f);
        }


    }

    public int createSpawnDelay(int minTime, int maxTime) {
        int spawnDelay = rand.nextInt(maxTime - minTime) + minTime;
        return spawnDelay;
    }

    public void checkLife(int Health) {
        try {

            lifeArrayList.add(new SpriteSheet(new Image("res/health1.png").getScaledCopy(3), 196, 196));
            lifeArrayList.add(new SpriteSheet(new Image("res/health2.png").getScaledCopy(3), 196, 196));
            lifeArrayList.add(new SpriteSheet(new Image("res/health3.png").getScaledCopy(3), 196, 196));

            if(Health == 3) {
                spriteHealth = lifeArrayList.get(2);
            }

            if (Health == 2) {
                spriteHealth = lifeArrayList.get(1);
            }
            else if (Health == 1) {
                spriteHealth = lifeArrayList.get(0);
            }

        } catch (SlickException e) {
            e.printStackTrace();
        }
    }
}
