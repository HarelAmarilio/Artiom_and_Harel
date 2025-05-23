package Artiom_and_Harel;

public class StaffCountCompertator implements Comperator<Committee>{
    @Override
    public int compare(Committee c1, Committee c2) {
        return Integer.compare(c1.getLecturers().length, c2.getLecturers().length);

    }
}
