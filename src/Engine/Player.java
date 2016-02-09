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
    private double speedXLimit = 100;
    private double speedYLimit = 100;

    public Player(Localisation posinit, Dimension dimInit, ArrayList<Boolean> iniOccupiedLevel, Level l){

        super(posinit, dimInit, 1, iniOccupiedLevel, l);

    }

    public void forward(double dTime){

        super.forward(dTime);

        if (x() < -dim.getWidth()/2)
        {
            setPosX(level.getW()-w()/2);
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
    public void processSpeedX(double dTime) {
        super.processSpeedX(dTime);
        if (speedX > speedXLimit)
        {
            speedX = speedXLimit;
        }
        else if (speedX < (-speedXLimit))
        {
            speedX = -speedXLimit;
        }
    }
    @Override
    public void processSpeedY(double dTime) {
        super.processSpeedY(dTime);
        if (speedY > speedYLimit)
        {
            speedY = speedYLimit;
        }
        else if (speedY < (-speedYLimit))
        {
            speedY = -speedYLimit;
        }
    }


}
