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
    // la masse d'un élément, si 0 masse pas comparable
    protected int mass ;
    // donné pour l'accélération et la vitesse:
    protected int speedX = 00;
    protected int speedY = 00;
    protected int accelX = 00;
    protected int accelY = 00;


    // liste des forces extérieures pour les calculs du vecteur accélération
    // Chaque élément de extforce est une liste avec comme premier élément x et deuxieme y des vecteur forces concerné
    private ArrayList<List<Integer>> appliedForces;
    // niveau dans l'environement : sol, bas ,milieu, haut, (0,1,2,3)
    private ArrayList<Boolean> occupiedLevel ;


    public Solid( int posXinit, int posYinit, int massInit, ArrayList<Boolean> initialOccupiedLevel){
        super();
        mass = massInit;
        posX = posXinit;
        posY = posYinit;
        occupiedLevel = initialOccupiedLevel;
        SOLIDLIST.add(this);
        appliedForces = new ArrayList<List<Integer>>();
        occupiedLevel = initialOccupiedLevel;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void addForce( List newForce)
    {
        appliedForces.add(newForce);
        updateAceleration();
    }
    protected void updateAceleration ()
    {

        int sumX= 0;
        int sumY= 0;
        for (int i = 0; i< appliedForces.size(); i ++)
        {
            sumX += appliedForces.get(i).get(0);
        }
        if( sumX >( mass* 0.5))
        {
            sumX += mass* 0.3 ;
        }
        else
        {
            sumX=0;
        }
        accelX = sumX/ mass;

        for (int i = 0; i< appliedForces.size() ; i++)
        {
            sumY+= appliedForces.get(i).get(0);
        }

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
        System.out.println("Colision !");
    }

    public void forward(){
        checkColision();
    }



}
