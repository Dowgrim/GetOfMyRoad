package Display;

import Engine.Player;
import Engine.Solid;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Created by Michael on 24/01/2016.
 */
public class DisplayPlayer extends DisplaySolid {

    public DisplayPlayer(Screen s, ArrayList<Integer> keys, Solid p){
        super(p);
        s.addKeyListener(new PointListener((Player)p, keys));
    }

    @Override
    public void paintComponent(Graphics g){
        g.setColor(Color.RED);
        g.fillOval(0, 0, 50, 50);
    }


    public class PointListener implements KeyListener {

        private Player player;
        private ArrayList<Integer> keys;

        public PointListener(Player p, ArrayList k){
            player = p;
            keys = k;
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode()== keys.get(0)) {
                player.addForce(0,-(Player.FORCEIMPULSE));
            }
            if(e.getKeyCode()== keys.get(1)) {
                player.addForce(0,Player.FORCEIMPULSE);
            }
            if(e.getKeyCode()== keys.get(2)) {
                player.addForce(-Player.FORCEIMPULSE,0);
            }
            if(e.getKeyCode()== keys.get(3)) {
                player.addForce(Player.FORCEIMPULSE,0);
            }
            if(e.getKeyCode()== keys.get(4))
            {
                player.removeForce(player.getSumForcesX(), player.getSumForcesY());
                player.setSpeedX(0);
                player.setSpeedY(0);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if(e.getKeyCode()== keys.get(0)) {
                player.removeForce(0,-Player.FORCEIMPULSE);
            }
            if(e.getKeyCode()== keys.get(1)) {
                player.removeForce(0,Player.FORCEIMPULSE);
            }
            if(e.getKeyCode()== keys.get(2)) {
                player.removeForce(-Player.FORCEIMPULSE,0);
            }
            if(e.getKeyCode()== keys.get(3)) {
                player.removeForce(Player.FORCEIMPULSE,0);
            }
        }
    }
}
