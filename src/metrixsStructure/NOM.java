package metrixsStructure;



import ast.AST;
import ast.AbstractType;
import projectComponent.ClassContent;
import projectComponent.FileContent;
import projectComponent.ProjectContent;

public class NOM extends MetrixsStruct{
	
	public NOM() {
		super.id = MetrixsID.NOM;
	}

	@Override
	public void calculate(AST ast, FileContent fileContent, ProjectContent projectContent) {
		// TODO Auto-generated method stub
		for(AbstractType type:ast.getTypes())
		{
			ClassContent cc = fileContent.getClass(type.getName());
			cc.getMetrixsContent().setMetrixStruct(MetrixsID.NOM, type.getMethods().size());
		}
	}

	@Override
	public void clean(ProjectContent projectcontent) {
		// TODO Auto-generated method stub
		
	}

}
