package command;

import core.CustomException;
import core.EmployeeManager;

/**
 * Represents a request to print the contents of the datastore.
 */
public class ListCommand implements Command {
    /**
     * Receiver responsible for handling and storing data
     */
    private final EmployeeManager manager;

    /**
     * Constructs a {@code ListCommand} with the receiver
     * @param employeeManager data storage handler
     */
    public ListCommand(EmployeeManager employeeManager) {
        this.manager = employeeManager;
    }

    /**
     * Calls datastore handler to execute list operation
     * @throws CustomException if datastore is empty
     */
    @Override
    public void execute() throws CustomException {
        manager.list();
    }

    /**
     * Undo is not supported for the list command
     * @throws CustomException as undo functionality is not supported
     */
    @Override
    public void undo() throws CustomException {
        throw new CustomException("Error: Cannot undo");
    }

    /**
     * Returns {@code false} indicating undo functionality is not supported
     * @return {@code false}
     */
    @Override
    public boolean canUndo() {
        return false;
    }
}