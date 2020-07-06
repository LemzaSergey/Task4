package com.company;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class InputArray {
    private Errors errors;

    public InputArray(String version) {
        this.errors = new Errors(version);
    }


    public int checkInt(String line) throws NumberFormatException {
        try {
            return Integer.parseInt(line);
        } catch (NumberFormatException e) {
            line = errors.checkIntError(line);
            return Integer.parseInt(line);
        }
    }

    public String[][] toTwoDimensionalArrayString(String[] array) {
        String[][] array2 = new String[array.length][];
        for (int i = 0; i < array.length; i++) {
            array2[i] = array[i].split("\\s+");
        }
        return array2;
    }

    public List<List<String>> toTwoDimensionalListString(String[] array) {

        String[][] twoDimensionalArray = toTwoDimensionalArrayString(array);
        return ArrayToList(twoDimensionalArray);
    }


    public List<List<String>> ArrayToList(String[][] array) {
        List<String> lineList = new ArrayList<>();
        List<List<String>> mainList = new ArrayList<>();
        for (String[] lineArray : array) {
            for (String s : lineArray) {
                lineList.add(s);
            }
            mainList.add(lineList);
            lineList = new ArrayList<>();
        }
        return mainList;
    }


    public static String[][] converterListStringToArrayString(List<List<String>> lists) {
        String[][] stringsArray = new String[lists.size()][4];
        for (int i = 0; i < lists.size(); i++) {
            for (int j = 0; j < lists.get(i).size(); j++) {
                if (Errors.workingListStringRedundant(lists, i)) {
                    return new String[0][0];
                }
                stringsArray[i][j] = lists.get(i).get(j);
            }

        }

        return stringsArray;
    }


    public Student converterStringToStudent(List<String> stringList) {

        int courseNumber;
        int groupNumber;
        String surname;
        String name;

        courseNumber = checkInt(stringList.get(0));
        groupNumber = checkInt(stringList.get(1));
        surname = stringList.get(2);
        name = stringList.get(3);

        return new Student(courseNumber, groupNumber, surname, name);
    }

    public List<Student> converterListStringToListStudent(List<List<String>> lists) {
        List<Student> studentList = new ArrayList<>();

        for (List<String> strings : lists) {
            studentList.add(converterStringToStudent(strings));
        }
        return studentList;
    }


    public List<String> converterStudentToString(Student students) {

        List<String> stringList = new ArrayList<>();

        stringList.add(Integer.toString(students.getCourseNumber()));
        stringList.add(Integer.toString(students.getGroupNumber()));
        stringList.add(students.getSurname());
        stringList.add(students.getName());

        return stringList;
    }

    public List<List<String>> converterListStudentToListString(List<Student> listStudent) {

        List<List<String>> listString = new ArrayList<>();

        for (Student students : listStudent) {
            listString.add(converterStudentToString(students));

        }
        return listString;
    }


}