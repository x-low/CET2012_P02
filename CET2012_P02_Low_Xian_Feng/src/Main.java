import command.*;
import core.*;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        EmployeeRegistry registry;
        EmployeeManager manager;
        Invoker invoker = new Invoker();
        Stack<Command> history = new Stack<Command>();
        try {
            registry = new EmployeeRegistry();
        } catch (CustomException e) {
            System.out.println(e.getMessage());
            return ;
        }
        manager = new EmployeeManager(registry);
        Command[] payload = new Command[]{
                new AddCommand(manager, "first_name", "last_name", "email"),
                new AddCommand(manager, "john", "Doe", "email"),
                new AddCommand(manager, "hanna", "moon", "email"),
                new AddCommand(manager, "ah", "boon", "email"),
                new ListCommand(manager),
                new UpdateCommand(manager, "3", "adam"),
                new ListCommand(manager),
                new UpdateCommand(manager, "1", "blue", "bell", "ice-cream"),
                new ListCommand(manager),
                new DeleteCommand(manager, "1"),
                new ListCommand(manager),
                new UndoCommand(manager, history),
                new ListCommand(manager)
        };

        invoker.setCommandsForExecution(payload);
        invoker.executeCommand(history);
        System.out.println(history.toString());

        try {
            registry.storeToFile();
        } catch (CustomException e) {
            System.out.println(e.getMessage());
        }
    }
}
