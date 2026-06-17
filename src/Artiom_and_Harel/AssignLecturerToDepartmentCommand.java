package Artiom_and_Harel;
import Artiom_and_Harel.*;
import java.util.Scanner;

public class AssignLecturerToDepartmentCommand implements Command {
    private CollegeManager manager;
    private Scanner scan;

    public AssignLecturerToDepartmentCommand(CollegeManager manager, Scanner scan) {
        this.manager = manager;
        this.scan = scan;
    }

    @Override
    public void execute() {
        try {
            System.out.println("Please enter the name of the lecturer");
            String lecturerName = InputHelper.getName(scan);
            Lecturer selectedLecturer = CollegeManager.findByName(manager.getLecturers(), lecturerName);

            System.out.println("Please enter the name of the department");
            String departmentName = InputHelper.getName(scan);
            CollegeManager.isExist(selectedLecturer, manager.getDepartments());

            manager.insertLecturerIntoDepartment(selectedLecturer, departmentName);
            System.out.println("Lecturer assigned to department.");
        } catch (ObjectNotFoundException | ObjectExists e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public String getDescription() { return "Assign lecturer to a department"; }
}