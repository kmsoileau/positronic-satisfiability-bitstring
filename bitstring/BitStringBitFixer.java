/*
 * BitStringBitFixer.java	1.0 05/04/15
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

package positronic.satisfiability.bitstring;

import positronic.satisfiability.elements.BitFixer;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;

public class BitStringBitFixer extends MetaProblem implements IProblem
{
  private static final long serialVersionUID = 1L;

  public BitStringBitFixer(IBitString b, int bit, boolean val) throws Exception
  {
    if((bit<0) || (b.size()-1<bit))
      this.setClauses(MetaProblem.unsolvableProblem().getClauses());
    else
    	this.setClauses(new BitFixer(b.getBooleanVariable(bit),val).getClauses());
  }
}