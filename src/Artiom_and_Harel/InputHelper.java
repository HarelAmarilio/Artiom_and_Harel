package Artiom_and_Harel;

import java.util.Scanner;

public class InputHelper {
    public static String getName(Scanner scan) {
        String name = scan.nextLine();
        while (name.trim().isEmpty()) {
            System.out.println("You haven't entered anything! Please enter something valid.");
            name = scan.nextLine();
        }
        return name;
    }
}