package Artiom_and_Harel;
import java.io.Serializable;
public class ObjectExists extends Exception implements Serializable {
    public ObjectExists(String message) {
        super(message);
    }

    public ObjectExists() {
        super("Object already exists");
    }
}
