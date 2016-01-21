import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.lang.Math;

/**
 * Created by Michael on 12/01/2016.
 */
public class Point extends Solid {

    private int modificateurHorizontal = 0;
    private int modificateurVertical = 0;
    // speed limit pour éviter une accélération infini et un manque de lisibilité
    private double speedXLimit = 0.05;
    private double speedYLimit = 0.05 ;

    private double forceImpulse = 1;
    public Point(Screen f, ArrayList<Integer> keys, int posXinit, int posYinit, ArrayList<Boolean> iniOccupiedLevel){

        super( posXinit, posYinit, 1, iniOccupiedLevel,f);

        f.addKeyListener(new PointListener(this, keys));
    }

    public void setModificateurHorizontal(int modificateurHorizontal) {
        this.modificateurHorizontal = modificateurHorizontal;
    }

    public void setModificateurVertical(int modificateurVertical) {
        this.modificateurVertical = modificateurVertical;
    }

    @Override
    public void paintComponent(Graphics g){
        g.setColor(Color.RED);
        g.fillOval(0, 0, 50, 50);
    }

    public void display() {
        setBounds(posX, posY, 50, 50);
    }

    public void forward(double dTime){


        super.forward(dTime);
        if (posX< -getWidth()/2)
        {
            posX = solidScreen.getWidth()-getWidth()/2;
        }
        else if (posX > (solidScreen.getWidth()-getWidth()/2))
        {
            posX = getWidth()/2;
        }

        if (posY < 0 )
        {
            posY =0;
        }
        else if (posY > (solidScreen.getHeight()-getHeight()))
        {
            posY = solidScreen.getHeight()-getHeight();
        }

    }

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
            System.out.println ("y" + speed);
        }
        else if (speed < (-speedYLimit))
        {
            speed = -speedYLimit;
        }
        return speed;
    }

    public class PointListener implements KeyListener {

        private Point point;
        private ArrayList<Integer> keys;

        public PointListener(Point p, ArrayList k){
            point = p;
            keys = k;
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode()== keys.get(0)) {
                point.addForce(0,-2);
            }
            if(e.getKeyCode()== keys.get(1)) {
                point.addForce(0,2);
            }
            if(e.getKeyCode()== keys.get(2)) {
                point.addForce(-2,0);
            }
            if(e.getKeyCode()== keys.get(3)) {
                point.addForce(2,0);
            }
            if(e.getKeyCode()== keys.get(4))
            {
                point.removeForce(getSumForcesX(),getSumForcesY());
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if(e.getKeyCode()== keys.get(0)) {
                point.removeForce(0,-2);
            }
            if(e.getKeyCode()== keys.get(1)) {
                point.removeForce(0,2);
            }
            if(e.getKeyCode()== keys.get(2)) {
                point.removeForce(-2,0);
            }
            if(e.getKeyCode()== keys.get(3)) {
                point.removeForce(2,0);
            }
        }
    }
}
