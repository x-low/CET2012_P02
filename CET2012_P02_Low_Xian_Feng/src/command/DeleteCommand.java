package command;

import core.CustomException;
import core.EmployeeRegistry;

public class DeleteCommand implements Command {
    private final EmployeeRegistry registry;
    private final String index;
    private String deletedEntry;

    public DeleteCommand(EmployeeRegistry registry, String index) {
        this.registry = registry;
        this.index = index;
    }

    @Override
    public void execute() throws CustomException {
        this.deletedEntry = registry.delete(index);
    }

    public void undo() {
        registry.insert(index, this.deletedEntry);
    }
}