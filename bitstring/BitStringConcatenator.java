/*
 * BitStringConcatenator.java	1.0 05/04/15
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

package positronic.satisfiability.bitstring;

import positronic.satisfiability.elements.BitEqualizer;
import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;

public class BitStringConcatenator extends MetaProblem implements IProblem
{
  private static final long serialVersionUID = 1L;

  public BitStringConcatenator(IBitString X, IBitString Y, IBitString Z) throws Exception
  {
    if(X.size()+Y.size()!=Z.size())
      this.setClauses(MetaProblem.unsolvableProblem().getClauses());
    else
    {
      IProblem[] p=new IProblem[Z.size()];
      int count=0;
      for(int i=0;i<X.size();i++)
      {
        p[count]=new BitEqualizer(X.getBooleanVariable(i),Z.getBooleanVariable(count));
        count++;
      }
      for(int i=0;i<Y.size();i++)
      {
        p[count]=new BitEqualizer(Y.getBooleanVariable(i),Z.getBooleanVariable(count));
        count++;
      }
      this.setClauses(new Conjunction(p).getClauses());
    }
  }
}