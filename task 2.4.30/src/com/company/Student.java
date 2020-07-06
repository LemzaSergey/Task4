package com.company;

import java.util.Comparator;

public class Student {

    private int courseNumber;
    private int groupNumber;
    private String surname;
    private String name;

    public Student(int courseNumber, int groupNumber, String surname, String name) {
        this.courseNumber = courseNumber;
        this.groupNumber = groupNumber;
        this.surname = surname;
        this.name = name;

    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }


}

class sortStudentGroup implements Comparator<Student> {

    @Override
    public int compare(Student one, Student two) {

        return one.getGroupNumber() - two.getGroupNumber();
    }

}

class sortStudentCourse implements Comparator<Student> {

    @Override
    public int compare(Student one, Student two) {

        return one.getCourseNumber() - two.getCourseNumber();
    }

}

class sortStudentSurname implements Comparator<Student> {

    @Override
    public int compare(Student one, Student two) {

        return one.getSurname().compareTo(two.getSurname());
    }

}

class sortStudentName implements Comparator<Student> {

    @Override
    public int compare(Student one, Student two) {

        return one.getName().compareTo(two.getName());
    }

}
