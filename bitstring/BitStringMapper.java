/*
 * BitStringMapper.java	1.0 05/04/21
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

package positronic.satisfiability.bitstring;

import positronic.satisfiability.bitstringlist.IBitStringList;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.Mapper;
import positronic.satisfiability.elements.MetaProblem;

public class BitStringMapper extends MetaProblem implements IProblem
{
  private static final long serialVersionUID = 1L;
  private IBitString domainVariable;
  private IBitString rangeVariable;

  public BitStringMapper(IBitString[] x, IBitString[] y, IBitString X, IBitString Y) throws Exception
  {
    if(x.length == 0 || y.length == 0 || x.length != y.length)
      this.setClauses(MetaProblem.unsolvableProblem().getClauses());
    else
    {
      this.domainVariable=X;
      this.rangeVariable=Y;
      IProblem[] px=new MetaProblem[x.length];
      IProblem[] py=new MetaProblem[y.length];
      for(int i=0;i<x.length;i++)
      {
        px[i]=new BitStringFixer(this.domainVariable,x[i]);
        py[i]=new BitStringFixer(this.rangeVariable,y[i]);
      }
      IProblem pcomb=new Mapper(px,py);
      this.setClauses(pcomb.getClauses());
    }
  }

  public BitStringMapper(IBitStringList x, IBitStringList y, IBitString X, IBitString Y) throws Exception
  {
    this(x.toArray(new IBitString[0]),
      y.toArray(new IBitString[0]),X,Y);
  }

	public IBitString getDomainVariable()
	{
		return domainVariable;
	}

	public IBitString getRangeVariable()
	{
		return rangeVariable;
	}

	public void setDomainVariable(IBitString domainVariable)
	{
		this.domainVariable = domainVariable;
	}

	public void setRangeVariable(IBitString rangeVariable)
	{
		this.rangeVariable = rangeVariable;
	}
}