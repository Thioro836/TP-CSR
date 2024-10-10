public class Client extends Thread {
  
   private Site siteDep;
   private Site siteArr;
   public Client (Site siteDep, Site siteArr){
      this.siteDep = siteDep;  
      this.siteArr = siteArr;

   }
    
 
   public void run() {
     try {
      siteDep.empruntVelo();
      Thread.sleep(100); 
      siteArr.depotVelo();
     } catch (InterruptedException e) {
      // Gestion de l'interruption, en général il est recommandé de rétablir l'état d'interruption du thread
      Thread.currentThread().interrupt();
  } catch (Exception e) {
      // Autres erreurs potentielles
      e.printStackTrace();
  }
}
}
