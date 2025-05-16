package Artiom_and_Harel;

public class LecturerCommitteeException extends Exception {
    public LecturerCommitteeException(String message) {
        super(message);
    }
    public LecturerCommitteeException(){super("You cant add this lecturer since he is not at least a Dr");}
}
