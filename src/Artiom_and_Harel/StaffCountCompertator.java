package Artiom_and_Harel;

import java.io.Serializable;
import java.util.Comparator;

public class StaffCountCompertator implements Comparator<Committee>, Serializable {
    @Override
    public int compare(Committee c1, Committee c2) {
        return Integer.compare(c1.getLecturers().size(), c2.getLecturers().size());

    }
}
