package metrixsStructure;

import ast.AST;
import ast.AbstractMethod;
import ast.AbstractStatement;
import ast.AbstractType;
import ast.NodeType;
import projectComponent.ClassContent;
import projectComponent.FileContent;
import projectComponent.MethodContent;
import projectComponent.ProjectContent;

public class CYCLO extends MetrixsStruct {
	public CYCLO()
	{
		super.id = MetrixsID.CYCLO;
	}

	@Override
	public void calculate(AST ast, FileContent fileContent, ProjectContent projectContent) {
		// TODO Auto-generated method stub
		for(AbstractType type:ast.getTypes())
		{
			ClassContent cc = fileContent.getClass(type.getName());
			for(AbstractMethod am:type.getMethods())
			{
				MethodContent mc = cc.getMethodByName(am.getName());
				int cyclo = 1;
				if(am.getStatements()==null)
				{
					cyclo = 1;
				}
				else
				{
					
					
					for(AbstractStatement as:am.getStatements())
					{
						switch(as.getNodeType())
						{
						case SWITCH_CASE:
							cyclo++;
							break;
						case IF:
						case FOR:
						case DO_WHILE:
						case WHILE:
						case CATCH:
						case CONDITIONAL_EXPRESSION:
							cyclo+=calculateExpression(as.getExpression(),as.getNodeType());
							break;
						default:
							break;
						}
						
					}
					
				}
				mc.getMetrixsContent().setMetrixStruct(MetrixsID.CYCLO, cyclo);
			}
		}
	}

	private int calculateExpression(String expression, NodeType nodeType) {
		int cyclo = 1;
		char[] chars = expression.toCharArray();
		// TODO Auto-generated method stub
		for (int i = 0; i < chars.length; i++) {
			char next = chars[i];
			if ((next == '&' || next == '|') && (next == chars[i + 1])) {
				cyclo++;
			}
		}
		return cyclo;
	}

}
