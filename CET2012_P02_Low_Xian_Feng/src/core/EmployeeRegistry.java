package core;
import command.Command;
import command.DeleteCommand;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class EmployeeRegistry {
    private ArrayList<String> dataStore;
    private static final String dataStorePath =
            System.getProperty("user.dir") +
                    "/CET2012_P02_Low_Xian_Feng/src/dataStore.txt";

    public EmployeeRegistry() throws CustomException {
        dataStore = new ArrayList<>();
        File data =  new File(dataStorePath);

        if (!data.exists())
            return ;
        if (!data.isFile())
            throw new CustomException(
                    "Error: Existing dataStore.txt is not a file"
            );
        if (!data.canRead())
            throw new CustomException("Error: Cannot read existing datastore");

        try (BufferedReader br = new BufferedReader(new FileReader(data))) {
            String line;
            while ((line = br.readLine()) != null)
                dataStore.add(line);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void storeToFile() throws CustomException {
        File data = new File(dataStorePath);

        try {
            if (!data.exists() && !data.createNewFile())
                throw new CustomException("Error: Cannot create datastore");
            if (!data.canWrite())
                throw new CustomException("Error: Cannot write to datastore");

            try (BufferedWriter bw = new BufferedWriter(
                    new FileWriter(data))) {
                for (String line : dataStore) {
                    bw.write(line);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void add(String[] data) {
        String line = String.format("%02d", dataStore.size() + 1) +
                ". " + data[0] + " " + data[1] + " " + data[2];
        dataStore.add(line);
    }

    public void deleteLastEntry() {
        dataStore.removeLast();
    }

    public String delete(String index) {
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

}