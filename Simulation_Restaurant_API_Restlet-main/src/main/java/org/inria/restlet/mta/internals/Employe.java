package org.inria.restlet.mta.internals;
public class Employe extends Thread {
    private Buffet buffet;
     boolean isrun=true;
    public Employe(Buffet buffet) {
        this.buffet=buffet;
        this.setDaemon(true);
    }
    public void run(){
        try {   
          while (isrun) {
                buffet.reapprovisionner();
                Thread.sleep(1000);
            
        }
       
        
        } catch (InterruptedException e) {
            //gestion de l'interruption
              Thread.currentThread().interrupt();
          
        }
}

}
