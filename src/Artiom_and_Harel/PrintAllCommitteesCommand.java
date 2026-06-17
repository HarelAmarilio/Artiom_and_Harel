package Artiom_and_Harel;
import Artiom_and_Harel.*;
import java.util.Scanner;
import java.util.Comparator;
import java.util.TreeSet;

public class PrintAllCommitteesCommand implements Command {
    private CollegeManager manager;
    private Scanner scan;

    public PrintAllCommitteesCommand(CollegeManager manager, Scanner scan) {
        this.manager = manager;
        this.scan = scan;
    }

    @Override
    public void execute() {
        if (manager.getCommittees().isEmpty()) {
            System.out.println("The list is empty.");
            return;
        }

        System.out.println("Do you want to print the details based on a certain criteria? (Yes/No)");
        String userChoice = InputHelper.getName(scan);

        Iterable<Committee> collectionToPrint = manager.getCommittees();

        if (userChoice.equalsIgnoreCase("Yes")) {
            System.out.println("Choose sorting criterion for Committees:");
            System.out.println("1) By Committee Name");
            System.out.println("2) By Articles Count");
            System.out.println("3) By Staff Count");
            int sortOption = scan.nextInt();
            scan.nextLine();

            Comparator<Committee> comparator = null;
            if (sortOption == 1) comparator = new CommitteeNameComparator();
            else if (sortOption == 2) {
                ArticlesCountComparator acc = new ArticlesCountComparator();
                comparator = (o1, o2) -> {
                    int res = acc.compare(o1, o2);
                    return (res == 0) ? o1.getName().compareTo(o2.getName()) : res;
                };
            }
            else if (sortOption == 3) {
                StaffCountCompertator scc = new StaffCountCompertator();
                comparator = (o1, o2) -> {
                    int res = scc.compare(o1, o2);
                    return (res == 0) ? o1.getName().compareTo(o2.getName()) : res;
                };
            }

            if (comparator != null) {
                TreeSet<Committee> sortedSet = new TreeSet<>(comparator);
                sortedSet.addAll(manager.getCommittees());
                collectionToPrint = sortedSet;
            }
        }

        for (Committee committee : collectionToPrint) {
            System.out.println(committee.toString());
        }
    }

    @Override
    public String getDescription() { return "All committees info"; }
}