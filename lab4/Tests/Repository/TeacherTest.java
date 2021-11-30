package Tests.Repository;

import com.company.Model.Course;
import com.company.Model.Student;
import com.company.Model.Teacher;
import com.company.Repository.StudentRepository;
import com.company.Repository.TeacherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeacherTest {
    TeacherRepository teachers;
    Course MAP,OOP,SDA;
    Teacher teacher1,teacher2;
    Student ana,maria,bianca;

    /**
     * initializarea datelor
     */
    @BeforeEach
    void setUp() throws FileNotFoundException {


        ArrayList<Teacher>teacher=new ArrayList<>();
        teachers=new TeacherRepository(teacher);

        ArrayList<Student> studentListOOP = new ArrayList<>();
        ArrayList<Student> studentListMAP = new ArrayList<>();

        ArrayList<Course> courseListteacher2 = new ArrayList<>();
        ArrayList<Course> courseListteacher1 = new ArrayList<>();

        List<Course>courseListana=new ArrayList<>();
        List<Course>courseListmaria=new ArrayList<>();
        List<Course>courseListUpdatedStud=new ArrayList<>();


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
     * Testeaza metoda de save
     */
    @Test
    void save() {
        assertEquals(0, teachers.findAll().size());
        teachers.save(teacher1);
        assertEquals(1, teachers.findAll().size());

    }
    /**
     * Testeaza metoda de findOne
     */
    @Test
    void findOne() {

        teachers.save(teacher1);
        Long id=teacher1.getID();

        assertEquals(teacher1,teachers.findOne(id));
    }
    /**
     * Testeaza metoda de findAll
     */
    @Test
    void findAll() {
        teachers.save(teacher1);
        teachers.save(teacher2);

        assertEquals(2, teachers.findAll().size());

    }


    /**
     * testeaza metoda delete
     */
    @Test
    void delete() {

        teachers.save(teacher1);
        assertEquals(1,teachers.findAll().size());

        teachers.delete(teacher1.getID());
        assertEquals(0,teachers.findAll().size());

    }
    /**
     * testeaza metoda update
     */
    @Test
    void update() {

        teachers.save(teacher1);
        ArrayList<Course> updatedList = new ArrayList<>(0);
        Teacher teacherUpdate = new Teacher("teacher11", "lastName11", 400L, updatedList);
        teachers.update(teacherUpdate);
        Teacher updTeacher =  teachers.findOne(teacherUpdate.getID());

        assertEquals("teacher11", updTeacher.getFirstName());
    }
}
