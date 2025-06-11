package Artiom_and_Harel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Doctor extends Lecturer implements Comparable<Doctor>,Serializable{
    protected ArrayList <String> articles;
    protected int numberOfArticles; //The number of articles the Doctor has
    public Doctor(String fullName, int ID, DgreeNames degreeType, String degreeName, double salary, String department,int numberOfArticle,ArrayList <String> articles) {
        super(fullName, ID, degreeType, degreeName, salary, department);
        this.numberOfArticles=numberOfArticle;
        this.articles=articles;
    }
    public int compareTo(Doctor l1){
        return Integer.compare(this.numberOfArticles, l1.getNumberOfArticles());
    }
    public ArrayList<String> getArticles() {
        return articles;
    }

    public int getNumberOfArticles() {
        return numberOfArticles;
    }

    public void setArticles(ArrayList <String> articles) {
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
        return Objects.hash(super.hashCode(), getArticles(), getNumberOfArticles());
    }

    @Override
    public String toString() {
        return super.toString() +
                ", articles=" + articles +
                ", numberOfArticles=" + numberOfArticles +
                '}';
    }
}
