package metrixsStructure;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ast.AST;
import ast.AbstractFieldAccess;
import ast.AbstractMethod;
import ast.AbstractMethodInvocation;
import ast.AbstractStatement;
import ast.AbstractType;
import ast.NodeType;
import projectComponent.ClassContent;
import projectComponent.FileContent;
import projectComponent.ProjectContent;

public class TCC extends MetrixsStruct {

	public TCC() {
		super.id = MetrixsID.TCC;
	}
	
	@Override
	public void calculate(AST ast, FileContent fileReport, ProjectContent projectReport) {
		for (AbstractType type : ast.getTypes()) {
			ClassContent cc = fileReport.getClass(type.getName());
			cc.getMetrixsContent().setMetrixStruct(MetrixsID.TCC, calculate(type));
		}
	}

	public double calculate(AbstractType type) {
		List<AbstractMethod> methodList = new ArrayList<AbstractMethod>();
		for (AbstractMethod m : type.getMethods()) {
			if (!(m.getModifiers().contains("abstract") || m.isConstructor())) {
				methodList.add(m);
			}
		}

		int n = methodList.size();
		int npc = (n * (n - 1)) / 2;
		int ndc = 0;

		List<List<String>> accessedFieldsByMethods = new ArrayList<List<String>>();
		for (AbstractMethod method : methodList) {
			accessedFieldsByMethods.add(processAccessedFields(type, method));
		}

		for (int i = 0; i < accessedFieldsByMethods.size(); i++) {
			for (int j = i + 1; j < accessedFieldsByMethods.size(); j++) {
				if (isConnected(accessedFieldsByMethods.get(i), accessedFieldsByMethods.get(j))) {
					ndc++;
				}
			}
		}

		double result = npc > 0 ? ndc * 1.0 / npc : 0;
		return new BigDecimal(result).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public List<String> processAccessedFields(AbstractType currType, AbstractMethod method) {
		Set<String> fields = new HashSet<String>();
		for (AbstractStatement stmt : method.getStatements()) {
			if (stmt.getNodeType() == NodeType.FIELD_ACCESS) {
				AbstractFieldAccess fdAccess = (AbstractFieldAccess) stmt;
				if (currType.getName().equals(fdAccess.getDeclaringClass())) {
					fields.add(stmt.getExpression());
				}
			} else if (stmt.getNodeType() == NodeType.METHOD_INVOCATION) {
				AbstractMethodInvocation methodInv = (AbstractMethodInvocation) stmt;
				if (methodInv.isAccessor() && currType.getName().equals(methodInv.getDeclaringClass())) {
					fields.add(methodInv.getAccessedField());
				}
			} else {
				continue;
			}
		}
		return new ArrayList<String>(fields);
	}

	private boolean isConnected(List<String> method1, List<String> method2) {
		for (String field : method1) {
			if (method2.contains(field)) {
				return true;
			}
		}
		return false;
	}

}
