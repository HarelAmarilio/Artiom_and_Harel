package Artiom_and_Harel;
import java.util.Comparator;

public class CommitteeNameComparator implements Comparator<Committee> {
    @Override
    public int compare(Committee o1, Committee o2) {
        return o1.getName().compareTo(o2.getName());
    }
}