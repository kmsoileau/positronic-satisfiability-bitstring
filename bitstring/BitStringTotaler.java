/*
 * BitStringTotaler.java	1.0 05/04/15
 *
 * Copyright 2004-2005 Positronic Software.
 *
 *
 */

package positronic.satisfiability.bitstring;

import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;
import positronic.satisfiability.naturalnumber.INaturalNumber;
import positronic.satisfiability.naturalnumber.NaturalNumber;
import positronic.satisfiability.naturalnumber.NaturalNumberAdder;
import positronic.satisfiability.naturalnumber.NaturalNumberEqualizer;
import positronic.satisfiability.naturalnumber.NaturalNumberFromBitConverter;

public class BitStringTotaler extends MetaProblem implements IProblem
{
  private static final long serialVersionUID = 1L;

  public BitStringTotaler(IBitString X, INaturalNumber Z) throws Exception
  {
    INaturalNumber[] N=new INaturalNumber[X.size()];
    NaturalNumberFromBitConverter[] nnfbc=new NaturalNumberFromBitConverter[X.size()];
    for(int i=0;i<N.length;i++)
    {
      N[i]=new NaturalNumber();
      nnfbc[i]=new NaturalNumberFromBitConverter(N[i],X.getBooleanVariable(i));
    }
    IProblem problem=new Conjunction(nnfbc);

    if(N.length>1)
    {
      NaturalNumberAdder[] ad=new NaturalNumberAdder[N.length-1];
      INaturalNumber[] subtotal=new INaturalNumber[N.length-1];
      for(int i=0;i<subtotal.length;i++)
        subtotal[i]=new NaturalNumber("subTotal$"+i);
      ad[0]=new NaturalNumberAdder(N[0],N[1],subtotal[0]);
      for(int i=1;i<N.length-1;i++)
        ad[i]=new NaturalNumberAdder(subtotal[i-1],N[i+1],subtotal[i]);
      problem=new Conjunction(problem,new Conjunction(ad),new NaturalNumberEqualizer(Z,subtotal[N.length-2]));
    }
    else
      problem=new Conjunction(problem,new NaturalNumberEqualizer(Z,N[0]));

    this.setClauses(problem.getClauses());
  }
}