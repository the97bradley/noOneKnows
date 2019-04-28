import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.bson.Document;
import org.bson.types.ObjectId;

import ast.AST;
import ast.AbstractMethod;
import ast.AbstractType;
import codesmell.CodeSmell;
import codesmell.CodeSmellFactory;
import codesmell.CodeSmellId;
import metrixsStructure.MetrixFactory;
import metrixsStructure.MetrixsID;
import metrixsStructure.MetrixsStruct;
import parser.Language;
import parser.Parser;
import projectComponent.ClassContent;
import projectComponent.FileContent;
import projectComponent.MethodContent;
import projectComponent.ProjectContent;
import projectComponent.RMFileUtils;

public class AnalysisRunner {

	private ProjectContent projectContent = new ProjectContent();

	private Map<MetrixsID, MetrixsStruct> metricsToCalculate = new LinkedHashMap<>();
	private Map<CodeSmellId, CodeSmell> codeSmellsToDetect = new LinkedHashMap<>();
	private Map<String, Parser> parsersToUse = new LinkedHashMap<>();
	private Map<Language, String[]> sourceFolders = new LinkedHashMap<>();

	private String repository;

	public AnalysisRunner(String repository) {
		this.repository = repository;
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

	public void setParsers(List<Parser> parsers) {
		for (Parser p : parsers) {
			if (p.getSourceFolders() == null || p.getSourceFolders().length == 0) 
			{
				p.setSourceFolders(RMFileUtils.getAllDirsAsString(repository).toArray(new String[0]));
			} 
			else {
				p.setSourceFolders(RMFileUtils.concatFilePath(repository, p.getSourceFolders()));
			}

			for (String ext : p.getExtensions()) {
				parsersToUse.put(ext, p);
			}
		}
	}
	


	public void run() throws IOException {
		for (File file : FileUtils.listFiles(new File(repository), parsersToUse.keySet().toArray(new String[0]),
				true)) {
			analyzeFile(file, repository);
		}

		for (MetrixsStruct metric : metricsToCalculate.values()) {
			metric.clean(projectContent);
		}

		for (CodeSmell codeSmell : codeSmellsToDetect.values()) {
			for (FileContent fr : projectContent.getAllFiles()) {
				codeSmell.detect(fr, projectContent);
			}
		}
		System.out.println(projectContent);

		//persistData(analysisReportId, commit, repoId, reference);
	}
	


	private void analyzeFile(File file, String repository) throws IOException {
		Parser parser = parsersToUse.get(FilenameUtils.getExtension(file.getAbsolutePath()));
		if (parser == null) {
			return;
		}

		String filename = FilenameUtils.normalize(file.getAbsolutePath(), true).substring(repository.length() + 1);

		AST ast = parser.generate(filename, FileUtils.readFileToString(file, "UTF-8"),
				sourceFolders.get(parser.getId()));

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

	private void persistData(ObjectId analysisReportId, Commit commit, ObjectId repoId, String reference) {
		CodeAnalysis dao = new CodeAnalysis();
		List<Document> documents = new ArrayList<>();
		

		int i = 0;
		for (FileContent fr : projectContent.getAllFiles()) {
			Document doc = fr.toDocument();
			doc.append("analysis_report", analysisReportId).
				append("reference", reference).
				append("commit", commit.getHash()).
				append("commit_date", commit.getCommitterDate()).
				append("repository", repoId);
			
			documents.add(doc);

			if (i == 1000) {
				i = 0;
				dao.insertMany(documents);
				documents.clear();
			}
			i++;
		}

		if (documents.size() > 0) {
			dao.insertMany(documents);
		}
	}

	// Check if the metric requisites are being calculated in the correct order.
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

	// checks if the code smells are being detected in the correct order based on
	// theirs requisites, and also make sure that the needed metrics were calculated too.
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