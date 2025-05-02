package Artiom_and_Harel;
import java.util.Scanner;
import java.util.Objects;

public class Lecturer {
    private String FullName;
    private int ID;
    private String DegreeType;
    private String DegreeName;
    private double Salary;
    private String department;

    public Lecturer(String fullName, int ID, String degreeType, String degreeName, double salary, String department) {
        setFullName(fullName);
        setID(ID);
        setDegreeType(degreeType);
        setDegreeName(degreeName);
        setSalary(salary);
        setDepartment(department);
    }
    //if the lecturer doesn't have a department yet
    public Lecturer(String fullName, double salary, String degreeName, String degreeType, int ID) {
        this(fullName,ID,degreeType,degreeName,salary,null);
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
        Scanner scan = new Scanner(System.in);
        while(salary == 0){
            System.out.println("The Salary is not valid please enter again");
            salary=scan.nextDouble();
        }
        this.Salary = salary;
    }

    public String getDegreeName() {
        return DegreeName;
    }

    public void setDegreeName(String degreeName) {
        Scanner scan = new Scanner(System.in);
        while(degreeName == null || degreeName.isEmpty()) {
            System.out.println("The degree name is not valid please enter again");
            degreeName = scan.nextLine();
        }
        this.DegreeName = degreeName;
    }

    public String getDegreeType() {
        return DegreeType;
    }

    public void setDegreeType(String degreeType) {
        Scanner scan = new Scanner(System.in);
        while(degreeType == null || degreeType.isEmpty() || !(degreeType.equals("Dr") || degreeType.equals("B.A") || degreeType.equals("M.A")|| degreeType.equals("professor"))) {
            System.out.println("The degree type is not valid please enter again");
            degreeType = scan.nextLine();
        }
        this.DegreeType = degreeType;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        Scanner scan = new Scanner(System.in);
        while(ID == 0){
            System.out.println("The ID is not valid please enter again");
            ID =scan.nextInt();
        }
        this.ID = ID;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        Scanner scan = new Scanner(System.in);
        while(fullName == null || fullName.isEmpty()) {
            System.out.println("The name is not valid please enter again");
            fullName = scan.nextLine();
        }
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
        return
                "FullName='" + FullName + '\'' +
                        ", ID=" + String.format("%09d",ID) +
                        ", DegreeType='" + DegreeType + '\'' +
                        ", DegreeName='" + DegreeName + '\'' +
                        ", Salary=" + Salary +
                        ",department='" + department + '\''+ '}';

    }
}