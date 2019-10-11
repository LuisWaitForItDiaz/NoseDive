package io.github.nose;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;


import state.GameState;
import state.MenuState;
import state.LoadingState;
import state.EndState;

public class Main extends StateBasedGame
{
    public static boolean _APPLET;

    public Main(){
        super("nosBoi");
    }

    public static void main(String[] args){
        int maxFPS = 120;
        try
        {
            AppGameContainer appgc = new AppGameContainer((new Main()));
            appgc.setDisplayMode(1600, 900, false);
            appgc.setTargetFrameRate(maxFPS);
            appgc.start();

        }catch(SlickException e){
            e.printStackTrace();
        }
    }
    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        addState(new MenuState());
        addState(new LoadingState());
        addState(new GameState());
        addState(new EndState());
    }
}
