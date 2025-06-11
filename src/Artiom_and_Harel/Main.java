package Artiom_and_Harel;
import java.io.*;
import java.util.Scanner;

public class Main implements Serializable {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter college name:");
        String collegeName = scanner.nextLine();
        String filename = collegeName + ".dat";

        College_Managment college_managment = loadDataFromFile(filename);
        college_managment.print_Menu();

        saveDataToFile(college_managment, filename);
    }

    public static College_Managment loadDataFromFile(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return (College_Managment) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No saved data found, starting new college management.");
            return new College_Managment();
        }
    }

    public static void saveDataToFile(College_Managment college, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(college);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
}
