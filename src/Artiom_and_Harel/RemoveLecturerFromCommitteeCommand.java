package Artiom_and_Harel;
import Artiom_and_Harel.*;
import java.util.Scanner;

public class RemoveLecturerFromCommitteeCommand implements Command {
    private CollegeManager manager;
    private Scanner scan;

    public RemoveLecturerFromCommitteeCommand(CollegeManager manager, Scanner scan) {
        this.manager = manager;
        this.scan = scan;
    }

    @Override
    public void execute() {
        try {
            System.out.println("Please enter the name of the committee");
            String committeeName = InputHelper.getName(scan);
            Committee c = CollegeManager.findByName(manager.getCommittees(), committeeName);

            System.out.println("Please enter the name of the lecturer you would like to remove:)");
            String lecturerName = InputHelper.getName(scan);
            Lecturer lecturerRemove = CollegeManager.findByName(manager.getLecturers(), lecturerName);

            manager.removeFromCommittee(lecturerRemove, c);
            System.out.println("Lecturer has been removed");
        } catch (ObjectNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public String getDescription() { return "Remove lecturer from committee"; }
}