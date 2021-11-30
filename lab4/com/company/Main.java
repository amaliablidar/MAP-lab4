package com.company;

import com.company.Controller.RegistrationSystemController;
import com.company.Model.Course;
import com.company.Model.Student;
import com.company.Model.Teacher;
import com.company.Repository.CourseRepository;
import com.company.Repository.StudentRepository;
import com.company.Repository.TeacherRepository;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Course> courseList=new ArrayList<>();
        ArrayList<Student> students=new ArrayList<>();
        ArrayList<Teacher> teacher=new ArrayList<>();
        TeacherRepository teacherRepository = new TeacherRepository(teacher);
        StudentRepository studentRepository = new StudentRepository(students);
        CourseRepository courseRepository = new CourseRepository(courseList);


        RegistrationSystemController controller = new RegistrationSystemController(studentRepository,teacherRepository,courseRepository);

    }
}
