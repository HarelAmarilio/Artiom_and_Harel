package Artiom_and_Harel;
import java.io.*;

public class BinaryFileRepository implements CollegeRepository {
    private static final String DATA_FILE = "college_data.bin";

    @Override
    public CollegeManager loadData() throws Exception {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            throw new FileNotFoundException("Data file not found");
        }
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            return (CollegeManager) in.readObject();
        }
    }

    @Override
    public void saveData(CollegeManager manager) throws Exception {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            out.writeObject(manager);
        }
    }
}