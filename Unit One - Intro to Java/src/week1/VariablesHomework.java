package week1;

public class VariablesHomework {
    public static void main(String[] args) {
        int radius = 5;
        questionOne(radius);
        questionTwo(radius);
        int a=-2;
        int b=4;
        int c=3;
        int x=5;
        questionThree(a,b,c,x);
        int x1=2;
        int y1=3;
        int x2=5;
        int y2=7;
        questionFour(x1,y1,x2,y2);
        int pennies=6;
        int nickles=10;
        int dimes=11;
        int quarters=4;
        int loonies=5;
        int toonies=2;
        questionFive(pennies, nickles, dimes, quarters, loonies, toonies);
        questionSix(a,b,c);
    }
    private static void questionOne(int radius){
        double area = Math.PI*Math.pow(radius, 2);
        System.out.println(area);
    }
    private static void questionTwo(int radius){
        double volume = Math.PI*4/3*Math.pow(radius, 3);
        System.out.println(volume);
    }
    private static void questionThree(int a, int b, int c, int x){
        double y=(a*Math.pow(x,2))+(b*x)+c;
        System.out.println(y);
    }
    private static void questionFour(int x1, int y1, int x2, int y2){
        double slope = (double)(y2-y1)/(x2-x1);
        System.out.println(slope);
    }
    private static void questionFive(int pennies, int nickles, int dimes, int quarters, int loonies, int toonies){
        double money=(0.01*pennies)+(0.05*nickles)+(0.1*dimes)+(0.25*quarters)+loonies+(2.0*toonies);
        System.out.println(money);
    }
    private static void questionSix(int a, int b, int c){
        double rootOne=((-1*b)+Math.sqrt(Math.pow(b,2)-(4*a*c)))/(2*a);
        double rootTwo=((-1*b)-Math.sqrt(Math.pow(b,2)-(4*a*c)))/(2*a);
        System.out.println("("+rootOne+", 0) ("+rootTwo+", 0)");
    }
}