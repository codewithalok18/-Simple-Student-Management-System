package org.newexample;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManager manager = new StudentManager();
        int choice;

        do {
            System.out.println("\n--- Student Management Menu ---");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Sort by Name");
            System.out.println("7. Sort by Marks");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("ID: ");
                    int id = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Marks: ");
                    double marks = sc.nextDouble();
                    manager.addStudent(new Student(id, name, marks));
                    break;
                case 2:
                    manager.viewStudents();
                    break;
                case 3:
                    System.out.print("Enter ID to search: ");
                    int sid = sc.nextInt();
                    Student found = manager.searchById(sid);
                    System.out.println(found != null ? found : "Student not found.");
                    break;
                case 4:
                    System.out.print("Enter ID to update: ");
                    int uid = sc.nextInt();
                    sc.nextLine();
                    System.out.print("New Name: ");
                    String newName = sc.nextLine();
                    System.out.print("New Marks: ");
                    double newMarks = sc.nextDouble();
                    boolean updated = manager.updateStudent(uid, newName, newMarks);
                    System.out.println(updated ? "Updated successfully." : "Student not found.");
                    break;
                case 5:
                    System.out.print("Enter ID to delete: ");
                    int did = sc.nextInt();
                    boolean deleted = manager.deleteStudent(did);
                    System.out.println(deleted ? "Deleted successfully." : "Student not found.");
                    break;
                case 6:
                    manager.sortByName();
                    System.out.println("Sorted by name.");
                    break;
                case 7:
                    manager.sortByMarks();
                    System.out.println("Sorted by marks.");
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 0);
    }
}
