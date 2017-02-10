/*
 * BitStringMappingComposition.java	1.0 05/04/08
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

package positronic.satisfiability.bitstring;

import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;

public class BitStringMapperComposition extends MetaProblem implements IProblem
{
  private static final long serialVersionUID = 1L;

  public BitStringMapperComposition(BitStringMapper first, BitStringMapper second,
    IBitString X, IBitString Y) throws Exception
  {
    IProblem prob=new Conjunction(new MetaProblem[]
    {
      new BitStringEqualizer(X,first.getDomainVariable()),
      first,
      new BitStringEqualizer(first.getRangeVariable(),second.getDomainVariable()),
      second,
      new BitStringEqualizer(second.getRangeVariable(),Y),
    });
    this.setClauses(prob.getClauses());
  }
}