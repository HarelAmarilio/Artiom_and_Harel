package Artiom_and_Harel;
import java.io.Serializable;
public class LecturerCommitteeException extends Exception implements Serializable {
    public LecturerCommitteeException(String message) {
        super(message);
    }
    public LecturerCommitteeException(){super("You cant add this lecturer since he is not at least a Dr");}
}
