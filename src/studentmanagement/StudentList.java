package studentmanagement;

public class StudentList {

    Node head;
    int size;

    // Add a new student to the end of the list
    public void add(Student student) {
        Node newNode = new Node(student);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    // Find a student by ID (linear search): O(n)
    public Student find(String id) {
        Node current = head;
        while (current != null) {
            if (current.data.id.equals(id)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    // Update the name and major of a student by ID
    public boolean update(String id, String newName, String newMajor) {
        Student student = find(id);
        if (student == null) {
            return false;
        }
        student.name = newName;
        student.major = newMajor;
        return true;
    }

    // Display all students
    public void showList() {
        System.out.println("Student List (" + size + " students):");
        Node current = head;
        while (current != null) {
            System.out.println("  " + current.data);
            current = current.next;
        }
    }
}
