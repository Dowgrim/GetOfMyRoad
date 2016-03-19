package Engine;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Created by Michael on 12/01/2016.
 *
 */
public class Player extends Solid {

    public static int FORCEIMPULSE = 50;

    private int modificateurHorizontal = 0;
    private int modificateurVertical = 0;
    // speed limit pour éviter une accélération infini et un manque de lisibilité
    private double speedXLimit = 100;
    private double speedYLimit = 100;
    // contrainte appliquer par le joueur
    private double playerXConstrain;
    private double playerYConstrain;
    // limite sur les contraint du joueur:
    private double playerXConstrainLimit= 50;
    private double playerYConstrainLimit = 50 ;

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
        double speed = speedX + dTime * (sumForcesX+playerXConstrain) / mass;
        if (speedX > speedXLimit)
        {
            speedX = speedXLimit;
        }
        else if (speedX < (-speedXLimit))
        {
            speedX = -speedXLimit;
        }
        if(sumForcesX != 0)
            sumForcesX -= (sumForcesX > 0 ? 2 : -2);

    }
    @Override
    public void processSpeedY(double dTime) {
        double speed = speedY + dTime * (sumForcesY+playerYConstrain) / mass;

        speedY = speed;
        if (speedY > speedYLimit)
        {
            speedY = speedYLimit;
        }

        else if (speedY < (-speedYLimit))
        {
            speedY = -speedYLimit;
        }

        if(sumForcesY != 0)
            sumForcesY -= (sumForcesY > 0 ? 2 : -2);
    }
    public void setPlayerXConstrain( double constrain)
    {

        playerXConstrain=(constrain>playerXConstrainLimit? playerXConstrainLimit:constrain);


    }

    public void setPlayerYConstrain(double constrain)
    {
        playerYConstrain = (constrain>playerYConstrainLimit ? playerYConstrainLimit : constrain);

    }
    public void setPlayerConstrain(double xConstrain, double yConstrain)
    {
        setPlayerXConstrain(xConstrain);

        setPlayerYConstrain(yConstrain);

    }
}
