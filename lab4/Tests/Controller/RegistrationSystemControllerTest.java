package Tests.Controller;

import com.company.Model.Course;
import com.company.Model.Student;
import com.company.Model.Teacher;
import com.company.Controller.RegistrationSystemController;
import com.company.Repository.CourseRepository;
import com.company.Repository.StudentRepository;
import com.company.Repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class RegistrationSystemControllerTest {
    RegistrationSystemController registrationSystem;
    StudentRepository studentRepo;
    CourseRepository courseRepo;
    TeacherRepository teacherRepo;
    Course MAP,OOP,SDA;
    Teacher teacher1,teacher2;
    Student ana,maria,bianca;


    /**
     * Initialization of data for test methods
     */
    @BeforeEach
    void init() throws FileNotFoundException {
        ArrayList<Course>courseRepoList=new ArrayList<>();
        courseRepo=new CourseRepository(courseRepoList);

        ArrayList<Student>studentRepoList=new ArrayList<>();
        studentRepo=new StudentRepository(studentRepoList);

        ArrayList<Teacher>teacherRepoList=new ArrayList<>();
        teacherRepo=new TeacherRepository(teacherRepoList);

        registrationSystem=new RegistrationSystemController(studentRepo,teacherRepo,courseRepo);


        ArrayList<Student> studentListOOP = new ArrayList<>();
        ArrayList<Student> studentListMAP = new ArrayList<>();

        ArrayList<Course> courseListteacher2 = new ArrayList<>();
        ArrayList<Course> courseListteacher1 = new ArrayList<>();

        ArrayList<Course> courseListana=new ArrayList<>();
        ArrayList<Course> courseListmaria=new ArrayList<>();
        ArrayList<Course> courseListUpdatedStud=new ArrayList<>();

        teacher1 = new Teacher("teacher1", "lastName1", 400L, courseListteacher1);
        teacher2 = new Teacher("teacher2", "lastName2", 401L, courseListteacher2);

        OOP = new Course("OOP", 1, teacher2, 3, studentListOOP, 100L);
        MAP = new Course("MAP", 2, teacher1, 5, studentListMAP, 101L);
        SDA = new Course("SDA ", 2, teacher1, 5, studentListMAP, 102L);

        ana= new Student("ana","blidar",500L,5,courseListana);
        maria= new Student("maria","pop",501L,6,courseListmaria);
        bianca= new Student("bianca","oanta",502L,7,courseListUpdatedStud);
        courseListteacher1.add(OOP);
        courseListteacher2.add(MAP);

        courseRepo.save(OOP);
        courseRepo.save(MAP);
        courseRepo.save(SDA);

    }

    /**
     * testeaza metoda de inregistrare a unui student la un curs
     */

    @Test
    void register() {
        int initialCreditNumber = maria.getTotalCredits();
        registrationSystem.register(maria,OOP);
        assertEquals(initialCreditNumber+OOP.getCredits(),maria.getTotalCredits());
        assertEquals(maria,OOP.findOne(maria.getID()));
    }

    /**
     * testeaza metoda retrieveCoursesWithFreePlaces
     */
    @Test
    void retrieveCoursesWithFreePlaces() {

        registrationSystem.register(maria,OOP);
        registrationSystem.register(ana,MAP);
        registrationSystem.register(bianca,SDA);

        ArrayList<Course>listCourseWithFreePlaces=registrationSystem.retrieveCoursesWithFreePlaces();
        assertEquals(listCourseWithFreePlaces.size(),3);

    }

    /**
     * testeaza metoda retrieveStudentsEnrolledForACourse
     */
    @Test
    void retrieveStudentsEnrolledForACourse() {
        registrationSystem.register(maria,OOP);
        registrationSystem.register(ana,OOP);
        registrationSystem.register(bianca,SDA);

        ArrayList<Student>listStudents=registrationSystem.retrieveStudentsEnrolledForACourse(OOP);
        assertEquals(2,listStudents.size());
        List<Student>studentList=registrationSystem.retrieveStudentsEnrolledForACourse(SDA);
        assertEquals(1,studentList.size());

    }

    /**
     * tests the deleteCourseByTeacher metode
     */
    @Test
    void deleteCourseByTeacher() {

        registrationSystem.register(maria,OOP);
        registrationSystem.register(ana,OOP);
        registrationSystem.register(bianca,SDA);
        assertEquals(ana.getTotalCredits(),6);
        assertEquals(maria.getTotalCredits(),7);
        System.out.println(teacher2.getCourses().size());
        ArrayList<Student>studentList=registrationSystem.retrieveStudentsEnrolledForACourse(OOP);
        Course course= registrationSystem.deleteCourseByTeacher(OOP,teacher2,studentList);
        System.out.println(teacher2.getCourses().size());

        assertEquals(ana.getTotalCredits(),6);
        assertEquals(maria.getTotalCredits(),7);

    }
}