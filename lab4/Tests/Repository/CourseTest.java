package Tests.Repository;

import com.company.Model.Course;
import com.company.Model.Student;
import com.company.Model.Teacher;
import com.company.Repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {
    CourseRepository courses;
    Course MAP,OOP,SDA;
    Teacher teacher1,teacher2;

    /**
     * Initializarea datelor
     */
    @BeforeEach
    void init() throws FileNotFoundException {


        ArrayList<Course> course= new ArrayList<>();
        courses= new CourseRepository(course);

        ArrayList<Student> studentListOOP = new ArrayList<>();
        ArrayList<Student> studentListMAP = new ArrayList<>();

        ArrayList<Course> courseListteacher2 = new ArrayList<>();
        ArrayList<Course> courseListteacher1 = new ArrayList<>();

        teacher1 = new Teacher("teacher1", "lastName1", 300L, courseListteacher1);
        teacher2 = new Teacher("teacher2", "lastName2", 301L, courseListteacher2);

        OOP = new Course("OOP", 1, teacher2, 3, studentListOOP, 400L);
        MAP = new Course("MAP", 2, teacher1, 5, studentListMAP, 401L);
        SDA = new Course("SDA ", 2, teacher1, 5, studentListMAP, 402L);
    }


    /**
     * testeaza metoda de save 
     */
    @Test
    void save() {

        assertEquals(0,courses.findAll().size());

        courses.save(OOP);
        courses.save(SDA);
        assertEquals(2,courses.findAll().size());

    }

    /**
     * testeaza metoda de findOne
     */
    @Test
    void findOne() {
        courses.save(OOP);

        Long id=OOP.getId();
        Course findCourse= courses.findOne(id);
        assertEquals(OOP,findCourse);
    }
    /**
     * testeaza metoda de findAll
     */
    @Test
    void findAll() {

        courses.save(OOP);

        assertEquals(1,courses.findAll().size());


    }
    /**
     * testeaza metoda de delete
     */
    @Test
    void delete() {

        courses.save(OOP);
        courses.save(MAP);

        assertEquals(2,courses.findAll().size());

        courses.delete(MAP.getId());

        assertEquals(1,courses.findAll().size());

    }
    /**
     * testeaza metoda de update
     */
    @Test
    void update() {

        courses.save(MAP);
        ArrayList<Student> updatedList = new ArrayList<>(0);
        Course courseUpdated = new Course("MAP",3,teacher2,5,updatedList,401L);
        courses.update(courseUpdated);
        Course courseUpd = courses.findOne(courseUpdated.getId());
        assertEquals(3, courseUpd.getCredits());


    }
}