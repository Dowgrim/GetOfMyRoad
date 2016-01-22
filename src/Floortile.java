import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by ThePolyBro on 15/01/2016.
 */
public class Floortile extends Solid{

    private double floorSpeed;
    private Color color;
    private int sideLenght;
    private Floortile companionTile;
    public Floortile(int posXinit, int posYinit, ArrayList<Boolean> initOccupiedLevels, Screen screen, Color colorInit, int size)
    {
        super( posXinit, posYinit, 15, initOccupiedLevels, screen);
        floorSpeed = 0.1 ;
        color = colorInit;
        sideLenght = size;
    }
    // chaque tile du carelage doit avoir un companion tile ,
    // qui est la tile précédente dans la liste, et qui permet de maintenir le carelage en place
    public void setCompanion(Floortile tile)
    {
        companionTile = tile;
    }
    public void checkCompanion()
    {
        double distance;
        posX = companionTile.getPosX();
        int compPosY = companionTile.getPosY();
        if (compPosY<= - companionTile.getHeight())
        {
            posY = compPosY+getHeight();
            if (compPosY-posY>getHeight())
            {
                posY = compPosY-getHeight();
            }
        }

    }

    @Override
    public void paintComponent(Graphics g){
        g.setColor(color);
        g.fillRect(0, 0, sideLenght,sideLenght);
    }

    public void display() {
        setBounds(posX, posY, sideLenght, sideLenght);
        repaint();
    }

    @Override
    public void forward(double dTime){
        if(posY > (Math.floor(solidScreen.getHeight()/sideLenght)+2)*sideLenght) {
            posY = -sideLenght*2;

        }
        checkCompanion();
        posY += 1;
        super.forward(dTime);
    }

    @Override
    public double processSpeedX(double dTime) {
            int speed = 0;
        return speed;
    }

    @Override
    public double processSpeedY(double dTime) {
        double speed = floorSpeed;
        return speed;
    }

    @Override
    public void checkColision() {

    }
}
