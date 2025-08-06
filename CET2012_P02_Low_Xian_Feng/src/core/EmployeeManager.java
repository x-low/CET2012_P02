package core;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class EmployeeManager {
    private final EmployeeRegistry registry;

    public EmployeeManager(EmployeeRegistry employeeRegistry) {
        this.registry = employeeRegistry;
    }

    public void add(String[] data) throws CustomException {
        if (ManagerTools.isLatinEmail(data[2]))
            data[2] = ManagerTools.toTitleCase(data[2]);
        ArrayList<String> dataStore = this.registry.getDataStore();
        String line = String.format("%02d", dataStore.size() + 1) +
                ". " + data[0] + " " + data[1];
        line = ManagerTools.toTitleCase(line) + " " + data[2];
        dataStore.add(line);
    }

    public void deleteLastEntry() throws CustomException {
        ArrayList<String> dataStore = this.registry.getDataStore();
        try {
            dataStore.removeLast();
        } catch (NoSuchElementException e) {
            throw new CustomException("Error: Nothing to delete");
        }
    }

    public String delete(String index) throws CustomException {
        ArrayList<String> dataStore = this.registry.getDataStore();
        int idx;
        String deletedEntry;
        try {
            idx = Integer.parseInt(index);
            deletedEntry = dataStore.get(idx - 1);
            dataStore.remove(idx - 1);
        } catch (NumberFormatException e) {
            throw new CustomException("Error: Index must be positive number");
        } catch (IndexOutOfBoundsException e) {
            throw new CustomException("Error: Index entry does not exist");
        }
        for (int i = idx - 1; i < dataStore.size(); i++) {
            String entry = dataStore.get(i);
            String[] split = entry.split("^[^.]+");
            String newEntry = String.format("%02d", i + 1) + split[1];
            dataStore.set(i, newEntry);
        }
        return (deletedEntry);
    }

    public void insert(String index, String data) throws CustomException {
        ArrayList<String> dataStore = this.registry.getDataStore();
        int idx;
        try {
            idx = Integer.parseInt(index);
            dataStore.add(idx - 1, data);
        } catch (NumberFormatException e) {
                throw new CustomException("Error: Index must be positive number");
        } catch (IndexOutOfBoundsException e) {
                throw new CustomException("Error: Cannot insert at index");
        }
        for (int i = idx; i < dataStore.size(); i++) {
            String entry = dataStore.get(i);
            String[] split = entry.split("^[^.]+");
            String newEntry = String.format("%02d", i + 1) + split[1];
            dataStore.set(i, newEntry);
        }
    }

    public void list() throws CustomException {
        ArrayList<String> dataStore = this.registry.getDataStore();
        if (dataStore.isEmpty())
            throw new CustomException("Error: Nothing to list");
        dataStore.forEach(System.out::println);
        System.out.println();
    }

    public String update(String[] data) throws CustomException {
        ArrayList<String> dataStore = this.registry.getDataStore();
        int idx;
        String entry, newEntry;
        String[] split;
        try {
            idx = Integer.parseInt(data[0]);
            entry = dataStore.get(idx - 1);
            split = entry.split(" ");
            newEntry = String.format("%02d. ", idx) + data[1];
        } catch (NumberFormatException e) {
            throw new CustomException("Error: Index must be positive number");
        } catch (IndexOutOfBoundsException e) {
            throw new CustomException("Error: Index entry does not exist");
        }
        if (data.length > 2)
            newEntry += " " + data[2];
        else
            newEntry += " " + split[2];
        newEntry = ManagerTools.toTitleCase(newEntry);
        if (data.length > 3) {
            if (ManagerTools.isLatinEmail(data[3]))
                data[3] = ManagerTools.toTitleCase(data[3]);
            newEntry += " " + data[3];
        }
        else
            newEntry += " " + split[3];
        this.delete(data[0]);
        this.insert(data[0],  newEntry);
        return (entry);
    }
}