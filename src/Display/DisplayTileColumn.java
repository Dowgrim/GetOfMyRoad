package Display;

import Engine.Floortile;
import Engine.Level;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TheRogBro on 22/01/2016.
 */
public class DisplayTileColumn {
    private Level level;
    private List<Floortile> column = new ArrayList<Floortile>();
    private double columnSpeed;

    public DisplayTileColumn(int interval, double size, int lenght, Level levelInit, JLayeredPane layers, Screen screen)
    {
        level = levelInit;
        int c = (int) (Math.random()*10)%3;
        Floortile floortile;
        columnSpeed = 50;
        for ( int i = 0; i < lenght+1; i++) {
            for (int j = 0; j < 10; j++){
                floortile = level.floortileInitialisation(interval * j, (int) (size * (i)), (int) size);
                DisplayFloortile df = new DisplayFloortile(new Color((int) (Math.random() * 150),
                        (int) (Math.random() * 150), (int) (Math.random() * 150)), floortile);
                layers.add(df, JLayeredPane.DEFAULT_LAYER, 1);
                floortile.setFloorSpeed(columnSpeed);
                screen.addSolids(df);
                column.add(floortile);
            }
        }
    }
}
