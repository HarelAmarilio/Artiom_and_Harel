package Artiom_and_Harel;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class CollegeUI {

    private CollegeManager manager;
    private Scanner scan;
    private Map<Integer, Command> menuCommands;
    private Map<DgreeNames, LecturerFactory> factories;
    private CollegeRepository repository;
    public CollegeUI(CollegeRepository repository) {
        this.repository = repository;
        this.scan = new Scanner(System.in);
        loadData();
        initializeFactories();
        initializeMenu();
    }

    private void loadData() {
        try {
            this.manager = repository.loadData(); // שימוש בממשק המופשט
            System.out.println("Successfully loaded college data.");
        } catch (Exception e) {
            System.out.println("Data file not found or corrupted. System initialized empty.");
            this.manager = new CollegeManager();
        }
    }

    private void initializeFactories() {
        factories = new HashMap<>();
        factories.put(DgreeNames.BACHELOR, new StandardLecturerFactory(DgreeNames.BACHELOR));
        factories.put(DgreeNames.MASTER, new StandardLecturerFactory(DgreeNames.MASTER));
        factories.put(DgreeNames.DOCTOR, new DoctorFactory());
        factories.put(DgreeNames.PROFESSOR, new ProfessorFactory());
    }

    private void initializeMenu() {
        menuCommands = new TreeMap<>();

        menuCommands.put(1, new AddLecturerCommand(manager, scan, factories));
        menuCommands.put(2, new AddCommitteeCommand(manager, scan));
        menuCommands.put(3, new AssignLecturerToCommitteeCommand(manager, scan));
        menuCommands.put(4, new ChangeCommitteeHeadCommand(manager, scan));
        menuCommands.put(5, new RemoveLecturerFromCommitteeCommand(manager, scan));
        menuCommands.put(6, new AddDepartmentCommand(manager, scan));
        menuCommands.put(7, new PrintAverageSalaryCommand(manager));
        menuCommands.put(8, new PrintDepartmentAverageSalaryCommand(manager, scan));
        menuCommands.put(9, new PrintAllLecturersCommand(manager, scan));
        menuCommands.put(10, new PrintAllCommitteesCommand(manager, scan));
        menuCommands.put(11, new AssignLecturerToDepartmentCommand(manager, scan));
        menuCommands.put(12, new CompareArticlesCommand(manager, scan));
        menuCommands.put(13, new CloneCommitteeCommand(manager, scan));
        menuCommands.put(14, new CompareTwoCommitteesCommand(manager, scan));
    }

    public void start() {
        while (true) {
            printMenu();
            System.out.print("Please select an option: If you want to exit the system, enter 0: ");

            if (!scan.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid number.\n");
                scan.nextLine();
                continue;
            }

            int input = scan.nextInt();
            scan.nextLine();

            if (input == 0) {
                exitSystem();
                break;
            }

            Command command = menuCommands.get(input);
            if (command != null) {
                command.execute();
                System.out.println();
            } else {
                System.out.println("Invalid option! Please try again.\n");
            }
        }
    }

    private void printMenu() {
        System.out.println("0) Exit the system");
        for (Map.Entry<Integer, Command> entry : menuCommands.entrySet()) {
            System.out.println(entry.getKey() + ") " + entry.getValue().getDescription());
        }
    }

    private void exitSystem() {
        try {
            repository.saveData(manager); // שימוש בממשק המופשט
            System.out.println("Data saved successfully.");
        } catch (Exception e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
        System.out.println("Thank you for using the system :)");
    }
}