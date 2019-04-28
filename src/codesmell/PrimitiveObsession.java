package codesmell;

import java.util.HashMap;

import metrixsStructure.MetrixsID;
import projectComponent.ClassContent;
import projectComponent.FileContent;
import projectComponent.ProjectContent;

public class PrimitiveObsession extends CodeSmell{
	
	private static final MetrixsID[] REQUIRED_METRICS = {MetrixsID.NOF};

	private int THRESHOLD = 5;
	
	public PrimitiveObsession()
	{
		super.id = CodeSmellId.PRIMITIVE_OBSESSION;
		super.thresholds = new HashMap<>();
		super.thresholds.put(MetrixsID.NOF.name(), THRESHOLD);
		super.requiredMetrics = REQUIRED_METRICS;
	}

	@Override
	public void detect(FileContent fileContent, ProjectContent projectContent) {
		// TODO Auto-generated method stub
		for(ClassContent cc : fileContent.getClasses())
		{
			int priField = cc.getMetrixsContent().getMetrixStruct(MetrixsID.NOF,Integer.class);
			if(priField>THRESHOLD)
			{
				cc.getMetrixsContent().setCodeSmell(CodeSmellId.PRIMITIVE_OBSESSION);
			}
		}
	}

}
