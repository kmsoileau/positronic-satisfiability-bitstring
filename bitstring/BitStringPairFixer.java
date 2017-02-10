/*
 * BitStringPairFixer.java	1.0 10/04/22
 *
 * Copyright 2010 Positronic Software.
 *
 *
 */

package positronic.satisfiability.bitstring;

import positronic.satisfiability.elements.BitFixer;
import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.IBooleanVariable;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;
import positronic.satisfiability.exceptions.BitStringFixerException;

public class BitStringPairFixer extends MetaProblem implements IProblem
{
  private static final long serialVersionUID = -2635396533022255719L;

  public BitStringPairFixer(IBitString target1, IBitString target2,
		  boolean[] c1, boolean[] c2) throws Exception
  {
  	IProblem p=new Conjunction(new BitStringFixer(target1,c1),
  			new BitStringFixer(target2,c2));
  	this.setClauses(p.getClauses());
  }

  public BitStringPairFixer(IBitString target1, IBitString target2,
		  IBitString data1, IBitString data2) throws Exception
  {
    this(target1,target2,data1.getBVArray(),data2.getBVArray());
  }

  public BitStringPairFixer(IBitString target1, IBitString target2,
		  IBooleanVariable[] data1, IBooleanVariable[] data2) throws Exception{
    if(target1.size()!=data1.length||target2.size()!=data2.length)
      this.setClauses(MetaProblem.unsolvableProblem().getClauses());
    else
    {
      BitStringBitFixer[] bnnbf1=new BitStringBitFixer[data1.length];
      for(int i=0;i<bnnbf1.length;i++)
        bnnbf1[i]=new BitStringBitFixer(target1,i,data1[i].getValue());
      BitStringBitFixer[] bnnbf2=new BitStringBitFixer[data2.length];
      for(int i=0;i<bnnbf2.length;i++)
        bnnbf2[i]=new BitStringBitFixer(target2,i,data2[i].getValue());
      IProblem p=new Conjunction(new Conjunction(bnnbf1),new Conjunction(bnnbf2));
      this.setClauses(p.getClauses());
    }
  }

  public BitStringPairFixer(IBitString target1,IBitString target2,
		  String s1,String s2) throws Exception
  {
  	int j1=Math.min(s1.length(),target1.size());
  	IProblem[] p1=new MetaProblem[j1];
  	for(int i=0;i<j1;i++)
  	{
  		if(s1.charAt(i)!='0' && s1.charAt(i)!='1')
  			throw new BitStringFixerException("Attempted to fix an IBitString using improperly formatted data.");
  		if(s1.charAt(i)=='0')
  			p1[i]=new BitFixer(target1.getBooleanVariable(i),false);
  		if(s1.charAt(i)=='1')
  			p1[i]=new BitFixer(target1.getBooleanVariable(i),true);
  	}
  	int j2=Math.min(s2.length(),target2.size());
  	IProblem[] p2=new MetaProblem[j2];
  	for(int i=0;i<j2;i++)
  	{
  		if(s2.charAt(i)!='0' && s2.charAt(i)!='1')
  			throw new BitStringFixerException("Attempted to fix an IBitString using improperly formatted data.");
  		if(s2.charAt(i)=='0')
  			p2[i]=new BitFixer(target2.getBooleanVariable(i),false);
  		if(s2.charAt(i)=='1')
  			p2[i]=new BitFixer(target2.getBooleanVariable(i),true);
  	}
  	IProblem pp=new Conjunction(new Conjunction(p1),new Conjunction(p2));
  	
  	this.setClauses(pp.getClauses());
  }
}