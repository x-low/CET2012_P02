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
        Command[] payload = new Command[]{new AddCommand(manager, "first", "first", "email"),
                new AddCommand(manager, "Second", "sEcOnD", "email"),
                new UpdateCommand(manager, "4", "uh", "what?"),
                new AddCommand(manager, "THIRD", "ThIRd", "email"),
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
