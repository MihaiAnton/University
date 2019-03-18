





package main;

public class Main {

    public static void main(String[] args) {

         Polygon p = new Polygon();
         char[] sign = new char[1024];
         int pos = 0;
         boolean isCorrect = true;

         for(int i=0;i<args.length;i++) {
             if(args[i].equals("+") || args[i].equals("-") ||args[i].equals("*") || args[i].equals("/")){
                 sign[pos] = args[i].charAt(0);
                 pos++;
             }
             else  {
                 ComplexNr nr = new ComplexNr();
                 if (nr.isCorrectComplex(args[i])) {
                     ComplexNr nr2 = new ComplexNr(args[i]);
                     p.add(nr2);
                 }
                 else{
                     isCorrect = false;
                 }

             }
         }
         if(!isCorrect){
             System.out.println("Not correct expression.");
         }
         else {
             System.out.println("Result = " + p.getResult(sign).toString());
             System.out.println("Perimeter = " + p.perimeter());
         }
    }
}

class ComplexNr{

    float a;
    float b;

    public ComplexNr(){
        a=0;
        b=0;
    }

    public ComplexNr(String nr){
        a=0;
        b=0;
        boolean beforePlus=true;
        boolean isMinus = false;
        for(int i=0;i<nr.length();i++) {
            if (nr.charAt(i) >= '0' && nr.charAt(i) <= '9') {
                if (beforePlus) {
                    a = a * 10 + (nr.charAt(i) - '0');
                } else {
                    b = b * 10 + (nr.charAt(i) - '0');
                }
            }
            else if (nr.charAt(i) == '+' || nr.charAt(i) == '-') {
                beforePlus = false;
                if (nr.charAt(i) == '-') {
                    isMinus = true;
                }
            }else if(nr.charAt(i) == 'i' && beforePlus){

                b=a;
                a=0;
            }
        }
        if(isMinus)
            b = -b;
    }

    public ComplexNr(float a, float b){
        this.a = a;
        this.b = b;
    }

    public ComplexNr add(Object ot){
        if(!(ot instanceof ComplexNr)){
            return null;
        }
        else{
            ComplexNr nr = (ComplexNr)ot;
            float a = this.getA() + nr.getA();
            float b=  this.getB() + nr.getB();
            ComplexNr rez = new ComplexNr(a,b);
            return rez;
        }
    }

    public ComplexNr minus(Object ot){
        if(!(ot instanceof ComplexNr)){
            return null;
        }
        else{
            ComplexNr nr = (ComplexNr)ot;
            float a = this.getA() - nr.getA();
            float b=  this.getB() - nr.getB();
            ComplexNr rez = new ComplexNr(a,b);
            return rez;
        }
    }

    public ComplexNr mul(Object ot) {
        if (!(ot instanceof ComplexNr)) {
            return null;
        }
        else{
            ComplexNr nr = (ComplexNr)ot;
            return new ComplexNr((this.getA()*nr.getA() - this.getB()*nr.getB()),
                                  this.getB()*nr.getA() + nr.getB()*this.getA());
        }
    }

    public ComplexNr getOpposite(){

        float newB = this.getB() * -1;
        return new ComplexNr(this.getA(), newB);
    }

    public ComplexNr div(Object ot){
        if(!(ot instanceof ComplexNr)){
            return null;
        }
        else{
            ComplexNr nr = (ComplexNr)ot;
            ComplexNr rez = this.mul(nr.getOpposite());
            double  factor = Math.pow(nr.getA(),2)+Math.pow(nr.getB(),2);
            return new ComplexNr((rez.getA()/(float)factor), (rez.getB()/(float)factor));
        }
    }



    @Override
    public String toString() {
        String sign = new String();

        if(this.getB() < 0){
            sign = "";
        }
        else{
            sign = "+";
        }
        return this.getA() + sign + this.getB()+"i";
    }

    public float getA(){
        return a;
    }
    public float getB(){
        return b;
    }

    public boolean isCorrectComplex(String nr) {
        a = 0;
        b = 0;
        boolean beforePlus = true;
        boolean isMinus = false;
        for (int i = 0; i < nr.length(); i++) {
            if (nr.charAt(i) >= '0' && nr.charAt(i) <= '9') {
                if (beforePlus) {
                    a = a * 10 + (nr.charAt(i) - '0');
                } else {
                    b = b * 10 + (nr.charAt(i) - '0');
                }
            } else if (nr.charAt(i) == '+' || nr.charAt(i) == '-') {
                if(!beforePlus)
                    return false;
                beforePlus = false;
                if (nr.charAt(i) == '-') {
                    isMinus = true;
                }
            } else if(nr.charAt(i) == 'i') {
                if(i!=nr.length()-1)
                    return false;
            } else
                return false;
        }


        return true;
    }
}

class Polygon {

    int points;
    ComplexNr arr[];

    Polygon(){
        points = 0;
        arr = new ComplexNr[1024];
    }

    public void add(ComplexNr nr){
        arr[points] = nr;
        points++;
    }



    public ComplexNr getResult(char[] sgn){

        ComplexNr addable[] = new ComplexNr[1024];
        int last = 0;
        addable[last] = this.arr[0];

        for(int i=0;i<sgn.length;i++){

            if(sgn[i] == '+' || sgn[i] == '-'){
                last++;
                if(sgn[i] == '+') {
                    addable[last] = this.arr[i + 1];
                }
                else{
                    addable[last] = new ComplexNr(-this.arr[i+1].getA(), -this.arr[i+1].getB());
                }
            }
            else{
                if(sgn[i] == '*'){
                    addable[last] = addable[last].mul(this.arr[i+1]);
                }
                else if(sgn[i] == '/'){
                    addable[last] = addable[last].div(this.arr[i+1]);
                }
            }

        }

        ComplexNr rez = new ComplexNr(0,0);
        for(int i=0;i<=last;i++){
            rez = rez.add(addable[i]);
        }


        return rez;
    }

    private double distance(ComplexNr a, ComplexNr b){
        return Math.sqrt(Math.pow(a.getA()-b.getA(),2) + Math.pow(a.getB()-b.getB(),2));
    }

    public double perimeter(){
        double p = 0;
        for(int i=0;i<points-1;i++){
            p+= this.distance(arr[i],arr[i+1]);
        }
        p+=this.distance(arr[0],arr[points-1]);

        return p;
    }

}




























