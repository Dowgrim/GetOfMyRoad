package Engine;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Created by Michael on 12/01/2016.
 */
public class Player extends Solid {

    public static int FORCEIMPULSE = 50;

    private int modificateurHorizontal = 0;
    private int modificateurVertical = 0;
    // speed limit pour éviter une accélération infini et un manque de lisibilité
    private double speedXLimit = 50;
    private double speedYLimit = 50 ;

    public Player(Localisation posinit, Dimension dimInit, ArrayList<Boolean> iniOccupiedLevel, Level l){

        super(posinit, dimInit, 1, iniOccupiedLevel, l);

    }

    public void forward(double dTime){

        super.forward(dTime);

        if (pos.getX() < -dim.getWidth()/2)
        {
            pos.setX(level.getW()-w()/2);
        }
        else if (x() > (level.getW()-w()/2))
        {
            setPosX(-w()/2);
        }

        if (y() < 0 )
        {
            setPosY(0);
        }
        else if (y() > (level.getH()-h()))
        {
            setPosY(level.getH()-h());
        }

    }
    // pour pouvoir limiter la vitesse du pions.
    @Override
    public double processSpeedX(double dTime) {
        double speed = super.processSpeedX(dTime);
        if (speed>speedXLimit)
        {
            speed = speedXLimit;
        }
        else if (speed< (-speedXLimit))
        {
            speed = -speedXLimit;
        }
        return speed;
    }
    @Override
    public double processSpeedY(double dTime) {
        double speed = super.processSpeedY(dTime);
        if (speed>speedYLimit)
        {
            speed = speedYLimit;
        }
        else if (speed < (-speedYLimit))
        {
            speed = -speedYLimit;
        }
        return speed;
    }


}
