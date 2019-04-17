package Check;

import com.github.javaparser.ast.Node;

public class LookUp {
	
	public static boolean LookUpWithoutComment(Node node,String s)
	{
		node = node.removeComment();
		
		 if(node.toString().equals(s))
		{
			return true;
		}
		 else if(node.getChildNodes().toString()=="[]")
		{
			return false;
		}
		else
		{
			boolean result = false;
			
			 for(int i=0;i<node.getChildNodes().size();i++)
				{
					result = result||LookUpWithoutComment(node.getChildNodes().get(i),s);
				}
				
			return result;
		}
		
		
		
	}

}
