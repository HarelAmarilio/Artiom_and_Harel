package Artiom_and_Harel;
import Artiom_and_Harel.*;
import java.util.Scanner;
import java.util.Comparator;
import java.util.TreeSet;

public class PrintAllLecturersCommand implements Command {
    private CollegeManager manager;
    private Scanner scan;

    public PrintAllLecturersCommand(CollegeManager manager, Scanner scan) {
        this.manager = manager;
        this.scan = scan;
    }

    @Override
    public void execute() {
        if (manager.getLecturers().isEmpty()) {
            System.out.println("The list is empty.");
            return;
        }

        System.out.println("Do you want to print the details based on a certain criteria? (Yes/No)");
        String userChoice = InputHelper.getName(scan);

        Iterable<Lecturer> collectionToPrint = manager.getLecturers();

        if (userChoice.equalsIgnoreCase("Yes")) {
            System.out.println("Choose sorting criterion for Lecturers:");
            System.out.println("1) By Name (Alphabetical)");
            System.out.println("2) By Salary (Low to High)");
            int sortOption = scan.nextInt();
            scan.nextLine();

            Comparator<Lecturer> comparator = null;
            if (sortOption == 1) comparator = new NameComparator();
            else if (sortOption == 2) comparator = new SalaryComparator();

            if (comparator != null) {
                TreeSet<Lecturer> sortedSet = new TreeSet<>(comparator);
                sortedSet.addAll(manager.getLecturers());
                collectionToPrint = sortedSet;
            }
        }

        for (Lecturer lecturer : collectionToPrint) {
            System.out.println(lecturer.toString());
        }
    }

    @Override
    public String getDescription() { return "All lecturers info"; }
}