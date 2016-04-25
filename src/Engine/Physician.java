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
            System.out.println("handling colision ");
            for (List<Solid> colision : colisionList) {
                System.out.println("in for");
                Solid solid1 = colision.get(0);
                Solid solid2 = colision.get(1);

                // resolution de la colision d'un point de vue énergétique :
                // si ils sont sur le même niveau
                // on cosidère que le choc est inelastique ( conservation de l'énergie)
                double solid1EC = solid1.getCineticEngergyTot();
                double solid2EC = solid2.getCineticEngergyTot();
                // calcule de la superposition des formes :
                double crossX = solid1.w() / 2 + solid2.w() / 2
                        - Math.abs(solid1.centerX() - solid2.centerX());
                double crossY = solid1.h() / 2 + solid2.h() / 2
                        - Math.abs(solid1.centerY() - solid2.centerY());
                // on reregle la possition de celui avec le moins d'énergie
                if (solid1EC < solid2EC) {
                    // on reregle la pos x du solid 1
                    solid1.setPosX(solid1.x() + (solid1.x() < solid2.x() ? -crossX : +crossX));
                    solid1.setPosY(solid1.y() + (solid1.y() < solid2.y() ? -crossY : +crossY));

                } else if (solid1EC > solid2EC) {
                    solid2.setPosX(solid2.x() + (solid2.x() < solid1.x() ? -crossX : +crossX));
                    solid2.setPosY(solid2.y() + (solid2.y() < solid1.y() ? -crossY : +crossY));
                }
                //a energie egale, on les fait ce reculer tout les deux d'une distance egale
                else {
                    solid1.setPosX(solid1.x() + ((solid1.x() < solid2.x() ? -crossX : +crossX) / 2));
                    solid1.setPosY(solid1.y() + ((solid1.y() < solid2.y() ? -crossY : +crossY) / 2));

                    solid2.setPosX(solid2.x() + ((solid2.x() < solid1.x() ? -crossX : +crossX) / 2));
                    solid2.setPosY(solid2.y() + ((solid2.y() < solid1.y() ? -crossY : +crossY) / 2));
                }

            }
        colisionList.clear();

    }
    public void colisionAdder(List<Solid> colision)
    {
        colisionList.add(colision);
    }

}
