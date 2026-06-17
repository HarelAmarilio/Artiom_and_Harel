package Artiom_and_Harel;
import Artiom_and_Harel.*;
import java.util.Scanner;

public class AddCommitteeCommand implements Command {
    private CollegeManager manager;
    private Scanner scan;

    public AddCommitteeCommand(CollegeManager manager, Scanner scan) {
        this.manager = manager;
        this.scan = scan;
    }

    @Override
    public void execute() {
        try {
            System.out.println("Please enter the name of the committee");
            String committeeName = InputHelper.getName(scan);
            manager.doesExistByName(manager.getCommittees(), committeeName);

            System.out.println("Please enter the name of the head of the committee");
            String headName = InputHelper.getName(scan);
            Lecturer head = CollegeManager.findByName(manager.getLecturers(), headName);

            DgreeNames typeOfCommittee;
            while (true) {
                System.out.println("Please enter committee's degree type (BACHELOR, MASTER, DOCTOR, PROFESSOR)");
                try {
                    typeOfCommittee = DgreeNames.valueOf(InputHelper.getName(scan).toUpperCase());
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid degree type!");
                }
            }

            manager.isHeadCommitteeDr(headName);
            Committee newCommittee = new Committee(committeeName, head, typeOfCommittee);
            manager.addCommittee(newCommittee);
            System.out.println("Committee added successfully!");
        } catch (ObjectExists | ObjectNotFoundException | LecturerCommitteeException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public String getDescription() { return "Add committee"; }
}