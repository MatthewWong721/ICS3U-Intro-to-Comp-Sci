package week3;

public class WritingFunctionsHomework {
    public static void main(String[] args) {
        double price=985;
        double tax=5.5;
        double questionOne = getTotalCost(price,tax);
        double length=4.5;
        double width=2.3;
        String questionTwo = getDimensions(length,width);
        int questionThree = minutesInYear();
        int questionFour = getLightYear();
        int won = 110;
        int lost = 44;
        double questionFive = winRate(won,lost);
        double mass = 10;
        double velocity = 12;
        double questionSix = getMomentum(mass, velocity);
        double fahrenheit = 98;
        double questionSeven = convertCelsius(fahrenheit);
        double number;
        String questionEight = squareAndRoot(number);
        int itemsSold;
        double pay = getPay(itemsSold);
        double length2;
        double width2;
        String questionTen = getDimensions(length2,width2);
        double mass2;
        double velocity2;
        double energy = getEnergy(mass2, velocity2);

    }
    private static double getTotalCost(double price, double tax){
        return price*(1+(tax/100));
    }    
    private static String getDimensions(double length, double width){
        return "Area: "+length*width+"sq.ft Perimeter: "+(length+length+width+width)+"ft";
    }
    private static int minutesInYear(){
        return 60*24*365;
    }
    private static int getLightYear(){
        return 300000000*60*60*24*365;
    }
    private static double winRate(int won, int lost){
        return (int)((double)won/(won+lost)*100000)+0.5)/(double)(1000);
    }
    private static double getMomentum(double mass, double velocity){
        return mass*velocity;
    }
    private static double convertCelsius(double fahrenheit){
        return (fahrenheit-32)*5/9;
    }
    private static String squareAndRoot(double number){
        return "Square: "+Math.pow(number, 2)+" Square root: "+Math.sqrt(number);        
    }
    private static double getPay(int items){
        return items*0.27;
    }
    private static double getEnergy(double mass, double speed){
        return mass*Math.pow(speed,2)/2;
    }
}
