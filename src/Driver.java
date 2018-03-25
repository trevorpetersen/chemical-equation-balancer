import chem.ChemicalEquation;
import chem.InputParser;
import chem.math.ArrayMatrix;

import java.util.Scanner;

public class Driver {
    public static void main(String[] args){
        /*TODO Add matrix ref to solve strat, solve with b (sort b in sort), use b in getAnsw,
         try to remove set matrix, duplicate matrix so I am not modifying OG data
         */
        //TODO Add vector class?

        //TODO Clean up matrix/arraymatrix
        //TODO Add some test cases
        //TODO Convert input into matrix
        //TODO Move matrix to another package

        InputParser inputParser = new InputParser();
        Scanner inputScanner = new Scanner(System.in);

        System.out.print("Enter an equation: ");

        ChemicalEquation chemicalEquation = inputParser.parseChemicalEquation(inputScanner.nextLine());

        System.out.println(chemicalEquation);


        /*

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


        //System.out.println(matrix);

        //matrix.solve(new float[3]);
        //matrix.gaussianElimination();
        //matrix.getAns();

        matrix.solve(new float[matrix.getNumCols()]);

        //System.out.println();

        //System.out.println(matrix);
        */

    }
}
