
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
  private static final int MIN_PAIR_PLUS = 50;
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
  static int ante = 0;
  static int pairPlus = 0;
  static int play = 0;
  static Scanner in = new Scanner(System.in);

  /**
   * The main method, runs as long as the player can afford to place an ante and
   * play wager
   * 
   * @param args
   */
  public static void main(String[] args) {
    while (money - MIN_ANTE - MIN_PLAY >= 0) {
      ante = 0;
      pairPlus = 0;
      play = 0;
      String playerHand = dealCards();
      String dealerHand = dealCards();
      System.out.println("Money: $" + money);
      anteWager();
      pairPlusWager();
      System.out.println("Player: " + playerHand);
      playWager();
      if (fold)
        continue;
      System.out.println("Player: " + playerHand);
      System.out.println("Dealer: " + dealerHand);
      anteAndPlay(dealerHand, playerHand);
      if (pairPlus > 0)
        pairPlus(playerHand);
    }
    System.out.println("You cannot afford to play again");
  }

  /**
   * Creates a string representing a deck of 3 cards which each have a unique
   * combination of faces and suits
   * 
   * @return a string in the format "FS FS FS " with F being a letter or number
   *         representing a face (1,2,3,4,5,6,7,8,9,10,J,Q,K,A) and S being a
   *         letter representing a suit (H,D,C,S)
   */
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

  /**
   * Creates a string representing a card
   * 
   * @return a string in the format "FS" with F being a letter or number
   *         representing a face (1,2,3,4,5,6,7,8,9,10,J,Q,K,A) and S being a
   *         letter representing a suit (H,D,C,S)
   */
  private static String getCard() {
    return getFace() + getSuit();
  }

  /**
   * Creates a string representing a random suit
   * 
   * @return a string that consists of a letter representing a random suit
   */
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

  /**
   * Creates a string representing a random face
   * 
   * @return a string that consists of a number or letter representing a random
   *         face
   */
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

  /**
   * Checks if the card already exists in the hand
   * 
   * @param hand A string representing a hand
   * @param card A string representing a card
   * @return true if the card exists in the hand, false otherwise
   */
  public static boolean isUnique(String hand, String card) {
    return hand.indexOf(card) == -1;
  }

  /**
   * Prompts the player for their ante wager and stores the ante wager value
   */
  private static void anteWager() {
    boolean validInput = false;
    while (!validInput) {
      try {
        System.out.print("Enter ante wager ($50-$100):");
        ante = Integer.parseInt(in.nextLine());
        if (ante < MIN_ANTE || ante > MAX_ANTE)
          System.out.println("Wager must be between $50 and $100");
        else if (money - ante * 2 < 0) {
          System.out.println("You cannot afford this wager");
        } else
          validInput = true;
      } catch (NumberFormatException e) {
        System.out.println("Please enter a number.");
      }
    }

  }

  /**
   * Gives the player the option to place a pair plus wager. If they choose to do
   * so, prompts the player for their pair plus wager and stores the pair plus
   * wager value
   */
  private static void pairPlusWager() {
    boolean validInput = false;
    boolean validInput2 = false;
    while (!validInput) {
      if (money - ante * 2 - MIN_PAIR_PLUS < 0) {
        System.out.println("You cannot afford to place a pair plus wager.");
        validInput = true;
      } else {
        System.out.println("Would you like to place a pair plus wager? (Y/N)");
        String yOrN = in.nextLine();
        if (yOrN.equalsIgnoreCase("n")) {
          validInput = true;
        } else if (yOrN.equalsIgnoreCase("y")) {
          while (!validInput2) {
            try {
              System.out.print("Enter pair plus wager ($50-$100):");
              pairPlus = Integer.parseInt(in.nextLine());
              if (pairPlus < MIN_PAIR_PLUS || pairPlus > MAX_PAIR_PLUS)
                System.out.println("Wager must be between $50 and $100");
              else if (money - ante * 2 - pairPlus < 0) {
                System.out.println("You cannot afford this wager");
              } else
                validInput2 = true;
            } catch (NumberFormatException e) {
              System.out.println("Please enter a number.");
            }
          }
          validInput = true;
        } else
          System.out.println("Answer must be \"Y\" or \"N\"");
      }

    }
  }

  /**
   * Gives the player the option to place a play wager or fold
   */
  private static void playWager() {
    boolean validInput = false;
    fold = false;
    while (!validInput) {
      System.out.println("Would you like to place a play wager? (Y/N)");
      String yOrN = in.nextLine();
      if (yOrN.equalsIgnoreCase("n")) {
        money -= ante;
        money -= pairPlus;
        validInput = true;
        fold = true;
        System.out.println("You lost your ante wager ($" + ante + ") and your pair plus wager ($" + pairPlus + ").");
      } else if (yOrN.equalsIgnoreCase("y")) {
        play = ante;
        validInput = true;
      } else
        System.out.println("Answer must be \"Y\" or \"N\"");
    }
  }

  /**
   * Decides how much money the player gains or loses from their ante and play
   * wagers by comparing the player and dealer hands If the dealer has a hand of
   * Jack-high or worse, the play wager is returned to the player. You still lose
   * the Ante Wager. If the dealer has a hand of Queen-high or better, both the
   * play wager and the ante are paid out at 1 to 1 if the player has a better
   * hand than the dealer. If the dealer’s hand is superior, both the ante and
   * play bets are collected. If there is a tie, the player keeps the play wager
   * and loses the ante wager.
   * 
   * @param playerHand a string representing the player's hand
   * @param dealerHand a string representing the dealer's hand
   */
  private static void anteAndPlay(String playerHand, String dealerHand) {
    if (evaluateHands(playerHand, dealerHand) == -1) {
      money -= ante;
      money -= play;
      System.out.println("You lost your ante wager ($" + ante + ") and your play wager ($" + play + ").");
    } else if (evaluateHands(playerHand, dealerHand) == 1 && getHighCard(dealerHand) >= QUEEN) {
      money += ante;
      money += play;
      System.out.println("You won your ante wager ($" + ante + ") and your play wager ($" + play + ").");
    } else {
      money -= ante;
      System.out.println("You lost your ante wager ($" + ante + ").");
    }
  }

  /**
   * Decides how much money the player gains or loses from their pair plus wager
   * based on the quality of the player hand See hand type constants for
   * multiplier values
   * 
   * @param playerHand a string representing the player's hand
   */
  private static void pairPlus(String playerHand) {
    int multiplier = handType(playerHand);
    if (multiplier > 0) {
      money += pairPlus * multiplier;
      System.out.println("You won $" + (pairPlus * multiplier) + " from your pair plus wager.");
    } else {
      money -= pairPlus;
      System.out.println("You lost your pair plus wager ($" + pairPlus + ").");
    }
  }

  /**
   * Compares two hands to see if one is superior, inferior or equal to the other
   * 
   * @param hand1 one hand being compared
   * @param hand2 another hand being compared
   * @return 1 if hand1 is superior, -1 if it is inferior, and 0 if the two are
   *         equal
   */
  private static int evaluateHands(String hand1, String hand2) {
    if (handType(hand1) != handType(hand2)) {
      if (handType(hand1) > handType(hand2))
        return 1;
      else
        return -1;
    } else
      return equalHandType(hand1, hand2);
  }

  /**
   * Returns a number to be used as a pair plus multiplier based off of the hand
   * quality
   * 
   * @param hand the hand being checked
   * @return an integer that is larger depending on the quality of the hand
   */
  private static int handType(String hand) {
    if (isStraightFlush(hand))
      return STRAIGHT_FLUSH;
    else if (isThreeOfAKind(hand))
      return THREE_OF_A_KIND;
    else if (isStraight(hand))
      return STRAIGHT;
    else if (isFlush(hand))
      return FLUSH;
    else if (isPair(hand))
      return PAIR;
    else
      return HIGH_CARD;
  }

  /**
   * Checks if the hand is a pair
   * 
   * @param hand the hand being checked
   * @return true if the hand is a pair, false otherwise
   */
  private static boolean isPair(String hand) {
    String f1 = findFace(hand, 1);
    String f2 = findFace(hand, 2);
    String f3 = findFace(hand, 3);
    return f1.equals(f2) || f1.equals(f3) || f2.equals(f3);
  }

  /**
   * Checks if the hand is a flush
   * 
   * @param hand the hand being checked
   * @return true if the hand is a flush, false otherwise
   */
  private static boolean isFlush(String hand) {
    String s1 = findSuit(hand, 1);
    String s2 = findSuit(hand, 2);
    String s3 = findSuit(hand, 3);
    return s1.equals(s2) && s2.equals(s3);
  }

  /**
   * Checks if the hand is a straight
   * 
   * @param hand the hand being checked
   * @return true if the hand is a straight, false otherwise
   */
  private static boolean isStraight(String hand) {
    int f1 = findFaceInt(hand, 1);
    int f2 = findFaceInt(hand, 2);
    int f3 = findFaceInt(hand, 3);
    int max = Math.max(f1, Math.max(f2, f3));
    int min = -Math.max(-f1, Math.max(-f2, -f3));
    int mid = (f1 + f2 + f3) - (max + min);
    return (min + 1 == mid && mid + 1 == max) || (min == ACE && mid == 2 && max == 3);
  }

  /**
   * Checks if the hand is a three of a kind
   * 
   * @param hand the hand being checked
   * @return true if the hand is a three of a kind, false otherwise
   */
  private static boolean isThreeOfAKind(String hand) {
    String f1 = findFace(hand, 1);
    String f2 = findFace(hand, 2);
    String f3 = findFace(hand, 3);
    return f1.equals(f2) && f2.equals(f3);
  }

  /**
   * Checks if the hand is a straight flush
   * 
   * @param hand the hand being checked
   * @return true if the hand is a straight flush, false otherwise
   */
  private static boolean isStraightFlush(String hand) {
    return isStraight(hand) && isFlush(hand);
  }

  /**
   * Finds a face of a certain card in the hand
   * 
   * @param hand    the hand
   * @param faceNum the card from which to retrieve the face from, the first card
   *                on the left is 1, the second is 2, third is 3
   * @return a string representing a face, can be a letter or number
   */
  private static String findFace(String hand, int cardNum) {
    int facesFound = 0;
    for (int i = 0; i < hand.length(); i++) {
      String currentChar = hand.substring(i, i + 1);
      if ("HDCS".indexOf(currentChar) >= 0) {
        if (facesFound + 1 == cardNum) {
          if (hand.substring(i - 1, i).equals("0"))
            return hand.substring(i - 2, i);
          else
            return hand.substring(i - 1, i);
        }
        facesFound++;
      }
    }
    return "";
  }

  /**
   * Finds a face of a certain card in the hand
   * 
   * @param hand    the hand
   * @param faceNum the card from which to retrieve the face from, the first card
   *                on the left is 1, the second is 2, third is 3
   * @return an int representing a face, numbers are the same, J, Q, K, A are
   *         converted to number equivalents
   */
  private static int findFaceInt(String hand, int cardNum) {
    String face = findFace(hand, cardNum);
    if ("2345678910".indexOf(face) >= 0)
      return Integer.parseInt(face);
    else {
      if (face.equals("A"))
        return ACE;
      if (face.equals("J"))
        return JACK;
      if (face.equals("Q"))
        return QUEEN;
      else
        return KING;
    }
  }

  /**
   * Finds a suit of a certain card in the hand
   * 
   * @param hand    the hand
   * @param faceNum the card from which to retrieve the suit from, the first card
   *                on the left is 1, the second is 2, third is 3
   * @return a string representing a suit, "H", "D", "C", or "S"
   */
  private static String findSuit(String hand, int cardNum) {
    int suitsFound = 0;
    for (int i = 0; i < hand.length(); i++) {
      String currentChar = hand.substring(i, i + 1);
      if ("HDCS".indexOf(currentChar) >= 0) {
        if (suitsFound + 1 == cardNum)
          return currentChar;
        suitsFound++;
      }
    }
    return "";
  }

  /**
   * Gets the face of the highest card in a hand
   * 
   * @param hand the hand
   * @return an int representing a face, numbers are the same, J, Q, K, A are
   *         converted to number equivalents
   */
  private static int getHighCard(String hand) {
    int f1 = findFaceInt(hand, 1);
    int f2 = findFaceInt(hand, 2);
    int f3 = findFaceInt(hand, 3);
    return Math.max(f1, Math.max(f2, f3));
  }

  /**
   * Gets the face of the middle card in a hand
   * 
   * @param hand the hand
   * @return an int representing a face, numbers are the same, J, Q, K, A are
   *         converted to number equivalents
   */
  private static int getMidCard(String hand) {
    int f1 = findFaceInt(hand, 1);
    int f2 = findFaceInt(hand, 2);
    int f3 = findFaceInt(hand, 3);
    int max = getHighCard(hand);
    int min = getLowCard(hand);
    return (f1 + f2 + f3) - (max + min);
  }

  /**
   * Gets the face of the lowest card in a hand
   * 
   * @param hand the hand
   * @return an int representing a face, numbers are the same, J, Q, K, A are
   *         converted to number equivalents
   */
  private static int getLowCard(String hand) {
    int f1 = findFaceInt(hand, 1);
    int f2 = findFaceInt(hand, 2);
    int f3 = findFaceInt(hand, 3);
    return -Math.max(-f1, Math.max(-f2, -f3));
  }

  /**
   * Compares two hands of the same type to see if one is superior, inferior or
   * equal to the other
   * 
   * @param hand1 one hand being compared
   * @param hand2 another hand being compared
   * @return 1 if hand1 is superior, -1 if it is inferior, and 0 if the two are
   *         equal
   */
  private static int equalHandType(String hand1, String hand2) {
    if (handType(hand1) == STRAIGHT_FLUSH)
      return isSuperiorStraightFlush(hand1, hand2);
    else if (handType(hand1) == THREE_OF_A_KIND)
      return isSuperiorThreeOfAKind(hand1, hand2);
    else if (handType(hand1) == STRAIGHT)
      return isSuperiorStraight(hand1, hand2);
    else if (handType(hand1) == FLUSH)
      return isSuperiorFlush(hand1, hand2);
    else if (handType(hand1) == PAIR)
      return isSuperiorPair(hand1, hand2);
    else
      return isSuperiorHighCard(hand1, hand2);
  }

  /**
   * Compares two hands that are pairs to see if one is superior, inferior or
   * equal to the other
   * 
   * @param hand1 one pair hand being compared
   * @param hand2 another pair hand being compared
   * @return 1 if hand1 is superior, -1 if it is inferior, and 0 if the two are
   *         equal
   */
  private static int isSuperiorPair(String hand1, String hand2) {
    int f1 = findFaceInt(hand1, 1);
    int f2 = findFaceInt(hand1, 2);
    int f3 = findFaceInt(hand1, 3);
    int pair1 = 0;
    int pair2 = 0;
    int nonPair1 = 0;
    int nonPair2 = 0;
    if (f1 == f2) {
      pair1 = f1;
      nonPair1 = f3;
    } else if (f2 == f3) {
      pair1 = f2;
      nonPair1 = f1;
    } else if (f1 == f3) {
      pair1 = f1;
      nonPair1 = f2;
    }
    f1 = findFaceInt(hand2, 1);
    f2 = findFaceInt(hand2, 2);
    f3 = findFaceInt(hand2, 3);
    if (f1 == f2) {
      pair2 = f1;
      nonPair2 = f3;
    } else if (f2 == f3) {
      pair2 = f2;
      nonPair2 = f1;
    } else if (f1 == f3) {
      pair2 = f1;
      nonPair2 = f2;
    }
    if (pair1 != pair2) {
      if (pair1 > pair2)
        return 1;
      else
        return -1;
    } else {
      if (nonPair1 > nonPair2)
        return 1;
      else if (nonPair1 < nonPair2)
        return -1;
      else
        return 0;
    }
  }

  /**
   * Compares two hands that are flushes to see if one is superior, inferior or
   * equal to the other
   * 
   * @param hand1 one flush hand being compared
   * @param hand2 another flush hand being compared
   * @return 1 if hand1 is superior, -1 if it is inferior, and 0 if the two are
   *         equal
   */
  private static int isSuperiorFlush(String hand1, String hand2) {
    int h1 = getHighCard(hand1);
    int h2 = getHighCard(hand2);
    int m1 = getMidCard(hand1);
    int m2 = getMidCard(hand2);
    int l1 = getLowCard(hand1);
    int l2 = getLowCard(hand2);
    if (h1 != h2) {
      if (h1 > h2)
        return 1;
      else
        return -1;
    } else if (m1 != m2) {
      if (m1 > m2)
        return 1;
      else
        return -1;
    } else {
      if (l1 > l2)
        return 1;
      else if (l1 < l2)
        return -1;
      else
        return 0;
    }
  }

  /**
   * Compares two hands that are straights to see if one is superior, inferior or
   * equal to the other
   * 
   * @param hand1 one straight hand being compared
   * @param hand2 another straight hand being compared
   * @return 1 if hand1 is superior, -1 if it is inferior, and 0 if the two are
   *         equal
   */
  private static int isSuperiorStraight(String hand1, String hand2) {
    int m1 = getMidCard(hand1);
    int m2 = getMidCard(hand2);
    if (m1 > m2)
      return 1;
    else if (m1 < m2)
      return -1;
    else
      return 0;
  }

  /**
   * Compares two hands that are three of a kinds to see if one is superior,
   * inferior or equal to the other
   * 
   * @param hand1 one three of a kind hand being compared
   * @param hand2 another three of a kind hand being compared
   * @return 1 if hand1 is superior, -1 if it is inferior, and 0 if the two are
   *         equal
   */
  private static int isSuperiorThreeOfAKind(String hand1, String hand2) {
    return isSuperiorStraight(hand1, hand2);
  }

  /**
   * Compares two hands that are straight flushesvto see if one is superior,
   * inferior or equal to the other
   * 
   * @param hand1 one straight flush hand being compared
   * @param hand2 another straight flush hand being compared
   * @return 1 if hand1 is superior, -1 if it is inferior, and 0 if the two are
   *         equal
   */
  private static int isSuperiorStraightFlush(String hand1, String hand2) {
    return isSuperiorStraight(hand1, hand2);
  }

  /**
   * Compares two hands that are high cards to see if one is superior, inferior or
   * equal to the other
   * 
   * @param hand1 one high card hand being compared
   * @param hand2 another high card hand being compared
   * @return 1 if hand1 is superior, -1 if it is inferior, and 0 if the two are
   *         equal
   */
  private static int isSuperiorHighCard(String hand1, String hand2) {
    return isSuperiorFlush(hand1, hand2);
  }
}
