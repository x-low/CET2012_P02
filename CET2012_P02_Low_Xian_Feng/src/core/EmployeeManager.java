package core;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class EmployeeManager {
    private final FileHandler fileHandler;

    public EmployeeManager() {
        this.fileHandler = new FileHandler();
    }

    public void storeToFile() {
        fileHandler.storeToFile();
    }

    public void add(String[] data) throws CustomException {
        if (ManagerTools.isLatinEmail(data[2]))
            data[2] = ManagerTools.toTitleCase(data[2]);
        ArrayList<String> dataStore = this.fileHandler.getDataStore();
        String line = String.format("%02d", dataStore.size() + 1) +
                ". " + data[0] + " " + data[1];
        line = ManagerTools.toTitleCase(line) + " " + data[2];
        dataStore.add(line);
    }

    public void deleteLastEntry() throws CustomException {
        ArrayList<String> dataStore = this.fileHandler.getDataStore();
        try {
            dataStore.removeLast();
        } catch (NoSuchElementException e) {
            throw new CustomException("Error: Nothing to delete");
        }
    }

    public String delete(int idx) throws CustomException {
        ArrayList<String> dataStore = this.fileHandler.getDataStore();
        String deletedEntry;
        try {
            deletedEntry = dataStore.get(idx - 1);
            dataStore.remove(idx - 1);
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

    public void insert(int idx, String data) throws CustomException {
        ArrayList<String> dataStore = this.fileHandler.getDataStore();
        try {
            dataStore.add(idx - 1, data);
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
        ArrayList<String> dataStore = this.fileHandler.getDataStore();
        if (dataStore.isEmpty())
            throw new CustomException("Error: Nothing to list");
        System.out.println("list");
        dataStore.forEach(System.out::println);
        System.out.println();
    }

    public String update(int idx, String[] data) throws CustomException {
        ArrayList<String> dataStore = this.fileHandler.getDataStore();
        String entry, newEntry;
        String[] split;
        try {
            entry = dataStore.get(idx - 1);
            split = entry.split(" ");
            newEntry = String.format("%02d. ", idx) + data[1];
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
        this.delete(idx);
        this.insert(idx,  newEntry);
        return (entry);
    }
}