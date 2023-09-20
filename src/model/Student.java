package model;

public class Student implements Comparable<Student>{
    private String id, name, semester, courseName;

    public Student() {
    }

    public Student(String id, String name, String semester, String courseName) {
        this.id = id;
        this.name = name;
        this.semester = semester;
        this.courseName = courseName;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSemester() {
        return semester;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name=" + name + ", semester=" + semester + ", courseName=" + courseName + "}";
    }

    @Override
    public int compareTo(Student o) {
        int d = this.name.compareTo(o.getName());
        if (d>0) return 1;
        else if (d<0) return -1;
        else return 0;     
    }
    
    
}
