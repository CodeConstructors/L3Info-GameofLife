package Controleur;

import java.util.logging.Level;
import java.util.logging.Logger;

class auto extends Thread {

    private Controleur controleur;
    private boolean paused = true;
    private final Object lock = new Object();
    /**<b> run </b>
     * Effectue la boucle de jeu. Appelle nextMove et attend 
     * pour une durée predefinie dans les parametres
     */
    public void run() {
        paused = false;
        while (true) {
            
            //Je ne suis pas certain du fonctionnement du lock mais : 
            /* Le thread suit l'objet lock, ils sont synchroniser
            La fonction lock.wait() est bloquante et attend que lock.notifyAll()
            soit appeller par un autre thread (ici le thread principal ) 
            Cela permet de mettre en pause le thread sans l'interompre
            */
            synchronized (lock) {
                if (paused) {
                    try {
                        lock.wait();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(auto.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        this.controleur.nextMove();
                        Thread.sleep(Static.TEMPS_PAUSE);
                    } catch (InterruptedException ex) {
                        System.err.println("auto.java: run() thread.sleep");
                        Logger.getLogger(auto.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    public auto(Controleur c) {
        this.controleur = c;
        this.setDaemon(true);
    }

    public void arret() {
        this.paused = true;
    }

    public void play() {
        synchronized (lock) {
            this.paused = false;
            lock.notifyAll();
        }
    }

    public boolean getEtat() {
        return this.paused;
    }
}
