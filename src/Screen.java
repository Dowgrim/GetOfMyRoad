import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.security.Key;
import java.util.*;
import java.util.Timer;

/**
 * Created by Michael on 12/01/2016.
 */
public class Screen extends JFrame {

    //TODO : C'est moche mais j'en ai mare de trimbaler ce truc
    public static ArrayList<Boolean> LEVELS = new ArrayList<Boolean>();

    private List<Point> players;
    private List<Floortile> floortiles;
    private double lastUpdateTime;
    public Screen(){
        super("Get of my Road !!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(360, 640);
        setLayout(null);
        setResizable(false);

        lastUpdateTime = System.currentTimeMillis();


        players = new ArrayList<Point>();
        floortiles = new ArrayList<Floortile>();
        LEVELS.add(false);
        LEVELS.add(true);
        LEVELS.add(true);
        LEVELS.add(true);

        ArrayList<Integer> keys1 = new ArrayList<Integer>();
        keys1.add(KeyEvent.VK_UP);
        keys1.add(KeyEvent.VK_DOWN);
        keys1.add(KeyEvent.VK_LEFT);
        keys1.add(KeyEvent.VK_RIGHT);
        //pour arreter les pions
        keys1.add(KeyEvent.VK_SPACE);
        playerInitialisation(keys1, 0, 0);


        ArrayList<Integer> keys2= new ArrayList<Integer>();
        keys2.add(KeyEvent.VK_Z);
        keys2.add(KeyEvent.VK_S);
        keys2.add(KeyEvent.VK_Q);
        keys2.add(KeyEvent.VK_D);
        // pour arréter les pions
        keys2.add(KeyEvent.VK_SPACE);

        playerInitialisation(keys2, 50, 50);

        //floortileInitialisation(150, -500);

        setVisible(true);

        Thread t = new Thread(){
            public void run(){
                Timer time = new Timer("lol");
                time.schedule(new TaskScheduled(), 0, 5);
            }
        };
        t.start();

        while(true){
            diplay();
        }
    }

    private void diplay() {
        for(Point p : players){
            p.display();
        }

        for(Floortile f : floortiles){
            f.display();
        }
    }


    public void playerInitialisation(ArrayList<Integer> keys, int initPosX, int initPosY){
        Point p1 = new Point(this, keys, initPosX, initPosY, LEVELS);
        players.add(p1);
        getContentPane().add(p1);
    }

    public void floortileInitialisation(int initPosX, int initPosY){
        Floortile FloorTile1 = new Floortile (initPosX, initPosY, LEVELS,this);
        floortiles.add(FloorTile1);
        getContentPane().add(FloorTile1);
    }

    public class TaskScheduled extends TimerTask {

        @Override
        public void run() {
            double dTime = (System.currentTimeMillis() - lastUpdateTime)*0.001;
            for(Point p : players){
                p.forward(dTime);
            }
            for(Floortile f : floortiles){
                f.forward(dTime);
            }
        }
    }


}
