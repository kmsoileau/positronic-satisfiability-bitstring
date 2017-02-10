/*
 * BitStringOrer.java	1.0 05/04/15
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

package positronic.satisfiability.bitstring;

import positronic.satisfiability.elements.BitOrer;
import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;

public class BitStringOrer extends MetaProblem implements IProblem
{
	private static final long serialVersionUID = -7404743827141134121L;

	public BitStringOrer(IBitString X, IBitString Y, IBitString Z) throws Exception
	{
		if(X.size()!=Y.size() || X.size()!=Z.size())
			this.setClauses(MetaProblem.unsolvableProblem().getClauses());
		else
		{
			int commonsize=X.size();
			IProblem[] p=new IProblem[commonsize];
			int count=0;
			for(int i=0;i<commonsize;i++)
				p[count++]=new BitOrer(X.getBooleanVariable(i),Y.getBooleanVariable(i),Z.getBooleanVariable(i));
			this.setClauses(new Conjunction(p).getClauses());
		}
	}
}