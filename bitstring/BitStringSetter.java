/*
 * BitStringSetter.java	1.0 05/04/15
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

package positronic.satisfiability.bitstring;

import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;

public class BitStringSetter extends MetaProblem implements IProblem
{
  private static final long serialVersionUID = 1L;

  public BitStringSetter(IBitString X, IBitString Y) throws Exception
  {
    if(X.size()!=Y.size())
      this.setClauses(MetaProblem.unsolvableProblem().getClauses());
    else
    {
      int commonsize=X.size();
      IProblem[] p=new IProblem[commonsize];
      for(int i=0;i<commonsize;i++)
        p[i]=new BitStringBitFixer(Y,i,true);
      this.setClauses(new Conjunction(p).getClauses());
    }
  }
}