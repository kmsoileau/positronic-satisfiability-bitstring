/*
 * BitLinkedString.java	1.0 05/12/20
 *
 * Copyright 2005 Positronic Software.
 *
 *
 */
 /**
 * A linked list of IBooleanVariables.
 *
 * @author  Kerry Michael Soileau
 * <blockquote><pre>
 * ksoileau@yahoo.com
 * http://kerrysoileau.com/index.html
 * </pre></blockquote>
 * @version 1.0, 05/12/20
 * @see BooleanVariable
 * @see IBooleanVariable
 * @see BitLinkedStringException
 * @see BitStringException
 */

package positronic.satisfiability.bitstring;

import java.util.LinkedList;
import java.util.List;

import positronic.satisfiability.elements.BooleanVariable;
import positronic.satisfiability.elements.IBooleanVariable;
import positronic.satisfiability.exceptions.BitLinkedStringException;
import positronic.satisfiability.exceptions.BitStringException;

public class BitLinkedString extends LinkedList<IBooleanVariable> implements IBitLinkedString
{
  private static int bLSCount;
	private static final long serialVersionUID = 1L;
  private String name;

  public BitLinkedString() throws Exception
  {
    this("BitLinkedString$"+ bLSCount++,new boolean[0]);
  }

  public BitLinkedString(boolean[] data) throws Exception
  {
    this("BitLinkedString$"+ bLSCount++,data);
  }

  public BitLinkedString(char[] data) throws Exception
  {
    this("BitLinkedString$"+ bLSCount++,data);
  }

  public BitLinkedString(IBitString b) throws Exception
  {
    this(b.getName(),b.getBVArray());
  }

  public BitLinkedString(IBooleanVariable[] data) throws Exception
  {
    this("BitLinkedString$"+ bLSCount++,data);
  }

  public BitLinkedString(int bits) throws Exception
  {
    this("BitLinkedString$"+ bLSCount++,new boolean[bits]);
  }

  public BitLinkedString(String data) throws Exception
  {
    this("BitLinkedString$"+ bLSCount++,data);
  }

  public BitLinkedString(String name, boolean[] data) throws Exception
  {
    if(data==null)
      throw new BitLinkedStringException("Null data passed to constructor.");
    if("".compareTo(name)==0)
      throw new BitLinkedStringException("Null name passed to constructor.");
    this.setName(name);
    //this.setBVData(new IBooleanVariable[data.length]);
    for(int i=0;i<data.length;i++)
      super.add(BooleanVariable.getBooleanVariable(name+"$"+i,data[i]));
    //for(int i=0;i<data.length;i++)
    //  this.setBooleanVariable(i,BooleanVariable.getBooleanVariable(name+"$"+i,data[i]));
  }

  public BitLinkedString(String name, char[] data) throws Exception
  {
  	if(name==null)
      throw new BitLinkedStringException("Null String passed to constructor.");
  	if(data==null)
      throw new BitLinkedStringException("Null char[] passed to constructor.");
    this.setName(name);
    for(int i=0;i<data.length;i++)
    {
      if(data[i]!='0' && data[i]!='1')
        throw new BitLinkedStringException("Construction failed on bad char[] data.");
      if(data[i]=='0')
        this.setBooleanVariable(i,BooleanVariable.getBooleanVariable(name+"$"+i,false));
      if(data[i]=='1')
        this.setBooleanVariable(i,BooleanVariable.getBooleanVariable(name+"$"+i,true));
    }
  }

  public BitLinkedString(String name, IBooleanVariable[] data) throws Exception
  {
    if(data==null)
      throw new BitLinkedStringException("Null data passed to constructor.");
    if(name==null || "".compareTo(name)==0)
      throw new BitLinkedStringException("Null name passed to constructor.");
    this.setName(name);
    for(int i=0;i<data.length;i++)
    {
      if(data[i]==null)
        data[i]=BooleanVariable.getBooleanVariable();
      this.add(BooleanVariable.getBooleanVariable(name+"$"+i,data[i].getValue()));
    }
  }

  public BitLinkedString(String name, int bits) throws Exception
  {
    this(name,new boolean[bits]);
  }

  public BitLinkedString(String name, String data) throws Exception
  {
    this(name,data.toCharArray());
  }

  @Override
public List<IBooleanVariable> asList()
  {
    return this;
  }

  @Override
public Object clone()
  {
    try
    {
    	IBitString b=new BitLinkedString(this.getName(),this.getBVArray());
      return b;
    }
    catch(Exception err)
    {
    	if(err instanceof CloneNotSupportedException)
				try
				{
					throw (CloneNotSupportedException)err;
				} 
    		catch (CloneNotSupportedException e)
				{
					e.printStackTrace();
					return null;
				}
			else
    	{
    		System.err.println("Exception: Attempt to clone BitLinkedString failed.");
    		return null;
    	}
    }
  }

  @Override
public boolean equals(Object anObject)
  {
  	if(anObject==null)
      return false; //Nothing is equal to a null Object.
    if(!(anObject instanceof IBitString))
      return false;
    if(!this.asList().containsAll(extracted(anObject)))
      return false;
    if(!(extracted(anObject)).containsAll(this))
      return false;
    return true;
  }

  @SuppressWarnings("unchecked")
  private List<IBooleanVariable> extracted(Object anObject) 
  {
	  return (List<IBooleanVariable>)anObject;
  }

  @Override
public IBooleanVariable getBooleanVariable(int i) throws Exception
  {
    if(i<0 || this.size()-1<i)
    	throw new BitLinkedStringException("Attempted to use getBooleanVariable outside range.");
    
    return this.get(i);
  }

  @Override
public IBooleanVariable[] getBVArray() throws Exception
  {
    //return getBVArray(this.size());
  	return super.toArray(new IBooleanVariable[0]);
  }

  @Override
public IBooleanVariable[] getBVArray(int n) throws Exception
  {
  	if(n<1)
    	throw new BitLinkedStringException("Passed zero or a negative int to getBVArray method.");
    
    IBooleanVariable[] b=new IBooleanVariable[n];
    for(int i=0;i<this.size() && i<n;i++)
      b[i]=this.getBooleanVariable(i);
    for(int i=this.size();i<n;i++)
      b[i]=BooleanVariable.getBooleanVariable();
    return b;
  }

  /*public IBooleanVariable[] getBVData()
  {
    return (IBooleanVariable[])super.toArray(new IBooleanVariable[0]);
  }*/

  @Override
public String getName()
  {
    return this.name;
  }

  public int index(IBooleanVariable b) throws Exception
  {
  	if(b==null)
    	throw new BitLinkedStringException("Null IBooleanVariable was passed to index method.");
    
    for(int i=0;i<super.size();i++)
      if(this.getBooleanVariable(i).equals(b))
        return i;
    return -1;
  }

  @Override
public void setBooleanVariable(int i, IBooleanVariable ib) throws Exception
  {
  	if(i<0)
      throw new BitLinkedStringException("Attempted to use setBooleanVariable outside range.");
  	if(ib==null)
      throw new BitLinkedStringException("Null IBooleanVariable passed to constructor.");
    while(super.size()<=i)
      super.add(BooleanVariable.getBooleanVariable(this.getName()+"$"+i));
    super.set(i,ib);
  }
  
  /*public void setBVData(IBooleanVariable[] data) throws Exception
  {
  	if(data==null)
    	throw new BitLinkedStringException("Passed null String to setBVData method.");
    for(int i=0;i<data.length;i++)
      super.add(data[i]);
  }*/

  @Override
public void setName(String name) throws Exception
  {
  	if(name==null)
    	throw new BitLinkedStringException("Passed null String to getBVArray method.");
    
    this.name = name;
  }

  @Override
public int size()
  {
    return super.size();
  }

  @Override
public String toBits()
  {
	  String ret="";
	  for(int column=0;column<super.size();column++)
		  	try
	  		{
			  if(this.getBooleanVariable(column).getValue())
				  ret+="1";
			  else
				  ret+="0";
	  		} 
	  		catch (Exception e)
			{
				e.printStackTrace();
			}
	  return ret;
  }

  public boolean[] toBooleanArray() throws Exception
  {
    IBooleanVariable[] a=this.getBVArray();
    boolean[] res=new boolean[a.length];
    for(int i=0;i<res.length;i++)
      res[i]=a[i].getValue();
    return res;
  }

  @Override
public String toString()
  {
    String ret="";
    for(int column=0;column<super.size();column++)
			try
			{
				if(this.getBooleanVariable(column).getValue())
				  ret+="T";
				else
				  ret+="F";
			} catch (Exception e)
			{
				e.printStackTrace();
			}
    return ret;
  }
}