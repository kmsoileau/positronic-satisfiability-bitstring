/*
 * BitStringNoter.java	1.0 05/04/01
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

package positronic.satisfiability.bitstring;

import positronic.satisfiability.elements.BitNoter;
import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;

public class BitStringNoter extends MetaProblem implements IProblem
{
  private static final long serialVersionUID = 1L;

  public BitStringNoter(IBitString X, IBitString Y) throws Exception
  {
    IProblem[] p=new IProblem[X.size()];
    int count=0;
    for(int i=0;i<p.length;i++)
      p[count++]=new BitNoter(X.getBooleanVariable(i),Y.getBooleanVariable(i));
    this.setClauses(new Conjunction(p).getClauses());
  }
}