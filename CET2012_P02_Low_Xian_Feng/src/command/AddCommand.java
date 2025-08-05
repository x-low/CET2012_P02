package command;

import core.CustomException;
import core.EmployeeManager;

public class AddCommand implements Command {
    private final EmployeeManager manager;
    private final String[] data;

    public AddCommand(EmployeeManager employeeManager, String input) {
        this.manager = employeeManager;
        this.data = input.split(" ");
    }

    @Override
    public void execute() throws CustomException {
        if (data.length != 3)
            throw new CustomException("Error: Add requires 3 inputs");
        manager.add(data);
    }

    @Override
    public void undo() throws CustomException {
        manager.deleteLastEntry();
    }

    @Override
    public boolean canUndo() {
        return true;
    }
}