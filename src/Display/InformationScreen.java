package Display;

import Engine.Level;
import Engine.Player;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thebro on 26/04/16.
 */
public class InformationScreen extends JFrame {
    private List<JLabel> informations;
    private Level level;
    public InformationScreen(Level l)
    {
        super("Information");

        level = l ;
        int i = 0 ;
        informations = new ArrayList<JLabel>();
        while (i < l.getPlayers().size())
        {
            JLabel pos = new JLabel();
            pos.setText(" Player" + i+ " x : " + String.valueOf(l.getPlayers().get(i).x())+
                    " ; y : " + String.valueOf(l.getPlayers().get(i).y()));
            System.out.println("lab el player " +i +"pos : " + i*40);
            pos.setBounds(0,i*50,10000,40);
            informations.add(pos);
            this.add(pos);
            i ++;

        }
        setSize(100,200);
        setVisible(true);
    }
    public void update()
    {
        display();
    }
    private void display()
    {
        int i = 0 ;
        while ( i< informations.size())
        {
            informations.get(i).setText(" Player" + i+ " x : " + String.valueOf(level.getPlayers().get(i).x())+
                    " ; y : " + String.valueOf(level.getPlayers().get(i).y()));
            i++;
        }
    }
}


