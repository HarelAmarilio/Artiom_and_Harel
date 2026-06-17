package Artiom_and_Harel;
import Artiom_and_Harel.*;
import java.util.Scanner;

public class ChangeCommitteeHeadCommand implements Command {
    private CollegeManager manager;
    private Scanner scan;

    public ChangeCommitteeHeadCommand(CollegeManager manager, Scanner scan) {
        this.manager = manager;
        this.scan = scan;
    }

    @Override
    public void execute() {
        try {
            System.out.println("Please enter the name of the committee");
            String committeeName = InputHelper.getName(scan);
            Committee changeHead = CollegeManager.findByName(manager.getCommittees(), committeeName);

            System.out.println("Please enter the name of the lecturer you would like to make the head");
            String headName = InputHelper.getName(scan);
            Lecturer newHead = CollegeManager.findByName(manager.getLecturers(), headName);

            manager.isHeadCommitteeDr(headName);
            changeHead.setHeadOfCommittee(newHead);
            System.out.println("Head of committee updated.");
        } catch (ObjectNotFoundException | LecturerCommitteeException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public String getDescription() { return "Assigning a head of committee to a committee"; }
}