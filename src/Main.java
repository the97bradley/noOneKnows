import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import codesmell.CodeSmell;
import codesmell.CodeSmellFactory;
import codesmell.CodeSmellId;
import metrixsStructure.MetrixFactory;
import metrixsStructure.MetrixsID;
import metrixsStructure.MetrixsStruct;
import parser.JavaParser;
import parser.Parser;

public class Main {

	public static void main(String[] args) throws IOException {

		List<Parser> parser = new ArrayList<Parser>();
		parser.add(new JavaParser());
		List<MetrixsStruct> metrix = new ArrayList<MetrixsStruct>();
		metrix.add(MetrixFactory.getMetric(MetrixsID.LOC));
		metrix.add(MetrixFactory.getMetric(MetrixsID.NOF));
		metrix.add(MetrixFactory.getMetric(MetrixsID.NOP));
		List<CodeSmell> codeSmell = new ArrayList<CodeSmell>();
		codeSmell.add(CodeSmellFactory.getCodeSmell(CodeSmellId.LONG_METHOD));
		codeSmell.add(CodeSmellFactory.getCodeSmell(CodeSmellId.LARGE_CLASS));
		codeSmell.add(CodeSmellFactory.getCodeSmell(CodeSmellId.PRIMITIVE_OBSESSION));
		codeSmell.add(CodeSmellFactory.getCodeSmell(CodeSmellId.LONG_PARAMETER_LIST));

		
		AnalysisRunner runner = new AnalysisRunner("C:\\Users\\zhang\\Desktop\\code\\Project\\src\\ast");
		
		runner.setParsers(parser);
		runner.setCodeMetrics(metrix);
		runner.setCodeSmells(codeSmell);
		runner.run();
		
		// TODO Auto-generated method stub
		
	}

}
