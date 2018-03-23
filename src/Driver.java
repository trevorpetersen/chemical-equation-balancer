import chem.ArrayMatrix;
import chem.Matrix;

public class Driver {
    public static void main(String[] args){
        //TODO Clean up matrix/arraymatrix
        //TODO Add some test cases
        //TODO Convert input into matrix

        ArrayMatrix matrix = new ArrayMatrix(3,4);

        matrix.setValue(0,0,1);
        matrix.setValue(0,1,0);
        matrix.setValue(0,2,-1);
        matrix.setValue(0,3,0);

        matrix.setValue(1,0,1);
        matrix.setValue(1,1,0);
        matrix.setValue(1,2,0);
        matrix.setValue(1,3,-2);

        matrix.setValue(2,0,0);
        matrix.setValue(2,1,2);
        matrix.setValue(2,2,0);
        matrix.setValue(2,3,-2);


        matrix.sort();
        System.out.println(matrix);

        //matrix.solve(new float[3]);
        matrix.gaussianElimination();
        matrix.getAns();


        System.out.println();

        System.out.println(matrix);

    }
}
