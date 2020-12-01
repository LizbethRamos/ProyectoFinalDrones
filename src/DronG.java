/**
 *Lizbeth Ramos López    201749275 
 * FCC BUAP
 */
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

public class DronG extends Ellipse2D{
    private double x, y;
    
    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public double getWidth() {
        return 20;
    }

    @Override
    public double getHeight() {
        return 20;
    }

    @Override
    public boolean isEmpty() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setFrame(double x, double y, double width, double height) {
        this.x = x;
        this.y =  y;
    }

    @Override
    public Rectangle2D getBounds2D() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
