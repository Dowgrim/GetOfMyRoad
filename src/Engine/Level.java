package Engine;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Timer;

/**
 * Created by Michael on 12/01/2016.
 */
public class Level {


    private List<Player> players;
    private List<Floortile> floortiles;

    private Dimension levelDimension;

    private double lastUpdateTime;


    public Level(Dimension dim){

        levelDimension = dim;

        lastUpdateTime = System.currentTimeMillis();

        players = new ArrayList<Player>();
        floortiles = new ArrayList<Floortile>();

        Thread t = new Thread(){
            public void run(){
                Timer time = new Timer("lol");
                time.schedule(new TaskScheduled(), 0, 6);
            }
        };
        t.start();

    }

    public Player playerInitialisation(int initPosX, int initPosY){
        ArrayList<Boolean> pointsLevels = new ArrayList<Boolean>();
        pointsLevels.add(false);
        pointsLevels.add(true);
        pointsLevels.add(true);
        pointsLevels.add(true);
        Player p1 = new Player(new Localisation(initPosX, initPosY), new Dimension(50, 50), pointsLevels, this);
        players.add(p1);
        return p1;
    }

    public Floortile floortileInitialisation(int initPosX, int initPosY, int sideSize){
        ArrayList<Boolean> floorLevels = new ArrayList<Boolean>();
        floorLevels.add(true);
        floorLevels.add(false);
        floorLevels.add(false);
        floorLevels.add(false);

        Floortile FloorTile1 = new Floortile (new Localisation(initPosX, initPosY), new Dimension(sideSize, sideSize), floorLevels, this);
        floortiles.add(FloorTile1);
        return FloorTile1;
    }

    public int getW() {
        return (int)levelDimension.getWidth();
    }

    public int getH() {
        return (int)levelDimension.getHeight();
    }

    public class TaskScheduled extends TimerTask {

        @Override
        public void run() {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            double dTime = (System.currentTimeMillis() - lastUpdateTime)*0.001;
            for(Player p : players){
                p.forward(dTime);
            }
            for(Floortile f : floortiles){
                f.forward(dTime);
            }
        }
    }


}
