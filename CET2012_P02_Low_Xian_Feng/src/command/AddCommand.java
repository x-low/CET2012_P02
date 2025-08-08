package command;

import core.CustomException;
import core.EmployeeManager;

/**
 * Accepts a {@code String} to be entered as a record into the datastore
 * following the pattern of "first_name last_name email"
 */
public class AddCommand implements Command {
    /**
     * Receiver responsible for handling and storing data
     */
    private final EmployeeManager manager;
    /**
     * Input split into datafields by spaces
     */
    private final String[] data;

    /**
     * Constructs an {@code AddCommand} with the receiver and entry to add.
     * Splits input into separate data fields
     * @param employeeManager data storage handler
     * @param input entry to be added
     */
    public AddCommand(EmployeeManager employeeManager, String input) {
        this.manager = employeeManager;
        this.data = input.split(" ");
    }

    /**
     * Calls datastore handler to execute the add operation
     * @throws CustomException if number of data fields is not 3 or
     * the operation fails in the datastore handler
     */
    @Override
    public void execute() throws CustomException {
        if (data.length != 3)
            throw new CustomException("Error: Add requires 3 inputs");
        manager.add(data);
        System.out.println("add");
    }

    /**
     * Undo the most recent add operation by removing the last entry
     * in the datastore
     * @throws CustomException if there is no entry to be removed
     */
    @Override
    public void undo() throws CustomException {
        manager.deleteLastEntry();
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