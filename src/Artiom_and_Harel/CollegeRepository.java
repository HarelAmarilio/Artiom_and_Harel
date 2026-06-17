package Artiom_and_Harel;

public interface CollegeRepository {
    CollegeManager loadData() throws Exception;
    void saveData(CollegeManager manager) throws Exception;
}