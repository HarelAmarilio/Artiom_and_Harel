package Artiom_and_Harel;

import java.util.Objects;

public class Professor extends Doctor {
    private String title;
    public Professor(String fullName, int ID, DgreeNames degreeType, String degreeName, double salary, String department,int numberOfArticle,String [] articles, String title) {
        super(fullName,ID,degreeType, degreeName, salary,department,numberOfArticle,articles);
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Professor professor = (Professor) o;
        return Objects.equals(title, professor.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title);
    }

    @Override
    public String toString() {
        return super.toString()+
                ", title='" + title + '\'';
    }
}
