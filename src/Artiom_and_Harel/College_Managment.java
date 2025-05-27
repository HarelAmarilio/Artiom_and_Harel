//Names of students - Artiom and Harel
package Artiom_and_Harel;
import java.util.Scanner;
public class College_Managment {
    private static Lecturer[] lecturers = new Lecturer[1];
    private static int size_of_lecturers = 0;
    private static Committee[] committees = new Committee[1];
    private static int size_of_committee = 0;
    private static Department[] departments = new Department[1];
    private static int size_of_departments = 0;

    public void print_Menu(){
        PrintRealMenu();
        Scanner scan = new Scanner(System.in);
        System.out.println("Please select an option: If you want to exit the system, enter 0");
        int input = scan.nextInt();
        while (input != 0) {
            switch (input) {
                case 0:
                    break;
                case 1:
                    System.out.println("Please enter lecturer's info");
                    Lecturer lecturer = LecturerBuild();
                    while(lecturer==null){
                        lecturer = LecturerBuild();
                    }
                    lecturers = (Lecturer[]) add(lecturer, lecturers, size_of_lecturers);
                    size_of_lecturers++;
                    // Adding the lecturer into the requested department
                    if(!lecturer.getDepartment().isEmpty()) {
                        InsertLecturerIntoDepartment(lecturer, departments, lecturer.getDepartment(), size_of_departments);
                    }
                    break;

                case 2:
                    try{
                    System.out.println("Please enter the name of the committee");
                    String committeeName = getName();
                    doesExistByName(committees,committeeName,size_of_committee);
                    System.out.println("Please enter the name of the head of the committee");
                    String headOfCommitteeName = getName();
                    Lecturer HeadOfCommittee=(Lecturer)findByName(lecturers,headOfCommitteeName,size_of_lecturers);

                    // Checking if the requested head of committee stands with the terms
                    isHeadCommitteeDr(headOfCommitteeName, committeeName);
                    Committee newCommittee = new Committee(committeeName,HeadOfCommittee);
                    committees = (Committee[]) add(newCommittee, committees, size_of_committee);
                    size_of_committee++;

                    } catch (ObjectExists | ObjectNotFoundException | LecturerCommitteeException e) {
                        System.err.println(e.getMessage());
                        break;
                    }
                    break;

                case 3:
                    try {
                    System.out.println("Please enter committee's name");
                    String committeeName = getName();
                    Committee selectedCommittee = (Committee) findByName(committees, committeeName, size_of_committee);
                    System.out.println("Please enter lecturer's name");
                    String lecturersName = getName();
                    Lecturer selectedlecturer = (Lecturer) findByName(lecturers, lecturersName, size_of_lecturers);
                    while(selectedlecturer.equals(selectedCommittee.getHeadOfCommittee())){
                        System.out.println("The selected lecturer is the head of the committee, please give another name");
                        lecturersName = getName();
                        selectedlecturer = (Lecturer) findByName(lecturers, lecturersName, size_of_lecturers);
                    }
                    InsertLecturerIntoCommittee(selectedlecturer, committees, selectedCommittee, size_of_committee);

                    }catch(ObjectNotFoundException | ObjectExists e){
                        System.err.println(e.getMessage());
                        break;
                    }
                    break;

                case 4:
                    try{
                    System.out.println("Please enter the name of the committee");
                    String committeeName = getName();
                    Committee changeHead = (Committee)findByName(committees,committeeName,size_of_committee);
                    System.out.println("Please enter the name of the lecturer you would like to make the head of the committee");
                    String headOfCommitteeName = getName();
                    Lecturer newHeadOfCommittee = (Lecturer) findByName(lecturers, headOfCommitteeName, size_of_lecturers);
                    isHeadCommitteeDr(headOfCommitteeName, committeeName);
                    changeHead.setHeadOfCommittee(newHeadOfCommittee);

                    } catch (ObjectNotFoundException | LecturerCommitteeException e) {
                        System.err.println(e.getMessage());
                        break;
                    }
                    break;

                case 5:
                    try {
                    System.out.println("Please enter the name of the committee");
                    String committeeName=getName();
                    Committee c = (Committee) findByName(committees, committeeName, size_of_committee);
                    System.out.println("Please enter the name of the lecturer you would like to remove:)");
                    String lecturersName = getName();
                    Lecturer lecturerRemove = (Lecturer) findByName(lecturers, lecturersName, size_of_lecturers);
                    RemoveFromCommittee(lecturerRemove,c.getLecturers(),c);
                    System.out.println("Lecturer has been removed");

                    }catch (ObjectNotFoundException e) {
                        System.err.println(e.getMessage());
                        break;
                    }
                    break;

                case 6:
                    try {
                    System.out.println("Please enter the name of the department");
                    String departmentName = getName();
                    doesExistByName(departments, departmentName, size_of_departments);
                    System.out.println("Please enter the number of students in this department");
                    int number_of_students = scan.nextInt();
                    // Checking if the lecturer already exists in the department's info
                    Department newDepartment = new Department(departmentName, number_of_students);
                    departments = (Department[]) add(newDepartment, departments, size_of_departments++);

                    }catch(ObjectExists e){
                        System.err.println(e.getMessage());
                        break;
                    }
                    break;

                case 7:
                    double Avg = 0;
                    for (int i = 0; i < size_of_lecturers; i++) {
                        Avg = Avg + lecturers[i].getSalary();
                    }
                    Avg = Avg / size_of_lecturers;
                    System.out.println("The average salary of the lectures is: " + Avg);
                    break;

                case 8:
                    int counter = 0;
                    scan.nextLine();
                    System.out.println("Average salaries of which department would you like?");
                    String Department = scan.nextLine();
                    double Average = 0;
                    for (int i = 0; i < size_of_lecturers; i++) {
                        if (lecturers[i].getDepartment().equals(Department)) {
                            Average = Average + lecturers[i].getSalary();
                            counter++;
                        }
                    }
                    Average = Average / counter;
                    System.out.println("The average salary of the lecturers in this department is: " + Average);
                    break;

                case 9:
                    printArray(lecturers, size_of_lecturers);
                    System.out.println();
                    break;

                case 10:
                    printArray(committees, size_of_committee);
                    System.out.println();
                    break;

                case 11:
                    try {
                    System.out.println("Please enter the name of the lecturer");
                    String LecturerName = getName();
                    //Checking by the name the lecturer exists in our system.
                    Lecturer selectedLecturer = (Lecturer) findByName(lecturers, LecturerName, size_of_lecturers);
                    System.out.println("Please enter the name of the department");
                    String department = getName();
                    isExist(selectedLecturer, departments, size_of_departments);
                    // Adding the lecturer to the selected department.
                    InsertLecturerIntoDepartment(selectedLecturer, departments, department, size_of_departments);

                    }catch(ObjectNotFoundException | ObjectExists e){
                        System.out.println(e.getMessage());
                        break;
                    }
                    break;
                case 12:
                    try{
                    System.out.println("Please enter the name of the Doctor");
                    String DoctorName = getName();
                    Lecturer currentDoctor = (Lecturer) findByName(lecturers, DoctorName, size_of_lecturers);

                    if(currentDoctor.getDegreeType()!=DgreeNames.DOCTOR){
                        System.out.println("This lecturer is not a doctor please try again");
                        System.out.println();
                        break;
                    }
                    Doctor currentDoc=(Doctor)currentDoctor;

                    System.out.println("Please enter the name of the Professor");
                    String ProfessorName = getName();
                    Lecturer currentProfessor = (Lecturer) findByName(lecturers, ProfessorName, size_of_lecturers);

                    if(currentProfessor.getDegreeType()!=DgreeNames.PROFESSOR){
                        System.out.println("This lecturer is not a professor please try again");
                        System.out.println();
                        break;
                    }
                    Doctor currentPro=(Doctor)currentProfessor;

                    int compare = currentDoc.compareTo(currentPro);
                    if (compare == -1)
                        System.out.println(currentDoc.getDegreeType()+ " " + currentDoc.getFullName() + " has less articles than  " + currentPro.getDegreeType() + " " + currentPro.getFullName());

                    else if (compare == 0)
                        System.out.println(currentDoc.getDegreeType() + " " + currentDoc.getFullName() + " has the same articles as  " + currentPro.getDegreeType() + " " + currentPro.getFullName());

                    else
                        System.out.println(currentDoc.getDegreeType() + " " + currentDoc.getFullName() + " has more articles than  " + currentPro.getDegreeType() + " " + currentPro.getFullName());

                    } catch (ObjectNotFoundException e) {
                        System.err.println(e.getMessage());
                        break;
                    }
                    break;
                case 13:
                    try {
                    System.out.println("Please enter the name of the committee you would like to clone");
                    String CommitteeName = getName();
                    Committee currentCommittee =(Committee)findByName(committees, CommitteeName, size_of_committee);
                    Committee cloned = currentCommittee.clone();
                    committees = (Committee[]) add(cloned, committees, size_of_committee);
                    size_of_committee++;
                    System.out.println("The committee " +CommitteeName + " has been cloned successfully");
                    for(int i=0; i < cloned.getLecturers().length;i++){
                        cloned.getLecturers()[i].setInCommittee((Committee[])add(cloned, cloned.getLecturers()[i].getInCommittee(),IndexOfFirstNull(cloned.getLecturers()[i].getInCommittee())));
                    }

                    }catch (ObjectNotFoundException | CloneNotSupportedException e){
                        System.err.println(e.getMessage());
                        break;
                    }
                    break;
                case 14:
                    try {
                    System.out.println("Please enter the name of the first committee");
                    String firstCommitteeName = getName();
                    System.out.println("Please enter the name of the second committee");
                    String secondCommitteeName = getName();
                    Committee first = (Committee) findByName(committees, firstCommitteeName, size_of_committee);
                    Committee second =(Committee) findByName(committees, secondCommitteeName, size_of_committee);
                    System.out.println("For comparing with Articles Count - Press 1, For comparing with Staff Count - Press 2");
                    int digit = scan.nextInt();
                        if(digit==1){
                        ArticlesCountComparator articlesCountComparator = new ArticlesCountComparator();
                        System.out.println(articlesCountComparator.compare(first, second));
                        }
                        if(digit==2){
                        StaffCountCompertator a=new StaffCountCompertator();
                        System.out.println(a.compare(first, second));
                        }

                    }catch (ObjectNotFoundException e) {
                        System.err.println(e.getMessage());
                        break;
                    }

                default:
                    System.out.println("Invalid option!");
            }
            PrintRealMenu();
            input = scan.nextInt();
        }
        System.out.println("Thank you for using the system :)");
    }

    // A function for getting the specified name for lecturer/department/committee
    public static String getName() {
        Scanner scan = new Scanner(System.in);
        String name = scan.nextLine();
        while (name.isEmpty()) {
            System.out.println("You haven't entered a name! Please enter a valid name.");
            name = scan.nextLine();
        }
        return name;
    }
    public static Object findByName(Object [] details, String name,int size) throws ObjectNotFoundException{
        if(details instanceof Lecturer[]) {
            for (int i = 0; i < size; i++) {
                if ((lecturers[i]!=null) && lecturers[i].getFullName().equals(name)) {
                    return lecturers[i];
                }
            }
        }
        else if(details instanceof Committee[]) {
            for(int i=0;i < size_of_committee;i++){
                if(committees[i] != null && committees[i].getName().equals(name)){
                    return committees[i];
                }
            }
        }
        else if(details instanceof Department[]) {
            for(int i=0;i<size_of_departments;i++){
                if(departments[i] != null && departments[i].getName().equals(name)){
                    return departments[i];
                }
            }
        }
        throw new ObjectNotFoundException("couldn't find what you were looking for, please try again");

    }

    // A function that checks if something exists already in the array by object
    public static void isExist(Object obj,Object [] details,int size) throws ObjectExists{
        for(int i=0;i<size;i++){
            if(details[i]!=null && details[i].equals(obj)) {
            throw new ObjectExists("Already exists!");

            }
        }
    }
    // The function updates the actual size of the array in case input has been made
    public static Object[] add(Object obj, Object[] details, int index) {
        if (index >= details.length) {
            details = increaseArraySize(details);
        }
        details[index] = obj;
        return details;
    }
    // Prints the selected array
    public static void printArray(Object [] details,int size){
        for(int i=0;i<size;i++){
            if(details[i]!=null){ System.out.println(details[i].toString());}
        }
    }
    // A function for increasing the size of the array if necessary
    public static Object[] increaseArraySize(Object[] details) {
        if (details instanceof Lecturer[]) {
            Lecturer[] newArray = new Lecturer[details.length * 2];
            for (int i = 0; i < details.length; i++) {
                newArray[i] = (Lecturer) details[i];
            }
            return newArray;

        } else if (details instanceof Committee[]) {
            Committee[] newArray = new Committee[details.length * 2];
            for (int i = 0; i < details.length; i++) {
                newArray[i] = (Committee) details[i];
            }
            return newArray;

        } else if (details instanceof Department[]) {
            Department[] newArray = new Department[details.length * 2];
            for (int i = 0; i < details.length; i++) {
                newArray[i] = (Department) details[i];
            }
            return newArray;
        }
        return details;

    }
    public static Lecturer LecturerBuild(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the name of the lecturer");
        String name = getName();
        try {
            doesExistByName(lecturers,name,size_of_lecturers);
        }catch(ObjectExists e){
            System.err.println(e.getMessage());
            return null;
        }
        System.out.println("Please enter lecturer's ID");
        int ID= scan.nextInt();
        scan.nextLine();
        while(ID <= 0){
            System.out.println("The ID is not valid please enter again");
            ID =scan.nextInt();
            scan.nextLine();
        }
        DgreeNames dgreeType2 = null;
        while(true) {
            System.out.println("Please enter lecturer's degree type(Bachelor,Master,Doctor,Professor)");
            String degreeType = getName().toUpperCase();
            try{
                dgreeType2 = DgreeNames.valueOf(degreeType);
            break;
            }catch(IllegalArgumentException e) {
                System.out.println("Invalid degree type!");
            }
        }

        System.out.println("Please enter degree's name");
        String degreeName =getName();
        while(degreeName == null) {
            System.out.println("The degree name is not valid please enter again");
            degreeName = scan.nextLine();
        }
        System.out.println("Please enter lecturer's salary");
        double salary=scan.nextDouble();
        scan.nextLine();
        while(salary <= 0){
            System.out.println("The Salary is not valid please enter again");
            salary=scan.nextDouble();
            scan.nextLine();
        }
        System.out.println("Please enter lecturer's department");
        // Because there is a possibility the lecturer will not have a department. That's why using scan.
        String department = scan.nextLine();
        while(!department.isEmpty()){
            try {
                findByName(departments, department, size_of_departments);
                break;
            }catch (ObjectNotFoundException e){
                System.out.println(e.getMessage());
                System.out.println("please enter another department name");
                department = scan.nextLine();
            }
        }
        if(dgreeType2 == DgreeNames.DOCTOR || dgreeType2 == DgreeNames.PROFESSOR){
            System.out.println("Please enter how many articles have you published");
            int numberOfArticles = scan.nextInt();
            String [] ArticlesNames= new String[numberOfArticles];
            for(int i=0;i< ArticlesNames.length;i++){
                System.out.println("Please enter the names of the articles");
                ArticlesNames[i] = getName();
            }
            if(dgreeType2 == DgreeNames.PROFESSOR){
                System.out.println("Please enter who gave you your title");
                String title = getName();
                return new Professor(name,ID,dgreeType2,degreeName,salary,department,numberOfArticles,ArticlesNames,title);
            }
            return new Doctor(name,ID,dgreeType2,degreeName,salary,department,numberOfArticles,ArticlesNames);
        }
        return new Lecturer(name,ID,dgreeType2,degreeName,salary,department);
    }


    public static void RemoveFromCommittee(Lecturer lecturer, Lecturer [] lecturersINCommittee, Committee committee){
        for(int i = 0; i< lecturersINCommittee.length; i++) {
            if (lecturersINCommittee[i].equals(lecturer)) {
                lecturersINCommittee[i] = null;
            }
        }
        for (int i = 0; i < lecturer.getInCommittee().length; i++) {
            if (lecturer.getInCommittee()[i] != null &&
                lecturer.getInCommittee()[i].equals(committee)) {
                lecturer.getInCommittee()[i] = null;
            }
        }
    }
    public static void InsertLecturerIntoCommittee(Lecturer selctedlecturer,Committee[] committees,Committee selctedCommittee,int size_of_committee) throws ObjectExists{
        for(int i=0;i<selctedlecturer.getInCommittee().length;i++) {
            if (selctedlecturer.getInCommittee()[i]!=null && selctedlecturer.getInCommittee()[i].equals(selctedCommittee)) {
                throw new ObjectExists("Lecturer already in committee");
            }
        }
        for(int i=0;i<size_of_committee;i++){
            if(selctedCommittee.equals(committees[i])){
                committees[i].setLecturers((Lecturer[])add(selctedlecturer, committees[i].getLecturers(),IndexOfFirstNull(committees[i].getLecturers())));
            }
        }
        for(int i=0;i<lecturers.length;i++) {
            if (selctedlecturer.equals(lecturers[i])) {
                lecturers[i].setInCommittee((Committee[]) add(selctedCommittee, lecturers[i].getInCommittee(), IndexOfFirstNull(lecturers[i].getInCommittee())));
            }
        }
    }
    public static void InsertLecturerIntoDepartment(Lecturer lecturer, Department[] departments, String department, int size_of_departments) {
    if (departments == null || size_of_departments == 0) {
        System.out.println("There are no departments as of now");
        return;
    }

    if (lecturer.getDepartment().isEmpty()) {
        lecturer.setDepartment(department);
        for (int i = 0; i < size_of_departments; i++) {
            if (departments[i] != null && departments[i].getName().equals(lecturer.getDepartment())) {
                departments[i].setLecturers((Lecturer[])add(lecturer, departments[i].getLecturers(), IndexOfFirstNull(departments[i].getLecturers())));
                return;
            }
        }
    } else {
        for (int i = 0; i < size_of_departments; i++) {
            if (departments[i] != null && departments[i].getName().equals(lecturer.getDepartment())) {
                departments[i].setLecturers((Lecturer[])add(lecturer, departments[i].getLecturers(), IndexOfFirstNull(departments[i].getLecturers())));
                return;
            }
        }
    }
}
    public static void isHeadCommitteeDr(String LecturerName,String committeeName) throws LecturerCommitteeException {
        try {
        Lecturer headOfCommittee=(Lecturer) findByName(lecturers, LecturerName, size_of_lecturers);
        // Checking if the selected lecturer is a dr/professor or not
        if(headOfCommittee.getDegreeType()==DgreeNames.DOCTOR || headOfCommittee.getDegreeType()==DgreeNames.PROFESSOR){
            return;
        }
        throw new LecturerCommitteeException("The lecturer you requested is not in line with the terms");

        }catch(ObjectNotFoundException e){
            System.err.println(e.getMessage());
        }
    }
    public static int IndexOfFirstNull(Object[] details){
        for(int i=0;i< details.length;i++){
            if(details[i]==null){return i;}
        }
        return details.length;
    }
    // in case there is something that needs to throw that something does exist already
    public static void doesExistByName(Object[] details, String name, int size) throws ObjectExists{
        try {
            findByName(details, name, size);
            throw new ObjectExists("Name already exists");
        } catch (ObjectNotFoundException e) {
            //nothing
        }
    }

    public static void PrintRealMenu(){
        System.out.println("0) Exit the system");
        System.out.println("1) Add lecturer");
        System.out.println("2) Add committee");
        System.out.println("3) Add lecturer to a committee");
        System.out.println("4) Assigning a head of committee to a committee");
        System.out.println("5) Remove lecturer from committee ");
        System.out.println("6) Add a department ");
        System.out.println("7) Average salaries of all college lecturers");
        System.out.println("8) Average salaries of college lecturers belonging to a specific department");
        System.out.println("9) All lecturers info");
        System.out.println("10) All committees info");
        System.out.println("11) Assign lecturer to a department ");
        System.out.println("12) Compering between Professor and Doctor - Number of articles ");
        System.out.println("13) Clone a committee of your choice ");
        System.out.println("14) Comparing between two committees of your choice ");
    }
}