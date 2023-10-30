package Snap;
import java.util.ArrayList;
import java.util.Collections;

class Card {
    private String suit;
    private String rank;

    public Card(String suit, String rank) {
        this.suit = suit;
        this.rank = rank;
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    public boolean isSameRank(Card other) {
        return this.rank.equals(other.rank);
    }

    public boolean isSuperSnap(Card other) {
        return this.isSameRank(other) && 
              (((this.suit.equals("Hearts") || this.suit.equals("Diamonds")) && (other.suit.equals("Hearts") || other.suit.equals("Diamonds"))) || 
               ((this.suit.equals("Clubs") || this.suit.equals("Spades")) && (other.suit.equals("Clubs") || other.suit.equals("Spades"))));
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}

public class Snap {
    private ArrayList<Card> deck = new ArrayList<>();

    public Snap() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        
        for (String suit : suits) {
            for (String rank : ranks) {
                deck.add(new Card(suit, rank));
            }
        }
    }

    public void playGame() {
        Collections.shuffle(deck);

        int snapCount = 0;
        int superSnapCount = 0;

        for (int i = 0; i < deck.size() - 1; i++) {
            System.out.println(deck.get(i));

            if (deck.get(i).isSuperSnap(deck.get(i + 1))) {
                System.out.println(deck.get(i + 1));
                System.out.println("SUPERSNAP!!!");
                superSnapCount++;
                i++; // Skip the next card as it's already printed
            } else if (deck.get(i).isSameRank(deck.get(i + 1))) {
                System.out.println(deck.get(i + 1));
                System.out.println("SNAP!!!");
                snapCount++;
                i++; // Skip the next card as it's already printed
            }
        }
        
        if (deck.size() % 2 != 0) {
            System.out.println(deck.get(deck.size() - 1));  // print the last card if an odd number of cards
        }
        System.out.println("There were " + snapCount + " snaps of which " + superSnapCount + " were supersnaps.");
    }

    public static void main(String[] args) {
        Snap game = new Snap();
        game.playGame();
    }
}
