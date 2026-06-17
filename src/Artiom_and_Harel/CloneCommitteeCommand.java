package Artiom_and_Harel;
import Artiom_and_Harel.*;
import java.util.Scanner;

public class CloneCommitteeCommand implements Command {
    private CollegeManager manager;
    private Scanner scan;

    public CloneCommitteeCommand(CollegeManager manager, Scanner scan) {
        this.manager = manager;
        this.scan = scan;
    }

    @Override
    public void execute() {
        try {
            System.out.println("Please enter the name of the committee you would like to clone");
            String name = InputHelper.getName(scan);
            Committee currentCommittee = CollegeManager.findByName(manager.getCommittees(), name);

            Committee cloned = currentCommittee.clone();
            manager.addCommittee(cloned);
            System.out.println("The committee " + name + " has been cloned successfully");

            for (Lecturer current : manager.getLecturers()) {
                current.getInCommittee().add(cloned);
            }
        } catch (ObjectNotFoundException | CloneNotSupportedException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public String getDescription() { return "Clone a committee of your choice"; }
}