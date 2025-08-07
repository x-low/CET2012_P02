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
        int idx;
        try {
            idx = Integer.parseInt(data[0]);
        }  catch (NumberFormatException e) {
            throw new CustomException("Error: Index must be positive integer");
        }
        prevEntry = manager.update(idx, data);
        System.out.println("update");
    }

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

    @Override
    public boolean canUndo() {
        return true;
    }
}