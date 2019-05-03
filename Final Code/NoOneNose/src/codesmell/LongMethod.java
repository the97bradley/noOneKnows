package codesmell;

import java.util.HashMap;

import metrixsStructure.MetrixsID;
import projectComponent.ClassContent;
import projectComponent.FileContent;
import projectComponent.MethodContent;
import projectComponent.ProjectContent;

public class LongMethod extends CodeSmell{
	
	private static final MetrixsID[] REQUIRED_METRICS = {MetrixsID.LOC};

	private int mlocThreshold = 40;
	
	public LongMethod() {
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		super.id = CodeSmellId.LONG_METHOD;
		super.requiredMetrics= REQUIRED_METRICS;
		super.thresholds = new HashMap<>();
		super.thresholds.put(MetrixsID.LOC.name(), mlocThreshold);
		
		
	}

	@Override
	public void detect(FileContent fileContent, ProjectContent projectContent) {
		// TODO Auto-generated method stub
		for(ClassContent cc: fileContent.getClasses())
		{
			for(MethodContent mc:cc.getMethods())
			{
				int loc = mc.getMetrixsContent().getMetrixStruct(MetrixsID.LOC,Integer.class);
				if(loc>mlocThreshold)
				{
					mc.getMetrixsContent().setCodeSmell(CodeSmellId.LONG_METHOD);
				}
			}
		}
		
	}
	
	public int getMlocThreshold() {
		return mlocThreshold;
	}
	

}
