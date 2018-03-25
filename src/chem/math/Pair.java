package chem.math;

public class Pair<T,S>{
    private T one;
    private S two;

    public Pair(T one, S two){
        this.one = one;
        this.two = two;
    }

    public T getOne(){
        return one;
    }

    public S getTwo(){
        return two;
    }

}
