import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Michael on 12/01/2016.
 */
public class Point extends JPanel {

    private int modificateurHorizontal = 0;
    private int modificateurVertical = 0;

    protected int posX = 10;
    protected int posY = 50;

    public Point(JFrame f){
        f.addKeyListener(new PointListener(this));
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
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
        g.fillOval(25, 25, 50, 50);
    }

    public void display() {
        setBounds(posX, posY, 100, 100);
        repaint();
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

        public PointListener(Point p){
            point = p;
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            //TODO Vérif sorti d'écran
            switch(e.getKeyCode()){
                case KeyEvent.VK_UP: {
                    point.setModificateurVertical(-2);
                    break;
                }
                case KeyEvent.VK_DOWN: {
                    point.setModificateurVertical(+2);
                    break;
                }
                case KeyEvent.VK_LEFT: {
                    point.setModificateurHorizontal(-2);
                    break;
                }
                case KeyEvent.VK_RIGHT:{
                    point.setModificateurHorizontal(+2);
                    break;
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            //TODO Vérif sorti d'écran
            switch(e.getKeyCode()){
                case KeyEvent.VK_UP: {
                    point.setModificateurVertical(0);
                    break;
                }
                case KeyEvent.VK_DOWN: {
                    point.setModificateurVertical(0);
                    break;
                }
                case KeyEvent.VK_LEFT: {
                    point.setModificateurHorizontal(0);
                    break;
                }
                case KeyEvent.VK_RIGHT:{
                    point.setModificateurHorizontal(0);
                    break;
                }
            }
        }
    }
}
