package Artiom_and_Harel;
import java.util.Comparator;

public class NameComparator implements Comparator<Lecturer> {
    @Override
    public int compare(Lecturer o1, Lecturer o2) {
        return o1.getName().compareTo(o2.getName());
    }
}