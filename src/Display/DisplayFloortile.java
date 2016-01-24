package Display;

import Engine.Solid;

import java.awt.*;

/**
 * Created by Michael on 24/01/2016.
 */
public class DisplayFloortile extends DisplaySolid {

    private Color color;

    public DisplayFloortile(Color c, Solid s){
        super(s);
        color = c;
    }

    @Override
    public void paintComponent(Graphics g){
        g.setColor(color);
        g.fillRect(solid.x(), solid.y(), solid.w(), solid.h());
    }

}
