package parser;



import ast.AST;

public abstract class Parser {

	protected Language id;
	protected String[] extensions;
	private String[] sourceFolders;

	/**
	 * @param filename
	 *            the file path.
	 * @param source
	 *            the source code.
	 * @param srcFolders
	 *            the source folders.
	 * 
	 * @return the AST representing the source code.
	 */
	public abstract AST generate(String filename, String source, String[] srcFolders);

	/**
	 * @return the file extensions that the parser can deal.
	 */
	public String[] getExtensions() {
		return extensions;
	}

	/**
	 * @return the programming language supported by the parser
	 */
	public Language getId() {
		return id;
	}

	public String[] getSourceFolders() {
		return sourceFolders;
	}

	public void setSourceFolders(String[] sourceFolders) {
		this.sourceFolders = sourceFolders;
	}

}