package codesmell;

import java.util.HashMap;

import metrixsStructure.MetrixsID;
import projectComponent.ClassContent;
import projectComponent.FileContent;
import projectComponent.MethodContent;
import projectComponent.ProjectContent;

public class SwitchStatement extends CodeSmell{
	
	private static final MetrixsID[] REQUIRED_METRICS = { MetrixsID.SW};
	private int THRESHOLD = 1;
	public SwitchStatement()
	{
		super.id = CodeSmellId.SWITCH_STATEMENT;
		super.requiredMetrics = REQUIRED_METRICS;
		super.thresholds = new HashMap<>();
		super.thresholds.put(MetrixsID.SW.name(), THRESHOLD);
	}

	@Override
	public void detect(FileContent fileContent, ProjectContent projectContent) {
		// TODO Auto-generated method stub
		for(ClassContent cc: fileContent.getClasses())
		{
			for(MethodContent mc:cc.getMethods())
			{
				if(mc.getMetrixsContent().hasMetrixStruct(MetrixsID.SW))
				{
					if(mc.getMetrixsContent().getMetrixStruct(MetrixsID.SW,Integer.class)>=1)
					{
						mc.getMetrixsContent().setCodeSmell(CodeSmellId.SWITCH_STATEMENT);
						
					}
					
				}
			}
		}
	}

}
