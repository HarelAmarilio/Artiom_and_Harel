package Artiom_and_Harel;
import Artiom_and_Harel.*;
import java.util.Scanner;

public interface LecturerFactory {
    Lecturer create(Scanner scan, String name, int id, String degreeName, double salary, String department);
}