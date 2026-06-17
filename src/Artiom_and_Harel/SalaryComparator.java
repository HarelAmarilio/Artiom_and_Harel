package Artiom_and_Harel;
import java.util.Comparator;

public class SalaryComparator implements Comparator<Lecturer> {
    @Override
    public int compare(Lecturer l1, Lecturer l2) {
        int res = Double.compare(l1.getSalary(), l2.getSalary());
        if (res == 0) {
            return l1.getName().compareTo(l2.getName());
        }
        return res;
    }
}