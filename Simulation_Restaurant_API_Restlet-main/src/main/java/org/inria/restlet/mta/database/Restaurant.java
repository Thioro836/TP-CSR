package org.inria.restlet.mta.database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.inria.restlet.mta.internals.Buffet;
import org.inria.restlet.mta.internals.Client;
import org.inria.restlet.mta.internals.Cuisinier;
import org.inria.restlet.mta.internals.Employe;
import org.inria.restlet.mta.internals.StandCuisson;
import org.inria.restlet.mta.internals.StatusClient;

/**
 *
 * In-memory database
 *
 * @author ctedeschi
 * @author msimonin
 *
 */
public class Restaurant {

    /* Constantes de la simulation */
    int NB_Places_dispo = 25;
    private static final int NB_CLIENTS = 40;
    // private Client[] clients = new Client[NB_CLIENTS];
    private List<Client> clients;
    private Cuisinier cuisinier;
    private Employe employe;
    /* Objets partagés */
    private final Buffet buffet = new Buffet();
    private final StandCuisson standCuisson = new StandCuisson();
    private int clientCount_;
    private String name;

    public Restaurant() {

        /* Instanciation de l'employe */
        employe = new Employe(buffet);
        /* Instanciation du cuisinier */
        cuisinier = new Cuisinier(standCuisson);

        /* Instanciation des clients */
        clients = new ArrayList<>();
        for (int i = 0; i < NB_CLIENTS; i++) {
            Client client = new Client(name, buffet, standCuisson, this);
            client.setIdentifiant(clientCount_);
            client.setNom("Client " + (i + 1));
            clientCount_++;

        }
    }

    /* Gérer l'accès au restaurant */
    public synchronized StatusClient entrerRestaurant() {

        while (NB_Places_dispo == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

        }
        NB_Places_dispo--;
        System.out.println(Thread.currentThread().getName() + " entre dans le restaurant." + NB_Places_dispo);
        return StatusClient.WAITING_TO_ENTER;
    }

    /* Libérer de la place après avoir mangé */
    public synchronized StatusClient sortirRestaurant() {
        NB_Places_dispo++;
        notifyAll();
        System.out.println(Thread.currentThread().getName() + " quitte le restaurant." + NB_Places_dispo);
        return StatusClient.OUT;
    }

    public void demarrer() {

        try {
            // for (Client client : clients) {
            // client.start();
            // }
            employe.start();
            cuisinier.start();

            for (Client client : clients) {
                client.join(); // Attendre que tous les clients aient terminé
            }
            employe.join();
            cuisinier.join();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized Client createClient(String name) {
        Client client = new Client(name, buffet, standCuisson, this);
        clients.add(client);
        client.start();
        return client;

    }

    public List<Client> getClients() {
        return clients;
    }

}
