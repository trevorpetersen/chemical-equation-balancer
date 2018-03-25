package chem;

import chem.math.Matrix;

import java.util.Collection;
import java.util.List;

public class ChemicalEquation {
    List<Compound> reactants;
    List<Compound> products;

    public ChemicalEquation(List<Compound> reactants, List<Compound> products){
        this.reactants = reactants;
        this.products = products;
    }

    public Matrix toMatrix(){
        //TODO


        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < reactants.size(); i++){
            Compound compound = reactants.get(i);
            sb.append(compound);

            if(i + 1 != reactants.size()){
                sb.append("+");
            }
        }

        sb.append("=");

        for(int i = 0; i < products.size(); i++){
            Compound compound = products.get(i);
            sb.append(compound);

            if(i + 1 != products.size()){
                sb.append("+");
            }
        }

        return sb.toString();
    }
}
