package positronic.satisfiability.bitstring;

public class Walk
{
	private IBitString[] nodes;

	public Walk(IBitString[] nodes) 
	{
		this.nodes=nodes;
	}
	
	public Walk(int length, int bits) throws Exception 
	{
		this(new IBitString[length]);
		for(int i=0;i<length;i++)
			nodes[i]=new BitString(bits);
	}
	
	public IBitString getNode(int i) 
	{
		return this.nodes[i];
	}

	public int length()
	{
		return this.nodes.length;
	}

	public void setNode(int i,IBitString val) 
	{
		this.nodes[i] = val;
	}
}
