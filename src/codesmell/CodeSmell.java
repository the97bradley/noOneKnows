package codesmell;

import java.util.Map;

import metrixsStructure.MetrixsID;
import projectComponent.FileContent;
import projectComponent.ProjectContent;



public abstract class CodeSmell {
	
	protected CodeSmellId id;
	protected Map<String, Number> thresholds;
	protected MetrixsID[] requiredMetrics;
	protected CodeSmellId[] requiredCodesmells;
	
	public abstract void detect(FileContent fileContent, ProjectContent projectContent);

	public CodeSmellId getId() {
		return id;
	}

	public MetrixsID[] getRequiredMetrics() {
		return requiredMetrics;
	}

	public Map<String, Number> getThresholds() {
		return thresholds;
	}

	public CodeSmellId[] getRequiredCodesmells() {
		return requiredCodesmells;
	}
	
	
}
