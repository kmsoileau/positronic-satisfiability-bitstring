/*
 * BitStringXorer.java	1.0 05/04/03
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

package positronic.satisfiability.bitstring;

import positronic.satisfiability.elements.BitXorer;
import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;

public class BitStringXorer extends MetaProblem implements IProblem
{
  private static final long serialVersionUID = 1L;

  public BitStringXorer(IBitString X, IBitString Y, IBitString Z) throws Exception
  {
    if(X.size()!=Y.size() || X.size()!=Z.size())
      this.setClauses(MetaProblem.unsolvableProblem().getClauses());
    else
    {
      IProblem[] p=new IProblem[X.size()];
      int count=0;
      for(int i=0;i<X.size();i++)
        p[count++]=new BitXorer(X.getBooleanVariable(i),Y.getBooleanVariable(i),Z.getBooleanVariable(i));
      this.setClauses(new Conjunction(p).getClauses());
    }
  }
}