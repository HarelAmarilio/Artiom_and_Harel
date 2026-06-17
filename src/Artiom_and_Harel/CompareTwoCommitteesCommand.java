package Artiom_and_Harel;
import Artiom_and_Harel.*;
import java.util.Scanner;

public class CompareTwoCommitteesCommand implements Command {
    private CollegeManager manager;
    private Scanner scan;

    public CompareTwoCommitteesCommand(CollegeManager manager, Scanner scan) {
        this.manager = manager;
        this.scan = scan;
    }

    @Override
    public void execute() {
        try {
            System.out.println("Please enter the name of the first committee");
            Committee first = CollegeManager.findByName(manager.getCommittees(), InputHelper.getName(scan));

            System.out.println("Please enter the name of the second committee");
            Committee second = CollegeManager.findByName(manager.getCommittees(), InputHelper.getName(scan));

            System.out.println("For comparing with Articles Count - Press 1, For comparing with Staff Count - Press 2");
            int digit = scan.nextInt();
            scan.nextLine();

            if (digit == 1) {
                ArticlesCountComparator comp = new ArticlesCountComparator();
                System.out.println(comp.compare(first, second));
            } else if (digit == 2) {
                StaffCountCompertator comp = new StaffCountCompertator();
                System.out.println(comp.compare(first, second));
            } else {
                System.out.println("Invalid selection.");
            }
        } catch (ObjectNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public String getDescription() { return "Comparing between two committees of your choice"; }
}