import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;
import java.util.Timer;
import java.util.function.BooleanSupplier;

/**
 * Created by Michael on 12/01/2016.
 */
public class Screen extends JFrame {

    //TODO : C'est moche mais j'en ai mare de trimbaler ce truc

    private List<Point> players;
    private List<Floortile> floortiles;
    private List<List> tilesColumns = new ArrayList<>();
    private JLayeredPane layers = new JLayeredPane();

    private double lastUpdateTime;
    public Screen(){
        super("Get of my Road !!");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(360, 640);
        setLayout(null);
        setResizable(false);
        layers.setSize(getWidth(),getHeight());
        getContentPane().add(layers);

        lastUpdateTime = System.currentTimeMillis();


        players = new ArrayList<Point>();
        floortiles = new ArrayList<Floortile>();


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
        for ( int i =0; i<4; i++)
        {
            int columnSize = getWidth()/4;
            tilesColumns.add(columnCreator(columnSize*i,columnSize,9));
        }

        setVisible(true);

        Thread t = new Thread(){
            public void run(){
                Timer time = new Timer("lol");
                time.schedule(new TaskScheduled(), 0, 6);
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

    public List<Floortile> columnCreator (int posX, double size, int lenght)
    {
        // permet de créé les listes colones de carelage
        List<Color> floorTilesColor = new ArrayList<>();
        floorTilesColor.add(Color.DARK_GRAY);
        floorTilesColor.add(Color.GRAY);
        floorTilesColor.add(Color.BLACK);
        int c = (int) (Math.random()*10)%3;
        List<Floortile> column = new ArrayList<>();
        column.add(floortileInitialisation(posX, (int) (size),floorTilesColor.get(c), (int) size));

        for ( int i = 1; i < lenght+1; i++)
        {
            column.add(floortileInitialisation(posX, (int) (size * (i+1)),floorTilesColor.get((i+c)%3), (int) size));
            column.get(i).setCompanion(column.get(i-1));
        }
        column.get(0).setCompanion(column.get(lenght));
        return column;
    }
    public void playerInitialisation(ArrayList<Integer> keys, int initPosX, int initPosY){
        ArrayList<Boolean> pointsLevels = new ArrayList<Boolean>();
        pointsLevels.add(false);
        pointsLevels.add(true);
        pointsLevels.add(true);
        pointsLevels.add(true);
        Point p1 = new Point(this, keys, initPosX, initPosY, pointsLevels);
        players.add(p1);
        layers.add(p1,JLayeredPane.PALETTE_LAYER,1);
    }

    public Floortile floortileInitialisation(int initPosX, int initPosY, Color colorInit, int sideSize){
        ArrayList<Boolean> floorLevels = new ArrayList<Boolean>();
        floorLevels.add(true);
        floorLevels.add(false);
        floorLevels.add(false);
        floorLevels.add(false);
        Floortile FloorTile1 = new Floortile (initPosX, initPosY, floorLevels,this,colorInit,sideSize);
        floortiles.add(FloorTile1);
        layers.add(FloorTile1,JLayeredPane.DEFAULT_LAYER,10);
        return FloorTile1;
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
