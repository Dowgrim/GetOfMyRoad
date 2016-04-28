package Engine;

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

                double crossX = one.x() - two.x();
                double crossY = one.y() - two.y();

                Boolean oneLeft = one.x()<two.x();
                Boolean oneUp = one.y()<two.y();

                // calcule de la superposition des formes :
                if (oneLeft){
                    crossX =  crossX + one.w();
                }
                else {
                    crossX =  -crossX +two.w();
                }
                if ( oneUp)
                {
                    crossY = crossY + one.h();
                }
                else
                {
                    crossY = -crossY + two.h();
                }



                // on reregle la possition de celui avec le moins d'énergie
                if (solid1EC < solid2EC)
                {
                    // on reregle la pos x du solid 1

                    one.setPosX(one.x()+(oneLeft? -crossX:  +crossX));
                    one.setPosY(one.y()+(oneUp ? -crossY : +crossY));


                } else if (solid1EC > solid2EC)
                {
                    two.setPosX(two.x()+(oneLeft ? +crossX: -crossX));
                    two.setPosY(two.y()+(oneLeft? +crossY : -crossY));
                }
                //a energie egale, on les fait ce reculer tout les deux d'une distance egale
                else
                {
                    one.setPosX(one.x()+(oneLeft? -crossX:  +crossX)/2);
                    one.setPosY(one.y()+(oneUp ? -crossY : +crossY)/2);

                    two.setPosX(two.x()+(oneLeft ? +crossX: -crossX)/2);
                    two.setPosY(two.y()+(oneLeft? +crossY : -crossY)/2);
                }

            }
        colisionList.clear();

    }
    public void colisionAdder(List<Solid> colision)
    {
        colisionList.add(colision);
    }

}
