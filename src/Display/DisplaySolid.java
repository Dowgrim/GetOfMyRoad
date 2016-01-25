package Display;

import Engine.Solid;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Michael on 23/01/2016.
 */
public class DisplaySolid extends JPanel {

    protected Solid solid;

    public DisplaySolid(Solid s){
        solid = s;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    public void display() {
        setBounds(solid.x(), solid.y(), solid.w(), solid.h());
    }


}
