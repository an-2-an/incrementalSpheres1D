package model;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by user on 07.11.2017.
 */
public class Field {

    private int size;
    private double length;
    private PointObject[] points;
    private ArrayList<Cluster> clusters;
    private ArrayList<MatrixCell> matrix;
    Random random;

    public Field(int size, double length) {
        this.size = size;
        this.length = length;
        this.random = new Random();
        this.points = new PointObject[size];
        this.clusters = new ArrayList<>();
        this.matrix = new ArrayList<>();
        Cluster Temp;
        double temp;

        for ( int i = 0; i < size; i++ ) {
            temp = random.nextDouble() * length;
            points[i] = new PointObject(i, temp);
            Temp = new Cluster(i);
            Temp.addPointObject(this.points[i]);
            this.clusters.add(Temp);
        }

        makeMatrix();
        this.matrix.sort(MatrixCell::compareTo);
    }

    private void makeMatrix() {
        double distance;
        for (int i = 0; i < size-1; i++) {
            for (int j = i+1; j < size; j++) {
                distance = Math.abs(this.getPoints()[i].getCoordinate() - this.getPoints()[j].getCoordinate());
                this.matrix.add(new MatrixCell(i,j,distance));
            }
        }
    }

    public Cluster getClasterByOrder(int i) {
        if( i < this.clusters.size() ) {
            return this.clusters.get(i);
        } else {
            System.out.println("can't get cluster #" + i);
            return null;
        }
    }

    public Cluster getClusterByNumber(int number) {
        for(Cluster cluster : this.clusters) {
            if( cluster.getNumber() == number ) return cluster;
        }
        return null;
    }

    public void joinClastersByNumbers(int n1, int n2) {
        Cluster clusterAcceptor;
        Cluster clusterDonator;
        PointObject temp;
        if( n1 < n2 ) {
            clusterAcceptor = this.getClusterByNumber(n1);
            clusterDonator = this.getClusterByNumber(n2);
        } else {
            clusterAcceptor = this.getClusterByNumber(n2);
            clusterDonator = this.getClusterByNumber(n1);
        }
        for (int i = 0; i < clusterDonator.getSize(); i++) {
            temp = clusterDonator.getPointObjectByOrder(i);
            clusterAcceptor.addPointObject(temp);
        }
        this.clusters.remove(clusterDonator);
    }

    public int getClustersNumberByPointIndex(int pointIndex) {

        for (int i = 0; i < this.getCurrentSize(); i++) {
            for (int j = 0; j < this.getClasterByOrder(i).getSize(); j++) {
                if( this.getClasterByOrder(i).getPointIndexByOrder(j) == pointIndex) {
                    return this.getClasterByOrder(i).getNumber();
                }
            }
        }
        return -1;
    }

    public boolean areTwoPointsBelongDifferentClucters(int pointIndex1, int pointIndex2) {
        int numOfCluster1 = this.getClustersNumberByPointIndex(pointIndex1);
        int numOfCluster2 = this.getClustersNumberByPointIndex(pointIndex2);
        return (numOfCluster1 != numOfCluster2);
    }

    public int getCurrentSize() {
        return this.clusters.size();
    }

    public String getClustersGSON() {
        Gson gson = new Gson();
        return gson.toJson(this.clusters);
    }

    public State getState(double d) {
        String desc = this.getClustersGSON();
        return new State(this.getCurrentSize(),d,desc);
    }

    public double getLength() {
        return length;
    }

    public PointObject[] getPoints() { return points; }

    public ArrayList<MatrixCell> getMatrix() { return matrix; }


}
