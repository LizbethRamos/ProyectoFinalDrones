
import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
   
public class DibujaDrones extends JPanel{  
    private BufferedImage ima;
    private ArrayList<DronG> drones;            
    private ArrayList<Line2D> lineas;
    DibujaDrones(BufferedImage ima, ArrayList<DronG> drones, ArrayList<Line2D> lineas){
        this.ima=ima;
        this.drones = drones;
        this.lineas = lineas;
    }
   
    @Override
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        Dimension d = getSize();        
        Image Imagen = createImage(d.width, d.height);
        Graphics2D offG = (Graphics2D)Imagen.getGraphics();
        offG.drawImage(ima, 0, 0,522, 497, null);
        offG.setColor(Color.black);
        for(DronG dron : drones){
            offG.fill(dron);
        }
        for(Line2D l : lineas){
            offG.draw(l);
        }
        g2.drawImage(Imagen, 0, 0, null);
    }

    @Override
    public void update(Graphics g){
        paint(g);
    }    
}
