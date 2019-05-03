package Executor;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import codesmell.CodeSmell;
import codesmell.CodeSmellFactory;
import codesmell.CodeSmellId;
import metrixsStructure.MetrixFactory;
import metrixsStructure.MetrixsID;
import metrixsStructure.MetrixsStruct;

public class Detect {

		public  static String run(String directory,String[] files,ArrayList<File> listFiles, ArrayList<Boolean> codeSmellEnable) throws IOException {

		
		AnalysisRunner runner = new AnalysisRunner(directory,listFiles);

		
		List<MetrixsStruct> metrix = new ArrayList<MetrixsStruct>();
		List<CodeSmell> codeSmell = new ArrayList<CodeSmell>();	
		
		HashMap<CodeSmellId,Boolean> codeSmellHash = new HashMap<>();
	
		
		
		
		
		
		
		
		
		
		
		
		for(MetrixsID mid:MetrixsID.values())
		{
			metrix.add(MetrixFactory.getMetric(mid));
		}	
		
		for(int i =0;i<CodeSmellId.values().length;i++)
		{
			codeSmellHash.put(CodeSmellId.values()[i], codeSmellEnable.get(i));
		}
		
		for(CodeSmellId cid:CodeSmellId.values())
		{
			if(codeSmellHash.get(cid)==true)
			{
				codeSmell.add(CodeSmellFactory.getCodeSmell(cid));
			}
			
		}
		
		runner.setCodeMetrics(metrix);
		runner.setCodeSmells(codeSmell);
		return runner.run();
		
		
		
		
		
		
		// TODO Auto-generated method stub
		
	}

}
