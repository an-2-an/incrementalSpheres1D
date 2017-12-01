package view;

import model.Cluster;
import model.Field;
import model.Matrix;

/**
 * Created by user on 07.11.2017.
 */
public class View {
    public void showPoints(Field field) {
        int size = field.getPoints().length;
        for (int i = 0; i < size; i++) {
            System.out.println("point#" + field.getPoints()[i].getIndex() +
                " " + field.getPoints()[i].getCoordinate());
        }
        System.out.println();
    }

    public void showCluster(Cluster cluster) {
        System.out.println(cluster.toString());
        /*System.out.print("cluster #" + cluster.getNumber() + "[");
        for (int j = 0; j < cluster.getSize(); j++) {
            System.out.print(cluster.getPointIndexByOrder(j) + " ");
        }
        System.out.print("]");
        System.out.println();*/
    }

    public void showStructure(Field field) {
        int size = field.getCurrentSize();
        for (int i = 0; i < size; i++) {
           showCluster(field.getClasterByOrder(i));
        }
        System.out.println();
    }

    public void showMatrix(Field field) {
        for (int i = 0; i < field.getMatrix().size(); i++) {
            System.out.println(field.getMatrix().get(i).toString());
        }
        System.out.println();
    }

    public void showMatrix(Matrix matrix) {
        for (int i = 0; i < matrix.getRows(); i++) {
            for (int j = 0; j < matrix.getCols(); j++) {
                System.out.print(matrix.getElementValue(i,j) + "\t");
            }
            System.out.println();
        }
    }

}
