
package week6;

import java.util.Scanner;

public class ThreeCardPoker {

  private static final int HEARTS = 0;
  private static final int DIAMONDS = 1;
  private static final int CLUBS = 2;
  private static final int SPADES = 3;
  private static final int NUM_SUITS = 4;
  private static final int NUM_FACES = 13;
  private static final int JACK = 11;
  private static final int QUEEN = 12;
  private static final int KING = 13;
  private static final int ACE = 14;
  private static final int MIN_ANTE = 50;
  private static final int MAX_ANTE = 100;
  private static final int MIN_PAIR_PLUS=50;
  private static final int MAX_PAIR_PLUS = 100;
  private static final int MIN_PLAY = 50;
  private static final int STRAIGHT_FLUSH = 40;
  private static final int THREE_OF_A_KIND = 30;
  private static final int STRAIGHT = 6;
  private static final int FLUSH = 3;
  private static final int PAIR = 1;
  private static final int HIGH_CARD = 0;
  static int money = 1000;
  static boolean fold = false;
  static int ante=0;
  static int pairPlus=0;
  static int play = 0;
  static Scanner in = new Scanner(System.in);

public static void main(String[] args) {
    while(money-MIN_ANTE-MIN_PLAY>=0){
        ante=0;
        pairPlus=0;
        play=0;
        String playerHand = dealCards();
        String dealerHand = dealCards();
        System.out.println("Money: $"+money);
        anteWager();
        pairPlusWager();
        System.out.println("Player: " + playerHand);
        playWager();
        if(fold)
            continue;
        System.out.println("Player: " + playerHand);
        System.out.println("Dealer: " + dealerHand);
        anteAndPlay(dealerHand, playerHand);
        if(pairPlus>0)
          pairPlus(playerHand);
    }
    System.out.println("You cannot afford to play again");
  }

  private static String dealCards() {
    String cards = "";

    for (int i = 0; i < 3; i++) {
      Boolean hasCard = false;
      while (!hasCard) {
        String card = getCard();
        if (isUnique(cards, card)) {
          cards += card + " ";
          hasCard = true;
        }
      }
    }
    return cards;
  }

  private static String getCard() {
    return getFace() + getSuit();
  }

  private static String getSuit() {
    int suit = (int) (Math.random() * NUM_SUITS);
    if (suit == HEARTS)
      return "H";
    else if (suit == DIAMONDS)
      return "D";
    else if (suit == CLUBS)
      return "C";
    else if (suit == SPADES)
      return "S";
    else
      return null;
  }

  private static String getFace() {
    int face = (int) (Math.random() * NUM_FACES + 2);
    if (face >= 2 && face <= 10)
      return "" + face;
    else if (face == JACK)
      return "J";
    else if (face == QUEEN)
      return "Q";
    else if (face == KING)
      return "K";
    else if (face == ACE)
      return "A";
    else
      return null;
  }

  public static boolean isUnique(String playerHand, String card) {
    return playerHand.indexOf(card) == -1;
  }

  private static void anteWager(){
    boolean validInput = false;
    while(!validInput){
      try{
        System.out.print("Enter ante wager ($50-$100):");
        ante = Integer.parseInt(in.nextLine());
        if(ante<MIN_ANTE||ante>MAX_ANTE)
            System.out.println("Wager must be between $50 and $100");
        else if(money-ante*2<0){
            System.out.println("You cannot afford this wager");
        }else
            validInput = true;
      }catch(NumberFormatException e){
        System.out.println("Please enter a number.");
      }
    }
    
  }

  private static void pairPlusWager(){
    boolean validInput = false;
    boolean validInput2 = false;
    while(!validInput){
      if(money-ante*2-MIN_PAIR_PLUS<0){
        System.out.println("You cannot afford to place a pair plus wager.");
        validInput=true;
      }else{
        System.out.println("Would you like to place a pair plus wager? (Y/N)");
        String yOrN = in.nextLine();
          if(yOrN.equalsIgnoreCase("n")){
              validInput=true;
          }else if(yOrN.equalsIgnoreCase("y")){
              while(!validInput2){
                try{
                  System.out.print("Enter pair plus wager ($50-$100):");
                  pairPlus = Integer.parseInt(in.nextLine());
                  if(pairPlus<MIN_PAIR_PLUS||pairPlus>MAX_PAIR_PLUS)
                      System.out.println("Wager must be between $50 and $100");
                  else if(money-ante*2-pairPlus<0){
                      System.out.println("You cannot afford this wager");
                  }else
                      validInput2=true;
                }catch(NumberFormatException e){
                  System.out.println("Please enter a number.");
                }
              }
              validInput=true;
          }else 
              System.out.println("Answer must be \"Y\" or \"N\"");
      }
        
    }
  }

  private static void playWager(){
      boolean validInput = false;
      fold=false;
      while(!validInput){
        System.out.println("Would you like to place a play wager? (Y/N)");
        String yOrN = in.nextLine();
        if(yOrN.equalsIgnoreCase("n")){
            money-=ante;
            money-=pairPlus;
            validInput=true;
            fold = true;
            System.out.println("You lost your ante wager ($"+ante+") and your pair plus wager ($"+pairPlus+").");
        }else if(yOrN.equalsIgnoreCase("y")){
            play=ante;
            validInput = true;
        }else 
            System.out.println("Answer must be \"Y\" or \"N\"");
    }
  }

  private static void anteAndPlay(String dealerHand, String playerHand){
    if(handType(dealerHand)>handType(playerHand)||(handType(dealerHand)==handType(playerHand)&&getHighCard(dealerHand)>getHighCard(playerHand))){
      money-=ante;
      money-=play;
      System.out.println("You lost your ante wager ($"+ante+") and your play wager ($"+play+").");
    }else if(getHighCard(dealerHand)>=12){
      money+=ante;
      money+=play; 
      System.out.println("You won your ante wager ($"+ante+") and your play wager ($"+play+").");
    }else{
      money-=ante;
      System.out.println("You lost your ante wager ($"+ante+").");
    }
  }

  private static void pairPlus(String playerHand){
    int winnings = handType(playerHand);
    if(winnings>0){
      money+=pairPlus*winnings;
      System.out.println("You won $"+(pairPlus*winnings)+" from your pair plus wager.");
    }
    else{
      money-=pairPlus;
      System.out.println("You lost your pair plus wager ($"+pairPlus+").");
    }
  }

  public static int handType(String playerHand) {
    if (isStraightFlush(playerHand))
        return STRAIGHT_FLUSH;
    else if (isThreeOfAKind(playerHand))
        return THREE_OF_A_KIND;
    else if (isStraight(playerHand))
        return STRAIGHT;
    else if (isFlush(playerHand))
        return FLUSH;
    else if (isPair(playerHand))
        return PAIR;
    else
        return HIGH_CARD;
  }

  private static boolean isPair(String hand) {
    String f1=findFace(hand, 1);
    String f2=findFace(hand, 2);
    String f3=findFace(hand, 3);
    return f1.equals(f2)||f1.equals(f3)||f2.equals(f3);
  }

  private static boolean isFlush(String hand) {
    String s1=findSuit(hand,1);
    String s2=findSuit(hand,2);
    String s3=findSuit(hand,3);
    return s1.equals(s2)&&s2.equals(s3);
  }

  private static boolean isStraight(String hand) {
    int f1=findFaceInt(hand, 1);
    int f2=findFaceInt(hand, 2);
    int f3=findFaceInt(hand, 3);
    int max = Math.max(f1, Math.max(f2, f3));
    int min = -Math.max(-f1, Math.max(-f2, -f3)); 
    int mid = (f1 + f2 + f3) - (max+min); 
    return (min+1==mid&&mid+1==max)||(min==ACE&&mid==2&&max==3);
  }

  private static boolean isThreeOfAKind(String hand) {
    String f1=findFace(hand, 1);
    String f2=findFace(hand, 2);
    String f3=findFace(hand, 3);
    return f1.equals(f2)&&f2.equals(f3);
  }

  private static boolean isStraightFlush(String hand) {
    return isStraight(hand)&&isFlush(hand);
  }

  private static String findFace(String hand,int faceNum){
    int facesFound = 0;
    for(int i=0;i<hand.length();i++){
      String currentChar = hand.substring(i,i+1);
      if("HDCS".indexOf(currentChar)>=0){
        if(facesFound+1==faceNum){
          if(hand.substring(i-1,i).equals("0"))
            return hand.substring(i-2,i);
          else 
            return hand.substring(i-1,i);
        }
        facesFound++;
      }
    }
    return "";
  }

  private static int findFaceInt(String hand, int faceNum){
    String face=findFace(hand,faceNum);
    if("2345678910".indexOf(face)>=0)
      return Integer.parseInt(face);
    else{
      if(face.equals("A"))
        return ACE;
      if(face.equals("J"))
        return JACK;
      if(face.equals("Q"))
        return QUEEN;
      else
        return KING;
    }
  }

  private static String findSuit(String hand,int suitNum){
    int suitsFound = 0;
    for(int i=0;i<hand.length();i++){
      String currentChar = hand.substring(i,i+1);
      if("HDCS".indexOf(currentChar)>=0){
        if(suitsFound+1==suitNum)
          return currentChar;
        suitsFound++;
      }
    }
    return "";
  }
  
  private static int getHighCard(String hand){
    int f1=findFaceInt(hand, 1);
    int f2=findFaceInt(hand, 2);
    int f3=findFaceInt(hand, 3);
    return Math.max(f1, Math.max(f2, f3));
  }
}