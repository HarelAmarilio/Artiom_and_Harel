package Artiom_and_Harel;

import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;
// Data Management
public class CollegeManager implements Serializable {
    private static final long serialVersionUID = 1L;

    private Set<Lecturer> lecturers = new HashSet<>();
    private Set<Committee> committees = new HashSet<>();
    private Set<Department> departments = new HashSet<>();

    public Set<Lecturer> getLecturers() { return lecturers; }
    public Set<Committee> getCommittees() { return committees; }
    public Set<Department> getDepartments() { return departments; }

    public void addLecturer(Lecturer lecturer) {
        lecturers.add(lecturer);
        if (lecturer.getDepartment() != null && !lecturer.getDepartment().isEmpty()) {
            insertLecturerIntoDepartment(lecturer, lecturer.getDepartment());
        }
    }

    public void addCommittee(Committee committee) {
        committees.add(committee);
    }

    public void addDepartment(Department department) {
        departments.add(department);
    }

    public void removeFromCommittee(Lecturer lecturer, Committee committee) {
        if (lecturer == null || committee == null) return;
        committee.getLecturers().remove(lecturer);
        lecturer.getInCommittee().remove(committee);
    }

    public void insertLecturerIntoCommittee(Lecturer selectedLecturer, Committee selectedCommittee)
            throws ObjectExists, LecturerCommitteeException {
        if (!selectedLecturer.getDegreeType().equals(selectedCommittee.getTypeOfDegree())) {
            throw new LecturerCommitteeException("Lecturer degree is not the same type as of committee");
        }

        if (selectedLecturer.getInCommittee().contains(selectedCommittee)) {
            throw new ObjectExists("Lecturer already in committee");
        }

        selectedCommittee.getLecturers().add(selectedLecturer);
        selectedLecturer.getInCommittee().add(selectedCommittee); //CHECK
    }

    public void insertLecturerIntoDepartment(Lecturer lecturer, String departmentName) {
        if (departments.isEmpty()) return;

        if (lecturer.getDepartment().isEmpty()) {
            lecturer.setDepartment(departmentName);
        }

        for (Department d : departments) {
            if (d != null && d.getName().equals(departmentName)) {
                d.getLecturers().add(lecturer);
                return;
            }
        }
    }

    public void isHeadCommitteeDr(String lecturerName) throws LecturerCommitteeException, ObjectNotFoundException {
        Lecturer headOfCommittee = findByName(lecturers, lecturerName);
        if (headOfCommittee.getDegreeType() == DgreeNames.DOCTOR || headOfCommittee.getDegreeType() == DgreeNames.PROFESSOR) {
            return;
        }
        throw new LecturerCommitteeException("The lecturer you requested is not in line with the terms");
    }

    public static <T extends Nameable> T findByName(Set<T> details, String name) throws ObjectNotFoundException {
        for (T element : details) {
            if (element != null && element.getName().equals(name)) return element;
        }
        throw new ObjectNotFoundException("Object with name '" + name + "' not found.");
    }

    public static <T> void isExist(Object obj, Set<T> details) throws ObjectExists {
        if (details.contains(obj)) {
            throw new ObjectExists("Object already exists.");
        }
    }

    public <T extends Nameable> void doesExistByName(Set<T> details, String name) throws ObjectExists {
        try {
            findByName(details, name);
            throw new ObjectExists("Name already exists");
        } catch (ObjectNotFoundException e) {
            // האובייקט לא קיים, ניתן להמשיך בבטחה
        }
    }
}