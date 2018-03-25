package chem.math;

public interface Matrix {
    float getValue(int i, int j);
    void setValue(int i, int j, float value);
    int getNumRows();
    int getNumCols();
    float[] getRow(int rowIndex);
    float[] solve(float[] b);
    void setMatrix(float[][] newMatrix);
}
