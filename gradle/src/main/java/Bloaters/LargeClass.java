package Bloaters;

public class LargeClass {
	public boolean isLargeClass(int ClassLength)
	{
		boolean result = false;
		if(ClassLength>110)
		{
			result = true;
		}
		return result;
	}

}
