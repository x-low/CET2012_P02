import command.*;
import core.*;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        EmployeeManager manager = new EmployeeManager();
        Invoker invoker = new Invoker();
        Stack<Command> history = new Stack<Command>();
        Command[] payload = new Command[]{
                new AddCommand(manager, "first_name last_name valid@g.com"),
                new AddCommand(manager, "first_name last_name _@gmail.com"),
                new AddCommand(manager, "first_name last_name _____"),
                new AddCommand(manager, "first_name last_name valid@gmail.co"),
                new AddCommand(manager, "first_name last_name inva.-.-lid@gmail.com"),
                new AddCommand(manager, "first_name last_name invalid@gma.-.-il.com"),
                new AddCommand(manager, "first_name last_name .invalid@gmail.com"),
                new AddCommand(manager, "first_name last_name invalid.@gmail.com"),
                new AddCommand(manager, "first_name last_name invalid@.gmail.com"),
                new AddCommand(manager, "first_name last_name invalid@gmail..com"),
                new AddCommand(manager, "first_name last_name -invalid@gmail.com"),
                new AddCommand(manager, "first_name last_name invalid-@gmail.com"),
                new AddCommand(manager, "first_name last_name invalid@gmail-.com"),
                new AddCommand(manager, "first_name last_name invalid@-gmail.com"),
                new AddCommand(manager, "first_name last_name inv..alid@gmail.com"),
                new AddCommand(manager, "first_name last_name inva--lid@gmail.com"),
                new AddCommand(manager, "first_name last_name invalid@gma..il.com"),
                new AddCommand(manager, "first_name last_name invalid@gma--il.com"),
                new AddCommand(manager, "first_name last_name invalid@.com"),
                new AddCommand(manager, "first_name last_name invalid@gmail."),
                new AddCommand(manager, "first_name last_name invalid@gmail"),
                new AddCommand(manager, "first_name last_name invalid@"),
                new AddCommand(manager, "first_name last_name invalid."),
                new AddCommand(manager, "first_name last_name invalid-"),
                new AddCommand(manager, "TEST test _TesT"),
                new ListCommand(manager)
        };

        invoker.setCommandsForExecution(payload);
        invoker.executeCommand(history);
        System.out.println(history.toString());

        manager.storeToFile();
    }
}
