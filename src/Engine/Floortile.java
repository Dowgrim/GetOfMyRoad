package Engine;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by ThePolyBro on 15/01/2016.
 */
public class Floortile extends Solid{

    private double floorSpeed;
    private Level level;

    public Floortile(Localisation posinit, Dimension dim, ArrayList<Boolean> initOccupiedLevels, Level l)
    {
        super(posinit, dim, 15, initOccupiedLevels, l);
        level = l;
        floorSpeed = 0.1 ;
    }

    public void setFloorSpeed(double floorSpeed) {
        this.floorSpeed = floorSpeed;
    }

    @Override
    public void forward(double dTime){
        if(pos.getY() > (Math.ceil(level.getH()/dim.getHeight())+1)*dim.getHeight()) {
            pos.setY(-dim.getHeight());
        }

        super.forward(dTime);
    }

    @Override
    public double processSpeedX(double dTime) {
        int speed = 0;
        return speed;
    }

    @Override
    public double processSpeedY(double dTime) {
        double speed = floorSpeed;
        return speed;
    }

    @Override
    public void checkColision() {

    }
}
