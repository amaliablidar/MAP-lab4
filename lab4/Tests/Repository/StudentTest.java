package Tests.Repository;

import com.company.Model.Course;
import com.company.Model.Student;
import com.company.Model.Teacher;
import com.company.Repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class StudentTest {
    StudentRepository students;
    Course MAP,OOP,SDA;
    Teacher teacher1,teacher2;
    Student ana,maria,bianca;


    /**
     * initializarea datelor
     */
    @BeforeEach
    void init() throws FileNotFoundException {
        ArrayList<Student>studentsList=new ArrayList<>();
        students=new StudentRepository(studentsList);

        ArrayList<Student> studentListOOP = new ArrayList<>();
        ArrayList<Student> studentListMAP = new ArrayList<>();

        ArrayList<Course> courseListteacher2 = new ArrayList<>();
        ArrayList<Course> courseListteacher1 = new ArrayList<>();

        ArrayList<Course>courseListana=new ArrayList<>();
        ArrayList<Course>courseListmaria=new ArrayList<>();
        ArrayList<Course>courseListUpdatedStud=new ArrayList<>();

        teacher1 = new Teacher("teacher1", "lastName1", 400L, courseListteacher1);
        teacher2 = new Teacher("teacher2", "lastName2", 401L, courseListteacher2);

        OOP = new Course("OOP", 1, teacher2, 3, studentListOOP, 100L);
        MAP = new Course("MAP", 2, teacher1, 5, studentListMAP, 101L);
        SDA = new Course("SDA ", 2, teacher1, 5, studentListMAP, 102L);

        ana= new Student("ana","blidar",500L,5,courseListana);
        maria= new Student("maria","pop",501L,6,courseListmaria);
        bianca= new Student("bianca","oanta",502L,7,courseListUpdatedStud);

    }
    /**
     * testeaza metoda save
     */
    @Test
    void save() {
        assertEquals(0, students.findAll().size());
        students.save(maria);
        assertEquals(1, students.findAll().size());


    }
    /**
     * testeaza metoda findOne
     */
    @Test
    void findOne() {

        students.save(ana);
        Long id=ana.getID();

        assertEquals(ana,students.findOne(id));

    }
    /**
     * testeaza metoda findAll 
     */
    @Test
    void findAll() {
        students.save(ana);
        students.save(maria);

        assertEquals(2, students.findAll().size());

    }

    /**
     * testeaza metoda delete 
     */
    @Test
    void delete() {

        students.save(ana);
        assertEquals(1,students.findAll().size());

        students.delete(ana.getID());
        assertEquals(0,students.findAll().size());

    }
    /**
     * testeaza metoda update
     */
    @Test
    void update() {

        students.save(ana);
        ArrayList<Course> updatedList = new ArrayList<>(0);
        Student updateStudent= new Student("ana","le",500L,7,updatedList);
        students.update(updateStudent);
        Student updStudent =  students.findOne(updateStudent.getID());

        assertEquals(7, updStudent.getTotalCredits());
    }
}
