class Poly
{
	private class Term
	{
		private int coef;
		private int expo;
		private Term next = null;
		private Term(int coef, int expo)
		{
			this.coef = coef;
			this.expo = expo;
		}
	}
	private Term head;
	private Term term; //temporary term for repeated use later on
	public Poly()
	{
		head = new Term(1, -1);
		head.next = head;
	}
	public Poly add(int coef, int expo)
	{
		if(expo < 0)
		{
			throw new IllegalArgumentException("Exponent cannot be negative.");
		}
		Term newTerm = new Term(coef, expo);
		Term left = head;
		Term right = head.next;
		while(true)
		{
			if(expo < right.expo)
			{
				left = right;
				right = right.next;
			}
			else if(expo == right.expo)
			{
				right.coef += coef;
				if(right.coef == 0)
				{
					left.next = right.next;
				}
				break;
			}
			else
			{
				left.next = newTerm;
				newTerm.next = right;
				break;
			}
		}
		return this;
	}
	public Poly minus()
	{
		Poly minusPoly = new Poly();
		term = head.next;
		while(term != head)
		{
			minusPoly.add(-term.coef, term.expo);
			term = term.next;
		}
		return minusPoly;
	}
	public Poly plus(Poly other)
	{
		Poly copyPoly = new Poly();
		term = head.next;
		while(term != head)
		{
			copyPoly.add(term.coef, term.expo);
			term = term.next;
		}
		term = other.head.next;
		while(term != other.head)
		{
			copyPoly.add(term.coef, term.expo);
			term = term.next;
		}
		return copyPoly;
	}
	public String toString()
	{
		String expression = "";
		term = head.next;
		if(term == head)
		{
			return "0";
		}
		while(term != head)
		{
			expression += term.coef + " " + "x";
			expression += Super.digits(term.expo) + " + ";
			term = term.next;
		}
		return expression.substring(0, expression.length() - 4);
	}
}

//  SUPER. Turn an integer into a string of Unicode superscript digits, with an
//  optional minus sign.

class Super
{

//  DIGITS. Return a string of superscript digits that represents NUMBER.

  public static String digits(int number)
  {
    if (number < 0)
    {
      return "⁻" + digiting(number);
    }
    else if (number > 0)
    {
      return digiting(- number);
    }
    else
    {
      return "⁰";
    }
  }

//  DIGITING. Do all the work for DIGITS. NUMBER is always negative, because in
//  2's complement arithmetic, there is one more negative integer (−2147483648)
//  than there are positive integers. See:
//
//  James R. Low. "A Short Note on Scanning Integers."  SIGPLAN Notices. Volume
//  14. Number 1. January 1979. Pages 55–56.

  private static String digiting(int number)
  {
    if (number == 0)
    {
      return "";
    }
    else
    {
      return digiting(number / 10) + "⁰¹²³⁴⁵⁶⁷⁸⁹".charAt(-(number % 10));
    }
  }
}

class PiningForTheFjords  
{  
  public static void main(String [] args)  
  {  
    Poly p1 = new Poly().add(1, 3).add(1, 1).add(1, 2);  
    Poly p2 = new Poly().add(2, 1).add(3, 2);  
    Poly p3 = p2.minus();

    System.out.println(p1);           //  1 x³ + 1 x² + 1 x¹  
    System.out.println(p2);           //  3 x² + 2 x¹  
    System.out.println(p3);           //  -3 x² + -2 x¹  
  
    System.out.println(p1.plus(p2));  //  1 x³ + 4 x² + 3 x¹  
    System.out.println(p1.plus(p3));  //  1 x³ + -2 x² + -1 x¹  
  }  
}