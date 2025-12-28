package gurseluyanik;

public class DuplicateInfoException extends RuntimeException{

	// Define serialVersionUID
	private static final long serialVersionUID = 1L;

	// Define constructor for DuplicateInfoException
	public DuplicateInfoException(String message) {
		super(message);
	}
}
