package online.vegetable.sales.exception;


public class ProductAlreadyExitException extends  RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProductAlreadyExitException(String message) {
		super(message);
	}
	
	
	public ProductAlreadyExitException() {
		
	}
	
	
}
