/**
 * Represents a completed treatment, pushed onto the TreatmentStack
 * once a patient's emergency treatment is finished.
 */
public class TreatmentRecord {
    private int patientId;
    private String patientName;
    private String medicalCondition;
    private String treatmentSummary;
    private String completionDate;

    public TreatmentRecord(int patientId, String patientName, String medicalCondition,
                            String treatmentSummary, String completionDate) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.medicalCondition = medicalCondition;
        this.treatmentSummary = treatmentSummary;
        this.completionDate = completionDate;
    }

    public int getPatientId() { return patientId; }
    public String getPatientName() { return patientName; }

    @Override
    public String toString() {
        return "Patient ID: " + patientId +
                " | Name: " + patientName +
                " | Condition: " + medicalCondition +
                " | Treatment: " + treatmentSummary +
                " | Completed: " + completionDate;
    }
}
