package Artiom_and_Harel;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the College Management System! Please enter the name of the college");
        String collegeName=scanner.nextLine();
        College_Managment college_managment = new College_Managment();
        college_managment.print_Menu();
        // Calling the menu method which activates the menu and the other methods in case of use :)
    }
}