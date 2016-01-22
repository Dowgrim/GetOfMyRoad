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
    private TileColumn tileColumn;
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
        if (companionTile != null) {

            int compPosY = companionTile.getPosY();
            if (compPosY <= -companionTile.getHeight()) {
                posY = compPosY + getHeight();
                if (compPosY - posY > getHeight()) {
                    posY = compPosY - getHeight();
                }
            }
        }
    }
    public void setTileColumn(TileColumn tileColumn)
    {
        this.tileColumn = tileColumn;
    }

    public void setFloorSpeed(double floorSpeed) {
        this.floorSpeed = floorSpeed;
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
        if(posY > solidScreen.getHeight()) {

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
