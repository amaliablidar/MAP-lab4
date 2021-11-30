package com.company.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class Student extends Person {
    private int totalCredits;
    private List<Course>enrolledCourses;


    public Student (String firstname,String name ,Long studentID,int totalCredits,List<Course>enrolledCourses)
    {
        super.setLastName(name);
        super.setFirstName(firstname);
        super.setID(studentID);
        this.totalCredits=totalCredits;
        this.enrolledCourses=enrolledCourses;
    }

    public Student (){};

    public void addCourse(Course newCourse)
    {
        enrolledCourses.add(newCourse);
        int newTotalCredits=totalCredits+newCourse.getCredits();
        setTotalCredits(newTotalCredits);
    }

    public void delete(Course course)
    {
        List<Course>newCourseList=new ArrayList<>();
        for (Course course1:enrolledCourses)
        {
            if (!course.getId().equals(course1.getId()))
            {
                newCourseList.add(course1);
            }
        }
        setEnrolledCourses(newCourseList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(getID(), student.getID());
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalCredits, enrolledCourses);
    }

    public int getTotalCredits() {
        return totalCredits;
    }

    public List<Course> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(List<Course> enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    public void setTotalCredits(int totalCredits) {
        this.totalCredits = totalCredits;
    }


}