package Artiom_and_Harel;

import java.util.Arrays;
import java.util.Objects;

public class Doctor extends Lecturer{
    private String [] articles=new String[1];
    private int numberOfArticles; //The number of articles the Doctor has
    public Doctor(String fullName, int ID, DgreeNames degreeType, String degreeName, double salary, String department,String [] articles) {
        super(fullName, ID, degreeType, degreeName, salary, department);
        this.articles = articles;
        this.numberOfArticles=0;
    }

    public String[] getArticles() {
        return articles;
    }

    public int getNumberOfArticles() {
        return numberOfArticles;
    }

    public void setArticles(String[] articles) {
        this.articles = articles;
    }

    public void setNumberOfArticles(int numberOfArticles) {
        this.numberOfArticles = numberOfArticles;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Doctor doctor = (Doctor) o;
        return numberOfArticles == doctor.numberOfArticles && Objects.deepEquals(articles, doctor.articles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), Arrays.hashCode(articles), numberOfArticles);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "articles=" + Arrays.toString(articles) +
                '}';
    }
}
