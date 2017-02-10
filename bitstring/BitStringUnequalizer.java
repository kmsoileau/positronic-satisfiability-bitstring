/*
 * BitStringUnequalizer.java	1.0 05/04/03
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

package positronic.satisfiability.bitstring;

import positronic.satisfiability.elements.BitUnequalizer;
import positronic.satisfiability.elements.Disjunction;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;

public class BitStringUnequalizer extends MetaProblem implements IProblem
{
  private static final long serialVersionUID = 1L;

  public BitStringUnequalizer(IBitString X, IBitString Y) throws Exception
  {
    if(X.size()==Y.size())
    {
      BitUnequalizer[] thba=new BitUnequalizer[X.size()];
      for(int i=0;i<X.size();i++)
        thba[i]=new BitUnequalizer(X.getBooleanVariable(i),Y.getBooleanVariable(i));
      IProblem p1=new Disjunction(thba);
      this.setClauses(p1.getClauses());
    }
    else
    	this.setClauses(super.trivialProblem().getClauses());
  }
}