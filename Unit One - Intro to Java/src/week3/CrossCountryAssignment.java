package week3;

import java.util.Scanner;

public class CrossCountryAssignment {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    processRunner(in);  // runner 1
    processRunner(in);  // runner 2
    processRunner(in);  // runner 3

    in.close();
  }

  private static void processRunner(Scanner in) {
    System.out.print("Please enter runner's first name: ");
    String fName = in.nextLine();

    System.out.print("Please enter runner's last name: ");
    String lName = in.nextLine();

    System.out.print("Please enter your time to the end of the first mile: ");
    String mileOneTime = in.nextLine();
    
    System.out.print("Please enter your time to the end of the second mile: ");
    String mileTwoTime = in.nextLine();
    
    System.out.print("Please enter your time to the end of the 5km race: ");
    String raceCompleteTime = in.nextLine();

    String s1 = subtractTimes("0:00.000",mileOneTime);  //time for split 1
    String s2 = subtractTimes(mileOneTime,mileTwoTime); //time for split 2
    String s3 = subtractTimes(mileTwoTime, raceCompleteTime);   //time for split 3

    printSummary(fName,lName,s1,s2,s3);
  }

  private static void printSummary(String fName, String lName, String s1, String s2, String s3){    //prints a summary with runner name and 3 split times given first name, last name, and split times
    System.out.println("Runner Summary | "+fName+" "+lName);
    System.out.println("Split 1: "+s1);
    System.out.println("Split 2: "+s2);
    System.out.println("Split 3: "+s3);
  }

  private static String subtractTimes(String startTime, String finishTime){ //returns time in the format "mm:ss.sss" for a split given the start and finish times in the same format
    double startSeconds = convertToSeconds(startTime);  //converts from minutes format to seconds to do math
    double finishSeconds = convertToSeconds(finishTime);
    String splitTime = convertToMinutes(finishSeconds-startSeconds);    //converts back to proper format to return
    return splitTime;
  }

  private static String convertToMinutes(double secondsDouble){ //returns time in the format "mm:ss.sss" given the time in seconds
      int minutes = (int)(secondsDouble/60);
      double seconds = secondsDouble%60;
      String timeInMinutes = String.format("%d:%06.3f", minutes,  seconds);
      return timeInMinutes;

  }

  /**
   * Converts a time into a doubld (seconds) "5:34.221" => 334.221
   * 
   * @param timeString time in the format "mm:ss.sss"
   * @return converts time into seconds
   */
  private static double convertToSeconds(String timeString) {
    int colon = timeString.indexOf(":");
    int minutesAsSeconds = Integer.parseInt(timeString.substring(0,colon)) * 60;
    double seconds = Double.parseDouble(timeString.substring(colon+1));

    return minutesAsSeconds + seconds;
  }
}