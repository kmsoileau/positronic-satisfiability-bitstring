package positronic.satisfiability.bitstring;

public class BitStringPair
{
	private IBitString left;
	private IBitString right;
	
	public BitStringPair(IBitString left,IBitString right)
	{
		this.setLeft(left);
		this.setRight(right);
	}
	
	@Override
	public boolean equals(Object o)
	{
		if(o==null || !(o instanceof BitStringPair))
			return false;
		BitStringPair currbsp=(BitStringPair)o;
		if(!(this.getLeft().equals(currbsp.getLeft())))
			return false;
		if(!(this.getRight().equals(currbsp.getRight())))
			return false;
		return true;
	}
	
	public IBitString getLeft()
	{
		return left;
	}
	
	public IBitString getRight()
	{
		return right;
	}
	
	public void setLeft(IBitString left)
	{
		this.left = left;
	}
	
	public void setRight(IBitString right)
	{
		this.right = right;
	}
	
	@Override
	public String toString()
	{
		String res="{";
		res+=left;
		res+=",";
		res+=right;
		return res+"}";
	}
}
