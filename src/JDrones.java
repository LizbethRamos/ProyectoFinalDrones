/**
 *Lizbeth Ramos López    201749275 
 * FCC BUAP
 */
import java.awt.geom.Line2D;
import javax.swing.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;

public class JDrones extends javax.swing.JFrame {
    private DibujaDrones panel;
    private NumDron n;
    private BufferedImage ima;
    private ArrayList<Dron> drones;
    private ArrayList<DronG> dronesG;
    private ArrayList<Line2D> lineasG;
    private DronG aux;
    private int cX = 450;
    private int cY = 500;
    private int opcSincronizacion = -1;

    public JDrones() {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        initComponents();
        try{
            ima = ImageIO.read(new File ("CU.PNG"));
        }catch(IOException e){e.printStackTrace();}
        n = new NumDron();
        drones = new ArrayList<Dron>();
        dronesG = new ArrayList<DronG>();
        lineasG = new ArrayList<Line2D>();
        panel = new DibujaDrones(ima, dronesG, lineasG);
        panel.setBounds(0,0,cX,cY);
        add(panel);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        OpcionMutex = new javax.swing.JMenuItem();
        OpcionSemaforo = new javax.swing.JMenuItem();
        OpcionVC = new javax.swing.JMenuItem();
        OpcionMonitores = new javax.swing.JMenuItem();
        OpcionBarreras = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jMenu1.setText("Algoritmo de Sincronización");

        OpcionMutex.setText("Mutex");
        OpcionMutex.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpcionMutexActionPerformed(evt);
            }
        });
        jMenu1.add(OpcionMutex);

        OpcionSemaforo.setText("Semaforo");
        OpcionSemaforo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpcionSemaforoActionPerformed(evt);
            }
        });
        jMenu1.add(OpcionSemaforo);

        OpcionVC.setText("Variable de condición");
        OpcionVC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpcionVCActionPerformed(evt);
            }
        });
        jMenu1.add(OpcionVC);

        OpcionMonitores.setText("Monitores");
        OpcionMonitores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpcionMonitoresActionPerformed(evt);
            }
        });
        jMenu1.add(OpcionMonitores);

        OpcionBarreras.setText("Barreras");
        OpcionBarreras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpcionBarrerasActionPerformed(evt);
            }
        });
        jMenu1.add(OpcionBarreras);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Opciones Dron");

        jMenuItem1.setText("Nuevo");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 455, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 499, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        if(n.getConta() > 1 && n.getConta() < 10){
            crearDron();
            crearDron();
            lineasG.add(new Line2D.Double());
        }
        if(n.getConta() == 0 || n.getConta() == 1){
            if(n.getConta() == 1)
                lineasG.add(new Line2D.Double());
            crearDron();
        }
        calcularLimites();
        for(Dron d: drones){
           if(!d.isAlive())
            try{
                d.start();
            }catch(IllegalThreadStateException ex){ex.printStackTrace();}               
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void OpcionMutexActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpcionMutexActionPerformed
        System.out.println("entra mutex");
        iniciar();
        this.opcSincronizacion = 0;
    }//GEN-LAST:event_OpcionMutexActionPerformed

    private void OpcionSemaforoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpcionSemaforoActionPerformed
        System.out.println("entra semaforo");
        iniciar();
        this.opcSincronizacion = 1;
    }//GEN-LAST:event_OpcionSemaforoActionPerformed

    private void OpcionVCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpcionVCActionPerformed
        System.out.println("entra VC");
        iniciar();
        this.opcSincronizacion = 2;
    }//GEN-LAST:event_OpcionVCActionPerformed

    private void OpcionMonitoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpcionMonitoresActionPerformed
        System.out.println("entra Monitores");
        iniciar();
        this.opcSincronizacion = 3;
    }//GEN-LAST:event_OpcionMonitoresActionPerformed

    private void OpcionBarrerasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpcionBarrerasActionPerformed
        System.out.println("Entra barreras");
        iniciar();
        this.opcSincronizacion = 4;
    }//GEN-LAST:event_OpcionBarrerasActionPerformed

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JDrones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JDrones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JDrones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JDrones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JDrones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem OpcionBarreras;
    private javax.swing.JMenuItem OpcionMonitores;
    private javax.swing.JMenuItem OpcionMutex;
    private javax.swing.JMenuItem OpcionSemaforo;
    private javax.swing.JMenuItem OpcionVC;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    // End of variables declaration//GEN-END:variables

    private void calcularLimites() {
        int x, y;
        switch (n.getConta()){
            case 1:
                x = (cX - 20); y = (cY - 20);
                break;
            case 2:
                x = (cX - 20)/2; y = (cY - 20);
                break;
            default:
                x = (cX - 20)/2; y = (cY - 20)/(n.getConta()/2);
                break;                
        }
        int t = 0;
        for(int w = 0; w < (cX - 20); w+= x){
            for(int z = 0; z < (cY - 20); z += y){
                dronesG.get(t).setFrame(w,z,20,20);
//                dronesG.get(t).setFrame((int)(Math.random()*x+w),(int)(Math.random()*y+z), 20, 20);
                drones.get(t).setLimInfXInfY(w, z);
                drones.get(t).setLimSupXSupY(w + x, z + y);                        
                drones.get(t).setAB();
                t++;
            }
        }
        if(n.getConta()>1){
            t = 0;
            lineasG.get(t).setLine(cX/2, 0, cX/2, cY);
            t++;
            for(int z = y; z < (cY - 20); z+=y){
                lineasG.get(t).setLine(0, z, cX, z);
                t++;
            }
        }
    }

    private void crearDron() {
        n.setConta(n.getConta() +1);
        aux = new DronG();
        dronesG.add(aux);
        drones.add(new Dron(panel,aux, opcSincronizacion));
    }
    
    private void iniciar(){
            for( Dron d: drones)
                d.parar();
            dronesG.clear();
            drones.clear();
            lineasG.clear();
            n.setConta(0);
            cX = 450;
            cY = 500; 
    }
}
