public class Site {
    static final int STOCK_INIT = 5;
    static final int STOCK_MAX = 10;
    static final int BORNE_SUP = 8;
    static final int BORNE_INF = 2;
    private int id_site;
    private int stock;
    private int stockCamion = 20;  // Exemple de nombre de vélos dans le camion
    private boolean isCamionPresent = false;  // Indique si le camion est présent

    public Site(int id_site) {
        this.stock = STOCK_INIT;
        this.id_site=id_site;
    }
    public int getId() {
        return this.id_site;
    }
    public synchronized void empruntVelo() {
       // System.out.println(Thread.currentThread().getName() + id_site+ " - Début de empruntVelo()");

        while (stock == 0 || isCamionPresent) {
            try {
                wait();  // Attendre qu'un vélo soit disponible ou que le camion soit parti
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Emprunter un vélo
        stock--;
        System.out.println(Thread.currentThread().getName() +  " a emprunté un vélo sur le site "+ id_site + " Stock actuel : " + stock);
        
        notifyAll();  // Réveiller d'autres clients ou le camion
    }

    public synchronized void depotVelo() {
      //  System.out.println(Thread.currentThread().getName() + " - Début de depotVelo()");

        while (stock == STOCK_MAX || isCamionPresent) {
            try {
                wait();  // Attendre qu'il y ait de la place pour déposer un vélo ou que le camion soit parti
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Déposer un vélo
        stock++;
        System.out.println(Thread.currentThread().getName() + " a déposé un vélo sur le site "+id_site +" Stock actuel : " + stock);
        
        notifyAll();  // Réveiller d'autres clients ou le camion
    }

    public synchronized void equilibreStock() {
        System.out.println(Thread.currentThread().getName() + " - Camion commence à équilibrer le stock sur le site " +id_site );

        isCamionPresent = true;  // Empêcher les clients d'utiliser le site pendant l'équilibrage

        if (stock > BORNE_SUP) {
            int surplus = stock - STOCK_INIT;
            stockCamion += surplus;   
            stock = STOCK_INIT;  //faire un calcul
            System.out.println("Camion a chargé " + surplus + " vélos. Stock réinitialisé à " + stock);
        } else if (stock < BORNE_INF && stockCamion > 0) {
            int deficit = STOCK_INIT - stock;
            int velosADeposer = Math.min(deficit, stockCamion);
            stock += velosADeposer;
            stockCamion -= velosADeposer;
            System.out.println("Camion a déposé " + velosADeposer + " vélos sur le site "+id_site + " Stock augmenté à " + " "+ stock);
        } else {
            System.out.println("Le stock est déjà équilibré à " + stock+ " sur le site "+id_site );
        }

        isCamionPresent = false;  // Le camion a fini, les clients peuvent utiliser le site
        notifyAll();  // Réveiller les clients en attente
    }
}
