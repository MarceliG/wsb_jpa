package com.jpacourse.dto;

import java.time.LocalDateTime;
import java.util.List;

public class VisitTO {

    private LocalDateTime time;
    private String doctorName;
    private List<String> medicalTreatments;

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public List<String> getMedicalTreatments() {
        return medicalTreatments;
    }

    public void setMedicalTreatments(List<String> medicalTreatments) {
        this.medicalTreatments = medicalTreatments;
    }
}
