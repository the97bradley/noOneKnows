package metrixsStructure;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



import ast.AST;
import ast.AbstractMethod;
import ast.AbstractType;
import projectComponent.ClassContent;
import projectComponent.FileContent;
import projectComponent.MethodContent;
import projectComponent.ProjectContent;



public class LOC extends MetrixsStruct{
	
	private Pattern pattern;

	public LOC() {
		
		super.id = MetrixsID.LOC;
		pattern = Pattern.compile("(\r\n)|(\n)|(\r)");
	}

	@Override
	public void calculate(AST ast, FileContent fileContent, ProjectContent projectContent) {
		// TODO Auto-generated method stub
		fileContent.getMetrixsContent().setMetrixStruct(MetrixsID.LOC, calculate(ast.getSource()));
		for(AbstractType type:ast.getTypes())
		{
			ClassContent cc = fileContent.getClass(type.getName());
			cc.getMetrixsContent().setMetrixStruct(MetrixsID.LOC, calculate(ast, type));
			for(AbstractMethod method:type.getMethods())
			{
				MethodContent mc = cc.getMethodByName(method.getName());
				mc.getMetrixsContent().setMetrixStruct(MetrixsID.LOC, calculate(ast, method));
			}
		}
		
	}
	
	public int calculate(AST ast, AbstractType type) {
		String clazz = ast.getSource().substring(type.getStartPosition(), type.getEndPosition());
		String body = clazz.substring(clazz.indexOf('{'));
		return calculate(body);
	}
	
	public int calculate(AST ast, AbstractMethod method) {
		String m = ast.getSource().substring(method.getStartPosition(), method.getEndPosition());
		return m.contains("{") ? calculate(m.substring(m.indexOf('{'))) : 0;
	}
	
	public int calculate(String source) {
		if (source == null || source.length() == 0) {
			return 0;
		}

		int lines = 1;
		Matcher matcher = pattern.matcher(source);
		while (matcher.find()) {
			lines++;
		}

		return lines;
	}
}