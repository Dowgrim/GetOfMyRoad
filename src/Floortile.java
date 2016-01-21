import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by ThePolyBro on 15/01/2016.
 */
public class Floortile extends Solid{

    private double floorSpeed;
    public Floortile(int posXinit, int posYinit, ArrayList<Boolean> initOccupiedLevels, Screen screen)
    {
        super( posXinit, posYinit, 15, initOccupiedLevels, screen);
        floorSpeed = 0.1 ;
    }



    @Override
    public void paintComponent(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 50, 50);
    }

    public void display() {
        setBounds(posX, posY, 50, 50);
        repaint();
    }

    @Override
    public void forward(double dTime){
        if(posY > 600) {
            posY = 0;
            posX = (int)(Math.random()*360);
        }
        posY += 1;
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
}
