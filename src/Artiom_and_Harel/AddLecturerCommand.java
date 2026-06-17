package Artiom_and_Harel;
import Artiom_and_Harel.*;
import Artiom_and_Harel.*;
import java.util.Scanner;
import java.util.Map;

public class AddLecturerCommand implements Command {
    private CollegeManager manager;
    private Scanner scan;
    private Map<DgreeNames, LecturerFactory> factories;

    public AddLecturerCommand(CollegeManager manager, Scanner scan, Map<DgreeNames, LecturerFactory> factories) {
        this.manager = manager;
        this.scan = scan;
        this.factories = factories;
    }

    @Override
    public void execute() {
        System.out.println("Please enter the name of the lecturer");
        String name = scan.nextLine();

        try {
            manager.doesExistByName(manager.getLecturers(), name);
        } catch (ObjectExists e) {
            System.err.println(e.getMessage());
            return;
        }

        System.out.println("Please enter lecturer's ID");
        int id = scan.nextInt(); scan.nextLine();

        System.out.println("Please enter degree type (BACHELOR, MASTER, DOCTOR, PROFESSOR):");
        DgreeNames degreeType;
        try {
            degreeType = DgreeNames.valueOf(scan.nextLine().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid degree type!");
            return;
        }

        System.out.println("Please enter degree's name");
        String degreeName = scan.nextLine();

        System.out.println("Please enter lecturer's salary");
        double salary = scan.nextDouble(); scan.nextLine();

        System.out.println("Please enter lecturer's department (Press Enter to skip):");
        String department = scan.nextLine();

        LecturerFactory factory = factories.get(degreeType);
        if (factory != null) {
            Lecturer newLecturer = factory.create(scan, name, id, degreeName, salary, department);
            manager.addLecturer(newLecturer);
            System.out.println("Lecturer added successfully!");
        } else {
            System.out.println("Error: No factory found for this degree type.");
        }
    }

    @Override
    public String getDescription() {
        return "Add lecturer";
    }
}