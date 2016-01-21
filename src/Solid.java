import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

/**
 * Created by ThePolyBro on 15/01/2016.
 */
public class Solid extends JPanel{

    private static ArrayList<Solid> SOLIDLIST = new ArrayList<Solid>();

    protected int posX = 00;
    protected int posY = 00;
    private int lastPosX = 00;
    private int lastPosY = 00;
    // la masse d'un élément, si 0 masse pas comparable
    // on vas l'utilisé comme priorité pour l'instant
    protected int mass ;
    // donné pour l'accélération et la vitesse:
    protected double speedX = 00;
    protected double speedY = 00;
    protected double accelX = 00;
    protected double accelY = 00;
    protected Screen solidScreen;
    private int sumForcesX = 0;
    private int sumForcesY = 0;

    // liste des forces extérieures pour les calculs du vecteur accélération
    // Chaque élément de extforce est une liste avec comme premier élément x et deuxieme y des vecteur forces concerné
    // niveau dans l'environement : sol, bas ,milieu, haut, (0,1,2,3)
    private ArrayList<Boolean> occupiedLevel ;


    public Solid( int posXinit, int posYinit, int massInit, ArrayList<Boolean> initialOccupiedLevel, Screen solidScreenInit){
        super();
        mass = massInit;

        posX = posXinit;
        posY = posYinit;

        occupiedLevel = initialOccupiedLevel;
        SOLIDLIST.add(this);
        occupiedLevel = initialOccupiedLevel;
        solidScreen = solidScreenInit;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getMass(){return mass;}
    public void setPosX(int posX) {
        this.lastPosX = posX;
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.lastPosY = posY;
        this.posY = posY;
    }

    public void addForce(int forceX,int forceY)
    {
        sumForcesY += forceY;
        sumForcesX += forceX;
        System.out.println("sumForce X : "+ sumForcesX+"\n sumForceY : "+sumForcesY);

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
    public void checkColision()
    {
        Rectangle checkingRectangle = this.getBounds();
        Rectangle otherRectangle;

        for (int i = 0; i< SOLIDLIST.size(); i++)
        {
            if(!(SOLIDLIST.get(i) == this)) {
                otherRectangle = SOLIDLIST.get(i).getBounds();
                if (checkingRectangle.intersects(otherRectangle)) {
                    collisionHandler(SOLIDLIST.get(i));
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
            int difX = S.getPosX()-getPosX();
            int difY = S.getPosY()-getPosY();
            S.moveTo(difX,difY);
        }

    }

    public void forward(double dTime){
        speedX =this.processSpeedX(dTime);
        speedY =this.processSpeedY(dTime);

        posX += dTime *speedX;
        posY += dTime *speedY;


        checkColision();
    }
    public void backward()
    {
        setPosX(lastPosX);
        setPosY(lastPosY);
    }
    public void moveTo(int dirX, int dirY)
    {
        setPosX(getPosX()+dirX);
        setPosY(getPosY()+dirY);
    }


}
