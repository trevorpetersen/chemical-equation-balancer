package chem.math;

import chem.math.solver.GaussianEliminationStrategy;
import chem.math.solver.SolveStrategy;

public class ArrayMatrix  implements Matrix {
    private int rows, cols;
    private float[][] matrix;
    private SolveStrategy solveStrategy;

    public ArrayMatrix(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        matrix = new float[rows][cols];

        solveStrategy = new GaussianEliminationStrategy();
    }

    public ArrayMatrix(int rows, int cols, SolveStrategy solveStrategy){
        this.rows = rows;
        this.cols = cols;
        this.solveStrategy = solveStrategy;
        matrix = new float[rows][cols];
    }

    @Override
    public float getValue(int i, int j) {
        return matrix[i][j];
    }

    @Override
    public void setValue(int i, int j, float value) {
        matrix[i][j] = value;
    }

    @Override
    public int getNumRows() {
        if(matrix == null || matrix.length == 0){
            return 0;
        }else{
            return matrix.length;
        }
    }

    @Override
    public int getNumCols() {
        if(matrix == null || matrix.length == 0){
            return 0;
        }else{
            return matrix[0].length;
        }
    }

    @Override
    public float[] solve(float[] b) {
        return solveStrategy.solve(this, b);
    }

    @Override
    public float[] getRow(int rowIndex) {
        return matrix[rowIndex];
    }

    @Override
    public void setMatrix(float[][] newMatrix) {
        this.matrix = newMatrix;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(j != cols - 1){
                    sb.append(matrix[i][j] + " ");
                }else{
                    sb.append(matrix[i][j] + " ");
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}