package command;

import core.CustomException;

import java.util.Stack;

public interface Command {
    void execute() throws CustomException;
    void undo() throws CustomException;
    boolean canUndo();
}