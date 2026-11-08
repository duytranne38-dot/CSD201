package studentmanagement;

public class TreeNode {

    String studentId;
    String[] courses;
    int courseCount;
    TreeNode left;
    TreeNode right;

    public TreeNode(String studentId) {
        this.studentId = studentId;
        this.courses = new String[10];
        this.courseCount = 0;
        this.left = null;
        this.right = null;
    }

    public void addCourse(String courseCode) {
        if (courseCount < courses.length) {
            courses[courseCount] = courseCode;
            courseCount++;
        }
    }

    public String courseList() {
        String result = "";
        for (int i = 0; i < courseCount; i++) {
            result += courses[i];
            if (i < courseCount - 1) {
                result += ", ";
            }
        }
        return result;
    }
}
