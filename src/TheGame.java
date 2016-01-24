import Display.Screen;
import Engine.Level;

import java.awt.*;

/**
 * Created by Michael on 12/01/2016.
 */
public class TheGame {

    Level level;
    Screen screen;

    public TheGame(){
        level = new Level(new Dimension(360, 640));
        screen = new Screen(level);
    }


    public static void main(String[] args) {
        new TheGame();
    }
}
