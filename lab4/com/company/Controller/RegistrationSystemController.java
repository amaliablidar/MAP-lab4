package com.company.Controller;

import com.company.Model.Course;
import com.company.Model.Student;
import com.company.Model.Teacher;
import com.company.Repository.CourseRepository;
import com.company.Repository.StudentRepository;
import com.company.Repository.TeacherRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RegistrationSystemController
{
    private StudentRepository studentRepository;
    private  TeacherRepository teacherRepository;
    private  CourseRepository courseRepository;

    public RegistrationSystemController(StudentRepository studentRepository, TeacherRepository teacherRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.courseRepository = courseRepository;
    }

    /**
     * @return a student repository
     */
    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    /**
     * @return a teacher repository
     */
    public TeacherRepository getTeacherRepository() {
        return teacherRepository;
    }

    /**
     * @return a course repository
     */
    public CourseRepository getCourseRepository() {
        return courseRepository;
    }

    /**
     * inregistreaza un student la un curs si pune in lista studentului cursul
     * @param student- studentul ce va fi inregistrat la curs
     * @param course-cursul la care va fi inregistrat studentul
     * @return true- daca studentul a fost inregistrat cu succes sau fals in caz contrar
     **/
    public boolean register(Student student,Course course)
    {

        if((course.getMaxStudents()-course.getStudentsList().size())>0){
            if(student.getTotalCredits() + course.getCredits() <= 30) {
                course.save(student);
                student.addCourse(course);
                return true;
            }
        }

        return false;
    }

    /**
     * @return lista de cursuri
     */
    public ArrayList<Course> getAllCourses()
    {
        return  courseRepository.findAll();
    }


    /**
     * @return lista cursurilor cu locuri disponibile
     */
    public ArrayList<Course> retrieveCoursesWithFreePlaces()
    {
        ArrayList<Course>coursesWithFreePlaces=new ArrayList<>();
        for(Course course:getAllCourses())
        {
            if(course.getMaxStudents()-course.getStudentsList().size()>0)
            {
                coursesWithFreePlaces.add(course);
            }

        }
        return coursesWithFreePlaces;

    }

    /**
     *
     * @return lista studentilor de la un curs
     */
    public ArrayList<Student> retrieveStudentsEnrolledForACourse(Course course)
    {

        return course.getStudentsList();

    }

    /**
     * metoda sterge un curs, acesta poate fi sters doar de un profesor
     * @return cursul daca acesta a fost sters si null in caz contrar
     */
    public Course deleteCourseByTeacher(Course course, Teacher teacher,List<Student> studentsEnrolledForACourse)
    {
        List<Course>teachersCourses=teacher.getCourses();
        for (int i=0;i<teachersCourses.size();i++)
        {

            if (teachersCourses.get(i).getId().equals(course.getId()))
            {
                courseRepository.delete(course.getId());

                for (Student student:studentsEnrolledForACourse)
                {
                    student.setTotalCredits(student.getTotalCredits() - course.getCredits());
                    student.delete(course);

                }
                course.getStudentsList().clear();
                teachersCourses.remove(i);
                return course;
            }

        }
        return null;

    }
    /**
    * metoda sorteaza studentii alfabetic
    * @return lista sortata
    **/
        public ArrayList<Student> studentsSortedAlphabetically(ArrayList<Student>students)
    {
        ArrayList<Student> newList = new ArrayList<>(students);
        newList.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getLastName().compareTo(o2.getLastName());
            }
        });
        return newList;
    }
    
    /**
     * 
     * @param courses lista de cursuri ce trebuie sortata alfabetic
     * @return lista sortata
     */
    public ArrayList<Course> CoursesSortedAlphabetically(ArrayList<Course> courses)
    {
        ArrayList<Course> newList = new ArrayList<>(courses);
        newList.sort(new Comparator<Course>() {
            @Override
            public int compare(Course o1, Course o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });

        return newList;
    }

    /**
     * 
     * @param credits numarul de credite dupa care sunt filtrati studentii
     * @param studentList lista studentilor ce trebuie filtrati
     * @return lista studentilor dupa filtrare
     */
       public ArrayList<Student> studentsFilteredByCredits(int credits,ArrayList<Student>students)
    {
        ArrayList<Student> newList=new ArrayList<>();
        for (Student student:students)
        {
            if (student.getTotalCredits()==credits)
                newList.add(student);
        }
        return newList;
    }
    /**
     * 
     * @param credits numarul de credite dupa care trebuie filtrata lista de cursuri
     * @return lista de cursuri filtrate dupa credite
     */
    public List<Course> coursesFilteredByCredits(int credits, ArrayList<Course> courses)
    {
        ArrayList<Course> newList=new ArrayList<>();
        for (Course course:courses)
        {
            if (course.getCredits()==credits)
                newList.add(course);
        }
        return newList;
    }

}
