package com.jpacourse.service;

import java.util.List;

import com.jpacourse.dto.PatientTO;

public interface PatientService {
    PatientTO savePatient(PatientTO patientTO);

    PatientTO getPatient(Long id);

    List<PatientTO> getAllPatients();

    void deletePatient(Long id);
}
