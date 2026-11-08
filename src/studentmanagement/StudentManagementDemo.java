package studentmanagement;

/**
 * Student Management System - Computational Thinking in CSD201.
 *
 * Linked List -> store student list
 * Queue       -> course registration queue
 * BST         -> search by student ID (returns the registered course codes)
 */
public class StudentManagementDemo {

    public static void main(String[] args) {
        StudentList list = new StudentList();
        RegistrationQueue queue = new RegistrationQueue();
        CourseBST bst = new CourseBST();

        // 1. Enrollment: store students in the Linked List
        System.out.println("===== 1. ENROLLMENT (LINKED LIST) =====");
        list.add(new Student("SE003", "An", "Software Engineering"));
        list.add(new Student("SE001", "Binh", "Artificial Intelligence"));
        list.add(new Student("SE005", "Chi", "Information Assurance"));
        list.add(new Student("SE002", "Dung", "Software Engineering"));
        list.add(new Student("SE004", "Hoa", "Digital Art"));
        list.showList();

        // 2. Linked list update: change the major of a student
        System.out.println("\n===== 2. UPDATE STUDENT (LINKED LIST) =====");
        if (list.update("SE005", "Chi", "Software Engineering")) {
            System.out.println("Updated SE005: " + list.find("SE005"));
        }

        // 3. Queueing: students register for courses (first come, first served)
        System.out.println("\n===== 3. COURSE REGISTRATION (QUEUE) =====");
        enqueue(queue, list.find("SE003"), "CSD201");
        enqueue(queue, list.find("SE001"), "DBI202");
        enqueue(queue, list.find("SE005"), "CSD201");
        enqueue(queue, list.find("SE004"), "CSD201");
        enqueue(queue, list.find("SE004"), "JPD123");
        enqueue(queue, list.find("SE003"), "DBI202");

        System.out.println("\nProcessing registrations:");
        while (!queue.isEmpty()) {
            Registration reg = queue.dequeue();
            bst.addCourse(reg.student.id, reg.courseCode);
            System.out.println("Dequeue: " + reg.student.name
                    + " registered " + reg.courseCode + " successfully");
        }

        // 4. Searching: find the courses of a student by ID using the BST
        System.out.println("\n===== 4. SEARCH BY STUDENT ID (BST) =====");
        String[] searchIds = {"SE004", "SE002"};
        for (String id : searchIds) {
            System.out.println("Search " + id + ":");
            TreeNode result = bst.searchWithPath(id);
            if (result != null) {
                System.out.println("  Courses: " + result.courseList());
            } else {
                System.out.println("  " + id + " has not registered any course");
            }
        }

        // 5. Experiment for our research question
        System.out.println("\n===== 5. PERFORMANCE TEST: LINKED LIST vs BST =====");
        PerformanceTest.run();
    }

    static void enqueue(RegistrationQueue queue, Student student, String course) {
        queue.enqueue(new Registration(student, course));
        System.out.println("Enqueue: " + student.name + " -> " + course);
    }
}
