package Engine;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thebro on 24/04/16.
 */
public class Physician extends Thread{

    protected static ArrayList<Solid> SOLIDLIST = new ArrayList<Solid>();

    private List<List<Solid>> colisionList;

    public Physician()
    {
        colisionList = new ArrayList<List<Solid>>();
        start();
    }

    /**
     *
     */
    public void colisionHandler()
    {

        if(colisionList.isEmpty()){
            return;
        }
        synchronized (colisionList) {
            for (List<Solid> colision : colisionList) {
                Solid one = colision.get(0);
                Solid two = colision.get(1);

                // resolution de la colision d'un point de vue énergétique :
                // si ils sont sur le même niveau
                // on cosidère que le choc est inelastique ( conservation de l'énergie)
                double solid1EC = one.getCineticEngergyTot();
                double solid2EC = two.getCineticEngergyTot();


                // on reregle la possition de celui avec le moins d'énergie
                if (solid1EC < solid2EC) {
                    backwarder(one, 1, two, 0);
                } else if (solid1EC > solid2EC) {
                    backwarder(two, 1, one, 0);
                }

                //a energie egale, on les fait ce reculer tout les deux d'une distance egale
                else {

                    backwarder(one, 0.5, two, 0.5);

                }
            }
        }
        colisionList.clear();
    }
    public void colisionAdder(List<Solid> colision)
    {
        synchronized (colisionList) {
            colisionList.add(colision);
        }
    }

    public void checkColision(Solid solid){
        // on fait une AABB autour de notre objet pour detecter la colision
        Rectangle checkingRectangle = solid.getBounds();
        Rectangle otherRectangle;
        int j;
        // on navigue dans les solid pour vérifier les colisions
        for (int i = 0; i < SOLIDLIST.size(); i++) {
            if (!(SOLIDLIST.get(i) == solid)) {
                j = 0;
                //sont-ils sur le même niveau  ?
                while (j < solid.occupiedLevel.size()) {
                    if (solid.occupiedLevel.get(j) == SOLIDLIST.get(i).occupiedLevel.get(j)) {
                        j = solid.occupiedLevel.size();
                        otherRectangle = new Rectangle(SOLIDLIST.get(i).getBounds());
                        // si les 2 AABB des 2 objects se rencontre
                        if (checkingRectangle.intersects(otherRectangle)) {
                            java.util.List<Solid> colision = new ArrayList<Solid>();
                            //TODO modifier sauvegarde des colisions
                            colision.add(solid);
                            colision.add(SOLIDLIST.get(i));
                            colisionAdder(colision);
                        }
                    }
                    j++;
                }
            }
        }
    }


    private void backwarder(Solid one, double onePart, Solid two, double twoPart)
    {
        while (one.getBounds().intersects(two.getBounds()))
        {
            double dTime = 0.01;

            System.out.println("one old pos : " + one.x() +";" + one.y()
                    + " change one speedy" + one.speedY*dTime*onePart );

            synchronized (one) {
                one.setPosX(one.x() - (one.speedX * dTime) * onePart);
                one.setPosY(one.y() - (one.speedY * dTime) * onePart);
            }

            System.out.println("one new pos : " + one.x() +";" + one.y());

            synchronized (two) {
                two.setPosX(two.x() - (two.speedX * dTime) * twoPart);
                two.setPosY(two.y() - (two.speedY * dTime) * twoPart);
            }
        }
    }

    @Override
    public void run(){
        while(true){

            System.out.println("yolo");
            colisionHandler();
        }
    }

}