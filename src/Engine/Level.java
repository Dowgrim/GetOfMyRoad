package Engine;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Timer;

/**
 * Created by Michael on 12/01/2016.
 */
public class Level {

    private boolean START = false;


    private List<Player> players;
    private List<Floortile> floortiles;

    private Dimension levelDimension;

    protected Physician levelPhysician;

    private double lastUpdateTime;

    public Level(Dimension dim){

        levelDimension = dim;

        levelPhysician = new Physician();

        lastUpdateTime = System.currentTimeMillis();

        players = new ArrayList<Player>();
        floortiles = new ArrayList<Floortile>();

        Thread t = new Thread(){
            public void run(){
                synchronized (MocheMaisSimplifieLaVie()) {
                    try {
                        MocheMaisSimplifieLaVie().wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Timer time = new Timer("lol");
                time.schedule(new TaskScheduled(), 0, 20);
            }
        };
        t.start();

    }

    private Level MocheMaisSimplifieLaVie(){
        return this;
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

    public List<Player> getPlayers() {
        return players;
    }

    public List<Floortile> getFloortiles() {
        return floortiles;
    }

    public void start() {

    }

    public class TaskScheduled extends TimerTask {

        @Override
        public void run() {
            double dTime = (System.currentTimeMillis() - lastUpdateTime)*0.001;
            lastUpdateTime = System.currentTimeMillis();

            for(Player p : players){
                p.forward(dTime);
            }
            for(Floortile f : floortiles){
                f.forward(dTime);
            }
            levelPhysician.colisionHandler();
        }
    }


}
