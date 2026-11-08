package studentmanagement;

public class RegistrationQueue {

    QueueNode front;
    QueueNode rear;

    public boolean isEmpty() {
        return front == null;
    }

    // Add a registration request to the rear of the queue: O(1)
    public void enqueue(Registration reg) {
        QueueNode newNode = new QueueNode(reg);
        if (rear == null) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
    }

    // Remove the request at the front of the queue: O(1)
    public Registration dequeue() {
        if (isEmpty()) {
            return null;
        }
        Registration reg = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        return reg;
    }
}
