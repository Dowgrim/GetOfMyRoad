package Display;

import Engine.Solid;

import javax.swing.*;

/**
 * Created by Michael on 23/01/2016.
 */
public class DisplaySolid extends JPanel {

    protected Solid solid;

    public DisplaySolid(Solid s){
        solid = s;
    }

    public void display() {
        setBounds(solid.x(), solid.y(), solid.w(), solid.h());
    }


}
