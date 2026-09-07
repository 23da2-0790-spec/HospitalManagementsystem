import java.util.Scanner;

/**
 * Mini Hospital Emergency Management System
 * CIT300 - Data Structures and Algorithms - Individual Mid Assignment
 *
 * Ties together four data structures:
 *  - PatientBST      (Binary Search Tree)  -> patient records, keyed by Patient ID
 *  - EmergencyQueue   (Queue, FIFO)         -> patients waiting for treatment
 *  - TreatmentStack   (Stack, LIFO)         -> completed treatment history
 *  - VisitLinkedList  (Singly Linked List)  -> each patient's past visit history
 */
public class HospitalSystem {

    private static PatientBST patientBST = new PatientBST();
    private static EmergencyQueue emergencyQueue = new EmergencyQueue();
    private static TreatmentStack treatmentStack = new TreatmentStack();
    private static Scanner scanner = new Scanner(System.in);
    private static int nextVisitId = 1;

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1 -> patientMenu();
                case 2 -> queueMenu();
                case 3 -> stackMenu();
                case 4 -> visitHistoryMenu();
                case 0 -> {
                    System.out.println("Exiting Hospital Emergency Management System. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    // ---------------------------------------------------------------
    // MAIN MENU
    // ---------------------------------------------------------------
    private static void printMainMenu() {
        System.out.println("\n===== MINI HOSPITAL EMERGENCY MANAGEMENT SYSTEM =====");
        System.out.println("1. Patient Records (BST)");
        System.out.println("2. Emergency Patient Queue");
        System.out.println("3. Treatment History (Stack)");
        System.out.println("4. Patient Visit History (Linked List)");
        System.out.println("0. Exit");
    }

    // ---------------------------------------------------------------
    // 1. PATIENT RECORDS - BST
    // ---------------------------------------------------------------
    private static void patientMenu() {
        System.out.println("\n--- Patient Records (BST) ---");
        System.out.println("1. Register New Patient");
        System.out.println("2. Search Patient by ID");
        System.out.println("3. Delete Patient");
        System.out.println("4. Display All Patients (In-order)");
        System.out.println("0. Back to Main Menu");
        int choice = readInt("Enter your choice: ");

        switch (choice) {
            case 1 -> registerPatient();
            case 2 -> {
                int id = readInt("Enter Patient ID to search: ");
                Patient found = patientBST.search(id);
                System.out.println(found != null ? "Found -> " + found : "Patient not found.");
            }
            case 3 -> {
                int id = readInt("Enter Patient ID to delete: ");
                patientBST.delete(id);
            }
            case 4 -> {
                System.out.println("\nAll Patients (ascending Patient ID):");
                patientBST.inorderTraversal();
            }
            case 0 -> { /* return to main menu */ }
            default -> System.out.println("Invalid choice.");
        }
    }

    private static void registerPatient() {
        int id = readInt("Enter Patient ID: ");
        if (patientBST.search(id) != null) {
            System.out.println("A patient with this ID already exists.");
            return;
        }
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        int age = readInt("Enter Age: ");
        System.out.print("Enter Contact Number: ");
        String contact = scanner.nextLine();
        System.out.print("Enter Medical Condition: ");
        String condition = scanner.nextLine();

        Patient patient = new Patient(id, name, age, contact, condition);
        patientBST.insert(patient);
        System.out.println("Patient registered successfully.");
    }

    // ---------------------------------------------------------------
    // 2. EMERGENCY QUEUE
    // ---------------------------------------------------------------
    private static void queueMenu() {
        System.out.println("\n--- Emergency Patient Queue ---");
        System.out.println("1. Add Registered Patient to Queue (Enqueue)");
        System.out.println("2. Treat Next Patient (Dequeue)");
        System.out.println("3. Display Waiting Queue");
        System.out.println("0. Back to Main Menu");
        int choice = readInt("Enter your choice: ");

        switch (choice) {
            case 1 -> {
                int id = readInt("Enter Patient ID to add to queue: ");
                Patient patient = patientBST.search(id);
                if (patient == null) {
                    System.out.println("No such patient. Register the patient first (option 1).");
                } else {
                    emergencyQueue.enqueue(patient);
                }
            }
            case 2 -> {
                Patient treated = emergencyQueue.dequeue();
                if (treated != null) {
                    System.out.println("Now treating: " + treated);
                    System.out.print("Enter treatment summary: ");
                    String summary = scanner.nextLine();
                    System.out.print("Enter completion date (e.g. 2026-09-07): ");
                    String date = scanner.nextLine();
                    TreatmentRecord record = new TreatmentRecord(
                            treated.getPatientId(), treated.getName(),
                            treated.getMedicalCondition(), summary, date);
                    treatmentStack.push(record);
                }
            }
            case 3 -> {
                System.out.println("\nPatients Currently Waiting:");
                emergencyQueue.displayQueue();
            }
            case 0 -> { /* return to main menu */ }
            default -> System.out.println("Invalid choice.");
        }
    }

    // ---------------------------------------------------------------
    // 3. TREATMENT HISTORY - STACK
    // ---------------------------------------------------------------
    private static void stackMenu() {
        System.out.println("\n--- Treatment History (Stack) ---");
        System.out.println("1. Display Treatment Records");
        System.out.println("2. Undo / Remove Most Recent Treatment Record (Pop)");
        System.out.println("0. Back to Main Menu");
        int choice = readInt("Enter your choice: ");

        switch (choice) {
            case 1 -> {
                System.out.println("\nTreatment Records (most recent first):");
                treatmentStack.displayStack();
            }
            case 2 -> {
                TreatmentRecord removed = treatmentStack.pop();
                if (removed != null) {
                    System.out.println("Removed record: " + removed);
                }
            }
            case 0 -> { /* return to main menu */ }
            default -> System.out.println("Invalid choice.");
        }
    }

    // ---------------------------------------------------------------
    // 4. PATIENT VISIT HISTORY - SINGLY LINKED LIST
    // ---------------------------------------------------------------
    private static void visitHistoryMenu() {
        int id = readInt("Enter Patient ID to manage visit history: ");
        Patient patient = patientBST.search(id);
        if (patient == null) {
            System.out.println("No such patient. Register the patient first.");
            return;
        }

        System.out.println("\n--- Visit History for " + patient.getName() + " ---");
        System.out.println("1. Add New Visit");
        System.out.println("2. Remove a Visit");
        System.out.println("3. Search for a Visit");
        System.out.println("4. Display Full Visit History");
        System.out.println("0. Back to Main Menu");
        int choice = readInt("Enter your choice: ");

        VisitLinkedList history = patient.getVisitHistory();

        switch (choice) {
            case 1 -> {
                System.out.print("Enter Visit Date (e.g. 2026-09-07): ");
                String date = scanner.nextLine();
                System.out.print("Enter Doctor Name: ");
                String doctor = scanner.nextLine();
                System.out.print("Enter Diagnosis: ");
                String diagnosis = scanner.nextLine();
                System.out.print("Enter Treatment: ");
                String treatment = scanner.nextLine();

                Visit visit = new Visit(nextVisitId++, date, doctor, diagnosis, treatment);
                history.addVisit(visit);
                System.out.println("Visit added with Visit ID: " + visit.getVisitId());
            }
            case 2 -> {
                int visitId = readInt("Enter Visit ID to remove: ");
                boolean removed = history.removeVisit(visitId);
                System.out.println(removed ? "Visit removed." : "Visit ID not found.");
            }
            case 3 -> {
                int visitId = readInt("Enter Visit ID to search: ");
                Visit found = history.searchVisit(visitId);
                System.out.println(found != null ? "Found -> " + found : "Visit not found.");
            }
            case 4 -> {
                System.out.println("\nVisit History:");
                history.displayVisits();
            }
            case 0 -> { /* return to main menu */ }
            default -> System.out.println("Invalid choice.");
        }
    }

    // ---------------------------------------------------------------
    // HELPER: safe integer input
    // ---------------------------------------------------------------
    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            try {
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
