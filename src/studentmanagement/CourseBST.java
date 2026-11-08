package studentmanagement;

public class CourseBST {

    TreeNode root;

    // Save a registered course under the student's ID (key = student ID)
    public void addCourse(String studentId, String courseCode) {
        if (root == null) {
            root = new TreeNode(studentId);
            root.addCourse(courseCode);
            return;
        }
        TreeNode current = root;
        while (true) {
            int cmp = studentId.compareTo(current.studentId);
            if (cmp == 0) {
                current.addCourse(courseCode);
                return;
            } else if (cmp < 0) {
                if (current.left == null) {
                    current.left = new TreeNode(studentId);
                    current.left.addCourse(courseCode);
                    return;
                }
                current = current.left;
            } else {
                if (current.right == null) {
                    current.right = new TreeNode(studentId);
                    current.right.addCourse(courseCode);
                    return;
                }
                current = current.right;
            }
        }
    }

    // Search course codes by student ID: O(log n) on a balanced tree
    public TreeNode search(String studentId) {
        TreeNode current = root;
        while (current != null) {
            int cmp = studentId.compareTo(current.studentId);
            if (cmp == 0) {
                return current;
            } else if (cmp < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return null;
    }

    // Same search, but prints the path so we can see how the tree is used
    public TreeNode searchWithPath(String studentId) {
        TreeNode current = root;
        int steps = 0;
        System.out.print("  Path: ");
        while (current != null) {
            steps++;
            System.out.print(current.studentId + " ");
            int cmp = studentId.compareTo(current.studentId);
            if (cmp == 0) {
                System.out.println("-> found after " + steps + " step(s)");
                return current;
            } else if (cmp < 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        System.out.println("-> not found after " + steps + " step(s)");
        return null;
    }
}
