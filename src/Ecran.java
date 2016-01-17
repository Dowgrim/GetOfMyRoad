import javax.swing.*;
import java.awt.event.KeyEvent;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.Timer;

/**
 * Created by Michael on 12/01/2016.
 */
public class Ecran extends JFrame {

    List<Point> players;
    List<Floortile> floortiles;

    public Ecran(){
        super("Get of my Road !!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(360, 640);
        setLayout(null);
        setResizable(false);

        players = new ArrayList<Point>();
        floortiles = new ArrayList<Floortile>();

        ArrayList<Integer> keys1 = new ArrayList<Integer>();
        keys1.add(KeyEvent.VK_UP);
        keys1.add(KeyEvent.VK_DOWN);
        keys1.add(KeyEvent.VK_LEFT);
        keys1.add(KeyEvent.VK_RIGHT);
        // niveaux occupé:
        ArrayList<Boolean> levels1 = new ArrayList<>();
        levels1 . add(false);
        for( int i =1; i < 3; i++)
        {
            levels1.add(true);
        }

        Point p1 = new Point(this, keys1, 100,100,levels1);
        players.add(p1);
        getContentPane().add(p1);

        ArrayList<Integer> keys2= new ArrayList<Integer>();
        keys2.add(KeyEvent.VK_Z);
        keys2.add(KeyEvent.VK_S);
        keys2.add(KeyEvent.VK_Q);
        keys2.add(KeyEvent.VK_D);
        // occupied levels :
        ArrayList<Boolean> levels2 = new ArrayList<>();
        levels2 . add(false);
        for( int i =1; i < 3; i++)
        {
            levels2.add(true);
        }

        Point p2 = new Point(this, keys2,50,50, levels2);
        players.add(p2);
        getContentPane().add(p2);
        ArrayList<Boolean> levelFloor = new ArrayList<>();
        levelFloor.add(true);
        for( int i =1; i < 3; i++)
        {
            levelFloor.add(false);
        }
        Floortile FloorTile1 = new Floortile ( this, 0,0,levelFloor);
        floortiles.add(FloorTile1);
        getContentPane().add(FloorTile1);

        Thread t = new Thread(){
            public void run(){
                Timer time = new Timer("lol");
                time.schedule(new TaskScheduled(), 0, 5);
            }
        };
        t.start();




        setVisible(true);

        while(true) {
            p2.display();
            p1.display();
        }
    }

    public class TaskScheduled extends TimerTask {

        @Override
        public void run() {
            for(int i = 0; i< players.size(); i++){
                players.get(i).forward();
            }
        }
    }

}
