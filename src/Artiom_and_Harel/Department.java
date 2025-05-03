package Artiom_and_Harel;

import java.util.Objects;

public class Department {
    private String Name;
    private int number_of_students;
    private Lecturer [] lecturers=new Lecturer[1];
    // Our attributes

    public Department(String name, int number_of_students) {
        setName(name);
        setNumber_of_students(number_of_students);
        setLecturers(lecturers);
    }
    public String getName() {
        return Name;
    }

    public int getNumber_of_students() {
        return number_of_students;
    }

    public Lecturer[] getLecturers() {
        return lecturers;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public void setNumber_of_students(int number_of_students) {
        this.number_of_students = number_of_students;
    }

    public void setLecturers(Lecturer[] lecturers) {
        this.lecturers = lecturers;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(Name, that.Name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(Name);
    }

    @Override
    public String toString() {
        String lecturersNames = "";

        if (lecturers != null) {
            for (Lecturer lecturer : lecturers) {
                if (lecturer == null){continue;}
                if (!lecturersNames.isEmpty()) {
                    lecturersNames += ", ";
                }
                lecturersNames += lecturer.getFullName();

            }
        }

        return "Department{" +
                "Name='" + Name + '\'' +
                ", number of students=" + number_of_students +
                ", lecturers=" + lecturersNames +
                '}';
    }
}