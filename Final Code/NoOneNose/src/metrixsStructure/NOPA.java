package metrixsStructure;

import ast.AST;
import ast.AbstractField;
import ast.AbstractType;
import projectComponent.ClassContent;
import projectComponent.FileContent;
import projectComponent.ProjectContent;

public class NOPA extends MetrixsStruct {
	public NOPA()
	{
		super.id = MetrixsID.NOPA;
	}

	@Override
	public void calculate(AST ast, FileContent fileContent, ProjectContent projectContent) {
		// TODO Auto-generated method stub
		for(AbstractType type:ast.getTypes())
		{
			ClassContent cc = fileContent.getClass(type.getName());
			int NOPA = 0;
			for(AbstractField af:type.getFields())
			{
				if(af.getModifiers().contains("public"))
				{
					NOPA++;
				}
			}
			cc.getMetrixsContent().setMetrixStruct(MetrixsID.NOPA,NOPA);
		}

	}

}
