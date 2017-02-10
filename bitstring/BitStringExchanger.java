package positronic.satisfiability.bitstring;

import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;

public class BitStringExchanger extends MetaProblem implements IProblem
{
	private static final long serialVersionUID = -2232899099998480968L;

	public BitStringExchanger(IBitString xBefore, IBitString yBefore,
									IBitString xAfter, IBitString yAfter) throws Exception
	{
		IProblem p=new Conjunction(
    		new BitStringEqualizer(xBefore,yAfter),
    		new BitStringEqualizer(yBefore,xAfter));

		this.setClauses(p.getClauses());
	}
}
