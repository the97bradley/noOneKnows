package codesmell;


import metrixsStructure.MetrixsID;
import projectComponent.ClassContent;
import projectComponent.FileContent;
import projectComponent.MethodContent;
import projectComponent.ProjectContent;

public class ComplexMethod extends CodeSmell {
	
	private static final MetrixsID[] REQUIRED_METRICS = { MetrixsID.CYCLO };

	private int cycloThreshold = 5;
	
	public ComplexMethod()
	{
		super.id = CodeSmellId.COMPLEX_METHOD;
		super.requiredMetrics = REQUIRED_METRICS;
	}

	@Override
	public void detect(FileContent fileContent, ProjectContent projectContent) {
		// TODO Auto-generated method stub
		for(ClassContent cc : fileContent.getClasses())
		{
			for(MethodContent mc:cc.getMethods())
			{
				int cyclo = mc.getMetrixsContent().getMetrixStruct(MetrixsID.CYCLO,Integer.class);
				if(cyclo>cycloThreshold)
				{
					mc.getMetrixsContent().setCodeSmell(CodeSmellId.COMPLEX_METHOD);
				}
			}
		}

	}

}
