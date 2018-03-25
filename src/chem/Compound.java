package chem;

import java.util.List;

public class Compound {
    private List<Element> elements;
    private int coefficient;

    public Compound(List<Element>  elements){
        this.elements = elements;
        this.coefficient = 1;
    }

    public void setCoefficient(int coefficient){
        this.coefficient = coefficient;
    }

    public int getCoefficient(){
        return coefficient;
    }

    public boolean contains(Element element){
        return elements.contains(element);
    }

    public Element getElement(Element element){
        for(int i = 0; i < elements.size(); i++){
            if(elements.get(i) == element){
                return elements.get(i);
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(coefficient);
        for(Element element : elements){
            sb.append(element);
        }

        return sb.toString();
    }
}
