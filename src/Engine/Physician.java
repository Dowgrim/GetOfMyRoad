package Engine;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thebro on 24/04/16.
 */
public class Physician {
    private List<List<Solid>> colisionList;
    public Physician()
    {
        colisionList = new ArrayList<List<Solid>>();
    }
    public void colisionHandler()
    {
            for (List<Solid> colision : colisionList) {
                Solid one = colision.get(0);
                Solid two = colision.get(1);

                // resolution de la colision d'un point de vue énergétique :
                // si ils sont sur le même niveau
                // on cosidère que le choc est inelastique ( conservation de l'énergie)
                double solid1EC = one.getCineticEngergyTot();
                double solid2EC = two.getCineticEngergyTot();






                // on reregle la possition de celui avec le moins d'énergie
                if (solid1EC < solid2EC)
                {
                    backwarder(one,1,two,0);
                }

                else if (solid1EC > solid2EC)
                {
                    backwarder(two,1,one,0);
                }

                //a energie egale, on les fait ce reculer tout les deux d'une distance egale
                else
                {

                    backwarder(one,0.5,two,0.5);

                }

            }
        colisionList.clear();

    }
    public void colisionAdder(List<Solid> colision)
    {
        colisionList.add(colision);
    }


    private void backwarder(Solid one, double onePart, Solid two, double twoPart)
    {

        while (one.getBounds().intersects(two.getBounds()))
        {
            double dTime = 0.1;

            System.out.println("one old pos : " + one.x() +";" + one.y()
                    + " change one speedy" + one.speedY*dTime*onePart );

            one.setPosX(one.x()-(one.speedX*dTime)*onePart);
            one.setPosY(one.y()-(one.speedY*dTime)*onePart);

            System.out.println("one new pos : " + one.x() +";" + one.y());

            two.setPosX(two.x()-(two.speedX*dTime)*twoPart);
            two.setPosY(two.y()-(two.speedY*dTime)*twoPart);



        }
    }


     }