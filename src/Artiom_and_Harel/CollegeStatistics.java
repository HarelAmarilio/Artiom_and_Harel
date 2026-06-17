package Artiom_and_Harel;

import java.util.HashSet;
import java.util.Set;

public class CollegeStatistics {

    public static double calculateAverageSalary(Iterable<? extends Payable> employees) {
        if (employees == null || !employees.iterator().hasNext()) return 0;

        double sum = 0;
        int count = 0;
        for (Payable current : employees) {
            sum += current.getSalary();
            count++;
        }
        return sum / count;
    }

    public static double calculateDepartmentAverageSalary(Set<Lecturer> lecturers, String departmentName) {
        if (lecturers == null || lecturers.isEmpty()) return 0;
        double sum = 0;
        int counter = 0;
        for (Lecturer current : lecturers) {
            if (current.getDepartment() != null && current.getDepartment().equals(departmentName)) {
                sum += current.getSalary();
                counter++;
            }
        }
        return counter == 0 ? 0 : sum / counter;
    }
}