package com.xlip.comrotator.screen;

import com.xlip.comrotator.menu.GameMenu;
import com.xlip.comrotator.screen.world.GameWorld;
import com.xlip.threedtemp.Screen.Screen;

public class GameScreen extends Screen {
    GameWorld gameWorld;
    GameMenu gameMenu;

    public GameScreen() {
        super();
        this.gameWorld = new GameWorld();
        this.gameMenu = new GameMenu();
        setMenu(gameMenu);
        setWorld(gameWorld);
    }


}
