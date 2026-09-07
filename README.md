# Mini Hospital Emergency Management System

**Module:** CIT300 - Data Structures and Algorithms
**Assignment:** Individual Mid Assignment
**Language:** Java

## Overview

A console-based Java application that simulates a hospital's emergency
department. It demonstrates four core data structures, each implemented
from scratch (no built-in `java.util.Stack` / `Queue` / `TreeMap`):

| Requirement                     | Data Structure          | File(s)                                  |
|----------------------------------|--------------------------|-------------------------------------------|
| Patient Records                  | Binary Search Tree (BST) | `PatientBST.java`, `Patient.java`          |
| Emergency Patient Queue          | Queue (FIFO)              | `EmergencyQueue.java`                      |
| Treatment History                | Stack (LIFO)              | `TreatmentStack.java`, `TreatmentRecord.java` |
| Patient Visit History            | Singly Linked List        | `VisitLinkedList.java`, `Visit.java`       |
| Application entry point / menu   | —                         | `HospitalSystem.java`                      |

## Features

### 1. Patient Records (Binary Search Tree)
- Insert a new patient, keyed by Patient ID
- Search for a patient by Patient ID
- Delete a patient (handles 0, 1, and 2-child deletion cases)
- In-order traversal to list all patients in ascending Patient ID order

### 2. Emergency Patient Queue (Queue)
- Enqueue a registered patient into the waiting line
- Dequeue the next patient for treatment (FIFO)
- Display everyone currently waiting
- Graceful handling of an empty queue

### 3. Treatment History (Stack)
- Push a completed treatment record after a patient is treated
- Pop the most recently completed record (undo)
- Display all treatment records, most recent first (LIFO)
- Graceful handling of an empty stack

### 4. Patient Visit History (Singly Linked List)
- Each `Patient` owns its own linked list of past visits
- Add a new visit, remove a visit by ID, search by visit ID
- Display the full visit history for a patient

## Project Structure

```
HospitalEMS/
├── src/
│   ├── Patient.java
│   ├── PatientBST.java
│   ├── EmergencyQueue.java
│   ├── TreatmentRecord.java
│   ├── TreatmentStack.java
│   ├── Visit.java
│   ├── VisitLinkedList.java
│   └── HospitalSystem.java   <- main() / console menu
├── screenshots/               <- program output screenshots go here
├── .gitignore
└── README.md
```

## How to Compile and Run

From inside the `src/` folder:

```bash
javac *.java -d ../bin
java -cp ../bin HospitalSystem
```

Or open the project in IntelliJ IDEA / Eclipse / VS Code and run
`HospitalSystem.java` directly.

## Sample Usage

1. Register a patient (Patient Records menu → option 1)
2. Add that patient to the emergency queue (Queue menu → option 1)
3. Treat the next patient — this dequeues them and pushes a treatment
   record onto the stack (Queue menu → option 2)
4. View the treatment history (Treatment History menu → option 1)
5. Add a visit record for a patient (Visit History menu → option 1)

## Design Notes

- The Queue and Stack are implemented with custom linked node classes
  rather than `java.util` collections, to demonstrate understanding of
  the underlying structures as required by the assignment.
- The BST delete operation uses the in-order successor approach for
  nodes with two children.
- Each `Patient` object holds a reference to its own `VisitLinkedList`,
  so visit history is naturally scoped per patient.

## Author

[Your Name] — [Your Student ID]
CIT300 - Data Structures and Algorithms
