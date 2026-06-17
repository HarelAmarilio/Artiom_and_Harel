package Artiom_and_Harel;

import Artiom_and_Harel.*;
import java.util.Scanner;

public class CompareArticlesCommand implements Command { // שיניתי את השם כי עכשיו זה כללי!
    private CollegeManager manager;
    private Scanner scan;

    public CompareArticlesCommand(CollegeManager manager, Scanner scan) {
        this.manager = manager;
        this.scan = scan;
    }

    @Override
    public void execute() {
        try {
            System.out.println("Please enter the name of the first lecturer");
            Lecturer firstLecturer = CollegeManager.findByName(manager.getLecturers(), InputHelper.getName(scan));

            System.out.println("Please enter the name of the second lecturer");
            Lecturer secondLecturer = CollegeManager.findByName(manager.getLecturers(), InputHelper.getName(scan));

            // בדיקת LSP טהורה! האם שני המרצים מסוגלים להתנהג כמפרסמי מאמרים?
            if (!(firstLecturer instanceof ArticlePublisher) || !(secondLecturer instanceof ArticlePublisher)) {
                System.out.println("One or both of the selected lecturers do not publish articles.");
                return;
            }

            // כעת אנו בטוחים (Safe Cast) שמותר לנו להתייחס אליהם כמפרסמים
            ArticlePublisher pub1 = (ArticlePublisher) firstLecturer;
            ArticlePublisher pub2 = (ArticlePublisher) secondLecturer;

            int count1 = pub1.getNumberOfArticles();
            int count2 = pub2.getNumberOfArticles();

            if (count1 < count2) {
                System.out.println(firstLecturer.getName() + " has fewer articles than " + secondLecturer.getName());
            } else if (count1 == count2) {
                System.out.println(firstLecturer.getName() + " has the same number of articles as " + secondLecturer.getName());
            } else {
                System.out.println(firstLecturer.getName() + " has more articles than " + secondLecturer.getName());
            }

        } catch (ObjectNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public String getDescription() { return "Compare number of articles between two lecturers"; }
}