import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.Timer;

/**
 * Created by Michael on 12/01/2016.
 */
public class Ecran extends JFrame {

    List<Point> players;

    public Ecran(){
        super("Get of my Road !!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(360, 640);
        setLayout(null);
        setResizable(false);

        players = new ArrayList<Point>();

        Point p1 = new Point(this);
        players.add(p1);
        getContentPane().add(p1);


        Thread t = new Thread(){
            public void run(){
                Timer time = new Timer("lol");
                time.schedule(new TaskScheduled(), 0, 5);
            }
        };
        t.start();




        setVisible(true);

        while(true)
            p1.display();
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
