package TestClass;

public class GodClass {
	
	private DataClass data = new DataClass();
	private LargeClass large = new LargeClass();
	public void god()
	{
		int a = data.a;
		int b = (int)data.b;
		for(int i =0;i<2;i++)
		{
			if(i==10)
			{
				for(int j=0;j<1;j++)
				{
					if(j==2)
					{
						return;
					}
					
				}
			}
		}
		
		int c =  (int)data.c;
		data.setA(large.calc(a, b,c));
	}

}
