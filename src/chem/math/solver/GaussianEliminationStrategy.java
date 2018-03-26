package chem.math.solver;

import chem.math.Matrix;
import chem.math.Pair;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class GaussianEliminationStrategy implements SolveStrategy {
    @Override
    public float[] solve(Matrix matrix, float[] b) {
        int numRows = matrix.getNumRows();
        int numCols = matrix.getNumCols();

        for(int i = 0; i < numCols - 1 && i + 1 < numRows; i++){
            sort(matrix, b);

            for(int j = i; j < numRows - 1; j++){
                float mult;
                if(matrix.getValue(i,i) == 0){
                    mult = 0;
                }else{
                    mult = matrix.getValue(j+1,i) / matrix.getValue(i, i);
                }

                for(int k = i; k < numCols; k++){
                    matrix.setValue(j+1,k, matrix.getValue(j+1, k) - (mult * matrix.getValue(i, k)));
                }
            }
        }

        for(int  i = 0; i < numRows; i++){
            float divisor = 1.0f;
            boolean hasFoundFirst = false;
            for(int j = 0; j < numCols; j++){
                if(matrix.getValue(i,j) != 0 && ! hasFoundFirst){
                    divisor = matrix.getValue(i,j);
                    hasFoundFirst = true;
                }
                matrix.setValue(i, j, matrix.getValue(i,j) / divisor);
            }
        }

        return getAns(matrix, b);
    }


    public void sort(Matrix matrix, float[] b){
        int numRows = matrix.getNumRows();
        int numCols = matrix.getNumCols();

        PriorityQueue<Pair> queue = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair pair, Pair t1) {
                int one = (Integer) pair.getTwo();
                int two = (Integer) t1.getTwo();

                return one - two;
            }
        });

        PriorityQueue<Pair> queueB = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair pair, Pair t1) {
                int one = (Integer) pair.getTwo();
                int two = (Integer) t1.getTwo();

                return one - two;
            }
        });

        int zeroCount = 0;
        for(int i = 0; i < numRows; i++){
            for(int j = 0; j < numCols; j++){
                if(matrix.getValue(i, j) == 0){
                    zeroCount++;
                }else{
                    break;
                }
            }
            queue.add(new Pair<float[], Integer>(matrix.getRow(i),zeroCount));
            queueB.add(new Pair<Float, Integer>(b[i],zeroCount));
            zeroCount = 0;
        }

        float[][] newMatrix = new float[numRows][numCols];
        for(int i = 0; i < numRows; i++){
            newMatrix[i] = (float[])queue.poll().getOne();
            b[i] = (float) queueB.poll().getOne();
        }

        matrix.setMatrix(newMatrix);
    }

    public float[] getAns(Matrix matrix, float[] b){
        int numRows = matrix.getNumRows();
        int numCols = matrix.getNumCols();

        boolean[] isPivot = new boolean[numCols];
        float[] ans = new float[numCols];
        Arrays.fill(ans, -1.0f);


        for(int i = 0; i < numRows; i++){
            for(int j = 0; j < numCols; j++){
                if(matrix.getValue(i,j) != 0){
                    isPivot[j] = true;
                    break;
                }
            }
        }


        for(int i = 0; i < numCols; i++){
            if(! isPivot[i]){
                int mult = getDenominatorsLCM(matrix, i);
                ans[i] = mult;
            }
        }

        for(int i = numRows - 1; i >= 0; i--){

            //Skip past rows of all zero
            boolean rowIsAllZeros = true;
            for(int j = 0; j < numCols; j++){
                if(matrix.getValue(i,j) != 0){
                    rowIsAllZeros = false;
                    break;
                }
            }
            if(rowIsAllZeros){
                continue;
            }


            float sum = 0;
            for(int j = numCols - 1; j >= 0; j--){
                if(ans[j] == -1){
                    ans[j] = -sum + b[i];
                    break;
                }else{
                    sum = sum + (ans[j] * matrix.getValue(i,j));
                }
            }
        }

        return ans;
    }

    private int getDenominatorsLCM(Matrix matrix, int columnNum) {
        int numRows = matrix.getNumRows();
        int numCols = matrix.getNumCols();

        float mult = 1.0f;
        for(int i = 0; i < numRows; i++){
            int count = 1;
            while( (count * matrix.getValue(i,columnNum)) - (int)(count * matrix.getValue(i,columnNum)) != 0){
                count++;
            }

            if(mult % count == 0){
                continue;
            }else if(count % mult == 0){
                mult = count;
            }else{
                mult = mult * count;
            }
        }

        float mult2 = mult;
        while(mult2 != Math.round(mult2)){
            mult2 = mult2 + mult;
        }

        return (int)mult2;
    }

}
