package codesmell;

import java.util.HashMap;

import metrixsStructure.MetrixsID;
import projectComponent.ClassContent;
import projectComponent.FileContent;
import projectComponent.ProjectContent;

public class DataClass extends CodeSmell {
	
	private static final MetrixsID[] REQUIRED_METRIXS = {MetrixsID.WMC,MetrixsID.NOPA,MetrixsID.NOAM,MetrixsID.WOC};
	
	private double wocThreshold = 0.5;
	private int wmcThreshold1 = 20;
	private int wmcThreshold2 = 50;
	private int publicMembersThreshold1 = 5;
	private int publicMembersThreshold2 = 8;
	
	public DataClass()
	{
		super.id = CodeSmellId.DATA_CLASS;
		super.requiredMetrics = REQUIRED_METRIXS;
		super.thresholds = new HashMap<>();
		super.thresholds.put(MetrixsID.WOC.name(), wocThreshold);
		super.thresholds.put(MetrixsID.WMC.name(), wmcThreshold1);
		super.thresholds.put(MetrixsID.WMC.name(), wmcThreshold2);
		super.thresholds.put(MetrixsID.NOPA.name()+" and "+MetrixsID.NOAM.name(), publicMembersThreshold1);
		super.thresholds.put(MetrixsID.NOPA.name()+" and "+MetrixsID.NOAM.name(), publicMembersThreshold1);
	}

	@Override
	public void detect(FileContent fileContent, ProjectContent projectContent) {
		// TODO Auto-generated method stub
		for(ClassContent cc : fileContent.getClasses())
		{
			int wmc = cc.getMetrixsContent().getMetrixStruct(MetrixsID.WMC,Integer.class);
			double woc = cc.getMetrixsContent().getMetrixStruct(MetrixsID.WOC,Double.class);
			int NOPA = cc.getMetrixsContent().getMetrixStruct(MetrixsID.NOPA,Integer.class);
			int NOAM = cc.getMetrixsContent().getMetrixStruct(MetrixsID.NOPA,Integer.class);
			int publicMember = NOPA+NOAM;
			boolean data = (publicMember > publicMembersThreshold1 && wmc < wmcThreshold1)|| (publicMember > publicMembersThreshold2 && wmc < wmcThreshold2);
			if(data&&woc<wocThreshold)
			{
				cc.getMetrixsContent().setCodeSmell(CodeSmellId.DATA_CLASS);
			}

			
		}

	}

}
