/*
 * BitStringAlternater.java	1.0 05/10/21
 *
 * Copyright 2005 Positronic Software.
 *
 *
 */

package positronic.satisfiability.bitstring;

import positronic.satisfiability.elements.BitUnequalizer;
import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;

public class BitStringAlternator extends MetaProblem implements IProblem
{
  private static final long serialVersionUID = 1L;

  public BitStringAlternator(IBitString X) throws Exception
  {
    IProblem[] p=new IProblem[X.size()];
    for(int i=0;i<X.size()-1;i++)
    	p[i]=new BitUnequalizer(X.getBooleanVariable(i),X.getBooleanVariable(i+1));
        
    this.setClauses(new Conjunction(p).getClauses());
  }
}