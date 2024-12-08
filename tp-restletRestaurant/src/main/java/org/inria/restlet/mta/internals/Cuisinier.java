package org.inria.restlet.mta.internals;
public class Cuisinier extends Thread {
    private StandCuisson standCuisson;

    public Cuisinier(StandCuisson standCuisson) {
        this.standCuisson = standCuisson;
    }

    public void run() {
        try {
            standCuisson.cuirePlat();
        } catch (Exception e) {

        }

    }

}
