/*
 * BitStringZerothBitFixer.java	1.0 05/10/21
 *
 * Copyright 2005 Positronic Software.
 *
 *
 */

package positronic.satisfiability.bitstring;

import positronic.satisfiability.elements.BitFixer;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;

public class BitStringZerothBitFixer extends MetaProblem implements IProblem
{
  private static final long serialVersionUID = 1L;

  public BitStringZerothBitFixer(IBitString X, boolean b) throws Exception
  {
    this.setClauses(new BitFixer(X.getBooleanVariable(0), b).getClauses());
  }
}