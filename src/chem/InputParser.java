package chem;

import java.util.ArrayList;
import java.util.List;

public class InputParser {
    public InputParser(){

    }

    public ChemicalEquation parseChemicalEquation(String inputString){
        inputString.replace(" ", "");
        if(!inputString.contains("=")){
           return null;
        }

        String[] splitEquation = inputString.split("=");
        String[] reactantStrings = splitEquation[0].split("\\+");
        String[] productStrings = splitEquation[1].split("\\+");

        List<Compound> reactants = parseCompounds(reactantStrings);
        List<Compound> products = parseCompounds(productStrings);


        return new ChemicalEquation(reactants, products);
    }

    private List<Compound> parseCompounds(String[] compoundStrings) {
        List<Compound> compounds = new ArrayList<>();
        for(int i = 0; i < compoundStrings.length; i++){
            compounds.add(parseCompound(compoundStrings[i]));
        }
        return compounds;
    }

    private Compound parseCompound(String compoundString) {
        List<Element> elements = new ArrayList<>();

        int start = 0;
        for(int i = 1; i < compoundString.length(); i++){
            if(Character.isUpperCase(compoundString.charAt(i))){
                String elementString = compoundString.substring(start,i);
                elements.add(parseElement(elementString));

                start = i;
            }
        }


        String elementString = compoundString.substring(start,compoundString.length());
        elements.add(parseElement(elementString));

        return new Compound(elements);
    }

    private Element parseElement(String elementString) {
        int subscript = 1;
        int subscriptIndex = -1;
        for(int i = 0; i < elementString.length(); i++){
            if(Character.isDigit(elementString.charAt(i))){
                subscriptIndex = i;
                break;
            }
        }

        if(subscriptIndex != -1){
            subscript = Integer.parseInt(elementString.substring(subscript, elementString.length()));
            elementString = elementString.substring(0, subscriptIndex);
        }


        return new Element(elementString,subscript);
    }
}
