package chem;

public class Element {
    private String name;
    private int subscript;

    public Element(String name, int subscript){
        this.name = name;
        this.subscript = subscript;
    }

    public String getName(){
        return name;
    }

    public int getSubscript(){
        return subscript;
    }

    public void setSubscript(int subscript){
        this.subscript = subscript;
    }

    @Override
    public String toString() {
        return name + subscript;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Element)){
            return false;
        }

        Element e2 = (Element) o;
        return name.equals(e2.getName());
    }
}
