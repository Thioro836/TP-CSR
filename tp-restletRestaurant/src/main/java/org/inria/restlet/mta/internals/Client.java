package org.inria.restlet.mta.internals;

import java.util.Random;

import org.inria.restlet.mta.database.Restaurant;

public class Client extends Thread {
    private Buffet buffet;
    private StandCuisson standCuisson;
    private Restaurant restaurant;
    private StatusClient status;
    private String name;
    private int id_;

    public Client(String name,Buffet buffet, StandCuisson standCuisson, Restaurant restaurant) {
        this.name=name;
        this.buffet = buffet;
        this.standCuisson = standCuisson;
        this.restaurant = restaurant;
        this.status = StatusClient.WAITING_TO_ENTER;
        
    }

    public void run() {
        try {
            restaurant.entrerRestaurant();
            setStatus(StatusClient.AT_THE_BUFFET);
            buffet.seServir();
            Thread.sleep(300);
            setStatus(StatusClient.WAITING_FOR_THE_COOK);
            standCuisson.attendreCuisson();
            standCuisson.recupererPlat();
            setStatus(StatusClient.EATING);
            Thread.sleep(2000);
            restaurant.sortirRestaurant();
            setStatus(StatusClient.OUT);
        } catch (Exception e) {
            // gestion de l'interruption
            Thread.currentThread().interrupt();
        }
    }

    public String getNom() {
        return this.name;
    }

    public void setNom(String name) {
        this.name = name;
    }

    public int getIdentifiant() {
        return id_;
    }

    public void setIdentifiant(int id) {
        this.id_ = id;
    }

    public synchronized StatusClient getStatus() {
        return this.status;
    }

    public synchronized void setStatus(StatusClient newState) {
        this.status = newState;
    }
}
