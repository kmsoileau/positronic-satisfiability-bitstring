/*
 * BitStringHighPopper.java	1.0 05/04/21
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

public class BitStringHighPopper extends MetaProblem implements IProblem
{
  private static final long serialVersionUID = 1L;

  public BitStringHighPopper(IBitString X, IBitString Y) throws Exception
  {
    if(X.size()!=Y.size()+1)
      this.setClauses(MetaProblem.unsolvableProblem().getClauses());
    else
    {
      IProblem[] p=new IProblem[X.size()];
      int count=0;
      for(int i=0;i<Y.size();i++)
        p[count++]=new BitEqualizer(X.getBooleanVariable(i),Y.getBooleanVariable(i));
      this.setClauses(new Conjunction(p).getClauses());
    }
  }
}