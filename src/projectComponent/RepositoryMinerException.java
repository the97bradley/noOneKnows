package projectComponent;

public class RepositoryMinerException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public RepositoryMinerException(String message){
		super(message);
	}

	public RepositoryMinerException(Throwable cause){
		super(cause);
	}
	
	public RepositoryMinerException(String message, Throwable cause){
		super(message, cause);
	}
	
}
