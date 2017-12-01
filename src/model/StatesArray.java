package model;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;


/**
 * Created by user on 09.11.2017.
 */
public class StatesArray {

    private ArrayList<State> states;

    public StatesArray() {
        this.states = new ArrayList<>();
    }

    public State getStateByOrder(int num) {
        return states.get(num);
    }

    public void addState(State state) {
        this.states.add(state);
    }

    public int getSize() {
        return this.states.size();
    }

    public ArrayList<ChartPoint> getArrayForChart() {
        ArrayList<ChartPoint> temp = new ArrayList<>();
        temp.add(new ChartPoint(0,states.get(0).getClastersQuantity()));
        for (int i = 1; i < this.states.size(); i++) {
            temp.add(new ChartPoint(this.states.get(i).getDistance(),this.states.get(i).getClastersQuantity()+1));
            temp.add(new ChartPoint(this.states.get(i).getDistance(),this.states.get(i).getClastersQuantity()));
        }
        temp.add(new ChartPoint((this.states.get(this.states.size() - 1).getDistance() * 1.1), 1));
        return temp;
    }

    public void saveDataDat() {
        File file = new File("chart.dat");
        FileOutputStream fileOutputStream = null;
        PrintWriter  printWriter = null;
        ArrayList<ChartPoint> arrayList = this.getArrayForChart();

        try {
            fileOutputStream = new FileOutputStream(file);
            printWriter = new PrintWriter(fileOutputStream);
            for (int i = 0; i < arrayList.size(); i++) {
                printWriter.write(arrayList.get(i).getX() + "\t" + arrayList.get(i).getY() + "\n");
                printWriter.flush();
            }
            printWriter.close();
            fileOutputStream.close();

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }


    }

    public void saveDataXLS() {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("ChartData");

        ArrayList<ChartPoint> arrayList = this.getArrayForChart();
        for (int i = 0; i < arrayList.size(); i++) {
            Row row = sheet.createRow(i);
            Cell cellX = row.createCell(0);
            cellX.setCellValue(arrayList.get(i).getX());
            Cell cellY = row.createCell(1);
            cellY.setCellValue(arrayList.get(i).getY());
        }
        try {
            workbook.write(new FileOutputStream("chartdata.xls"));
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createStates(Field field) {
        this.addState(field.getState(0));
        int counter = 0;
        double distance = 0.0;
        while ( field.getCurrentSize() > 1 ) {
            MatrixCell temp = field.getMatrix().get(counter);
            distance = temp.getValue();
            int pi1 = temp.getVertexOneIndex();
            int pi2 = temp.getVertexTwoIndex();
            if( field.areTwoPointsBelongDifferentClucters(pi1,pi2) ) {
                int nc1 = field.getClustersNumberByPointIndex(pi1);
                int nc2 = field.getClustersNumberByPointIndex(pi2);
                field.joinClastersByNumbers(nc1, nc2);
                this.addState(field.getState(distance));
            }
//            view.showStructure(field);
            counter++;
        }
    }



}
