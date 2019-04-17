package Object_Orientation_Abusers;

import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.body.MethodDeclaration;

import Check.LookUp;

public class Switch_Statements {

	public boolean isSwitchStatement(MethodDeclaration method)
	{
		return method.toString().contains("switch(");
		
		
		

		
		
	}


	
}
