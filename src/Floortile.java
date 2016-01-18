import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by ThePolyBro on 15/01/2016.
 */
public class Floortile extends Solid{


    public Floortile(JFrame f, int posXinit, int posYinit, ArrayList<Boolean> initOccupiedLevels)
    {
        super(posXinit,posYinit,0,initOccupiedLevels);

    }



    @Override
    public void paintComponent(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, 50, 50);
    }
    public void display() {
        setBounds(posX, posY, 50, 50);
        repaint();
    }
}
