import model.*;
import view.View;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by user on 07.11.2017.
 */
public class Controller {
    public static void main(String[] args) {
        int times = 1000;
        int size = 50;

        View view = new View();
        Matrix matrix = new Matrix(times,size);

        for (int i = 0; i < times; i++) {


            Field field = new Field(size, 100.0);
            StatesArray statesArray = new StatesArray();
//            view.showPoints(field);
//            view.showMatrix(field);
//            view.showStructure(field);

            statesArray.createStates(field);

            for (int j = 0; j < statesArray.getSize(); j++) {
                matrix.setElement(i,j,statesArray.getStateByOrder(j).getDistance());
            }

//       System.out.println(field.getClustersGSON());
//
//            for (int i = 0; i < statesArray.getSize(); i++) {
//                System.out.println(statesArray.getStateByOrder(i).getClastersQuantity() +
//                        " " + statesArray.getStateByOrder(i).getDistance());
//            }

//            statesArray.saveDataDat();
//            statesArray.saveDataXLS();
        }
//        view.showMatrix(matrix);
        matrix.saveDataXLS();
    }
}
