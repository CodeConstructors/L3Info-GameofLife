package Controleur;


import Controleur.Controleur;
import java.util.logging.Level;
import java.util.logging.Logger;

class auto extends Thread {
    private Controleur controleur;
    private boolean paused = true;
    private final Object lock = new Object();
    public void run(){
        paused = false;
        while(true){
             
                 synchronized(lock){
                     if(paused){
                             
                         
                        try {

                            lock.wait();
                        } catch (InterruptedException ex) {
                            Logger.getLogger(auto.class.getName()).log(Level.SEVERE, null, ex);
                        }
                     }else{
                            try {
                           this.controleur.nextMove();
                           Thread.sleep(1000);
                       } catch (InterruptedException ex) {
                           System.out.println("catch play");
                           Logger.getLogger(auto.class.getName()).log(Level.SEVERE, null, ex);
                       }
                     }
                    
                 }
                 System.out.println("test");
                     
             }
    }
    
    
    public auto(Controleur c){
         this.controleur = c;
         this.setDaemon(true);
         
    }
    
    public void  arret(){
        this.paused = true;
    }
    
     public void play(){
          synchronized(lock){
            this.paused = false;
            lock.notifyAll();
          }
        
    }
    public boolean getEtat(){
        return this.paused;
    }
}
