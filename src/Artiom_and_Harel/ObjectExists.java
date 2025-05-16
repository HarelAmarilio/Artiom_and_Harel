package Artiom_and_Harel;

public class ObjectExists extends Exception {
    public ObjectExists(String message) {
        super(message);
    }

    public ObjectExists() {
        super("Object already exists");
    }
}
