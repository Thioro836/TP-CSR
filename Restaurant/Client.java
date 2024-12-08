
public class Client extends Thread {
    private Buffet buffet;
    private StandCuisson standCuisson;
    private Restaurant restaurant;

    public Client(Buffet buffet, StandCuisson standCuisson, Restaurant restaurant) {
        this.buffet = buffet;
        this.standCuisson = standCuisson;
        this.restaurant = restaurant;

    }

    public void run() {
        try {
            restaurant.entrerRestaurant();
            buffet.seServir();
            Thread.sleep(300);

            standCuisson.entrerStand();
            standCuisson.recupererPlat();
            // mange
            Thread.sleep(2000);

            restaurant.sortirRestaurant();

        } catch (Exception e) {
            // gestion de l'interruption

        }

    }

}
