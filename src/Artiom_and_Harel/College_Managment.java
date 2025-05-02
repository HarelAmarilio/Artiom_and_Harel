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

    public void print_Menu() {
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
                    lecturers = (Lecturer[]) add(lecturer, lecturers, size_of_lecturers);
                    size_of_lecturers++;
                    // Adding the lecturer into the requested department
                    if(!lecturer.getDepartment().isEmpty()) {
                        InsertLecturerIntoDepartment(lecturer, departments, lecturer.getDepartment(), size_of_departments);
                    }
                    break;

                case 2:
                    System.out.println("Please enter the name of the committee");
                    String committeeName = getName();
                    while (isExists(committeeName, committees, size_of_committee)) {
                        System.out.println("Please enter a name of another committee");
                        committeeName = getName();
                    }
                    System.out.println("Please enter the name of the head of the committee");
                    String headOfCommitteeName = getName();
                    while (!isHeadCommitteeDr(headOfCommitteeName)){
                        System.out.println("The selected lecturer is not a dr/professor, please give another name");
                        headOfCommitteeName = getName();
                    }
                    Lecturer headOfCommittee = (Lecturer)findByName(lecturers,headOfCommitteeName,size_of_lecturers);
                    Committee newCommittee = new Committee(committeeName,headOfCommittee);
                    committees = (Committee[]) add(newCommittee, committees, size_of_committee);
                    size_of_committee++;
                    break;

                case 3:
                    System.out.println("Please enter committee's name");
                    String committeesName = getName();
                    while (!isExists(committeesName, committees, size_of_committee)) {
                        System.out.println("this committee doesn't exist please enter a name of another committee");
                        committeesName = getName();
                    }
                    Committee selectedCommittee=findCommitteebyName(committees,committeesName,size_of_committee);
                    System.out.println("Please enter lecturer's name");
                    String lecturersName = getName();
                    while (!isExists(lecturersName, lecturers, size_of_lecturers)) {
                        System.out.println("this Lecturer doesn't exist please enter a name of another lecturer");
                        lecturersName = getName();
                    }
                    Lecturer selectedlecturer=(Lecturer)findByName(lecturers,lecturersName,size_of_lecturers);
                    while(selectedlecturer.equals(selectedCommittee.getHeadOfCommittee())){
                        System.out.println("The selected lecturer is the head of the committee, please give another name");
                        lecturersName = getName();
                        selectedlecturer=(Lecturer)findByName(lecturers,lecturersName,size_of_lecturers);
                    }
                    InsertLecturerIntoCommittee(selectedlecturer,committees,selectedCommittee,size_of_committee);

                    break;

                case 4:
                    System.out.println("Please enter the name of the committee");
                    committeeName=getName();
                    System.out.println("Please enter the name of the lecturer you would like to add");
                    headOfCommitteeName = getName();
                    while(!isHeadCommitteeDr(headOfCommitteeName)){
                        System.out.println("Please enter a name of another lecturer");
                        headOfCommitteeName = getName();
                    }
                    Committee changeHead=(Committee)findByName(committees,committeeName,size_of_committee);
                    Lecturer newHeadOfCommittee=(Lecturer)findByName(lecturers,headOfCommitteeName,size_of_lecturers);
                    changeHead.setHeadOfCommittee(newHeadOfCommittee);
                    break;


                case 5:
                    System.out.println("Please enter the name of the committee");
                    committeeName=getName();
                    Committee c=(Committee)findByName(committees,committeeName,size_of_committee);
                    System.out.println("Please enter the name of the lecturer you would like to remove:)");
                    lecturersName = getName();
                    Lecturer lecturerRemove=(Lecturer)findByName(lecturers,lecturersName,size_of_lecturers);
                    RemoveFromCommittee(lecturerRemove,c.getLecturers());
                    System.out.println("Lecturer has been removed");
                    break;

                case 6:
                    System.out.println("Please enter the name of the department");
                    String departmentName = getName();
                    while (isExist(departmentName, departments, size_of_departments)) {
                        System.out.println("Department name already exists, please enter another name");
                        departmentName = getName();
                    }
                    System.out.println("Please enter the number of students in this department");
                    int number_of_students = scan.nextInt();
                    // Checking if the lecturer already exists in the department's info
                    Department newDepartment = new Department(departmentName, number_of_students);
                    departments = (Department[]) add(newDepartment, departments, size_of_departments++);
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
                    System.out.println("Please enter the name of the lecturer");
                    String LecturerName = getName();
                    //Checking by the id the lecturer exists in our system.
                    while (findByName(lecturers, LecturerName, size_of_lecturers) == null) {
                        System.out.println("This lecturer is not in our database. Please enter another name");
                        LecturerName = getName();
                    }
                    Lecturer selectedLecturer = (Lecturer)findByName(lecturers, LecturerName, size_of_lecturers);
                    System.out.println("Please enter the name of the department");
                    String department = getName();
                    if (isExist(selectedLecturer, departments, size_of_departments)) {
                        System.out.println("Already assigned in the department!");
                        break;
                    }
                    // Adding the lecturer to the selected department.
                    InsertLecturerIntoDepartment(selectedLecturer, departments, department, size_of_departments);
                    break;
                case 12:
                    printArray(departments, size_of_departments);
                    break;
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
    public static Object findByName(Object [] details, String name,int size) {
        if(details instanceof Lecturer[]) {
            for (int i = 0; i < size; i++) {
                if (!(lecturers[i].equals(null))&&lecturers[i].getFullName().equals(name)) {
                    return lecturers[i];
                }
            }
        }
        else if(details instanceof Committee[]) {
            for(int i=0;i < size_of_committee;i++){
                if(!(committees[i].equals(null))&&committees[i].getName().equals(name)){
                    return committees[i];
                }
            }
        }
        else if(details instanceof Department[]) {
            for(int i=0;i<size_of_departments;i++){
                if(!(departments[i].equals(null))&&departments[i].getName().equals(name)){
                    return departments[i];
                }
            }
        }
        return null;

    }
    public static Committee findCommitteebyName(Committee[] committees,String name,int size_of_committees){
        for(int i=0;i < size_of_committees;i++){
            if(committees[i].getName().equals(name)){
                return committees[i];
            }
        }
        return null;
    }
    // A function that checks if something exists already in the array by name
    public static boolean isExists(String name,Object [] details,int size){
        if(details instanceof Committee[]){
            for(int i=0;i < size;i++){
                if(committees[i].getName().equals(name)){
                    return true;
                }
            }
        }
        else if(details instanceof Lecturer[]){
            for(int i=0;i < size;i++){
                if (lecturers[i].getFullName().equals(name)){
                    return true;
                }
            }
        }
        else if(details instanceof Department[]){
            for(int i=0;i < size;i++){
                if (departments[i].getName().equals(name)){
                    return true;
                }
            }
        }
        return false;
    }
    // A function that checks if something exists already in the array by object
    public static boolean isExist(Object obj,Object [] details,int size) {
        if (details instanceof Department[] && obj instanceof Lecturer) {
            for(int i=0;i<size_of_departments;i++){
                if(departments[i].getName().equals(((Lecturer) obj).getDepartment())){
                    return true;
                }
            }
            return false;
        }
        for(int i=0;i<size;i++){
            if(details[i]!=null && details[i].equals(obj)) {
                System.out.println("Already exists!");
                return true;
            }
        }
        return false;
    }
    // The function updates the actual size of the array in case input has been made
    public static Object[] add(Object obj, Object[] details, int size) {
        if (size >= details.length) {
            details = increaseArraySize(details);
        }
        details[size] = obj;
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
        while (name.isEmpty() || findByName(lecturers,name,size_of_lecturers)!=null) {
            System.out.println("please enter a valid name");
            name = getName();
        }
        System.out.println("Please enter lecturer's ID");
        int ID= scan.nextInt();
        scan.nextLine();
        System.out.println("Please enter lecturer's degree type(Dr,B.A,M.A,Professor)");
        String degreeType = getName();
        System.out.println("Please enter degree's name");
        String degreeName =getName();
        System.out.println("Please enter lecturer's salary");
        double salary=scan.nextDouble();
        scan.nextLine();
        System.out.println("Please enter lecturer's department");
        // Because there is a possibiliy the lecturer will not have a department. That's why using scan.
        String department = scan.nextLine();
        while(department!=""&!(isExists(department,departments,size_of_departments))){
            System.out.println("the following department doesn't exist please enter a valid department name:");
            department = scan.nextLine();
        }

        Lecturer newLecturer=new Lecturer(name,ID,degreeType,degreeName,salary,department);
        return newLecturer;
    }
    public static void RemoveFromCommittee(Lecturer lecturer,Lecturer [] lecturers){
        for(int i=0;i<lecturers.length;i++){
            if(lecturers[i].getFullName().equals(lecturer.getFullName())){
                lecturers[i]=null;

            }
        }
    }
    public static void InsertLecturerIntoCommittee(Lecturer selctedlecturer,Committee[] committees,Committee selctedCommittee,int size_of_committee){
        for(int i=0;i<size_of_committee;i++){
            if(selctedCommittee.equals(committees[i])){
                committees[i].setLecturers((Lecturer[])add(selctedlecturer, committees[i].getLecturers(),IndexOfFirstNull(committees[i].getLecturers())));
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
    
    System.out.println("Department not found: " + department);
}
    // Checking if the head of the committee is a dr/professor
    public static boolean isHeadCommitteeDr(String LecturerName) {
        Lecturer headOfCommittee = (Lecturer)findByName(lecturers,LecturerName,size_of_lecturers);
        // Checking if the selected lecturer is a dr/professor or not
        if(headOfCommittee.getDegreeType().equals("Dr")||headOfCommittee.getDegreeType().equals("professor")){
            return true;
        }
        return false;

    }
    public static int IndexOfFirstNull(Object[] details){
        for(int i=0;i< details.length;i++){
            if(details[i]==null){return i;}
        }
        return details.length;
    }
    public static void PrintRealMenu(){
        System.out.println("0) Exit the system");
        System.out.println("1) Add lecturer");
        System.out.println("2) Add committee");
        System.out.println("3) Add lecturer to a committee");
        System.out.println("4) Assigning a head of committee to a committee");
        System.out.println("5) Remove lecturer from committee ");
        System.out.println("6) Adding a department ");
        System.out.println("7) Average salaries of all college lecturers");
        System.out.println("8) Average salaries of college lecturers belonging to a specific department");
        System.out.println("9) All lecturers info");
        System.out.println("10) All committees info");
        System.out.println("11) Assign lecturer to a department ");
    }
}