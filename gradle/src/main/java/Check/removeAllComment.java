package Check;

import com.github.javaparser.ast.Node;

public class removeAllComment {
	public static Node removeAllComments(Node node)
	{
		node = node.removeComment();
		for(int i =0;i<node.getChildNodes().size();i++)
		{
			removeAllComments(node.getChildNodes().get(i));
		}
		return node;
	}

}
