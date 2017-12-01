package model;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by user on 16.11.2017.
 */
public class Matrix {

    private int rows;
    private int cols;
    private double[][] matrix;
    private double[] averages;

    public Matrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        matrix = new double[rows][];
        for (int i = 0; i < matrix.length; i++) {
            matrix[i] = new double[cols];
        }
        averages = new double[cols];
    }

    public void setElement(int i, int j, double value) {
        this.matrix[i][j] = value;
    }

    public double getElementValue(int i, int j) {
        return matrix[i][j];
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public void saveDataXLS() {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("matrix");

        for (int i = 0; i < this.rows; i++) {
            Row row = sheet.createRow(i);
            for (int j = 0; j < this.cols; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue(this.matrix[i][j]);
            }
        }

        Sheet sheetAgregate = workbook.createSheet("average");
        this.calcAverages();
        Row rowAverage = sheetAgregate.createRow(0);
        for (int i = 0; i < this.cols; i++) {
            Cell cellAverage = rowAverage.createCell(i);
            cellAverage.setCellValue(this.averages[i]);
        }

        try {
            workbook.write(new FileOutputStream("matrix.xls"));
            workbook.close();
        } catch (IOException e) {
            System.out.println("can't get xls file");
        }
    }

    private void calcAverages() {
        for (int i = 0; i < this.cols; i++) {
            double sum = 0.0;
            for (int j = 0; j < this.rows; j++) {
                sum += this.matrix[j][i];
            }
            this.averages[i] = sum / rows;
        }
    }
}
