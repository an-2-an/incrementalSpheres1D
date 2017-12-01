package model;

/**
 * Created by user on 07.11.2017.
 */
public class PointObject {
    private double coordinate;
    private int index;

    public PointObject(int index, double coordinate) {
        this.coordinate = coordinate;
        this.index = index;
    }

    public double getCoordinate() {
        return coordinate;
    }

    public int getIndex() {
        return index;
    }
}
