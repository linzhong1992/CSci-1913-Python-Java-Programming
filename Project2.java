import java.util.Random;
//  CARD. A playing card. You are not allowed to change this class in any way.

final class Card
{

//  RANK NAME. Printable names of card suits. We don't use the name at index 0,
//  because no card has rank 0.

  private static final String [] rankName =
   { "",
     "ace",      //  1
     "two",      //  2
     "three",    //  3
     "four",     //  4
     "five",     //  5
     "six",      //  6
     "seven",    //  7
     "eight",    //  8
     "nine",     //  9
     "ten",      //  10
     "jack",     //  11
     "queen",    //  12
     "king" };   //  13

//  SUIT NAME. Printable names of card suits.

  private static final String [] suitName =
   { "spade",    //  0
     "heart",    //  1
     "diamond",  //  2
     "club" };   //  3

  private int rank;  //  Card rank, between 1 and 13 inclusive.
  private int suit;  //  Card suit, between 0 and 3 inclusive.

//  CARD. Constructor. Make a new CARD with the given RANK and SUIT.

  public Card(int rank, int suit)
  {
    if (0 <= suit && suit <= 3 && 1 <= rank && rank <= 13)
    {
      this.rank = rank;
      this.suit = suit;
    }
    else
    {
      throw new IllegalArgumentException("No such card.");
    }
  }

//  GET RANK. Return the RANK of this card.

  public int getRank()
  {
    return rank;
  }

//  GET SUIT. Return the SUIT of this card.

  public int getSuit()
  {
    return suit;
  }

//  TO STRING. Return a string that describes this card, for printing only. For
//  example, we might return "the queen of diamonds" or "the ace of hearts".

  public String toString()
  {
    return "the " + rankName[rank] + " of " + suitName[suit] + "s";
  }
}

class Deck
{
  private int count = 52;
  private Random r = new Random();
  private Card[] deck = new Card[52];
  public Deck()
  {
    int n = 0;
    for(int i = 1; i <= 13; i++)
    {
      for(int j = 0; j <= 3; j++)
      {
        deck[n] = new Card(i, j);
        n++;
      }
    }
  }
  public void shuffle()
  { 
    if(count == 52)
    {
      for(int i = 51; i >= 1; i--)
      {
        int j = Math.abs(r.nextInt()) % (i + 1);
        Card temp = deck[i];
        deck[i] = deck[j];
        deck[j] = temp;
      }
    }
    else
    {
      throw new IllegalStateException("Game has started, please do not shuffle.");
    }
  }
  public boolean canDeal()
  {
    if(count <= 0)
    {
      return false;
    }
    else
    {
      return true;
    }
  }
  public Card deal()
  {
    if(canDeal())
    {
      count -= 1;
      Card temp = deck[count];
      deck[count] = null;
      return temp;
    }
    else
    {
      throw new IllegalStateException("All cards have been dealt.");
    }
  }
}

class Tableau
{
  private Pile first;
  private class Pile
  {
    private Card card;
    private Pile next;
    private Pile(Card card, Pile next)
    {
      this.card = card;
      this.next = next;
    }
  }
  public Tableau()
  {
    first = new Pile(null, null);
  }
  private void addPile(Card card)
  {
    if(first.card == null)
    {
      first.card = card;
    }
    else
    {
      first = new Pile(card, first);
    }
    System.out.println("Added " + card.toString());   
  }
  private boolean canMerge()
  {
    if(manyPiles())
    {
      if(canPutOn(first.card, first.next.card))
      {
        return true;
      }
      else
      {
        return false;
      }
    }
    else
    {
      return false;
    }
  }
  private boolean canPutOn(Card left, Card right)
  {
    if(left.getSuit() == right.getSuit() || left.getRank() > right.getRank())
    {
      return true;
    }
    else
    {
      return false;
    }
  }
  private boolean manyPiles()
  {
    if(first.next == null)
    {
      return false;
    }
    else
    {
      return true;
    }
  }
  private void mergePiles()
  {
    System.out.println("Merge " + first.card.toString() + " and " + first.next.card.toString() + ".");
    first.next = first.next.next;
  }
  private void results()
  {
    if(manyPiles())
    {
      System.out.println("Lost the game.");
    }
    else
    {
      System.out.println("Won the game.");
    }
  }
  public void play()
  {
    Deck deck = new Deck();
    deck.shuffle();
    int i = 0;
    while(deck.canDeal())
    {
      addPile(deck.deal());
      while(canMerge())
      {
        mergePiles();
      }
    }
    results();
  }
}

class TimeWaster
{
  public static void main(String[] args) 
  {
    Tableau t = new Tableau();
    t.play();
  }
}