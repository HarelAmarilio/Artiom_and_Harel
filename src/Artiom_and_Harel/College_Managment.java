//Names of students - Artiom and Harel
package Artiom_and_Harel;
import java.io.*;
import java.util.HashSet; // FIX:Importing Hashset instead of ArrayList
import java.util.Scanner;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Comparator;

public class College_Managment implements Serializable {
    //FIX:Lines 9-11: Changing the Data Structure.
    private HashSet<Lecturer> lecturers = new HashSet<>();
    private HashSet<Committee> committees = new HashSet<Committee>();
    private HashSet<Department> departments = new HashSet<Department>();
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
                    lecturers.add(lecturer);
                    // Adding the lecturer into the requested department
                    if(!lecturer.getDepartment().isEmpty()) {InsertLecturerIntoDepartment(lecturer, departments, lecturer.getDepartment());
                    }
                    break;

                case 2:
                    try{
                        System.out.println("Please enter the name of the committee");
                        String committeeName = getName();
                        doesExistByName(committees,committeeName);
                        System.out.println("Please enter the name of the head of the committee");
                        String headOfCommitteeName = getName();
                        Lecturer HeadOfCommittee= findByName(lecturers,headOfCommitteeName);
                        DgreeNames typeOfCommittee = null;
                        while(true) {
                            System.out.println("Please enter commitee's degree type(Bachelor,Master,Doctor,Professor)");
                            String degreeType = getName().toUpperCase();
                            try{
                                typeOfCommittee = DgreeNames.valueOf(degreeType);
                                break;
                            }catch(IllegalArgumentException e) {
                                System.out.println("Invalid degree type!");
                            }
                        }
                        // Checking if the requested head of committee stands with the terms
                        isHeadCommitteeDr(headOfCommitteeName, committeeName);
                        Committee newCommittee = new Committee(committeeName,HeadOfCommittee,typeOfCommittee);
                        committees.add(newCommittee);

                    } catch (ObjectExists | ObjectNotFoundException | LecturerCommitteeException e) {
                        System.err.println(e.getMessage());
                        break;
                    }
                    break;

                case 3:
                    try {
                        System.out.println("Please enter committee's name");
                        String committeeName = getName();
                        Committee selectedCommittee = findByName(committees, committeeName);
                        System.out.println("Please enter lecturer's name");
                        String lecturersName = getName();
                        Lecturer selectedlecturer = findByName(lecturers, lecturersName);
                        while(selectedlecturer.equals(selectedCommittee.getHeadOfCommittee())){
                            System.out.println("The selected lecturer is the head of the committee, please give another name");
                            lecturersName = getName();
                            selectedlecturer = findByName(lecturers, lecturersName);
                        }
                        InsertLecturerIntoCommittee(selectedlecturer, committees, selectedCommittee);

                    }catch(ObjectNotFoundException | LecturerCommitteeException | ObjectExists e){
                        System.err.println(e.getMessage());
                        break;
                    }
                    break;

                case 4:
                    try{
                        System.out.println("Please enter the name of the committee");
                        String committeeName = getName();
                        Committee changeHead = findByName(committees,committeeName);
                        System.out.println("Please enter the name of the lecturer you would like to make the head of the committee");
                        String headOfCommitteeName = getName();
                        Lecturer newHeadOfCommittee = findByName(lecturers, headOfCommitteeName);
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
                        Committee c = findByName(committees, committeeName);
                        System.out.println("Please enter the name of the lecturer you would like to remove:)");
                        String lecturersName = getName();
                        Lecturer lecturerRemove = findByName(lecturers, lecturersName);
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
                        doesExistByName(departments, departmentName);
                        System.out.println("Please enter the number of students in this department");
                        int number_of_students = scan.nextInt();
                        // Checking if the lecturer already exists in the department's info
                        Department newDepartment = new Department(departmentName, number_of_students);
                        departments.add(newDepartment);

                    }catch(ObjectExists e){
                        System.err.println(e.getMessage());
                        break;
                    }
                    break;

                case 7:
                    double Avg = 0;
                    //FIX:Made a foreach in order to add each lecturer's salary.
                    for(Lecturer current: lecturers) {
                        Avg=Avg+current.getSalary();
                    }
                    Avg = Avg / lecturers.size();
                    System.out.println("The average salary of the lectures is: " + Avg);
                    break;

                case 8:
                    int counter = 0;
                    scan.nextLine();
                    System.out.println("Average salaries of which department would you like?");
                    String Department = scan.nextLine();
                    double Average = 0;
                    //FIX:Made a foreach in order to calculate each department it's average
                    for(Lecturer current: lecturers) {
                        if(current.getDepartment().equals(Department)){
                            Average=Average+current.getSalary();
                            counter++;
                        }
                    }
                    Average = Average / counter;
                    System.out.println("The average salary of the lecturers in this department is: " + Average);
                    break;

                case 9:
                    // To give sorting to lecturers

                    printHash(lecturers);
                    System.out.println();
                    break;

                case 10:
                    // To give sorting to committees
                    printHash(committees);
                    System.out.println();
                    break;

                case 11:
                    try {
                        System.out.println("Please enter the name of the lecturer");
                        String LecturerName = getName();
                        //Checking by the name the lecturer exists in our system.
                        Lecturer selectedLecturer = (Lecturer) findByName(lecturers, LecturerName);
                        System.out.println("Please enter the name of the department");
                        String department = getName();
                        isExist(selectedLecturer, departments);
                        // Adding the lecturer to the selected department.
                        InsertLecturerIntoDepartment(selectedLecturer, departments, department);

                    }catch(ObjectNotFoundException | ObjectExists e){
                        System.out.println(e.getMessage());
                        break;
                    }
                    break;
                case 12:
                    try{
                        System.out.println("Please enter the name of the Doctor");
                        String DoctorName = getName();
                        Lecturer currentDoctor = findByName(lecturers, DoctorName);

                        if(currentDoctor.getDegreeType()!=DgreeNames.DOCTOR){
                            System.out.println("This lecturer is not a doctor please try again");
                            System.out.println();
                            break;
                        }
                        Doctor currentDoc=(Doctor)currentDoctor;

                        System.out.println("Please enter the name of the Professor");
                        String ProfessorName = getName();
                        Lecturer currentProfessor = findByName(lecturers, ProfessorName);

                        if(currentProfessor.getDegreeType()!=DgreeNames.PROFESSOR){
                            System.out.println("This lecturer is not a professor please try again");
                            System.out.println();
                            break;
                        }
                        Doctor currentPro=(Doctor)currentProfessor;

                        int compare = currentDoc.compareTo(currentPro);
                        if (compare == -1)
                            System.out.println(currentDoc.getDegreeType()+ " " + currentDoc.getName() + " has less articles than  " + currentPro.getDegreeType() + " " + currentPro.getName());

                        else if (compare == 0)
                            System.out.println(currentDoc.getDegreeType() + " " + currentDoc.getName() + " has the same articles as  " + currentPro.getDegreeType() + " " + currentPro.getName());

                        else
                            System.out.println(currentDoc.getDegreeType() + " " + currentDoc.getName() + " has more articles than  " + currentPro.getDegreeType() + " " + currentPro.getName());

                    } catch (ObjectNotFoundException e) {
                        System.err.println(e.getMessage());
                        break;
                    }
                    break;
                case 13:
                    try {
                        System.out.println("Please enter the name of the committee you would like to clone");
                        String CommitteeName = getName();
                        Committee currentCommittee =findByName(committees, CommitteeName);
                        Committee cloned = currentCommittee.clone();
                        committees.add(cloned);
                        System.out.println("The committee " +CommitteeName + " has been cloned successfully");
                        for(Lecturer current: lecturers) {
                            current.getInCommittee().add(cloned);
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
                        Committee first = findByName(committees, firstCommitteeName);
                        Committee second = findByName(committees, secondCommitteeName);
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
            System.out.println("You haven't entered anything! Please enter something valid.");
            name = scan.nextLine();
        }
        return name;
    }
    // FIXED FIXED FIXED FIXED FIXED FIXED FIXED FIXED FIXED FIXED FIXED FIXED
    public static <T extends Nameable> T findByName(HashSet<T> details, String name) throws ObjectNotFoundException {
        for (T element : details) {
            if (element != null && element.getName().equals(name)) return element;
        }
        
        throw new ObjectNotFoundException("Object with name '" + name + "' not found.");
    }
//
    //FIX:A function that checks if something exists already in the array by object
    public static <T> void isExist(Object obj,HashSet<T> details) throws ObjectExists {
        if (details.contains(obj)){
            throw new ObjectExists("Object with name '" + obj + "' already exists.");
        }

    }

    // Prints the selected array FIXED FIXED FIXED FIXED FIXED FIXED FIXED FIXED FIXED FIXED FIXED FIXED
    public static <T> void printHash(HashSet<T> details){
        if (details == null || details.isEmpty()) {
            System.out.println("The list is empty.");
            return;
        }

        System.out.println("Do you want to print the details based on a certain criteria? (Yes/No)");
        String userChoice = getName();

        Comparator<T> chosenComparator = null;

        if (userChoice.equals("Yes")) {
            Scanner scan = new Scanner(System.in);
            T sampleElement = details.iterator().next();

            if (sampleElement instanceof Lecturer) {
                System.out.println("Choose sorting criterion for Lecturers:");
                System.out.println("1) By Name (Alphabetical)");
                System.out.println("2) By Salary (Low to High)");
                int sortOption = scan.nextInt();

                if (sortOption == 1) {
                    chosenComparator = new Comparator<T>() {
                        @Override
                        public int compare(T o1, T o2) {
                            return ((Lecturer) o1).getName().compareTo(((Lecturer) o2).getName());
                        }
                    };
                } else if (sortOption == 2) {
                    chosenComparator = new Comparator<T>() {
                        @Override
                        public int compare(T o1, T o2) {
                            Lecturer l1 = (Lecturer) o1;
                            Lecturer l2 = (Lecturer) o2;
                            int res = Double.compare(l1.getSalary(), l2.getSalary());
                            if (res == 0) {
                                return l1.getName().compareTo(l2.getName());
                            }
                            return res;
                        }
                    };
                }
            }
            else if (sampleElement instanceof Committee) {
                System.out.println("Choose sorting criterion for Committees:");
                System.out.println("1) By Committee Name");
                System.out.println("2) By Articles Count");
                System.out.println("3) By Staff Count");
                int sortOption = scan.nextInt();

                if (sortOption == 1) {
                    chosenComparator = new Comparator<T>() {
                        @Override
                        public int compare(T o1, T o2) {
                            return ((Committee) o1).getName().compareTo(((Committee) o2).getName());
                        }
                    };
                } else if (sortOption == 2) {
                    final ArticlesCountComparator acc = new ArticlesCountComparator();
                    chosenComparator = new Comparator<T>() {
                        @Override
                        public int compare(T o1, T o2) {
                            int res = acc.compare((Committee) o1, (Committee) o2);
                            if (res == 0) return ((Committee) o1).getName().compareTo(((Committee) o2).getName());
                            return res;
                        }
                    };
                } else if (sortOption == 3) {
                    final StaffCountCompertator scc = new StaffCountCompertator();
                    chosenComparator = new Comparator<T>() {
                        @Override
                        public int compare(T o1, T o2) {
                            int res = scc.compare((Committee) o1, (Committee) o2);
                            if (res == 0) return ((Committee) o1).getName().compareTo(((Committee) o2).getName());
                            return res;
                        }
                    };
                }
            }
        }

        Iterable<T> finalCollectionToPrint = details;

        if (chosenComparator != null) {
            TreeSet<T> sortedTreeSet = new TreeSet<>(chosenComparator);
            sortedTreeSet.addAll(details);
            finalCollectionToPrint = sortedTreeSet;
        }


        Iterator<T> iterator = finalCollectionToPrint.iterator();
        while (iterator.hasNext()) {
            T currentElement = iterator.next();
            System.out.println(currentElement.toString());
        }
    }

    public Lecturer LecturerBuild(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the name of the lecturer");
        String name = getName();
        try {
            doesExistByName(this.lecturers,name);
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
                findByName(this.departments, department);
                break;
            }catch (ObjectNotFoundException e){
                System.out.println(e.getMessage());
                System.out.println("please enter another department name");
                department = scan.nextLine();
            }
        }
        if(dgreeType2 == DgreeNames.DOCTOR || dgreeType2 == DgreeNames.PROFESSOR){
            // FIX:Changing into Hashset from ArrayList
            HashSet<String> ArticlesNames=new HashSet<>();
            System.out.println("Please enter the names of the articles (or enter '#' to finish):");
            while (true){
                //FIX:Used the "add" of Hashset in order to add the names of the articels
                String currentArticle = getName();
                if(currentArticle.equals("#")) break;
                ArticlesNames.add(currentArticle);
                
                System.out.println("Please enter the names of the articles (or enter '#' to finish):");
                
            }
            if(dgreeType2 == DgreeNames.PROFESSOR){
                System.out.println("Please enter who gave you your title");
                String title = getName();
                return new Professor(name,ID,dgreeType2,degreeName,salary,department,ID,ArticlesNames,title);
            }
            return new Doctor(name,ID,dgreeType2,degreeName,salary,department,ArticlesNames);
        }
        return new Lecturer(name,ID,dgreeType2,degreeName,salary,department);
    }

//FIX:Changing the Data Structure from ArrayList to HashSet
    public static void RemoveFromCommittee(Lecturer lecturer, HashSet <Lecturer> lecturersINCommittee, Committee committee){
        if (lecturer == null || committee == null) {
            return;
        }

        if (lecturersINCommittee != null) {
            lecturersINCommittee.remove(lecturer);
        }

        if (lecturer.getInCommittee() != null) {
            lecturer.getInCommittee().remove(committee);
        }
    }

    //FIX:Changing the Data Structure from ArrayList to HashSet
    public void InsertLecturerIntoCommittee(Lecturer selctedlecturer, HashSet<Committee> committees, Committee selctedCommittee) throws ObjectExists, LecturerCommitteeException {
        if (!selctedlecturer.getDegreeType().equals(selctedCommittee.getTypeOfDegree())) {
            throw new LecturerCommitteeException("Lecturer degree is not the same type as of committee");
        }

        for (Committee com : selctedlecturer.getInCommittee()) {
            if (com != null && com.equals(selctedCommittee)) {
                throw new ObjectExists("Lecturer already in committee");
            }
        }

        for (Committee c : committees) {
            if (selctedCommittee.equals(c)) {
                c.getLecturers().add(selctedlecturer);
                break;
            }
        }

        for (Lecturer lec : this.lecturers) {
            if (selctedlecturer.equals(lec)) {
                lec.getInCommittee().add(selctedCommittee);
                break;
            }
        }
    }

    // FIX: Hashset Into ArrayList
    public static void InsertLecturerIntoDepartment(Lecturer lecturer, HashSet<Department> departments, String department) {
        if (departments == null || departments.isEmpty()) {
            System.out.println("There are no departments as of now");
            return;
        }

        if (lecturer.getDepartment().isEmpty()) {
            lecturer.setDepartment(department);
            for (Department d : departments) {
                if (d != null && d.getName().equals(lecturer.getDepartment())) {
                    d.getLecturers().add(lecturer);
                    return;
                }
            }
        } else {
            //FIX:Foreach loop in order to handle the Hashset
            for(Department d : departments){
                if(d.getName().equals(lecturer.getDepartment())){
                    d.getLecturers().add(lecturer);
                    return;
                }
            }
        }
    }

    public void isHeadCommitteeDr(String LecturerName,String committeeName) throws LecturerCommitteeException {
        try {
            Lecturer headOfCommittee= findByName(this.lecturers, LecturerName);
            // Checking if the selected lecturer is a dr/professor or not
            if(headOfCommittee.getDegreeType()==DgreeNames.DOCTOR || headOfCommittee.getDegreeType()==DgreeNames.PROFESSOR){
                return;
            }
            throw new LecturerCommitteeException("The lecturer you requested is not in line with the terms");

        }catch(ObjectNotFoundException e){
            System.err.println(e.getMessage());
        }
    }

    // remove function of "IndexOfFirstNull"- because we work with Hash Set.


    // in case there is something that needs to throw that something does exist already
    public static <T extends Nameable> void doesExistByName(HashSet<T> details, String name) throws ObjectExists{
        try {
            findByName(details, name);
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
