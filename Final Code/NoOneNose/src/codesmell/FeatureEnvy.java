package codesmell;


import java.util.HashMap;

import metrixsStructure.MetrixsID;
import projectComponent.ClassContent;
import projectComponent.FileContent;
import projectComponent.MethodContent;
import projectComponent.ProjectContent;

public class FeatureEnvy extends CodeSmell {
	
	private static final MetrixsID[] REQUIRED_METRICS = { MetrixsID.LAA, MetrixsID.ATFD, MetrixsID.FDP };
	
	private double laaThreshold = 0.33;
	private int atfdThreshold = 5;
	private int fdpThreshold = 5;
	
	public FeatureEnvy() {
		super.id = CodeSmellId.FEATURE_ENVY;
		super.requiredMetrics = REQUIRED_METRICS;
		
		super.thresholds = new HashMap<>();
		super.thresholds.put(MetrixsID.LAA.name(), laaThreshold);
		super.thresholds.put(MetrixsID.FDP.name(), fdpThreshold);
		super.thresholds.put(MetrixsID.ATFD.name(), atfdThreshold);
	}

	

	public double getLaaThreshold() {
		return laaThreshold;
	}



	public void setLaaThreshold(double laaThreshold) {
		this.laaThreshold = laaThreshold;
	}



	public int getAtfdThreshold() {
		return atfdThreshold;
	}



	public void setAtfdThreshold(int atfdThreshold) {
		this.atfdThreshold = atfdThreshold;
	}



	public int getFdpThreshold() {
		return fdpThreshold;
	}



	public void setFdpThreshold(int fdpThreshold) {
		this.fdpThreshold = fdpThreshold;
	}



	@Override
	public void detect(FileContent fileContent, ProjectContent projectContent) {
		// TODO Auto-generated method stub
		for(ClassContent cc : fileContent.getClasses())
		{
			for(MethodContent mc : cc.getMethods())
			{
				int attp = mc.getMetrixsContent().getMetrixStruct(MetrixsID.ATFD,Integer.class);
				int fdp = mc.getMetrixsContent().getMetrixStruct(MetrixsID.FDP,Integer.class);
				double laa = mc.getMetrixsContent().getMetrixStruct(MetrixsID.LAA,Double.class);
				if(attp>atfdThreshold&&fdp<fdpThreshold&&laa<laaThreshold)
				{
					mc.getMetrixsContent().setCodeSmell(CodeSmellId.FEATURE_ENVY);
				}
			}
		}

	}

}
