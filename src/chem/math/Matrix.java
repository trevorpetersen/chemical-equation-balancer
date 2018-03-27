package chem.math;

public interface Matrix {
    void setMatrix(float[][] newMatrix);
    int getNumRows();
    int getNumCols();

    float getValue(int i, int j);
    float[] getRow(int rowIndex);
    void setValue(int i, int j, float value);
    float[] solve(float[] b);
}
