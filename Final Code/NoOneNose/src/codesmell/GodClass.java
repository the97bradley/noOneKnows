package codesmell;


import java.util.HashMap;

import metrixsStructure.MetrixsID;
import projectComponent.ClassContent;
import projectComponent.FileContent;
import projectComponent.ProjectContent;

public class GodClass extends CodeSmell {
	private static final MetrixsID[] REQUIRED_METRICS = { MetrixsID.ATFD, MetrixsID.WMC, MetrixsID.TCC };

	private int atfdThreshold = 5;
	private int wmcThreshold = 5;
	private double tccThreshold = 0.33;
	
	public GodClass()
	{
		super.id = CodeSmellId.GOD_CLASS;
		super.requiredMetrics = REQUIRED_METRICS;
		super.thresholds = new HashMap<>();
		super.thresholds.put(MetrixsID.ATFD.name(), atfdThreshold);
		super.thresholds.put(MetrixsID.WMC.name(), wmcThreshold);
		super.thresholds.put(MetrixsID.TCC.name(), tccThreshold);
	}
	@Override
	public void detect(FileContent fileContent, ProjectContent projectContent) {
		// TODO Auto-generated method stub
		for(ClassContent cc:fileContent.getClasses())
		{
			int atfd = cc.getMetrixsContent().getMetrixStruct(MetrixsID.ATFD,Integer.class);
			int wmc = cc.getMetrixsContent().getMetrixStruct(MetrixsID.WMC,Integer.class);
			double tcc = cc.getMetrixsContent().getMetrixStruct(MetrixsID.TCC,Double.class);
			
			if((atfd > atfdThreshold) && (wmc >= wmcThreshold) && (tcc < tccThreshold))
			{
				cc.getMetrixsContent().setCodeSmell(CodeSmellId.GOD_CLASS);
			}
		}

	}

}
