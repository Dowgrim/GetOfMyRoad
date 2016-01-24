package Engine;

/**
 * Created by Michael on 24/01/2016.
 */
public class Localisation {

    private double x;
    private double y;

    public Localisation(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
