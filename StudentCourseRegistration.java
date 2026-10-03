//student course registration

import java.util.ArrayList;
import java.util.Scanner;

class Course {

    String code;
    String title;
    String description;
    int capacity;
    int registered;

    Course(String code, String title, String description, int capacity) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.registered = 0;
    }

    boolean hasSlot() {
        return registered < capacity;
    }

    void registerStudent() {
        registered++;
    }

    void removeStudent() {
        registered--;
    }

    void display() {
        System.out.println("\nCourse Code: " + code);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Available Slots: " + (capacity - registered));
    }
}

class Student {

    int id;
    String name;
    ArrayList<Course> courses = new ArrayList<>();

    Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    boolean isRegistered(Course course) {
        return courses.contains(course);
    }

    void register(Course course) {
        courses.add(course);
        course.registerStudent();
    }

    void remove(Course course) {
        courses.remove(course);
        course.removeStudent();
    }

    void displayCourses() {
        System.out.println("\nCourses registered by " + name + ":");

        if (courses.isEmpty()) {
            System.out.println("No courses registered.");
            return;
        }

        for (Course course : courses) {
            System.out.println(course.code + " - " + course.title);
        }
    }
}

public class StudentCourseRegistration {

    static Scanner sc = new Scanner(System.in);

    static ArrayList<Course> courses = new ArrayList<>();

    static Student student = new Student(101, "Bhavya");

    static void addCourses() {
        courses.add(new Course(
                "CS101",
                "Java Programming",
                "Object Oriented Programming",
                3));

        courses.add(new Course(
                "CS102",
                "Data Structures",
                "Arrays, Linked Lists and Trees",
                2));

        courses.add(new Course(
                "CS103",
                "Database Management",
                "SQL and Database Concepts",
                4));

        courses.add(new Course(
                "CS104",
                "Artificial Intelligence",
                "AI and Machine Learning Basics",
                3));
    }

    static void displayCourses() {
        System.out.println("\n===== AVAILABLE COURSES =====");

        for (Course course : courses) {
            course.display();
        }
    }

    static Course findCourse(String code) {

        for (Course course : courses) {
            if (course.code.equalsIgnoreCase(code)) {
                return course;
            }
        }

        return null;
    }

    static void registerCourse() {

        System.out.print("Enter course code: ");
        String code = sc.next();

        Course course = findCourse(code);

        if (course == null) {
            System.out.println("Course not found.");
        } else if (student.isRegistered(course)) {
            System.out.println("You are already registered for this course.");
        } else if (!course.hasSlot()) {
            System.out.println("No available slots.");
        } else {
            student.register(course);
            System.out.println("Course registered successfully.");
        }
    }

    static void removeCourse() {

        System.out.print("Enter course code to remove: ");
        String code = sc.next();

        Course course = findCourse(code);

        if (course == null) {
            System.out.println("Course not found.");
        } else if (!student.isRegistered(course)) {
            System.out.println("You are not registered for this course.");
        } else {
            student.remove(course);
            System.out.println("Course removed successfully.");
        }
    }

    static void menu() {

        int choice;

        do {
            System.out.println("\n===== COURSE REGISTRATION =====");
            System.out.println("1. Display Courses");
            System.out.println("2. Register Course");
            System.out.println("3. Remove Course");
            System.out.println("4. My Courses");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    displayCourses();
                    break;

                case 2:
                    registerCourse();
                    break;

                case 3:
                    removeCourse();
                    break;

                case 4:
                    student.displayCourses();
                    break;

                case 5:
                    System.out.println("Thank you!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 5);
    }

    public static void main(String[] args) {

        addCourses();
        menu();

        sc.close();
    }
}
