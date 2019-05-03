package metrixsStructure;

import ast.AST;
import ast.AbstractMethod;
import ast.AbstractType;
import projectComponent.ClassContent;
import projectComponent.FileContent;
import projectComponent.MethodContent;
import projectComponent.ProjectContent;

public class NOP extends MetrixsStruct{
	public NOP()
	{
		super.id = MetrixsID.NOP;
	}

	@Override
	public void calculate(AST ast, FileContent fileContent, ProjectContent projectContent) {
		// TODO Auto-generated method stub
		for(AbstractType at:ast.getTypes())
		{
			ClassContent cc = fileContent.getClass(at.getName());
			for(AbstractMethod am:at.getMethods())
			{
				MethodContent mc = cc.getMethodByName(am.getName());
				int p = am.getParameters().size();
				mc.getMetrixsContent().setMetrixStruct(MetrixsID.NOP, p);
			}
		}
	}
}
