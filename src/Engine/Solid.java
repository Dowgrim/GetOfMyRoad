package Engine;

import java.awt.*;
import java.awt.List;
import java.util.*;

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

    protected int sumForcesX = 0;
    protected int sumForcesY = 0;

    private int forceMax = 300;
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
        // on met a jour les Lastpos afin de pouvoir reculer si il y as colision
        lastPos.setX(pos.getX());
        pos.setX(posX);
    }

    public void setPosY(double posY) {
        lastPos.setY(pos.getY());
        pos.setY(posY);
    }
    // Permet d'ajouter une valeur de force a la somme des forces
    public void addForce(int forceX,int forceY)
    {
        sumForcesX += forceX ;
        sumForcesY += forceY ;
    }
    // permet de diminuer le total des force d'une valeur de force
    public void removeForce(int forceX, int forceY)
    {
        sumForcesX += -forceX;
        sumForcesY += -forceY;
    }

    public int getSumForcesX(){return sumForcesX;}
    public int getSumForcesY(){return sumForcesY;}
    // on calcule la vitesse a partire de la somme des forces qui s'apliquent au solide
    public void processSpeedX(double dTime)
    {
        double speed = speedX + dTime * sumForcesX / mass;
        speedX = speed;
    }

    public void processSpeedY(double dTime) {
        double speed = speedY + dTime * sumForcesY / mass;

        speedY = speed;
    }


    public void setSpeedX(double speed)
    {
        speedX = speed;
    }

    public void setSpeedY(double speed)
    {
        speedY = speed;
    }
    // vérificateur de colision
    public void checkColision() {
        // on fait une AABB autour de notre objet pour detecter la colision
        Rectangle checkingRectangle = getBounds();
        Rectangle otherRectangle;
        int j;
        // on navigue dans les solid pour vérifier les colisions
        for (int i = 0; i < SOLIDLIST.size(); i++) {
            if (!(SOLIDLIST.get(i) == this)) {
                j = 0;
                //sont-ils sur le même niveau  ?
                while (j < occupiedLevel.size()) {
                    if (occupiedLevel.get(j) == SOLIDLIST.get(i).occupiedLevel.get(j)) {
                        j = occupiedLevel.size();
                        otherRectangle = new Rectangle(SOLIDLIST.get(i).getBounds());
                        // si les 2 AABB des 2 objects se rencontre
                        if (checkingRectangle.intersects(otherRectangle)) {
                            java.util.List<Solid> colision = new ArrayList<Solid>();
                            colision.add(this);
                            colision.add(SOLIDLIST.get(i));

                            level.levelPhysician.colisionAdder(colision);

                        }
                    }
                    j++;
                }
            }
        }
    }
    // réaction a la colision avec un solid
    private void collisionHandler(Solid S)
    {
        System.out.println("Colision");
        if (mass<=S.getMass())
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
        processSpeedX(dTime);
        processSpeedY(dTime);

        pos.setX(pos.getX() + dTime * speedX);
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

    public double centerX(){ return x()+(w()/2); }

    public double centerY() {return y()+(h()/2);}

    public double getCineticEnergyX() {
        return 1/2*mass*speedX*speedX;
    }
    public double getCineticEnergyY()
    {
        return 1/2*mass*speedY*speedY;
    }
    public double getCineticEngergyTot()
    {
        return Math.sqrt(Math.pow(getCineticEnergyX(),2)+Math.pow(getCineticEnergyY(),2));
    }
}
