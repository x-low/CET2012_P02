package core;

/**
 * Exception class exclusively thrown by errors encountered by
 * EmployeeManager and Command classes
 */
public class CustomException extends Exception {
    /**
     * Constructs custom exception message for printing
     * @param message the error message
     */
    public CustomException(String message) {
        super(message);
    }
}