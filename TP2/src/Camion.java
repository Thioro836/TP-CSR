public class Camion extends Thread{
    
    private Site[] sites;
    boolean turn=true;
    int i=0;
    public Camion(Site[] sites) {
        this.sites=sites;
        setDaemon(true);
      
        //TODO Auto-generated constructor stub
    }
    public void run(){
        while (turn  ) {
            
         // Le camion continue à faire sa tournée
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    // TODO: handle exception
                }
                sites[i].equilibreStock();
                i++;
                if(i==5){
                    i=0;
                }
                
        }
    }

}
    

