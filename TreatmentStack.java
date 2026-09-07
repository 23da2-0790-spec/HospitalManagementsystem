/**
 * LIFO Stack that stores completed treatment records.
 * Requirement 3 of the assignment: push, pop, display, empty handling.
 * Implemented using a linked structure (top pointer).
 */
public class TreatmentStack {

    private class Node {
        TreatmentRecord record;
        Node next;

        Node(TreatmentRecord record) {
            this.record = record;
            this.next = null;
        }
    }

    private Node top;
    private int size;

    public TreatmentStack() {
        top = null;
        size = 0;
    }

    /** Push a newly completed treatment record onto the stack. */
    public void push(TreatmentRecord record) {
        Node newNode = new Node(record);
        newNode.next = top;
        top = newNode;
        size++;
        System.out.println("Treatment record for " + record.getPatientName() + " saved to history.");
    }

    /** Pop (remove) the most recently completed treatment record. */
    public TreatmentRecord pop() {
        if (isEmpty()) {
            System.out.println("Treatment history is empty. Nothing to remove.");
            return null;
        }
        TreatmentRecord removed = top.record;
        top = top.next;
        size--;
        return removed;
    }

    /** Display all treatment records, most recently completed first. */
    public void displayStack() {
        if (isEmpty()) {
            System.out.println("No treatment records found.");
            return;
        }
        Node current = top;
        while (current != null) {
            System.out.println(current.record);
            current = current.next;
        }
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int getSize() {
        return size;
    }
}
