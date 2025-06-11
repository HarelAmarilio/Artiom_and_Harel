package Artiom_and_Harel;

import java.util.Comparator;

public class StaffCountCompertator implements Comparator<Committee> {
    @Override
    public int compare(Committee c1, Committee c2) {
        return Integer.compare(c1.getLecturers().length, c2.getLecturers().length);

    }
}
