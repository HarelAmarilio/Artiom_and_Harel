package Artiom_and_Harel;
import java.util.Objects;

public class Committee implements Cloneable {
    private String name;
    private Lecturer[] lecturers=new Lecturer[1];
    private Lecturer headOfCommittee; // Must be a Dr + will not appear in the lectures

   public Committee clone() throws CloneNotSupportedException {
       Committee clone = (Committee) super.clone();
       clone.CopyArray(this.lecturers);
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

    //makes a shallow copy of the lecturers array for the clone action
    public void CopyArray(Lecturer[] lecturers){
       if(lecturers != null && lecturers.length >0){
           this.lecturers = new Lecturer[lecturers.length];
           for(int i=0;i<lecturers.length;i++){
               if (lecturers[i] !=null){
                   this.lecturers[i] = lecturers[i];
               }else{
                   this.lecturers[i]=null;
               }
           }
       }else{
           this.lecturers=new Lecturer[0];
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
