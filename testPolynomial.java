public class testPolynomial {
    public static void main(String[] args) {

        boolean[] modulo = {true,false,true,true};
        boolean[] testnul = {true};
        boolean[] testeins = {true,false};
        boolean[] testzwei = {true,false,false};
        boolean[] testdrei = {true,false,false,false};
        boolean[] testvier = {true,false,false,false,false};
        boolean[] testfuenf = {true,false,false,false,false,false};
        boolean[] testsechs = {true,false,false,false,false,false,false};

        PolynomialGF2 modu = new PolynomialGF2(4, modulo);
        PolynomialGF2 nul = new PolynomialGF2(4,testnul);
        PolynomialGF2 eins = new PolynomialGF2(4,testeins);
        PolynomialGF2 zwei = new PolynomialGF2(1,testzwei);
        PolynomialGF2 drei = new PolynomialGF2(1,testdrei);
        PolynomialGF2 vier = new PolynomialGF2(1,testvier);
        PolynomialGF2 fuenf = new PolynomialGF2(1,testfuenf);
        PolynomialGF2 sechs = new PolynomialGF2(1,testsechs);



        System.out.println(" i | hash | x^i");
        System.out.println("----------------------");
        System.out.println(" 0 |   " + nul.mod(modu).hashCode() + "  | " + nul.mod(modu).toString());
        System.out.println(" 1 |   " + eins.mod(modu).hashCode() + "  | " + eins.mod(modu).toString());
        System.out.println(" 2 |   " + zwei.mod(modu).hashCode() + "  | " + zwei.mod(modu).toString());
        System.out.println(" 3 |   " + drei.mod(modu).hashCode() + "  | " + drei.mod(modu).toString());
        System.out.println(" 4 |   " + vier.mod(modu).hashCode() + "  | " + vier.mod(modu).toString());
        System.out.println(" 5 |   " + fuenf.mod(modu).hashCode() + "  | " + fuenf.mod(modu).toString());
        System.out.println(" 6 |   " + sechs.mod(modu).hashCode() + "  | " + sechs.mod(modu).toString());
    }
}
