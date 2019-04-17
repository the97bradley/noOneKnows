package Check;

import java.util.ArrayList;
import java.util.List;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;

public class SetUpCu {
	
	
	public CompilationUnit setUpCuNoComment(CompilationUnit cu) {
		// TODO Auto-generated method stub
		Node tem = cu.clone();
		return (CompilationUnit) removeAllComment.removeAllComments(tem);
		
	}
	
	
	public List<MethodDeclaration> setUpMethods(CompilationUnit cu) {
		// TODO Auto-generated method stub
		List<MethodDeclaration> resultMethods = new ArrayList<MethodDeclaration>();

		if(cu.getTypes().size()>0)
		{
			for(int i = 0;i<cu.getTypes().size();i++)
			{
				if(!(cu.getType(i).getMethods().isEmpty()))
				{
					resultMethods.addAll(cu.getType(i).getMethods());
				}
				
			}
		}
		return resultMethods;
		
		
		
	}
	
	public List<FieldDeclaration> setUpField(CompilationUnit cu) {
		// TODO Auto-generated method stub
		List<FieldDeclaration> resultFields = new ArrayList<FieldDeclaration>();

		if(cu.getTypes().size()>0)
		{
			for(int i = 0;i<cu.getTypes().size();i++)
			{
				if(!(cu.getType(i).getFields().isEmpty()))
				{
					resultFields.addAll(cu.getType(i).getFields());
				}
				
			}
		}
		return resultFields;
		
		
		
	}

}
