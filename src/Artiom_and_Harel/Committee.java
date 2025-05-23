package Artiom_and_Harel;

import java.util.Arrays;
import java.util.Objects;

public class Committee implements Cloneable {
    private String name;
    private Lecturer[] lecturers=new Lecturer[1];
    private Lecturer headOfCommittee; // Must be a Dr + will not appear in the lectures
   public Committee clone() throws CloneNotSupportedException {
       Committee clone = (Committee) super.clone();
       if (this.lecturers != null) {
           clone.lecturers = Arrays.copyOf(this.lecturers, this.lecturers.length);
       }
       clone.headOfCommittee= (Lecturer) headOfCommittee.clone();
       clone.name="new-" + name; // Adding "new" as requested
       return clone;
   }
    // Adding a head of committee who's a lecturer and the array of the lecturers
    public Committee(String name, Lecturer headOfCommittee) {
        setName(name);
        setHeadOfCommittee(headOfCommittee);
        setLecturers(lecturers);
    }

    public Lecturer[] getLecturers() {
        return lecturers;
    }

    public Lecturer getHeadOfCommittee() {
        return headOfCommittee;
    }

    public void setLecturers(Lecturer[] lecturers) {
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
                    lecturersNames += lecturer.getFullName();
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
