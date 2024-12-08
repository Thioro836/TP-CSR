
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Buffet {

    private Compartiment compartiments;

    public Buffet() {
        compartiments = new Compartiment();
    }

    public void seServir() {
        Random random = new Random();
        for (String compartiment : compartiments.getCompartiments()) {
            double quantiteDemandee = random.nextDouble(0, 0.1);
            compartiments.prendrePortion(compartiment, quantiteDemandee);

        }

    }

    public void reapprovisionner() {
        for (String compartiment : compartiments.getCompartiments()) {
            compartiments.reapprovisionnerCompartiment(compartiment);

        }
    }

}
