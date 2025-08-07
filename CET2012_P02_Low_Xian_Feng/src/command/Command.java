package command;

import core.CustomException;

/**
 * Represents an executable action capable of executing a command,
 * to check whether it support an undo functionality and if it does,
 * the ability to undo a previously executed command
 */
public interface Command {
    /**
     * Executes corresponding command's main action
     * @throws CustomException if input is invalid or action
     * could not be successfully carried out by receiver
     */
    void execute() throws CustomException;

    /**
     * Undo the effects of previously executed command
     * if undo is supported
     * @throws CustomException if undo is not supported or action
     * could not be successfully carried out by receiver
     */
    void undo() throws CustomException;

    /**
     * Indicates whether command supports undo functionality
     * @return {@code true} if undo is supported, {@code false} if not
     */
    boolean canUndo();
}