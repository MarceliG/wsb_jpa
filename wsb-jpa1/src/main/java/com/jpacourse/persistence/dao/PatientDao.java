package com.jpacourse.persistence.dao;

import java.time.LocalDateTime;
import java.util.List;

import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;

public interface PatientDao extends Dao<PatientEntity, Long> {
    VisitEntity addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitTime, String visitDescription);
    List<PatientEntity> findByPatientName(String patientName);
    List<PatientEntity> findWithMoreThanXVisits(Long visitCount);
    List<PatientEntity> findByPatientPhoneNumber(String visitCount);
    void updatPatient(Long patientId, String newFirstName);
}