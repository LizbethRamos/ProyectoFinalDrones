/**
 *Lizbeth Ramos López    201749275 
 * FCC BUAP
 */
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.CyclicBarrier;

public class Dron extends Thread{
   
    private DibujaDrones panel;
    private int a = 1, b = 1, limInfX = 0, limSupX = 430, limInfY = 0, limSupY = 480, opcion;
    private DronG d;
    private boolean corriendo = true;
    //variables de sincronización
    private Semaphore s;       
    private Lock mutex;
    private Monitores monitor;
    final CyclicBarrier barrera;

    Dron(DibujaDrones panel, DronG d, int opcion){
        this.panel=panel;
        this.d = d;
        this.opcion = opcion;
        //variables de sincronización
        this.s = new Semaphore(1);
        this.mutex = new ReentrantLock();   
        this.monitor = new Monitores();
        this.barrera = new CyclicBarrier(1);
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
                switch (opcion){
                    case 0:
                        mutex.lock();
                        SC();
                        mutex.unlock();
                        break;
                    case 1:
                        s.acquire();
                        SC();
                        s.release();                        
                        break;
                    case 2:

                        break;
                    case 3:
                        monitor.put();
                        SC();
                        monitor.get();
                        break;
                    case 4:
                        barrera.await();//Se queda bloqueado hasta que todos los drones hagan esta llamada
                           SC();
                        barrera.await();
                        break;
                }
                panel.repaint();
                Thread.sleep((int)(Math.random()*15));
                if(!corriendo)
                    this.join();
            } catch (Exception ex) {
                Logger.getLogger(Dron.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    private void SC() {
        d.setFrame(d.getX() + a, d.getY() + b, 20, 20);       
        if (d.getY() > limSupY || d.getY() < limInfY){
            b=-b;
        }
        if (d.getX() > limSupX || d.getX()<limInfX){
            a=-a;
        }
    }
    public void parar(){
        this.corriendo = false;
    }
}
