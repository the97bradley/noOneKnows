package codesmell;

import java.util.HashMap;

import metrixsStructure.MetrixsID;
import projectComponent.ClassContent;
import projectComponent.FileContent;
import projectComponent.MethodContent;
import projectComponent.ProjectContent;

public class LongParameterList extends CodeSmell{
	
	private static final MetrixsID[] REQUIRED_METRICS = {MetrixsID.NOP};
	private int THRESHOLD = 3;
	
	public LongParameterList()
	{
		super.id = CodeSmellId.LONG_PARAMETER_LIST;	
		super.requiredMetrics = REQUIRED_METRICS;
		super.thresholds = new HashMap<>();
		super.thresholds.put(MetrixsID.NOP.name(), THRESHOLD);
		
	}

	@Override
	public void detect(FileContent fileContent, ProjectContent projectContent) {
		// TODO Auto-generated method stub
		for(ClassContent cc:fileContent.getClasses())
		{
			for(MethodContent mc:cc.getMethods())
			{
				if(mc.getMetrixsContent().getMetrixStruct(MetrixsID.NOP,Integer.class)>THRESHOLD)
				{
					mc.getMetrixsContent().setCodeSmell(CodeSmellId.LONG_PARAMETER_LIST);
				}
			}
		}
		
	}

}
