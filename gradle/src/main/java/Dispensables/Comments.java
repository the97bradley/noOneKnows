package Dispensables;

import java.util.ArrayList;

import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.Comment;

public class Comments {
	public boolean tooManyComments(int commentLength)
	{
		if(commentLength>10)
		{
			return true;
		}
		
		
		return false;
	}

}
