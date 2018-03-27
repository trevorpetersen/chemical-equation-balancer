import chem.ChemicalEquation;
import chem.Compound;
import chem.InputParser;
import chem.math.Matrix;

import java.util.List;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args){
        getInput();
    }

    public static void getInput(){

        InputParser inputParser = new InputParser();
        Scanner inputScanner = new Scanner(System.in);

        System.out.print("Enter an equation: ");

        ChemicalEquation chemicalEquation = inputParser.parseChemicalEquation(inputScanner.nextLine());

        if(chemicalEquation == null){
            System.out.println("Unrecognized format. Please try again");
            getInput();
            return;
        }

        Matrix matrix = chemicalEquation.toMatrix();
        float[] b = new float[matrix.getNumCols()];
        float[] ans = matrix.solve(b);

        List<Compound> reactants = chemicalEquation.getReactants();
        List<Compound> products = chemicalEquation.getProducts();

        for(int i = 0; i < reactants.size(); i++){
            Compound compound = reactants.get(i);
            compound.setCoefficient((int)ans[i]);
        }

        for(int i = 0; i < products.size(); i++){
            Compound compound = products.get(i);
            compound.setCoefficient((int)ans[reactants.size() + i]);
        }

        System.out.println(chemicalEquation);
    }
}
