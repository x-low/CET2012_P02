package Command;

public interface UndoableCommand extends Command {
    void undo();
}