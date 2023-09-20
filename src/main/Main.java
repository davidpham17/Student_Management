
package main;

import controller.StudentManager;
import model.Student;


public class Main {

    public static void main(String[] args) {
        Student st = new Student();
        
        new StudentManager(st).run();
    }
    
}
