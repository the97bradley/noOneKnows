package metrixsStructure;

import ast.AST;
import ast.AbstractMethod;
import ast.AbstractType;
import projectComponent.ClassContent;
import projectComponent.FileContent;
import projectComponent.MethodContent;
import projectComponent.ProjectContent;

public class WMC extends MetrixsStruct {
	private static final MetrixsID[] REQUIRED_METRIXS = {MetrixsID.CYCLO};
	
	public WMC()
	{
		super.id = MetrixsID.WMC;
		super.requiredID = REQUIRED_METRIXS;
	}

	@Override
	public void calculate(AST ast, FileContent fileContent, ProjectContent projectContent) {
		// TODO Auto-generated method stub
		for(AbstractType type:ast.getTypes())
		{
			int WMC = 0;
			ClassContent cc = fileContent.getClass(type.getName());
			
			for(AbstractMethod am:type.getMethods())
			{
				MethodContent mc = cc.getMethodByName(am.getName());
				WMC+=mc.getMetrixsContent().getMetrixStruct(MetrixsID.CYCLO,Integer.class);
				
			}
			if(!type.getMethods().isEmpty())
			{
				cc.getMetrixsContent().setMetrixStruct(MetrixsID.WMC, WMC);
			}
		}
		
	}

}
