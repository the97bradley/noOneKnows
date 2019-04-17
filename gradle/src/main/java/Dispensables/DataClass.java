package Dispensables;

public class DataClass {

	public boolean isDataClass(int methodNum,int fieldNum)
	{
		if(methodNum<2&&fieldNum>8)
		{
			return true;
		}
		
		return false;
	}
}
