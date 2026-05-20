package Artiom_and_Harel;
import java.util.HashSet;
import java.util.Objects;
import java.io.Serializable;
public class Lecturer implements Nameable, Serializable {
    protected String FullName;
    protected int ID;
    protected DgreeNames DegreeType;
    protected String DegreeName;
    protected double Salary;
    protected String department;
    protected HashSet<Committee> InCommittee=new HashSet<>();
    private static final long serialVersionUID = 1L;

    public Lecturer(String fullName, int ID, DgreeNames degreeType, String degreeName, double salary, String department) {
        setFullName(fullName);
        setID(ID);
        setDegreeType(degreeType);
        setDegreeName(degreeName);
        setSalary(salary);
        setDepartment(department);
    }
    //if the lecturer doesn't have a department yet
    public Lecturer(String fullName, double salary, String degreeName, DgreeNames degreeType, int ID) {
        this(fullName,ID,degreeType,degreeName,salary,null);
    }

    public HashSet<Committee> getInCommittee() {
        return InCommittee;
    }

    public void setInCommittee(HashSet<Committee> committee) {
        InCommittee = committee;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return Salary;
    }

    public void setSalary(double salary) {
        this.Salary = salary;
    }

    public String getDegreeName() {
        return DegreeName;
    }

    public void setDegreeName(String degreeName) {
        this.DegreeName = degreeName;
    }

    public DgreeNames getDegreeType() {
        return DegreeType;
    }

    public void setDegreeType(DgreeNames degreeType) {
        this.DegreeType = degreeType;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        this.FullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Lecturer lecturer = (Lecturer) o;
        return Objects.equals(FullName, lecturer.FullName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(FullName);
    }

    @Override
    public String toString() {
        String committeessNames = "";

        if (InCommittee != null) {
            for (Committee committee : InCommittee) {
                if (committee == null){continue;}
                if (!committeessNames.isEmpty()) {
                    committeessNames += ", ";
                }
                committeessNames += committee.getName();

            }
        }

        return
                "Full Name='" + FullName + '\'' +
                        ", ID=" + String.format("%09d",ID) +
                        ", Degree Type='" + DegreeType + '\'' +
                        ", Degree Name='" + DegreeName + '\'' +
                        ", Salary=" + Salary +
                        ", department='" + department + '\''+
                        ", committees='" + committeessNames + '\'';

    }

}
