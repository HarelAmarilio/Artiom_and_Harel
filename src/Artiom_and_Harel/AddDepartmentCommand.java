package Artiom_and_Harel;
import Artiom_and_Harel.*;
import java.util.Scanner;

public class AddDepartmentCommand implements Command {
    private CollegeManager manager;
    private Scanner scan;

    public AddDepartmentCommand(CollegeManager manager, Scanner scan) {
        this.manager = manager;
        this.scan = scan;
    }

    @Override
    public void execute() {
        try {
            System.out.println("Please enter the name of the department");
            String departmentName = InputHelper.getName(scan);
            manager.doesExistByName(manager.getDepartments(), departmentName);

            System.out.println("Please enter the number of students in this department");
            int numStudents = scan.nextInt();
            scan.nextLine(); // ניקוי ה-buffer

            Department newDepartment = new Department(departmentName, numStudents);
            manager.addDepartment(newDepartment);
            System.out.println("Department added.");
        } catch (ObjectExists e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public String getDescription() { return "Add a department"; }
}