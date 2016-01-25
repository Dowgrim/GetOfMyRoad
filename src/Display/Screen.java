package Display;

import Engine.Level;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by Michael on 24/01/2016.
 */
public class Screen extends JFrame {

    private Level level;
    private ArrayList<DisplaySolid> solids = new ArrayList<DisplaySolid>();


    private JLayeredPane layers = new JLayeredPane();

    public Screen(Level l){
        super("Get of my Road !!");

        level = l;

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(level.getW(), level.getH());
        setLayout(null);
        setResizable(false);
        layers.setSize(getWidth(),getHeight());
        getContentPane().add(layers);

        ArrayList<Integer> keys1 = new ArrayList<Integer>();
        keys1.add(KeyEvent.VK_UP);
        keys1.add(KeyEvent.VK_DOWN);
        keys1.add(KeyEvent.VK_LEFT);
        keys1.add(KeyEvent.VK_RIGHT);
        //pour arreter les pions
        keys1.add(KeyEvent.VK_SPACE);
        DisplayPlayer dp1 = new DisplayPlayer(this, keys1, level.playerInitialisation(0, 0));
        solids.add(dp1);
        layers.add(dp1, JLayeredPane.PALETTE_LAYER, 1);

        ArrayList<Integer> keys2 = new ArrayList<Integer>();
        keys2.add(KeyEvent.VK_Z);
        keys2.add(KeyEvent.VK_S);
        keys2.add(KeyEvent.VK_Q);
        keys2.add(KeyEvent.VK_D);
        // pour arréter les pions
        keys2.add(KeyEvent.VK_SPACE);
        DisplayPlayer dp2 = new DisplayPlayer(this, keys2, level.playerInitialisation(50, 50));
        solids.add(dp2);
        layers.add(dp2, JLayeredPane.PALETTE_LAYER, 1);

        int columnSize = getWidth()/4;

        new DisplayTileColumn(columnSize, columnSize, 25, level, layers, this);

        setVisible(true);

        while(true){
            diplay();
        }

    }

    public void addSolids(DisplaySolid solid){
        solids.add(solid);
    }

    private void diplay() {
        for(DisplaySolid d : solids){
            d.display();
        }
    }
}
