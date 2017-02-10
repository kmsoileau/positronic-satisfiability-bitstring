/*
 * BitStringEqualizer.java	1.0 05/04/15
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
import positronic.satisfiability.exceptions.BitStringEqualizerException;

public class BitStringEqualizer extends MetaProblem implements IProblem
{
	private static final long serialVersionUID = -7124138810958693451L;

	public BitStringEqualizer(IBitString X, IBitString Y) throws Exception
	{
		/*if(X.size()!=Y.size())
			this.setClauses(Problem.unsolvableProblem().getClauses());*/
		if(X.size()!=Y.size())
			throw new BitStringEqualizerException("IBitStrings of unequal size were passed to the constructor.");
		else
		{
			int commonsize=X.size();
			IProblem[] p=new IProblem[commonsize];
			int count=0;
			for(int i=0;i<commonsize;i++)
				p[count++]=new BitEqualizer(X.getBooleanVariable(i),Y.getBooleanVariable(i));
			this.setClauses(new Conjunction(p).getClauses());
		}
	}
}