package org.newexample;

import java.io.*;
import java.util.*;

public class StudentManager {
    private List<Student> students;
    private final String FILE_NAME = "students.dat";

    public StudentManager() {
        students = loadFromFile();
    }

    public void addStudent(Student s) {
        students.add(s);
        saveToFile();
    }

    public void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records found.");
        } else {
            students.forEach(System.out::println);
        }
    }

    public Student searchById(int id) {
        return students.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
    }

    public boolean updateStudent(int id, String name, double marks) {
        Student s = searchById(id);
        if (s != null) {
            s.setName(name);
            s.setMarks(marks);
            saveToFile();
            return true;
        }
        return false;
    }

    public boolean deleteStudent(int id) {
        Student s = searchById(id);
        if (s != null) {
            students.remove(s);
            saveToFile();
            return true;
        }
        return false;
    }

    public void sortByName() {
        students.sort(Comparator.comparing(Student::getName));
    }

    public void sortByMarks() {
        students.sort(Comparator.comparingDouble(Student::getMarks).reversed());
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Student> loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}
