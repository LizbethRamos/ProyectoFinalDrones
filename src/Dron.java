
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.Semaphore;
public class Dron extends Thread{
   
    private DibujaDrones panel;
    private int a = 1;
    private int b = 1;
    private int limInfX = 0, limSupX = 430, limInfY = 0, limSupY = 480;
    private DronG d;
    Semaphore s;   
    Dron(DibujaDrones panel, Semaphore s, DronG d){
        this.panel=panel;
        this.s=s;
        this.d = d;
    }

    public void setAB(){
        this.a = 1;
        this.b = 1;
    }
    public void setLimInfXInfY(int limInfX, int limInfY) {
        this.limInfX = limInfX;
        this.limInfY = limInfY;
    }

    public void setLimSupXSupY(int limSupX, int limSupY) {
        this.limSupX = limSupX;
        this.limSupY = limSupY;
    }
      
    public void run(){
        while(true){
            try {
                s.acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(Dron.class.getName()).log(Level.SEVERE, null, ex);
            }
            d.setFrame(d.getX() + a, d.getY() + b, 20, 20);
            verificaLimites();
            panel.repaint();
            s.release();
            try{
                Thread.sleep((int)(Math.random()*15));
            }catch(Exception e){e.printStackTrace();}
        }
    }
    private void verificaLimites() {
               if (d.getY() > limSupY){
                   b=-b;
               }
                if (d.getY() < limInfY){
                   b=-b;
               }
               if (d.getX() > limSupX){
                   a=-a;
               }
               if (d.getX()<limInfX){
                   a=-a;
               }        
    }
}
