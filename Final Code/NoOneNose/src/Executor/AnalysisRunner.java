package Executor;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import ast.AST;
import ast.AbstractMethod;
import ast.AbstractType;
import codesmell.CodeSmell;
import codesmell.CodeSmellFactory;
import codesmell.CodeSmellId;
import metrixsStructure.MetrixFactory;
import metrixsStructure.MetrixsID;
import metrixsStructure.MetrixsStruct;
import parser.JavaParser;
import projectComponent.ClassContent;
import projectComponent.FileContent;
import projectComponent.MethodContent;
import projectComponent.ProjectContent;

public class AnalysisRunner {

	private ProjectContent projectContent = new ProjectContent();
	private Map<MetrixsID, MetrixsStruct> metricsToCalculate = new LinkedHashMap<>();
	private Map<CodeSmellId, CodeSmell> codeSmellsToDetect = new LinkedHashMap<>();
	private JavaParser parser = new JavaParser();

	private String repository;
	private ArrayList<File>myFile;
	
	public AnalysisRunner(String repository, ArrayList<File> listFiles) {
		this.repository = repository;
		this.myFile = listFiles;
	}

	public Collection<MetrixsStruct> getCalculatedMetrics() {
		return metricsToCalculate.values();
	}

	public Collection<CodeSmell> getDetectedCodeSmells() {
		return codeSmellsToDetect.values();
	}

	public void setCodeMetrics(List<MetrixsStruct> codeMetrics) {
		if (codeMetrics == null) {
			return;
		}

		for (MetrixsStruct metric : codeMetrics) {
			visitMetric(metric);
		}
	}

	public void setCodeSmells(List<CodeSmell> codeSmells) {
		if (codeSmells == null) {
			return;
		}

		for (CodeSmell codeSmell : codeSmells) {
			visitCodeSmell(codeSmell);
		}
	}

	


	public String run() throws IOException {
		for (File file : myFile) {
			analyzeFile(file, repository);
		}

		for (CodeSmell codeSmell : codeSmellsToDetect.values()) {
			for (FileContent fr : projectContent.getAllFiles()) {
				codeSmell.detect(fr, projectContent);
			}
		}
		return projectContent.toString();
	}
	


	private void analyzeFile(File file, String repository) throws IOException {

		if (parser == null) {
			return;
		}

		String filename = FilenameUtils.normalize(file.getAbsolutePath(), true).substring(repository.length() + 1);

		AST ast = parser.generate(filename, FileUtils.readFileToString(file, "UTF-8"));

		FileContent fr = new FileContent(ast.getFileName());
		for (AbstractType type : ast.getTypes()) {
			ClassContent cr = new ClassContent(type.getName(), type.getNodeType().toString());
			fr.getClassContents().put(type.getName(), cr);
			for (AbstractMethod method : type.getMethods()) {
				MethodContent mr = new MethodContent(method.getName());
				cr.getMethodsContent().put(method.getName(), mr);
			}
		}
		projectContent.addFileContent(fr);

		for (MetrixsStruct metric : metricsToCalculate.values()) {
			metric.calculate(ast, fr, projectContent);
		}
		
	}

	private void visitMetric(MetrixsStruct codeMetric) {
		if (codeMetric.getRequiredID() != null) {
			for (MetrixsID id : codeMetric.getRequiredID()) {
				if (!metricsToCalculate.containsKey(id)) {
					visitMetric(MetrixFactory.getMetric(id));
				}
			}
		}

		if (!metricsToCalculate.containsKey(codeMetric.getID())) {
			metricsToCalculate.put(codeMetric.getID(), codeMetric);
		}
	}


	private void visitCodeSmell(CodeSmell codeSmellParam) {
		if (codeSmellParam.getRequiredMetrics() != null) {
			for (MetrixsID id : codeSmellParam.getRequiredMetrics()) {
				visitMetric(MetrixFactory.getMetric(id));
			}
		}

		if (codeSmellParam.getRequiredCodesmells() != null) {
			for (CodeSmellId id : codeSmellParam.getRequiredCodesmells()) {
				if (!codeSmellsToDetect.containsKey(id)) {
					visitCodeSmell(CodeSmellFactory.getCodeSmell(id));
				}
			}
		}

		if (!codeSmellsToDetect.containsKey(codeSmellParam.getId())) {
			codeSmellsToDetect.put(codeSmellParam.getId(), codeSmellParam);
		}
	}

}