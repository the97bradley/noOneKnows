package Bloaters;

import com.github.javaparser.ast.body.MethodDeclaration;

public class LongParameter {
	
	public boolean isLongParameter(MethodDeclaration method)
	{
		if(method.getParameters().size()>4)
		{
			return true;
		}
		return false;
	}

}
