package Artiom_and_Harel;
import java.io.Serializable;
import java.util.Comparator;

public class ArticlesCountComparator implements Comparator<Committee>,Serializable{
    private int SumArticles(Committee committee) {
        int sum = 0;
        for (int i = 0; i < committee.getLecturers().size(); i++) {
            if (committee.getLecturers().get(i).getDegreeType().name().equals("BACHELOR") | committee.getLecturers().get(i).getDegreeType().name().equals("MASTER"))
                continue;
            Doctor current = (Doctor) committee.getLecturers().get(i);
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
