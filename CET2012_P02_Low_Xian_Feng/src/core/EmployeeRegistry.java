package core;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class EmployeeRegistry {
    private ArrayList<String> dataStore;
    private static final String dataStorePath = "src/dataStore.txt";

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
}