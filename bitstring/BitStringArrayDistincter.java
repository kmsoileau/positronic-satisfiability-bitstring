/*
 * BitStringArrayDistincter.java	1.0 10/03/25
 *
 * Copyright 2010 Positronic Software.
 *
 * This IProblem is satisfied if the IBitStrings in the parameter array X are unequal.
 * For instance, if 
 * IProblem P=BitStringArrayDistincter(IBitString[]{X1,X2,X3});
 * has been executed, then the IProblem P will be satisfied if
 * X1<>X2 and X1<>X2 and X2<>X3 are all true.
 */

package positronic.satisfiability.bitstring;

import positronic.satisfiability.elements.Conjunction;
import positronic.satisfiability.elements.IProblem;
import positronic.satisfiability.elements.MetaProblem;

public class BitStringArrayDistincter extends MetaProblem implements IProblem
{
  private static final long serialVersionUID = 8310339016366643917L;

	public BitStringArrayDistincter(IBitString[] X) throws Exception
  {
  	IProblem problem=null;
  	for(int i=0;i<X.length;i++)
  		for(int j=i+1;j<X.length;j++)
  			problem=new Conjunction(problem,new BitStringUnequalizer(X[i],X[j]));
  		
    this.setClauses(problem.getClauses());
  }
}