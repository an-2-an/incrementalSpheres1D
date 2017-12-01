package model;

/**
 * Created by user on 09.11.2017.
 */
public class State {

    private int clastersQuantity;
    private double distance;
    private String description;

    public State(int clastersQuantity, double distance, String description) {
        this.clastersQuantity = clastersQuantity;
        this.distance = distance;
        this.description = description;
    }

    public int getClastersQuantity() {
        return clastersQuantity;
    }

    public double getDistance() {
        return distance;
    }

    public String getDescription() {
        return description;
    }
}
