/**
 * Singly Linked List used to store a single patient's visit history.
 * Requirement 4 of the assignment: add, remove, search, display.
 */
public class VisitLinkedList {

    private class Node {
        Visit visit;
        Node next;

        Node(Visit visit) {
            this.visit = visit;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public VisitLinkedList() {
        head = null;
        size = 0;
    }

    /** Add a new visit to the end of the list. */
    public void addVisit(Visit visit) {
        Node newNode = new Node(visit);
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

    /** Remove a visit by its visit ID. Returns true if removed. */
    public boolean removeVisit(int visitId) {
        if (head == null) return false;

        if (head.visit.getVisitId() == visitId) {
            head = head.next;
            size--;
            return true;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.visit.getVisitId() == visitId) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    /** Search for a visit by visit ID. Returns null if not found. */
    public Visit searchVisit(int visitId) {
        Node current = head;
        while (current != null) {
            if (current.visit.getVisitId() == visitId) {
                return current.visit;
            }
            current = current.next;
        }
        return null;
    }

    /** Display every visit in the list, in insertion order. */
    public void displayVisits() {
        if (head == null) {
            System.out.println("   No visit history found for this patient.");
            return;
        }
        Node current = head;
        while (current != null) {
            System.out.println("   " + current.visit);
            current = current.next;
        }
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int getSize() {
        return size;
    }
}
