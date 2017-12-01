package model;

import java.util.ArrayList;

/**
 * Created by user on 07.11.2017.
 */
public class Cluster {

    private ArrayList<PointObject> content;
    private int number;

    public Cluster(int number) {
        this.content = new ArrayList<>();
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void addPointObject(PointObject item) {
        this.content.add(item);
    }

    public int getPointIndexByOrder(int i) {
        if( i < this.content.size() ) {
            return this.content.get(i).getIndex();
        } else {
            System.out.println("can't get index");
            return -1;
        }
    }

    public void removePointObject(int i) {
        if( i < this.content.size() ) {
            this.content.remove(i);
        } else {
            System.out.println("can't remove p. #" + i + " claster # " + this.getNumber());
        }
    }

    public int getSize() { return this.content.size(); }

    public PointObject getPointObjectByOrder(int i) {
        return this.content.get(i);
    }

    @Override
    public String toString() {
        StringBuilder temp = new StringBuilder();
                temp.append("Cluster #" + number + " { ");
        for (int i = 0; i < this.getSize(); i++) {
            temp.append(this.getPointObjectByOrder(i).getIndex() + " ");
        }
        temp.append("}");
        return temp.toString();
    }
}

