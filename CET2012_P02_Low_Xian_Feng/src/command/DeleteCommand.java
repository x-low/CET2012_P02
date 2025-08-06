package command;

import core.CustomException;
import core.EmployeeManager;

public class DeleteCommand implements Command {
    private final EmployeeManager manager;
    private final String index;
    private String deletedEntry = null;

    public DeleteCommand(EmployeeManager employeeManager, String idx) {
        this.manager = employeeManager;
        this.index = idx;
    }

    @Override
    public void execute() throws CustomException {
        if (this.index.split(" ").length != 1)
            throw new CustomException("Error: Delete only requires one input");
        deletedEntry = manager.delete(index);
    }

    public void undo() throws CustomException {
        if (deletedEntry == null)
            throw new CustomException("Error: Nothing to delete");
        manager.insert(index, deletedEntry);
    }

    @Override
    public boolean canUndo() {
        return true;
    }
}