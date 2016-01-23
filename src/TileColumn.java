import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by TheRogBro on 22/01/2016.
 */
public class TileColumn {
    private Screen screen;
    private List<Floortile> column = new ArrayList<Floortile>();
    private double columnSpeed;

    public TileColumn (int posX, double size, int lenght, Screen screenInit )
    {
        screen = screenInit;
        List<Color> floorTilesColor = new ArrayList<Color>();
        floorTilesColor.add(Color.DARK_GRAY);
        floorTilesColor.add(Color.GRAY);
        floorTilesColor.add(Color.BLACK);

        int c = (int) (Math.random()*10)%3;
        column.add(screen.floortileInitialisation(posX, (int) (size),floorTilesColor.get(c), (int) size));

        Floortile floortile;
        for ( int i = 1; i < lenght+1; i++)
        {
            floortile = screen.floortileInitialisation(posX, (int) (size * (i+1)),floorTilesColor.get((i+c)%3), (int) size);
            //floortile.setCompanion(column.get(i-1));
            floortile.setFloorSpeed(columnSpeed);
            column.add(floortile);
        }

        //column.get(0).setCompanion(column.get(lenght));
    }
}
