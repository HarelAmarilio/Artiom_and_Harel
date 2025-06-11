package Artiom_and_Harel;
import java.util.ArrayList;
import java.util.Objects;
import java.io.Serializable;
public class Committee implements Cloneable,Nameable,Serializable {
    private String name;
    private static final long serialVersionUID = 1L;
    private ArrayList<Lecturer> lecturers=new ArrayList<Lecturer>();
    private Lecturer headOfCommittee;
    private DgreeNames typeOfDegree;// Must be a Dr + will not appear in the lectures

    @Override
   public Committee clone() throws CloneNotSupportedException {
       Committee clone = (Committee) super.clone();
       clone.CopyArray(this.lecturers);
       clone.name = this.name + "-new"; // Adding "new" as requested
       return clone;
   }
    // Adding a head of committee who's a lecturer and the array of the lecturers
    public Committee(String name, Lecturer headOfCommittee, DgreeNames typeOfDegree) {
        setName(name);
        setHeadOfCommittee(headOfCommittee);
        setLecturers(lecturers);
        setTypeOfDegree(typeOfDegree);
    }


    public DgreeNames getTypeOfDegree() {
        return typeOfDegree;
    }

    public ArrayList<Lecturer> getLecturers() {
        return lecturers;
    }

    public Lecturer getHeadOfCommittee() {
        return headOfCommittee;
    }
    public void setTypeOfDegree(DgreeNames typeOfDegree) {
        this.typeOfDegree = typeOfDegree;
    }

    public void setLecturers(ArrayList<Lecturer> lecturers) {
        this.lecturers = lecturers;
    }

    public void setHeadOfCommittee(Lecturer headOfCommittee) {
        this.headOfCommittee = headOfCommittee;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Committee committee = (Committee) o;
        return Objects.equals(name, committee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    //makes a shallow copy of the lecturers array for the clone action
    public void CopyArray(ArrayList<Lecturer> lecturers){
        if (lecturers != null && !lecturers.isEmpty()) {
            this.lecturers = new ArrayList<>(lecturers.size());
            for (int i = 0; i < lecturers.size(); i++) {
                this.lecturers.add(lecturers.get(i));
            }
        } else {
            this.lecturers = new ArrayList<>(1);
        }
    }


    @Override
    public String toString() {
        String lecturersNames = "";

        if (lecturers != null) {
            for (Lecturer lecturer : lecturers) {
                if (lecturer == null){continue;}
                if (!lecturer.equals(headOfCommittee)) {
                    if (!lecturersNames.isEmpty()) {
                        lecturersNames += ", ";
                    }
                    lecturersNames += lecturer.getName();
                }
            }
        }

        return "Committee{" +
                "name='" + name + '\'' +
                ", lecturers=" + lecturersNames +
                '}';
    }
}

//עבור כל ועדה יש לשמור את שמה, את רשימת המרצים שחברים בה וכן המרצה שהוא יו"ר הועדה. יו"ר הועדה חייב להיות מרצה שהוא ד"ר לפחות, יו"ר הועדה לא יופיע ברשימת המרצים החברים בוועדה.
