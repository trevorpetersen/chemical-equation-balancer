package chem;

import java.rmi.activation.ActivationGroup_Stub;
import java.util.*;

public class ArrayMatrix  implements Matrix {
    private int rows, cols;
    private float[][] matrix;

    public ArrayMatrix(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
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
        return rows;
    }

    @Override
    public int getNumCols() {
        return cols;
    }

    @Override
    public float[] solve(float[] b) {
        if(b.length != cols){
            throw new RuntimeException("Matrix and vector size are incompatible");
        }

        float[] ans = new float[cols];

        for(int i = 0; i < 1000; i++){
            for(int j = 0; j < cols; j++){

                float sum = 0;
                for(int k = 0; k < cols; k++){
                    if(j != k){
                        sum = matrix[j][k] * ans[k];
                    }
                }

                ans[j] = (1/matrix[j][j]) * (b[j] - sum);
            }
        }


        System.out.println();
        for(int i = 0; i < cols; i++){
            System.out.println(ans[i]);
        }
        System.out.println();

        return ans;
    }

    public void gaussianElimination(){

        for(int i = 0; i < cols - 1 && i + 1 < rows; i++){
            sort();

            for(int j = i; j < rows - 1; j++){
                float mult;
                if(matrix[i][i] == 0){
                    mult = 0;
                }else{
                    mult = matrix[j+1][i] / matrix[i][i];
                }

                for(int k = i; k < cols; k++){
                    matrix[j+1][k] = matrix[j+1][k] - (mult * matrix[i][k]);
                }
            }
        }

        for(int  i = 0; i < rows; i++){
            float divisor = 1.0f;
            boolean hasFoundFirst = false;
            for(int j = 0; j < cols; j++){
                if(matrix[i][j] != 0 && ! hasFoundFirst){
                    divisor = matrix[i][j];
                    hasFoundFirst = true;
                }
                matrix[i][j] = matrix[i][j] / divisor;
            }
        }
    }

    public float[] getAns(){
        boolean[] isPivot = new boolean[cols];
        float[] ans = new float[cols];
        Arrays.fill(ans, -1.0f);


        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(matrix[i][j] != 0){
                    isPivot[j] = true;
                    break;
                }
            }
        }


        for(int i = 0; i < cols; i++){
            if(! isPivot[i]){
                int mult = getDenominatorsLCM(i);
                ans[i] = mult;
            }
        }

        for(int i = rows - 1; i >= 0; i--){
            float sum = 0;
            for(int j = cols - 1; j >= 0; j--){
                if(ans[j] == -1){
                    ans[j] = -sum;
                    break;
                }else{
                    sum = sum + (ans[j] * matrix[i][j]);
                }
            }
        }

        System.out.println("Ans:");
        for(int i = 0; i < cols; i++){
            System.out.println(ans[i]);
        }

        return ans;
    }

    private int getDenominatorsLCM(int columnNum) {
        int mult = 1;
        for(int i = 0; i < rows; i++){
            int count = 1;
            while( (count * matrix[i][columnNum]) - (int)(count * matrix[i][columnNum]) != 0){
                count++;
            }
            if(mult % count != 0){
                mult = mult * count;
            }
        }

        return mult;
    }

    public void sort(){
        PriorityQueue<Pair> queue = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair pair, Pair t1) {
                int one = (Integer) pair.getTwo();
                int two = (Integer) t1.getTwo();

                return one - two;
            }
        });

        int zeroCount = 0;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(matrix[i][j] == 0){
                    zeroCount++;
                }else{
                    break;
                }
            }
            queue.add(new Pair<float[], Integer>(matrix[i],zeroCount));
            zeroCount = 0;
        }

        float[][] newMatrix = new float[rows][cols];
        for(int i = 0; i < rows; i++){
            newMatrix[i] = (float[])queue.poll().getOne();
        }

        matrix = newMatrix;
    }

    private void swapRows(int row1, int row2){
        float[] tmpRow = matrix[row1];

        matrix[row1] = matrix[row2];
        matrix[row2] = tmpRow;
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