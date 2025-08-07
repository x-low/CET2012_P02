package command;

import core.CustomException;

import java.util.Stack;

/**
 * An object representing an executable command with
 * method for indicating whether it supports undo functionality
 */
public interface Command {
    /**
     * Executes the command's functionality through it's included
     * receiver. Prints name of command upon successful execution
     * @throws CustomException if wrong number of arguments
     */
    void execute() throws CustomException;
    void undo() throws CustomException;
    boolean canUndo();
}