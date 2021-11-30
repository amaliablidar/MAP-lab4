package com.company.Model;

import java.util.List;
import java.util.Objects;


public class Teacher extends Person {
    private List<Course>courses;

    public Teacher (String firstName,String lastName,Long teacherID,List<Course>courses)
    {
        super.setFirstName(firstName);
        super.setLastName(lastName);
        super.setID(teacherID);
        this.courses=courses;
    }
    public Teacher (){};

    public List<Course> getCourses() {
        return courses;
    }


    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(super.getID(), teacher.getID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(courses);
    }


}