import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Created by Michael on 12/01/2016.
 */
public class Point extends Solid {

    private int modificateurHorizontal = 0;
    private int modificateurVertical = 0;


    public Point(JFrame f, ArrayList<Integer> keys,int posXinit,int posYinit, ArrayList<Boolean> iniOccupiedLevel){

        super( posXinit, posYinit, 10, iniOccupiedLevel);

        f.addKeyListener(new PointListener(this, keys));
    }








    public void setModificateurHorizontal(int modificateurHorizontal) {
        this.modificateurHorizontal = modificateurHorizontal;
    }

    public void setModificateurVertical(int modificateurVertical) {
        this.modificateurVertical = modificateurVertical;
    }

    @Override
    public void paintComponent(Graphics g){
        g.setColor(Color.PINK);
        g.fillOval(0, 0, 50, 50);
    }

    public void display() {
        setBounds(posX, posY, 50, 50);
        //repaint();
    }

    public void forward(){
        if(posX <= 277 && modificateurHorizontal > 0 || posX >= -25 && modificateurHorizontal < 0) {
            posX += modificateurHorizontal;
        }
        if(posY <= 535 && modificateurVertical > 0 || posY >= -25 && modificateurVertical < 0) {
            posY += modificateurVertical;
        }
    }

    public class PointListener implements KeyListener {

        private Point point;
        private ArrayList<Integer> keys;

        public PointListener(Point p, ArrayList k){
            point = p;
            keys = k;
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode()== keys.get(0)) {
                point.setModificateurVertical(-2);
            }
            if(e.getKeyCode()== keys.get(1)) {
                point.setModificateurVertical(+2);
            }
            if(e.getKeyCode()== keys.get(2)) {
                point.setModificateurHorizontal(-2);
            }
            if(e.getKeyCode()== keys.get(3)) {
                point.setModificateurHorizontal(+2);
            }
            checkColision(point);


        }

        @Override
        public void keyReleased(KeyEvent e) {
            if(e.getKeyCode()== keys.get(0)) {
                point.setModificateurVertical(0);
            }
            if(e.getKeyCode()== keys.get(1)) {
                point.setModificateurVertical(0);
            }
            if(e.getKeyCode()== keys.get(2)) {
                point.setModificateurHorizontal(0);
            }
            if(e.getKeyCode()== keys.get(3)) {
                point.setModificateurHorizontal(0);
            }
        }
    }
}
