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

    public Compound parseCompound(String compoundString) {
        List<Element> elements = new ArrayList<>();

        if(compoundString.equals("")){
            return null;
        }

        if(compoundString.contains("(")){
            int openParenthesisIndex = compoundString.indexOf("(");
            //int closingParenthesisIndex = getClosingParenthesisIndex(compoundString.substring(openParenthesisIndex));
            int closingParenthesisIndex = getClosingParenthesisIndex(compoundString);

            int endOfSubscriptIndex = closingParenthesisIndex + 1;

            while(endOfSubscriptIndex + 1 < compoundString.length() && Character.isDigit(compoundString.charAt(endOfSubscriptIndex + 1))){
                endOfSubscriptIndex++;
            }

            int mult = 1;
            if(closingParenthesisIndex + 1 == endOfSubscriptIndex){
                mult = Integer.parseInt(compoundString.charAt(closingParenthesisIndex + 1) + "");
            }else{
                mult = Integer.parseInt(compoundString.substring(closingParenthesisIndex + 1, endOfSubscriptIndex + 1));
            }


            Compound before = parseCompound(compoundString.substring(0,openParenthesisIndex));
            Compound middle = parseCompound(compoundString.substring(openParenthesisIndex + 1, closingParenthesisIndex));
            Compound after = parseCompound(compoundString.substring(endOfSubscriptIndex + 1));


            for(Element element : middle.getAllElements()){
                element.setSubscript(element.getSubscript() * mult);
            }

            Compound[] compounds = new Compound[]{before, middle, after};

            return combineCompounds(compounds);
        }

        int start = 0;
        for(int i = 1; i < compoundString.length(); i++){
            if(Character.isUpperCase(compoundString.charAt(i))){
                String elementString = compoundString.substring(start,i);
                Element element = parseElement(elementString);

                if(elements.contains(element)){
                    Element elementInList = getElementInList(element, elements);
                    elementInList.setSubscript(elementInList.getSubscript() + element.getSubscript());
                }else{
                    elements.add(element);
                }

                start = i;
            }
        }


        String elementString = compoundString.substring(start,compoundString.length());
        Element element = parseElement(elementString);

        if(elements.contains(element)){
            Element elementInList = getElementInList(element, elements);
            elementInList.setSubscript(elementInList.getSubscript() + element.getSubscript());
        }else{
            elements.add(element);
        }

        return new Compound(elements);
    }

    private int getClosingParenthesisIndex(String compoundString) {

        int openParenthesisCount = 0;
        for(int i = compoundString.indexOf("("); i < compoundString.length(); i++){
            if(compoundString.charAt(i) == '('){
                openParenthesisCount++;
            }else if(compoundString.charAt(i) == ')'){
                openParenthesisCount--;
            }

            if(openParenthesisCount == 0){
                return i;
            }
        }

        return -1;
    }

    private Compound combineCompounds(Compound[] compounds) {
        List<Element> elements = new ArrayList<>();

        for(int i = 0; i < compounds.length; i++){
            if(compounds[i] == null){
                continue;
            }
            for(Element element : compounds[i].getAllElements()){
                if(elements.contains(element)){
                    Element elementInList = getElementInList(element, elements);
                    elementInList.setSubscript(elementInList.getSubscript() + element.getSubscript());
                }else{
                    elements.add(element);
                }
            }
        }

        return new Compound(elements);
    }

    private Element getElementInList(Element target, List<Element> elements) {
        for(Element element: elements){
            if(element.equals(target)){
                return element;
            }
        }
        return null;
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
            subscript = Integer.parseInt(elementString.substring(subscriptIndex, elementString.length()));
            elementString = elementString.substring(0, subscriptIndex);
        }


        return new Element(elementString,subscript);
    }
}
