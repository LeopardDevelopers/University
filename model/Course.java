package sample.model;

public class Course {
    private String name;
    private String comment;

    public Course(String name, String comment) {
        this.name = name;
        this.comment = comment;
    }

    public Course(){
        this("", "");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
