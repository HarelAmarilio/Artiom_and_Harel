package Artiom_and_Harel;
import Artiom_and_Harel.*;
import java.util.HashSet;
import java.util.Scanner;

public class ProfessorFactory implements LecturerFactory {
    @Override
    public Lecturer create(Scanner scan, String name, int id, String degreeName, double salary, String department) {
        HashSet<String> articlesNames = new HashSet<>();
        System.out.println("Please enter the names of the articles (or enter '#' to finish):");
        while (true) {
            String currentArticle = scan.nextLine();
            if (currentArticle.isEmpty()) continue;
            if (currentArticle.equals("#")) break;
            articlesNames.add(currentArticle);
        }
        System.out.println("Please enter who gave you your title:");
        String title = scan.nextLine();

        return new Professor(name, id, DgreeNames.PROFESSOR, degreeName, salary, department, id, articlesNames, title);
    }
}