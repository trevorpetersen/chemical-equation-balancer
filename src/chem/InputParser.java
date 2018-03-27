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

        if(splitEquation.length != 2){
            return null;
        }

        String[] reactantStrings = splitEquation[0].split("\\+");
        String[] productStrings = splitEquation[1].split("\\+");

        boolean invalidReactants = stringArrayContainsInvalidString(reactantStrings);
        boolean invalidProducts = stringArrayContainsInvalidString(productStrings);

        if(invalidReactants || invalidProducts){
            return null;
        }

        List<Compound> reactants = parseCompounds(reactantStrings);
        List<Compound> products = parseCompounds(productStrings);

        if(reactants == null || products == null){
            return null;
        }

        if(isInvalidEquation(reactants, products)){
            return null;
        }


        return new ChemicalEquation(reactants, products);
    }

    private boolean stringArrayContainsInvalidString(String[] stringArray) {
        for( int i = 0; i < stringArray.length; i++){
            String cur = stringArray[i];
            int parenthesisCount = 0;
            boolean hasSeenUpper = false;

            if(cur == null || cur.equals("") || cur.contains("()")){
                return true;
            }

            for(int j = 0; j < cur.length(); j++ ){
                if(cur.charAt(j) == '('){
                    parenthesisCount++;
                }

                if(cur.charAt(j) == ')'){
                    parenthesisCount--;
                    if(j + 1 >= cur.length() || !Character.isDigit(cur.charAt(j + 1))){
                        return true;
                    }
                }

                if(parenthesisCount < 0){
                    return true;
                }

                if(Character.isUpperCase(cur.charAt(j))){
                    hasSeenUpper = true;
                }

                if(Character.isLowerCase(cur.charAt(j)) && !hasSeenUpper){
                    return true;
                }

                if( !(Character.isAlphabetic(cur.charAt(j)) || Character.isDigit(cur.charAt(j)))){
                    if( !(cur.charAt(j) == '(' || cur.charAt(j) == ')') ){
                        return true;
                    }
                }
            }

            if(parenthesisCount != 0){
                return true;
            }
        }
        return false;
    }

    private boolean isInvalidEquation(List<Compound> reactants, List<Compound> products) {
        for(Compound compound : reactants){
            for(Element element : compound.getAllElements()){
                boolean isInProducts = false;
                for(Compound compound1 : products){
                    if(compound1.contains(element)){
                        isInProducts = true;
                    }
                }
                if(!isInProducts){
                    return true;
                }
            }
        }

        for(Compound compound : products){
            for(Element element : compound.getAllElements()){
                boolean isInReactants = false;
                for(Compound compound1 : reactants){
                    if(compound1.contains(element)){
                        isInReactants = true;
                    }
                }
                if(!isInReactants){
                    return true;
                }
            }

        }

        return false;
    }

    private List<Compound> parseCompounds(String[] compoundStrings) {
        List<Compound> compounds = new ArrayList<>();
        for(int i = 0; i < compoundStrings.length; i++){
            compounds.add(parseCompound(compoundStrings[i]));
        }

        if(compounds.size() == 0){
            System.out.println("size is bad");
            return null;
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
