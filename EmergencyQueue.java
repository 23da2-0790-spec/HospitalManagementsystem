/**
 * FIFO Queue that manages patients waiting in the emergency unit.
 * Requirement 2 of the assignment: enqueue, dequeue, display, empty handling.
 * Implemented using a linked structure (front/rear pointers) rather than
 * java.util.Queue, so the underlying data structure is hand-built.
 */
public class EmergencyQueue {

    private class Node {
        Patient patient;
        Node next;

        Node(Patient patient) {
            this.patient = patient;
            this.next = null;
        }
    }

    private Node front, rear;
    private int size;

    public EmergencyQueue() {
        front = rear = null;
        size = 0;
    }

    /** Add a patient to the back of the waiting queue. */
    public void enqueue(Patient patient) {
        Node newNode = new Node(patient);
        if (rear == null) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
        System.out.println("Patient " + patient.getName() + " (ID: " + patient.getPatientId()
                + ") added to the emergency queue.");
    }

    /** Remove and return the patient at the front of the queue (next to be treated). */
    public Patient dequeue() {
        if (isEmpty()) {
            System.out.println("The emergency queue is empty. No patient to treat.");
            return null;
        }
        Patient treated = front.patient;
        front = front.next;
        if (front == null) rear = null;
        size--;
        return treated;
    }

    /** Display every patient currently waiting, in queue order. */
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("The emergency queue is currently empty.");
            return;
        }
        Node current = front;
        int position = 1;
        while (current != null) {
            System.out.println(position + ". " + current.patient);
            current = current.next;
            position++;
        }
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int getSize() {
        return size;
    }
}
