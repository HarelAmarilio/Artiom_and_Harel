package Artiom_and_Harel;

import java.io.*;

public class DataManager {

    private static final String DATA_FILE = "college_data.bin";

    // טעינת נתונים מהקובץ הבינארי
    public static College_Managment loadCollegeData() {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            System.out.println("לא נמצא קובץ נתונים. המערכת תאותחל ריקה.");
            return new College_Managment(); // אתחול ריק
        }

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            College_Managment college = (College_Managment) in.readObject();
            System.out.println("Successfully loaded college data.");
            return college;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("error in loading" + e.getMessage());
            return new College_Managment(); // fallback לאתחול ריק
        }
    }

    // שמירת נתונים לקובץ הבינארי
    public static void saveCollegeData(College_Managment college) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            out.writeObject(college);
            System.out.println("Successfully loaded college data.");
        } catch (IOException e) {
            System.out.println("error in loading" + e.getMessage());
        }
    }
}
