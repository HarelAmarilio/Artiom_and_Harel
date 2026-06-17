package Artiom_and_Harel;

public class Main {
    public static void main(String[] args) {
        CollegeRepository myRepository = new BinaryFileRepository();
        CollegeUI ui = new CollegeUI(myRepository);
        ui.start();
    }
}