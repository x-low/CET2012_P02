package command;

import core.CustomException;
import core.EmployeeRegistry;

public class DeleteCommand implements UndoableCommand {
    private final EmployeeRegistry registry;
    private final String index;
    private String deletedEntry;

    public DeleteCommand(EmployeeRegistry registry, String index) {
        this.registry = registry;
        this.index = index;
    }

    @Override
    public void execute() throws CustomException {
        registry.delete(this, index);
    }

    public void undo() {
        registry.insert(index, this.deletedEntry);
    }

    public void setDeletedEntry(String deletedEntry) {
        this.deletedEntry = deletedEntry;
    }
}