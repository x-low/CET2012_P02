package command;

import core.CustomException;
import core.EmployeeManager;

public class UpdateCommand implements Command {
    private final EmployeeManager manager;
    private final String[] data;
    private String prevEntry = null;

    public UpdateCommand(EmployeeManager employeeManager, String input) {
        this.manager = employeeManager;
        this.data = input.split(" ");
    }

    @Override
    public void execute() throws CustomException {
        if (data.length < 2)
            throw new CustomException("Error: Update needs at least 2 inputs");
        if (data.length > 4)
            throw new CustomException("Error: Update needs at most 4 inputs");
        prevEntry = manager.update(data);
        System.out.println("update");
    }

    @Override
    public void undo() throws CustomException {
        if (prevEntry == null || data.length == 0)
            throw new CustomException("Error: Cannot undo");
        manager.delete(data[0]);
        manager.insert(data[0], prevEntry);
    }

    @Override
    public boolean canUndo() {
        return true;
    }
}