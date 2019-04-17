package Check;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.github.*;
import com.github.javaparser.*;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.DataKey;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.PackageDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.comments.Comment;

import Object_Orientation_Abusers.Switch_Statements;
public class FileComponent {
	
	private CompilationUnit cu;
	private CompilationUnit cuNoComment;
	private List<MethodDeclaration> methods = new ArrayList<MethodDeclaration>();
	private List<MethodDeclaration> methodsNoC = new ArrayList<MethodDeclaration>();
	private List<FieldDeclaration> fields = new ArrayList<FieldDeclaration>();

	private List<ImportDeclaration> imports = new NodeList<ImportDeclaration>();
	private List<Comment> comments = new ArrayList<Comment>();
	private List<ConstructorDeclaration> constructors = new ArrayList<ConstructorDeclaration>();
	private Optional<PackageDeclaration> Package;
	private JavaParser javaParser = new JavaParser();
	private SetUpCu set_up_cu = new SetUpCu();
	
	public FileComponent(File file) throws IOException 
	{
		setUpCu(file);
		cuNoComment = set_up_cu.setUpCuNoComment(cu);
		methods = set_up_cu.setUpMethods(cu);
		methodsNoC = set_up_cu.setUpMethods(cuNoComment);
		fields = set_up_cu.setUpField(cuNoComment);
		imports = cu.getImports();
		Package = cu.getPackageDeclaration();
		comments = cu.getComments();
		
	
		
	}
	
	

	

	private void setUpCu(File file) throws FileNotFoundException
	{
		if(file.isFile()&&file.getName().endsWith(".java"))
		{
			ParseResult<CompilationUnit> pr = javaParser.parse(file);
			
			Optional<CompilationUnit> op  = pr.getResult();
			cu = op.get();
		}
	}

	
	DataKey<String> key;
	public void type()
		{
		cu.getType(0).getFields();
		cu.getImports();
		cu.getPackageDeclaration();
		Switch_Statements ss = new Switch_Statements();
		
		//System.out.println(methodsNoC.get(0));
		System.out.println(fields.size());
		fields.get(0);

		System.out.println(cuNoComment.getType(0));
		System.out.println(ss.isSwitchStatement(cuNoComment.getType(0).getMethods().get(0)));
		
		
		
		}
	
	
}
