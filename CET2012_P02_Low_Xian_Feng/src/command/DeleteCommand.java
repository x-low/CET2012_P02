package command;

import core.CustomException;
import core.EmployeeManager;

/**
 * Accepts a {@code String} positive numerical index to
 * delete the corresponding entry from datastore
 */
public class DeleteCommand implements Command {
    /**
     * Receiver responsible for handling and storing data
     */
    private final EmployeeManager manager;
    /**
     * Entry index to be deleted from datastore
     */
    private final String index;
    /**
     * Previously deleted entry to be restored with {@code undo}
     */
    private String deletedEntry = null;
    /**
     * Marks if command has already been executed
     */
    private boolean executed = false;

    /**
     * Constructs a {@code DeleteCommand} with the receiver and entry number
     * to delete.
     * @param employeeManager data storage handler
     * @param idx entry to be deleted
     */
    public DeleteCommand(EmployeeManager employeeManager, String idx) {
        this.manager = employeeManager;
        this.index = idx;
    }

    /**
     * Calls datastore handler to execute the delete operation
     * @throws CustomException if index given is invalid or does not
     * exist in the datastore
     */
    @Override
    public void execute() throws CustomException {
        if (this.index.split(" ").length != 1)
            throw new CustomException("Error: Delete only requires one input");
        int idx;
        try {
            idx = Integer.parseInt(index);
        }  catch (NumberFormatException e) {
            throw new CustomException("Error: Index must be positive integer");
        }
        deletedEntry = manager.delete(idx);
        System.out.println("delete");
        executed = true;
    }

    /**
     * Restores the previously deleted entry back to datastore
     * @throws CustomException if nothing was previously deleted
     */
    public void undo() throws CustomException {
        if (!executed)
            throw new CustomException("Error: Cannot undo without executing");
        if (deletedEntry == null)
            throw new CustomException("Error: Nothing to delete");
        int idx;
        try {
            idx = Integer.parseInt(index);
        }  catch (NumberFormatException e) {
            throw new CustomException("Error: Index must be positive integer");
        }
        manager.insert(idx, deletedEntry);
        executed = false;
    }

    /**
     * Returns {@code true} indicating undo functionality is supported
     * @return {@code true}
     */
    @Override
    public boolean canUndo() {
        return true;
    }
}