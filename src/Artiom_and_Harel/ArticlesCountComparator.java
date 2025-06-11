package Artiom_and_Harel;

import java.util.Comparator;

public class ArticlesCountComparator implements Comparator<Committee>{
    private int SumArticles(Committee committee) {
        int sum = 0;
        for (int i = 0; i < committee.getLecturers().length; i++) {
            if (committee.getLecturers()[i].getDegreeType().name().equals("BACHELOR") | committee.getLecturers()[i].getDegreeType().name().equals("MASTER"))
                continue;
            Doctor current = (Doctor) committee.getLecturers()[i];
            sum += current.getNumberOfArticles();
        }
        return sum;
    }
    @Override
    public int compare(Committee o1, Committee o2) {
        int sum1 = SumArticles(o1);
        int sum2 = SumArticles(o2);
        return Integer.compare(sum1, sum2);
    }
}
