public class PolynomialGF2 implements Cloneable {

    private boolean[] polynomArray;
    final static boolean[] ZERO = {false};
    final static boolean[] ONE = {true};



    /**###############################################*/

    /**
     * @return true if objekt is zero polynom
     * */
    public static boolean isZero(PolynomialGF2 x){
        if(x.equals(new PolynomialGF2(1, ZERO)))
            return true;
        return false;
    }
    /**
     * @return true if objekt is one polynom
     * */
    public static boolean isONE(PolynomialGF2 x){
        if(x.equals(new PolynomialGF2()))
            return true;
        return false;
    }
    /**
     * trim
     * */
    public static boolean[] trim(boolean[] field){
        boolean[] trimField;
        int trimFieldLength = 0;
        int position = 0;
        for(int i = 0; i < field.length; i++){
            if (field[i] == true){
                trimFieldLength = field.length - i;
                position = i;
                break;
            }
        }
        trimField = new boolean[trimFieldLength];

        for(int i = 0; i < trimField.length; i++){
            trimField[i] = field[position+i];
        }
        if (trimField == null)
            trimField = ZERO;
        return trimField;
    }

    /**
     * TODO: shift
     * */

    /**
     * calculate degree of polynom from given array
     * @param polynom boolean[]
     * @return degree from given polynom
     * */
    public static int degree(boolean[] polynom){
        return polynom.length-1;
    }


    /**###############################################*/
    /**
     * Default Konstruktor
     * */
    public PolynomialGF2(){
        polynomArray = ONE;
    }

    public PolynomialGF2(int n , boolean[] koeffizienten){
        if(koeffizienten[0] == false)
            polynomArray = ZERO;
        else if (koeffizienten[0] == true)
            polynomArray = ONE;
        polynomArray = PolynomialGF2.trim(koeffizienten);
    }
    /**
     * getter method for koeffizient array
     * @return polynom array
     * */
    public boolean[] toArray(){
        return polynomArray;
    }

    /**
     * TODO: times, mod
     * */
    /**
     * shift a_(n−1)x^(n−1)+...+a_0 mit x^k = a_(n−1)x^(n−1+k) + . . . + a_0x^k
     * */
    public PolynomialGF2 shift(int k){
        if(k < 0){
            System.out.print("shift for negative k not defined! Break "); return null;
        }
        boolean[] a = new boolean[polynomArray.length+k];
        for(int i = 0; i < polynomArray.length; i++){
            a[i] = polynomArray[i];
        }
        return new PolynomialGF2(a.length, a);
    }
    /**
     * addition with to PolynomialGF2 objects
     * @return new PolynomialGF2 object
     * */
    public PolynomialGF2 plus(PolynomialGF2 input){
        boolean[] x,y,z;
        if(this.toArray().length >= input.toArray().length) {
            x = this.toArray(); y = input.toArray();
        }
        else {
            x = input.toArray(); y = this.toArray();
        }
        z = x.clone();
        int dif = x.length - y.length;
        for(int i = 0; i < y.length; i++){
            z[i+dif] = x[i+dif] ^ y[i];
        }
        return new PolynomialGF2(z.length, z);
    }
    /**
     *
     *
     * multiplication with to PolynomialGF2 objects using shift method
     * @return new PolynomialGF2 object
     * */
    public PolynomialGF2 times(PolynomialGF2 input){
       return this.shift(PolynomialGF2.degree(input.toArray()));
    }

    /**
     * mod
     * @return new PolynomialGF2 object
     * */
    public PolynomialGF2 mod(PolynomialGF2 input){
        //if this < input
        if(PolynomialGF2.degree(this.toArray()) < PolynomialGF2.degree(input.toArray())){
            return this.clone();
        }
        int dif = this.polynomArray.length - input.polynomArray.length;
        int i = 0;
        //copy of arrays
        boolean[] x = this.polynomArray.clone();
        boolean[] y = input.polynomArray.clone();
        //calculation
        do {
            for(int n = 0; n < input.polynomArray.length; n++){
                if(i == 0){
                    x[n] = x[n] ^ y[n];
                } else {
                    x[n+2+i-1] = x[n+2+i-1] ^ y[n];
                }
            }
            i++;
        }while (i <= dif-1);
        //prepare result
        boolean[] fin = new boolean[y.length];
        int g = 0;
        for(int k = dif; k < x.length; k++) {
            fin[g] = x[k];
            g++;
        }
        return new PolynomialGF2(fin.length, fin);
    }

    /**###############################################*/

    @Override
    public int hashCode() {
        String hash = "";
        for (int i = 0; i < polynomArray.length; i++) {
            if (polynomArray[i] == true)
                hash += "1";
            else
                hash += "0";
        }
        if (hash == "")
            hash += "0";
            return Integer.parseInt(hash,2);
    }
    /**
     * true if number a and number b are equal by hash
     * @param obj Object
     * @return boolean
     * */
    @Override
    public boolean equals(Object obj) {
        PolynomialGF2 o = (PolynomialGF2) obj;
        if(this.hashCode() == o.hashCode())
            return true;
        return false;
    }
    /**
     * implements Cloneabel
     * */
    @Override
    public PolynomialGF2 clone() {
        PolynomialGF2 clon = null;
        try {
            clon = (PolynomialGF2) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clon;
    }

    /**
     * Override toString
     * */
    @Override
    public String toString(){
        String outputSting = "";
        String koeffizientenSting = "";
        for(int i = 0; i < polynomArray.length; i++){
            if (polynomArray[i] == true){
                koeffizientenSting = "x^" + (-1*((i +1)- polynomArray.length)) + "+";
                if(koeffizientenSting.equalsIgnoreCase("x^1+")){
                    koeffizientenSting = "x+";
                }
                else if(koeffizientenSting.equalsIgnoreCase("x^0+")){
                    koeffizientenSting = "1+";
                }
                outputSting += koeffizientenSting;
            }
        }
        if (outputSting == ""){
            return "0";
        }
        else{
            outputSting = outputSting.substring(0,outputSting.length()-1);
            return outputSting;
        }

    }

}
