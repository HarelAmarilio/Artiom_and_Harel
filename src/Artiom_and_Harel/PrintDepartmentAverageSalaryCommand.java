package Artiom_and_Harel;
import Artiom_and_Harel.*;
import java.util.Scanner;

public class PrintDepartmentAverageSalaryCommand implements Command {
    private CollegeManager manager;
    private Scanner scan;

    public PrintDepartmentAverageSalaryCommand(CollegeManager manager, Scanner scan) {
        this.manager = manager;
        this.scan = scan;
    }

    @Override
    public void execute() {
        System.out.println("Average salaries of which department would you like?");
        String deptName = scan.nextLine();
        double avg = CollegeStatistics.calculateDepartmentAverageSalary(manager.getLecturers(), deptName);
        System.out.println("The average salary of the lecturers in this department is: " + avg);
    }

    @Override
    public String getDescription() { return "Average salaries of college lecturers belonging to a specific department"; }
}