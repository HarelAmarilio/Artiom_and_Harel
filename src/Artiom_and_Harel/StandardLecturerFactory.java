package Artiom_and_Harel;
import Artiom_and_Harel.*;
import java.util.Scanner;

public class StandardLecturerFactory implements LecturerFactory {
    private DgreeNames type;

    public StandardLecturerFactory(DgreeNames type) {
        this.type = type;
    }

    @Override
    public Lecturer create(Scanner scan, String name, int id, String degreeName, double salary, String department) {
        return new Lecturer(name, id, type, degreeName, salary, department);
    }
}