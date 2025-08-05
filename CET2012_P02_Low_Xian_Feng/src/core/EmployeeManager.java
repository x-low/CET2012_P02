package core;

import command.Command;

import java.util.ArrayList;
import java.util.Stack;

public class EmployeeManager {
    private final EmployeeRegistry registry;

    public EmployeeManager(EmployeeRegistry employeeRegistry) {
        this.registry = employeeRegistry;
    }

    public void add(String[] data) {
        ArrayList<String> dataStore = this.registry.getDataStore();
        String line = String.format("%02d", dataStore.size() + 1) +
                ". " + data[0] + " " + data[1] + " " + data[2];
        dataStore.add(line);
    }

    public void deleteLastEntry() {
        ArrayList<String> dataStore = this.registry.getDataStore();
        dataStore.removeLast();
    }

    public String delete(String index) {
        ArrayList<String> dataStore = this.registry.getDataStore();
        try {
            int idx = Integer.parseInt(index);
            String deletedEntry = dataStore.get(idx - 1);
            dataStore.remove(idx - 1);
            for (int i = idx - 1; i < dataStore.size(); i++) {
                String entry = dataStore.get(i);
                String[] split = entry.split("\\.");
                String newEntry = String.format("%02d.", idx) + split[1];
                dataStore.set(i, newEntry);
            }
            return (deletedEntry);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void insert(String index, String data) {
        ArrayList<String> dataStore = this.registry.getDataStore();
        try {
            int idx = Integer.parseInt(index);
            dataStore.add(idx - 1, data);
            for (int i = idx; i < dataStore.size(); i++) {
                String entry = dataStore.get(i);
                String[] split = entry.split("\\.");
                String newEntry = String.format("%02d.", idx) + split[1];
                dataStore.set(i, newEntry);
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    public void undo(Stack<Command> history) throws CustomException {
        if (history.isEmpty())
            throw new CustomException("Error: Nothing to undo");
        Command prevCommand = history.pop();
        prevCommand.undo();
    }

    public void list() throws CustomException {
        ArrayList<String> dataStore = this.registry.getDataStore();
        if (dataStore.isEmpty())
            throw new CustomException("Error: Nothing to list");
        dataStore.forEach(System.out::println);
    }

    public String update(String[] data) {
        ArrayList<String> dataStore = this.registry.getDataStore();
        try {
            int idx = Integer.parseInt(data[0]);
            String entry = dataStore.get(idx - 1);
            String[] split = entry.split(" ");
            String newEntry = String.format("%02d. ", idx) + data[1];
            if (data.length > 2)
                newEntry += " " + data[2];
            else
                newEntry += " " + split[2];
            if (data.length > 3)
                newEntry += " " + data[3];
            else
                newEntry += " " + split[3];
            this.delete(data[0]);
            this.insert(data[0],  newEntry);
            return (entry);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

}
