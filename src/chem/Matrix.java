package chem;

public interface Matrix {
    float getValue(int i, int j);
    void setValue(int i, int j, float value);
    int getNumRows();
    int getNumCols();
    float[] solve(float[] b);
}
