/**
 * Binary Search Tree that stores every registered Patient,
 * keyed by Patient ID. Requirement 1 of the assignment:
 * insert, search, delete, in-order traversal.
 */
public class PatientBST {

    private class Node {
        Patient patient;
        Node left, right;

        Node(Patient patient) {
            this.patient = patient;
            left = right = null;
        }
    }

    private Node root;

    public PatientBST() {
        root = null;
    }

    /** Insert a new patient into the tree using Patient ID as the key. */
    public void insert(Patient patient) {
        root = insertRec(root, patient);
    }

    private Node insertRec(Node node, Patient patient) {
        if (node == null) {
            return new Node(patient);
        }
        if (patient.getPatientId() < node.patient.getPatientId()) {
            node.left = insertRec(node.left, patient);
        } else if (patient.getPatientId() > node.patient.getPatientId()) {
            node.right = insertRec(node.right, patient);
        } else {
            System.out.println("Patient ID " + patient.getPatientId() + " already exists. Insert cancelled.");
        }
        return node;
    }

    /** Search for a patient using their Patient ID. Returns null if not found. */
    public Patient search(int patientId) {
        Node result = searchRec(root, patientId);
        return (result == null) ? null : result.patient;
    }

    private Node searchRec(Node node, int patientId) {
        if (node == null || node.patient.getPatientId() == patientId) {
            return node;
        }
        if (patientId < node.patient.getPatientId()) {
            return searchRec(node.left, patientId);
        }
        return searchRec(node.right, patientId);
    }

    /** Delete a patient record by Patient ID. */
    public void delete(int patientId) {
        if (search(patientId) == null) {
            System.out.println("Patient ID " + patientId + " not found. Nothing deleted.");
            return;
        }
        root = deleteRec(root, patientId);
        System.out.println("Patient ID " + patientId + " deleted successfully.");
    }

    private Node deleteRec(Node node, int patientId) {
        if (node == null) return null;

        if (patientId < node.patient.getPatientId()) {
            node.left = deleteRec(node.left, patientId);
        } else if (patientId > node.patient.getPatientId()) {
            node.right = deleteRec(node.right, patientId);
        } else {
            // Node found
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            // Two children: replace with in-order successor (smallest in right subtree)
            Node successor = findMin(node.right);
            node.patient = successor.patient;
            node.right = deleteRec(node.right, successor.patient.getPatientId());
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /** In-order traversal: displays all patients in ascending order of Patient ID. */
    public void inorderTraversal() {
        if (root == null) {
            System.out.println("No patient records found.");
            return;
        }
        inorderRec(root);
    }

    private void inorderRec(Node node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.println(node.patient);
            inorderRec(node.right);
        }
    }

    public boolean isEmpty() {
        return root == null;
    }
}
