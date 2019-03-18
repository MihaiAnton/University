package Domain;

public abstract class Pair<A,B> {

    A a;
    B b;

    public A getFirst(){
        return this.a;
    }
    public void setFirst(A a){
        this.a = a;
    }
    public B getSecond(){
        return this.b;
    }
    public  void setSecond(B b){
        this.b = b;
    }

}
