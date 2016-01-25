package Engine;

import java.awt.*;
import java.util.ArrayList;

import static java.lang.Math.abs;

/**
 * Created by ThePolyBro on 15/01/2016.
 */
public class Solid{

    private static ArrayList<Solid> SOLIDLIST = new ArrayList<Solid>();

    protected Level level;

    protected Localisation pos;
    private Localisation lastPos;

    protected Dimension dim;
    // la masse d'un élément, si 0 masse pas comparable
    // on vas l'utilisé comme priorité pour l'instant
    protected int mass ;
    // donné pour l'accélération et la vitesse:
    protected double speedX = 00;
    protected double speedY = 00;
    protected double accelX = 00;
    protected double accelY = 00;
    private int sumForcesX = 0;
    private int sumForcesY = 0;

    // liste des forces extérieures pour les calculs du vecteur accélération
    // Chaque élément de extforce est une liste avec comme premier élément x et deuxieme y des vecteur forces concerné
    // niveau dans l'environement : sol, bas ,milieu, haut, (0,1,2,3)
    private ArrayList<Boolean> occupiedLevel ;
    private Rectangle bounds;


    public Solid(Localisation posInit, Dimension dim,  int massInit, ArrayList<Boolean> initialOccupiedLevel, Level l){

        level = l;
        mass = massInit;

        pos = posInit;
        lastPos = posInit;
        this.dim = dim;

        occupiedLevel = initialOccupiedLevel;
        SOLIDLIST.add(this);
        occupiedLevel = initialOccupiedLevel;
    }

    public int getMass(){return mass;}

    public void setPosX(double posX) {
        lastPos.setX(pos.getX());
        pos.setX(posX);
    }

    public void setPosY(double posY) {
        lastPos.setY(pos.getY());
        pos.setY(posY);
    }

    public void addForce(int forceX,int forceY)
    {
        sumForcesY += forceY;
        sumForcesX += forceX;
    }

    public void removeForce(int forceX, int forceY)
    {
        sumForcesX += -forceX;
        sumForcesY += -forceY;
    }

    public int getSumForcesX(){return sumForcesX;}
    public int getSumForcesY(){return sumForcesY;}

    public double processSpeedX(double dTime)
    {
        double speed = speedX +dTime * sumForcesX / mass;
        return speed;
    }

    public double processSpeedY(double dTime) {
        double speed= speedY +dTime * sumForcesY / mass;
        return speed;
    }

    public void setSpeedX(double speed)
    {
        speedX = speed;
    }

    public void setSpeedY(double speed)
    {
        speedY = speed;
    }

    public void checkColision() {
        Rectangle checkingRectangle = getBounds();
        Rectangle otherRectangle;
        int j;
        for (int i = 0; i < SOLIDLIST.size(); i++) {
            if (!(SOLIDLIST.get(i) == this)) {
                j = 0;
                while (j < occupiedLevel.size()) {
                    if (occupiedLevel.get(j) == SOLIDLIST.get(i).occupiedLevel.get(j)) {
                        j = occupiedLevel.size();
                        otherRectangle = new Rectangle(SOLIDLIST.get(i).getBounds());
                        if (checkingRectangle.intersects(otherRectangle)) {
                            collisionHandler(SOLIDLIST.get(i));

                        }
                    }
                    j++;
                }
            }
        }
    }

    private void collisionHandler(Solid S)
    {
        System.out.println("Colision");
        if (mass<S.getMass())
        {
            this.backward();
        }
        else
        {
            /*
            int difX = S.getX()- pos.getX();
            int difY = S.getPosY()-getPosY();
            S.moveTo(difX,difY);
            */
        }

    }

    public void forward(double dTime){
        speedX =this.processSpeedX(dTime);
        speedY =this.processSpeedY(dTime);

        pos.setX(pos.getX() + dTime *speedX);
        pos.setY(pos.getY() + dTime * speedY);


        checkColision();
    }

    public void backward()
    {
        setPosX(lastPos.getX());
        setPosY(lastPos.getY());
    }
    public void moveTo(int dirX, int dirY)
    {
        setPosX(pos.getX()+dirX);
        setPosY(pos.getY()+dirY);
    }


    public Rectangle getBounds() {
        return new Rectangle((int) pos.getX(), (int) pos.getY(), (int) dim.getWidth(), (int) dim.getHeight());
    }

    public int x() {
        return (int)pos.getX();
    }

    public int y() {
        return (int)pos.getY();
    }

    public int w() {
        return (int)dim.getWidth();
    }

    public int h() {
        return (int)dim.getHeight();
    }
}
