package Artiom_and_Harel;
import Artiom_and_Harel.*;

public class PrintAverageSalaryCommand implements Command {
    private CollegeManager manager;

    public PrintAverageSalaryCommand(CollegeManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute() {
        double avg = CollegeStatistics.calculateAverageSalary(manager.getLecturers());
        System.out.println("The average salary of the lectures is: " + avg);
    }

    @Override
    public String getDescription() {
        return "Average salaries of all college lecturers";
    }
}