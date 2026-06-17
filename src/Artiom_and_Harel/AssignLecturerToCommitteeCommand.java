package Artiom_and_Harel;
import Artiom_and_Harel.*;
import java.util.Scanner;

public class AssignLecturerToCommitteeCommand implements Command {
    private CollegeManager manager;
    private Scanner scan;

    public AssignLecturerToCommitteeCommand(CollegeManager manager, Scanner scan) {
        this.manager = manager;
        this.scan = scan;
    }

    @Override
    public void execute() {
        try {
            System.out.println("Please enter committee's name");
            String committeeName = InputHelper.getName(scan);
            Committee committee = CollegeManager.findByName(manager.getCommittees(), committeeName);

            System.out.println("Please enter lecturer's name");
            String lecturerName = InputHelper.getName(scan);
            Lecturer selectedLecturer = CollegeManager.findByName(manager.getLecturers(), lecturerName);

            while (selectedLecturer.equals(committee.getHeadOfCommittee())) {
                System.out.println("The selected lecturer is the head of the committee, please give another name");
                lecturerName = InputHelper.getName(scan);
                selectedLecturer = CollegeManager.findByName(manager.getLecturers(), lecturerName);
            }
            manager.insertLecturerIntoCommittee(selectedLecturer, committee);
            System.out.println("Lecturer assigned to committee successfully.");
        } catch (ObjectNotFoundException | LecturerCommitteeException | ObjectExists e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public String getDescription() { return "Add lecturer to a committee"; }
}