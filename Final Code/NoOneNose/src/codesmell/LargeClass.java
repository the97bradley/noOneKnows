package codesmell;

import java.util.HashMap;

import metrixsStructure.MetrixsID;
import projectComponent.ClassContent;
import projectComponent.FileContent;
import projectComponent.ProjectContent;

public class LargeClass extends CodeSmell{
	
	private static final MetrixsID[] REQUIRED_METRIX = {MetrixsID.LOC};
	private int THRESHOLD = 100;
	
	public LargeClass()
	{
		
		init();
	}
	
	public int getTHRESHOLD() {
		return THRESHOLD;
	}

	public void setTHRESHOLD(int tHRESHOLD) {
		THRESHOLD = tHRESHOLD;
	}

	private void init()
	{
		super.id = CodeSmellId.LARGE_CLASS;
		super.thresholds = new HashMap<>();
		super.thresholds.put("MetrixsID.LOC.name()", THRESHOLD);
		super.requiredMetrics = REQUIRED_METRIX;
	}
	
	@Override
	public void detect(FileContent fileContent, ProjectContent projectContent) {
		// TODO Auto-generated method stub
		for(ClassContent cc:fileContent.getClasses())
		{
			int loc = cc.getMetrixsContent().getMetrixStruct(MetrixsID.LOC,Integer.class);
			if(loc>THRESHOLD)
			{
				cc.getMetrixsContent().setCodeSmell(CodeSmellId.LARGE_CLASS);;
			}
		}
		
	}
	
	

}
