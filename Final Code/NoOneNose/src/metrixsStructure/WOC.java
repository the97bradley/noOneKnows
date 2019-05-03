package metrixsStructure;

import java.math.BigDecimal;

import ast.AST;
import ast.AbstractField;
import ast.AbstractMethod;
import ast.AbstractType;
import projectComponent.ClassContent;
import projectComponent.FileContent;
import projectComponent.ProjectContent;

public class WOC extends MetrixsStruct {
	
	public WOC()
	{
		super.id = MetrixsID.WOC;
	}

	@Override
	public void calculate(AST ast, FileContent fileContent, ProjectContent projectContent) {
		// TODO Auto-generated method stub
		for(AbstractType type:ast.getTypes())
		{
			ClassContent cc = fileContent.getClass(type.getName());
			int publicMember=0;
			int functionalMember = 0;
			
			for(AbstractField af : type.getFields())
			{
				if(af.getModifiers().contains("public"))
				{
					publicMember++;
				}
			}
			
			for(AbstractMethod am:type.getMethods())
			{
				if(am.getModifiers().contains("public"))
				{
					publicMember++;
					if(!am.isAccessor())
					{
						functionalMember++;
					}
				}
			}
			double result = (publicMember == 0)?0:functionalMember*1.0/publicMember;
			double finalResult = new BigDecimal(result).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			cc.getMetrixsContent().setMetrixStruct(MetrixsID.WOC, finalResult);
		}

	}

}
