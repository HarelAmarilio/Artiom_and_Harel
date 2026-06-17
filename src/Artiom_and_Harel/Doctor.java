package Artiom_and_Harel;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;

//FIX: deleted the num of articles
public class Doctor extends Lecturer implements ArticlePublisher, Comparable<Doctor>,Serializable{
    protected HashSet <String> articles;
    public Doctor(String fullName, int ID, DgreeNames degreeType, String degreeName, double salary, String department,HashSet <String> articles) {
        super(fullName, ID, degreeType, degreeName, salary, department);
        this.articles=articles;
    }
    public int compareTo(Doctor l1){
        return Integer.compare(this.articles.size(), l1.getNumberOfArticles());
    }
    public HashSet<String> getArticles() {
        return articles;
    }
@Override
    public int getNumberOfArticles() {
        return this.articles.size();
    }

    public void setArticles(HashSet <String> articles) {
        this.articles = articles;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Doctor doctor = (Doctor) o;
        return this.articles.size() == doctor.articles.size() && Objects.deepEquals(articles, doctor.articles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getArticles(), getNumberOfArticles());
    }

    @Override
    public String toString() {
        return super.toString() +
                ", articles=" + articles +
                ", numberOfArticles=" + this.articles.size() +
                '}';
    }
}
