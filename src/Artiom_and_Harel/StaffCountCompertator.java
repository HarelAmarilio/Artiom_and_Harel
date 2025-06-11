package Artiom_and_Harel;

import java.util.Comparator;

public class StaffCountCompertator implements Comparator<Committee> {
    @Override
    public int compare(Committee c1, Committee c2) {
        return Integer.compare(c1.getLecturers().size(), c2.getLecturers().size());

    }
}
