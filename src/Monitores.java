/**
 *
 * @author Beth
 */
public class Monitores {
    private int recurso = 0;
    private boolean hayRecurso = false;
    
    public synchronized int acquire(){
        while(hayRecurso == false){
            try{
                wait();
            }catch(InterruptedException e){}
        }
        notifyAll();
        hayRecurso = false;
        return recurso;
    }
    public synchronized void release(){
        while(hayRecurso){
            try{
                wait();
            }catch(InterruptedException e){}
        }
        recurso = 1;
        hayRecurso = true;
        notifyAll();
    }
}
