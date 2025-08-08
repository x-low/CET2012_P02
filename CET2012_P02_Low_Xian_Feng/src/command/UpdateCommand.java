package command;

import core.CustomException;
import core.EmployeeManager;

/**
 * Accepts a {@code String} to be split into a positive numerical index
 * with data fields to replace an existing entry in the datastore
 */
public class UpdateCommand implements Command {
    /**
     * Receiver responsible for handling and storing data
     */
    private final EmployeeManager manager;
    /**
     * Index and data fields to be replaced
     */
    private final String[] data;
    /**
     * Entry that was replaced by execution of UpdateCommand
     */
    private String prevEntry = null;

    /**
     * Constructs a {@code UpdateCommand} with the receiver and data fields
     * indicating the index to be replaced and at least one data point to
     * be overwritten, supporting up to all three.
     * @param employeeManager data storage handler
     * @param input entry number to be deleted and data points to overwrite
     */
    public UpdateCommand(EmployeeManager employeeManager, String input) {
        this.manager = employeeManager;
        this.data = input.split(" ");
    }

    /**
     * Calls datastore handler to execute the update operation
     * @throws CustomException if index or data points are invalid or
     * number of data points is less than 1 or more than 3
     */
    @Override
    public void execute() throws CustomException {
        if (data.length < 2)
            throw new CustomException("Error: Update needs at least 2 inputs");
        if (data.length > 4)
            throw new CustomException("Error: Update needs at most 4 inputs");
        int idx;
        try {
            idx = Integer.parseInt(data[0]);
        }  catch (NumberFormatException e) {
            throw new CustomException("Error: Index must be positive integer");
        }
        prevEntry = manager.update(idx, data);
        System.out.println("update");
    }

    /**
     * Restores the previously overwritten entry back to datastore
     * @throws CustomException if nothing was previously updated
     */
    @Override
    public void undo() throws CustomException {
        if (prevEntry == null || data.length == 0)
            throw new CustomException("Error: Cannot undo");
        int idx;
        try {
            idx = Integer.parseInt(data[0]);
        }  catch (NumberFormatException e) {
            throw new CustomException("Error: Index must be positive integer");
        }
        manager.delete(idx);
        manager.insert(idx, prevEntry);
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