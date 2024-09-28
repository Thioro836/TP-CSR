// PENSEZ A INDIQUER PAR DES COMMENTAIRES LES MODIFICATIONS APPORTEES A CE SQUELETTE AU FUR
// ET A MESURE DE L'EVOLUTION DU CODE DEMANDEE DANS LE TP.

/**
 * Les objets instances de la classe Stock representent un ensemble de pieces,
 * empilees les unes sur les autres. Du fait de la disposition en piles, il n'est
 * pas possible que deux operateurs saisissent deux pieces au meme moment.
 *
 */
class Stock {
	/**
	 * Nombre de pieces dans la pile
	 */
    private int nbPieces;
    /**
     * Le nom du stock
     */
    private String nom;

    /**
     * Creer un nouvel objet instance de stock
     * @param nom Le nom du nouveau stock
     * @param nbPieces Le nombre de pieces initial
     */
    public Stock(String nom, int nbPieces) {
        this.nbPieces = nbPieces;
        this.nom = nom;
    }

    /**
     * Poser une piece sur le haut de la pile de pieces
     */
   //3.3 deux conditions distinctes (while pour deposer et notifyAll pour etre sure de reveiller un des deux  )
    public  synchronized void stocker() {
        while (nbPieces==0) {
            try {
                wait() ;
             } catch (Exception e) {
             
        }
    }
        nbPieces++;
        System.out.println("le thread courant est " + Thread.currentThread().getName() +" Le stock " + nom + " contient " + nbPieces + " piece(s).");
        notifyAll();
    }

    /**
     * Saisir une piece sur le haut de la pile de pieces
     */
    /*mettre un if que si on est certains de recevoir la pièce ,ici c'est le cas car on a un stock intermediaire et il n'y a que l'atelier 2 qui l"utilise
    sinon utiliser while lorsqu'on a deux ateliers qui attendent pour stocker
    3.3 deux conditions distinctes (while pour deposer et notifyAll pour etre sure de reveiller un des deux  )
    */
    public  synchronized void destocker() {
        
            while(nbPieces==0){
                try {
                wait() ;
             } catch (Exception e) {
                // TODO: handle exception
           }
        }
         nbPieces--;
        System.out.println("le thread courant est " + Thread.currentThread().getName() +" Le stock " + nom + " contient " + nbPieces + " piece(s).");

    
       
    }
        

    /**
     * Affiche l'etat de l'objet stock
     * on obtient pas le même affichage car c'est l'ordonnanceur qui choisit 
     */
    public  synchronized void afficher() {
        System.out.println("Le stock " + nom + " contient " + nbPieces + " piece(s).");
    }

}
