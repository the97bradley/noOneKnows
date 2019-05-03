package parser;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import ast.AST;

public class JavaParser {

	public AST generate(String filename, String source) {
		AST ast = new AST();
		ast.setFileName(filename);
		ast.setSource(source);
		

		ASTParser parser = ASTParser.newParser(org.eclipse.jdt.core.dom.AST.JLS3);
		parser.setResolveBindings(true);
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		parser.setBindingsRecovery(true);
		parser.setCompilerOptions(JavaCore.getOptions());
		parser.setUnitName(filename);
		parser.setSource(source.toCharArray());
	
		parser.setEnvironment(null, null, null, true);
		
		
		CompilationUnit cu = (CompilationUnit) parser.createAST(null);

		FileVisitor visitor = new FileVisitor();
		cu.accept(visitor);

		ast.setImports(visitor.getImports());
		ast.setPackageDeclaration(visitor.getPackageName());
		
		ast.setTypes(visitor.getTypes());
		
		

		return ast;
	}
	

	/**
	 * @return the programming language supported by the parser
	 */


}
