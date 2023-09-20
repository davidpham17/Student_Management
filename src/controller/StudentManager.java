package controller;

import common.Library;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import model.Report;
import model.Student;
import view.Menu;

public class StudentManager extends Menu<String>{
    static String[] mc = {"Create", "Find and Sort", "Update/Delete", "Report" ,"Exit"};
    ArrayList<Student> students = new ArrayList<>();
    
    protected Library library;
    
    public StudentManager(Student student) {
        super("WELCOME TO STUDENT MANAGEMENT", mc);
        library = new Library();
    }

    public void createStudent(ArrayList<Student> students) {
        while (true) {
            System.out.println("Enter ID:");
            String id = library.inputString().trim();
            System.out.println("Enter name:");
            String name = library.inputString().trim();
            System.out.println("Enter semester:");
            String semester = library.inputString().trim();
            System.out.println("Enter course name:");
            String courseName = library.inputCourse().trim();
            Student st = new Student(id, name, semester, courseName);
            if (library.checkIdExist(students, id)) {
                students.add(st);
            System.err.println("Create success!");
            }
            
            if (students.size() >= 10){
                System.out.print("Do you want to create more students (Y/N): ");
            if (!library.inputYesNo()) {
                return;
            }
            }
        }
    }
    
    public ArrayList<Student> searchStudentByName(ArrayList<Student> students) {
        System.out.println("Enter student name:");
        String nameSearch = library.inputString();
        int count = 0;
        ArrayList<Student> foundStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().equals(nameSearch)) {               
                count++;
                foundStudents.add(student);
            }
        }
        if (foundStudents.isEmpty()) {
            System.out.println("Not Found!");
        }
        return foundStudents;
    }
    
    public void findAndSort(ArrayList<Student> students) {
        ArrayList<Student> foundStudents = searchStudentByName(students);
        if (foundStudents.isEmpty()){
            return;
        }
        System.out.println("Found Students (sorted by name):");
            for (Student student : foundStudents) {
                System.out.println(student.toString());
            }
    }
    
    public  void sortByName(ArrayList<Student> students){
        Collections.sort(students, new Comparator<Student>() {
            @Override
            public int compare(Student st1, Student st2) {
                return st1.getName().compareTo(st2.getName());
            }
        });
        System.out.println("Sorted by name successfully!");
    }
    
    public void printListStudent(ArrayList<Student> students){
        if (students == null) return; 
        if(students.isEmpty()) System.out.println("Empty list!");
        else {
            System.out.println("List of student");
            System.out.println("--------------------------------------------------------------------------");
            for (Student st : students) {
                System.out.println(st);
            }
            System.out.println("Total: " + students.size() + " students.");
            System.out.println("--------------------------------------------------------------------------");
            }
    }
    
    public Student searchStudentByID(ArrayList<Student> students) {
        System.out.println("Enter student ID:");
        String idSearch = library.inputString();
        int count = 0;
        for (Student student : students) {
            if (student.getId().equals(idSearch)) {               
                count++;
                return student;
            }
        }
        if (count == 0) {
            System.out.println("Not Found!");
        }
        return null;
    }
    
    public void updateStudent(ArrayList<Student> students){
        Student updateStudent = searchStudentByID(students);
        if (updateStudent == null) return;
        else {            
        System.out.println("Enter student name:");
        String updateName = library.inputString();
        System.out.println("Enter semester:");
        String updateSemester = library.inputString(); 
        System.out.println("Enter course name:");
        String updateCourseName = library.inputCourse();
        if (library.checkChangeInfomation(updateStudent, updateName, updateSemester, updateCourseName)){
            updateStudent.setName(updateName);
            updateStudent.setSemester(updateSemester);
            updateStudent.setSemester(updateCourseName);
            System.out.println("Update successful!");
            printListStudent(students);
        }
        else {
            System.out.println("Nothing change!");
        }
        }
    }
    
    public void deleteStudent(ArrayList<Student> students){
        Student deleteStudent = searchStudentByID(students);
        if (deleteStudent == null) return;
        else {
            students.remove(deleteStudent);
            System.out.println("Delete student successfully!");
            printListStudent(students);
        }
    }
    
    public void updateAndDelete(){
        System.out.print("Do you want to update (U) or delete (D) student:");
        if (library.inputUpdateDelete()) {
            updateStudent(students);
        }
        else {
            deleteStudent(students);
        }
    }
    
    public void report(ArrayList<Student> students){
        if (students.isEmpty()) return;
        else {
            ArrayList<Report> reports = new ArrayList<>();
            for (int i = 0; i < students.size(); i++) {
            int total = 0;
                for (Student student : students) {
                    String id = student.getId();
                    String courseName = student.getCourseName();
                    String studentName = student.getName();
                    for (Student studentCountTotal : students) {
                        if (id.equalsIgnoreCase(studentCountTotal.getId())
                            && courseName.equalsIgnoreCase(studentCountTotal.getCourseName())) {
                            total++;
                    }
                }
                if (library.checkReportExist(reports, studentName,courseName, total)) {
                    reports.add(new Report(student.getName(), courseName, total));
                    }
                }
            }
            for (Report report : reports) {
                System.out.println(report.toString());
            }
    }
    }

    @Override
    public void execute(int n) {
        switch (n) {
            case 1:
                createStudent(students);
                break;
            case 2:
                findAndSort(students);
                break;
            case 3:
                updateAndDelete();
                break;
            case 4:
                report(students);
                break;
            case 5:
                System.out.println("Exit the program successfully!");
                System.exit(0);
                break;
        }
    }   
    

}