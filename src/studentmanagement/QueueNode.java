package studentmanagement;

public class QueueNode {

    Registration data;
    QueueNode next;

    public QueueNode(Registration data) {
        this.data = data;
        this.next = null;
    }
}
