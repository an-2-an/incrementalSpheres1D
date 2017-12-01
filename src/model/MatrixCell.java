package model;

/**
 * Created by user on 08.11.2017.
 */
public class MatrixCell implements Comparable<MatrixCell> {
    private int vertexOneIndex;
    private int vertexTwoIndex;
    private double value;

    public MatrixCell(int vertexOneIndex, int vertexTwoIndex, double value) {
        this.vertexOneIndex = vertexOneIndex;
        this.vertexTwoIndex = vertexTwoIndex;
        this.value = value;
    }

    public int getVertexOneIndex() {
        return vertexOneIndex;
    }

    public int getVertexTwoIndex() {
        return vertexTwoIndex;
    }

    public double getValue() {
        return value;
    }


    @Override
    public int compareTo(MatrixCell mc) {
        if( this.getValue() > mc.getValue() )return 1;
        else if( this.getValue() < mc.getValue() ) return -1;
        else return 0;
    }

    @Override
    public String toString() {
        return "MatrixCell{" +
                "v1=" + vertexOneIndex +
                ", v2=" + vertexTwoIndex +
                ", value=" + value +
                '}';
    }
}
