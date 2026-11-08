package studentmanagement;

public class Student {

    String id;
    String name;
    String major;

    public Student(String id, String name, String major) {
        this.id = id;
        this.name = name;
        this.major = major;
    }

    @Override
    public String toString() {
        return id + " - " + name + " (" + major + ")";
    }
}
