package chem;

import chem.math.ArrayMatrix;
import chem.math.Matrix;

import java.util.*;

public class ChemicalEquation {
    List<Compound> reactants;
    List<Compound> products;

    public ChemicalEquation(List<Compound> reactants, List<Compound> products){
        this.reactants = reactants;
        this.products = products;
    }

    public List<Compound> getReactants(){
        return reactants;
    }

    public List<Compound> getProducts(){
        return products;
    }

    public Matrix toMatrix(){
        Set<Element> allElements = new HashSet<>();

        for(Compound compound : reactants){
            for(Element element : compound.getAllElements()){
                allElements.add(element);
            }
        }

        List<int[]> equations = new ArrayList<>();

        for(Element element : allElements){
            int[] equation = new int[reactants.size() + products.size()];

            for(int i = 0; i < reactants.size(); i++){
                Compound compound = reactants.get(i);
                if(compound.contains(element)){
                    equation[i] = compound.getElement(element).getSubscript();
                }
            }

            for(int i = 0; i < products.size(); i++){
                Compound compound = products.get(i);
                if(compound.contains(element)){
                    equation[reactants.size() + i] = -compound.getElement(element).getSubscript();
                }
            }

            equations.add(equation);
        }

        if(equations.size() == 0){
            return null;
        }else{
            Matrix matrix = new ArrayMatrix(equations.size(), equations.get(0).length);

            for(int i = 0; i < equations.size(); i++){
                int[] row = equations.get(i);
                for(int j = 0; j < row.length; j++){
                    matrix.setValue(i,j,row[j]);
                }
            }
            return matrix;
        }
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
