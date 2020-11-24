
/**
 *
 * @author Beth
 */
public class Dibuja extends Thread{
    
    private DibujaDrones p;
    public Dibuja(DibujaDrones p){
        this.p = p;
    }
    @Override
    public void run(){
        while(true){
            p.repaint();
        }
    }
}
